package net.momostudios.redev.core.event;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.momostudios.redev.ReDev;
import net.momostudios.redev.util.registries.ModItems;

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
                         list.accept(ModItems.ACACIA_BOOKSHELF.getDefaultInstance());
                         list.accept(ModItems.BIRCH_BOOKSHELF.getDefaultInstance());
                         list.accept(ModItems.CRIMSON_BOOKSHELF.getDefaultInstance());
                         list.accept(ModItems.DARK_OAK_BOOKSHELF.getDefaultInstance());
                         list.accept(ModItems.JUNGLE_BOOKSHELF.getDefaultInstance());
                         list.accept(ModItems.MANGROVE_BOOKSHELF.getDefaultInstance());
                         list.accept(Items.BOOKSHELF.getDefaultInstance());
                         list.accept(ModItems.SPRUCE_BOOKSHELF.getDefaultInstance());
                         list.accept(ModItems.WARPED_BOOKSHELF.getDefaultInstance());

                         list.accept(ModItems.ACACIA_LADDER.getDefaultInstance());
                         list.accept(ModItems.BIRCH_LADDER.getDefaultInstance());
                         list.accept(ModItems.CRIMSON_LADDER.getDefaultInstance());
                         list.accept(ModItems.DARK_OAK_LADDER.getDefaultInstance());
                         list.accept(ModItems.JUNGLE_LADDER.getDefaultInstance());
                         list.accept(ModItems.MANGROVE_LADDER.getDefaultInstance());
                         list.accept(Items.LADDER.getDefaultInstance());
                         list.accept(ModItems.SPRUCE_LADDER.getDefaultInstance());
                         list.accept(ModItems.WARPED_LADDER.getDefaultInstance());

                         list.accept(ModItems.ACACIA_CAMPFIRE.getDefaultInstance());
                         list.accept(ModItems.BIRCH_CAMPFIRE.getDefaultInstance());
                         list.accept(ModItems.CRIMSON_CAMPFIRE.getDefaultInstance());
                         list.accept(ModItems.DARK_OAK_CAMPFIRE.getDefaultInstance());
                         list.accept(ModItems.JUNGLE_CAMPFIRE.getDefaultInstance());
                         list.accept(ModItems.MANGROVE_CAMPFIRE.getDefaultInstance());
                         list.accept(Items.CAMPFIRE.getDefaultInstance());
                         list.accept(ModItems.SPRUCE_CAMPFIRE.getDefaultInstance());
                         list.accept(ModItems.WARPED_CAMPFIRE.getDefaultInstance());

                         list.accept(ModItems.ACACIA_SOUL_CAMPFIRE.getDefaultInstance());
                         list.accept(ModItems.BIRCH_SOUL_CAMPFIRE.getDefaultInstance());
                         list.accept(ModItems.CRIMSON_SOUL_CAMPFIRE.getDefaultInstance());
                         list.accept(ModItems.DARK_OAK_SOUL_CAMPFIRE.getDefaultInstance());
                         list.accept(ModItems.JUNGLE_SOUL_CAMPFIRE.getDefaultInstance());
                         list.accept(ModItems.MANGROVE_SOUL_CAMPFIRE.getDefaultInstance());
                         list.accept(Items.SOUL_CAMPFIRE.getDefaultInstance());
                         list.accept(ModItems.SPRUCE_SOUL_CAMPFIRE.getDefaultInstance());
                         list.accept(ModItems.WARPED_SOUL_CAMPFIRE.getDefaultInstance());

                         list.accept(ModItems.ACACIA_SMITHING_TABLE.getDefaultInstance());
                         list.accept(ModItems.BIRCH_SMITHING_TABLE.getDefaultInstance());
                         list.accept(ModItems.CRIMSON_SMITHING_TABLE.getDefaultInstance());
                         list.accept(ModItems.DARK_OAK_SMITHING_TABLE.getDefaultInstance());
                         list.accept(ModItems.JUNGLE_SMITHING_TABLE.getDefaultInstance());
                         list.accept(ModItems.MANGROVE_SMITHING_TABLE.getDefaultInstance());
                         list.accept(Items.SMITHING_TABLE.getDefaultInstance());
                         list.accept(ModItems.SPRUCE_SMITHING_TABLE.getDefaultInstance());
                         list.accept(ModItems.WARPED_SMITHING_TABLE.getDefaultInstance());

                         list.accept(ModItems.ACACIA_RAIL.getDefaultInstance());
                         list.accept(ModItems.BIRCH_RAIL.getDefaultInstance());
                         list.accept(ModItems.CRIMSON_RAIL.getDefaultInstance());
                         list.accept(ModItems.DARK_OAK_RAIL.getDefaultInstance());
                         list.accept(ModItems.JUNGLE_RAIL.getDefaultInstance());
                         list.accept(ModItems.MANGROVE_RAIL.getDefaultInstance());
                         list.accept(Items.RAIL.getDefaultInstance());
                         list.accept(ModItems.SPRUCE_RAIL.getDefaultInstance());
                         list.accept(ModItems.WARPED_RAIL.getDefaultInstance());
                    }).build();
        });

        event.registerCreativeModeTab(new ResourceLocation(ReDev.MOD_ID, "redev_decoration"), builder ->
        {
             builder.title(Component.translatable("item_group." + ReDev.MOD_ID + ".decoration"))
                    .icon(ModItems.OAK_HEDGE_WALL::getDefaultInstance)
                    .displayItems((params, list) ->
                    {
                         list.accept(ModItems.ACACIA_HEDGE.getDefaultInstance());
                         list.accept(ModItems.BIRCH_HEDGE.getDefaultInstance());
                         list.accept(ModItems.DARK_OAK_HEDGE.getDefaultInstance());
                         list.accept(ModItems.JUNGLE_HEDGE.getDefaultInstance());
                         list.accept(ModItems.MANGROVE_HEDGE.getDefaultInstance());
                         list.accept(ModItems.OAK_HEDGE.getDefaultInstance());
                         list.accept(ModItems.SPRUCE_HEDGE.getDefaultInstance());

                         list.accept(ModItems.ACACIA_HEDGE_WALL.getDefaultInstance());
                         list.accept(ModItems.BIRCH_HEDGE_WALL.getDefaultInstance());
                         list.accept(ModItems.DARK_OAK_HEDGE_WALL.getDefaultInstance());
                         list.accept(ModItems.JUNGLE_HEDGE_WALL.getDefaultInstance());
                         list.accept(ModItems.MANGROVE_HEDGE_WALL.getDefaultInstance());
                         list.accept(ModItems.OAK_HEDGE_WALL.getDefaultInstance());
                         list.accept(ModItems.SPRUCE_HEDGE_WALL.getDefaultInstance());
                    }).build();
        });
    }
}
