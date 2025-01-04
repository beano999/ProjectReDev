package net.roadkill.redev;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.roadkill.redev.core.init.*;

import net.roadkill.redev.util.registries.ModBlocks;
import net.roadkill.redev.util.registries.ModGameRules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ReDev.MOD_ID)
public class ReDev
{
    public static final String MOD_ID = "redev";
    public static final Logger LOGGER = LogManager.getFormatterLogger("ReDev");

    public ReDev(IEventBus bus, ModContainer modContainer)
    {
        bus.addListener(this::onClientSetup);
        bus.addListener(this::onCommonSetup);
        ModGameRules.registerGameRules();

        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        EntityInit.ENTITY_TYPES.register(bus);
        BlockEntityInit.BLOCK_ENTITY_TYPES.register(bus);
        PotionInit.POTIONS.register(bus);
        EffectInit.EFFECTS.register(bus);
        ParticleTypesInit.PARTICLES.register(bus);
        SoundInit.SOUNDS.register(bus);
        BiomeCodecInit.BIOME_MODIFIER_SERIALIZERS.register(bus);
        FeatureInit.FEATURES.register(bus);
        CreativeTabInit.ITEM_GROUPS.register(bus);
        ModDataAttachments.ATTACHMENT_TYPES.register(bus);
    }

    public void onCommonSetup(final FMLCommonSetupEvent event)
    {
        // Register wood types
        WoodType.register(ModBlocks.WoodTypes.SCRAPWOOD);
        WoodType.register(ModBlocks.WoodTypes.WHISPUR);
        WoodType.register(ModBlocks.WoodTypes.PETRIFIED);
    }

    public void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            // Register wood types
            Sheets.addWoodType(ModBlocks.WoodTypes.SCRAPWOOD);
            Sheets.addWoodType(ModBlocks.WoodTypes.WHISPUR);
            Sheets.addWoodType(ModBlocks.WoodTypes.PETRIFIED);
        });
    }
}
