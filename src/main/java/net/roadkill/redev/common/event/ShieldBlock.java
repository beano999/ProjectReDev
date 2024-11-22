package net.roadkill.redev.common.event;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;
import net.roadkill.redev.core.init.ItemInit;
import net.roadkill.redev.util.registries.ModItems;

@EventBusSubscriber
public class ShieldBlock
{
    @SubscribeEvent
    public static void cancelProjectileHit(ProjectileImpactEvent event)
    {
        if (!(event.getRayTraceResult() instanceof EntityHitResult entityHit)) return;
        if (!(entityHit.getEntity() instanceof LivingEntity entity)) return;
        if (entity.isUsingItem() && entity.getUseItem().is(ItemInit.INFERNAL_PLATE)
        && entity.isDamageSourceBlocked(entity.damageSources().mobProjectile(event.getProjectile(), null)))
        {   //event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void blockDamage(LivingShieldBlockEvent event)
    {
        if (!event.getOriginalBlock()) return;

        LivingEntity entity = event.getEntity();

        // Halve damage blocked by Vanilla shield
        if (entity.getUseItem().is(Items.SHIELD))
        {   event.setBlockedDamage(event.getBlockedDamage() / 2);
        }
        // The rest of the logic is for the Inferno Plate
        if (!entity.getUseItem().is(ItemInit.INFERNAL_PLATE))
        {   return;
        }
        // Protection from direct attacks is halved again
        if (event.getDamageSource().is(DamageTypes.MOB_ATTACK) || event.getDamageSource().is(DamageTypes.PLAYER_ATTACK))
        {   event.setBlockedDamage(event.getBlockedDamage() / 2);
        }
        // The rest of the logic is for reflecting projectiles
        if (!(event.getDamageSource().getDirectEntity() instanceof Projectile projectile) || event.getDamageSource().getEntity() == null)
        {   return;
        }

        // spawn flame particles in front of the player
        if (entity.level() instanceof ServerLevel level)
        for (int i = 0; i < 10; i++)
        {
            Vec3 pos = entity.getEyePosition(1.0f);
            Vec3 dir = new Vec3(entity.getViewVector(1.0f).x, 0, entity.getViewVector(1.0f).z);
            Vec3 offset = dir.scale(0.5).add(0, -0.5, 0);
            Vec3 target = pos.add(dir.scale(2.0)).add(offset);
            level.sendParticles(ParticleTypes.FLAME, target.x, target.y, target.z, 1, 0.2, 0.2, 0.2, 0.05);
        }

        Entity shooter = event.getDamageSource().getEntity();

        // Calculate the direction to the shooter
        Vec3 arrowPosition = projectile.position();
        Vec3 shooterPosition = shooter.getEyePosition();

        // Account for the shooter's motion (assuming a simple linear prediction)
        Vec3 shooterVelocity = shooter.getDeltaMovement();
        double distance = arrowPosition.distanceTo(shooterPosition);
        double timeToHit = distance / projectile.getDeltaMovement().length(); // simplistic estimation
        Vec3 predictedShooterPosition = shooterPosition.add(shooterVelocity.scale(timeToHit));

        // Set the arrow's new trajectory
        Vec3 reflectedMotion = predictedShooterPosition.subtract(arrowPosition)
                               .normalize()
                               .scale(projectile.getDeltaMovement().length());

        // Copy the entity to circumvent weird delta movement behavior
        CompoundTag projectileData = new CompoundTag();
        projectile.save(projectileData);
        Entity copy = EntityType.create(projectileData, projectile.level(), EntitySpawnReason.EVENT).orElse(null);
        if (copy == null) return;
        // Remove old projectile
        projectile.remove(Entity.RemovalReason.DISCARDED);

        // Reflect slow, non-gravity projectiles like wither skulls and fireballs
        if (copy instanceof AbstractHurtingProjectile noGravityProjectile)
        {   Vec3 motion = reflectedMotion.normalize().scale(0.95);
            noGravityProjectile.setDeltaMovement(motion);
        }
        // Reflect arrows, accounting for gravity
        else if (copy instanceof AbstractArrow && !copy.isNoGravity())
        {   reflectedMotion = reflectedMotion.add(0, distance / 2 * 0.05, 0);
        }

        // Add reflected projectile entity
        projectile.setOwner(event.getEntity());
        copy.lookAt(EntityAnchorArgument.Anchor.FEET, predictedShooterPosition);
        copy.setDeltaMovement(reflectedMotion);
        shooter.level().addFreshEntity(copy);
    }
}
