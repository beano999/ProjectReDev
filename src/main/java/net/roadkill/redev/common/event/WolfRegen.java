package net.roadkill.redev.common.event;

import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WolfRegen
{
    @SubscribeEvent
    public static void onWolfUpdateTick(LivingEvent.LivingTickEvent event)
    {
        if (event.getEntity() instanceof Wolf wolf)
        {
            if (wolf.getHealth() < wolf.getMaxHealth()
            && wolf.level.getGameRules().getRule(GameRules.RULE_NATURAL_REGENERATION).get()
            && wolf.tickCount % 20 == 0)
            {   wolf.heal(1.0f);
            }
        }
    }
}
