package net.roadkill.redev.core.init;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.common.block.*;
import net.roadkill.redev.common.world.tree.ShadeTree;
import net.roadkill.redev.util.registries.ModBlocks;
import net.roadkill.redev.util.registries.ModSoundType;

public class BlockInit
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ReDev.MOD_ID);

    public static final RegistryObject<Block> ACACIA_BOOKSHELF = BLOCKS.register("acacia_bookshelf", () -> new Block(Block.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> BIRCH_BOOKSHELF = BLOCKS.register("birch_bookshelf", () -> new Block(Block.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> CRIMSON_BOOKSHELF = BLOCKS.register("crimson_bookshelf", () -> new Block(Block.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> DARK_OAK_BOOKSHELF = BLOCKS.register("dark_oak_bookshelf", () -> new Block(Block.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> JUNGLE_BOOKSHELF = BLOCKS.register("jungle_bookshelf", () -> new Block(Block.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> MANGROVE_BOOKSHELF = BLOCKS.register("mangrove_bookshelf", () -> new Block(Block.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> SPRUCE_BOOKSHELF = BLOCKS.register("spruce_bookshelf", () -> new Block(Block.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> WARPED_BOOKSHELF = BLOCKS.register("warped_bookshelf", () -> new Block(Block.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> SCRAPWOOD_BOOKSHELF = BLOCKS.register("scrapwood_bookshelf", () -> new Block(Block.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> WHISPUR_BOOKSHELF = BLOCKS.register("whispur_bookshelf", () -> new Block(Block.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistryObject<Block> PETRIFIED_BOOKSHELF = BLOCKS.register("petrified_bookshelf", () -> new Block(Block.Properties.copy(Blocks.BOOKSHELF).strength(3.0F, 8.0F).sound(ModSoundType.PETRIFIED_PLANKS)));
    public static final RegistryObject<Block> SHADE_BOOKSHELF = BLOCKS.register("shade_bookshelf", () -> new Block(Block.Properties.copy(Blocks.BOOKSHELF)));

    public static final RegistryObject<Block> ACACIA_LADDER = BLOCKS.register("acacia_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> BIRCH_LADDER  = BLOCKS.register("birch_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> CRIMSON_LADDER = BLOCKS.register("crimson_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> DARK_OAK_LADDER = BLOCKS.register("dark_oak_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> JUNGLE_LADDER = BLOCKS.register("jungle_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> MANGROVE_LADDER = BLOCKS.register("mangrove_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> SPRUCE_LADDER = BLOCKS.register("spruce_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> WARPED_LADDER = BLOCKS.register("warped_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> SCRAPWOOD_LADDER = BLOCKS.register("scrapwood_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> WHISPUR_LADDER = BLOCKS.register("whispur_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> PETRIFIED_LADDER = BLOCKS.register("petrified_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER).strength(3.0F, 8.0F).sound(ModSoundType.PETRIFIED_PLANKS)));
    public static final RegistryObject<Block> SHADE_LADDER = BLOCKS.register("shade_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> CHAIN_LADDER = BLOCKS.register("chain_ladder", () -> new ChainLadderBlock(Block.Properties.copy(Blocks.LADDER).strength(3F, 8F).sound(SoundType.CHAIN)));

    public static final RegistryObject<Block> ACACIA_SMITHING_TABLE = BLOCKS.register("acacia_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> BIRCH_SMITHING_TABLE = BLOCKS.register("birch_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> CRIMSON_SMITHING_TABLE = BLOCKS.register("crimson_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> DARK_OAK_SMITHING_TABLE = BLOCKS.register("dark_oak_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> JUNGLE_SMITHING_TABLE = BLOCKS.register("jungle_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> MANGROVE_SMITHING_TABLE = BLOCKS.register("mangrove_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> SPRUCE_SMITHING_TABLE = BLOCKS.register("spruce_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> WARPED_SMITHING_TABLE = BLOCKS.register("warped_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> SCRAPWOOD_SMITHING_TABLE = BLOCKS.register("scrapwood_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> WHISPUR_SMITHING_TABLE = BLOCKS.register("whispur_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> PETRIFIED_SMITHING_TABLE = BLOCKS.register("petrified_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> SHADE_SMITHING_TABLE = BLOCKS.register("shade_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));

    public static final RegistryObject<Block> ACACIA_CAMPFIRE = BLOCKS.register("acacia_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> ACACIA_SOUL_CAMPFIRE = BLOCKS.register("acacia_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));
    public static final RegistryObject<Block> BIRCH_CAMPFIRE = BLOCKS.register("birch_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> BIRCH_SOUL_CAMPFIRE = BLOCKS.register("birch_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));
    public static final RegistryObject<Block> CRIMSON_CAMPFIRE = BLOCKS.register("crimson_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> CRIMSON_SOUL_CAMPFIRE = BLOCKS.register("crimson_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));
    public static final RegistryObject<Block> DARK_OAK_CAMPFIRE = BLOCKS.register("dark_oak_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> DARK_OAK_SOUL_CAMPFIRE = BLOCKS.register("dark_oak_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));
    public static final RegistryObject<Block> JUNGLE_CAMPFIRE = BLOCKS.register("jungle_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> JUNGLE_SOUL_CAMPFIRE = BLOCKS.register("jungle_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));
    public static final RegistryObject<Block> MANGROVE_CAMPFIRE = BLOCKS.register("mangrove_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> OAK_CAMPFIRE = BLOCKS.register("oak_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> OAK_SOUL_CAMPFIRE = BLOCKS.register("oak_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));
    public static final RegistryObject<Block> MANGROVE_SOUL_CAMPFIRE = BLOCKS.register("mangrove_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));
    public static final RegistryObject<Block> SPRUCE_CAMPFIRE = BLOCKS.register("spruce_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> SPRUCE_SOUL_CAMPFIRE = BLOCKS.register("spruce_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));
    public static final RegistryObject<Block> WARPED_CAMPFIRE = BLOCKS.register("warped_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> WARPED_SOUL_CAMPFIRE = BLOCKS.register("warped_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));
    public static final RegistryObject<Block> PETRIFIED_CAMPFIRE = BLOCKS.register("petrified_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> PETRIFIED_SOUL_CAMPFIRE = BLOCKS.register("petrified_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));
    public static final RegistryObject<Block> SCRAPWOOD_CAMPFIRE = BLOCKS.register("scrapwood_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> SCRAPWOOD_SOUL_CAMPFIRE = BLOCKS.register("scrapwood_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));
    public static final RegistryObject<Block> BONE_CAMPFIRE = BLOCKS.register("bone_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> BONE_SOUL_CAMPFIRE = BLOCKS.register("bone_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));
    public static final RegistryObject<Block> SHADE_CAMPFIRE = BLOCKS.register("shade_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> SHADE_SOUL_CAMPFIRE = BLOCKS.register("shade_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));

    public static final RegistryObject<Block> ACACIA_RAIL = BLOCKS.register("acacia_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> BIRCH_RAIL = BLOCKS.register("birch_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> CRIMSON_RAIL = BLOCKS.register("crimson_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> DARK_OAK_RAIL = BLOCKS.register("dark_oak_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> JUNGLE_RAIL = BLOCKS.register("jungle_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> MANGROVE_RAIL = BLOCKS.register("mangrove_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> SPRUCE_RAIL = BLOCKS.register("spruce_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> WARPED_RAIL = BLOCKS.register("warped_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> SCRAPWOOD_RAIL = BLOCKS.register("scrapwood_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> WHISPUR_RAIL = BLOCKS.register("whispur_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> PETRIFIED_RAIL = BLOCKS.register("petrified_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> SHADE_RAIL = BLOCKS.register("shade_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));

    public static final RegistryObject<Block> ACACIA_DETECTOR_RAIL = BLOCKS.register("acacia_detector_rail", () -> new DetectorRailBlock(Block.Properties.copy(Blocks.DETECTOR_RAIL)));
    public static final RegistryObject<Block> BIRCH_DETECTOR_RAIL = BLOCKS.register("birch_detector_rail", () -> new DetectorRailBlock(Block.Properties.copy(Blocks.DETECTOR_RAIL)));
    public static final RegistryObject<Block> CRIMSON_DETECTOR_RAIL = BLOCKS.register("crimson_detector_rail", () -> new DetectorRailBlock(Block.Properties.copy(Blocks.DETECTOR_RAIL)));
    public static final RegistryObject<Block> DARK_OAK_DETECTOR_RAIL = BLOCKS.register("dark_oak_detector_rail", () -> new DetectorRailBlock(Block.Properties.copy(Blocks.DETECTOR_RAIL)));
    public static final RegistryObject<Block> JUNGLE_DETECTOR_RAIL = BLOCKS.register("jungle_detector_rail", () -> new DetectorRailBlock(Block.Properties.copy(Blocks.DETECTOR_RAIL)));
    public static final RegistryObject<Block> MANGROVE_DETECTOR_RAIL = BLOCKS.register("mangrove_detector_rail", () -> new DetectorRailBlock(Block.Properties.copy(Blocks.DETECTOR_RAIL)));
    public static final RegistryObject<Block> SPRUCE_DETECTOR_RAIL = BLOCKS.register("spruce_detector_rail", () -> new DetectorRailBlock(Block.Properties.copy(Blocks.DETECTOR_RAIL)));
    public static final RegistryObject<Block> WARPED_DETECTOR_RAIL = BLOCKS.register("warped_detector_rail", () -> new DetectorRailBlock(Block.Properties.copy(Blocks.DETECTOR_RAIL)));
    public static final RegistryObject<Block> SCRAPWOOD_DETECTOR_RAIL = BLOCKS.register("scrapwood_detector_rail", () -> new DetectorRailBlock(Block.Properties.copy(Blocks.DETECTOR_RAIL)));
    public static final RegistryObject<Block> WHISPUR_DETECTOR_RAIL = BLOCKS.register("whispur_detector_rail", () -> new DetectorRailBlock(Block.Properties.copy(Blocks.DETECTOR_RAIL)));
    public static final RegistryObject<Block> PETRIFIED_DETECTOR_RAIL = BLOCKS.register("petrified_detector_rail", () -> new DetectorRailBlock(Block.Properties.copy(Blocks.DETECTOR_RAIL)));
    public static final RegistryObject<Block> SHADE_DETECTOR_RAIL = BLOCKS.register("shade_detector_rail", () -> new DetectorRailBlock(Block.Properties.copy(Blocks.DETECTOR_RAIL)));

    public static final RegistryObject<Block> ACACIA_POWERED_RAIL = BLOCKS.register("acacia_powered_rail", () -> new ModPoweredRailBlock(Block.Properties.copy(Blocks.POWERED_RAIL)));
    public static final RegistryObject<Block> BIRCH_POWERED_RAIL = BLOCKS.register("birch_powered_rail", () -> new ModPoweredRailBlock(Block.Properties.copy(Blocks.POWERED_RAIL)));
    public static final RegistryObject<Block> CRIMSON_POWERED_RAIL = BLOCKS.register("crimson_powered_rail", () -> new ModPoweredRailBlock(Block.Properties.copy(Blocks.POWERED_RAIL)));
    public static final RegistryObject<Block> DARK_OAK_POWERED_RAIL = BLOCKS.register("dark_oak_powered_rail", () -> new ModPoweredRailBlock(Block.Properties.copy(Blocks.POWERED_RAIL)));
    public static final RegistryObject<Block> JUNGLE_POWERED_RAIL = BLOCKS.register("jungle_powered_rail", () -> new ModPoweredRailBlock(Block.Properties.copy(Blocks.POWERED_RAIL)));
    public static final RegistryObject<Block> MANGROVE_POWERED_RAIL = BLOCKS.register("mangrove_powered_rail", () -> new ModPoweredRailBlock(Block.Properties.copy(Blocks.POWERED_RAIL)));
    public static final RegistryObject<Block> SPRUCE_POWERED_RAIL = BLOCKS.register("spruce_powered_rail", () -> new ModPoweredRailBlock(Block.Properties.copy(Blocks.POWERED_RAIL)));
    public static final RegistryObject<Block> WARPED_POWERED_RAIL = BLOCKS.register("warped_powered_rail", () -> new ModPoweredRailBlock(Block.Properties.copy(Blocks.POWERED_RAIL)));
    public static final RegistryObject<Block> SCRAPWOOD_POWERED_RAIL = BLOCKS.register("scrapwood_powered_rail", () -> new ModPoweredRailBlock(Block.Properties.copy(Blocks.POWERED_RAIL)));
    public static final RegistryObject<Block> WHISPUR_POWERED_RAIL = BLOCKS.register("whispur_powered_rail", () -> new ModPoweredRailBlock(Block.Properties.copy(Blocks.POWERED_RAIL)));
    public static final RegistryObject<Block> PETRIFIED_POWERED_RAIL = BLOCKS.register("petrified_powered_rail", () -> new ModPoweredRailBlock(Block.Properties.copy(Blocks.POWERED_RAIL)));
    public static final RegistryObject<Block> SHADE_POWERED_RAIL = BLOCKS.register("shade_powered_rail", () -> new ModPoweredRailBlock(Block.Properties.copy(Blocks.POWERED_RAIL)));

    public static final RegistryObject<Block> ACACIA_ACTIVATOR_RAIL = BLOCKS.register("acacia_activator_rail", () -> new ModActivatorRailBlock(Block.Properties.copy(Blocks.ACTIVATOR_RAIL)));
    public static final RegistryObject<Block> BIRCH_ACTIVATOR_RAIL = BLOCKS.register("birch_activator_rail", () -> new ModActivatorRailBlock(Block.Properties.copy(Blocks.ACTIVATOR_RAIL)));
    public static final RegistryObject<Block> CRIMSON_ACTIVATOR_RAIL = BLOCKS.register("crimson_activator_rail", () -> new ModActivatorRailBlock(Block.Properties.copy(Blocks.ACTIVATOR_RAIL)));
    public static final RegistryObject<Block> DARK_OAK_ACTIVATOR_RAIL = BLOCKS.register("dark_oak_activator_rail", () -> new ModActivatorRailBlock(Block.Properties.copy(Blocks.ACTIVATOR_RAIL)));
    public static final RegistryObject<Block> JUNGLE_ACTIVATOR_RAIL = BLOCKS.register("jungle_activator_rail", () -> new ModActivatorRailBlock(Block.Properties.copy(Blocks.ACTIVATOR_RAIL)));
    public static final RegistryObject<Block> MANGROVE_ACTIVATOR_RAIL = BLOCKS.register("mangrove_activator_rail", () -> new ModActivatorRailBlock(Block.Properties.copy(Blocks.ACTIVATOR_RAIL)));
    public static final RegistryObject<Block> SPRUCE_ACTIVATOR_RAIL = BLOCKS.register("spruce_activator_rail", () -> new ModActivatorRailBlock(Block.Properties.copy(Blocks.ACTIVATOR_RAIL)));
    public static final RegistryObject<Block> WARPED_ACTIVATOR_RAIL = BLOCKS.register("warped_activator_rail", () -> new ModActivatorRailBlock(Block.Properties.copy(Blocks.ACTIVATOR_RAIL)));
    public static final RegistryObject<Block> SCRAPWOOD_ACTIVATOR_RAIL = BLOCKS.register("scrapwood_activator_rail", () -> new ModActivatorRailBlock(Block.Properties.copy(Blocks.ACTIVATOR_RAIL)));
    public static final RegistryObject<Block> WHISPUR_ACTIVATOR_RAIL = BLOCKS.register("whispur_activator_rail", () -> new ModActivatorRailBlock(Block.Properties.copy(Blocks.ACTIVATOR_RAIL)));
    public static final RegistryObject<Block> PETRIFIED_ACTIVATOR_RAIL = BLOCKS.register("petrified_activator_rail", () -> new ModActivatorRailBlock(Block.Properties.copy(Blocks.ACTIVATOR_RAIL)));
    public static final RegistryObject<Block> SHADE_ACTIVATOR_RAIL = BLOCKS.register("shade_activator_rail", () -> new ModActivatorRailBlock(Block.Properties.copy(Blocks.ACTIVATOR_RAIL)));

    public static final RegistryObject<Block> ACACIA_HEDGE = BLOCKS.register("acacia_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.ACACIA_LEAVES)));
    public static final RegistryObject<Block> AZALEA_HEDGE = BLOCKS.register("azalea_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.AZALEA_LEAVES)));
    public static final RegistryObject<Block> FLOWERING_AZALEA_HEDGE = BLOCKS.register("flowering_azalea_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.FLOWERING_AZALEA_LEAVES)));
    public static final RegistryObject<Block> BIRCH_HEDGE = BLOCKS.register("birch_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.BIRCH_LEAVES)));
    public static final RegistryObject<Block> DARK_OAK_HEDGE = BLOCKS.register("dark_oak_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.DARK_OAK_LEAVES)));
    public static final RegistryObject<Block> JUNGLE_HEDGE = BLOCKS.register("jungle_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.JUNGLE_LEAVES)));
    public static final RegistryObject<Block> MANGROVE_HEDGE = BLOCKS.register("mangrove_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.MANGROVE_LEAVES)));
    public static final RegistryObject<Block> OAK_HEDGE = BLOCKS.register("oak_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> SPRUCE_HEDGE = BLOCKS.register("spruce_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES)));
    public static final RegistryObject<Block> SHADE_HEDGE = BLOCKS.register("shade_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> TEAL_SHADE_HEDGE = BLOCKS.register("teal_shade_hedge", () -> new HedgeBlock(Block.Properties.copy(SHADE_HEDGE.get())));
    public static final RegistryObject<Block> RED_SHADE_HEDGE = BLOCKS.register("red_shade_hedge", () -> new HedgeBlock(Block.Properties.copy(SHADE_HEDGE.get())));
    public static final RegistryObject<Block> PURPLE_SHADE_HEDGE = BLOCKS.register("purple_shade_hedge", () -> new HedgeBlock(Block.Properties.copy(SHADE_HEDGE.get())));

    public static final RegistryObject<Block> ACACIA_HEDGE_WALL = BLOCKS.register("acacia_hedge_wall", () -> new LeafyWallBlock(Block.Properties.copy(Blocks.ACACIA_LEAVES)));
    public static final RegistryObject<Block> AZALEA_HEDGE_WALL = BLOCKS.register("azalea_hedge_wall", () -> new LeafyWallBlock(Block.Properties.copy(Blocks.AZALEA_LEAVES)));
    public static final RegistryObject<Block> FLOWERING_AZALEA_HEDGE_WALL = BLOCKS.register("flowering_azalea_hedge_wall", () -> new LeafyWallBlock(Block.Properties.copy(Blocks.FLOWERING_AZALEA_LEAVES)));
    public static final RegistryObject<Block> BIRCH_HEDGE_WALL = BLOCKS.register("birch_hedge_wall", () -> new LeafyWallBlock(Block.Properties.copy(Blocks.BIRCH_LEAVES)));
    public static final RegistryObject<Block> DARK_OAK_HEDGE_WALL = BLOCKS.register("dark_oak_hedge_wall", () -> new LeafyWallBlock(Block.Properties.copy(Blocks.DARK_OAK_LEAVES)));
    public static final RegistryObject<Block> JUNGLE_HEDGE_WALL = BLOCKS.register("jungle_hedge_wall", () -> new LeafyWallBlock(Block.Properties.copy(Blocks.JUNGLE_LEAVES)));
    public static final RegistryObject<Block> MANGROVE_HEDGE_WALL = BLOCKS.register("mangrove_hedge_wall", () -> new LeafyWallBlock(Block.Properties.copy(Blocks.MANGROVE_LEAVES)));
    public static final RegistryObject<Block> OAK_HEDGE_WALL = BLOCKS.register("oak_hedge_wall", () -> new LeafyWallBlock(Block.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> SPRUCE_HEDGE_WALL = BLOCKS.register("spruce_hedge_wall", () -> new LeafyWallBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES)));
    public static final RegistryObject<Block> SHADE_HEDGE_WALL = BLOCKS.register("shade_hedge_wall", () -> new LeafyWallBlock(Block.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> TEAL_SHADE_HEDGE_WALL = BLOCKS.register("teal_shade_hedge_wall", () -> new LeafyWallBlock(Block.Properties.copy(SHADE_HEDGE.get())));
    public static final RegistryObject<Block> RED_SHADE_HEDGE_WALL = BLOCKS.register("red_shade_hedge_wall", () -> new LeafyWallBlock(Block.Properties.copy(SHADE_HEDGE.get())));
    public static final RegistryObject<Block> PURPLE_SHADE_HEDGE_WALL = BLOCKS.register("purple_shade_hedge_wall", () -> new LeafyWallBlock(Block.Properties.copy(SHADE_HEDGE.get())));

    public static final RegistryObject<Block> BLACKSTONE_FURNACE = BLOCKS.register("blackstone_furnace", () -> new ModFurnaceBlock(Block.Properties.copy(Blocks.FURNACE)));
    public static final RegistryObject<Block> DEEPSLATE_FURNACE = BLOCKS.register("deepslate_furnace", () -> new ModFurnaceBlock(Block.Properties.copy(Blocks.FURNACE)));
    public static final RegistryObject<Block> ANDESITE_FURNACE = BLOCKS.register("andesite_furnace", () -> new ModFurnaceBlock(Block.Properties.copy(Blocks.FURNACE)));
    public static final RegistryObject<Block> DIORITE_FURNACE = BLOCKS.register("diorite_furnace", () -> new ModFurnaceBlock(Block.Properties.copy(Blocks.FURNACE)));
    public static final RegistryObject<Block> GRANITE_FURNACE = BLOCKS.register("granite_furnace", () -> new ModFurnaceBlock(Block.Properties.copy(Blocks.FURNACE)));

    public static final RegistryObject<Block> BLACKSTONE_DISPENSER = BLOCKS.register("blackstone_dispenser", () -> new ModDispenserBlock(Block.Properties.copy(Blocks.DISPENSER)));
    public static final RegistryObject<Block> DEEPSLATE_DISPENSER = BLOCKS.register("deepslate_dispenser", () -> new ModDispenserBlock(Block.Properties.copy(Blocks.DISPENSER)));
    public static final RegistryObject<Block> ANDESITE_DISPENSER = BLOCKS.register("andesite_dispenser", () -> new ModDispenserBlock(Block.Properties.copy(Blocks.DISPENSER)));
    public static final RegistryObject<Block> DIORITE_DISPENSER = BLOCKS.register("diorite_dispenser", () -> new ModDispenserBlock(Block.Properties.copy(Blocks.DISPENSER)));
    public static final RegistryObject<Block> GRANITE_DISPENSER = BLOCKS.register("granite_dispenser", () -> new ModDispenserBlock(Block.Properties.copy(Blocks.DISPENSER)));

    public static final RegistryObject<Block> BLACKSTONE_DROPPER = BLOCKS.register("blackstone_dropper", () -> new ModDropperBlock(Block.Properties.copy(Blocks.DROPPER)));
    public static final RegistryObject<Block> DEEPSLATE_DROPPER = BLOCKS.register("deepslate_dropper", () -> new ModDropperBlock(Block.Properties.copy(Blocks.DROPPER)));
    public static final RegistryObject<Block> ANDESITE_DROPPER = BLOCKS.register("andesite_dropper", () -> new ModDropperBlock(Block.Properties.copy(Blocks.DROPPER)));
    public static final RegistryObject<Block> DIORITE_DROPPER = BLOCKS.register("diorite_dropper", () -> new ModDropperBlock(Block.Properties.copy(Blocks.DROPPER)));
    public static final RegistryObject<Block> GRANITE_DROPPER = BLOCKS.register("granite_dropper", () -> new ModDropperBlock(Block.Properties.copy(Blocks.DROPPER)));

    public static final RegistryObject<Block> NETHER_GOLD_BLOCK = BLOCKS.register("nether_gold_block", () -> new Block(Block.Properties.copy(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> NETHER_GOLD_ORE = BLOCKS.register("nether_gold_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).strength(0.5F, 3.0F).sound(SoundType.NETHER_GOLD_ORE), UniformInt.of(0, 1)));
    public static final RegistryObject<Block> NETHER_DIAMOND_ORE = BLOCKS.register("nether_diamond_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).strength(2.5F, 6.0F).sound(SoundType.NETHER_GOLD_ORE).requiresCorrectToolForDrops(), UniformInt.of(0, 6)));
    public static final RegistryObject<Block> BASALT_DIAMOND_ORE = BLOCKS.register("basalt_diamond_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).strength(2.0F, 6.0F).sound(SoundType.BASALT).requiresCorrectToolForDrops(), UniformInt.of(0, 6)));

    public static final RegistryObject<Block> WITHERED_BLOCK = BLOCKS.register("withered_block", () -> new Block(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(2.0F, 20.0F).sound(SoundType.NETHERITE_BLOCK)));

    public static final RegistryObject<Block> NETHER_BRISTLE = BLOCKS.register("nether_bristle", () -> new NetherBristleBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.WOOD).noCollission().instabreak().sound(SoundType.AZALEA_LEAVES).offsetType(BlockBehaviour.OffsetType.XZ)));
    public static final RegistryObject<Block> WHISPUR_ROOT = BLOCKS.register("whispur_root", () -> new WhispurRootBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_LIGHT_GRAY).strength(0.4F).sound(SoundType.BONE_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> SCRAPWOOD_PLANKS = BLOCKS.register("scrapwood_planks", () -> new Block(Block.Properties.copy(Blocks.CRIMSON_PLANKS)));
    public static final RegistryObject<Block> SCRAPWOOD_HEAP = BLOCKS.register("scrapwood_heap", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.CRIMSON_STEM)));
    public static final RegistryObject<Block> SCRAPWOOD_SLAB = BLOCKS.register("scrapwood_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.CRIMSON_SLAB)));
    public static final RegistryObject<Block> SCRAPWOOD_STAIRS = BLOCKS.register("scrapwood_stairs", () -> new StairBlock(() -> SCRAPWOOD_PLANKS.get().defaultBlockState(), Block.Properties.copy(Blocks.CRIMSON_STAIRS)));
    public static final RegistryObject<Block> SCRAPWOOD_FENCE = BLOCKS.register("scrapwood_fence", () -> new FenceBlock(Block.Properties.copy(Blocks.CRIMSON_FENCE)));
    public static final RegistryObject<Block> SCRAPWOOD_FENCE_GATE = BLOCKS.register("scrapwood_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.CRIMSON_FENCE_GATE), WoodType.CRIMSON));
    public static final RegistryObject<Block> SCRAPWOOD_BUTTON = BLOCKS.register("scrapwood_button", () -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F), ModBlocks.BlockSets.SCRAPWOOD, 30, true));
    public static final RegistryObject<Block> SCRAPWOOD_PRESSURE_PLATE = BLOCKS.register("scrapwood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.CRIMSON_PRESSURE_PLATE), ModBlocks.BlockSets.SCRAPWOOD));
    public static final RegistryObject<Block> SCRAPWOOD_DOOR = BLOCKS.register("scrapwood_door", () -> new DoorBlock(Block.Properties.copy(Blocks.CRIMSON_DOOR), ModBlocks.BlockSets.SCRAPWOOD));
    public static final RegistryObject<Block> SCRAPWOOD_TRAPDOOR = BLOCKS.register("scrapwood_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.CRIMSON_TRAPDOOR), ModBlocks.BlockSets.SCRAPWOOD));
    public static final RegistryObject<Block> SCRAPWOOD_SIGN = BLOCKS.register("scrapwood_sign", () -> new ModStandingSignBlock(Block.Properties.copy(Blocks.CRIMSON_SIGN), ModBlocks.WoodTypes.SCRAPWOOD));
    public static final RegistryObject<Block> SCRAPWOOD_WALL_SIGN = BLOCKS.register("scrapwood_wall_sign", () -> new ModWallSignBlock(Block.Properties.copy(Blocks.CRIMSON_WALL_SIGN), ModBlocks.WoodTypes.SCRAPWOOD));

    public static final RegistryObject<Block> WHISPUR_PLANKS = BLOCKS.register("whispur_planks", () -> new Block(Block.Properties.copy(Blocks.WARPED_PLANKS)));
    public static final RegistryObject<Block> WHISPUR_SLAB = BLOCKS.register("whispur_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.WARPED_SLAB)));
    public static final RegistryObject<Block> WHISPUR_STAIRS = BLOCKS.register("whispur_stairs", () -> new StairBlock(() -> WHISPUR_PLANKS.get().defaultBlockState(), Block.Properties.copy(Blocks.WARPED_STAIRS)));
    public static final RegistryObject<Block> WHISPUR_FENCE = BLOCKS.register("whispur_fence", () -> new FenceBlock(Block.Properties.copy(Blocks.WARPED_FENCE)));
    public static final RegistryObject<Block> WHISPUR_FENCE_GATE = BLOCKS.register("whispur_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.WARPED_FENCE_GATE), WoodType.WARPED));
    public static final RegistryObject<Block> WHISPUR_BUTTON = BLOCKS.register("whispur_button", () -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F), ModBlocks.BlockSets.WHISPUR, 30, true));
    public static final RegistryObject<Block> WHISPUR_PRESSURE_PLATE = BLOCKS.register("whispur_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.WARPED_PRESSURE_PLATE), ModBlocks.BlockSets.WHISPUR));
    public static final RegistryObject<Block> WHISPUR_DOOR = BLOCKS.register("whispur_door", () -> new DoorBlock(Block.Properties.copy(Blocks.WARPED_DOOR), ModBlocks.BlockSets.WHISPUR));
    public static final RegistryObject<Block> WHISPUR_TRAPDOOR = BLOCKS.register("whispur_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.WARPED_TRAPDOOR), ModBlocks.BlockSets.WHISPUR));
    public static final RegistryObject<Block> WHISPUR_SIGN = BLOCKS.register("whispur_sign", () -> new ModStandingSignBlock(Block.Properties.copy(Blocks.WARPED_SIGN), ModBlocks.WoodTypes.WHISPUR));
    public static final RegistryObject<Block> WHISPUR_WALL_SIGN = BLOCKS.register("whispur_wall_sign", () -> new ModWallSignBlock(Block.Properties.copy(Blocks.WARPED_WALL_SIGN), ModBlocks.WoodTypes.WHISPUR));

    public static final RegistryObject<Block> STRIPPED_PETRIFIED_LOG = BLOCKS.register("stripped_petrified_log", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3.0F, 8.0F).sound(ModSoundType.PETRIFIED_LOG)));
    public static final RegistryObject<Block> PETRIFIED_LOG = BLOCKS.register("petrified_log", () -> new StrippableLogBlock(Block.Properties.copy(Blocks.OAK_LOG).strength(3.0F, 8.0F).sound(ModSoundType.PETRIFIED_LOG), STRIPPED_PETRIFIED_LOG.get()));
    public static final RegistryObject<Block> PETRIFIED_WOOD = BLOCKS.register("petrified_wood", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.OAK_WOOD).strength(3.0F, 8.0F).sound(ModSoundType.PETRIFIED_LOG)));
    public static final RegistryObject<Block> PETRIFIED_PLANKS = BLOCKS.register("petrified_planks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.WOOD).strength(3.0F, 8.0F).sound(ModSoundType.PETRIFIED_PLANKS)));
    public static final RegistryObject<Block> PETRIFIED_SLAB = BLOCKS.register("petrified_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.OAK_SLAB).strength(3.0F, 8.0F).sound(ModSoundType.PETRIFIED_PLANKS)));
    public static final RegistryObject<Block> PETRIFIED_STAIRS = BLOCKS.register("petrified_stairs", () -> new StairBlock(() -> PETRIFIED_PLANKS.get().defaultBlockState(), Block.Properties.copy(Blocks.OAK_STAIRS).strength(3.0F, 8.0F).sound(ModSoundType.PETRIFIED_PLANKS)));
    public static final RegistryObject<Block> PETRIFIED_FENCE = BLOCKS.register("petrified_fence", () -> new FenceBlock(Block.Properties.copy(Blocks.OAK_FENCE).strength(3.0F, 8.0F).sound(ModSoundType.PETRIFIED_PLANKS)));
    public static final RegistryObject<Block> PETRIFIED_FENCE_GATE = BLOCKS.register("petrified_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE).strength(3.0F, 8.0F).sound(ModSoundType.PETRIFIED_PLANKS), WoodType.OAK));
    public static final RegistryObject<Block> PETRIFIED_BUTTON = BLOCKS.register("petrified_button", () -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(ModSoundType.PETRIFIED_PLANKS), ModBlocks.BlockSets.PETRIFIED, 30, true));
    public static final RegistryObject<Block> PETRIFIED_PRESSURE_PLATE = BLOCKS.register("petrified_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).strength(0.5F).sound(ModSoundType.PETRIFIED_PLANKS), ModBlocks.BlockSets.PETRIFIED));
    public static final RegistryObject<Block> PETRIFIED_DOOR = BLOCKS.register("petrified_door", () -> new DoorBlock(Block.Properties.copy(Blocks.OAK_DOOR).strength(3.0F, 8.0F).sound(ModSoundType.PETRIFIED_PLANKS), ModBlocks.BlockSets.PETRIFIED));
    public static final RegistryObject<Block> PETRIFIED_TRAPDOOR = BLOCKS.register("petrified_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR).strength(3.0F, 8.0F).sound(ModSoundType.PETRIFIED_PLANKS), ModBlocks.BlockSets.PETRIFIED));
    public static final RegistryObject<Block> PETRIFIED_SIGN = BLOCKS.register("petrified_sign", () -> new ModStandingSignBlock(Block.Properties.copy(Blocks.OAK_SIGN).strength(3.0F, 8.0F).sound(ModSoundType.PETRIFIED_PLANKS), ModBlocks.WoodTypes.PETRIFIED));
    public static final RegistryObject<Block> PETRIFIED_WALL_SIGN = BLOCKS.register("petrified_wall_sign", () -> new ModWallSignBlock(Block.Properties.copy(Blocks.OAK_WALL_SIGN).strength(3.0F, 8.0F).sound(ModSoundType.PETRIFIED_PLANKS), ModBlocks.WoodTypes.PETRIFIED));

    public static final RegistryObject<Block> CARAMINE_RYE = BLOCKS.register("caramine_rye", () -> new CaramineRyeBlock(Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));

    public static final RegistryObject<Block> TWISTING_VINES_PLANT = BLOCKS.register("twisting_vines_plant", () -> new ModTwistingVinesPlantBlock(Block.Properties.copy(Blocks.TWISTING_VINES_PLANT)));
    public static final RegistryObject<Block> WARPED_ROOTS = BLOCKS.register("warped_roots", () -> new ModWarpedRootsBlock(Block.Properties.copy(Blocks.WARPED_ROOTS)));

    public static final RegistryObject<Block> CARVED_PUMPKIN = BLOCKS.register("carved_pumpkin", () -> new ModCarvedPumpkinBlock(Block.Properties.copy(Blocks.CARVED_PUMPKIN).lightLevel(value -> value.getValue(ModCarvedPumpkinBlock.LIT) ? 15 : 0)));

    public static final RegistryObject<Block> STRIPPED_SHADE_LOG = BLOCKS.register("stripped_shade_log", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SHADE_LOG = BLOCKS.register("shade_log", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SHADE_WOOD = BLOCKS.register("shade_wood", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SHADE_PLANKS = BLOCKS.register("shade_planks", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SHADE_SLAB = BLOCKS.register("shade_slab", () -> new SlabBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SHADE_STAIRS = BLOCKS.register("shade_stairs", () -> new StairBlock(() -> SHADE_PLANKS.get().defaultBlockState(), Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SHADE_FENCE = BLOCKS.register("shade_fence", () -> new FenceBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SHADE_FENCE_GATE = BLOCKS.register("shade_fence_gate", () -> new FenceGateBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).strength(2.0F).sound(SoundType.WOOD), WoodType.OAK));
    public static final RegistryObject<Block> SHADE_BUTTON = BLOCKS.register("shade_button", () -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD), ModBlocks.BlockSets.SHADE, 30, true));
    public static final RegistryObject<Block> SHADE_PRESSURE_PLATE = BLOCKS.register("shade_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD), ModBlocks.BlockSets.SHADE));
    public static final RegistryObject<Block> SHADE_DOOR = BLOCKS.register("shade_door", () -> new DoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).strength(2.0F).sound(SoundType.WOOD), ModBlocks.BlockSets.SHADE));
    public static final RegistryObject<Block> SHADE_TRAPDOOR = BLOCKS.register("shade_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR), ModBlocks.BlockSets.SHADE));
    public static final RegistryObject<Block> SHADE_SIGN = BLOCKS.register("shade_sign", () -> new ModStandingSignBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).strength(2.0F).sound(SoundType.WOOD), ModBlocks.WoodTypes.SHADE));
    public static final RegistryObject<Block> SHADE_WALL_SIGN = BLOCKS.register("shade_wall_sign", () -> new ModWallSignBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).strength(2.0F).sound(SoundType.WOOD), ModBlocks.WoodTypes.SHADE));

    public static final RegistryObject<Block> SHADE_LEAVES = BLOCKS.register("shade_leaves", () -> new LeavesBlock(Block.Properties.of(Material.LEAVES, MaterialColor.COLOR_GREEN).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> TEAL_SHADE_LEAVES = BLOCKS.register("teal_shade_leaves", () -> new LeavesBlock(Block.Properties.copy(SHADE_LEAVES.get())));
    public static final RegistryObject<Block> RED_SHADE_LEAVES = BLOCKS.register("red_shade_leaves", () -> new LeavesBlock(Block.Properties.copy(SHADE_LEAVES.get())));
    public static final RegistryObject<Block> PURPLE_SHADE_LEAVES = BLOCKS.register("purple_shade_leaves", () -> new LeavesBlock(Block.Properties.copy(SHADE_LEAVES.get())));

    public static final RegistryObject<Block> SHADE_SAPLING = BLOCKS.register("shade_sapling", () -> new ShadeSaplingBlock(new ShadeTree(ShadeTree.Color.NORMAL), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> TEAL_SHADE_SAPLING = BLOCKS.register("teal_shade_sapling", () -> new ShadeSaplingBlock(new ShadeTree(ShadeTree.Color.TEAL), Block.Properties.copy(SHADE_SAPLING.get())));
    public static final RegistryObject<Block> RED_SHADE_SAPLING = BLOCKS.register("red_shade_sapling", () -> new ShadeSaplingBlock(new ShadeTree(ShadeTree.Color.RED), Block.Properties.copy(SHADE_SAPLING.get())));
    public static final RegistryObject<Block> PURPLE_SHADE_SAPLING = BLOCKS.register("purple_shade_sapling", () -> new ShadeSaplingBlock(new ShadeTree(ShadeTree.Color.PURPLE), Block.Properties.copy(SHADE_SAPLING.get())));

    public static final RegistryObject<Block> DURIAN = BLOCKS.register("durian", () -> new DurianBlock(Block.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_BROWN).strength(0.2F).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> HADALITE = BLOCKS.register("hadalite", () -> new HadaliteBlock(Block.Properties.copy(Blocks.BEDROCK)));

    public static final RegistryObject<Block> OLD_GOLD = BLOCKS.register("old_gold", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> OLD_NETHER_GOLD = BLOCKS.register("old_nether_gold", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)));
}
