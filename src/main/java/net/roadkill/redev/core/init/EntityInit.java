package net.roadkill.redev.core.init;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressStructure;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.common.entity.DurianThornEntity;
import net.roadkill.redev.common.entity.HoveringInfernoEntity;
import net.roadkill.redev.common.entity.LithicanEntity;
import net.roadkill.redev.common.entity.RevenantEntity;
import net.roadkill.redev.util.registries.ModEntityTypes;

import java.lang.reflect.Field;
import java.util.List;

public class EntityInit
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ReDev.MOD_ID);

    public static final RegistryObject<EntityType<LithicanEntity>> LITHICAN = ENTITY_TYPES.register("lithican", () ->
            EntityType.Builder.of(LithicanEntity::new, MobCategory.MONSTER).sized(0.6f, 1.95f).build(new ResourceLocation(ReDev.MOD_ID, "lithican").toString()));

    public static final RegistryObject<EntityType<RevenantEntity>> REVENANT = ENTITY_TYPES.register("revenant", () ->
            EntityType.Builder.of(RevenantEntity::new, MobCategory.MONSTER).sized(0.6f, 1.95f).build(new ResourceLocation(ReDev.MOD_ID, "revenant").toString()));

    public static final RegistryObject<EntityType<DurianThornEntity>> DURIAN_THORN = ENTITY_TYPES.register("durian_thorn", () ->
            EntityType.Builder.of(DurianThornEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(ReDev.MOD_ID, "durian_thorn").toString()));

    public static final RegistryObject<EntityType<HoveringInfernoEntity>> HOVERING_INFERNO = ENTITY_TYPES.register("hovering_inferno", () ->
            EntityType.Builder.of(HoveringInfernoEntity::new, MobCategory.MONSTER).sized(1.5f, 2.5f).build(new ResourceLocation(ReDev.MOD_ID, "hovering_inferno").toString()));
}
