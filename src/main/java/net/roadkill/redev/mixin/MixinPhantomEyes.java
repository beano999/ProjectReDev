package net.roadkill.redev.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.PhantomEyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.roadkill.redev.core.entity.SpecialPhantom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EyesLayer.class)
public class MixinPhantomEyes
{
    private static final RenderType RED_EYES    = RenderType.eyes(new ResourceLocation("textures/entity/phantom_eyes_red.png"));
    private static final RenderType BLUE_EYES   = RenderType.eyes(new ResourceLocation("textures/entity/phantom_eyes_blue.png"));
    private static final RenderType GREEN_EYES  = RenderType.eyes(new ResourceLocation("textures/entity/phantom_eyes_green.png"));
    private static final RenderType HOLLOW_EYES = RenderType.eyes(new ResourceLocation("textures/entity/phantom_eyes_hollow.png"));

    EyesLayer<?, ?> self = (EyesLayer<?, ?>)(Object)this;

    @Redirect(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/Entity;FFFFFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/EyesLayer;renderType()Lnet/minecraft/client/renderer/RenderType;"))
    private RenderType renderType(EyesLayer<?, ?> instance, PoseStack ps, MultiBufferSource buffer, int p_116985_, Entity entity,
                                  float p_116987_, float p_116988_, float p_116989_, float p_116990_, float p_116991_, float p_116992_)
    {
        if (entity instanceof SpecialPhantom phantom && self instanceof PhantomEyesLayer)
        {
            return switch (phantom.getPhantomType())
            {   case RED    -> RED_EYES;
                case BLUE   -> BLUE_EYES;
                case GREEN  -> GREEN_EYES;
                case HOLLOW -> HOLLOW_EYES;
                default     -> instance.renderType();
            };
        }
        return instance.renderType();
    }
}
