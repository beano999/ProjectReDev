package net.roadkill.redev.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorItem.class)
public abstract class MixinArmorItem
{
    @Shadow public abstract EquipmentSlot getEquipmentSlot();

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void use(Level level, Player player, InteractionHand pHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir)
    {
        if (this.getEquipmentSlot() == EquipmentSlot.HEAD)
        {
            Vec3 vec3 = player.getEyePosition();
            Vec3 vec31 = player.getViewVector(1.0F);
            Vec3 vec32 = vec3.add(vec31.x * 100.0D, vec31.y * 100.0D, vec31.z * 100.0D);
            EntityHitResult entityhitresult = ProjectileUtil.getEntityHitResult(level, player, vec3, vec32, (new AABB(vec3, vec32)).inflate(1.0D), (entity) -> entity instanceof Pig);
            if (entityhitresult != null)
            {   cir.setReturnValue(InteractionResultHolder.pass(player.getItemInHand(pHand)));
            }
        }
    }
}
