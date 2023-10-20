package net.roadkill.redev.core.network.message;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EntityPosMessage
{
    int entityId;
    double x, y, z;

    public EntityPosMessage(Entity entity, double x, double y, double z)
    {   this.entityId = entity.getId();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    EntityPosMessage(int entityId, double x, double y, double z)
    {   this.entityId = entityId;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void encode(EntityPosMessage message, FriendlyByteBuf buffer)
    {   buffer.writeInt(message.entityId);
        buffer.writeDouble(message.x);
        buffer.writeDouble(message.y);
        buffer.writeDouble(message.z);
    }

    public static EntityPosMessage decode(FriendlyByteBuf buffer)
    {   return new EntityPosMessage(buffer.readInt(), buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
    }

    public static void handle(EntityPosMessage message, Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient())
        {
            context.enqueueWork(() ->
            {
                Entity entity = Minecraft.getInstance().level.getEntity(message.entityId);
                if (entity != null)
                {   entity.setPos(message.x, message.y, message.z);
                }
            });
        }

        context.setPacketHandled(true);
    }
}
