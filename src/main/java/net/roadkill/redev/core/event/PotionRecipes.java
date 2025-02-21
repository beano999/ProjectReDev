package net.roadkill.redev.core.event;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.roadkill.redev.core.init.PotionInit;

@EventBusSubscriber
public class PotionRecipes
{
    @SubscribeEvent
    public static void register(RegisterBrewingRecipesEvent event)
    {
        // Sight Potion
        event.getBuilder().addMix(
            Potions.INVISIBILITY,
            Items.SPIDER_EYE,
            PotionInit.SIGHT
        );

        // Extended Sight Potion
        event.getBuilder().addMix(
            PotionInit.SIGHT,
            Items.REDSTONE,
            PotionInit.LONG_SIGHT
        );
    }

    private static ItemStack createPotion(Holder<Potion> potion)
    {
        ItemStack potionItem = Items.POTION.getDefaultInstance();
        potionItem.set(DataComponents.POTION_CONTENTS, potionItem.get(DataComponents.POTION_CONTENTS).withPotion(potion));

        return potionItem;
    }
}
