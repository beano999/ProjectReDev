package net.roadkill.redev.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.network.PacketDistributor;
import net.roadkill.redev.core.entity.PhantomType;
import net.roadkill.redev.core.entity.SpecialPhantom;
import net.roadkill.redev.core.network.ReDevPacketHandler;
import net.roadkill.redev.core.network.message.PhantomTypeSyncMessage;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber
public class PhantomEvents
{
    @SubscribeEvent
    public static void onPhantomSnowball(LivingHurtEvent event)
    {
        if (event.getSource().getDirectEntity() instanceof Snowball)
        {   event.setAmount(Math.max(event.getAmount(), 4));
        }
    }

    @SubscribeEvent
    public static void beforePhantomHurt(LivingAttackEvent event)
    {
        if (event.getEntity() instanceof Phantom phantom && phantom instanceof SpecialPhantom special)
        {
            PhantomType type = special.getPhantomType();
            if (type == PhantomType.RED && event.getSource().type().effects() == DamageEffects.BURNING)
            {   event.setCanceled(true);
            }
            else if (type == PhantomType.HOLLOW)
            {   event.setCanceled(true);
                despawnHollowPhantom(phantom);
            }
            else if (type == PhantomType.GREEN && event.getSource().getEntity() instanceof Player player)
            {
                ListTag attackers = phantom.getPersistentData().getList("Attackers", 8);
                StringTag playerID = StringTag.valueOf(player.getUUID().toString());
                if (!attackers.contains(playerID))
                {   attackers.add(playerID);
                }
                phantom.getPersistentData().put("Attackers", attackers);
            }
        }
        if (event.getSource().getEntity() instanceof Phantom phantom && phantom instanceof SpecialPhantom special)
        {
            if (special.getPhantomType() == PhantomType.HOLLOW)
            {   event.setCanceled(true);
                despawnHollowPhantom(phantom);
            }
        }
    }

    static void despawnHollowPhantom(Phantom phantom)
    {
        Vec3 pos = phantom.position();
        if (phantom.level instanceof ServerLevel serverLevel)
        {   serverLevel.sendParticles(ParticleTypes.SMOKE, pos.x, pos.y, pos.z, 50,
                                      0.35, 0.35, 0.35, 0.05);
            phantom.remove(Entity.RemovalReason.KILLED);
        }
    }

    @SubscribeEvent
    public static void onPhantomSpawn(EntityJoinLevelEvent event)
    {
        if (event.getEntity() instanceof Phantom phantom && phantom instanceof SpecialPhantom special)
        {
            if (!event.getLevel().isClientSide)
            {   try
                {   special.setPhantomType(PhantomType.valueOf(phantom.getPersistentData().getString("PhantomType").toUpperCase()));
                } catch (Exception ignored) {}
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerStartTracking(PlayerEvent.StartTracking event)
    {
        if (event.getTarget() instanceof Phantom phantom && phantom instanceof SpecialPhantom special
        && event.getEntity() instanceof ServerPlayer player)
        {   ReDevPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player),
                                             new PhantomTypeSyncMessage(phantom, special.getPhantomType()));
        }
    }

    @SubscribeEvent
    public static void onPhantomSave(EntityLeaveLevelEvent event)
    {
        if (!event.getLevel().isClientSide && event.getEntity() instanceof Phantom phantom
        && phantom instanceof SpecialPhantom special)
        {   phantom.getPersistentData().putString("PhantomType", special.getPhantomType().name().toLowerCase());
        }
    }

    @SubscribeEvent
    public static void phantomTick(LivingEvent.LivingTickEvent event)
    {
        if (event.getEntity() instanceof Phantom phantom)
        {
            // Spawn ender particles
            if (phantom.level.isClientSide && ((SpecialPhantom) phantom).getPhantomType() != PhantomType.HOLLOW)
            {
                RandomSource random = phantom.getRandom();
                Vec3 speed = phantom.getDeltaMovement();
                if (phantom.tickCount % 3 == 0 || random.nextDouble() < (Math.abs(speed.x) * Math.abs(speed.z) + Math.abs(speed.y)) * 10)
                {
                    Vec3 pos = phantom.position().add(random.nextDouble() - 0.5,
                                                      random.nextDouble() - 0.5,
                                                      random.nextDouble() - 0.5);
                    phantom.level.addParticle(ParticleTypes.REVERSE_PORTAL, true, pos.x, pos.y, pos.z,
                                              random.nextDouble() * 0.04 - 0.02, 0.06, random.nextDouble() * 0.04 - 0.02);
                }
            }
            else
            {
                if (phantom.horizontalCollision && !phantom.noPhysics)
                {
                    phantom.noPhysics = true;
                    phantom.getPersistentData().putInt("NoClipCooldown", 80);

                    BlockPos anchorPoint = phantom.anchorPoint;
                    BlockPos pos = phantom.blockPosition();
                    int levelHeight = phantom.level.getHeight(Heightmap.Types.MOTION_BLOCKING, pos.getX(), pos.getZ()) + 10;

                    phantom.anchorPoint = new BlockPos(anchorPoint.getX(), levelHeight, anchorPoint.getZ());
                    Field movePoint = ObfuscationReflectionHelper.findField(Phantom.class, "f_33097_");
                    movePoint.setAccessible(true);
                    try
                    {   Vec3 move = (Vec3) movePoint.get(phantom);
                        movePoint.set(phantom, new Vec3(move.x + phantom.getRandom().nextInt(-20, 20),
                                                        move.y + 10,
                                                        move.z + phantom.getRandom().nextInt(-20, 20)));
                    } catch (Exception ignored) {}
                }

                CompoundTag tag = phantom.getPersistentData();
                if (tag.contains("NoClipCooldown"))
                {
                    int clipCooldown = tag.getInt("NoClipCooldown");
                    tag.putInt("NoClipCooldown", clipCooldown - 1);

                    if (clipCooldown <= 0)
                    {   phantom.noPhysics = false;
                        tag.remove("NoClipCooldown");
                    }
                }
            }
        }
    }
}
