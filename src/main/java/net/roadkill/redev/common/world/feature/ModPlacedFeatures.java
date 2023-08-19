package net.roadkill.redev.common.world.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.roadkill.redev.ReDev;

public class ModPlacedFeatures
{
    public static final ResourceKey<ConfiguredFeature<?,?>> FOOLS_GOLD_ORE = registerKey("fools_gold_ore");

    public static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name)
    {   return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ReDev.MOD_ID, name));
    }
}
