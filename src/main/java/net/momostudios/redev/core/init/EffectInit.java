package net.momostudios.redev.core.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.momostudios.redev.ReDev;
import net.momostudios.redev.common.effect.SightEffect;

public final class EffectInit
{
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ReDev.MOD_ID);

    public static final RegistryObject<MobEffect> SIGHT = EFFECTS.register("sight", SightEffect::new);
}
