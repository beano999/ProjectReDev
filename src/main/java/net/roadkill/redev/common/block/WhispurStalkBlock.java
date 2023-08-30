package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.roadkill.redev.core.init.BlockInit;

import java.util.Arrays;
import java.util.List;

public class WhispurStalkBlock extends PipeBlock
{
    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 1, 17);

    public WhispurStalkBlock(BlockBehaviour.Properties properties)
    {   super(0.3125F, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(UP, false).setValue(DOWN, false));
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos)
    {
        return 12;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context)
    {   return this.getStateForPlacement(context.getLevel(), context.getClickedPos());
    }

    public BlockState getStateForPlacement(BlockGetter level, BlockPos pos)
    {
        List<BlockState> neighbors = Arrays.stream(Direction.values()).map(pos::relative).map(level::getBlockState).toList();
        int distance = calculateDistance(level, pos);

        return this.defaultBlockState()
                .setValue(DOWN, isSupportingBlock(neighbors.get(0)))
                .setValue(UP, isSupportingBlock(neighbors.get(1)))
                .setValue(NORTH, isSupportingBlock(neighbors.get(2)))
                .setValue(SOUTH, isSupportingBlock(neighbors.get(3)))
                .setValue(WEST, isSupportingBlock(neighbors.get(4)))
                .setValue(EAST, isSupportingBlock(neighbors.get(5)))
                .setValue(DISTANCE, distance);
    }

    public static int calculateDistance(BlockGetter level, BlockPos pos)
    {
        List<BlockState> neighbors = Arrays.stream(Direction.values()).map(pos::relative).map(level::getBlockState).toList();
        int distance = 17;
        for (BlockState neighbor : neighbors)
        {
            if (isDirectSupport(neighbor))
            {   distance = 1;
                break;
            }
            if (neighbor.is(BlockInit.WHISPUR_ROOT.get()))
            {   distance = Math.min(distance, neighbor.getValue(DISTANCE) + 1);
            }
        }
        return distance;
    }

    public static boolean isSupported(BlockState state, BlockPos pos, BlockGetter level)
    {
        List<BlockState> neighbors = Arrays.stream(Direction.values()).map(pos::relative).map(level::getBlockState).toList();
        return neighbors.stream().anyMatch(neighbor ->
        {
            return isDirectSupport(neighbor)
                || neighbor.is(BlockInit.WHISPUR_ROOT.get()) && neighbor.getValue(DISTANCE) < state.getValue(DISTANCE);
        });
    }

    public static boolean isDirectSupport(BlockState state)
    {   return state.is(Blocks.BONE_BLOCK);
    }

    public static boolean isSupportingBlock(BlockState state)
    {   return isDirectSupport(state) || state.is(BlockInit.WHISPUR_ROOT.get());
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos)
    {
        if (!state.canSurvive(level, pos))
        {   level.scheduleTick(pos, this, 1);
            return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
        }
        else
        {   return state.setValue(PROPERTY_BY_DIRECTION.get(direction), isSupportingBlock(neighborState));
        }
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        if (!state.canSurvive(level, pos))
        {   level.destroyBlock(pos, true);
        }
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {   int distance = state.getValue(DISTANCE);
        return isSupported(state, pos, level) && distance < 17;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {   builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, DISTANCE);
    }

    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathType)
    {   return false;
    }
}
