package net.roadkill.redev;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.roadkill.redev.core.init.*;

import net.roadkill.redev.core.network.ReDevPacketHandler;
import net.roadkill.redev.util.registries.ModBlocks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ReDev.MOD_ID)
public class ReDev
{
    public static final String MOD_ID = "redev";
    public static final boolean REMAP_MIXINS = false;
    public static final Logger LOGGER = LogManager.getFormatterLogger("ReDev");

    public ReDev()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::onClientSetup);
        bus.addListener(this::onCommonSetup);

        PotionInit.POTIONS.register(bus);
        EffectInit.EFFECTS.register(bus);
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        SoundInit.SOUNDS.register(bus);
        BlockEntityInit.BLOCK_ENTITY_TYPES.register(bus);
        BiomeCodecInit.BIOME_MODIFIER_SERIALIZERS.register(bus);
        FeatureInit.FEATURES.register(bus);
        EntityInit.ENTITY_TYPES.register(bus);
    }

    public void onCommonSetup(final FMLCommonSetupEvent event)
    {   // Setup packets
        ReDevPacketHandler.init();
        // Register wood types
        WoodType.register(ModBlocks.WoodTypes.SCRAPWOOD);
        WoodType.register(ModBlocks.WoodTypes.WHISPUR);
        WoodType.register(ModBlocks.WoodTypes.PETRIFIED);
    }

    public void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() ->
        {   // Register wood types
            Sheets.addWoodType(ModBlocks.WoodTypes.SCRAPWOOD);
            Sheets.addWoodType(ModBlocks.WoodTypes.WHISPUR);
            Sheets.addWoodType(ModBlocks.WoodTypes.PETRIFIED);
            // Register block renderers
            BlockEntityRenderers.register(BlockEntityInit.SIGN_BLOCK_ENTITY_TYPE.get(), SignRenderer::new);
            BlockEntityRenderers.register(BlockEntityInit.CAMPFIRE_BLOCK_ENTITY_TYPE.get(), CampfireRenderer::new);
        });
    }
}
