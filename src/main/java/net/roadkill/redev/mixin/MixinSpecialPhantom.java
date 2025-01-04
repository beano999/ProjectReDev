package net.roadkill.redev.mixin;

import net.minecraft.world.entity.monster.Phantom;
import net.roadkill.redev.core.entity.PhantomType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Phantom.class)
public abstract class MixinSpecialPhantom
{
    @Redirect(method = "aiStep()V",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Phantom;igniteForSeconds(F)V"))
    public void onPhantomBurn(Phantom phantom, float seconds)
    {
        if (PhantomType.get(phantom) == PhantomType.RED)
        {   phantom.clearFire();
        }
        else phantom.igniteForSeconds(seconds);
    }
}
