package net.roadkill.redev.core.event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.roadkill.redev.core.init.PotionInit;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PotionRecipes
{
    @SubscribeEvent
    public static void register(FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            ItemStack sightPotion = PotionUtils.setPotion(Items.POTION.getDefaultInstance(), PotionInit.SIGHT.get());
            ItemStack invisPotion = PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.INVISIBILITY);
            BrewingRecipeRegistry.addRecipe(Ingredient.of(invisPotion), Ingredient.of(Items.FERMENTED_SPIDER_EYE), sightPotion);
            BrewingRecipeRegistry.addRecipe(Ingredient.of(sightPotion), Ingredient.of(Items.REDSTONE),
                                            PotionUtils.setPotion(Items.POTION.getDefaultInstance(), PotionInit.LONG_SIGHT.get()));
        });
    }
}
