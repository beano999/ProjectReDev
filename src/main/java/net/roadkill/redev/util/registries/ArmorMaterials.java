package net.roadkill.redev.util.registries;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.crafting.Ingredient;
import net.roadkill.redev.common.item.ModArmorMaterial;

public class ArmorMaterials
{
    public static final ModArmorMaterial HOGLIN_HIDE = new ModArmorMaterial("hoglin_hide", 15, new int[]{1, 2, 3, 1}, 20,
                                                                            SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(ModItems.HOGLIN_HIDE));
    public static final ModArmorMaterial NETHER_GOLD = new ModArmorMaterial("nether_gold", 25, new int[]{1, 1, 2, 1}, 40,
                                                                            SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> Ingredient.of(ModItems.NETHER_GOLD_INGOT));
    public static final ModArmorMaterial WITHERED = new ModArmorMaterial("withered", 35, new int[]{2, 5, 7, 2}, 20,
                                                                            SoundEvents.ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> Ingredient.of(ModItems.WITHERED_INGOT));
}
