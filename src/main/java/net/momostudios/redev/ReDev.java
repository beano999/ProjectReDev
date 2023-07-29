package net.momostudios.redev;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.momostudios.redev.core.init.*;

import net.momostudios.redev.core.network.ReDevPacketHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ReDev.MOD_ID)
public class ReDev
{
    public static final String MOD_ID = "redev";
    public static final boolean REMAP_MIXINS = false;
    private static final Logger LOGGER = LogManager.getFormatterLogger("ReDev");

    public ReDev()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::onClientSetup);
        bus.addListener(this::onCommonSetup);

        PotionInit.POTIONS.register(bus);
        EffectInit.EFFECTS.register(bus);
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        BlockEntityInit.BLOCK_ENTITY_TYPES.register(bus);
    }

    public void onCommonSetup(final FMLCommonSetupEvent event)
    {   // Setup packets
        ReDevPacketHandler.init();
    }

    public void onClientSetup(FMLClientSetupEvent event)
    {   BlockEntityRenderers.register(BlockEntityInit.CAMPFIRE_BLOCK_ENTITY_TYPE.get(), CampfireRenderer::new);
    }
}
