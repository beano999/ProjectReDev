package net.roadkill.redev.core.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.roadkill.redev.core.network.message.ParticleBatchMessage;
import net.roadkill.redev.core.network.message.PhantomTypeSyncMessage;
import net.roadkill.redev.core.network.message.SyncGameRulesMessage;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModPacketHandlers
{
    public static final String NETWORK_VERSION = "1";

    @SubscribeEvent
    public static void registerPackets(RegisterPayloadHandlersEvent event)
    {
        final PayloadRegistrar registrar = event.registrar(NETWORK_VERSION);

        registrar.playToClient(ParticleBatchMessage.TYPE, ParticleBatchMessage.CODEC, ParticleBatchMessage::handle);
        registrar.playToClient(PhantomTypeSyncMessage.TYPE, PhantomTypeSyncMessage.CODEC, PhantomTypeSyncMessage::handle);
        registrar.playToClient(SyncGameRulesMessage.TYPE, SyncGameRulesMessage.CODEC, SyncGameRulesMessage::handle);
    }
}
