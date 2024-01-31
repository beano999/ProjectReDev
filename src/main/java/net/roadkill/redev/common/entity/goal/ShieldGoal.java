package net.roadkill.redev.common.entity.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import net.roadkill.redev.common.entity.HoveringInfernoEntity;

public class ShieldGoal extends Goal
{
    HoveringInfernoEntity entity;
    int lastUsedTimestamp = 0;
    int ticksRemaining = 0;

    public ShieldGoal(HoveringInfernoEntity entity)
    {   this.entity = entity;
    }

    @Override
    public void start()
    {   ticksRemaining = 40;
        entity.setAttackPhase(HoveringInfernoEntity.AttackPhase.BLOCKING);
    }

    @Override
    public void stop()
    {   entity.setAttackPhase(HoveringInfernoEntity.AttackPhase.NONE);
        entity.setRandomAttackCooldown(5, 10);
        lastUsedTimestamp = entity.tickCount;
    }

    @Override
    public boolean canUse()
    {   return entity.getRandom().nextInt(2) == 0 && entity.getTarget() != null && entity.getTarget().isAlive()
            // entity is not currently attacking
            && entity.getAttackPhase() == HoveringInfernoEntity.AttackPhase.NONE
            && entity.getAttackCooldown() <= 0
            // can use shield more often if target is close
            && entity.tickCount - lastUsedTimestamp > (entity.distanceTo(entity.getTarget()) < 4.5 ? 60 : 100);
    }

    @Override
    public boolean canContinueToUse()
    {   return ticksRemaining > 0 && entity.getAttackPhase() == HoveringInfernoEntity.AttackPhase.BLOCKING
            && entity.getTarget() != null && entity.getTarget().isAlive();
    }

    @Override
    public void tick()
    {   ticksRemaining--;
    }

    @Override
    public boolean isInterruptable()
    {   return false;
    }
}
