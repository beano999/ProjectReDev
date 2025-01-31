package net.roadkill.redev.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.roadkill.redev.client.model.armor.HoglinHideCloakModel;
import net.roadkill.redev.client.model.armor.HoglinHideHeadModel;
import net.roadkill.redev.client.model.armor.HoglinHideHoovesModel;
import net.roadkill.redev.client.model.entity.HoveringInfernoModel;
import net.roadkill.redev.client.renderer.entity.*;
import net.roadkill.redev.common.item.HoglinArmorItem;
import net.roadkill.redev.core.init.EntityInit;
import net.roadkill.redev.core.init.ItemInit;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class RegisterEntityRenderers
{
    public static HoglinHideHeadModel<?> HOGLIN_HIDE_HEAD_MODEL = null;
    public static HoglinHideCloakModel<?> HOGLIN_HIDE_CLOAK_MODEL = null;
    public static HoglinHideHoovesModel<?> HOGLIN_HIDE_HOOVES_MODEL = null;

    public static void checkForInitModels()
    {
        if (HOGLIN_HIDE_HEAD_MODEL != null) return;

        EntityModelSet mcModels = Minecraft.getInstance().getEntityModels();

        HOGLIN_HIDE_HEAD_MODEL = new HoglinHideHeadModel<>(mcModels.bakeLayer(HoglinHideHeadModel.LAYER_LOCATION));
        HOGLIN_HIDE_CLOAK_MODEL = new HoglinHideCloakModel<>(mcModels.bakeLayer(HoglinHideCloakModel.LAYER_LOCATION));
        HOGLIN_HIDE_HOOVES_MODEL = new HoglinHideHoovesModel<>(mcModels.bakeLayer(HoglinHideHoovesModel.LAYER_LOCATION));
    }

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
    {
        event.registerLayerDefinition(HoveringInfernoModel.LAYER_LOCATION, HoveringInfernoModel::createBodyLayer);

        event.registerLayerDefinition(HoglinHideHeadModel.LAYER_LOCATION, HoglinHideHeadModel::createArmorLayer);
        event.registerLayerDefinition(HoglinHideCloakModel.LAYER_LOCATION, HoglinHideCloakModel::createArmorLayer);
        event.registerLayerDefinition(HoglinHideHoovesModel.LAYER_LOCATION, HoglinHideHoovesModel::createArmorLayer);
    }

    @SubscribeEvent
    public static void registerArmorModels(RegisterClientExtensionsEvent event)
    {
        event.registerItem(HoglinArmorItem.CLIENT_ITEM_EXTENSIONS, ItemInit.HOGLIN_HIDE_CAP, ItemInit.HOGLIN_HIDE_TUNIC, ItemInit.HOGLIN_HIDE_HOOVES);
    }
}
