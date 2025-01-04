package net.roadkill.redev.core.network.message;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Phantom;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.core.entity.PhantomType;

public class PhantomTypeSyncMessage implements CustomPacketPayload
{
    public static final Type<PhantomTypeSyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "phantom_type"));
    public static final StreamCodec<FriendlyByteBuf, PhantomTypeSyncMessage> CODEC = CustomPacketPayload.codec(PhantomTypeSyncMessage::encode, PhantomTypeSyncMessage::decode);

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

    public static void handle(PhantomTypeSyncMessage message, IPayloadContext context)
    {
        if (context.flow().getReceptionSide().isClient())
        {
            context.enqueueWork(() ->
            {
                LivingEntity entity = (LivingEntity) Minecraft.getInstance().level.getEntity(message.entityId);

                if (entity instanceof Phantom phantom)
                {   PhantomType.set(phantom, message.phantomType);
                }
            });
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type()
    {   return TYPE;
    }
}
