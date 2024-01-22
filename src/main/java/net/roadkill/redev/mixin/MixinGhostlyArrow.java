package net.roadkill.redev.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.EntityHitResult;
import net.roadkill.redev.data.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public class MixinGhostlyArrow
{
    AbstractArrow self = (AbstractArrow) (Object) this;

    @Inject(method = "onHitEntity", at = @At("HEAD"), cancellable = true)
    protected void cancelGhostlyArrow(EntityHitResult hitResult, CallbackInfo ci)
    {
        if (hitResult.getEntity().getType().is(ModTags.EntityTypes.GHOSTLY)
        && self.getType() == EntityType.ARROW)
        {   ci.cancel();
        }
    }
}
