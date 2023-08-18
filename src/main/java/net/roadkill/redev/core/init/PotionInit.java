package net.roadkill.redev.core.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roadkill.redev.ReDev;

public final class PotionInit
{
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, ReDev.MOD_ID);

    public static final RegistryObject<Potion> SIGHT = POTIONS.register("sight", () ->
            new Potion(new MobEffectInstance(EffectInit.SIGHT.get(), 3600)));
    public static final RegistryObject<Potion> LONG_SIGHT = POTIONS.register("sight_long", () ->
            new Potion("sight", new MobEffectInstance(EffectInit.SIGHT.get(), 9600)));

    public static final RegistryObject<Potion> RESISTANCE = POTIONS.register("resistance", () ->
            new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3600)));
    public static final RegistryObject<Potion> RESISTANCE_STRONG = POTIONS.register("resistance_strong", () ->
            new Potion("resistance", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1800, 1)));
    public static final RegistryObject<Potion> RESISTANCE_BRIEF = POTIONS.register("resistance_brief", () ->
            new Potion("resistance", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 9)));

    public static final RegistryObject<Potion> HASTE = POTIONS.register("haste", () ->
            new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, 36000)));
    public static final RegistryObject<Potion> HASTE_STRONG = POTIONS.register("haste_strong", () ->
            new Potion("haste", new MobEffectInstance(MobEffects.DIG_SPEED, 18000, 1)));

    public static final RegistryObject<Potion> BLINDNESS = POTIONS.register("blindness", () ->
            new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 400)));
    public static final RegistryObject<Potion> BLINDNESS_LONG = POTIONS.register("blindness_long", () ->
            new Potion("blindness", new MobEffectInstance(MobEffects.BLINDNESS, 800)));

    public static final RegistryObject<Potion> GLOWING = POTIONS.register("glowing", () ->
            new Potion(new MobEffectInstance(MobEffects.GLOWING, 600)));
    public static final RegistryObject<Potion> GLOWING_LONG = POTIONS.register("glowing_long", () ->
            new Potion("glowing", new MobEffectInstance(MobEffects.GLOWING, 2400)));

    public static final RegistryObject<Potion> LEVITATION = POTIONS.register("levitation", () ->
            new Potion(new MobEffectInstance(MobEffects.LEVITATION, 200)));
    public static final RegistryObject<Potion> LEVITATION_STRONG = POTIONS.register("levitation_strong", () ->
            new Potion("levitation", new MobEffectInstance(MobEffects.LEVITATION, 100, 9)));

    public static final RegistryObject<Potion> MINING_FATIGUE = POTIONS.register("mining_fatigue", () ->
            new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 1200)));
    public static final RegistryObject<Potion> MINING_FATIGUE_STRONG = POTIONS.register("mining_fatigue_strong", () ->
            new Potion("mining_fatigue", new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 600, 1)));
    public static final RegistryObject<Potion> MINING_FATIGUE_LONG = POTIONS.register("mining_fatigue_long", () ->
            new Potion("mining_fatigue", new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 3600)));

    public static final RegistryObject<Potion> NAUSEA = POTIONS.register("nausea", () ->
            new Potion(new MobEffectInstance(MobEffects.CONFUSION, 400)));
    public static final RegistryObject<Potion> NAUSEA_LONG = POTIONS.register("nausea_long", () ->
            new Potion("nausea", new MobEffectInstance(MobEffects.CONFUSION, 800)));

    public static final RegistryObject<Potion> WITHER = POTIONS.register("wither", () ->
            new Potion(new MobEffectInstance(MobEffects.WITHER, 400)));
    public static final RegistryObject<Potion> WITHER_LONG = POTIONS.register("wither_long", () ->
            new Potion("wither", new MobEffectInstance(MobEffects.WITHER, 800)));
    public static final RegistryObject<Potion> WITHER_STRONG = POTIONS.register("wither_strong", () ->
            new Potion("wither", new MobEffectInstance(MobEffects.WITHER, 400, 1)));

    public static final RegistryObject<Potion> DOLPHIN_GRACE = POTIONS.register("dolphin_grace", () ->
            new Potion(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 3600)));
}
