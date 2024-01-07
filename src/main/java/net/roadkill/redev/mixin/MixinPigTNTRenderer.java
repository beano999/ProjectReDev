package net.roadkill.redev.mixin;

import net.minecraft.client.model.PigModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.world.entity.animal.Pig;
import net.roadkill.redev.client.renderer.layer.PigHelmetLayer;
import net.roadkill.redev.client.renderer.layer.PigTNTLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PigRenderer.class)
public abstract class MixinPigTNTRenderer extends MobRenderer<Pig, PigModel<Pig>>
{
    public MixinPigTNTRenderer(EntityRendererProvider.Context pContext, PigModel<Pig> pModel, float pShadowRadius)
    {   super(pContext, pModel, pShadowRadius);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addTNT(EntityRendererProvider.Context context ,CallbackInfo ci)
    {   this.addLayer(new PigTNTLayer<>(this));
        this.addLayer(new PigHelmetLayer<>(this));
    }
}