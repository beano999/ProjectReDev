package net.roadkill.redev.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.util.ARGB;

public class RevenantModel<S extends SkeletonRenderState> extends SkeletonModel<S>
{
    public RevenantModel(ModelPart root)
    {   super(root);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int packedColor)
    {
        int transparentColor = ARGB.color(178, packedColor);
        super.renderToBuffer(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, transparentColor);
    }

    public void renderWithVeins(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int packedColor)
    {
        int transparentColor = ARGB.color(230, packedColor);
        super.renderToBuffer(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, transparentColor);
    }
}
