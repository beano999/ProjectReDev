package net.roadkill.redev.common.event;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.roadkill.redev.core.network.message.SyncGameRulesMessage;

@EventBusSubscriber
public class SyncGameRules
{
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {   PacketDistributor.sendToPlayer(player, new SyncGameRulesMessage());
        }
    }
}
