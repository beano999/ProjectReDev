package net.roadkill.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PumpkinBlock.class)
public class MixinCarvedPumpkin
{
    @Inject(method = "use", at = @At(value = "HEAD"), cancellable = true)
    private void use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, CallbackInfoReturnable<InteractionResult> returnable)
    {
        returnable.setReturnValue(InteractionResult.FAIL);
    }
}
