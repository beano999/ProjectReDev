package net.momostudios.redev.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.momostudios.redev.ReDev;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ThrownPotion.class)
public class MixinSplashPotion
{
    @Inject(method = "applySplash(Ljava/util/List;Lnet/minecraft/world/entity/Entity;)V",
            at = @At(value = "HEAD"), cancellable = true, remap = ReDev.REMAP_MIXINS)
    private void applySplash(List<MobEffectInstance> effects, Entity entity, CallbackInfo info)
    {
        if (entity instanceof ItemFrame itemFrame && effects.stream().anyMatch(effect -> effect.getEffect().equals(MobEffects.INVISIBILITY)))
        {   itemFrame.setInvisible(true);
            info.cancel();
        }
    }
}
