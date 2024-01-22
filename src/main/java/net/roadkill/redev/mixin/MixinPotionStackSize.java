package net.roadkill.redev.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class MixinPotionStackSize
{
    Item self = (Item) (Object) this;

    @Inject(method = "getMaxStackSize",
               at = @At(value = "HEAD"), cancellable = true)
    private void getPotionStackSize(CallbackInfoReturnable<Integer> cir)
    {
        if (self instanceof PotionItem)
        {   cir.setReturnValue(4);
        }
    }
}
