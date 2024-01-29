package net.roadkill.redev.common.event;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.common.entity.HoveringInfernoEntity;
import net.roadkill.redev.util.registries.ModEntityTypes;

@Mod.EventBusSubscriber
public class HoveringInfernoSpawn
{
    @SubscribeEvent
    public static void replaceBlazeSpawn(MobSpawnEvent.FinalizeSpawn event)
    {
        if (event.getEntity().getType() == EntityType.BLAZE && Math.random() < 0.1
        && event.getSpawnType() == MobSpawnType.NATURAL)
        {
            event.setSpawnCancelled(true);

            HoveringInfernoEntity hoveringInferno = new HoveringInfernoEntity((EntityType<? extends Blaze>) ModEntityTypes.HOVERING_INFERNO, event.getEntity().level);
            hoveringInferno.setPos(event.getEntity().position());
            event.getLevel().addFreshEntity(hoveringInferno);
        }
    }
}
