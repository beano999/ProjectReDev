package net.roadkill.redev.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.util.ARGB;
import net.roadkill.redev.client.model.entity.RevenantModel;
import net.roadkill.redev.client.renderer.entity.RevenantRenderer;

public class RevenantVeinsLayer<S extends SkeletonRenderState, M extends RevenantModel<S>> extends RenderLayer<S, M>
{
    private static final RenderType VEINS_OVERLAY = RenderType.entityTranslucentEmissive(RevenantRenderer.TEXTURE_VEINS, false);

    public RevenantVeinsLayer(RenderLayerParent<S, M> parentLayer)
    {   super(parentLayer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, S renderState, float p_117353_, float p_117354_)
    {
        float alpha = (float) Math.sin((renderState.ageInTicks + renderState.partialTick) / 16) / 8 + 0.7F;
        this.getParentModel().renderWithVeins(pPoseStack, pBuffer.getBuffer(VEINS_OVERLAY), pPackedLight,
                                              LivingEntityRenderer.getOverlayCoords(renderState, 0.0F),
                                              ARGB.colorFromFloat(alpha, 1.0F, 1.0F, 1.0F));
    }
}
