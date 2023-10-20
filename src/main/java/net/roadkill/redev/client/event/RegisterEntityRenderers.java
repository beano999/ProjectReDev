package net.roadkill.redev.client.event;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.client.renderer.entity.LithicanRenderer;
import net.roadkill.redev.core.init.EntityInit;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterEntityRenderers
{
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(EntityInit.LITHICAN.get(), LithicanRenderer::new);
    }
}
