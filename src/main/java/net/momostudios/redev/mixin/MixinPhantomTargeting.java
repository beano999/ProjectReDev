package net.momostudios.redev.mixin;


import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Phantom;
import net.momostudios.redev.ReDev;
import net.momostudios.redev.core.entity.PhantomType;
import net.momostudios.redev.core.entity.SpecialPhantom;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Phantom.PhantomAttackPlayerTargetGoal.class)
public abstract class MixinPhantomTargeting
{
    @Redirect(method = "canUse()Z",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Phantom;canAttack(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/ai/targeting/TargetingConditions;)Z"),
              remap = ReDev.REMAP_MIXINS)
    public boolean canAttack(Phantom phantom, LivingEntity target, TargetingConditions conditions)
    {   return target instanceof ServerPlayer player
        && (player.getStats().getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) >= 72000 || ((SpecialPhantom) phantom).getPhantomType() == PhantomType.RED)
        && phantom.canAttack(target, conditions);
    }
}

@Mixin(Phantom.PhantomMoveControl.class)
abstract class MixinPhantomMovement
{
    @Redirect(method = "tick()V",
              at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/monster/Phantom;horizontalCollision:Z", opcode = Opcodes.GETFIELD))
    public boolean onBounce(Phantom phantom)
    {   return !phantom.noPhysics && phantom.horizontalCollision;
    }
}
