package net.roadkill.redev.common.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class DurianThornEntity extends AbstractArrow
{
    public DurianThornEntity(EntityType<? extends AbstractArrow> entityType, Level level)
    {   super(entityType, level);
    }

    @Override
    public ItemStack getPickupItem()
    {   return ItemStack.EMPTY;
    }

    @Override
    protected ItemStack getDefaultPickupItem()
    {   return ItemStack.EMPTY;
    }

    @Override
    protected boolean tryPickup(Player player)
    {   return false;
    }

    @Override
    public void onHitBlock(BlockHitResult pResult)
    {   super.onHitBlock(pResult);
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent()
    {   return SoundEvent.createFixedRangeEvent(ResourceLocation.parse(""), 1);
    }
}
