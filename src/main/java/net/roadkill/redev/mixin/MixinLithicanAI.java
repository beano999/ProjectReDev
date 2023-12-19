package net.roadkill.redev.mixin;

import net.minecraft.world.entity.Mob;
import net.roadkill.redev.common.entity.LithicanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public class MixinLithicanAI
{
    private Mob self = (Mob) (Object) this;

    @Inject(method = "serverAiStep()V", at = @At("HEAD"), cancellable = true)
    public void serverAiStep(CallbackInfo ci)
    {
        if (self instanceof LithicanEntity lithican && !lithican.isActive())
        {   ci.cancel();
        }
    }
}
