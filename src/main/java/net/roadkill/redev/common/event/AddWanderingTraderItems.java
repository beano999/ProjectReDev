package net.roadkill.redev.common.event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.roadkill.redev.core.init.ItemInit;

@EventBusSubscriber
public class AddWanderingTraderItems
{
    @SubscribeEvent
    public static void addWanderingLoot(WandererTradesEvent event)
    {
        event.getRareTrades().add(new BasicItemListing(12, new ItemStack(ItemInit.RED_SHADE_SAPLING), 1, 8, 1));
        event.getRareTrades().add(new BasicItemListing(12, new ItemStack(ItemInit.TEAL_SHADE_SAPLING), 1, 8, 1));
        event.getRareTrades().add(new BasicItemListing(12, new ItemStack(ItemInit.PURPLE_SHADE_SAPLING), 1, 8, 1));

        event.getGenericTrades().add(new BasicItemListing(8, new ItemStack(Items.CHERRY_SAPLING), 1, 8, 1));
        event.getGenericTrades().add(new BasicItemListing(1, new ItemStack(Items.PINK_PETALS, 16), 8, 8, 1));

        event.getGenericTrades().add(new BasicItemListing(8, new ItemStack(Items.MANGROVE_PROPAGULE), 1, 8, 1));
    }
}
