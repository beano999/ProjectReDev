package net.roadkill.redev.common.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.roadkill.redev.ReDev;

import java.util.function.Supplier;

public record ModArmorMaterial(String name, int durability, int[] protection, int enchantability, SoundEvent equipSound,
                               float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) implements net.minecraft.world.item.ArmorMaterial
{
    private static final int[] DURABILITY = new int[]{13, 15, 16, 11};

    @Override
    public int getDurabilityForType(ArmorItem.Type type)
    {   return DURABILITY[type.getSlot().getIndex()] * this.durability;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type)
    {   return this.protection[type.getSlot().getIndex()];
    }

    @Override
    public int getEnchantmentValue()
    {   return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound()
    {   return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient()
    {   return this.repairIngredient.get();
    }

    @Override
    public String getName()
    {   return ReDev.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness()
    {   return this.toughness;
    }

    @Override
    public float getKnockbackResistance()
    {   return this.knockbackResistance;
    }
}
