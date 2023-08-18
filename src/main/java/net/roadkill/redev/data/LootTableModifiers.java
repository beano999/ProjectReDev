package net.roadkill.redev.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.data.loot_modifiers.AddDropsModifier;
import net.roadkill.redev.data.loot_modifiers.RemoveDropsModifier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class LootTableModifiers
{
    @SubscribeEvent
    public static void registerModifierSerializers(RegisterEvent event)
    {
        event.register(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> {
            helper.register(new ResourceLocation(ReDev.MOD_ID, "add_drops"), AddDropsModifier.CODEC);
            helper.register(new ResourceLocation(ReDev.MOD_ID, "remove_drops"), RemoveDropsModifier.CODEC);
        });
    }
}
