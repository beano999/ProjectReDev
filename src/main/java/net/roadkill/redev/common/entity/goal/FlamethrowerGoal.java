package net.roadkill.redev.common.entity.goal;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.roadkill.redev.common.entity.HoveringInfernoEntity;
import net.roadkill.redev.core.network.message.ParticleBatchMessage;
import net.roadkill.redev.util.RDMath;

import java.util.ArrayList;
import java.util.List;

public class FlamethrowerGoal extends Goal
{
    HoveringInfernoEntity entity;
    int ticksRemaining = 0;
    private final float distance = 10;

    public FlamethrowerGoal(HoveringInfernoEntity entity)
    {   this.entity = entity;
    }

    @Override
    public boolean canUse()
    {   return entity.getRandom().nextInt(2) == 0 && entity.getTarget() != null
            && entity.getTarget().isAlive()
            && entity.getTarget().distanceTo(entity) <= distance
            && entity.getTarget().distanceTo(entity) > 4.5
            && entity.getAttackPhase() == HoveringInfernoEntity.AttackPhase.NONE
            && entity.getAttackCooldown() <= 0;
    }

    @Override
    public boolean canContinueToUse()
    {   return entity.getTarget() != null && entity.getTarget().isAlive() && ticksRemaining > 0
            && entity.getAttackPhase() == HoveringInfernoEntity.AttackPhase.FLAMETHROWER
            && entity.getTarget().distanceTo(entity) <= distance;
    }

    @Override
    public void start()
    {   ticksRemaining = entity.getRandom().nextIntBetweenInclusive(10, 40);
        entity.setAttackPhase(HoveringInfernoEntity.AttackPhase.FLAMETHROWER);
    }

    @Override
    public void stop()
    {   entity.setAttackPhase(HoveringInfernoEntity.AttackPhase.NONE);
        entity.setRandomAttackCooldown();
    }

    @Override
    public void tick()
    {
        if (entity.getTarget() != null)
        {
            Vec3 direction = entity.getTarget().position().subtract(entity.position()).normalize();

            if (entity.level() instanceof ServerLevel level)
            {
                ParticleBatchMessage particles = new ParticleBatchMessage();
                for (int i = 0; i < 3; i++)
                {
                    particles.addParticle(ParticleTypes.FLAME, ParticleBatchMessage.ParticlePlacement.of(entity.getX(), entity.getY() + entity.getEyeHeight() / 2, entity.getZ())
                             .spread(entity.getBbWidth() / 2, entity.getBbHeight() / 2, entity.getBbWidth() / 2)
                             .velocity(direction.x * 1, direction.y * 1, direction.z * 1));
                }
                particles.sendEntity(entity);
            }
            if (entity.getRandom().nextInt(2) == 0 || entity.tickCount % 2 == 0)
            {   entity.playSound(SoundEvents.FIRECHARGE_USE, 0.5f, RDMath.randomFloat(entity.getRandom(), 0.7f, 1f));
            }

            AABB hurtBox = entity.getBoundingBox().expandTowards(direction.x * 2, direction.y * 2, direction.z * 2);
            List<AABB> hurtBoxes = List.of(
                    hurtBox,
                    hurtBox.move(direction.x * 2, direction.y * 2, direction.z * 2),
                    hurtBox.move(direction.x * 4, direction.y * 4, direction.z * 4),
                    hurtBox.move(direction.x * 6, direction.y * 6, direction.z * 6),
                    hurtBox.move(direction.x * 8, direction.y * 8, direction.z * 8),
                    hurtBox.move(direction.x * 10, direction.y * 10, direction.z * 10)
            );
            List<Entity> attackedEntities = new ArrayList<>();

            Vec3 blazePos = entity.getEyePosition();

            for (AABB box : hurtBoxes)
            {
                entity.level().getEntities(entity, box).forEach(target ->
                {
                    if (attackedEntities.contains(target)) return;

                    HitResult result = entity.level().clip(new ClipContext(entity.position(), target.position(), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));

                    if ((result.getType() != HitResult.Type.BLOCK || result.getLocation().distanceToSqr(blazePos) > target.distanceToSqr(blazePos)))
                    {
                        if (target instanceof LivingEntity living && living.isDamageSourceBlocked(entity.damageSources().mobAttack(entity))) return;

                        target.hurt(entity.damageSources().inFire(), 3);
                        target.setRemainingFireTicks(Math.max(target.getRemainingFireTicks(), 40));
                        attackedEntities.add(target);
                    }
                });
            }
            this.ticksRemaining--;
        }
    }

    @Override
    public boolean requiresUpdateEveryTick()
    {   return true;
    }

    @Override
    public boolean isInterruptable()
    {   return false;
    }
}
