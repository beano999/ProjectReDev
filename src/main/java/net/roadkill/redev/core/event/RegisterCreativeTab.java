package net.roadkill.redev.core.event;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.core.init.ItemInit;
import net.roadkill.redev.util.registries.ModItems;

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
        event.registerCreativeModeTab(new ResourceLocation(ReDev.MOD_ID, "wooden_items"), builder ->
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
                         ModItems.PETRIFIED_BOOKSHELF.getDefaultInstance(),
                         ModItems.SCRAPWOOD_BOOKSHELF.getDefaultInstance(),
                         ModItems.SPRUCE_BOOKSHELF.getDefaultInstance(),
                         ModItems.WARPED_BOOKSHELF.getDefaultInstance(),
                         ModItems.WHISPUR_BOOKSHELF.getDefaultInstance(),
                         ModItems.SHADE_BOOKSHELF.getDefaultInstance(),

                         ModItems.ACACIA_LADDER.getDefaultInstance(),
                         ModItems.BIRCH_LADDER.getDefaultInstance(),
                         ModItems.CRIMSON_LADDER.getDefaultInstance(),
                         ModItems.DARK_OAK_LADDER.getDefaultInstance(),
                         ModItems.JUNGLE_LADDER.getDefaultInstance(),
                         ModItems.MANGROVE_LADDER.getDefaultInstance(),
                         Items.LADDER.getDefaultInstance(),
                         ModItems.SPRUCE_LADDER.getDefaultInstance(),
                         ModItems.WARPED_LADDER.getDefaultInstance(),
                         ModItems.SCRAPWOOD_LADDER.getDefaultInstance(),
                         ModItems.WHISPUR_LADDER.getDefaultInstance(),
                         ModItems.PETRIFIED_LADDER.getDefaultInstance(),
                         ModItems.SHADE_LADDER.getDefaultInstance(),

                         ModItems.ACACIA_CAMPFIRE.getDefaultInstance(),
                         ModItems.BIRCH_CAMPFIRE.getDefaultInstance(),
                         ModItems.CRIMSON_CAMPFIRE.getDefaultInstance(),
                         ModItems.DARK_OAK_CAMPFIRE.getDefaultInstance(),
                         ModItems.JUNGLE_CAMPFIRE.getDefaultInstance(),
                         ModItems.MANGROVE_CAMPFIRE.getDefaultInstance(),
                         Items.CAMPFIRE.getDefaultInstance(),
                         ModItems.SPRUCE_CAMPFIRE.getDefaultInstance(),
                         ModItems.WARPED_CAMPFIRE.getDefaultInstance(),
                         ModItems.SCRAPWOOD_CAMPFIRE.getDefaultInstance(),
                         ModItems.BONE_CAMPFIRE.getDefaultInstance(),
                         ModItems.PETRIFIED_CAMPFIRE.getDefaultInstance(),
                         ModItems.SHADE_CAMPFIRE.getDefaultInstance(),

                         ModItems.ACACIA_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.BIRCH_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.CRIMSON_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.DARK_OAK_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.JUNGLE_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.MANGROVE_SOUL_CAMPFIRE.getDefaultInstance(),
                         Items.SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.SPRUCE_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.WARPED_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.SCRAPWOOD_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.BONE_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.PETRIFIED_SOUL_CAMPFIRE.getDefaultInstance(),
                         ModItems.SHADE_SOUL_CAMPFIRE.getDefaultInstance(),

                         ModItems.ACACIA_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.BIRCH_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.CRIMSON_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.DARK_OAK_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.JUNGLE_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.MANGROVE_SMITHING_TABLE.getDefaultInstance(),
                         Items.SMITHING_TABLE.getDefaultInstance(),
                         ModItems.SPRUCE_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.WARPED_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.SCRAPWOOD_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.WHISPUR_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.PETRIFIED_SMITHING_TABLE.getDefaultInstance(),
                         ModItems.SHADE_SMITHING_TABLE.getDefaultInstance(),

                         ModItems.ACACIA_RAIL.getDefaultInstance(),
                         ModItems.BIRCH_RAIL.getDefaultInstance(),
                         ModItems.CRIMSON_RAIL.getDefaultInstance(),
                         ModItems.DARK_OAK_RAIL.getDefaultInstance(),
                         ModItems.JUNGLE_RAIL.getDefaultInstance(),
                         ModItems.MANGROVE_RAIL.getDefaultInstance(),
                         Items.RAIL.getDefaultInstance(),
                         ModItems.SPRUCE_RAIL.getDefaultInstance(),
                         ModItems.WARPED_RAIL.getDefaultInstance(),
                         ModItems.SCRAPWOOD_RAIL.getDefaultInstance(),
                         ModItems.WHISPUR_RAIL.getDefaultInstance(),
                         ModItems.PETRIFIED_RAIL.getDefaultInstance(),
                         ModItems.SHADE_RAIL.getDefaultInstance(),

                         ModItems.ACACIA_DETECTOR_RAIL.getDefaultInstance(),
                         ModItems.BIRCH_DETECTOR_RAIL.getDefaultInstance(),
                         ModItems.CRIMSON_DETECTOR_RAIL.getDefaultInstance(),
                         ModItems.DARK_OAK_DETECTOR_RAIL.getDefaultInstance(),
                         ModItems.JUNGLE_DETECTOR_RAIL.getDefaultInstance(),
                         ModItems.MANGROVE_DETECTOR_RAIL.getDefaultInstance(),
                         Items.DETECTOR_RAIL.getDefaultInstance(),
                         ModItems.SPRUCE_DETECTOR_RAIL.getDefaultInstance(),
                         ModItems.WARPED_DETECTOR_RAIL.getDefaultInstance(),
                         ModItems.SCRAPWOOD_DETECTOR_RAIL.getDefaultInstance(),
                         ModItems.WHISPUR_DETECTOR_RAIL.getDefaultInstance(),
                         ModItems.PETRIFIED_DETECTOR_RAIL.getDefaultInstance(),
                         ModItems.SHADE_DETECTOR_RAIL.getDefaultInstance(),

                         ModItems.ACACIA_POWERED_RAIL.getDefaultInstance(),
                         ModItems.BIRCH_POWERED_RAIL.getDefaultInstance(),
                         ModItems.CRIMSON_POWERED_RAIL.getDefaultInstance(),
                         ModItems.DARK_OAK_POWERED_RAIL.getDefaultInstance(),
                         ModItems.JUNGLE_POWERED_RAIL.getDefaultInstance(),
                         ModItems.MANGROVE_POWERED_RAIL.getDefaultInstance(),
                         Items.POWERED_RAIL.getDefaultInstance(),
                         ModItems.SPRUCE_POWERED_RAIL.getDefaultInstance(),
                         ModItems.WARPED_POWERED_RAIL.getDefaultInstance(),
                         ModItems.SCRAPWOOD_POWERED_RAIL.getDefaultInstance(),
                         ModItems.WHISPUR_POWERED_RAIL.getDefaultInstance(),
                         ModItems.PETRIFIED_POWERED_RAIL.getDefaultInstance(),
                         ModItems.SHADE_POWERED_RAIL.getDefaultInstance(),

                         ModItems.ACACIA_ACTIVATOR_RAIL.getDefaultInstance(),
                         ModItems.BIRCH_ACTIVATOR_RAIL.getDefaultInstance(),
                         ModItems.CRIMSON_ACTIVATOR_RAIL.getDefaultInstance(),
                         ModItems.DARK_OAK_ACTIVATOR_RAIL.getDefaultInstance(),
                         ModItems.JUNGLE_ACTIVATOR_RAIL.getDefaultInstance(),
                         ModItems.MANGROVE_ACTIVATOR_RAIL.getDefaultInstance(),
                         Items.ACTIVATOR_RAIL.getDefaultInstance(),
                         ModItems.SPRUCE_ACTIVATOR_RAIL.getDefaultInstance(),
                         ModItems.WARPED_ACTIVATOR_RAIL.getDefaultInstance(),
                         ModItems.SCRAPWOOD_ACTIVATOR_RAIL.getDefaultInstance(),
                         ModItems.WHISPUR_ACTIVATOR_RAIL.getDefaultInstance(),
                         ModItems.PETRIFIED_ACTIVATOR_RAIL.getDefaultInstance(),
                         ModItems.SHADE_ACTIVATOR_RAIL.getDefaultInstance()));
                    }).build();
        });
    }

    @SubscribeEvent
    public static void injectIntoVanillaTabs(CreativeModeTabEvent.BuildContents event)
    {
        /*
         Natural Blocks
         */
        if (event.getTab().equals(CreativeModeTabs.NATURAL_BLOCKS))
        {
            injectItemsAfter(Items.BEDROCK.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.HADALITE
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.FLOWERING_AZALEA_LEAVES.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.SHADE_LEAVES,
                    ModItems.TEAL_SHADE_LEAVES,
                    ModItems.RED_SHADE_LEAVES,
                    ModItems.PURPLE_SHADE_LEAVES,
                    ModItems.ACACIA_HEDGE,
                    ModItems.AZALEA_HEDGE,
                    ModItems.FLOWERING_AZALEA_HEDGE,
                    ModItems.BIRCH_HEDGE,
                    ModItems.DARK_OAK_HEDGE,
                    ModItems.JUNGLE_HEDGE,
                    ModItems.MANGROVE_HEDGE,
                    ModItems.OAK_HEDGE,
                    ModItems.SPRUCE_HEDGE,

                    ModItems.ACACIA_HEDGE_WALL,
                    ModItems.AZALEA_HEDGE_WALL,
                    ModItems.FLOWERING_AZALEA_HEDGE_WALL,
                    ModItems.BIRCH_HEDGE_WALL,
                    ModItems.DARK_OAK_HEDGE_WALL,
                    ModItems.JUNGLE_HEDGE_WALL,
                    ModItems.MANGROVE_HEDGE_WALL,
                    ModItems.OAK_HEDGE_WALL,
                    ModItems.SPRUCE_HEDGE_WALL
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.MANGROVE_PROPAGULE.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.SHADE_SAPLING,
                    ModItems.TEAL_SHADE_SAPLING,
                    ModItems.RED_SHADE_SAPLING,
                    ModItems.PURPLE_SHADE_SAPLING
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.WARPED_FUNGUS.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.NETHER_BRISTLE,
                    ModItems.WHISPUR_ROOT
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.NETHER_GOLD_ORE.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.NETHER_GOLD_ORE,
                    ModItems.NETHER_DIAMOND_ORE
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.DEEPSLATE_DIAMOND_ORE.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.BASALT_DIAMOND_ORE
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.CARVED_PUMPKIN.getDefaultInstance(), event.getEntries(), List.of(
                    ItemInit.CARVED_PUMPKIN.get(),
                    ItemInit.CARVED_PUMPKIN_CREEPY.get(),
                    ItemInit.CARVED_PUMPKIN_SCOWLING.get(),
                    ItemInit.CARVED_PUMPKIN_CREEPER.get(),
                    ItemInit.CARVED_PUMPKIN_TWISTED.get(),
                    ItemInit.CARVED_PUMPKIN_HORRIFIED.get(),
                    ItemInit.CARVED_PUMPKIN_FURIOUS.get(),
                    ItemInit.CARVED_PUMPKIN_ZOMBIE.get(),
                    ItemInit.CARVED_PUMPKIN_LIT.get(),
                    ItemInit.CARVED_PUMPKIN_CREEPY_LIT.get(),
                    ItemInit.CARVED_PUMPKIN_SCOWLING_LIT.get(),
                    ItemInit.CARVED_PUMPKIN_CREEPER_LIT.get(),
                    ItemInit.CARVED_PUMPKIN_TWISTED_LIT.get(),
                    ItemInit.CARVED_PUMPKIN_HORRIFIED_LIT.get(),
                    ItemInit.CARVED_PUMPKIN_FURIOUS_LIT.get(),
                    ItemInit.CARVED_PUMPKIN_ZOMBIE_LIT.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().remove(Items.CARVED_PUMPKIN.getDefaultInstance());
            event.getEntries().remove(Items.JACK_O_LANTERN.getDefaultInstance());

            injectItemsAfter(ItemInit.CARVED_PUMPKIN_ZOMBIE_LIT.get().getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.DURIAN
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        /*
         Functional Blocks
         */
        else if (event.getTab().equals(CreativeModeTabs.FUNCTIONAL_BLOCKS))
        {
            injectItemsAfter(Items.FURNACE.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.BLACKSTONE_FURNACE,
                    ModItems.DEEPSLATE_FURNACE,
                    ModItems.ANDESITE_FURNACE,
                    ModItems.DIORITE_FURNACE,
                    ModItems.GRANITE_FURNACE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.WARPED_SIGN.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.SCRAPWOOD_SIGN,
                    ModItems.WHISPUR_SIGN,
                    ModItems.PETRIFIED_SIGN
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.LADDER.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.CHAIN_LADDER
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        /*
         Redstone Blocks
         */
        else if (event.getTab().equals(CreativeModeTabs.REDSTONE_BLOCKS))
        {
            injectItemsAfter(Items.DISPENSER.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.BLACKSTONE_DISPENSER,
                    ModItems.DEEPSLATE_DISPENSER,
                    ModItems.ANDESITE_DISPENSER,
                    ModItems.DIORITE_DISPENSER,
                    ModItems.GRANITE_DISPENSER
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.DROPPER.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.BLACKSTONE_DROPPER,
                    ModItems.DEEPSLATE_DROPPER,
                    ModItems.ANDESITE_DROPPER,
                    ModItems.DIORITE_DROPPER,
                    ModItems.GRANITE_DROPPER
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.FURNACE.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.BLACKSTONE_FURNACE,
                    ModItems.DEEPSLATE_FURNACE,
                    ModItems.ANDESITE_FURNACE,
                    ModItems.DIORITE_FURNACE,
                    ModItems.GRANITE_FURNACE
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        /*
         Ingredients
         */
        else if (event.getTab().equals(CreativeModeTabs.INGREDIENTS))
        {
            event.getEntries().putAfter(Items.RABBIT_HIDE.getDefaultInstance(), ModItems.HOGLIN_HIDE.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.GOLD_NUGGET.getDefaultInstance(), ModItems.NETHER_GOLD_NUGGET.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.STICK.getDefaultInstance(), ModItems.WOOD_SCRAP.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.BONE_MEAL.getDefaultInstance(), ModItems.CHARRED_BONE.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            injectItemsAfter(Items.GOLD_INGOT.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.NETHER_GOLD_INGOT,
                    ModItems.WITHERED_INGOT
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        /*
         Combat
         */
        else if (event.getTab().equals(CreativeModeTabs.COMBAT))
        {
            injectItemsAfter(Items.NETHERITE_BOOTS.getDefaultInstance(), event.getEntries(), List.of(
                    //ModItems.HOGLIN_HIDE_CAP,
                    //ModItems.HOGLIN_HIDE_TUNIC,
                    //ModItems.HOGLIN_HIDE_PANTS,
                    //ModItems.HOGLIN_HIDE_BOOTS,
                    ModItems.NETHER_GOLD_HELMET,
                    ModItems.NETHER_GOLD_CHESTPLATE,
                    ModItems.NETHER_GOLD_LEGGINGS,
                    ModItems.NETHER_GOLD_BOOTS,
                    ModItems.WITHERED_HELMET,
                    ModItems.WITHERED_CHESTPLATE,
                    ModItems.WITHERED_LEGGINGS,
                    ModItems.WITHERED_BOOTS
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.NETHERITE_SWORD.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.NETHER_GOLD_SWORD,
                    ModItems.WITHERED_SWORD
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.NETHERITE_AXE.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.NETHER_GOLD_AXE,
                    ModItems.WITHERED_AXE
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.DIAMOND_HORSE_ARMOR.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.NETHERITE_HORSE_ARMOR
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.SHIELD.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.NETHERITE_SHIELD
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        /*
         Building Blocks
         */
        else if (event.getTab().equals(CreativeModeTabs.BUILDING_BLOCKS))
        {
            injectItemsAfter(Items.GOLD_BLOCK.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.NETHER_GOLD_BLOCK, ModItems.OLD_GOLD, ModItems.OLD_NETHER_GOLD
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            injectItemsAfter(Items.WARPED_BUTTON.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.SCRAPWOOD_HEAP,
                    ModItems.SCRAPWOOD_PLANKS,
                    ModItems.SCRAPWOOD_STAIRS,
                    ModItems.SCRAPWOOD_SLAB,
                    ModItems.SCRAPWOOD_FENCE,
                    ModItems.SCRAPWOOD_FENCE_GATE,
                    ModItems.SCRAPWOOD_DOOR,
                    ModItems.SCRAPWOOD_TRAPDOOR,
                    ModItems.SCRAPWOOD_PRESSURE_PLATE,
                    ModItems.SCRAPWOOD_BUTTON,

                    Items.BONE_BLOCK,
                    ModItems.WHISPUR_PLANKS,
                    ModItems.WHISPUR_STAIRS,
                    ModItems.WHISPUR_SLAB,
                    ModItems.WHISPUR_FENCE,
                    ModItems.WHISPUR_FENCE_GATE,
                    ModItems.WHISPUR_DOOR,
                    ModItems.WHISPUR_TRAPDOOR,
                    ModItems.WHISPUR_PRESSURE_PLATE,
                    ModItems.WHISPUR_BUTTON,

                    ModItems.PETRIFIED_LOG,
                    ModItems.PETRIFIED_WOOD,
                    ModItems.STRIPPED_PETRIFIED_LOG,
                    ModItems.PETRIFIED_PLANKS,
                    ModItems.PETRIFIED_STAIRS,
                    ModItems.PETRIFIED_SLAB,
                    ModItems.PETRIFIED_FENCE,
                    ModItems.PETRIFIED_FENCE_GATE,
                    ModItems.PETRIFIED_DOOR,
                    ModItems.PETRIFIED_TRAPDOOR,
                    ModItems.PETRIFIED_PRESSURE_PLATE,
                    ModItems.PETRIFIED_BUTTON,

                    ModItems.SHADE_LOG,
                    ModItems.SHADE_WOOD,
                    ModItems.STRIPPED_SHADE_LOG,
                    ModItems.SHADE_PLANKS,
                    ModItems.SHADE_STAIRS,
                    ModItems.SHADE_SLAB,
                    ModItems.SHADE_FENCE,
                    ModItems.SHADE_FENCE_GATE,
                    ModItems.SHADE_DOOR,
                    ModItems.SHADE_TRAPDOOR,
                    ModItems.SHADE_PRESSURE_PLATE,
                    ModItems.SHADE_BUTTON
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        /*
         Tools & Utilities
         */
        else if (event.getTab().equals(CreativeModeTabs.TOOLS_AND_UTILITIES))
        {
            injectItemsAfter(Items.NETHERITE_HOE.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.NETHER_GOLD_SHOVEL,
                    ModItems.NETHER_GOLD_PICKAXE,
                    ModItems.NETHER_GOLD_AXE,
                    ModItems.NETHER_GOLD_HOE,
                    ModItems.WITHERED_SHOVEL,
                    ModItems.WITHERED_PICKAXE,
                    ModItems.WITHERED_AXE,
                    ModItems.WITHERED_HOE
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        else if (event.getTab().equals(CreativeModeTabs.SPAWN_EGGS))
        {
            injectItemsAfter(Items.ZOMBIFIED_PIGLIN_SPAWN_EGG.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.LITHICAN_SPAWN_EGG,
                    ModItems.REVENANT_SPAWN_EGG
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        else if (event.getTab().equals(CreativeModeTabs.FOOD_AND_DRINKS))
        {
            injectItemsAfter(Items.MELON_SLICE.getDefaultInstance(), event.getEntries(), List.of(
                    ModItems.DURIAN_SLICE
            ), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    static void injectItemsAfter(ItemStack injectPoint, MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, Collection<Item> list, CreativeModeTab.TabVisibility visibility)
    {
        List<Item> reverseList = new ArrayList<>(list);
        Collections.reverse(reverseList);
        for (Item item : reverseList)
        {   entries.putAfter(injectPoint, item.getDefaultInstance(), visibility);
        }
    }

    static void injectItemsBefore(ItemStack injectPoint, MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, Collection<Item> list, CreativeModeTab.TabVisibility visibility)
    {
        for (Item item : list)
        {   entries.putBefore(injectPoint, item.getDefaultInstance(), visibility);
        }
    }
}
