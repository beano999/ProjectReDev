package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class ShadeSaplingBlock extends SaplingBlock
{
    public ShadeSaplingBlock(AbstractTreeGrower tree, Properties properties)
    {   super(tree, properties);
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos)
    {   return state.is(BlockTags.DEAD_BUSH_MAY_PLACE_ON);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {   return mayPlaceOn(level.getBlockState(pos.below()), level, pos);
    }
}
