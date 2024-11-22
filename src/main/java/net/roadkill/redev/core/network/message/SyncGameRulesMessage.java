package net.roadkill.redev.core.network.message;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.common.util.LogicalSidedProvider;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.util.ClientOnlyHelper;
import net.roadkill.redev.util.registries.ModGameRules;

import java.util.HashMap;
import java.util.Map;

public class SyncGameRulesMessage implements CustomPacketPayload
{
    public static final Type<SyncGameRulesMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "sync_game_rules"));
    public static final StreamCodec<FriendlyByteBuf, SyncGameRulesMessage> CODEC = CustomPacketPayload.codec(SyncGameRulesMessage::encode, SyncGameRulesMessage::decode);

    public static final HashMap<String, Object> GAME_RULES = new HashMap<>();

    public static boolean getBoolean(GameRules.Key<GameRules.BooleanValue> key)
    {   return (Boolean) GAME_RULES.getOrDefault(key.getId(), false);
    }

    public static int getInt(GameRules.Key<GameRules.IntegerValue> key)
    {   return (Integer) GAME_RULES.getOrDefault(key.getId(), 0);
    }

    Map<String, Object> gameRules = new HashMap<>();

    public SyncGameRulesMessage()
    {}

    public SyncGameRulesMessage(Map<String, Object> gameRules)
    {   this.gameRules = gameRules;
    }
    
    public static void encode(SyncGameRulesMessage message, FriendlyByteBuf buffer)
    {
        GameRules gameRules = ((MinecraftServer) LogicalSidedProvider.WORKQUEUE.get(LogicalSide.SERVER)).getGameRules();
        final int[] size = {0};
        gameRules.visitGameRuleTypes(new GameRules.GameRuleTypeVisitor()
        {
            @Override
            public <T extends GameRules.Value<T>> void visit(GameRules.Key<T> pKey, GameRules.Type<T> pType)
            {   size[0]++;
            }
        });
        buffer.writeInt(size[0]);
        gameRules.visitGameRuleTypes(new GameRules.GameRuleTypeVisitor()
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
        final int[] size = {buffer.readInt()};
        for (int i = 0; i < size[0]; i++)
        {
            String key = buffer.readUtf();
            byte type = buffer.readByte();
            Object value = type == 0 ? buffer.readBoolean() : buffer.readInt();
            gameRules.put(key, value);
        }
        return new SyncGameRulesMessage(gameRules);
    }

    public static void handle(SyncGameRulesMessage message, IPayloadContext context)
    {
        if (context.flow().getReceptionSide().isClient())
        {
            context.enqueueWork(() ->
            {   GAME_RULES.putAll(message.gameRules);
            });
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type()
    {   return TYPE;
    }
}
