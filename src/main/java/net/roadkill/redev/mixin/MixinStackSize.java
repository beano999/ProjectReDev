package net.roadkill.redev.mixin;

import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SnowballItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class MixinStackSize
{
    Item item = (Item) (Object) this;

    @Inject(method = "getMaxStackSize()I", at = @At(value = "HEAD"), cancellable = true)
    private void setStackSize(CallbackInfoReturnable<Integer> cir)
    {
        if (item instanceof SnowballItem || item instanceof EggItem)
        {   cir.setReturnValue(64);
        }
    }
}
