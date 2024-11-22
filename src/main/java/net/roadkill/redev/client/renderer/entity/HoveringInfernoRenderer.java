package net.roadkill.redev.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.client.model.entity.HoveringInfernoModel;
import net.roadkill.redev.client.renderer.render_sate.HoveringInfernoRenderState;
import net.roadkill.redev.common.entity.HoveringInfernoEntity;

public class HoveringInfernoRenderer<T extends HoveringInfernoEntity, S extends HoveringInfernoRenderState, M extends HoveringInfernoModel<S>> extends MobRenderer<T, S, HoveringInfernoModel<S>>
{
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "textures/entity/hovering_inferno.png");

    public HoveringInfernoRenderer(EntityRendererProvider.Context context)
    {   super(context, new HoveringInfernoModel<>(context.bakeLayer(HoveringInfernoModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public S createRenderState()
    {   return (S) new HoveringInfernoRenderState();
    }

    @Override
    public void extractRenderState(T inferno, S renderState, float partialTick)
    {
        super.extractRenderState(inferno, renderState, partialTick);
        renderState.entity = inferno;
    }

    @Override
    protected int getBlockLightLevel(HoveringInfernoEntity pEntity, BlockPos pPos)
    {   return 15;
    }

    @Override
    protected void setupRotations(S renderState, PoseStack poseStack, float yBodyRot, float p_115320_)
    {   super.setupRotations(renderState, poseStack, 0, p_115320_);
    }

    @Override
    public ResourceLocation getTextureLocation(S renderState)
    {   return TEXTURE;
    }
}