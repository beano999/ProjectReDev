package net.roadkill.redev.common.event;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.util.registries.ModItems;

@Mod.EventBusSubscriber
public class ShieldBlock
{
    @SubscribeEvent
    public static void blockDamage(ShieldBlockEvent event)
    {
        LivingEntity entity = event.getEntity();
        if(entity.getUseItem().is(Items.SHIELD))
        {
            event.setBlockedDamage(event.getBlockedDamage() / 2);
        }
        if(!entity.getUseItem().is(ModItems.INFERNO_PLATE))
        {
            return;
        }
        if(entity.getUseItem().is(ModItems.INFERNO_PLATE) && (event.getDamageSource().is(DamageTypes.MOB_ATTACK) || event.getDamageSource().is(DamageTypes.PLAYER_ATTACK)))
        {
            event.setBlockedDamage(event.getBlockedDamage() / 2);
        }

        if (!(event.getDamageSource().getDirectEntity() instanceof Projectile projectile) || event.getDamageSource().getEntity() == null) {return;}

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
        Entity copy = projectile.getType().create(projectile.level);
        copy.deserializeNBT(projectile.serializeNBT());
        projectile.remove(Entity.RemovalReason.DISCARDED);

        // Reflect slow, non-gravity projectiles like wither skulls and fireballs
        if (copy instanceof AbstractHurtingProjectile noGravityProjectile)
        {   Vec3 motion = reflectedMotion.normalize().scale(0.95);
            noGravityProjectile.xPower = motion.x;
            noGravityProjectile.yPower = motion.y;
            noGravityProjectile.zPower = motion.z;
        }
        // Reflect arrows, accounting for gravity
        else if (copy instanceof AbstractArrow && !copy.isNoGravity())
        {   reflectedMotion = reflectedMotion.add(0, distance / 2 * 0.05, 0);
        }

        // Add reflected projectile entity
        projectile.setOwner(event.getEntity());
        copy.lookAt(EntityAnchorArgument.Anchor.FEET, predictedShooterPosition);
        copy.setDeltaMovement(reflectedMotion);
        shooter.level.addFreshEntity(copy);
    }
}
