package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class StrippableLogBlock extends RotatedPillarBlock
{
    private final Block strippedBlock;

    public StrippableLogBlock(Properties pProperties, Block strippedBlock)
    {   super(pProperties);
        this.strippedBlock = strippedBlock;
    }

    public BlockState getStrippedState(BlockState oldState)
    {
        if (oldState.getBlock() instanceof RotatedPillarBlock)
        {   return strippedBlock.defaultBlockState().setValue(AXIS, oldState.getValue(AXIS));
        }
        else return strippedBlock.defaultBlockState();
    }

    public void strip(Level level, BlockPos pos, BlockState oldState)
    {   level.setBlockAndUpdate(pos, this.getStrippedState(oldState));
        level.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if (stack.getItem() instanceof AxeItem)
        {
            if (!level.isClientSide)
            {   strip(level, pos, state);
                stack.hurtAndBreak(1, (ServerLevel) level, player, (entity) -> {});
            }
            return InteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
