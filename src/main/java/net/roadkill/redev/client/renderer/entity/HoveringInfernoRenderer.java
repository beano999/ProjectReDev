package net.roadkill.redev.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.client.model.entity.HoveringInfernoModel;
import net.roadkill.redev.common.entity.HoveringInfernoEntity;

public class HoveringInfernoRenderer<T extends HoveringInfernoEntity, M extends HoveringInfernoModel<T>> extends MobRenderer<HoveringInfernoEntity, HoveringInfernoModel<HoveringInfernoEntity>>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(ReDev.MOD_ID, "textures/entity/hovering_inferno.png");

    public HoveringInfernoRenderer(EntityRendererProvider.Context context)
    {   super(context, new HoveringInfernoModel<>(context.bakeLayer(HoveringInfernoModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(HoveringInfernoEntity entity)
    {   return TEXTURE;
    }

    @Override
    protected int getBlockLightLevel(HoveringInfernoEntity pEntity, BlockPos pPos)
    {   return 15;
    }

    @Override
    protected void setupRotations(HoveringInfernoEntity pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks)
    {   super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, 0, pPartialTicks);
    }
}