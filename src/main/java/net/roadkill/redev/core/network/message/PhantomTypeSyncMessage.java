package net.roadkill.redev.core.network.message;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;
import net.roadkill.redev.core.entity.PhantomType;
import net.roadkill.redev.core.entity.SpecialPhantom;

import java.util.function.Supplier;

public class PhantomTypeSyncMessage
{
    int entityId;
    PhantomType phantomType;

    public PhantomTypeSyncMessage(LivingEntity entity, PhantomType phantomType)
    {   this.entityId = entity.getId();
        this.phantomType = phantomType;
    }

    PhantomTypeSyncMessage(int entityId, PhantomType phantomType)
    {   this.entityId = entityId;
        this.phantomType = phantomType;
    }

    public static void encode(PhantomTypeSyncMessage message, FriendlyByteBuf buffer)
    {   buffer.writeInt(message.entityId);
        buffer.writeInt(message.phantomType.ordinal());
    }

    public static PhantomTypeSyncMessage decode(FriendlyByteBuf buffer)
    {   return new PhantomTypeSyncMessage(buffer.readInt(), PhantomType.values()[buffer.readInt()]);
    }

    public static void handle(PhantomTypeSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient())
        {
            context.enqueueWork(() ->
            {
                LivingEntity entity = (LivingEntity) Minecraft.getInstance().level.getEntity(message.entityId);

                if (entity instanceof SpecialPhantom phantom)
                {   phantom.setPhantomType(message.phantomType);
                }
            });
        }

        context.setPacketHandled(true);
    }
}
