package net.roadkill.redev.common.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class RevenantEntity extends AbstractSkeleton
{
    public RevenantEntity(EntityType<? extends AbstractSkeleton> entityType, Level level)
    {   super(entityType, level);
    }

    @Override
    public SoundEvent getStepSound()
    {   return SoundEvents.STRAY_STEP;
    }

    @Override
    protected boolean isSunBurnTick()
    {   return false;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource rand, DifficultyInstance pDifficulty)
    {
        if (rand.nextFloat() < 0.2F * (pDifficulty.getSpecialMultiplier() + 1))
        {
            for (EquipmentSlot equipmentslot : EquipmentSlot.values())
            {
                if (equipmentslot.getIndex() < 2) continue;
                if (rand.nextFloat() < 0.6F * (pDifficulty.getSpecialMultiplier() + 1))
                {
                    int armorValue = 3;
                    if (rand.nextFloat() < 0.3F)
                    {   --armorValue;
                    }
                    if (rand.nextFloat() < 0.3F)
                    {   ++armorValue;
                    }

                    if (equipmentslot.getType() == EquipmentSlot.Type.ARMOR)
                    {
                        if (this.getItemBySlot(equipmentslot).isEmpty())
                        {
                            Item item = getEquipmentForSlot(equipmentslot, armorValue);
                            if (item != null)
                            {   this.setItemSlot(equipmentslot, new ItemStack(item));
                            }
                        }
                    }
                }
            }
        }
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(this.random.nextInt(10) == 0 ? Items.DIAMOND_SWORD : Items.IRON_SWORD));
    }
}
