package net.roadkill.redev.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.client.model.entity.LithicanModel;
import net.roadkill.redev.client.renderer.layer.LithicanMoltenLayer;
import net.roadkill.redev.client.renderer.layer.StuckArrowLayer;
import net.roadkill.redev.common.entity.LithicanEntity;

public class LithicanRenderer<T extends LithicanEntity, M extends LithicanModel<T>> extends AbstractZombieRenderer<T, M>
{
    public static final ResourceLocation TEXTURE_STONE = new ResourceLocation(ReDev.MOD_ID, "textures/entity/lithican/stone_lithican.png");
    public static final ResourceLocation TEXTURE_SANDSTONE = new ResourceLocation(ReDev.MOD_ID, "textures/entity/lithican/sandstone_lithican.png");
    public static final ResourceLocation TEXTURE_HEAT = new ResourceLocation(ReDev.MOD_ID, "textures/entity/lithican/molten_lithican.png");

    public LithicanRenderer(EntityRendererProvider.Context pContext)
    {   this(pContext, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
        this.addLayer(new StuckArrowLayer<>(pContext, this));
        this.addLayer(new LithicanMoltenLayer<>(this));
    }

    private LithicanRenderer(EntityRendererProvider.Context pContext, ModelLayerLocation pZombieLayer, ModelLayerLocation pInnerArmor, ModelLayerLocation pOuterArmor)
    {   super(pContext, (M) new LithicanModel(pContext.bakeLayer(pZombieLayer)),
                        (M) new LithicanModel(pContext.bakeLayer(pInnerArmor)),
                        (M) new LithicanModel(pContext.bakeLayer(pOuterArmor)));
    }

    @Override
    public ResourceLocation getTextureLocation(LithicanEntity pEntity)
    {   return switch (pEntity.getVariant())
        {
            case 0 -> TEXTURE_STONE;
            default -> TEXTURE_SANDSTONE;
        };
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight)
    {   super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
