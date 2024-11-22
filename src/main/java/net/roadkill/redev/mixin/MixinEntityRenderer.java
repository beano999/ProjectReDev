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
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.scores.PlayerTeam;
import net.roadkill.redev.mixin_interfaces.IInvisibleRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinEntityRenderer<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<S>>
{
    private final LivingEntityRenderer<T, S, M> renderer = (LivingEntityRenderer<T, S, M>) (Object) this;
    private S renderState = null;

    @Inject(method = "render(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At("HEAD"))
    public void storeRenderState(S renderState, PoseStack poseStack, MultiBufferSource bufferSource, int something, CallbackInfo ci)
    {   this.renderState = renderState;
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V", at = @At("TAIL"))
    public void addRenderStateData(LivingEntity entity, S renderState, float partialTick, CallbackInfo ci)
    {
        IInvisibleRenderState invisibleState = (IInvisibleRenderState) renderState;

        invisibleState.setPartialInvisible(entity.getPersistentData().getBoolean("PartialInvisible"));
        invisibleState.setTeam(entity.getTeam());
        invisibleState.setSpectator(entity.isSpectator());
    }

    @ModifyArg(method = "render(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V"),
              index = 4)
    public int setRenderOpacity(int color)
    {
        IInvisibleRenderState invisibleState = (IInvisibleRenderState) renderState;
        if (Minecraft.getInstance().player != null)
        {
            float alpha = invisibleState.isPartialInvisible()
                        ? 0.3f
                        : renderState.isInvisible && invisibleState.getTeam() != null
                          && Objects.equals(invisibleState.getTeam(), Minecraft.getInstance().player.getTeam())
                          && invisibleState.getTeam().canSeeFriendlyInvisibles()
                        ? 0.15f
                        : -1f;

            if (alpha != -1)
            {   return ARGB.color((int) (alpha * 255), color);
            }
        }
        return color;
    }

    @Redirect(method = "render(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/RenderLayer;render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/renderer/entity/state/EntityRenderState;FF)V"))
    public void conditionalLayerRender(RenderLayer<S, M> layer, PoseStack poseStack, MultiBufferSource bufferSource, int i, EntityRenderState renderState, float v1, float v2)
    {
        IInvisibleRenderState invisibleState = (IInvisibleRenderState) renderState;

        if (invisibleState.isSpectator()) return;
        if (invisibleState.isPartialInvisible()
        && !(layer instanceof EyesLayer || layer instanceof ItemInHandLayer))
        {   return;
        }
        layer.render(poseStack, bufferSource, i, this.renderState, v1, v1);
    }

    @Inject(method = "getRenderType",
            at = @At("HEAD"), cancellable = true)
    public void getRenderType(S renderState, boolean a, boolean b, boolean c, CallbackInfoReturnable<RenderType> cir)
    {
        IInvisibleRenderState invisibleState = (IInvisibleRenderState) renderState;

        if (invisibleState.isPartialInvisible())
        {   cir.setReturnValue(RenderType.entityTranslucent(renderer.getTextureLocation(renderState)));
        }
    }

    @Mixin(LivingEntityRenderState.class)
    public static abstract class MixinLivingRenderState extends EntityRenderState implements IInvisibleRenderState
    {
        public boolean isPartialInvisible = false;
        public PlayerTeam team;
        public boolean isSpectator;

        @Override
        public boolean isPartialInvisible()
        {   return isPartialInvisible;
        }
        @Override
        public void setPartialInvisible(boolean invisible)
        {   isPartialInvisible = invisible;
        }

        @Override
        public PlayerTeam getTeam()
        {   return team;
        }
        @Override
        public void setTeam(PlayerTeam team)
        {   this.team = team;
        }

        @Override
        public boolean isSpectator()
        {   return isSpectator;
        }
        @Override
        public void setSpectator(boolean spectator)
        {   isSpectator = spectator;
        }
    }
}
