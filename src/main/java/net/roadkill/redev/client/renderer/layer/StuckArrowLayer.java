package net.roadkill.redev.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArrowModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.roadkill.redev.client.renderer.render_sate.LithicanRenderState;
import net.roadkill.redev.common.entity.LithicanEntity;

public class StuckArrowLayer<T extends LithicanEntity, S extends LithicanRenderState, M extends HumanoidModel<S>> extends RenderLayer<S, M>
{
    private final EntityRenderDispatcher dispatcher;

    public StuckArrowLayer(EntityRendererProvider.Context pContext, LivingEntityRenderer<T, S, M> pRenderer)
    {
        super(pRenderer);
        this.dispatcher = pContext.getEntityRenderDispatcher();
    }

    protected int numStuck(LithicanRenderState renderState)
    {
        return renderState.arrowCount;
    }

    protected void renderStuckItem(PoseStack poseStack, MultiBufferSource buffer, int packedLight, S renderState, float pX, float pY, float pZ, float partialTick)
    {
        float f = Mth.sqrt(pX * pX + pZ * pZ);
        Arrow arrow = new Arrow(null, renderState.x, renderState.y, renderState.z, ItemStack.EMPTY, null);
        arrow.setYRot((float) (Math.atan2(pX, pZ) * 180d / Math.PI));
        arrow.setXRot((float) (Math.atan2(pY, f) * 180d / Math.PI));
        arrow.yRotO = arrow.getYRot();
        arrow.xRotO = arrow.getXRot();
        this.dispatcher.render(arrow, 0.0D, 0.0D, 0.0D, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, S renderState, float p_117353_, float p_117354_)
    {
        int i = renderState.arrowCount;
        RandomSource randomsource = RandomSource.create(renderState.entityId);
        if (i > 0)
        {
            for(int j = 0; j < i; ++j)
            {
                poseStack.pushPose();
                ModelPart modelpart = this.getRandomModelPart(randomsource);
                ModelPart.Cube modelpart$cube = modelpart.getRandomCube(randomsource);
                modelpart.translateAndRotate(poseStack);
                float f = randomsource.nextFloat();
                float f1 = randomsource.nextFloat();
                float f2 = randomsource.nextFloat();
                float f3 = Mth.lerp(f, modelpart$cube.minX, modelpart$cube.maxX) / 16.0F;
                float f4 = Mth.lerp(f1, modelpart$cube.minY, modelpart$cube.maxY) / 16.0F;
                float f5 = Mth.lerp(f2, modelpart$cube.minZ, modelpart$cube.maxZ) / 16.0F;
                poseStack.translate(f3, f4, f5);
                f = -1.0F * (f * 2.0F - 1.0F);
                f1 = -1.0F * (f1 * 2.0F - 1.0F);
                f2 = -1.0F * (f2 * 2.0F - 1.0F);
                this.renderStuckItem(poseStack, buffer, packedLight, renderState, f, f1, f2, renderState.partialTick);
                poseStack.popPose();
            }
        }
    }

    public ModelPart getRandomModelPart(RandomSource pRandom)
    {   return switch (pRandom.nextInt(6))
        {
            case 1  -> this.getParentModel().body;
            case 2  -> this.getParentModel().rightArm;
            case 3  -> this.getParentModel().leftArm;
            case 4  -> this.getParentModel().rightLeg;
            case 5  -> this.getParentModel().leftLeg;
            default -> this.getParentModel().head;
        };
    }
}
