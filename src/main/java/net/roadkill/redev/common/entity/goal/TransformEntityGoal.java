package net.roadkill.redev.common.entity.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
// import net.minecraft.world.entity.monster.Zombie;
import net.roadkill.redev.common.entity.LithicanEntity;

public class TransformEntityGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    private final LithicanEntity lithican;

    public TransformEntityGoal(LithicanEntity lithican, Class<T> targetClass, boolean mustSee) {
        super(lithican, targetClass, mustSee);
        this.lithican = lithican;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && !(this.target instanceof LithicanEntity);
    }
}