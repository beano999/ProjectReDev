package net.roadkill.redev.common.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent;
import net.roadkill.redev.util.registries.ModGameRules;

@EventBusSubscriber
public class RemoveIFrames
{
    @SubscribeEvent
    public static void reduceSpamDamage(LivingIncomingDamageEvent event)
    {
        boolean oldCombatEnabled = ((ServerLevel) event.getEntity().level()).getGameRules().getBoolean(ModGameRules.DO_OLD_COMBAT);
        if (oldCombatEnabled) return;

        LivingEntity entity = event.getEntity();
        DamageSource damageSource = event.getSource();

        if (!(entity instanceof Player)
        && damageSource.is(DamageTypes.MOB_ATTACK) || damageSource.is(DamageTypes.MOB_ATTACK_NO_AGGRO) || damageSource.is(DamageTypes.PLAYER_ATTACK))
        {   event.getContainer().setPostAttackInvulnerabilityTicks(0);
        }

        if (event.getSource().getDirectEntity() instanceof Player player)
        {
            float attackStrength = player.getAttackStrengthScale(0);
            event.setAmount(event.getAmount() * (float) Math.pow(attackStrength, 10));
        }
    }

    @SubscribeEvent
    public static void reduceSpamKnockback(LivingKnockBackEvent event)
    {
        boolean oldCombatEnabled = ((ServerLevel) event.getEntity().level()).getGameRules().getBoolean(ModGameRules.DO_OLD_COMBAT);
        if (oldCombatEnabled) return;

        Entity attacker = event.getEntity().getLastAttacker();

        if (attacker instanceof Player player)
        {
            float attackStrength = player.getAttackStrengthScale(0);
            event.setStrength(event.getStrength() * attackStrength);
        }
    }
}
