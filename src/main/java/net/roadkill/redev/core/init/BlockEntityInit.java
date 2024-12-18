package net.roadkill.redev.core.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.roadkill.redev.ReDev;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BlockEntityInit
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ReDev.MOD_ID);

    @SubscribeEvent
    public static void addBlockEntityBlocks(BlockEntityTypeAddBlocksEvent event)
    {
        event.modify(BlockEntityType.CAMPFIRE,
                     BlockInit.ACACIA_CAMPFIRE.value(),  BlockInit.BIRCH_CAMPFIRE.value(),
                     BlockInit.CRIMSON_CAMPFIRE.value(), BlockInit.DARK_OAK_CAMPFIRE.value(),
                     BlockInit.JUNGLE_CAMPFIRE.value(),  BlockInit.MANGROVE_CAMPFIRE.value(),
                     BlockInit.OAK_CAMPFIRE.value(),     BlockInit.SPRUCE_CAMPFIRE.value(),
                     BlockInit.WARPED_CAMPFIRE.value(),  BlockInit.PETRIFIED_CAMPFIRE.value(),
                     BlockInit.SCRAPWOOD_CAMPFIRE.value(), BlockInit.BONE_CAMPFIRE.value(),
                     BlockInit.SHADE_CAMPFIRE.value(),
                     BlockInit.ACACIA_SOUL_CAMPFIRE.value(),  BlockInit.BIRCH_SOUL_CAMPFIRE.value(),
                     BlockInit.CRIMSON_SOUL_CAMPFIRE.value(), BlockInit.DARK_OAK_SOUL_CAMPFIRE.value(),
                     BlockInit.JUNGLE_SOUL_CAMPFIRE.value(),  BlockInit.MANGROVE_SOUL_CAMPFIRE.value(),
                     BlockInit.OAK_SOUL_CAMPFIRE.value(),     BlockInit.SPRUCE_SOUL_CAMPFIRE.value(),
                     BlockInit.WARPED_SOUL_CAMPFIRE.value(), BlockInit.PETRIFIED_SOUL_CAMPFIRE.value(),
                     BlockInit.SCRAPWOOD_SOUL_CAMPFIRE.value(), BlockInit.BONE_SOUL_CAMPFIRE.value(),
                     BlockInit.SHADE_SOUL_CAMPFIRE.value());

        event.modify(BlockEntityType.FURNACE,
                     BlockInit.BLACKSTONE_FURNACE.value(), BlockInit.DEEPSLATE_FURNACE.value(), BlockInit.ANDESITE_FURNACE.value(),
                     BlockInit.DIORITE_FURNACE.value(), BlockInit.GRANITE_FURNACE.value());

        event.modify(BlockEntityType.DISPENSER,
                     BlockInit.BLACKSTONE_DISPENSER.value(), BlockInit.DEEPSLATE_DISPENSER.value(), BlockInit.ANDESITE_DISPENSER.value(),
                     BlockInit.DIORITE_DISPENSER.value(), BlockInit.GRANITE_DISPENSER.value());

        event.modify(BlockEntityType.DROPPER,
                     BlockInit.BLACKSTONE_DROPPER.value(), BlockInit.DEEPSLATE_DROPPER.value(), BlockInit.ANDESITE_DROPPER.value(),
                     BlockInit.DIORITE_DROPPER.value(), BlockInit.GRANITE_DROPPER.value());

        event.modify(BlockEntityType.SIGN,
                     BlockInit.SCRAPWOOD_SIGN.value(), BlockInit.SCRAPWOOD_WALL_SIGN.value(),
                     BlockInit.WHISPUR_SIGN.value(), BlockInit.WHISPUR_WALL_SIGN.value(),
                     BlockInit.PETRIFIED_SIGN.value(), BlockInit.PETRIFIED_WALL_SIGN.value(),
                     BlockInit.SHADE_SIGN.value(), BlockInit.SHADE_WALL_SIGN.value());
    }
}