package net.roadkill.redev.mixin_interfaces;

import net.minecraft.world.item.ItemStack;

public interface IPig
{
    boolean getHasTNT();
    void setHasTNT(boolean bool);

    int getFuse();

    void setFuse(int fuse);

    ItemStack getHelmet();

    void setHelmet(ItemStack armorItem);
}
