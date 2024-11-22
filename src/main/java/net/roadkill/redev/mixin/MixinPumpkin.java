package net.roadkill.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.roadkill.redev.common.block.ModCarvedPumpkinBlock;
import net.roadkill.redev.core.init.BlockInit;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(PumpkinBlock.class)
public class MixinPumpkin
{
    @Inject(method = "useItemOn", at = @At(value = "HEAD"), cancellable = true)
    private void customCarving(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player,
                               InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir)
    {
        if (player instanceof ServerPlayer && stack.getItem() instanceof ShearsItem)
        {
            Direction facing = hitResult.getDirection();

            stack.hurtAndBreak(0, (ServerLevel) level, player, (item) -> {});
            player.swing(hand, true);
            level.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS);

            ItemEntity itementity = new ItemEntity(level,
                                                   pos.getX() + 0.5D + facing.getStepX() * 0.65D,
                                                   pos.getY() + 0.1D,
                                                   pos.getZ() + 0.5D + facing.getStepZ() * 0.65D,
                                                   new ItemStack(Items.PUMPKIN_SEEDS, 4));
            itementity.setDeltaMovement(0.05D * (double)facing.getStepX() + level.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double)facing.getStepZ() + level.random.nextDouble() * 0.02D);
            level.addFreshEntity(itementity);

            level.setBlock(pos, BlockInit.CARVED_PUMPKIN.get().defaultBlockState().setValue(ModCarvedPumpkinBlock.FACING, facing), 3);

            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }

    @Mixin(CarvedPumpkinBlock.class)
    public static class MixinCarvedPumpkin
    {
        @Final
        @Shadow
        private static final Predicate<BlockState> PUMPKINS_PREDICATE = (state) -> state != null && (state.getBlock() instanceof CarvedPumpkinBlock || state.is(Blocks.JACK_O_LANTERN));
    }
}
