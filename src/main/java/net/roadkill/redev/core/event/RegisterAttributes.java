package net.roadkill.redev.core.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.roadkill.redev.common.entity.HoveringInfernoEntity;
import net.roadkill.redev.core.init.EntityInit;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class RegisterAttributes
{
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event)
    {   event.put(EntityInit.HOVERING_INFERNO.get(), HoveringInfernoEntity.createAttributes().build());
    }
}
