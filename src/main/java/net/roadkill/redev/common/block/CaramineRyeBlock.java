package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.util.TriState;
import net.roadkill.redev.core.init.ItemInit;
import net.roadkill.redev.util.registries.ModItems;

import java.util.Arrays;

public class CaramineRyeBlock extends CropBlock
{
    public static final int MAX_AGE = 6;
    public static final int MAX_SINGLE_AGE = 2;

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);
    public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(6.0D, 0.0D, 6.0D, 10.0D, 6.0D,  10.0D),
            Block.box(5.0D, 0.0D, 5.0D, 11.0D, 12.0D,  11.0D),
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D),
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D),
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D),
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D),
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D)};

    public CaramineRyeBlock(Properties properties)
    {   super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(HALF, Half.BOTTOM));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        int age = state.getValue(this.getAgeProperty());
        return state.getValue(HALF) == Half.BOTTOM
             ? SHAPE_BY_AGE[age]
             : SHAPE_BY_AGE[Math.max(0, age - 3)];
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState)
    {   return false;
    }

    @Override
    protected int getBonemealAgeIncrease(Level level)
    {   return Mth.nextInt(level.random, 1, 2);
    }

    @Override
    public void growCrops(Level level, BlockPos pos, BlockState state)
    {   this.grow(level, pos, state, 1);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state)
    {   return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {   if (!level.isAreaLoaded(pos, 1)) return;
        if (!canSurvive(state, level, pos))
        {   level.destroyBlock(pos, true);
            return;
        }
        if (isMaxAge(state)) return;
        float growthSpeed = getGrowthSpeed(state, level, pos);

        if (CommonHooks.canCropGrow(level, pos, state, rand.nextInt((int) (25f / growthSpeed + 1)) == 0))
        {   this.grow(level, pos, state, 1);
            CommonHooks.fireCropGrowPost(level, pos, state);
        }
    }

    @Override
    public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant)
    {   return this.mayPlaceOn(state, level, soilPosition) ? TriState.TRUE : TriState.FALSE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos)
    {   return state.is(Blocks.SOUL_SOIL);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {
        BlockState groundState = level.getBlockState(pos.below());
        return this.mayPlaceOn(groundState, level, pos.below()) || groundState.is(this);
    }

    public void grow(Level level, BlockPos pos, BlockState state, int increase)
    {   // Only run this on the bottom half
        if (state.getValue(HALF) != Half.BOTTOM) return;
        // Don't grow naturally unless next to lava
        if (Arrays.stream(Direction.values()).noneMatch(dir -> dir.getAxis() != Direction.Axis.Y
        && level.getBlockState(pos.below().relative(dir)).is(Blocks.LAVA))) return;

        int newAge = Math.min(MAX_AGE, this.getAge(state) + increase);
        BlockState aboveState = level.getBlockState(pos.above());
        if (newAge > MAX_SINGLE_AGE)
        {
            if ((aboveState.isAir() || aboveState.is(this)))
                level.setBlock(pos.above(), this.defaultBlockState().setValue(AGE, newAge).setValue(HALF, Half.TOP), 2);
            else
                level.destroyBlock(pos, true);
        }
        level.setBlock(pos, this.defaultBlockState().setValue(AGE, newAge).setValue(HALF, Half.BOTTOM), 2);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state)
    {
        super.destroy(level, pos, state);
        if (state.getValue(HALF) == Half.BOTTOM)
        {   level.destroyBlock(pos.above(), false);
        }
        else level.destroyBlock(pos.below(), false);
    }

    @Override
    public int getMaxAge()
    {   return 6;
    }

    @Override
    protected ItemLike getBaseSeedId()
    {   return ItemInit.CARAMINE_RYE_SEEDS.value();
    }

    @Override
    public IntegerProperty getAgeProperty()
    {   return AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {   builder.add(AGE, HALF);
    }
}
