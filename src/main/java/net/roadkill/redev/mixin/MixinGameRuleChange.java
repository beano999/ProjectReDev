package net.roadkill.redev.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;
import net.roadkill.redev.core.network.ReDevPacketHandler;
import net.roadkill.redev.core.network.message.SyncGameRuleMessage;
import net.roadkill.redev.util.registries.ModGameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRules.Value.class)
public class MixinGameRuleChange
{
    @Inject(method = "onChanged", at = @At("HEAD"))
    public void onChanged(MinecraftServer server, CallbackInfo ci)
    {
        if (server != null)
        {   boolean doOldCombat = server.getLevel(Level.OVERWORLD).getGameRules().getRule(ModGameRules.DO_OLD_COMBAT).get();
            ReDevPacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new SyncGameRuleMessage("doOldCombat", doOldCombat));
        }
    }
}