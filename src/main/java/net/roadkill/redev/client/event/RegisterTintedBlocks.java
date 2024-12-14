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
    public static final int BIRCH_COLOR = -8345771;
    public static final int SPRUCE_COLOR = -10380959;
    public static final int DEFAULT_COLOR = -12012264;

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event)
    {
        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return tintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(tintGetter, blockPos) : DEFAULT_COLOR;
        },
        BlockInit.OAK_HEDGE.value(),      BlockInit.OAK_HEDGE_WALL.value(),
        BlockInit.JUNGLE_HEDGE.value(),   BlockInit.JUNGLE_HEDGE_WALL.value(),
        BlockInit.ACACIA_HEDGE.value(),   BlockInit.ACACIA_HEDGE_WALL.value(),
        BlockInit.DARK_OAK_HEDGE.value(), BlockInit.DARK_OAK_HEDGE_WALL.value());

        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return tintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(tintGetter, blockPos) : DEFAULT_COLOR;
        }, BlockInit.MANGROVE_HEDGE.value(), BlockInit.MANGROVE_HEDGE_WALL.value());

        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return tintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(tintGetter, blockPos) : SPRUCE_COLOR;
        }, BlockInit.SPRUCE_HEDGE.value(), BlockInit.SPRUCE_HEDGE_WALL.value());

        event.register((state, tintGetter, blockPos, tintIndex) -> {
            return tintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(tintGetter, blockPos) : BIRCH_COLOR;
        }, BlockInit.BIRCH_HEDGE.value(), BlockInit.BIRCH_HEDGE_WALL.value());
    }
}
