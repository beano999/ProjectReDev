package net.roadkill.redev.core.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.client.particle.VoidDustParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleTypesInit
{
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ReDev.MOD_ID);

    public static final RegistryObject<SimpleParticleType> VOID_DUST = PARTICLES.register("void_dust", () -> new SimpleParticleType(true));

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event)
    {   event.registerSpriteSet(VOID_DUST.get(), VoidDustParticle.Factory::new);
    }
}