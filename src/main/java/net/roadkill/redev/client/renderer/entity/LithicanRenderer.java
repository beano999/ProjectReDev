package net.roadkill.redev.client.renderer.entity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.client.model.entity.LithicanModel;
import net.roadkill.redev.common.entity.Lithican;

public class LithicanRenderer<T extends Lithican, M extends LithicanModel<T>> extends AbstractZombieRenderer<T, M>
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(ReDev.MOD_ID, "textures/entity/lithican.png");

    public LithicanRenderer(EntityRendererProvider.Context pContext)
    {   this(pContext, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
    }

    private LithicanRenderer(EntityRendererProvider.Context pContext, ModelLayerLocation pZombieLayer, ModelLayerLocation pInnerArmor, ModelLayerLocation pOuterArmor) {
        super(pContext, (M) new LithicanModel(pContext.bakeLayer(pZombieLayer)), (M) new LithicanModel(pContext.bakeLayer(pInnerArmor)), (M) new LithicanModel(pContext.bakeLayer(pOuterArmor)));
    }

    @Override
    public ResourceLocation getTextureLocation(Lithican pEntity)
    {   return TEXTURE;
    }
}
