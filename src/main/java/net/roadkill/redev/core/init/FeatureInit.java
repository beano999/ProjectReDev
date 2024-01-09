package net.roadkill.redev.core.init;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.common.world.feature.PetrifiedTreeFeature;
import net.roadkill.redev.common.world.feature.ShadeTreeFeature;

public class FeatureInit
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, ReDev.MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_FEATURE = FEATURES.register("petrified_tree", () -> new PetrifiedTreeFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SHADE_TREE_FEATURE = FEATURES.register("shade_tree", () -> new ShadeTreeFeature(NoneFeatureConfiguration.CODEC));
}
