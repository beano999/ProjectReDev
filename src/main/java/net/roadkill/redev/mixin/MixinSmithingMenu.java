package net.roadkill.redev.mixin;

import net.minecraft.world.inventory.LegacySmithingMenu;
import net.minecraft.world.level.block.SmithingTableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LegacySmithingMenu.class)
public class MixinSmithingMenu
{
    @Inject(at = @At("HEAD"), method = "isValidBlock", cancellable = true)
    public void isValidBlock(net.minecraft.world.level.block.state.BlockState state, CallbackInfoReturnable<Boolean> cir)
    {   cir.setReturnValue(state.getBlock() instanceof SmithingTableBlock);
    }
}
