package net.roadkill.redev.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TwistingVinesBlock;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.core.init.BlockInit;
import net.roadkill.redev.util.registries.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TwistingVinesBlock.class)
public class MixinTwistingVines
{
    @Inject(method = "getBodyBlock", at = @At("RETURN"), cancellable = true)
    private void onGetBodyBlock(CallbackInfoReturnable<Block> cir)
    {   cir.setReturnValue(BlockInit.TWISTING_VINES_PLANT.value());
    }
}
