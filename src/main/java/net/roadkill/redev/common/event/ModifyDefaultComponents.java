package net.roadkill.redev.common.event;

import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DamageResistant;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.roadkill.redev.core.init.ItemInit;
import net.roadkill.redev.util.registries.ModItems;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModifyDefaultComponents
{
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void setVanillaShieldDurability(ModifyDefaultComponentsEvent event)
    {
        // Durability
        event.modify(Items.SHIELD, patch -> patch.set(DataComponents.MAX_DAMAGE, 200));
        // Stack sizes
        event.modify(Items.POTION, patch -> patch.set(DataComponents.MAX_STACK_SIZE, 4));
        event.modify(Items.EGG, patch -> patch.set(DataComponents.MAX_STACK_SIZE, 64));
        event.modify(Items.SNOWBALL, patch -> patch.set(DataComponents.MAX_STACK_SIZE, 64));
        // Flammability
        event.modify(ItemInit.INFERNAL_PLATE.value(), patch -> patch.set(DataComponents.DAMAGE_RESISTANT, new DamageResistant(DamageTypeTags.IS_FIRE)));
        event.modify(Items.BLAZE_POWDER, patch -> patch.set(DataComponents.DAMAGE_RESISTANT, new DamageResistant(DamageTypeTags.IS_FIRE)));
        event.modify(Items.BLAZE_ROD, patch -> patch.set(DataComponents.DAMAGE_RESISTANT, new DamageResistant(DamageTypeTags.IS_FIRE)));
    }
}
