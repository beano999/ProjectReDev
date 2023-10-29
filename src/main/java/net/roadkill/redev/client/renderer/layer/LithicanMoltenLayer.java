package net.roadkill.redev.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.roadkill.redev.client.model.entity.LithicanModel;
import net.roadkill.redev.client.renderer.entity.LithicanRenderer;
import net.roadkill.redev.common.entity.LithicanEntity;
import net.roadkill.redev.util.RDMath;

public class LithicanMoltenLayer<T extends LithicanEntity, M extends LithicanModel<T>> extends RenderLayer<T, M>
{
    private static final RenderType MOLTEN_OVERLAY = RenderType.entityTranslucentEmissive(LithicanRenderer.TEXTURE_HEAT);

    public LithicanMoltenLayer(RenderLayerParent<T, M> parentLayer)
    {   super(parentLayer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity,
                       float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks,
                       float pNetHeadYaw, float pHeadPitch)
    {
        this.getParentModel().renderToBuffer(pPoseStack, pBuffer.getBuffer(MOLTEN_OVERLAY), pPackedLight, LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F), 1.0F, 1.0F, 1.0F, RDMath.clamp(pLivingEntity.getHeat() / 100f, 0f, 1f));
    }
}
