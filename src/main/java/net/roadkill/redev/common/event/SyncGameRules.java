package net.roadkill.redev.common.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.roadkill.redev.core.network.ReDevPacketHandler;
import net.roadkill.redev.core.network.message.SyncGameRulesMessage;

@Mod.EventBusSubscriber
public class SyncGameRules
{
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {   ReDevPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SyncGameRulesMessage());
        }
    }
}
