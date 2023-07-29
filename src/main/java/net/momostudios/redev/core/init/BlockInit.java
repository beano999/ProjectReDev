package net.momostudios.redev.core.init;

import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.momostudios.redev.ReDev;
import net.momostudios.redev.common.block.HedgeBlock;
import net.momostudios.redev.common.block.ModCampfireBlock;

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

    public static final RegistryObject<Block> ACACIA_LADDER = BLOCKS.register("acacia_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> BIRCH_LADDER = BLOCKS.register("birch_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> CRIMSON_LADDER = BLOCKS.register("crimson_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> DARK_OAK_LADDER = BLOCKS.register("dark_oak_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> JUNGLE_LADDER = BLOCKS.register("jungle_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> MANGROVE_LADDER = BLOCKS.register("mangrove_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> SPRUCE_LADDER = BLOCKS.register("spruce_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> WARPED_LADDER = BLOCKS.register("warped_ladder", () -> new LadderBlock(Block.Properties.copy(Blocks.LADDER)));

    public static final RegistryObject<Block> ACACIA_SMITHING_TABLE = BLOCKS.register("acacia_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> BIRCH_SMITHING_TABLE = BLOCKS.register("birch_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> CRIMSON_SMITHING_TABLE = BLOCKS.register("crimson_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> DARK_OAK_SMITHING_TABLE = BLOCKS.register("dark_oak_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> JUNGLE_SMITHING_TABLE = BLOCKS.register("jungle_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> MANGROVE_SMITHING_TABLE = BLOCKS.register("mangrove_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> SPRUCE_SMITHING_TABLE = BLOCKS.register("spruce_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));
    public static final RegistryObject<Block> WARPED_SMITHING_TABLE = BLOCKS.register("warped_smithing_table", () -> new SmithingTableBlock(Block.Properties.copy(Blocks.SMITHING_TABLE)));

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
    public static final RegistryObject<Block> MANGROVE_SOUL_CAMPFIRE = BLOCKS.register("mangrove_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));
    public static final RegistryObject<Block> SPRUCE_CAMPFIRE = BLOCKS.register("spruce_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> SPRUCE_SOUL_CAMPFIRE = BLOCKS.register("spruce_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));
    public static final RegistryObject<Block> WARPED_CAMPFIRE = BLOCKS.register("warped_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.CAMPFIRE)));
    public static final RegistryObject<Block> WARPED_SOUL_CAMPFIRE = BLOCKS.register("warped_soul_campfire", () -> new ModCampfireBlock(true, 1, Block.Properties.copy(Blocks.SOUL_CAMPFIRE)));

    public static final RegistryObject<Block> ACACIA_RAIL = BLOCKS.register("acacia_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> BIRCH_RAIL = BLOCKS.register("birch_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> CRIMSON_RAIL = BLOCKS.register("crimson_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> DARK_OAK_RAIL = BLOCKS.register("dark_oak_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> JUNGLE_RAIL = BLOCKS.register("jungle_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> MANGROVE_RAIL = BLOCKS.register("mangrove_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> SPRUCE_RAIL = BLOCKS.register("spruce_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));
    public static final RegistryObject<Block> WARPED_RAIL = BLOCKS.register("warped_rail", () -> new RailBlock(Block.Properties.copy(Blocks.RAIL)));

    public static final RegistryObject<Block> ACACIA_HEDGE = BLOCKS.register("acacia_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.ACACIA_LEAVES)));
    public static final RegistryObject<Block> BIRCH_HEDGE = BLOCKS.register("birch_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.BIRCH_LEAVES)));
    public static final RegistryObject<Block> DARK_OAK_HEDGE = BLOCKS.register("dark_oak_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.DARK_OAK_LEAVES)));
    public static final RegistryObject<Block> JUNGLE_HEDGE = BLOCKS.register("jungle_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.JUNGLE_LEAVES)));
    public static final RegistryObject<Block> MANGROVE_HEDGE = BLOCKS.register("mangrove_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.MANGROVE_LEAVES)));
    public static final RegistryObject<Block> OAK_HEDGE = BLOCKS.register("oak_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> SPRUCE_HEDGE = BLOCKS.register("spruce_hedge", () -> new HedgeBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES)));
}
