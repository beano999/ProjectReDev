package net.roadkill.redev.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.roadkill.redev.common.event.SwordBlockAnimation;
import net.roadkill.redev.mixin_interfaces.OldCombatPlayer;
import net.roadkill.redev.util.registries.ModGameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class MixinPlayerAttackSpeed implements OldCombatPlayer
{
    Player self = (Player) (Object) this;

    @Unique
    public boolean isSwordBlocking = false;

    @Inject(method = "getCurrentItemAttackStrengthDelay", at = @At("HEAD"), cancellable = true)
    public void getPlayerAttackSpeed(CallbackInfoReturnable<Float> cir)
    {
        if (self.level.getGameRules().getBoolean(ModGameRules.DO_OLD_COMBAT))
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
        public void use(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir)
        {
            if (!level.getGameRules().getBoolean(ModGameRules.DO_OLD_COMBAT)) return;
            ItemStack offHandItem = player.getItemInHand(InteractionHand.OFF_HAND);
            if (hand.equals(InteractionHand.MAIN_HAND) && offHandItem.getUseAnimation() != UseAnim.NONE) return;

            ItemStack stack = player.getItemInHand(hand);
            if (stack.getItem() instanceof SwordItem)
            {   ((OldCombatPlayer) player).setSwordBlocking(true);
                player.startUsingItem(hand);
                cir.setReturnValue(InteractionResultHolder.consume(stack));
            }
        }

        @Inject(method = "releaseUsing", at = @At("HEAD"))
        public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int chargeTime, CallbackInfo ci)
        {
            if (!level.getGameRules().getBoolean(ModGameRules.DO_OLD_COMBAT)) return;

            if (stack.getItem() instanceof SwordItem && entity instanceof Player player)
            {   ((OldCombatPlayer) player).setSwordBlocking(false);
                if (level.isClientSide)
                {   SwordBlockAnimation.IS_MAIN_BLOCKING = false;
                    SwordBlockAnimation.IS_OFF_BLOCKING = false;
                }
            }
        }

        @Inject(method = "getUseDuration", at = @At("HEAD"), cancellable = true)
        public void getUseDuration(ItemStack stack, CallbackInfoReturnable<Integer> cir)
        {
            if (stack.getItem() instanceof SwordItem)
            {   cir.setReturnValue(72000);
            }
        }
    }
}