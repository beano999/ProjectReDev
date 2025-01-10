package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import javax.annotation.Nullable;

public class HangingLadderBlock extends LadderBlock
{
    public HangingLadderBlock(Properties properties)
    {   super(properties);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos)
    {   return super.canSurvive(pState, pLevel, pPos) || pLevel.getBlockState(pPos.above()).getBlock() == this;
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos,
                                  Direction neighborDir, BlockPos neighborPos, BlockState neighborState, RandomSource random)
    {
        if (neighborDir == Direction.UP && !neighborState.is(this))
        {   return Blocks.AIR.defaultBlockState();
        }
        else if (state.getValue(WATERLOGGED))
        {   tickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, level, tickAccess, pos, neighborDir, neighborPos, neighborState, random);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        if (!context.replacingClickedOnBlock())
        {
            BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(context.getClickedFace().getOpposite()));
            if (blockstate.is(this) && blockstate.getValue(FACING) == context.getClickedFace())
            {   return null;
            }
        }

        BlockState blockstate1 = this.defaultBlockState();
        LevelReader levelreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

        for(Direction direction : context.getNearestLookingDirections())
        {
            if (direction.getAxis().isHorizontal())
            {
                BlockState aboveState = context.getLevel().getBlockState(blockpos.above());
                if (aboveState.is(this))
                {   direction = aboveState.getValue(LadderBlock.FACING).getOpposite();
                }
                blockstate1 = blockstate1.setValue(FACING, direction.getOpposite());
                if (blockstate1.canSurvive(levelreader, blockpos))
                {
                    return blockstate1.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
                }
            }
        }

        return null;
    }
}
