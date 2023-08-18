package net.roadkill.redev.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class MixinBlockConnections
{
    @Inject(method = "isExceptionForConnection(Lnet/minecraft/world/level/block/state/BlockState;)Z", at = @At("HEAD"), cancellable = true)
    private static void isConnectionException(BlockState state, CallbackInfoReturnable<Boolean> cir)
    {
        if (state.is(Blocks.JIGSAW))
        {   cir.setReturnValue(true);
        }
    }
}
