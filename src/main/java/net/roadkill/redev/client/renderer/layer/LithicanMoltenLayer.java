package net.roadkill.redev.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.util.ARGB;
import net.roadkill.redev.client.model.entity.LithicanModel;
import net.roadkill.redev.client.renderer.entity.LithicanRenderer;
import net.roadkill.redev.client.renderer.render_sate.LithicanRenderState;
import net.roadkill.redev.util.RDMath;

public class LithicanMoltenLayer<S extends LithicanRenderState, M extends LithicanModel<S>> extends RenderLayer<S, M>
{
    private static final RenderType MOLTEN_OVERLAY = RenderType.entityTranslucentEmissive(LithicanRenderer.TEXTURE_HEAT);

    public LithicanMoltenLayer(RenderLayerParent<S, M> parentLayer)
    {   super(parentLayer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, S renderState, float p_117353_, float p_117354_)
    {
        float alpha = (float) RDMath.blend(0f, 1f, renderState.heat, 0, 100);

        this.getParentModel().renderToBuffer(poseStack, buffer.getBuffer(MOLTEN_OVERLAY), packedLight,
                                             LivingEntityRenderer.getOverlayCoords(renderState, 0.0F),
                                             ARGB.colorFromFloat(alpha, 1.0F, 1.0F, 1.0F));
    }
}
