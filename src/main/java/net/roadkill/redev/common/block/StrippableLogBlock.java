package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
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
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if (player.getItemInHand(hand).getItem() instanceof AxeItem)
        {
            if (!level.isClientSide)
            {   strip(level, pos, state);
                player.getItemInHand(hand).hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(hand));
            }
            return InteractionResult.SUCCESS;
        }
        else return InteractionResult.PASS;
    }
}
