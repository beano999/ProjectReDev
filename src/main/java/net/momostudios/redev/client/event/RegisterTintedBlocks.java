package net.momostudios.redev.client.event;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.momostudios.redev.util.registries.ModBlocks;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterTintedBlocks
{
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event)
    {
        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return tintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(tintGetter, blockPos) : FoliageColor.getDefaultColor();
        }, ModBlocks.OAK_HEDGE, ModBlocks.JUNGLE_HEDGE, ModBlocks.ACACIA_HEDGE, ModBlocks.DARK_OAK_HEDGE, ModBlocks.JUNGLE_HEDGE);

        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return FoliageColor.getMangroveColor();
        }, ModBlocks.MANGROVE_HEDGE);

        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return FoliageColor.getEvergreenColor();
        }, ModBlocks.SPRUCE_HEDGE);

        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return FoliageColor.getBirchColor();
        }, ModBlocks.BIRCH_HEDGE);
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event)
    {
        event.register((stack, tintIndex) -> {
            return FoliageColor.getDefaultColor();
        }, ModBlocks.OAK_HEDGE, ModBlocks.JUNGLE_HEDGE, ModBlocks.ACACIA_HEDGE, ModBlocks.DARK_OAK_HEDGE, ModBlocks.JUNGLE_HEDGE, ModBlocks.MANGROVE_HEDGE, ModBlocks.SPRUCE_HEDGE);

        event.register((stack, tintIndex) -> {
            return FoliageColor.getMangroveColor();
        }, ModBlocks.MANGROVE_HEDGE);

        event.register((stack, tintIndex) -> {
            return FoliageColor.getBirchColor();
        }, ModBlocks.BIRCH_HEDGE);
    }
}
