package net.roadkill.redev.mixin;

import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.roadkill.redev.mixin_interfaces.IWitherSkeletonArsenal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(WitherSkeleton.class)
public class WitherSkeletonArsenal implements IWitherSkeletonArsenal
{
    @Unique
    private ItemStack redev$rangedItem = ItemStack.EMPTY;

    @Unique
    private ItemStack redev$meleeItem = ItemStack.EMPTY;

    @Override
    public ItemStack getRangedItem()
    {   return redev$rangedItem;
    }

    @Override
    public ItemStack getMeleeItem()
    {   return redev$meleeItem;
    }

    @Override
    public ItemStack setRangedItem(ItemStack itemStack)
    {   return redev$rangedItem = itemStack;
    }

    @Override
    public ItemStack setMeleeItem(ItemStack itemStack)
    {   return redev$meleeItem = itemStack;
    }
}
