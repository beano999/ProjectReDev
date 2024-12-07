package net.roadkill.redev.util.registries;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.data.ModTags;

import java.util.EnumMap;
import java.util.List;

public class ModArmorMaterials
{
    public static final ArmorMaterial HOGLIN_HIDE = new ArmorMaterial(
            100,
            Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.HELMET, 1);
                map.put(ArmorType.CHESTPLATE, 3);
                map.put(ArmorType.LEGGINGS, 2);
                map.put(ArmorType.BOOTS, 1);
            }), 15, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F,
            ModTags.Items.HOGLIN_HIDE_REPAIR_MATERIALS,
            ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "hoglin")));

    public static final ArmorMaterial NETHER_GOLD = new ArmorMaterial(
            300,
            Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.HELMET, 1);
                map.put(ArmorType.CHESTPLATE, 2);
                map.put(ArmorType.LEGGINGS, 1);
                map.put(ArmorType.BOOTS, 1);
            }), 25, SoundEvents.ARMOR_EQUIP_GOLD,
            0.0F, 0.0F,
            ModTags.Items.NETHER_GOLD_REPAIR_MATERIALS,
            ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "nether_gold")));

    public static final ArmorMaterial WITHERED = new ArmorMaterial(
            800,
            Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.HELMET, 2);
                map.put(ArmorType.CHESTPLATE, 7);
                map.put(ArmorType.LEGGINGS, 5);
                map.put(ArmorType.BOOTS, 2);
            }), 30, SoundEvents.ARMOR_EQUIP_NETHERITE,
            2.0F, 0.1F,
            ModTags.Items.WITHERED_REPAIR_MATERIALS,
            ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "withered")));
}
