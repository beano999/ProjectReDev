package net.roadkill.redev.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.core.init.ParticleTypesInit;
import net.roadkill.redev.util.RDMath;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class VoidFog
{
    @SubscribeEvent
    public static void onFogDensity(ViewportEvent.RenderFog event)
    {   double y = Minecraft.getInstance().player.getY();
        Level level = Minecraft.getInstance().level;
        if (y < 0 && level.dimensionTypeId() == BuiltinDimensionTypes.OVERWORLD)
        {   event.setFarPlaneDistance((float) RDMath.blendExp(32, event.getFarPlaneDistance(), y, -32, 0));
            event.setNearPlaneDistance((float) RDMath.blendExp(16, event.getNearPlaneDistance(), y, -32, 0));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void voidFogColor(ViewportEvent.ComputeFogColor event)
    {   Level level = Minecraft.getInstance().level;
        double y = Minecraft.getInstance().player.getY();
        if (y < 0 && level.dimensionTypeId() == BuiltinDimensionTypes.OVERWORLD)
        {   event.setRed((float) RDMath.blendExp(0, event.getRed(), y, -32, 0));
            event.setGreen((float) RDMath.blendExp(0, event.getGreen(), y, -32, 0));
            event.setBlue((float) RDMath.blendExp(0, event.getBlue(), y, -32, 0));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void renderVoidParticles(TickEvent.ClientTickEvent event)
    {
        Level level = Minecraft.getInstance().level;
        if (level == null) return;

        int minHeight = level.getMinBuildHeight();
        double y = Minecraft.getInstance().player.getY();
        if (y < 0 && level.dimensionTypeId() == BuiltinDimensionTypes.OVERWORLD && !Minecraft.getInstance().isPaused())
        {
            for (int i = 0; i < RDMath.blend(4, 0, y, minHeight + 10, 0); i++)
            {
                level.addParticle(ParticleTypesInit.VOID_DUST.get(),
                                  Minecraft.getInstance().player.getX() + level.random.nextIntBetweenInclusive(-16, 16),
                                  y + level.random.nextIntBetweenInclusive(-10, 10),
                                  Minecraft.getInstance().player.getZ() + level.random.nextIntBetweenInclusive(-16, 16), 0, 0, 0);
            }
        }
    }
}
