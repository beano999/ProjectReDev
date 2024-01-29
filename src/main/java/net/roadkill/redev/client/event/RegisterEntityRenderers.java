package net.roadkill.redev.client.event;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.client.model.entity.HoveringInfernoModel;
import net.roadkill.redev.client.renderer.entity.*;
import net.roadkill.redev.core.init.EntityInit;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterEntityRenderers
{
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {   event.registerEntityRenderer(EntityInit.LITHICAN.get(), LithicanRenderer::new);
        event.registerEntityRenderer(EntityInit.REVENANT.get(), RevenantRenderer::new);
        event.registerEntityRenderer(EntityInit.DURIAN_THORN.get(), DurianThornRenderer::new);
        event.registerEntityRenderer(EntityInit.DURIAN_THORN.get(), DurianThornRenderer::new);
        event.registerEntityRenderer(EntityInit.HOVERING_INFERNO.get(), HoveringInfernoRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {   event.registerLayerDefinition(HoveringInfernoModel.LAYER_LOCATION, HoveringInfernoModel::createBodyLayer);
    }
}
