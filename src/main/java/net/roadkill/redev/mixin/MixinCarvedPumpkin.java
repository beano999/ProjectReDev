package net.roadkill.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.roadkill.redev.common.block.ModCarvedPumpkinBlock;
import net.roadkill.redev.core.init.BlockInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PumpkinBlock.class)
public class MixinCarvedPumpkin
{
    @Inject(method = "use", at = @At(value = "HEAD"), cancellable = true)
    private void use(BlockState pState, Level level, BlockPos pos, Player player, InteractionHand pHand, BlockHitResult pHit, CallbackInfoReturnable<InteractionResult> returnable)
    {
        if(player instanceof ServerPlayer)
        {
            ItemStack itemStack = player.getItemInHand(pHand);
            if(itemStack.getItem() instanceof ShearsItem)
            {
                Direction faceDir = pHit.getDirection();
                itemStack.hurt(0, level.random, (ServerPlayer) player);
                player.swing(pHand, true);
                level.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS);
                ItemEntity itementity = new ItemEntity(level,
                                                   pos.getX() + 0.5D + faceDir.getStepX() * 0.65D,
                                                   pos.getY() + 0.1D,
                                                   pos.getZ() + 0.5D + faceDir.getStepZ() * 0.65D,
                                                   new ItemStack(Items.PUMPKIN_SEEDS, 4));
                itementity.setDeltaMovement(0.05D * (double)faceDir.getStepX() + level.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double)faceDir.getStepZ() + level.random.nextDouble() * 0.02D);
                level.addFreshEntity(itementity);
                level.setBlock(pos, BlockInit.CARVED_PUMPKIN.get().defaultBlockState().setValue(ModCarvedPumpkinBlock.FACING, faceDir), 3);
                returnable.setReturnValue(InteractionResult.SUCCESS);
            }
        }
        returnable.setReturnValue(InteractionResult.FAIL);
    }
}
