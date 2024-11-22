package net.roadkill.redev.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PhantomModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.PhantomEyesLayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.PhantomRenderState;
import net.minecraft.resources.ResourceLocation;
import net.roadkill.redev.core.entity.PhantomType;
import net.roadkill.redev.mixin_interfaces.IPhantomColorRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EyesLayer.class)
public class MixinPhantomEyes
{
    private static final RenderType RED_EYES    = RenderType.eyes(ResourceLocation.withDefaultNamespace("textures/entity/phantom_eyes_red.png"));
    private static final RenderType BLUE_EYES   = RenderType.eyes(ResourceLocation.withDefaultNamespace("textures/entity/phantom_eyes_blue.png"));
    private static final RenderType GREEN_EYES  = RenderType.eyes(ResourceLocation.withDefaultNamespace("textures/entity/phantom_eyes_green.png"));
    private static final RenderType HOLLOW_EYES = RenderType.eyes(ResourceLocation.withDefaultNamespace("textures/entity/phantom_eyes_hollow.png"));

    EyesLayer<?, ?> self = (EyesLayer<?, ?>)(Object)this;

    @Redirect(method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/EyesLayer;renderType()Lnet/minecraft/client/renderer/RenderType;"))
    private RenderType renderType(EyesLayer<PhantomRenderState, PhantomModel> instance,
                                  PoseStack poseStack, MultiBufferSource buffer, int packedLight, EntityRenderState renderState, float p_116987_, float p_116988_)
    {
        if (self instanceof PhantomEyesLayer && renderState instanceof IPhantomColorRenderState phantomState)
        {
            return switch (phantomState.getPhantomType())
            {
                case RED    -> RED_EYES;
                case BLUE   -> BLUE_EYES;
                case GREEN  -> GREEN_EYES;
                case HOLLOW -> HOLLOW_EYES;
                default -> instance.renderType();
            };
        }
        return instance.renderType();
    }

    @Mixin(PhantomRenderState.class)
    public static abstract class MixinPhantomRenderState implements IPhantomColorRenderState
    {
        public PhantomType phantomType = PhantomType.NORMAL;

        @Override
        public PhantomType getPhantomType()
        {   return phantomType;
        }

        @Override
        public void setPhantomType(PhantomType phantomType)
        {   this.phantomType = phantomType;
        }
    }
}
