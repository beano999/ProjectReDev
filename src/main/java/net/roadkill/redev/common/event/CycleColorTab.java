package net.roadkill.redev.common.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class CycleColorTab
{
    public static ItemStack COLORED_BLOCKS_ICON = new ItemStack(Items.RED_WOOL);
    private static final List<ItemStack> COLORED_BLOCKS = List.of(
            new ItemStack(Items.RED_WOOL),
            new ItemStack(Items.ORANGE_WOOL),
            new ItemStack(Items.YELLOW_WOOL),
            new ItemStack(Items.LIME_WOOL),
            new ItemStack(Items.GREEN_WOOL),
            new ItemStack(Items.CYAN_WOOL),
            new ItemStack(Items.BLUE_WOOL),
            new ItemStack(Items.PURPLE_WOOL),
            new ItemStack(Items.MAGENTA_WOOL)
    );

    @SubscribeEvent
    public static void tickBlockColor(TickEvent.ClientTickEvent event)
    {   if (event.phase == TickEvent.Phase.END)
        {   COLORED_BLOCKS_ICON = COLORED_BLOCKS.get((int) (System.currentTimeMillis() / 1000) % COLORED_BLOCKS.size());
        }
    }
}
