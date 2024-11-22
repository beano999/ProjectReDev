package net.roadkill.redev.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.roadkill.redev.client.renderer.render_sate.HoveringInfernoRenderState;
import net.roadkill.redev.common.entity.HoveringInfernoEntity;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class MixinHoveringInfernoFire
{
    @Inject(method = "renderFlame", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V"))
    public void moveFire(PoseStack poseStack, MultiBufferSource buffer, EntityRenderState renderState, Quaternionf rotation, CallbackInfo ci)
    {
        if (renderState instanceof HoveringInfernoRenderState infernoState)
        {
            Player player = Minecraft.getInstance().player;
            if (player == null) return;
            HoveringInfernoEntity inferno = infernoState.entity;
            Vec3 toPlayer = inferno.position().subtract(player.position()).normalize().scale(0.5);
            poseStack.translate(toPlayer.x(), toPlayer.y(), toPlayer.z());
        }
    }
}
