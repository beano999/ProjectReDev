package net.momostudios.redev.core.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.momostudios.redev.ReDev;

public final class PotionInit
{
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, ReDev.MOD_ID);

    public static final RegistryObject<Potion> SIGHT = POTIONS.register("sight", () ->
            new Potion(new MobEffectInstance(EffectInit.SIGHT.get(), 3600)));
    public static final RegistryObject<Potion> LONG_SIGHT = POTIONS.register("sight_long", () ->
            new Potion("sight", new MobEffectInstance(EffectInit.SIGHT.get(), 9600)));
}
