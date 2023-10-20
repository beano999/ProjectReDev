package net.roadkill.redev.mixin;

import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractSkeleton.class)
public class MixinSkeletonBase
{
    @Redirect(method = "reassessWeaponGoal", at = @At(target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", value = "INVOKE"))
    private boolean isBow(ItemStack itemStack, Item item)
    {   return itemStack.getItem() instanceof BowItem;
    }
}
