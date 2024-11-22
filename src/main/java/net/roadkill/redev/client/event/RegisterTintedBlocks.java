package net.roadkill.redev.client.event;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.roadkill.redev.core.init.BlockInit;
import net.roadkill.redev.core.init.ItemInit;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegisterTintedBlocks
{
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event)
    {
        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return tintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(tintGetter, blockPos) : FoliageColor.getDefaultColor();
        },
        BlockInit.OAK_HEDGE.value(),      BlockInit.OAK_HEDGE_WALL.value(),
        BlockInit.JUNGLE_HEDGE.value(),   BlockInit.JUNGLE_HEDGE_WALL.value(),
        BlockInit.ACACIA_HEDGE.value(),   BlockInit.ACACIA_HEDGE_WALL.value(),
        BlockInit.DARK_OAK_HEDGE.value(), BlockInit.DARK_OAK_HEDGE_WALL.value());

        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return FoliageColor.getMangroveColor();
        }, BlockInit.MANGROVE_HEDGE.value(), BlockInit.MANGROVE_HEDGE_WALL.value());

        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return FoliageColor.getEvergreenColor();
        }, BlockInit.SPRUCE_HEDGE.value(), BlockInit.SPRUCE_HEDGE_WALL.value());

        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return FoliageColor.getBirchColor();
        }, BlockInit.BIRCH_HEDGE.value(), BlockInit.BIRCH_HEDGE_WALL.value());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event)
    {
        event.register((stack, tintIndex) -> {
            return FoliageColor.getDefaultColor();
        },
        ItemInit.OAK_HEDGE.value(),      ItemInit.OAK_HEDGE_WALL.value(),
        ItemInit.JUNGLE_HEDGE.value(),   ItemInit.JUNGLE_HEDGE_WALL.value(),
        ItemInit.ACACIA_HEDGE.value(),   ItemInit.ACACIA_HEDGE_WALL.value(),
        ItemInit.DARK_OAK_HEDGE.value(), ItemInit.DARK_OAK_HEDGE_WALL.value(),
        ItemInit.JUNGLE_HEDGE.value(),   ItemInit.JUNGLE_HEDGE_WALL.value());

        event.register((stack, tintIndex) -> {
            return FoliageColor.getMangroveColor();
        }, ItemInit.MANGROVE_HEDGE.value(), ItemInit.MANGROVE_HEDGE_WALL.value());

        event.register((stack, tintIndex) -> {
            return FoliageColor.getEvergreenColor();
        }, ItemInit.SPRUCE_HEDGE.value(), ItemInit.SPRUCE_HEDGE_WALL.value());

        event.register((stack, tintIndex) -> {
            return FoliageColor.getBirchColor();
        }, ItemInit.BIRCH_HEDGE.value(), ItemInit.BIRCH_HEDGE_WALL.value());
    }
}
