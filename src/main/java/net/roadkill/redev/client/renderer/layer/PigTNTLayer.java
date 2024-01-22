package net.roadkill.redev.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.block.Blocks;
import net.roadkill.redev.mixin_interfaces.IPig;

public class PigTNTLayer<T extends Entity, M extends EntityModel<T>> extends RenderLayer<T, M>
{
    public PigTNTLayer(RenderLayerParent<T, M> pRenderer)
    {
        super(pRenderer);
    }

    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (pLivingEntity instanceof Pig)
        {
            IPig pigTNT = ((IPig) pLivingEntity);
            int fuse = pigTNT.getFuse();
            if (fuse == -1)
            {   fuse = 85;
            }
            if (pigTNT.hasTNT())
            {
                if ((float) fuse - pPartialTicks + 1.0F < 10.0F)
                {   float f = 1.0F - ((float)fuse - pPartialTicks + 1.0F) / 10.0F;
                    f = Mth.clamp(f, 0.0F, 1.0F);
                    f *= f;
                    f *= f;
                    float f1 = 1.0F + f * 0.3F;
                    pMatrixStack.scale(f1, f1, f1);
                }
                BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
                pMatrixStack.pushPose();
                pMatrixStack.mulPose(Axis.ZP.rotationDegrees(180));
                pMatrixStack.translate(-.405F, -1.075F, .05F);
                pMatrixStack.scale(.4F, .4F, .4F);
                TntMinecartRenderer.renderWhiteSolidBlock(blockRenderer, Blocks.TNT.defaultBlockState(), pMatrixStack, pBuffer, pPackedLight, fuse / 5 % 2 == 0);
                pMatrixStack.popPose();

                pMatrixStack.pushPose();
                pMatrixStack.mulPose(Axis.ZP.rotationDegrees(180));
                pMatrixStack.translate(0F, -1.075F, .05F);
                pMatrixStack.scale(.4F, .4F, .4F);
                TntMinecartRenderer.renderWhiteSolidBlock(blockRenderer, Blocks.TNT.defaultBlockState(), pMatrixStack, pBuffer, pPackedLight, fuse / 5 % 2 == 0);
                pMatrixStack.popPose();
            }
        }
    }
}
