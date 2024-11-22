package net.roadkill.redev.mixin;

import net.minecraft.client.model.AbstractZombieModel;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.world.entity.monster.Monster;
import net.roadkill.redev.client.model.entity.LithicanModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractZombieModel.class)
public class MixinZombieModel<T extends Monster>
{
    AbstractZombieModel<?> self = (AbstractZombieModel<?>) (Object) this;
    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/ZombieRenderState;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/AnimationUtils;animateZombieArms(Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/model/geom/ModelPart;ZFF)V"),
            cancellable = true)
    public void setupAnim(ZombieRenderState zombieRenderState, CallbackInfo ci)
    {
        if (self instanceof LithicanModel)
        {   ci.cancel();
        }
    }
}
