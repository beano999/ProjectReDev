package net.roadkill.redev.client.renderer.entity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.common.entity.DurianThornEntity;

public class DurianThornRenderer extends ArrowRenderer<DurianThornEntity>
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(ReDev.MOD_ID, "textures/entity/durian_thorn.png");

    public DurianThornRenderer(EntityRendererProvider.Context context)
    {   super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(DurianThornEntity entity)
    {   return TEXTURE;
    }
}
