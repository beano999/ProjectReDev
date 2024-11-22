package net.roadkill.redev.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import net.neoforged.neoforge.network.PacketDistributor;
import net.roadkill.redev.core.network.message.SyncGameRulesMessage;
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
        {   PacketDistributor.sendToAllPlayers(new SyncGameRulesMessage());
        }
    }
}