package net.roadkill.redev.core.entity;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.common.entity.LithicanEntity;
import net.roadkill.redev.common.entity.RevenantEntity;
import net.roadkill.redev.core.init.EntityInit;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterAttributes
{
    @SubscribeEvent
    public static void onRegisterAttributes(EntityAttributeCreationEvent event)
    {   event.put(EntityInit.LITHICAN.get(), LithicanEntity.createAttributes().build());
        event.put(EntityInit.REVENANT.get(), RevenantEntity.createAttributes().build());
    }
}
