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
        event.getBuilder().addRecipe(new IBrewingRecipe()
        {
            @Override
            public boolean isInput(ItemStack input)
            {   return ItemStack.isSameItemSameComponents(input, createPotion(Potions.INVISIBILITY));
            }

            @Override
            public boolean isIngredient(ItemStack ingredient)
            {   return ingredient.is(Items.SPIDER_EYE);
            }

            @Override
            public ItemStack getOutput(ItemStack input, ItemStack ingredient)
            {   return createPotion(PotionInit.SIGHT);
            }
        });

        // Extended Sight Potion
        event.getBuilder().addRecipe(new IBrewingRecipe()
        {
            @Override
            public boolean isInput(ItemStack input)
            {   return ItemStack.isSameItemSameComponents(input, createPotion(PotionInit.SIGHT));
            }

            @Override
            public boolean isIngredient(ItemStack ingredient)
            {   return ingredient.is(Items.REDSTONE);
            }

            @Override
            public ItemStack getOutput(ItemStack input, ItemStack ingredient)
            {   return createPotion(PotionInit.LONG_SIGHT);
            }
        });
    }

    private static ItemStack createPotion(Holder<Potion> potion)
    {
        ItemStack potionItem = Items.POTION.getDefaultInstance();
        potionItem.set(DataComponents.POTION_CONTENTS, potionItem.get(DataComponents.POTION_CONTENTS).withPotion(potion));

        return potionItem;
    }
}
