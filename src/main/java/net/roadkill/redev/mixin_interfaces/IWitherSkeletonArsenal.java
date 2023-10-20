package net.roadkill.redev.mixin_interfaces;

import net.minecraft.world.item.ItemStack;

public interface IWitherSkeletonArsenal
{
    ItemStack getRangedItem();

    ItemStack getMeleeItem();

    ItemStack setRangedItem(ItemStack itemStack);

    ItemStack setMeleeItem(ItemStack itemStack);
}
