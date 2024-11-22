package net.roadkill.redev.common.event;

import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.roadkill.redev.core.network.message.SyncGameRulesMessage;

@EventBusSubscriber
public class WolfRegen
{
    @SubscribeEvent
    public static void onWolfUpdateTick(EntityTickEvent.Pre event)
    {
        if (event.getEntity() instanceof Wolf wolf)
        {
            if (wolf.getHealth() < wolf.getMaxHealth() && wolf.tickCount % 20 == 0
            && SyncGameRulesMessage.getBoolean(GameRules.RULE_NATURAL_REGENERATION))
            {
                wolf.heal(1.0f);
            }
        }
    }
}
