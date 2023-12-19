package net.roadkill.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.roadkill.redev.ReDev;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBehaviour.class)
public class MixinWalkSnow
{
    @Inject(method = "entityInside", at = @At("HEAD"))
    public void onStepSnow(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo ci)
    {
        if (entity instanceof LivingEntity living && !living.isFallFlying() && !(living instanceof Player player && player.getAbilities().flying)
        && state.is(Blocks.SNOW) && living.getItemBySlot(EquipmentSlot.FEET).isEmpty())
        {   Vec3 motion = living.getDeltaMovement();
            living.setDeltaMovement(motion.x / 1.25, motion.y, motion.z / 1.25);
        }
    }
}
