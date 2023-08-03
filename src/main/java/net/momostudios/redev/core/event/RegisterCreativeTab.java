package net.momostudios.redev.core.event;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.momostudios.redev.ReDev;
import net.momostudios.redev.util.registries.ModItems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterCreativeTab
{
    @SubscribeEvent
    public static void onCreativeTabRegister(CreativeModeTabEvent.Register event)
    {
        event.registerCreativeModeTab(new ResourceLocation(ReDev.MOD_ID, "redev_wood_items"), builder ->
        {
             builder.title(Component.translatable("item_group." + ReDev.MOD_ID + ".wooden_items"))
                    .icon(ModItems.SPRUCE_BOOKSHELF::getDefaultInstance)
                    .displayItems((params, list) ->
                    {
                         list.acceptAll(List.of(
                         ModItems.ACACIA_BOOKSHELF.getDefaultInstance(),
                         ModItems.BIRCH_BOOKSHELF.getDefaultInstance(),
                         ModItems.CRIMSON_BOOKSHELF.getDefaultInstance(),
                         ModItems.DARK_OAK_BOOKSHELF.getDefaultInstance(),
                         ModItems.JUNGLE_BOOKSHELF.getDefaultInstance(),
                         ModItems.MANGROVE_BOOKSHELF.getDefaultInstance(),
                         Items.BOOKSHELF.getDefaultInstance(),
                         ModItems.SPRUCE_BOOKSHELF.getDefaultInstance(),
                         ModItems.WARPED_BOOKSHELF.getDefaultInstance(),

                         ModItems.ACACIA_LADDER.getDefaultInstance(),
                         ModItems.BIRCH_LADDER.getDefaultInstance(),
                         ModItems.CRIMSON_LADDER.getDefaultInstance(),
                         ModItems.DARK_OAK_LADDER.getDefaultInstance(),
                         ModItems.JUNGLE_LADDER.getDefaultInstance(),
                         ModItems.MANGROVE_LADDER.getDefaultInstance(),
                         Items.LADDER.getDefaultInstance(),
                         ModItems.SPRUCE_LADDER.getDefaultInstance(),
                         ModItems.WARPED_LADDER.getDefaultInstance(),

                         ModItems.ACACIA_CAMPFIRE.getDefaultInstance(),
                         ModItems.BIRCH_CAMPFIRE.getDefaultInstance(),
                         ModItems.CRIMSON_CAMPFIRE.getDefaultInstance(),
                         ModItems.DARK_OAK_CAMPFIRE.getDefaultInstance(),
                         ModItems.JUNGLE_CAMPFIRE.getDefaultInstance(),
                         ModItems.MANGROVE_CAMPFIRE.getDefaultInstance(),
                         Items.CAMPFIRE.getDefaultInstance(),
                         ModItems.SPRUCE_CAMPFIRE.getDefaultInstance(),
                         ModItems.WARPED_CAMPFIRE.getDefaultInstance(),

                         ModItems.ACACIA_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.BIRCH_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.CRIMSON_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.DARK_OAK_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.JUNGLE_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.MANGROVE_SOUL_CAMPFIRE.getDefaultInstance(),
                         Items.SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.SPRUCE_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.WARPED_SOUL_CAMPFIRE.getDefaultInstance(),

                         ModItems.ACACIA_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.BIRCH_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.CRIMSON_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.DARK_OAK_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.JUNGLE_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.MANGROVE_SMITHING_TABLE.getDefaultInstance(),
                         Items.SMITHING_TABLE.getDefaultInstance(),
                         ModItems.SPRUCE_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.WARPED_SMITHING_TABLE.getDefaultInstance(),

                         ModItems.ACACIA_RAIL.getDefaultInstance(),
                         ModItems.BIRCH_RAIL.getDefaultInstance(),
                         ModItems.CRIMSON_RAIL.getDefaultInstance(),
                         ModItems.DARK_OAK_RAIL.getDefaultInstance(),
                         ModItems.JUNGLE_RAIL.getDefaultInstance(),
                         ModItems.MANGROVE_RAIL.getDefaultInstance(),
                         Items.RAIL.getDefaultInstance(),
                         ModItems.SPRUCE_RAIL.getDefaultInstance(),
                         ModItems.WARPED_RAIL.getDefaultInstance()));
                    }).build();
        });

        /*event.registerCreativeModeTab(new ResourceLocation(ReDev.MOD_ID, "redev_main"), builder ->
        {
             builder.title(Component.translatable("item_group." + ReDev.MOD_ID + ".main"))
                    .icon(ModItems.BLACKSTONE_FURNACE::getDefaultInstance)
                    .displayItems((params, list) ->
                    {
                    }).build();
        });*/
    }

    @SubscribeEvent
    public static void injectIntoVanillaTabs(CreativeModeTabEvent.BuildContents event)
    {
        if (event.getTab() == CreativeModeTabs.NATURAL_BLOCKS)
        {
            injectItemsAfter(Items.FLOWERING_AZALEA_LEAVES.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.ACACIA_HEDGE.getDefaultInstance(),
                    ModItems.AZALEA_HEDGE.getDefaultInstance(),
                    ModItems.FLOWERING_AZALEA_HEDGE.getDefaultInstance(),
                    ModItems.BIRCH_HEDGE.getDefaultInstance(),
                    ModItems.DARK_OAK_HEDGE.getDefaultInstance(),
                    ModItems.JUNGLE_HEDGE.getDefaultInstance(),
                    ModItems.MANGROVE_HEDGE.getDefaultInstance(),
                    ModItems.OAK_HEDGE.getDefaultInstance(),
                    ModItems.SPRUCE_HEDGE.getDefaultInstance(),

                    ModItems.ACACIA_HEDGE_WALL.getDefaultInstance(),
                    ModItems.AZALEA_HEDGE_WALL.getDefaultInstance(),
                    ModItems.FLOWERING_AZALEA_HEDGE_WALL.getDefaultInstance(),
                    ModItems.BIRCH_HEDGE_WALL.getDefaultInstance(),
                    ModItems.DARK_OAK_HEDGE_WALL.getDefaultInstance(),
                    ModItems.JUNGLE_HEDGE_WALL.getDefaultInstance(),
                    ModItems.MANGROVE_HEDGE_WALL.getDefaultInstance(),
                    ModItems.OAK_HEDGE_WALL.getDefaultInstance(),
                    ModItems.SPRUCE_HEDGE_WALL.getDefaultInstance()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        else if (event.getTab() == CreativeModeTabs.FUNCTIONAL_BLOCKS)
        {
            injectItemsAfter(Items.FURNACE.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.BLACKSTONE_FURNACE.getDefaultInstance(),
                    ModItems.DEEPSLATE_FURNACE.getDefaultInstance(),
                    ModItems.ANDESITE_FURNACE.getDefaultInstance(),
                    ModItems.DIORITE_FURNACE.getDefaultInstance(),
                    ModItems.GRANITE_FURNACE.getDefaultInstance()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        else if (event.getTab() == CreativeModeTabs.INGREDIENTS)
        {
            event.getEntries().putAfter(Items.RABBIT_HIDE.getDefaultInstance(), ModItems.HOGLIN_HIDE.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        else if (event.getTab() == CreativeModeTabs.COMBAT)
        {
            injectItemsAfter(Items.LEATHER_BOOTS.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.HOGLIN_HIDE_CAP.getDefaultInstance(),
                    ModItems.HOGLIN_HIDE_TUNIC.getDefaultInstance(),
                    ModItems.HOGLIN_HIDE_PANTS.getDefaultInstance(),
                    ModItems.HOGLIN_HIDE_BOOTS.getDefaultInstance()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    static void injectItemsAfter(ItemStack injectPoint, MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, Collection<ItemStack> list, CreativeModeTab.TabVisibility visibility)
    {
        List<ItemStack> reverseList = new ArrayList<>(list);
        Collections.reverse(reverseList);
        for (ItemStack itemStack : reverseList)
        {   entries.putAfter(injectPoint, itemStack, visibility);
        }
    }
}
