package net.roadkill.redev.util.registries;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.roadkill.redev.core.init.ItemInit;

public class ModItems
{
    public static final Item HOGLIN_HIDE = ItemInit.HOGLIN_HIDE.get();
    //public static final Item HOGLIN_HIDE_CAP = ItemInit.HOGLIN_HIDE_CAP.get();
    //public static final Item HOGLIN_HIDE_TUNIC = ItemInit.HOGLIN_HIDE_TUNIC.get();
    //public static final Item HOGLIN_HIDE_PANTS = ItemInit.HOGLIN_HIDE_PANTS.get();
    //public static final Item HOGLIN_HIDE_BOOTS = ItemInit.HOGLIN_HIDE_BOOTS.get();

    public static final Item NETHER_GOLD_NUGGET = ItemInit.NETHER_GOLD_NUGGET.get();
    public static final Item NETHER_GOLD_INGOT = ItemInit.NETHER_GOLD_INGOT.get();
    public static final Item NETHER_GOLD_SWORD = ItemInit.NETHER_GOLD_SWORD.get();
    public static final Item NETHER_GOLD_SHOVEL = ItemInit.NETHER_GOLD_SHOVEL.get();
    public static final Item NETHER_GOLD_PICKAXE = ItemInit.NETHER_GOLD_PICKAXE.get();
    public static final Item NETHER_GOLD_AXE = ItemInit.NETHER_GOLD_AXE.get();
    public static final Item NETHER_GOLD_HOE = ItemInit.NETHER_GOLD_HOE.get();
    public static final Item NETHER_GOLD_HELMET = ItemInit.NETHER_GOLD_HELMET.get();
    public static final Item NETHER_GOLD_CHESTPLATE = ItemInit.NETHER_GOLD_CHESTPLATE.get();
    public static final Item NETHER_GOLD_LEGGINGS = ItemInit.NETHER_GOLD_LEGGINGS.get();
    public static final Item NETHER_GOLD_BOOTS = ItemInit.NETHER_GOLD_BOOTS.get();

    public static final Item CHARRED_BONE = ItemInit.CHARRED_BONE.get();

    public static final Item WITHERED_INGOT = ItemInit.WITHERED_INGOT.get();
    public static final Item WITHERED_SWORD = ItemInit.WITHERED_SWORD.get();
    public static final Item WITHERED_SHOVEL = ItemInit.WITHERED_SHOVEL.get();
    public static final Item WITHERED_PICKAXE = ItemInit.WITHERED_PICKAXE.get();
    public static final Item WITHERED_AXE = ItemInit.WITHERED_AXE.get();
    public static final Item WITHERED_HOE = ItemInit.WITHERED_HOE.get();
    public static final Item WITHERED_HELMET = ItemInit.WITHERED_HELMET.get();
    public static final Item WITHERED_CHESTPLATE = ItemInit.WITHERED_CHESTPLATE.get();
    public static final Item WITHERED_LEGGINGS = ItemInit.WITHERED_LEGGINGS.get();
    public static final Item WITHERED_BOOTS = ItemInit.WITHERED_BOOTS.get();

    public static final Item WARPED_DRUPEL = ItemInit.WARPED_DRUPEL.get();

    public static final Item LITHICAN_SPAWN_EGG = ItemInit.LITHICAN_SPAWN_EGG.get();
    public static final Item REVENANT_SPAWN_EGG = ItemInit.REVENANT_SPAWN_EGG.get();

    /*
     Block Items
     */
    public static final Item ACACIA_BOOKSHELF = ItemInit.ACACIA_BOOKSHELF.get();
    public static final Item BIRCH_BOOKSHELF = ItemInit.BIRCH_BOOKSHELF.get();
    public static final Item CRIMSON_BOOKSHELF = ItemInit.CRIMSON_BOOKSHELF.get();
    public static final Item DARK_OAK_BOOKSHELF = ItemInit.DARK_OAK_BOOKSHELF.get();
    public static final Item JUNGLE_BOOKSHELF = ItemInit.JUNGLE_BOOKSHELF.get();
    public static final Item MANGROVE_BOOKSHELF = ItemInit.MANGROVE_BOOKSHELF.get();
    public static final Item SPRUCE_BOOKSHELF = ItemInit.SPRUCE_BOOKSHELF.get();
    public static final Item WARPED_BOOKSHELF = ItemInit.WARPED_BOOKSHELF.get();
    public static final Item SCRAPWOOD_BOOKSHELF = ItemInit.SCRAPWOOD_BOOKSHELF.get();
    public static final Item WHISPUR_BOOKSHELF = ItemInit.WHISPUR_BOOKSHELF.get();
    public static final Item PETRIFIED_BOOKSHELF = ItemInit.PETRIFIED_BOOKSHELF.get();

    public static final Item ACACIA_LADDER = ItemInit.ACACIA_LADDER.get();
    public static final Item BIRCH_LADDER = ItemInit.BIRCH_LADDER.get();
    public static final Item CRIMSON_LADDER = ItemInit.CRIMSON_LADDER.get();
    public static final Item DARK_OAK_LADDER = ItemInit.DARK_OAK_LADDER.get();
    public static final Item JUNGLE_LADDER = ItemInit.JUNGLE_LADDER.get();
    public static final Item MANGROVE_LADDER = ItemInit.MANGROVE_LADDER.get();
    public static final Item SPRUCE_LADDER = ItemInit.SPRUCE_LADDER.get();
    public static final Item WARPED_LADDER = ItemInit.WARPED_LADDER.get();
    public static final Item SCRAPWOOD_LADDER = ItemInit.SCRAPWOOD_LADDER.get();
    public static final Item WHISPUR_LADDER = ItemInit.WHISPUR_LADDER.get();
    public static final Item PETRIFIED_LADDER = ItemInit.PETRIFIED_LADDER.get();
    public static final Item SHADE_LADDER = ItemInit.SHADE_LADDER.get();

    public static final Item CHAIN_LADDER = ItemInit.CHAIN_LADDER.get();

    public static final Item ACACIA_SMITHING_TABLE = ItemInit.ACACIA_SMITHING_TABLE.get();
    public static final Item BIRCH_SMITHING_TABLE = ItemInit.BIRCH_SMITHING_TABLE.get();
    public static final Item CRIMSON_SMITHING_TABLE = ItemInit.CRIMSON_SMITHING_TABLE.get();
    public static final Item DARK_OAK_SMITHING_TABLE = ItemInit.DARK_OAK_SMITHING_TABLE.get();
    public static final Item JUNGLE_SMITHING_TABLE = ItemInit.JUNGLE_SMITHING_TABLE.get();
    public static final Item MANGROVE_SMITHING_TABLE = ItemInit.MANGROVE_SMITHING_TABLE.get();
    public static final Item SPRUCE_SMITHING_TABLE = ItemInit.SPRUCE_SMITHING_TABLE.get();
    public static final Item WARPED_SMITHING_TABLE = ItemInit.WARPED_SMITHING_TABLE.get();
    public static final Item SCRAPWOOD_SMITHING_TABLE = ItemInit.SCRAPWOOD_SMITHING_TABLE.get();
    public static final Item WHISPUR_SMITHING_TABLE = ItemInit.WHISPUR_SMITHING_TABLE.get();
    public static final Item PETRIFIED_SMITHING_TABLE = ItemInit.PETRIFIED_SMITHING_TABLE.get();

    public static final Item ACACIA_CAMPFIRE = ItemInit.ACACIA_CAMPFIRE.get();
    public static final Item BIRCH_CAMPFIRE = ItemInit.BIRCH_CAMPFIRE.get();
    public static final Item CRIMSON_CAMPFIRE = ItemInit.CRIMSON_CAMPFIRE.get();
    public static final Item DARK_OAK_CAMPFIRE = ItemInit.DARK_OAK_CAMPFIRE.get();
    public static final Item JUNGLE_CAMPFIRE = ItemInit.JUNGLE_CAMPFIRE.get();
    public static final Item MANGROVE_CAMPFIRE = ItemInit.MANGROVE_CAMPFIRE.get();
    public static final Item SPRUCE_CAMPFIRE = ItemInit.SPRUCE_CAMPFIRE.get();
    public static final Item WARPED_CAMPFIRE = ItemInit.WARPED_CAMPFIRE.get();
    public static final Item SCRAPWOOD_CAMPFIRE = ItemInit.SCRAPWOOD_CAMPFIRE.get();
    public static final Item BONE_CAMPFIRE = ItemInit.BONE_CAMPFIRE.get();
    public static final Item PETRIFIED_CAMPFIRE = ItemInit.PETRIFIED_CAMPFIRE.get();

    public static final Item ACACIA_SOUL_CAMPFIRE = ItemInit.ACACIA_SOUL_CAMPFIRE.get();
    public static final Item BIRCH_SOUL_CAMPFIRE = ItemInit.BIRCH_SOUL_CAMPFIRE.get();
    public static final Item CRIMSON_SOUL_CAMPFIRE = ItemInit.CRIMSON_SOUL_CAMPFIRE.get();
    public static final Item DARK_OAK_SOUL_CAMPFIRE = ItemInit.DARK_OAK_SOUL_CAMPFIRE.get();
    public static final Item JUNGLE_SOUL_CAMPFIRE = ItemInit.JUNGLE_SOUL_CAMPFIRE.get();
    public static final Item MANGROVE_SOUL_CAMPFIRE = ItemInit.MANGROVE_SOUL_CAMPFIRE.get();
    public static final Item SPRUCE_SOUL_CAMPFIRE = ItemInit.SPRUCE_SOUL_CAMPFIRE.get();
    public static final Item WARPED_SOUL_CAMPFIRE = ItemInit.WARPED_SOUL_CAMPFIRE.get();
    public static final Item SCRAPWOOD_SOUL_CAMPFIRE = ItemInit.SCRAPWOOD_SOUL_CAMPFIRE.get();
    public static final Item BONE_SOUL_CAMPFIRE = ItemInit.BONE_SOUL_CAMPFIRE.get();
    public static final Item PETRIFIED_SOUL_CAMPFIRE = ItemInit.PETRIFIED_SOUL_CAMPFIRE.get();

    public static final Item ACACIA_RAIL = ItemInit.ACACIA_RAIL.get();
    public static final Item BIRCH_RAIL = ItemInit.BIRCH_RAIL.get();
    public static final Item CRIMSON_RAIL = ItemInit.CRIMSON_RAIL.get();
    public static final Item DARK_OAK_RAIL = ItemInit.DARK_OAK_RAIL.get();
    public static final Item JUNGLE_RAIL = ItemInit.JUNGLE_RAIL.get();
    public static final Item MANGROVE_RAIL = ItemInit.MANGROVE_RAIL.get();
    public static final Item SPRUCE_RAIL = ItemInit.SPRUCE_RAIL.get();
    public static final Item WARPED_RAIL = ItemInit.WARPED_RAIL.get();
    public static final Item SCRAPWOOD_RAIL = ItemInit.SCRAPWOOD_RAIL.get();
    public static final Item WHISPUR_RAIL = ItemInit.WHISPUR_RAIL.get();
    public static final Item PETRIFIED_RAIL = ItemInit.PETRIFIED_RAIL.get();

    public static final Item ACACIA_DETECTOR_RAIL = ItemInit.ACACIA_DETECTOR_RAIL.get();
    public static final Item BIRCH_DETECTOR_RAIL = ItemInit.BIRCH_DETECTOR_RAIL.get();
    public static final Item CRIMSON_DETECTOR_RAIL = ItemInit.CRIMSON_DETECTOR_RAIL.get();
    public static final Item DARK_OAK_DETECTOR_RAIL = ItemInit.DARK_OAK_DETECTOR_RAIL.get();
    public static final Item JUNGLE_DETECTOR_RAIL = ItemInit.JUNGLE_DETECTOR_RAIL.get();
    public static final Item MANGROVE_DETECTOR_RAIL = ItemInit.MANGROVE_DETECTOR_RAIL.get();
    public static final Item SPRUCE_DETECTOR_RAIL = ItemInit.SPRUCE_DETECTOR_RAIL.get();
    public static final Item WARPED_DETECTOR_RAIL = ItemInit.WARPED_DETECTOR_RAIL.get();
    public static final Item SCRAPWOOD_DETECTOR_RAIL = ItemInit.SCRAPWOOD_DETECTOR_RAIL.get();
    public static final Item WHISPUR_DETECTOR_RAIL = ItemInit.WHISPUR_DETECTOR_RAIL.get();
    public static final Item PETRIFIED_DETECTOR_RAIL = ItemInit.PETRIFIED_DETECTOR_RAIL.get();

    public static final Item ACACIA_POWERED_RAIL = ItemInit.ACACIA_POWERED_RAIL.get();
    public static final Item BIRCH_POWERED_RAIL = ItemInit.BIRCH_POWERED_RAIL.get();
    public static final Item CRIMSON_POWERED_RAIL = ItemInit.CRIMSON_POWERED_RAIL.get();
    public static final Item DARK_OAK_POWERED_RAIL = ItemInit.DARK_OAK_POWERED_RAIL.get();
    public static final Item JUNGLE_POWERED_RAIL = ItemInit.JUNGLE_POWERED_RAIL.get();
    public static final Item MANGROVE_POWERED_RAIL = ItemInit.MANGROVE_POWERED_RAIL.get();
    public static final Item SPRUCE_POWERED_RAIL = ItemInit.SPRUCE_POWERED_RAIL.get();
    public static final Item WARPED_POWERED_RAIL = ItemInit.WARPED_POWERED_RAIL.get();
    public static final Item SCRAPWOOD_POWERED_RAIL = ItemInit.SCRAPWOOD_POWERED_RAIL.get();
    public static final Item WHISPUR_POWERED_RAIL = ItemInit.WHISPUR_POWERED_RAIL.get();
    public static final Item PETRIFIED_POWERED_RAIL = ItemInit.PETRIFIED_POWERED_RAIL.get();

    public static final Item ACACIA_ACTIVATOR_RAIL = ItemInit.ACACIA_ACTIVATOR_RAIL.get();
    public static final Item BIRCH_ACTIVATOR_RAIL = ItemInit.BIRCH_ACTIVATOR_RAIL.get();
    public static final Item CRIMSON_ACTIVATOR_RAIL = ItemInit.CRIMSON_ACTIVATOR_RAIL.get();
    public static final Item DARK_OAK_ACTIVATOR_RAIL = ItemInit.DARK_OAK_ACTIVATOR_RAIL.get();
    public static final Item JUNGLE_ACTIVATOR_RAIL = ItemInit.JUNGLE_ACTIVATOR_RAIL.get();
    public static final Item MANGROVE_ACTIVATOR_RAIL = ItemInit.MANGROVE_ACTIVATOR_RAIL.get();
    public static final Item SPRUCE_ACTIVATOR_RAIL = ItemInit.SPRUCE_ACTIVATOR_RAIL.get();
    public static final Item WARPED_ACTIVATOR_RAIL = ItemInit.WARPED_ACTIVATOR_RAIL.get();
    public static final Item SCRAPWOOD_ACTIVATOR_RAIL = ItemInit.SCRAPWOOD_ACTIVATOR_RAIL.get();
    public static final Item WHISPUR_ACTIVATOR_RAIL = ItemInit.WHISPUR_ACTIVATOR_RAIL.get();
    public static final Item PETRIFIED_ACTIVATOR_RAIL = ItemInit.PETRIFIED_ACTIVATOR_RAIL.get();

    public static final Item ACACIA_HEDGE = ItemInit.ACACIA_HEDGE.get();
    public static final Item AZALEA_HEDGE = ItemInit.AZALEA_HEDGE.get();
    public static final Item FLOWERING_AZALEA_HEDGE = ItemInit.FLOWERING_AZALEA_HEDGE.get();
    public static final Item BIRCH_HEDGE = ItemInit.BIRCH_HEDGE.get();
    public static final Item DARK_OAK_HEDGE = ItemInit.DARK_OAK_HEDGE.get();
    public static final Item JUNGLE_HEDGE = ItemInit.JUNGLE_HEDGE.get();
    public static final Item MANGROVE_HEDGE = ItemInit.MANGROVE_HEDGE.get();
    public static final Item OAK_HEDGE = ItemInit.OAK_HEDGE.get();
    public static final Item SPRUCE_HEDGE = ItemInit.SPRUCE_HEDGE.get();

    public static final Item ACACIA_HEDGE_WALL = ItemInit.ACACIA_HEDGE_WALL.get();
    public static final Item AZALEA_HEDGE_WALL = ItemInit.AZALEA_HEDGE_WALL.get();
    public static final Item FLOWERING_AZALEA_HEDGE_WALL = ItemInit.FLOWERING_AZALEA_HEDGE_WALL.get();
    public static final Item BIRCH_HEDGE_WALL = ItemInit.BIRCH_HEDGE_WALL.get();
    public static final Item DARK_OAK_HEDGE_WALL = ItemInit.DARK_OAK_HEDGE_WALL.get();
    public static final Item JUNGLE_HEDGE_WALL = ItemInit.JUNGLE_HEDGE_WALL.get();
    public static final Item MANGROVE_HEDGE_WALL = ItemInit.MANGROVE_HEDGE_WALL.get();
    public static final Item OAK_HEDGE_WALL = ItemInit.OAK_HEDGE_WALL.get();
    public static final Item SPRUCE_HEDGE_WALL = ItemInit.SPRUCE_HEDGE_WALL.get();

    public static final Item BLACKSTONE_FURNACE = ItemInit.BLACKSTONE_FURNACE.get();
    public static final Item DEEPSLATE_FURNACE = ItemInit.DEEPSLATE_FURNACE.get();
    public static final Item ANDESITE_FURNACE = ItemInit.ANDESITE_FURNACE.get();
    public static final Item DIORITE_FURNACE = ItemInit.DIORITE_FURNACE.get();
    public static final Item GRANITE_FURNACE = ItemInit.GRANITE_FURNACE.get();

    public static final Item BLACKSTONE_DISPENSER = ItemInit.BLACKSTONE_DISPENSER.get();
    public static final Item DEEPSLATE_DISPENSER = ItemInit.DEEPSLATE_DISPENSER.get();
    public static final Item ANDESITE_DISPENSER = ItemInit.ANDESITE_DISPENSER.get();
    public static final Item DIORITE_DISPENSER = ItemInit.DIORITE_DISPENSER.get();
    public static final Item GRANITE_DISPENSER = ItemInit.GRANITE_DISPENSER.get();

    public static final Item BLACKSTONE_DROPPER = ItemInit.BLACKSTONE_DROPPER.get();
    public static final Item DEEPSLATE_DROPPER = ItemInit.DEEPSLATE_DROPPER.get();
    public static final Item ANDESITE_DROPPER = ItemInit.ANDESITE_DROPPER.get();
    public static final Item DIORITE_DROPPER = ItemInit.DIORITE_DROPPER.get();
    public static final Item GRANITE_DROPPER = ItemInit.GRANITE_DROPPER.get();

    public static final Item NETHER_GOLD_BLOCK = ItemInit.NETHER_GOLD_BLOCK.get();
    public static final Item NETHER_GOLD_ORE = ItemInit.NETHER_GOLD_ORE.get();
    public static final Item NETHER_DIAMOND_ORE = ItemInit.NETHER_DIAMOND_ORE.get();
    public static final Item BASALT_DIAMOND_ORE = ItemInit.BASALT_DIAMOND_ORE.get();

    public static final Item WITHERED_BLOCK = ItemInit.WITHERED_BLOCK.get();

    public static final Item NETHER_BRISTLE = ItemInit.NETHER_BRISTLE.get();
    public static final Item WHISPUR_ROOT = ItemInit.WHISPUR_ROOT.get();

    public static final Item WOOD_SCRAP = ItemInit.WOOD_SCRAP.get();
    public static final Item SCRAPWOOD_HEAP = ItemInit.SCRAPWOOD_HEAP.get();
    public static final Item SCRAPWOOD_PLANKS = ItemInit.SCRAPWOOD_PLANKS.get();
    public static final Item SCRAPWOOD_SLAB = ItemInit.SCRAPWOOD_SLAB.get();
    public static final Item SCRAPWOOD_STAIRS = ItemInit.SCRAPWOOD_STAIRS.get();
    public static final Item SCRAPWOOD_FENCE = ItemInit.SCRAPWOOD_FENCE.get();
    public static final Item SCRAPWOOD_FENCE_GATE = ItemInit.SCRAPWOOD_FENCE_GATE.get();
    public static final Item SCRAPWOOD_BUTTON = ItemInit.SCRAPWOOD_BUTTON.get();
    public static final Item SCRAPWOOD_PRESSURE_PLATE = ItemInit.SCRAPWOOD_PRESSURE_PLATE.get();
    public static final Item SCRAPWOOD_DOOR = ItemInit.SCRAPWOOD_DOOR.get();
    public static final Item SCRAPWOOD_TRAPDOOR = ItemInit.SCRAPWOOD_TRAPDOOR.get();
    public static final Item SCRAPWOOD_SIGN = ItemInit.SCRAPWOOD_SIGN.get();

    public static final Item WHISPUR_PLANKS = ItemInit.WHISPUR_PLANKS.get();
    public static final Item WHISPUR_SLAB = ItemInit.WHISPUR_SLAB.get();
    public static final Item WHISPUR_STAIRS = ItemInit.WHISPUR_STAIRS.get();
    public static final Item WHISPUR_FENCE = ItemInit.WHISPUR_FENCE.get();
    public static final Item WHISPUR_FENCE_GATE = ItemInit.WHISPUR_FENCE_GATE.get();
    public static final Item WHISPUR_BUTTON = ItemInit.WHISPUR_BUTTON.get();
    public static final Item WHISPUR_PRESSURE_PLATE = ItemInit.WHISPUR_PRESSURE_PLATE.get();
    public static final Item WHISPUR_DOOR = ItemInit.WHISPUR_DOOR.get();
    public static final Item WHISPUR_TRAPDOOR = ItemInit.WHISPUR_TRAPDOOR.get();
    public static final Item WHISPUR_SIGN = ItemInit.WHISPUR_SIGN.get();

    public static final Item PETRIFIED_LOG = ItemInit.PETRIFIED_LOG.get();
    public static final Item PETRIFIED_WOOD = ItemInit.PETRIFIED_WOOD.get();
    public static final Item STRIPPED_PETRIFIED_LOG = ItemInit.STRIPPED_PETRIFIED_LOG.get();
    public static final Item PETRIFIED_PLANKS = ItemInit.PETRIFIED_PLANKS.get();
    public static final Item PETRIFIED_SLAB = ItemInit.PETRIFIED_SLAB.get();
    public static final Item PETRIFIED_STAIRS = ItemInit.PETRIFIED_STAIRS.get();
    public static final Item PETRIFIED_FENCE = ItemInit.PETRIFIED_FENCE.get();
    public static final Item PETRIFIED_FENCE_GATE = ItemInit.PETRIFIED_FENCE_GATE.get();
    public static final Item PETRIFIED_BUTTON = ItemInit.PETRIFIED_BUTTON.get();
    public static final Item PETRIFIED_PRESSURE_PLATE = ItemInit.PETRIFIED_PRESSURE_PLATE.get();
    public static final Item PETRIFIED_DOOR = ItemInit.PETRIFIED_DOOR.get();
    public static final Item PETRIFIED_TRAPDOOR = ItemInit.PETRIFIED_TRAPDOOR.get();
    public static final Item PETRIFIED_SIGN = ItemInit.PETRIFIED_SIGN.get();

    public static final Item SHADE_LEAVES = ItemInit.SHADE_LEAVES.get();
    public static final Item TEAL_SHADE_LEAVES = ItemInit.TEAL_SHADE_LEAVES.get();
    public static final Item RED_SHADE_LEAVES = ItemInit.RED_SHADE_LEAVES.get();
    public static final Item PURPLE_SHADE_LEAVES = ItemInit.PURPLE_SHADE_LEAVES.get();

    public static final Item SHADE_LOG = ItemInit.SHADE_LOG.get();
    public static final Item SHADE_WOOD = ItemInit.SHADE_WOOD.get();
    public static final Item STRIPPED_SHADE_LOG = ItemInit.STRIPPED_SHADE_LOG.get();
    public static final Item SHADE_PLANKS = ItemInit.SHADE_PLANKS.get();
    public static final Item SHADE_SLAB = ItemInit.SHADE_SLAB.get();
    public static final Item SHADE_STAIRS = ItemInit.SHADE_STAIRS.get();
    public static final Item SHADE_FENCE = ItemInit.SHADE_FENCE.get();
    public static final Item SHADE_FENCE_GATE = ItemInit.SHADE_FENCE_GATE.get();
    public static final Item SHADE_BUTTON = ItemInit.SHADE_BUTTON.get();
    public static final Item SHADE_PRESSURE_PLATE = ItemInit.SHADE_PRESSURE_PLATE.get();
    public static final Item SHADE_DOOR = ItemInit.SHADE_DOOR.get();
    public static final Item SHADE_TRAPDOOR = ItemInit.SHADE_TRAPDOOR.get();
    public static final Item SHADE_SIGN = ItemInit.SHADE_SIGN.get();

    public static final Item SHADE_SAPLING = ItemInit.SHADE_SAPLING.get();
    public static final Item TEAL_SHADE_SAPLING = ItemInit.TEAL_SHADE_SAPLING.get();
    public static final Item RED_SHADE_SAPLING = ItemInit.RED_SHADE_SAPLING.get();
    public static final Item PURPLE_SHADE_SAPLING = ItemInit.PURPLE_SHADE_SAPLING.get();

    public static final Item DURIAN = ItemInit.DURIAN.get();
    public static final Item DURIAN_SLICE = ItemInit.DURIAN_SLICE.get();

    public static final Item CARAMINE_RYE_SEEDS = ItemInit.CARAMINE_RYE_SEEDS.get();

    public static final class Tiers
    {   public static final Tier NETHER_GOLD = new ForgeTier(0, 300, 3.0F, -1.0F, 50, BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ModItems.NETHER_GOLD_INGOT));
        public static final Tier WITHERED = new ForgeTier(3, 800, 9.0F, -1.0F, 18, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ModItems.WITHERED_INGOT));
    }
}
