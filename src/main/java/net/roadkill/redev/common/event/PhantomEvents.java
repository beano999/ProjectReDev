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
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.util.ObfuscationReflectionHelper;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.roadkill.redev.core.entity.PhantomType;
import net.roadkill.redev.core.entity.SpecialPhantom;
import net.roadkill.redev.core.network.message.PhantomTypeSyncMessage;

import java.lang.reflect.Field;

@EventBusSubscriber
public class PhantomEvents
{
    @SubscribeEvent
    public static void onPhantomSnowball(LivingIncomingDamageEvent event)
    {
        if (event.getEntity() instanceof Phantom phantom && event.getSource().getDirectEntity() instanceof Snowball)
        {
            if (((SpecialPhantom) phantom).getPhantomType() == PhantomType.BLUE)
            {   event.setCanceled(true);
            }
            else event.setAmount(Math.max(event.getAmount(), 4));
        }
    }

    @SubscribeEvent
    public static void beforePhantomHurt(LivingIncomingDamageEvent event)
    {
        if (event.getEntity() instanceof Phantom phantom && phantom instanceof SpecialPhantom special)
        {
            PhantomType type = special.getPhantomType();
            if (type == PhantomType.RED && event.getSource().type().effects() == DamageEffects.BURNING)
            {   event.setCanceled(true);
            }
            else if (type == PhantomType.HOLLOW)
            {   event.setCanceled(true);
                poofHollowPhantom(phantom);
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
                poofHollowPhantom(phantom);
            }
        }
    }

    static void poofHollowPhantom(Phantom phantom)
    {
        Vec3 pos = phantom.position();
        if (phantom.level() instanceof ServerLevel serverLevel)
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
        {   PacketDistributor.sendToPlayer(player, new PhantomTypeSyncMessage(phantom, special.getPhantomType()));
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

    private static final Field ANCHOR_POINT = ObfuscationReflectionHelper.findField(Phantom.class, "anchorPoint");
    private static final Field MOVE_POINT = ObfuscationReflectionHelper.findField(Phantom.class, "moveTargetPoint");
    static
    {   ANCHOR_POINT.setAccessible(true);
        MOVE_POINT.setAccessible(true);
    }

    @SubscribeEvent
    public static void phantomTick(EntityTickEvent.Pre event)
    {
        if (event.getEntity() instanceof Phantom phantom)
        {
            // Spawn ender particles
            if (phantom.level().isClientSide)
            {
                if (((SpecialPhantom) phantom).getPhantomType() != PhantomType.HOLLOW)
                {   RandomSource random = phantom.getRandom();
                    Vec3 speed = phantom.getDeltaMovement();
                    if (phantom.tickCount % 3 == 0 || random.nextDouble() < (Math.abs(speed.x) * Math.abs(speed.z) + Math.abs(speed.y)) * 10)
                    {
                        Vec3 pos = phantom.position().add(random.nextDouble() - 0.5,
                                                          random.nextDouble() - 0.5,
                                                          random.nextDouble() - 0.5);
                        phantom.level().addAlwaysVisibleParticle(ParticleTypes.REVERSE_PORTAL, true, pos.x, pos.y, pos.z,
                                                  random.nextDouble() * 0.04 - 0.02, 0.06, random.nextDouble() * 0.04 - 0.02);
                    }
                }
            }
            else
            {
                if (phantom.isInWater() && ((SpecialPhantom) phantom).getPhantomType() != PhantomType.BLUE)
                {   phantom.hurt(phantom.level().damageSources().drown(), 2);
                }

                if (phantom.horizontalCollision && !phantom.noPhysics)
                {   phantom.noPhysics = true;
                    phantom.getPersistentData().putInt("NoclipCooldown", 80);

                    try
                    {
                        BlockPos anchorPoint = (BlockPos) ANCHOR_POINT.get(phantom);
                        BlockPos pos = phantom.blockPosition();
                        int levelHeight = phantom.level().getHeight(Heightmap.Types.MOTION_BLOCKING, pos.getX(), pos.getZ()) + 10;

                        ANCHOR_POINT.set(phantom, new BlockPos(anchorPoint.getX(), levelHeight, anchorPoint.getZ()));
                    }
                    catch (Exception ignored) {}
                    try
                    {   Vec3 move = (Vec3) MOVE_POINT.get(phantom);
                        MOVE_POINT.set(phantom, new Vec3(move.x + phantom.getRandom().nextInt(-20, 20),
                                                        move.y + 10,
                                                        move.z + phantom.getRandom().nextInt(-20, 20)));
                    } catch (Exception ignored) {}
                }

                CompoundTag tag = phantom.getPersistentData();
                if (tag.contains("NoclipCooldown"))
                {   int clipCooldown = tag.getInt("NoclipCooldown");
                    tag.putInt("NoclipCooldown", clipCooldown - 1);

                    if (clipCooldown <= 0)
                    {   phantom.noPhysics = false;
                        tag.remove("NoclipCooldown");
                    }
                }
            }
        }
    }
}