package net.momostudios.redev.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
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
import net.momostudios.redev.core.entity.PhantomType;
import net.momostudios.redev.core.entity.SpecialPhantom;
import net.momostudios.redev.core.network.ReDevPacketHandler;
import net.momostudios.redev.core.network.message.PhantomTypeSyncMessage;

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
    public static void onPhantomBurn(LivingAttackEvent event)
    {
        if (event.getEntity() instanceof Phantom phantom && ((SpecialPhantom) phantom).getPhantomType() == PhantomType.RED
        && event.getSource().type().effects() == DamageEffects.BURNING)
        {   event.setCanceled(true);
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
            if (phantom.level.isClientSide)
            {
                RandomSource random = phantom.getRandom();
                Vec3 speed = phantom.getDeltaMovement();
                if (phantom.tickCount % 3 == 0 || random.nextDouble() < (Math.abs(speed.x) * Math.abs(speed.z) + Math.abs(speed.y)) * 10)
                {
                    phantom.level.addParticle(ParticleTypes.REVERSE_PORTAL, true,
                                              event.getEntity().getX() + random.nextDouble() - 0.5,
                                              event.getEntity().getY() + random.nextDouble() - 0.5,
                                              event.getEntity().getZ() + random.nextDouble() - 0.5,
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
