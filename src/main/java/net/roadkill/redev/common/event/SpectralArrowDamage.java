package net.roadkill.redev.common.event;

import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.roadkill.redev.data.ModTags;

@EventBusSubscriber
public class SpectralArrowDamage
{
    @SubscribeEvent
    public static void onArrowHit(LivingIncomingDamageEvent event)
    {
        if (!event.getSource().isDirect() && event.getSource().getDirectEntity() instanceof SpectralArrow)
        {
            if (event.getEntity().getType().is(ModTags.EntityTypes.GHOSTLY))
            {   event.setAmount(event.getAmount() * 2);
            }
            else if (event.getEntity() instanceof WanderingTrader)
            {   event.setAmount(event.getAmount() * 100f);
            }
        }
    }
}
