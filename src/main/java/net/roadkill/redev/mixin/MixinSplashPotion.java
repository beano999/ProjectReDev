package net.roadkill.redev.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.roadkill.redev.ReDev;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.stream.StreamSupport;

@Mixin(ThrownPotion.class)
public class MixinSplashPotion
{
    @Inject(method = "applySplash",
            at = @At(value = "HEAD"), cancellable = true)
    private void applySplash(ServerLevel level, Iterable<MobEffectInstance> effects, Entity entity, CallbackInfo ci)
    {
        if (entity instanceof ItemFrame itemFrame && StreamSupport.stream(effects.spliterator(), true).anyMatch(effect -> effect.getEffect().equals(MobEffects.INVISIBILITY)))
        {   itemFrame.setInvisible(true);
            ci.cancel();
        }
    }
}
