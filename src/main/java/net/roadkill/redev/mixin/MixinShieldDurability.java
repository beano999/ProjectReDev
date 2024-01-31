package net.roadkill.redev.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShieldItem.class)
public class MixinShieldDurability
{
    ShieldItem shieldItem = ((ShieldItem)(Object) this);

    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeShieldDurability(Item.Properties pProperties, CallbackInfo ci)
    {
        if(shieldItem.maxDamage == 336)
        {
            shieldItem.maxDamage = 100;
        }
    }

}
