package net.momostudios.redev.util.registries;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.crafting.Ingredient;
import net.momostudios.redev.common.item.ModArmorMaterial;

public class ArmorMaterials
{
    public static final ModArmorMaterial HOGLIN_HIDE = new ModArmorMaterial("hoglin_hide", 15, new int[]{1, 1, 2, 1}, 20,
                                                                            SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(ModItems.HOGLIN_HIDE));
}
