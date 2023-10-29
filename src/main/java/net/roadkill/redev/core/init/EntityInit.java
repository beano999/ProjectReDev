package net.roadkill.redev.core.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.common.entity.LithicanEntity;
import net.roadkill.redev.common.entity.RevenantEntity;

public class EntityInit
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ReDev.MOD_ID);

    public static final RegistryObject<EntityType<LithicanEntity>> LITHICAN = ENTITY_TYPES.register("lithican", () -> EntityType.Builder.of(LithicanEntity::new, MobCategory.MONSTER).sized(0.6f, 1.95f).build(new ResourceLocation(ReDev.MOD_ID, "lithican").toString()));
    public static final RegistryObject<EntityType<RevenantEntity>> REVENANT = ENTITY_TYPES.register("revenant", () -> EntityType.Builder.of(RevenantEntity::new, MobCategory.MONSTER).sized(0.6f, 1.95f).build(new ResourceLocation(ReDev.MOD_ID, "revenant").toString()));
}
