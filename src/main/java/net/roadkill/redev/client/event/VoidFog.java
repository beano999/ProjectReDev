package net.roadkill.redev.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.roadkill.redev.core.init.EffectInit;
import net.roadkill.redev.core.init.ParticleTypesInit;
import net.roadkill.redev.core.network.message.SyncGameRulesMessage;
import net.roadkill.redev.util.RDMath;
import net.roadkill.redev.util.registries.ModEffects;
import net.roadkill.redev.util.registries.ModGameRules;

@EventBusSubscriber(Dist.CLIENT)
public class VoidFog
{
    private static final int FOG_START_Y = 64;
    private static final int FOG_END_Y = 112;

    @SubscribeEvent
    public static void onFogDensity(ViewportEvent.RenderFog event)
    {
        Player player = Minecraft.getInstance().player;
        Level level = Minecraft.getInstance().level;
        if (level == null || player == null) return;
        if (player.hasEffect(MobEffects.NIGHT_VISION) || player.hasEffect(EffectInit.SIGHT)
        || !SyncGameRulesMessage.getBoolean(ModGameRules.DO_VOID_FOG))
        {   return;
        }

        double y = RDMath.getAverageDepth(player);
        if (level.dimensionTypeRegistration().is(BuiltinDimensionTypes.OVERWORLD))
        {   event.setFarPlaneDistance((float) RDMath.blendLog(event.getFarPlaneDistance(), 16, y, FOG_START_Y, FOG_END_Y, 200));
            event.setNearPlaneDistance((float) RDMath.blendLog(event.getNearPlaneDistance(), 8, y, FOG_START_Y, FOG_END_Y, 200));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void voidFogColor(ViewportEvent.ComputeFogColor event)
    {
        Player player = Minecraft.getInstance().player;
        Level level = Minecraft.getInstance().level;
        if (level == null || player == null) return;
        if (player.hasEffect(MobEffects.NIGHT_VISION) || player.hasEffect(EffectInit.SIGHT)
        || !SyncGameRulesMessage.getBoolean(ModGameRules.DO_VOID_FOG))
        {   return;
        }

        double y = RDMath.getAverageDepth(player);
        if (level.dimensionTypeRegistration().is(BuiltinDimensionTypes.OVERWORLD))
        {   event.setRed((float) RDMath.blendLog(event.getRed(), 0, y, FOG_START_Y, FOG_END_Y - 10, 200));
            event.setGreen((float) RDMath.blendLog(event.getGreen(), 0, y, FOG_START_Y, FOG_END_Y - 10, 200));
            event.setBlue((float) RDMath.blendLog(event.getBlue(), 0, y, FOG_START_Y, FOG_END_Y - 10, 200));
        }
    }

    @SubscribeEvent
    public static void renderVoidParticles(ClientTickEvent.Post event)
    {
        Player player = Minecraft.getInstance().player;
        Level level = Minecraft.getInstance().level;
        if (level == null || player == null) return;
        if (player.hasEffect(MobEffects.NIGHT_VISION) || player.hasEffect(EffectInit.SIGHT)
        || !SyncGameRulesMessage.getBoolean(ModGameRules.DO_VOID_FOG))
        {   return;
        }

        double y = RDMath.getAverageDepth(player);
        if (level.dimensionTypeRegistration().is(BuiltinDimensionTypes.OVERWORLD) && !Minecraft.getInstance().isPaused())
        {
            int particles = (int) RDMath.blend(0, 5, y, FOG_END_Y - 10, FOG_START_Y);
            for (int i = 0; i < particles; i++)
            {
                level.addParticle(ParticleTypesInit.VOID_DUST.value(),
                                  player.getX() + level.random.nextIntBetweenInclusive(-16, 16),
                                  player.getY() + level.random.nextIntBetweenInclusive(-10, 10),
                                  player.getZ() + level.random.nextIntBetweenInclusive(-16, 16), 0, 0, 0);
            }
        }
    }
}
