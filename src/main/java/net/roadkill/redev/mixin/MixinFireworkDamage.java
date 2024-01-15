package net.roadkill.redev.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

@Mixin(FireworkRocketEntity.class)
public abstract class MixinFireworkDamage
{
    @Shadow public abstract boolean isShotAtAngle();

    FireworkRocketEntity self = (FireworkRocketEntity) (Object) this;

    @Inject(method = "dealExplosionDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 1),
            cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void cancelDamage(CallbackInfo ci, float f, ItemStack itemstack, CompoundTag compoundtag, ListTag listtag, double d0, Vec3 vec3, Iterator var8, LivingEntity entity, boolean flag, float damage)
    {   ci.cancel();
        entity.hurt(entity.damageSources().fireworks(self, self.getOwner()), isShotAtAngle() ? damage / 2 : damage);
    }
}