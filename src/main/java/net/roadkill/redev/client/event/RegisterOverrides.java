package net.roadkill.redev.client.event;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.core.init.ItemInit;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegisterOverrides
{
    @SubscribeEvent
    public static void register(FMLClientSetupEvent event)
    {
       event.enqueueWork(() ->
       {
           ItemProperties.register(ItemInit.NETHERITE_SHIELD.get(), ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "blocking"), (stack, level, entity, id) ->
           {
               if (entity != null && entity.getTicksUsingItem() > 0 && entity.getUseItem().equals(stack))
               {    return 1;
               }
               return 0;
           });
           ItemProperties.register(ItemInit.INFERNAL_PLATE.get(), ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "blocking"), (stack, level, entity, id) ->
           {
               if (entity != null && level != null && entity.getTicksUsingItem() > 0 && entity.getUseItem().equals(stack))
               {    return 1;
               }
               return 0;
           });
       });
    }
}
