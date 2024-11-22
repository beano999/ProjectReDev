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
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.block.Blocks;
import net.roadkill.redev.mixin_interfaces.IPig;

public class PigTNTLayer<T extends Entity, S extends LivingEntityRenderState, M extends EntityModel<S>> extends RenderLayer<S, M>
{
    public PigTNTLayer(RenderLayerParent<S, M> renderer)
    {   super(renderer);
    }

    @Override
    public void render(PoseStack ps, MultiBufferSource buffer, int packedLight, S renderState, float p_117353_, float p_117354_)
    {
        float partialTick = renderState.partialTick;
        if (renderState instanceof IPig pigData)
        {
            int fuse = pigData.getFuse();
            if (fuse == -1)
            {   fuse = 85;
            }
            if (pigData.hasTNT())
            {
                // Exploding animation
                if ((float) fuse - partialTick + 1.0F < 10.0F)
                {
                    float f = 1.0F - ((float)fuse - partialTick + 1.0F) / 10.0F;
                    f = Mth.clamp(f, 0.0F, 1.0F);
                    f *= f;
                    f *= f;
                    float f1 = 1.0F + f * 0.3F;
                    ps.scale(f1, f1, f1);
                    ps.translate(0, -f/4, 0);
                }
                BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
                // Render left TNT
                ps.pushPose();
                ps.mulPose(Axis.ZP.rotationDegrees(180));
                ps.translate(-0.405F, -1.075F, 0.05F);
                ps.scale(0.4F, 0.4F, 0.4F);
                TntMinecartRenderer.renderWhiteSolidBlock(blockRenderer, Blocks.TNT.defaultBlockState(), ps, buffer, packedLight, fuse / 5 % 2 == 0);
                ps.popPose();

                // Render right TNT
                ps.pushPose();
                ps.mulPose(Axis.ZP.rotationDegrees(180));
                ps.translate(0F, -1.075F, 0.05F);
                ps.scale(0.4F, 0.4F, 0.4F);
                TntMinecartRenderer.renderWhiteSolidBlock(blockRenderer, Blocks.TNT.defaultBlockState(), ps, buffer, packedLight, fuse / 5 % 2 == 0);
                ps.popPose();
            }
        }
    }
}
