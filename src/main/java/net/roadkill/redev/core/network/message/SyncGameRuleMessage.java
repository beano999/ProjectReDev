package net.roadkill.redev.core.network.message;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.roadkill.redev.util.RDMath;

import java.util.function.Supplier;

public class SyncGameRuleMessage
{
    String id;
    Object value;
    
    public SyncGameRuleMessage(String id, Object value)
    {   this.id = id;
        this.value = value;
    }
    
    public static void encode(SyncGameRuleMessage message, FriendlyByteBuf buffer)
    {   buffer.writeUtf(message.id);
        if (message.value instanceof Boolean)
        {   buffer.writeByte(0);
            buffer.writeByte((Boolean) message.value ? 1 : 0);
        }
        else
        {   buffer.writeByte(1);
            buffer.writeInt((Integer) message.value);
        }
    }

    public static SyncGameRuleMessage decode(FriendlyByteBuf buffer)
    {
        String id = buffer.readUtf();
        byte type = buffer.readByte();
        Object value = type == 0 ? buffer.readByte() == 1 : buffer.readInt();
        return new SyncGameRuleMessage(id, value);
    }

    public static void handle(SyncGameRuleMessage message, Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient())
        {
            context.enqueueWork(() ->
            {
                Level level = RDMath.getClientLevel();
                GameRules gameRules = level.getGameRules();
                GameRules.visitGameRuleTypes(new GameRules.GameRuleTypeVisitor()
                {
                    @Override
                    public <T extends GameRules.Value<T>> void visit(GameRules.Key<T> key, GameRules.Type<T> type)
                    {
                        if (key.getId().equals(message.id))
                        {
                            gameRules.getRule(key).setFrom(message.value instanceof Boolean
                                                           ? (T) new GameRules.BooleanValue((GameRules.Type<GameRules.BooleanValue>) type, (Boolean) message.value)
                                                           : (T) new GameRules.IntegerValue((GameRules.Type<GameRules.IntegerValue>) type, (Integer) message.value), null);
                        }
                    }
                });
            });
        }

        context.setPacketHandled(true);
    }
}
