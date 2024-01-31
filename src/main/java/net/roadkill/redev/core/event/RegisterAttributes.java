package net.roadkill.redev.core.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.common.entity.HoveringInfernoEntity;
import net.roadkill.redev.core.init.EntityInit;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterAttributes
{
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event)
    {   event.put(EntityInit.HOVERING_INFERNO.get(), HoveringInfernoEntity.createAttributes().build());
    }
}
