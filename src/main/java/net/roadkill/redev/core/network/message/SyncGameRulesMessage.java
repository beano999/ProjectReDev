package net.roadkill.redev.core.network.message;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.roadkill.redev.util.ClientOnlyHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SyncGameRulesMessage
{
    Map<String, Object> gameRules = new HashMap<>();

    public SyncGameRulesMessage()
    {}

    public SyncGameRulesMessage(Map<String, Object> gameRules)
    {   this.gameRules = gameRules;
    }
    
    public static void encode(SyncGameRulesMessage message, FriendlyByteBuf buffer)
    {
        GameRules gameRules = ((MinecraftServer) LogicalSidedProvider.WORKQUEUE.get(LogicalSide.SERVER)).getGameRules();
        GameRules.visitGameRuleTypes(new GameRules.GameRuleTypeVisitor()
        {
            @Override
            public void visitBoolean(GameRules.Key<GameRules.BooleanValue> key, GameRules.Type<GameRules.BooleanValue> type)
            {   buffer.writeUtf(key.getId());
                buffer.writeByte(0);
                buffer.writeBoolean(gameRules.getBoolean(key));
            }

            @Override
            public void visitInteger(GameRules.Key<GameRules.IntegerValue> key, GameRules.Type<GameRules.IntegerValue> type)
            {   buffer.writeUtf(key.getId());
                buffer.writeByte(1);
                buffer.writeInt(gameRules.getInt(key));
            }
        });
    }

    public static SyncGameRulesMessage decode(FriendlyByteBuf buffer)
    {
        Map<String, Object> gameRules = new HashMap<>();
        final int[] size = {0};
        GameRules.visitGameRuleTypes(new GameRules.GameRuleTypeVisitor()
        {
            @Override
            public <T extends GameRules.Value<T>> void visit(GameRules.Key<T> pKey, GameRules.Type<T> pType)
            {   size[0]++;
            }
        });
        for (int i = 0; i < size[0]; i++)
        {   String key = buffer.readUtf();
            byte type = buffer.readByte();
            Object value = type == 0 ? buffer.readBoolean() : buffer.readInt();
            gameRules.put(key, value);
        }
        return new SyncGameRulesMessage(gameRules);
    }

    public static void handle(SyncGameRulesMessage message, Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient())
        {
            context.enqueueWork(() ->
            {
                Level level = ClientOnlyHelper.getClientLevel();
                GameRules gameRules = level.getGameRules();
                GameRules.visitGameRuleTypes(new GameRules.GameRuleTypeVisitor()
                {
                    @Override
                    public void visitBoolean(GameRules.Key<GameRules.BooleanValue> key, GameRules.Type<GameRules.BooleanValue> type)
                    {   gameRules.getRule(key).set((Boolean) message.gameRules.get(key.getId()), null);
                    }

                    @Override
                    public void visitInteger(GameRules.Key<GameRules.IntegerValue> key, GameRules.Type<GameRules.IntegerValue> type)
                    {   gameRules.getRule(key).set((Integer) message.gameRules.get(key.getId()), null);
                    }
                });
            });
        }

        context.setPacketHandled(true);
    }
}
