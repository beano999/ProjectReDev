package net.momostudios.redev.core.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.momostudios.redev.ReDev;

public class ItemInit
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ReDev.MOD_ID);

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
}
