package net.roadkill.redev.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(PotionItem.class)
public class MixinPotionItem extends Item
{
    @Override
    public boolean isFoil(ItemStack pStack)
    {   return true;
    }

    public MixinPotionItem(Properties properties)
    {   super(properties);
    }

    @Mixin(MobEffect.class)
    public static class MixinPotionColor
    {
        MobEffect self = (MobEffect) (Object) this;

        @Inject(method = "getColor", at = @At("HEAD"), cancellable = true)
        private void getPotionColor(CallbackInfoReturnable<Integer> cir)
        {
            String id = self.getDescriptionId();
            switch (id)
            {
                case "effect.minecraft.strength"     -> cir.setReturnValue(11351375);
                case "effect.minecraft.night_vision" -> cir.setReturnValue(3681914);
                case "effect.minecraft.jump_boost"   -> cir.setReturnValue(12779366);
            }
        }
    }

    @Mixin(ItemRenderer.class)
    public static class MixinReducePotionGlint
    {
        @Inject(method = "renderStatic(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;"
                       + "Lnet/minecraft/world/item/ItemDisplayContext;"
                       + "ZLcom/mojang/blaze3d/vertex/PoseStack;"
                       + "Lnet/minecraft/client/renderer/MultiBufferSource;"
                       + "Lnet/minecraft/world/level/Level;III)V", at = @At("HEAD"))
        private void reducePotionGlint(@Nullable LivingEntity entity, ItemStack itemStack, ItemDisplayContext diplayContext, boolean leftHand,
                                       PoseStack poseStack, MultiBufferSource bufferSource, @Nullable Level level, int combinedLight,
                                       int combinedOverlay, int seed, CallbackInfo ci)
        {
            if (itemStack.getItem() instanceof PotionItem)
            {   RenderSystem.setShaderGlintAlpha(Minecraft.getInstance().options.glintStrength().get() / 1.5);
            }
            else
            {   RenderSystem.setShaderGlintAlpha(Minecraft.getInstance().options.glintStrength().get());
            }
        }
    }
}
