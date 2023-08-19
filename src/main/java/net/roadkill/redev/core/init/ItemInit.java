package net.roadkill.redev.core.init;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.util.registries.ArmorMaterials;

public class ItemInit
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ReDev.MOD_ID);

    public static final RegistryObject<Item> HOGLIN_HIDE = ITEMS.register("hoglin_hide", () -> new Item(new Item.Properties()));
    public static final RegistryObject<ArmorItem> HOGLIN_HIDE_CAP = ITEMS.register("hoglin_hide_helmet", () -> new ArmorItem(ArmorMaterials.HOGLIN_HIDE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<ArmorItem> HOGLIN_HIDE_TUNIC = ITEMS.register("hoglin_hide_chestplate", () -> new ArmorItem(ArmorMaterials.HOGLIN_HIDE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<ArmorItem> HOGLIN_HIDE_PANTS = ITEMS.register("hoglin_hide_leggings", () -> new ArmorItem(ArmorMaterials.HOGLIN_HIDE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<ArmorItem> HOGLIN_HIDE_BOOTS = ITEMS.register("hoglin_hide_boots", () -> new ArmorItem(ArmorMaterials.HOGLIN_HIDE, ArmorItem.Type.BOOTS, new Item.Properties()));

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

    public static final RegistryObject<Item> ACACIA_LADDER = ITEMS.register("acacia_ladder", () -> new BlockItem(BlockInit.ACACIA_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_LADDER = ITEMS.register("birch_ladder", () -> new BlockItem(BlockInit.BIRCH_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_LADDER = ITEMS.register("crimson_ladder", () -> new BlockItem(BlockInit.CRIMSON_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_LADDER = ITEMS.register("dark_oak_ladder", () -> new BlockItem(BlockInit.DARK_OAK_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_LADDER = ITEMS.register("jungle_ladder", () -> new BlockItem(BlockInit.JUNGLE_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_LADDER = ITEMS.register("mangrove_ladder", () -> new BlockItem(BlockInit.MANGROVE_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_LADDER = ITEMS.register("spruce_ladder", () -> new BlockItem(BlockInit.SPRUCE_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_LADDER = ITEMS.register("warped_ladder", () -> new BlockItem(BlockInit.WARPED_LADDER.get(), new Item.Properties()));

    public static final RegistryObject<Item> ACACIA_SMITHING_TABLE = ITEMS.register("acacia_smithing_table", () -> new BlockItem(BlockInit.ACACIA_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_SMITHING_TABLE = ITEMS.register("birch_smithing_table", () -> new BlockItem(BlockInit.BIRCH_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_SMITHING_TABLE = ITEMS.register("crimson_smithing_table", () -> new BlockItem(BlockInit.CRIMSON_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_SMITHING_TABLE = ITEMS.register("dark_oak_smithing_table", () -> new BlockItem(BlockInit.DARK_OAK_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_SMITHING_TABLE = ITEMS.register("jungle_smithing_table", () -> new BlockItem(BlockInit.JUNGLE_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_SMITHING_TABLE = ITEMS.register("mangrove_smithing_table", () -> new BlockItem(BlockInit.MANGROVE_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_SMITHING_TABLE = ITEMS.register("spruce_smithing_table", () -> new BlockItem(BlockInit.SPRUCE_SMITHING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_SMITHING_TABLE = ITEMS.register("warped_smithing_table", () -> new BlockItem(BlockInit.WARPED_SMITHING_TABLE.get(), new Item.Properties()));

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

    public static final RegistryObject<Item> ACACIA_RAIL = ITEMS.register("acacia_rail", () -> new BlockItem(BlockInit.ACACIA_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_RAIL = ITEMS.register("birch_rail", () -> new BlockItem(BlockInit.BIRCH_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_RAIL = ITEMS.register("crimson_rail", () -> new BlockItem(BlockInit.CRIMSON_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_RAIL = ITEMS.register("dark_oak_rail", () -> new BlockItem(BlockInit.DARK_OAK_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_RAIL = ITEMS.register("jungle_rail", () -> new BlockItem(BlockInit.JUNGLE_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_RAIL = ITEMS.register("mangrove_rail", () -> new BlockItem(BlockInit.MANGROVE_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_RAIL = ITEMS.register("spruce_rail", () -> new BlockItem(BlockInit.SPRUCE_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_RAIL = ITEMS.register("warped_rail", () -> new BlockItem(BlockInit.WARPED_RAIL.get(), new Item.Properties()));

    public static final RegistryObject<Item> ACACIA_DETECTOR_RAIL = ITEMS.register("acacia_detector_rail", () -> new BlockItem(BlockInit.ACACIA_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_DETECTOR_RAIL = ITEMS.register("birch_detector_rail", () -> new BlockItem(BlockInit.BIRCH_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_DETECTOR_RAIL = ITEMS.register("crimson_detector_rail", () -> new BlockItem(BlockInit.CRIMSON_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_DETECTOR_RAIL = ITEMS.register("dark_oak_detector_rail", () -> new BlockItem(BlockInit.DARK_OAK_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_DETECTOR_RAIL = ITEMS.register("jungle_detector_rail", () -> new BlockItem(BlockInit.JUNGLE_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_DETECTOR_RAIL = ITEMS.register("mangrove_detector_rail", () -> new BlockItem(BlockInit.MANGROVE_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_DETECTOR_RAIL = ITEMS.register("spruce_detector_rail", () -> new BlockItem(BlockInit.SPRUCE_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_DETECTOR_RAIL = ITEMS.register("warped_detector_rail", () -> new BlockItem(BlockInit.WARPED_DETECTOR_RAIL.get(), new Item.Properties()));

    public static final RegistryObject<Item> ACACIA_POWERED_RAIL = ITEMS.register("acacia_powered_rail", () -> new BlockItem(BlockInit.ACACIA_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_POWERED_RAIL = ITEMS.register("birch_powered_rail", () -> new BlockItem(BlockInit.BIRCH_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_POWERED_RAIL = ITEMS.register("crimson_powered_rail", () -> new BlockItem(BlockInit.CRIMSON_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_POWERED_RAIL = ITEMS.register("dark_oak_powered_rail", () -> new BlockItem(BlockInit.DARK_OAK_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_POWERED_RAIL = ITEMS.register("jungle_powered_rail", () -> new BlockItem(BlockInit.JUNGLE_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_POWERED_RAIL = ITEMS.register("mangrove_powered_rail", () -> new BlockItem(BlockInit.MANGROVE_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_POWERED_RAIL = ITEMS.register("spruce_powered_rail", () -> new BlockItem(BlockInit.SPRUCE_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_POWERED_RAIL = ITEMS.register("warped_powered_rail", () -> new BlockItem(BlockInit.WARPED_POWERED_RAIL.get(), new Item.Properties()));

    public static final RegistryObject<Item> ACACIA_ACTIVATOR_RAIL = ITEMS.register("acacia_activator_rail", () -> new BlockItem(BlockInit.ACACIA_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_ACTIVATOR_RAIL = ITEMS.register("birch_activator_rail", () -> new BlockItem(BlockInit.BIRCH_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_ACTIVATOR_RAIL = ITEMS.register("crimson_activator_rail", () -> new BlockItem(BlockInit.CRIMSON_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_ACTIVATOR_RAIL = ITEMS.register("dark_oak_activator_rail", () -> new BlockItem(BlockInit.DARK_OAK_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_ACTIVATOR_RAIL = ITEMS.register("jungle_activator_rail", () -> new BlockItem(BlockInit.JUNGLE_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_ACTIVATOR_RAIL = ITEMS.register("mangrove_activator_rail", () -> new BlockItem(BlockInit.MANGROVE_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_ACTIVATOR_RAIL = ITEMS.register("spruce_activator_rail", () -> new BlockItem(BlockInit.SPRUCE_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_ACTIVATOR_RAIL = ITEMS.register("warped_activator_rail", () -> new BlockItem(BlockInit.WARPED_ACTIVATOR_RAIL.get(), new Item.Properties()));

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

    public static final RegistryObject<Item> FOOLS_GOLD_NUGGET = ITEMS.register("fools_gold_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FOOLS_GOLD_INGOT = ITEMS.register("fools_gold_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FOOLS_GOLD_BLOCK = ITEMS.register("fools_gold_block", () -> new BlockItem(BlockInit.FOOLS_GOLD_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> FOOLS_GOLD_ORE = ITEMS.register("fools_gold_ore", () -> new BlockItem(BlockInit.FOOLS_GOLD_ORE.get(), new Item.Properties()));
}
