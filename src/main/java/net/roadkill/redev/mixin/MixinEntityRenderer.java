package net.roadkill.redev.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.roadkill.redev.ReDev;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinEntityRenderer<T extends LivingEntity, M extends EntityModel<T>>
{
    private final LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>> renderer = (LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>) (Object) this;
    private LivingEntity entity = null;

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At("HEAD"), remap = ReDev.REMAP_MIXINS)
    public void storeData(T entity, float f1, float f2, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci)
    {   this.entity = entity;
    }

    @ModifyArg(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;IIFFFF)V"),
              index = 7, remap = ReDev.REMAP_MIXINS)
    public float setRenderOpacity(float alpha)
    {
        if (Minecraft.getInstance().player != null)
        {
            return entity.getPersistentData().getBoolean("PartialInvisible")
                 ? 0.3f : entity.isInvisible() && entity.getTeam() != null
                          && Objects.equals(entity.getTeam(), Minecraft.getInstance().player.getTeam())
                          && entity.getTeam().canSeeFriendlyInvisibles()
                        ? 0.15f
                        : alpha;
        }
        return alpha;
    }

    @Redirect(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/RenderLayer;render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/Entity;FFFFFF)V"),
              remap = ReDev.REMAP_MIXINS)
    public void conditionalLayerRender(RenderLayer<Entity, EntityModel<Entity>> layer, PoseStack stack, MultiBufferSource bufferSource, int i, Entity t, float f1, float f2, float f3, float f4, float f5, float f6)
    {   if (entity.isSpectator()) return;
        if (entity.getPersistentData().getBoolean("PartialInvisible")
        && !(layer instanceof EyesLayer || layer instanceof ItemInHandLayer<?,?>))
        {   return;
        }
        layer.render(stack, bufferSource, i, t, f1, f2, f3, f4, f5, f6);
    }

    @Inject(method = "getRenderType(Lnet/minecraft/world/entity/LivingEntity;ZZZ)Lnet/minecraft/client/renderer/RenderType;",
            at = @At("HEAD"), cancellable = true, remap = ReDev.REMAP_MIXINS)
    public void getRenderType(T entity, boolean bool1, boolean bool2, boolean bool3, CallbackInfoReturnable<RenderType> cir)
    {
        if (entity.getPersistentData().getBoolean("PartialInvisible"))
        {   cir.setReturnValue(RenderType.entityTranslucent(renderer.getTextureLocation(entity)));
        }
    }
}
