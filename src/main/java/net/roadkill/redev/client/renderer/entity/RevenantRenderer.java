package net.roadkill.redev.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.roadkill.redev.client.model.entity.RevenantModel;
import net.roadkill.redev.client.renderer.layer.RevenantVeinsLayer;
import net.roadkill.redev.common.entity.RevenantEntity;
import org.jetbrains.annotations.Nullable;

public class RevenantRenderer<T extends RevenantEntity, M extends RevenantModel<T>> extends HumanoidMobRenderer<T, M>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("redev", "textures/entity/revenant/revenant.png");
    public static final ResourceLocation TEXTURE_VEINS = new ResourceLocation("redev", "textures/entity/revenant/revenant_veins.png");

    public RevenantRenderer(EntityRendererProvider.Context context)
    {   this(context, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
        this.addLayer(new RevenantVeinsLayer<>(this));
    }

    public RevenantRenderer(EntityRendererProvider.Context pContext, ModelLayerLocation layerLocation, ModelLayerLocation pInnerModelLayer, ModelLayerLocation pOuterModelLayer)
    {   super(pContext, (M) new RevenantModel<T>(pContext.bakeLayer(layerLocation)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new RevenantModel<T>(pContext.bakeLayer(pInnerModelLayer)), 
                                                     new RevenantModel<T>(pContext.bakeLayer(pOuterModelLayer)), pContext.getModelManager()));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity)
    {   return TEXTURE;
    }

    @Nullable
    @Override
    protected RenderType getRenderType(T pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing)
    {   return RenderType.entityTranslucent(this.getTextureLocation(pLivingEntity));
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight)
    {   super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
