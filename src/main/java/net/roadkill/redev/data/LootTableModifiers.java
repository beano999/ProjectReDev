package net.roadkill.redev.data;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.data.loot_modifiers.AddDropsModifier;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class LootTableModifiers
{
    @SubscribeEvent
    public static void registerModifierSerializers(RegisterEvent event)
    {
        event.register(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> {
            helper.register(ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "add_drops"), AddDropsModifier.CODEC);
        });
    }
}
