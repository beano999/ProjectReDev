package net.roadkill.redev.mixin;

import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Pig;
import net.roadkill.redev.client.renderer.layer.PigHelmetLayer;
import net.roadkill.redev.client.renderer.layer.TNTLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PigRenderer.class)
public abstract class MixinPigTNTRenderer extends MobRenderer<Pig, PigModel<Pig>>
{


    public MixinPigTNTRenderer(EntityRendererProvider.Context pContext, PigModel<Pig> pModel, float pShadowRadius) {
        super(pContext, pModel, pShadowRadius);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addTNT(EntityRendererProvider.Context context ,CallbackInfo ci)
    {
        this.addLayer(new TNTLayer<>(this, new PigModel<>(context.bakeLayer(ModelLayers.PIG_SADDLE)), new ResourceLocation("textures/block/tnt_side.png")));
        this.addLayer(new PigHelmetLayer<>(this));
    }
}

