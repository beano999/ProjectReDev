package net.roadkill.redev.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.roadkill.redev.core.init.ParticleTypesInit;
import net.roadkill.redev.util.RDMath;

@EventBusSubscriber(Dist.CLIENT)
public class VoidFog
{
    @SubscribeEvent
    public static void onFogDensity(ViewportEvent.RenderFog event)
    {   double y = Minecraft.getInstance().player.getY();
        Level level = Minecraft.getInstance().level;
        if (y < 0 && level.dimensionTypeRegistration() == BuiltinDimensionTypes.OVERWORLD)
        {   event.setFarPlaneDistance((float) RDMath.blendExp(16, event.getFarPlaneDistance(), y, -32, 0));
            event.setNearPlaneDistance((float) RDMath.blendExp(8, event.getNearPlaneDistance(), y, -32, 0));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void voidFogColor(ViewportEvent.ComputeFogColor event)
    {
        Level level = Minecraft.getInstance().level;
        double y = Minecraft.getInstance().player.getY();
        if (y < 0 && level.dimensionTypeRegistration() == BuiltinDimensionTypes.OVERWORLD)
        {   event.setRed((float) RDMath.blendExp(0, event.getRed(), y, -32, 0));
            event.setGreen((float) RDMath.blendExp(0, event.getGreen(), y, -32, 0));
            event.setBlue((float) RDMath.blendExp(0, event.getBlue(), y, -32, 0));
        }
    }

    @SubscribeEvent
    public static void renderVoidParticles(ClientTickEvent.Post event)
    {
        Level level = Minecraft.getInstance().level;
        if (level == null) return;

        int minHeight = level.getMinY();
        double y = Minecraft.getInstance().player.getY();
        if (y < 0 && level.dimensionTypeRegistration() == BuiltinDimensionTypes.OVERWORLD && !Minecraft.getInstance().isPaused())
        {
            for (int i = 0; i < RDMath.blend(5, 0, y, minHeight + 10, 0); i++)
            {
                level.addParticle(ParticleTypesInit.VOID_DUST.value(),
                                  Minecraft.getInstance().player.getX() + level.random.nextIntBetweenInclusive(-16, 16),
                                  y + level.random.nextIntBetweenInclusive(-10, 10),
                                  Minecraft.getInstance().player.getZ() + level.random.nextIntBetweenInclusive(-16, 16), 0, 0, 0);
            }
        }
    }
}
