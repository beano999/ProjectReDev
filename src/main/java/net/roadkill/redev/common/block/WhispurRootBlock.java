package net.roadkill.redev.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.roadkill.redev.core.init.BlockInit;

import java.util.Arrays;
import java.util.List;

public class WhispurRootBlock extends PipeBlock
{
    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 1, 17);
    public static final BooleanProperty GROWING = BooleanProperty.create("growing");

    public static final MapCodec<WhispurRootBlock> CODEC = simpleCodec(WhispurRootBlock::new);

    public WhispurRootBlock(BlockBehaviour.Properties properties)
    {   super(0.3125F, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(UP, false).setValue(DOWN, false).setValue(DISTANCE, 1).setValue(GROWING, false));
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
                .setValue(DISTANCE, distance)
                .setValue(GROWING, true);
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

    @Override
    public boolean isRandomlyTicking(BlockState state)
    {   return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        int distance = state.getValue(DISTANCE);
        if (state.getValue(GROWING) && distance < 16)
        {
            int rootDir = rand.nextInt(2);
            Direction direction;
            if (rootDir > 0)
            {   direction = Direction.UP;
            }
            else direction = switch (rand.nextIntBetweenInclusive(0, 3))
            {   case 0 -> Direction.EAST;
                case 1 -> Direction.NORTH;
                case 2 -> Direction.WEST;
                case 3 -> Direction.SOUTH;
                default -> throw new IllegalStateException("Unexpected value: " + rand.nextInt(3));
            };

            BlockPos newPos = pos.relative(direction);
            if (level.getBlockState(newPos).isAir() && Direction.stream().noneMatch(dir -> dir != direction.getOpposite() && level.getBlockState(newPos.relative(dir)).is(this)))
            {
                if (rand.nextInt(2) == 0)
                {   level.setBlock(pos, state.setValue(GROWING, false), 3);
                }
                BlockState newState = this.getStateForPlacement(level, newPos).setValue(DISTANCE, distance + 1);
                if (rand.nextInt(distance) > 5)
                {   newState = newState.setValue(GROWING, false);
                }
                level.setBlock(newPos, newState, 3);
            }
        }
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

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos,
                                     Direction neighborDir, BlockPos neighborPos, BlockState neighborState, RandomSource random)
    {
        if (!state.canSurvive(level, pos))
        {   tickAccess.scheduleTick(pos, this, 1);
            return super.updateShape(state, level, tickAccess, pos, neighborDir, neighborPos, neighborState, random);
        }
        else
        {   return state.setValue(PROPERTY_BY_DIRECTION.get(neighborDir), isSupportingBlock(neighborState));
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
    {   builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, DISTANCE, GROWING);
    }

    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathType)
    {   return false;
    }

    @Override
    protected MapCodec<? extends PipeBlock> codec()
    {   return CODEC;
    }
}
