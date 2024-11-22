package net.roadkill.redev.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.roadkill.redev.client.event.SwordBlockAnimation;
import net.roadkill.redev.core.network.message.SyncGameRulesMessage;
import net.roadkill.redev.mixin_interfaces.IOldCombat;
import net.roadkill.redev.util.registries.ModGameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class MixinPlayerAttackSpeed implements IOldCombat
{
    @Unique
    public boolean isSwordBlocking = false;

    @Inject(method = "getCurrentItemAttackStrengthDelay", at = @At("HEAD"), cancellable = true)
    public void getPlayerAttackSpeed(CallbackInfoReturnable<Float> cir)
    {
        if (SyncGameRulesMessage.getBoolean(ModGameRules.DO_OLD_COMBAT))
        {   cir.setReturnValue(0.0F);
        }
    }

    @Override
    public boolean isSwordBlocking()
    {   return isSwordBlocking;
    }

    @Override
    public void setSwordBlocking(boolean blocking)
    {   isSwordBlocking = blocking;
    }

    @Mixin(Item.class)
    public static class MixinSwordBlock
    {
        @Inject(method = "use", at = @At("HEAD"), cancellable = true)
        public void use(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir)
        {
            if (!SyncGameRulesMessage.getBoolean(ModGameRules.DO_OLD_COMBAT)) return;
            ItemStack offHandItem = player.getItemInHand(InteractionHand.OFF_HAND);
            if (hand.equals(InteractionHand.MAIN_HAND) && offHandItem.getUseAnimation() != ItemUseAnimation.NONE) return;

            ItemStack stack = player.getItemInHand(hand);
            if (stack.getItem() instanceof SwordItem)
            {
                ((IOldCombat) player).setSwordBlocking(true);
                player.startUsingItem(hand);
                cir.setReturnValue(InteractionResult.CONSUME);
            }
        }

        @Inject(method = "releaseUsing", at = @At("HEAD"))
        public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int useTime, CallbackInfoReturnable<Boolean> cir)
        {
            if (!SyncGameRulesMessage.getBoolean(ModGameRules.DO_OLD_COMBAT)) return;

            if (stack.getItem() instanceof SwordItem && entity instanceof Player player)
            {
                ((IOldCombat) player).setSwordBlocking(false);
                if (level.isClientSide)
                {   SwordBlockAnimation.IS_MAIN_BLOCKING = false;
                    SwordBlockAnimation.IS_OFF_BLOCKING = false;
                }
            }
        }

        @Inject(method = "getUseDuration", at = @At("HEAD"), cancellable = true)
        public void getUseDuration(ItemStack stack, LivingEntity entity, CallbackInfoReturnable<Integer> cir)
        {
            if (stack.getItem() instanceof SwordItem)
            {   cir.setReturnValue(72000);
            }
        }
    }
}