package net.roadkill.redev.mixin;


import net.minecraft.nbt.StringTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Phantom;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.core.entity.PhantomType;
import net.roadkill.redev.core.entity.SpecialPhantom;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Phantom.PhantomAttackPlayerTargetGoal.class)
public abstract class MixinPhantomTargeting
{
    @Redirect(method = "canUse()Z",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Phantom;canAttack(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/ai/targeting/TargetingConditions;)Z"))
    public boolean canAttack(Phantom phantom, ServerLevel level, LivingEntity target, TargetingConditions conditions)
    {
        SpecialPhantom special = ((SpecialPhantom) phantom);
        PhantomType type = special.getPhantomType();

        boolean hasPlayerAttacked = phantom.getPersistentData().getList("Attackers", 8).contains(StringTag.valueOf(target.getUUID().toString()));
        boolean shouldTarget = target instanceof ServerPlayer player && player.getStats().getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) >= 72000
                               || type == PhantomType.RED;

        return type == PhantomType.GREEN ? hasPlayerAttacked
                                         : shouldTarget
        && phantom.canAttack(level, target, conditions);
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
