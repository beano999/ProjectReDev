package net.roadkill.redev.common.event;

import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.data.ModTags;

@Mod.EventBusSubscriber
public class SpectralArrowDamage
{
    @SubscribeEvent
    public static void onArrowHit(LivingHurtEvent event)
    {
        if (event.getSource().isIndirect() && event.getSource().getDirectEntity() instanceof SpectralArrow)
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
