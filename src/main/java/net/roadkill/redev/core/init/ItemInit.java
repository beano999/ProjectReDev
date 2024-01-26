package net.roadkill.redev.core.init;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.common.item.CarvedPumpkinItem;
import net.roadkill.redev.common.item.ChainLadderItem;
import net.roadkill.redev.common.item.NetheriteShieldItem;
import net.roadkill.redev.util.registries.ArmorMaterials;
import net.roadkill.redev.util.registries.ModItems;

public class ItemInit
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ReDev.MOD_ID);

    public static final RegistryObject<Item> HOGLIN_HIDE = ITEMS.register("hoglin_hide", () -> new Item(new Item.Properties()));
    //public static final RegistryObject<ArmorItem> HOGLIN_HIDE_CAP = ITEMS.register("hoglin_hide_helmet", () -> new ArmorItem(ArmorMaterials.HOGLIN_HIDE, ArmorItem.Type.HELMET, new Item.Properties()));
    //public static final RegistryObject<ArmorItem> HOGLIN_HIDE_TUNIC = ITEMS.register("hoglin_hide_chestplate", () -> new ArmorItem(ArmorMaterials.HOGLIN_HIDE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    //public static final RegistryObject<ArmorItem> HOGLIN_HIDE_PANTS = ITEMS.register("hoglin_hide_leggings", () -> new ArmorItem(ArmorMaterials.HOGLIN_HIDE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    //public static final RegistryObject<ArmorItem> HOGLIN_HIDE_BOOTS = ITEMS.register("hoglin_hide_boots", () -> new ArmorItem(ArmorMaterials.HOGLIN_HIDE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> WOOD_SCRAP = ITEMS.register("wood_scrap", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NETHER_GOLD_NUGGET = ITEMS.register("nether_gold_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHER_GOLD_INGOT = ITEMS.register("nether_gold_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHER_GOLD_SWORD = ITEMS.register("nether_gold_sword", () -> new SwordItem(ModItems.Tiers.NETHER_GOLD, 4, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> NETHER_GOLD_PICKAXE = ITEMS.register("nether_gold_pickaxe", () -> new PickaxeItem(ModItems.Tiers.NETHER_GOLD, 4, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> NETHER_GOLD_AXE = ITEMS.register("nether_gold_axe", () -> new AxeItem(ModItems.Tiers.NETHER_GOLD, 8, -3f, new Item.Properties()));
    public static final RegistryObject<Item> NETHER_GOLD_SHOVEL = ITEMS.register("nether_gold_shovel", () -> new ShovelItem(ModItems.Tiers.NETHER_GOLD, 4.5f, -3f, new Item.Properties()));
    public static final RegistryObject<Item> NETHER_GOLD_HOE = ITEMS.register("nether_gold_hoe", () -> new HoeItem(ModItems.Tiers.NETHER_GOLD, 1, -1f, new Item.Properties()));
    public static final RegistryObject<Item> NETHER_GOLD_HELMET = ITEMS.register("nether_gold_helmet", () -> new ArmorItem(ArmorMaterials.NETHER_GOLD, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> NETHER_GOLD_CHESTPLATE = ITEMS.register("nether_gold_chestplate", () -> new ArmorItem(ArmorMaterials.NETHER_GOLD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> NETHER_GOLD_LEGGINGS = ITEMS.register("nether_gold_leggings", () -> new ArmorItem(ArmorMaterials.NETHER_GOLD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> NETHER_GOLD_BOOTS = ITEMS.register("nether_gold_boots", () -> new ArmorItem(ArmorMaterials.NETHER_GOLD, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> CHARRED_BONE = ITEMS.register("charred_bone", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WITHERED_INGOT = ITEMS.register("withered_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WITHERED_SWORD = ITEMS.register("withered_sword", () -> new SwordItem(ModItems.Tiers.WITHERED, 6, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> WITHERED_PICKAXE = ITEMS.register("withered_pickaxe", () -> new PickaxeItem(ModItems.Tiers.WITHERED, 5, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> WITHERED_AXE = ITEMS.register("withered_axe", () -> new AxeItem(ModItems.Tiers.WITHERED, 9, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> WITHERED_SHOVEL = ITEMS.register("withered_shovel", () -> new ShovelItem(ModItems.Tiers.WITHERED, 5.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> WITHERED_HOE = ITEMS.register("withered_hoe", () -> new HoeItem(ModItems.Tiers.WITHERED, 2, -1.0F, new Item.Properties()));
    public static final RegistryObject<Item> WITHERED_HELMET = ITEMS.register("withered_helmet", () -> new ArmorItem(ArmorMaterials.WITHERED, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> WITHERED_CHESTPLATE = ITEMS.register("withered_chestplate", () -> new ArmorItem(ArmorMaterials.WITHERED, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> WITHERED_LEGGINGS = ITEMS.register("withered_leggings", () -> new ArmorItem(ArmorMaterials.WITHERED, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> WITHERED_BOOTS = ITEMS.register("withered_boots", () -> new ArmorItem(ArmorMaterials.WITHERED, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> CARAMINE_RYE_SEEDS = ITEMS.register("caramine_rye_seeds", () -> new ItemNameBlockItem(BlockInit.CARAMINE_RYE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHURK = ITEMS.register("churk", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().fast().nutrition(4).saturationMod(0.3f).build())));
    public static final RegistryObject<Item> CARAMINE_RYE = ITEMS.register("caramine_rye", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WARPED_DRUPEL = ITEMS.register("warped_drupel", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().fast().nutrition(3).saturationMod(0.3f).build())));

    public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR = ITEMS.register("netherite_horse_armor", () -> new HorseArmorItem(16, "netherite", new Item.Properties().stacksTo(1).fireResistant()));

    public static final RegistryObject<Item> LITHICAN_SPAWN_EGG = ITEMS.register("lithican_spawn_egg", () -> new ForgeSpawnEggItem(EntityInit.LITHICAN, 5263440, 10782599, new Item.Properties()));
    public static final RegistryObject<Item> REVENANT_SPAWN_EGG = ITEMS.register("revenant_spawn_egg", () -> new ForgeSpawnEggItem(EntityInit.REVENANT, 5395605, 5874129, new Item.Properties()));

    /*
     Block Items
     */
    public static final RegistryObject<Item> ACACIA_BOOKSHELF = ITEMS.register("acacia_bookshelf", () -> new BlockItem(BlockInit.ACACIA_BOOKSHELF.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_BOOKSHELF = ITEMS.register("birch_bookshelf", () -> new BlockItem(BlockInit.BIRCH_BOOKSHELF.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_BOOKSHELF = ITEMS.register("crimson_bookshelf", () -> new BlockItem(BlockInit.CRIMSON_BOOKSHELF.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_BOOKSHELF = ITEMS.register("dark_oak_bookshelf", () -> new BlockItem(BlockInit.DARK_OAK_BOOKSHELF.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_BOOKSHELF = ITEMS.register("jungle_bookshelf", () -> new BlockItem(BlockInit.JUNGLE_BOOKSHELF.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_BOOKSHELF = ITEMS.register("mangrove_bookshelf", () -> new BlockItem(BlockInit.MANGROVE_BOOKSHELF.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_BOOKSHELF = ITEMS.register("spruce_bookshelf", () -> new BlockItem(BlockInit.SPRUCE_BOOKSHELF.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_BOOKSHELF = ITEMS.register("warped_bookshelf", () -> new BlockItem(BlockInit.WARPED_BOOKSHELF.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_BOOKSHELF = ITEMS.register("scrapwood_bookshelf", () -> new BlockItem(BlockInit.SCRAPWOOD_BOOKSHELF.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_BOOKSHELF = ITEMS.register("whispur_bookshelf", () -> new BlockItem(BlockInit.WHISPUR_BOOKSHELF.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_BOOKSHELF = ITEMS.register("petrified_bookshelf", () -> new BlockItem(BlockInit.PETRIFIED_BOOKSHELF.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_BOOKSHELF = ITEMS.register("shade_bookshelf", () -> new BlockItem(BlockInit.SHADE_BOOKSHELF.get(), new Item.Properties()));

    public static final RegistryObject<Item> ACACIA_LADDER = ITEMS.register("acacia_ladder", () -> new BlockItem(BlockInit.ACACIA_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_LADDER = ITEMS.register("birch_ladder", () -> new BlockItem(BlockInit.BIRCH_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_LADDER = ITEMS.register("crimson_ladder", () -> new BlockItem(BlockInit.CRIMSON_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_LADDER = ITEMS.register("dark_oak_ladder", () -> new BlockItem(BlockInit.DARK_OAK_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_LADDER = ITEMS.register("jungle_ladder", () -> new BlockItem(BlockInit.JUNGLE_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_LADDER = ITEMS.register("mangrove_ladder", () -> new BlockItem(BlockInit.MANGROVE_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_LADDER = ITEMS.register("spruce_ladder", () -> new BlockItem(BlockInit.SPRUCE_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_LADDER = ITEMS.register("warped_ladder", () -> new BlockItem(BlockInit.WARPED_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_LADDER = ITEMS.register("scrapwood_ladder", () -> new BlockItem(BlockInit.SCRAPWOOD_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_LADDER = ITEMS.register("whispur_ladder", () -> new BlockItem(BlockInit.WHISPUR_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_LADDER = ITEMS.register("petrified_ladder", () -> new BlockItem(BlockInit.PETRIFIED_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_LADDER = ITEMS.register("shade_ladder", () -> new BlockItem(BlockInit.SHADE_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHAIN_LADDER = ITEMS.register("chain_ladder", () -> new ChainLadderItem(BlockInit.CHAIN_LADDER.get(), new Item.Properties()));

    public static final RegistryObject<Item> ACACIA_SMITHING_TABLE = ITEMS.register("acacia_smithing_table", () -> new BlockItem(BlockInit.ACACIA_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_SMITHING_TABLE = ITEMS.register("birch_smithing_table", () -> new BlockItem(BlockInit.BIRCH_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_SMITHING_TABLE = ITEMS.register("crimson_smithing_table", () -> new BlockItem(BlockInit.CRIMSON_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_SMITHING_TABLE = ITEMS.register("dark_oak_smithing_table", () -> new BlockItem(BlockInit.DARK_OAK_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_SMITHING_TABLE = ITEMS.register("jungle_smithing_table", () -> new BlockItem(BlockInit.JUNGLE_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_SMITHING_TABLE = ITEMS.register("mangrove_smithing_table", () -> new BlockItem(BlockInit.MANGROVE_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_SMITHING_TABLE = ITEMS.register("spruce_smithing_table", () -> new BlockItem(BlockInit.SPRUCE_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_SMITHING_TABLE = ITEMS.register("warped_smithing_table", () -> new BlockItem(BlockInit.WARPED_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_SMITHING_TABLE = ITEMS.register("scrapwood_smithing_table", () -> new BlockItem(BlockInit.SCRAPWOOD_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_SMITHING_TABLE = ITEMS.register("whispur_smithing_table", () -> new BlockItem(BlockInit.WHISPUR_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_SMITHING_TABLE = ITEMS.register("petrified_smithing_table", () -> new BlockItem(BlockInit.PETRIFIED_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_SMITHING_TABLE = ITEMS.register("shade_smithing_table", () -> new BlockItem(BlockInit.SHADE_SMITHING_TABLE.get(), new Item.Properties()));

    public static final RegistryObject<Item> ACACIA_CAMPFIRE = ITEMS.register("acacia_campfire", () -> new BlockItem(BlockInit.ACACIA_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> ACACIA_SOUL_CAMPFIRE = ITEMS.register("acacia_soul_campfire", () -> new BlockItem(BlockInit.ACACIA_SOUL_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_CAMPFIRE = ITEMS.register("birch_campfire", () -> new BlockItem(BlockInit.BIRCH_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_SOUL_CAMPFIRE = ITEMS.register("birch_soul_campfire", () -> new BlockItem(BlockInit.BIRCH_SOUL_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_CAMPFIRE = ITEMS.register("crimson_campfire", () -> new BlockItem(BlockInit.CRIMSON_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_SOUL_CAMPFIRE = ITEMS.register("crimson_soul_campfire", () -> new BlockItem(BlockInit.CRIMSON_SOUL_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_CAMPFIRE = ITEMS.register("dark_oak_campfire", () -> new BlockItem(BlockInit.DARK_OAK_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_SOUL_CAMPFIRE = ITEMS.register("dark_oak_soul_campfire", () -> new BlockItem(BlockInit.DARK_OAK_SOUL_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_CAMPFIRE = ITEMS.register("jungle_campfire", () -> new BlockItem(BlockInit.JUNGLE_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_SOUL_CAMPFIRE = ITEMS.register("jungle_soul_campfire", () -> new BlockItem(BlockInit.JUNGLE_SOUL_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_CAMPFIRE = ITEMS.register("mangrove_campfire", () -> new BlockItem(BlockInit.MANGROVE_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_SOUL_CAMPFIRE = ITEMS.register("mangrove_soul_campfire", () -> new BlockItem(BlockInit.MANGROVE_SOUL_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_CAMPFIRE = ITEMS.register("spruce_campfire", () -> new BlockItem(BlockInit.SPRUCE_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_SOUL_CAMPFIRE = ITEMS.register("spruce_soul_campfire", () -> new BlockItem(BlockInit.SPRUCE_SOUL_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_CAMPFIRE = ITEMS.register("warped_campfire", () -> new BlockItem(BlockInit.WARPED_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_SOUL_CAMPFIRE = ITEMS.register("warped_soul_campfire", () -> new BlockItem(BlockInit.WARPED_SOUL_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_CAMPFIRE = ITEMS.register("scrapwood_campfire", () -> new BlockItem(BlockInit.SCRAPWOOD_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_SOUL_CAMPFIRE = ITEMS.register("scrapwood_soul_campfire", () -> new BlockItem(BlockInit.SCRAPWOOD_SOUL_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BONE_CAMPFIRE = ITEMS.register("bone_campfire", () -> new BlockItem(BlockInit.BONE_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BONE_SOUL_CAMPFIRE = ITEMS.register("bone_soul_campfire", () -> new BlockItem(BlockInit.BONE_SOUL_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_CAMPFIRE = ITEMS.register("petrified_campfire", () -> new BlockItem(BlockInit.PETRIFIED_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_SOUL_CAMPFIRE = ITEMS.register("petrified_soul_campfire", () -> new BlockItem(BlockInit.PETRIFIED_SOUL_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_CAMPFIRE = ITEMS.register("shade_campfire", () -> new BlockItem(BlockInit.SHADE_CAMPFIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_SOUL_CAMPFIRE = ITEMS.register("shade_soul_campfire", () -> new BlockItem(BlockInit.SHADE_SOUL_CAMPFIRE.get(), new Item.Properties()));

    public static final RegistryObject<Item> ACACIA_RAIL = ITEMS.register("acacia_rail", () -> new BlockItem(BlockInit.ACACIA_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_RAIL = ITEMS.register("birch_rail", () -> new BlockItem(BlockInit.BIRCH_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_RAIL = ITEMS.register("crimson_rail", () -> new BlockItem(BlockInit.CRIMSON_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_RAIL = ITEMS.register("dark_oak_rail", () -> new BlockItem(BlockInit.DARK_OAK_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_RAIL = ITEMS.register("jungle_rail", () -> new BlockItem(BlockInit.JUNGLE_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_RAIL = ITEMS.register("mangrove_rail", () -> new BlockItem(BlockInit.MANGROVE_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_RAIL = ITEMS.register("spruce_rail", () -> new BlockItem(BlockInit.SPRUCE_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_RAIL = ITEMS.register("warped_rail", () -> new BlockItem(BlockInit.WARPED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_RAIL = ITEMS.register("scrapwood_rail", () -> new BlockItem(BlockInit.SCRAPWOOD_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_RAIL = ITEMS.register("whispur_rail", () -> new BlockItem(BlockInit.WHISPUR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_RAIL = ITEMS.register("petrified_rail", () -> new BlockItem(BlockInit.PETRIFIED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_RAIL = ITEMS.register("shade_rail", () -> new BlockItem(BlockInit.SHADE_RAIL.get(), new Item.Properties()));

    public static final RegistryObject<Item> ACACIA_DETECTOR_RAIL = ITEMS.register("acacia_detector_rail", () -> new BlockItem(BlockInit.ACACIA_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_DETECTOR_RAIL = ITEMS.register("birch_detector_rail", () -> new BlockItem(BlockInit.BIRCH_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_DETECTOR_RAIL = ITEMS.register("crimson_detector_rail", () -> new BlockItem(BlockInit.CRIMSON_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_DETECTOR_RAIL = ITEMS.register("dark_oak_detector_rail", () -> new BlockItem(BlockInit.DARK_OAK_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_DETECTOR_RAIL = ITEMS.register("jungle_detector_rail", () -> new BlockItem(BlockInit.JUNGLE_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_DETECTOR_RAIL = ITEMS.register("mangrove_detector_rail", () -> new BlockItem(BlockInit.MANGROVE_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_DETECTOR_RAIL = ITEMS.register("spruce_detector_rail", () -> new BlockItem(BlockInit.SPRUCE_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_DETECTOR_RAIL = ITEMS.register("warped_detector_rail", () -> new BlockItem(BlockInit.WARPED_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_DETECTOR_RAIL = ITEMS.register("scrapwood_detector_rail", () -> new BlockItem(BlockInit.SCRAPWOOD_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_DETECTOR_RAIL = ITEMS.register("whispur_detector_rail", () -> new BlockItem(BlockInit.WHISPUR_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_DETECTOR_RAIL = ITEMS.register("petrified_detector_rail", () -> new BlockItem(BlockInit.PETRIFIED_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_DETECTOR_RAIL = ITEMS.register("shade_detector_rail", () -> new BlockItem(BlockInit.SHADE_DETECTOR_RAIL.get(), new Item.Properties()));

    public static final RegistryObject<Item> ACACIA_POWERED_RAIL = ITEMS.register("acacia_powered_rail", () -> new BlockItem(BlockInit.ACACIA_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_POWERED_RAIL = ITEMS.register("birch_powered_rail", () -> new BlockItem(BlockInit.BIRCH_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_POWERED_RAIL = ITEMS.register("crimson_powered_rail", () -> new BlockItem(BlockInit.CRIMSON_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_POWERED_RAIL = ITEMS.register("dark_oak_powered_rail", () -> new BlockItem(BlockInit.DARK_OAK_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_POWERED_RAIL = ITEMS.register("jungle_powered_rail", () -> new BlockItem(BlockInit.JUNGLE_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_POWERED_RAIL = ITEMS.register("mangrove_powered_rail", () -> new BlockItem(BlockInit.MANGROVE_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_POWERED_RAIL = ITEMS.register("spruce_powered_rail", () -> new BlockItem(BlockInit.SPRUCE_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_POWERED_RAIL = ITEMS.register("warped_powered_rail", () -> new BlockItem(BlockInit.WARPED_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_POWERED_RAIL = ITEMS.register("scrapwood_powered_rail", () -> new BlockItem(BlockInit.SCRAPWOOD_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_POWERED_RAIL = ITEMS.register("whispur_powered_rail", () -> new BlockItem(BlockInit.WHISPUR_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_POWERED_RAIL = ITEMS.register("petrified_powered_rail", () -> new BlockItem(BlockInit.PETRIFIED_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_POWERED_RAIL = ITEMS.register("shade_powered_rail", () -> new BlockItem(BlockInit.SHADE_POWERED_RAIL.get(), new Item.Properties()));

    public static final RegistryObject<Item> ACACIA_ACTIVATOR_RAIL = ITEMS.register("acacia_activator_rail", () -> new BlockItem(BlockInit.ACACIA_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_ACTIVATOR_RAIL = ITEMS.register("birch_activator_rail", () -> new BlockItem(BlockInit.BIRCH_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_ACTIVATOR_RAIL = ITEMS.register("crimson_activator_rail", () -> new BlockItem(BlockInit.CRIMSON_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_ACTIVATOR_RAIL = ITEMS.register("dark_oak_activator_rail", () -> new BlockItem(BlockInit.DARK_OAK_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_ACTIVATOR_RAIL = ITEMS.register("jungle_activator_rail", () -> new BlockItem(BlockInit.JUNGLE_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_ACTIVATOR_RAIL = ITEMS.register("mangrove_activator_rail", () -> new BlockItem(BlockInit.MANGROVE_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_ACTIVATOR_RAIL = ITEMS.register("spruce_activator_rail", () -> new BlockItem(BlockInit.SPRUCE_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_ACTIVATOR_RAIL = ITEMS.register("warped_activator_rail", () -> new BlockItem(BlockInit.WARPED_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_ACTIVATOR_RAIL = ITEMS.register("scrapwood_activator_rail", () -> new BlockItem(BlockInit.SCRAPWOOD_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_ACTIVATOR_RAIL = ITEMS.register("whispur_activator_rail", () -> new BlockItem(BlockInit.WHISPUR_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_ACTIVATOR_RAIL = ITEMS.register("petrified_activator_rail", () -> new BlockItem(BlockInit.PETRIFIED_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_ACTIVATOR_RAIL = ITEMS.register("shade_activator_rail", () -> new BlockItem(BlockInit.SHADE_ACTIVATOR_RAIL.get(), new Item.Properties()));

    public static final RegistryObject<Item> ACACIA_HEDGE = ITEMS.register("acacia_hedge", () -> new BlockItem(BlockInit.ACACIA_HEDGE.get(), new Item.Properties()));
    public static final RegistryObject<Item> AZALEA_HEDGE = ITEMS.register("azalea_hedge", () -> new BlockItem(BlockInit.AZALEA_HEDGE.get(), new Item.Properties()));
    public static final RegistryObject<Item> FLOWERING_AZALEA_HEDGE = ITEMS.register("flowering_azalea_hedge", () -> new BlockItem(BlockInit.FLOWERING_AZALEA_HEDGE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_HEDGE = ITEMS.register("birch_hedge", () -> new BlockItem(BlockInit.BIRCH_HEDGE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_HEDGE = ITEMS.register("dark_oak_hedge", () -> new BlockItem(BlockInit.DARK_OAK_HEDGE.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_HEDGE = ITEMS.register("jungle_hedge", () -> new BlockItem(BlockInit.JUNGLE_HEDGE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_HEDGE = ITEMS.register("mangrove_hedge", () -> new BlockItem(BlockInit.MANGROVE_HEDGE.get(), new Item.Properties()));
    public static final RegistryObject<Item> OAK_HEDGE = ITEMS.register("oak_hedge", () -> new BlockItem(BlockInit.OAK_HEDGE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_HEDGE = ITEMS.register("spruce_hedge", () -> new BlockItem(BlockInit.SPRUCE_HEDGE.get(), new Item.Properties()));

    public static final RegistryObject<Item> ACACIA_HEDGE_WALL = ITEMS.register("acacia_hedge_wall", () -> new BlockItem(BlockInit.ACACIA_HEDGE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> AZALEA_HEDGE_WALL = ITEMS.register("azalea_hedge_wall", () -> new BlockItem(BlockInit.AZALEA_HEDGE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> FLOWERING_AZALEA_HEDGE_WALL = ITEMS.register("flowering_azalea_hedge_wall", () -> new BlockItem(BlockInit.FLOWERING_AZALEA_HEDGE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_HEDGE_WALL = ITEMS.register("birch_hedge_wall", () -> new BlockItem(BlockInit.BIRCH_HEDGE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_HEDGE_WALL = ITEMS.register("dark_oak_hedge_wall", () -> new BlockItem(BlockInit.DARK_OAK_HEDGE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_HEDGE_WALL = ITEMS.register("jungle_hedge_wall", () -> new BlockItem(BlockInit.JUNGLE_HEDGE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_HEDGE_WALL = ITEMS.register("mangrove_hedge_wall", () -> new BlockItem(BlockInit.MANGROVE_HEDGE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> OAK_HEDGE_WALL = ITEMS.register("oak_hedge_wall", () -> new BlockItem(BlockInit.OAK_HEDGE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_HEDGE_WALL = ITEMS.register("spruce_hedge_wall", () -> new BlockItem(BlockInit.SPRUCE_HEDGE_WALL.get(), new Item.Properties()));

    public static final RegistryObject<Item> BLACKSTONE_FURNACE = ITEMS.register("blackstone_furnace", () -> new BlockItem(BlockInit.BLACKSTONE_FURNACE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_FURNACE = ITEMS.register("deepslate_furnace", () -> new BlockItem(BlockInit.DEEPSLATE_FURNACE.get(), new Item.Properties()));
    public static final RegistryObject<Item> ANDESITE_FURNACE = ITEMS.register("andesite_furnace", () -> new BlockItem(BlockInit.ANDESITE_FURNACE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DIORITE_FURNACE = ITEMS.register("diorite_furnace", () -> new BlockItem(BlockInit.DIORITE_FURNACE.get(), new Item.Properties()));
    public static final RegistryObject<Item> GRANITE_FURNACE = ITEMS.register("granite_furnace", () -> new BlockItem(BlockInit.GRANITE_FURNACE.get(), new Item.Properties()));

    public static final RegistryObject<Item> BLACKSTONE_DISPENSER = ITEMS.register("blackstone_dispenser", () -> new BlockItem(BlockInit.BLACKSTONE_DISPENSER.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_DISPENSER = ITEMS.register("deepslate_dispenser", () -> new BlockItem(BlockInit.DEEPSLATE_DISPENSER.get(), new Item.Properties()));
    public static final RegistryObject<Item> ANDESITE_DISPENSER = ITEMS.register("andesite_dispenser", () -> new BlockItem(BlockInit.ANDESITE_DISPENSER.get(), new Item.Properties()));
    public static final RegistryObject<Item> DIORITE_DISPENSER = ITEMS.register("diorite_dispenser", () -> new BlockItem(BlockInit.DIORITE_DISPENSER.get(), new Item.Properties()));
    public static final RegistryObject<Item> GRANITE_DISPENSER = ITEMS.register("granite_dispenser", () -> new BlockItem(BlockInit.GRANITE_DISPENSER.get(), new Item.Properties()));

    public static final RegistryObject<Item> BLACKSTONE_DROPPER = ITEMS.register("blackstone_dropper", () -> new BlockItem(BlockInit.BLACKSTONE_DROPPER.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_DROPPER = ITEMS.register("deepslate_dropper", () -> new BlockItem(BlockInit.DEEPSLATE_DROPPER.get(), new Item.Properties()));
    public static final RegistryObject<Item> ANDESITE_DROPPER = ITEMS.register("andesite_dropper", () -> new BlockItem(BlockInit.ANDESITE_DROPPER.get(), new Item.Properties()));
    public static final RegistryObject<Item> DIORITE_DROPPER = ITEMS.register("diorite_dropper", () -> new BlockItem(BlockInit.DIORITE_DROPPER.get(), new Item.Properties()));
    public static final RegistryObject<Item> GRANITE_DROPPER = ITEMS.register("granite_dropper", () -> new BlockItem(BlockInit.GRANITE_DROPPER.get(), new Item.Properties()));

    public static final RegistryObject<Item> NETHER_GOLD_BLOCK = ITEMS.register("nether_gold_block", () -> new BlockItem(BlockInit.NETHER_GOLD_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> NETHER_GOLD_ORE = ITEMS.register("nether_gold_ore", () -> new BlockItem(BlockInit.NETHER_GOLD_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> NETHER_DIAMOND_ORE = ITEMS.register("nether_diamond_ore", () -> new BlockItem(BlockInit.NETHER_DIAMOND_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BASALT_DIAMOND_ORE = ITEMS.register("basalt_diamond_ore", () -> new BlockItem(BlockInit.BASALT_DIAMOND_ORE.get(), new Item.Properties()));

    public static final RegistryObject<Item> WITHERED_BLOCK = ITEMS.register("withered_block", () -> new BlockItem(BlockInit.WITHERED_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> NETHER_BRISTLE = ITEMS.register("nether_bristle", () -> new BlockItem(BlockInit.NETHER_BRISTLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_ROOT = ITEMS.register("whispur_root", () -> new BlockItem(BlockInit.WHISPUR_ROOT.get(), new Item.Properties()));

    public static final RegistryObject<Item> SCRAPWOOD_PLANKS = ITEMS.register("scrapwood_planks", () -> new BlockItem(BlockInit.SCRAPWOOD_PLANKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_HEAP = ITEMS.register("scrapwood_heap", () -> new BlockItem(BlockInit.SCRAPWOOD_HEAP.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_SLAB = ITEMS.register("scrapwood_slab", () -> new BlockItem(BlockInit.SCRAPWOOD_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_STAIRS = ITEMS.register("scrapwood_stairs", () -> new BlockItem(BlockInit.SCRAPWOOD_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_FENCE = ITEMS.register("scrapwood_fence", () -> new BlockItem(BlockInit.SCRAPWOOD_FENCE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_FENCE_GATE = ITEMS.register("scrapwood_fence_gate", () -> new BlockItem(BlockInit.SCRAPWOOD_FENCE_GATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_BUTTON = ITEMS.register("scrapwood_button", () -> new BlockItem(BlockInit.SCRAPWOOD_BUTTON.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_PRESSURE_PLATE = ITEMS.register("scrapwood_pressure_plate", () -> new BlockItem(BlockInit.SCRAPWOOD_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_DOOR = ITEMS.register("scrapwood_door", () -> new BlockItem(BlockInit.SCRAPWOOD_DOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_TRAPDOOR = ITEMS.register("scrapwood_trapdoor", () -> new BlockItem(BlockInit.SCRAPWOOD_TRAPDOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> SCRAPWOOD_SIGN = ITEMS.register("scrapwood_sign", () -> new SignItem(new Item.Properties().stacksTo(16), BlockInit.SCRAPWOOD_SIGN.get(), BlockInit.SCRAPWOOD_WALL_SIGN.get()));

    public static final RegistryObject<Item> WHISPUR_PLANKS = ITEMS.register("whispur_planks", () -> new BlockItem(BlockInit.WHISPUR_PLANKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_SLAB = ITEMS.register("whispur_slab", () -> new BlockItem(BlockInit.WHISPUR_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_STAIRS = ITEMS.register("whispur_stairs", () -> new BlockItem(BlockInit.WHISPUR_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_FENCE = ITEMS.register("whispur_fence", () -> new BlockItem(BlockInit.WHISPUR_FENCE.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_FENCE_GATE = ITEMS.register("whispur_fence_gate", () -> new BlockItem(BlockInit.WHISPUR_FENCE_GATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_BUTTON = ITEMS.register("whispur_button", () -> new BlockItem(BlockInit.WHISPUR_BUTTON.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_PRESSURE_PLATE = ITEMS.register("whispur_pressure_plate", () -> new BlockItem(BlockInit.WHISPUR_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_DOOR = ITEMS.register("whispur_door", () -> new BlockItem(BlockInit.WHISPUR_DOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_TRAPDOOR = ITEMS.register("whispur_trapdoor", () -> new BlockItem(BlockInit.WHISPUR_TRAPDOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHISPUR_SIGN = ITEMS.register("whispur_sign", () -> new SignItem(new Item.Properties().stacksTo(16), BlockInit.WHISPUR_SIGN.get(), BlockInit.WHISPUR_WALL_SIGN.get()));

    public static final RegistryObject<Item> PETRIFIED_LOG = ITEMS.register("petrified_log", () -> new BlockItem(BlockInit.PETRIFIED_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRIPPED_PETRIFIED_LOG = ITEMS.register("stripped_petrified_log", () -> new BlockItem(BlockInit.STRIPPED_PETRIFIED_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_WOOD = ITEMS.register("petrified_wood", () -> new BlockItem(BlockInit.PETRIFIED_WOOD.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_PLANKS = ITEMS.register("petrified_planks", () -> new BlockItem(BlockInit.PETRIFIED_PLANKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_SLAB = ITEMS.register("petrified_slab", () -> new BlockItem(BlockInit.PETRIFIED_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_STAIRS = ITEMS.register("petrified_stairs", () -> new BlockItem(BlockInit.PETRIFIED_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_FENCE = ITEMS.register("petrified_fence", () -> new BlockItem(BlockInit.PETRIFIED_FENCE.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_FENCE_GATE = ITEMS.register("petrified_fence_gate", () -> new BlockItem(BlockInit.PETRIFIED_FENCE_GATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_BUTTON = ITEMS.register("petrified_button", () -> new BlockItem(BlockInit.PETRIFIED_BUTTON.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_PRESSURE_PLATE = ITEMS.register("petrified_pressure_plate", () -> new BlockItem(BlockInit.PETRIFIED_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_DOOR = ITEMS.register("petrified_door", () -> new BlockItem(BlockInit.PETRIFIED_DOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_TRAPDOOR = ITEMS.register("petrified_trapdoor", () -> new BlockItem(BlockInit.PETRIFIED_TRAPDOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_SIGN = ITEMS.register("petrified_sign", () -> new SignItem(new Item.Properties().stacksTo(16), BlockInit.PETRIFIED_SIGN.get(), BlockInit.PETRIFIED_WALL_SIGN.get()));

    public static final RegistryObject<Item> CARVED_PUMPKIN = ITEMS.register("carved_pumpkin", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 0, false));
    public static final RegistryObject<Item> CARVED_PUMPKIN_CREEPY = ITEMS.register("carved_pumpkin_creepy", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 1, false));
    public static final RegistryObject<Item> CARVED_PUMPKIN_HORRIFIED = ITEMS.register("carved_pumpkin_horrified", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 2, false));
    public static final RegistryObject<Item> CARVED_PUMPKIN_CREEPER = ITEMS.register("carved_pumpkin_creeper", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 3, false));
    public static final RegistryObject<Item> CARVED_PUMPKIN_SCOWLING = ITEMS.register("carved_pumpkin_scowling", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 4, false));
    public static final RegistryObject<Item> CARVED_PUMPKIN_TWISTED = ITEMS.register("carved_pumpkin_twisted", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 5, false));
    public static final RegistryObject<Item> CARVED_PUMPKIN_FURIOUS = ITEMS.register("carved_pumpkin_furious", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 6, false));
    public static final RegistryObject<Item> CARVED_PUMPKIN_ZOMBIE = ITEMS.register("carved_pumpkin_zombie", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 7, false));
    public static final RegistryObject<Item> CARVED_PUMPKIN_CREEPY_LIT = ITEMS.register("carved_pumpkin_creepy_lit", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 1, true));
    public static final RegistryObject<Item> CARVED_PUMPKIN_HORRIFIED_LIT = ITEMS.register("carved_pumpkin_horrified_lit", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 2, true));
    public static final RegistryObject<Item> CARVED_PUMPKIN_CREEPER_LIT = ITEMS.register("carved_pumpkin_creeper_lit", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 3, true));
    public static final RegistryObject<Item> CARVED_PUMPKIN_SCOWLING_LIT = ITEMS.register("carved_pumpkin_scowling_lit", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 4, true));
    public static final RegistryObject<Item> CARVED_PUMPKIN_TWISTED_LIT = ITEMS.register("carved_pumpkin_twisted_lit", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 5, true));
    public static final RegistryObject<Item> CARVED_PUMPKIN_FURIOUS_LIT = ITEMS.register("carved_pumpkin_furious_lit", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 6, true));
    public static final RegistryObject<Item> CARVED_PUMPKIN_ZOMBIE_LIT = ITEMS.register("carved_pumpkin_zombie_lit", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 7, true));
    public static final RegistryObject<Item> CARVED_PUMPKIN_LIT = ITEMS.register("carved_pumpkin_lit", () -> new CarvedPumpkinItem(BlockInit.CARVED_PUMPKIN.get(), new Item.Properties(), 0, true));

    public static final RegistryObject<Item> SHADE_LEAVES = ITEMS.register("shade_leaves", () -> new BlockItem(BlockInit.SHADE_LEAVES.get(), new Item.Properties()));
    public static final RegistryObject<Item> TEAL_SHADE_LEAVES = ITEMS.register("teal_shade_leaves", () -> new BlockItem(BlockInit.TEAL_SHADE_LEAVES.get(), new Item.Properties()));
    public static final RegistryObject<Item> RED_SHADE_LEAVES = ITEMS.register("red_shade_leaves", () -> new BlockItem(BlockInit.RED_SHADE_LEAVES.get(), new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_SHADE_LEAVES = ITEMS.register("purple_shade_leaves", () -> new BlockItem(BlockInit.PURPLE_SHADE_LEAVES.get(), new Item.Properties()));

    public static final RegistryObject<Item> SHADE_SAPLING = ITEMS.register("shade_sapling", () -> new BlockItem(BlockInit.SHADE_SAPLING.get(), new Item.Properties()));
    public static final RegistryObject<Item> TEAL_SHADE_SAPLING = ITEMS.register("teal_shade_sapling", () -> new BlockItem(BlockInit.TEAL_SHADE_SAPLING.get(), new Item.Properties()));
    public static final RegistryObject<Item> RED_SHADE_SAPLING = ITEMS.register("red_shade_sapling", () -> new BlockItem(BlockInit.RED_SHADE_SAPLING.get(), new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_SHADE_SAPLING = ITEMS.register("purple_shade_sapling", () -> new BlockItem(BlockInit.PURPLE_SHADE_SAPLING.get(), new Item.Properties()));

    public static final RegistryObject<Item> SHADE_LOG = ITEMS.register("shade_log", () -> new BlockItem(BlockInit.SHADE_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRIPPED_SHADE_LOG = ITEMS.register("stripped_shade_log", () -> new BlockItem(BlockInit.STRIPPED_SHADE_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_WOOD = ITEMS.register("shade_wood", () -> new BlockItem(BlockInit.SHADE_WOOD.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_PLANKS = ITEMS.register("shade_planks", () -> new BlockItem(BlockInit.SHADE_PLANKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_SLAB = ITEMS.register("shade_slab", () -> new BlockItem(BlockInit.SHADE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_STAIRS = ITEMS.register("shade_stairs", () -> new BlockItem(BlockInit.SHADE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_FENCE = ITEMS.register("shade_fence", () -> new BlockItem(BlockInit.SHADE_FENCE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_FENCE_GATE = ITEMS.register("shade_fence_gate", () -> new BlockItem(BlockInit.SHADE_FENCE_GATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_BUTTON = ITEMS.register("shade_button", () -> new BlockItem(BlockInit.SHADE_BUTTON.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_PRESSURE_PLATE = ITEMS.register("shade_pressure_plate", () -> new BlockItem(BlockInit.SHADE_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_DOOR = ITEMS.register("shade_door", () -> new BlockItem(BlockInit.SHADE_DOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_TRAPDOOR = ITEMS.register("shade_trapdoor", () -> new BlockItem(BlockInit.SHADE_TRAPDOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> SHADE_SIGN = ITEMS.register("shade_sign", () -> new SignItem(new Item.Properties().stacksTo(16), BlockInit.SHADE_SIGN.get(), BlockInit.SHADE_WALL_SIGN.get()));

    public static final RegistryObject<Item> DURIAN = ITEMS.register("durian", () -> new BlockItem(BlockInit.DURIAN.get(), new Item.Properties()));
    public static final RegistryObject<Item> DURIAN_SLICE = ITEMS.register("durian_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));

    public static final RegistryObject<Item> HADALITE = ITEMS.register("hadalite", () -> new BlockItem(BlockInit.HADALITE.get(), new Item.Properties()));

    public static final RegistryObject<Item> OLD_GOLD = ITEMS.register("old_gold", () -> new BlockItem(BlockInit.OLD_GOLD.get(), new Item.Properties()));
    public static final RegistryObject<Item> OLD_NETHER_GOLD = ITEMS.register("old_nether_gold", () -> new BlockItem(BlockInit.OLD_NETHER_GOLD.get(), new Item.Properties()));

    public static final RegistryObject<Item> NETHERITE_SHIELD = ITEMS.register("shield_netherite", () -> new NetheriteShieldItem(new Item.Properties()));
}
