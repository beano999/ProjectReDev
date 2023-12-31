package net.roadkill.redev.common.event;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.mixin_interfaces.OldCombatPlayer;

@Mod.EventBusSubscriber
public class PlayerBlockSword
{
    @SubscribeEvent
    public static void onBlockingDamage(LivingHurtEvent event)
    {
        if (event.getEntity() instanceof Player player
        && ((OldCombatPlayer) player).isSwordBlocking())
        {
            DamageSource source = event.getSource();
            if (!source.is(DamageTypeTags.BYPASSES_ARMOR))
            {   event.setAmount(event.getAmount() * 0.5F);
            }
        }
    }
}