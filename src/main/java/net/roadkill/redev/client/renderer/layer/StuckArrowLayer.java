package net.roadkill.redev.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;

public class StuckArrowLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private final EntityRenderDispatcher dispatcher;

    public StuckArrowLayer(EntityRendererProvider.Context pContext, LivingEntityRenderer<T, M> pRenderer)
    {   super(pRenderer);
        this.dispatcher = pContext.getEntityRenderDispatcher();
    }

    protected int numStuck(T pEntity) {
        return pEntity.getArrowCount();
    }

    protected void renderStuckItem(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, Entity pEntity, float pX, float pY, float pZ, float pPartialTick) {
        float f = Mth.sqrt(pX * pX + pZ * pZ);
        Arrow arrow = new Arrow(pEntity.level, pEntity.getX(), pEntity.getY(), pEntity.getZ());
        arrow.setYRot((float)(Math.atan2((double)pX, (double)pZ) * (double)(180F / (float)Math.PI)));
        arrow.setXRot((float)(Math.atan2((double)pY, (double)f) * (double)(180F / (float)Math.PI)));
        arrow.yRotO = arrow.getYRot();
        arrow.xRotO = arrow.getXRot();
        this.dispatcher.render(arrow, 0.0D, 0.0D, 0.0D, 0.0F, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        int i = this.numStuck(pLivingEntity);
        RandomSource randomsource = RandomSource.create(pLivingEntity.getId());
        if (i > 0) {
            for(int j = 0; j < i; ++j) {
                pMatrixStack.pushPose();
                ModelPart modelpart = this.getRandomModelPart(randomsource);
                ModelPart.Cube modelpart$cube = modelpart.getRandomCube(randomsource);
                modelpart.translateAndRotate(pMatrixStack);
                float f = randomsource.nextFloat();
                float f1 = randomsource.nextFloat();
                float f2 = randomsource.nextFloat();
                float f3 = Mth.lerp(f, modelpart$cube.minX, modelpart$cube.maxX) / 16.0F;
                float f4 = Mth.lerp(f1, modelpart$cube.minY, modelpart$cube.maxY) / 16.0F;
                float f5 = Mth.lerp(f2, modelpart$cube.minZ, modelpart$cube.maxZ) / 16.0F;
                pMatrixStack.translate(f3, f4, f5);
                f = -1.0F * (f * 2.0F - 1.0F);
                f1 = -1.0F * (f1 * 2.0F - 1.0F);
                f2 = -1.0F * (f2 * 2.0F - 1.0F);
                this.renderStuckItem(pMatrixStack, pBuffer, pPackedLight, pLivingEntity, f, f1, f2, pPartialTicks);
                pMatrixStack.popPose();
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
