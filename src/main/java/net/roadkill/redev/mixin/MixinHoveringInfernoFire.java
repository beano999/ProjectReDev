package net.roadkill.redev.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.roadkill.redev.common.entity.HoveringInfernoEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class MixinHoveringInfernoFire
{
    private static Entity CURRENT_ENTITY = null;

    @ModifyArg(method = "renderFlame", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V"), index = 1)
    public float modifyHeight(float y)
    {   if (CURRENT_ENTITY instanceof HoveringInfernoEntity)
        {   return 0.8f;
        }
        return y;
    }

    @ModifyArg(method = "renderFlame", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V"), index = 2)
    public float modifyWidth(float x)
    {   if (CURRENT_ENTITY instanceof HoveringInfernoEntity)
        {   return 0.8f;
        }
        return x;
    }

    @Inject(method = "renderFlame", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V"))
    public void moveFire(PoseStack poseStack, MultiBufferSource buffer, Entity entity, CallbackInfo ci)
    {   poseStack.translate(0, 0.2, 0);
        CURRENT_ENTITY = entity;
    }
}
