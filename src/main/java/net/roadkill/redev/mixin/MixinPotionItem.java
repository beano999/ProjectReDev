package net.roadkill.redev.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
        @Inject(method = "render", at = @At("HEAD"))
        private void reducePotionGlint(ItemStack stack, ItemDisplayContext context, boolean inLeftHand, PoseStack ps, MultiBufferSource buffer, int light, int overlay, BakedModel model, CallbackInfo ci)
        {
            if (stack.getItem() instanceof PotionItem)
            {   RenderSystem.setShaderGlintAlpha(Minecraft.getInstance().options.glintStrength().get() / 1.5);
            }
            else
            {   RenderSystem.setShaderGlintAlpha(Minecraft.getInstance().options.glintStrength().get());
            }
        }
    }
}
