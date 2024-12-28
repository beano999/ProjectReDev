package net.roadkill.redev.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.roadkill.redev.core.init.EffectInit;
import net.roadkill.redev.core.init.ParticleTypesInit;
import net.roadkill.redev.util.RDMath;
import net.roadkill.redev.util.registries.ModEffects;

@EventBusSubscriber(Dist.CLIENT)
public class VoidFog
{
    private static final int FOG_START_Y = -16;
    private static final int FOG_END_Y = -48;

    @SubscribeEvent
    public static void onFogDensity(ViewportEvent.RenderFog event)
    {
        Player player = Minecraft.getInstance().player;
        Level level = Minecraft.getInstance().level;
        if (level == null || player == null) return;
        if (player.hasEffect(MobEffects.NIGHT_VISION) || player.hasEffect(EffectInit.SIGHT)) return;

        double y = Minecraft.getInstance().player.getY();
        if (y < 0 && level.dimensionTypeRegistration().is(BuiltinDimensionTypes.OVERWORLD))
        {   event.setFarPlaneDistance((float) RDMath.blendExp(16, event.getFarPlaneDistance(), y, FOG_END_Y, FOG_START_Y));
            event.setNearPlaneDistance((float) RDMath.blendExp(8, event.getNearPlaneDistance(), y, FOG_END_Y, FOG_START_Y));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void voidFogColor(ViewportEvent.ComputeFogColor event)
    {
        Player player = Minecraft.getInstance().player;
        Level level = Minecraft.getInstance().level;
        if (level == null || player == null) return;
        if (player.hasEffect(MobEffects.NIGHT_VISION) || player.hasEffect(EffectInit.SIGHT)) return;

        double y = player.getY();
        if (y < 0 && level.dimensionTypeRegistration().is(BuiltinDimensionTypes.OVERWORLD))
        {   event.setRed((float) RDMath.blendExp(0, event.getRed(), y, -32, 0));
            event.setGreen((float) RDMath.blendExp(0, event.getGreen(), y, -32, 0));
            event.setBlue((float) RDMath.blendExp(0, event.getBlue(), y, -32, 0));
        }
    }

    @SubscribeEvent
    public static void renderVoidParticles(ClientTickEvent.Post event)
    {
        Player player = Minecraft.getInstance().player;
        Level level = Minecraft.getInstance().level;
        if (level == null || player == null) return;
        if (player.hasEffect(MobEffects.NIGHT_VISION) || player.hasEffect(EffectInit.SIGHT)) return;

        int minHeight = level.getMinY();
        double y = player.getY();
        if (y < 0 && level.dimensionTypeRegistration().is(BuiltinDimensionTypes.OVERWORLD) && !Minecraft.getInstance().isPaused())
        {
            for (int i = 0; i < RDMath.blend(5, 0, y, minHeight + 10, 0); i++)
            {
                level.addParticle(ParticleTypesInit.VOID_DUST.value(),
                                  player.getX() + level.random.nextIntBetweenInclusive(-16, 16),
                                  y + level.random.nextIntBetweenInclusive(-10, 10),
                                  player.getZ() + level.random.nextIntBetweenInclusive(-16, 16), 0, 0, 0);
            }
        }
    }
}
