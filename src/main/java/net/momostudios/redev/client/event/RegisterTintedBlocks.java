package net.momostudios.redev.client.event;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.momostudios.redev.util.registries.ModBlocks;
import net.momostudios.redev.util.registries.ModItems;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterTintedBlocks
{
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event)
    {
        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return tintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(tintGetter, blockPos) : FoliageColor.getDefaultColor();
        }, ModBlocks.OAK_HEDGE, ModBlocks.JUNGLE_HEDGE, ModBlocks.ACACIA_HEDGE, ModBlocks.DARK_OAK_HEDGE, ModBlocks.JUNGLE_HEDGE,
           ModBlocks.OAK_HEDGE_WALL, ModBlocks.JUNGLE_HEDGE_WALL, ModBlocks.ACACIA_HEDGE_WALL, ModBlocks.DARK_OAK_HEDGE_WALL, ModBlocks.JUNGLE_HEDGE_WALL);

        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return FoliageColor.getMangroveColor();
        }, ModBlocks.MANGROVE_HEDGE, ModBlocks.MANGROVE_HEDGE_WALL);

        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return FoliageColor.getEvergreenColor();
        }, ModBlocks.SPRUCE_HEDGE, ModBlocks.SPRUCE_HEDGE_WALL);

        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return FoliageColor.getBirchColor();
        }, ModBlocks.BIRCH_HEDGE, ModBlocks.BIRCH_HEDGE_WALL);
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event)
    {
        event.register((stack, tintIndex) -> {
            return FoliageColor.getDefaultColor();
        }, ModItems.OAK_HEDGE, ModItems.JUNGLE_HEDGE, ModItems.ACACIA_HEDGE, ModItems.DARK_OAK_HEDGE, ModItems.JUNGLE_HEDGE,
           ModItems.OAK_HEDGE_WALL, ModItems.JUNGLE_HEDGE_WALL, ModItems.ACACIA_HEDGE_WALL, ModItems.DARK_OAK_HEDGE_WALL, ModItems.JUNGLE_HEDGE_WALL);

        event.register((stack, tintIndex) -> {
            return FoliageColor.getMangroveColor();
        }, ModItems.MANGROVE_HEDGE, ModItems.MANGROVE_HEDGE_WALL);

        event.register((stack, tintIndex) -> {
            return FoliageColor.getEvergreenColor();
        }, ModItems.SPRUCE_HEDGE, ModItems.SPRUCE_HEDGE_WALL);

        event.register((stack, tintIndex) -> {
            return FoliageColor.getBirchColor();
        }, ModItems.BIRCH_HEDGE, ModItems.BIRCH_HEDGE_WALL);
    }
}
