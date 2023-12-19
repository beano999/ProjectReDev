package net.roadkill.redev.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.roadkill.redev.client.model.entity.RevenantModel;
import net.roadkill.redev.client.renderer.entity.RevenantRenderer;
import net.roadkill.redev.common.entity.RevenantEntity;
import net.roadkill.redev.util.RDMath;

public class RevenantVeinsLayer<T extends RevenantEntity, M extends RevenantModel<T>> extends RenderLayer<T, M>
{
    private static final RenderType VEINS_OVERLAY = RenderType.entityTranslucentEmissive(RevenantRenderer.TEXTURE_VEINS);

    public RevenantVeinsLayer(RenderLayerParent<T, M> parentLayer)
    {   super(parentLayer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity,
                       float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks,
                       float pNetHeadYaw, float pHeadPitch)
    {
        this.getParentModel().renderToBuffer(pPoseStack, pBuffer.getBuffer(VEINS_OVERLAY), pPackedLight,
                                             LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F), 1.0F, 1.0F, 1.0F, (float) RDMath.blend(0, 0.5f, pLivingEntity.tickCount % 20 + pPartialTick, 0, 20));
    }
}
