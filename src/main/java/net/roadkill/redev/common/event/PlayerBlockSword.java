package net.roadkill.redev.common.event;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.roadkill.redev.mixin_interfaces.IOldCombat;

@EventBusSubscriber
public class PlayerBlockSword
{
    @SubscribeEvent
    public static void onBlockingDamage(LivingIncomingDamageEvent event)
    {
        if (event.getEntity() instanceof Player player
        && ((IOldCombat) player).isSwordBlocking())
        {
            DamageSource source = event.getSource();
            if (!source.is(DamageTypeTags.BYPASSES_ARMOR))
            {   event.setAmount(event.getAmount() * 0.5F);
            }
        }
    }
}