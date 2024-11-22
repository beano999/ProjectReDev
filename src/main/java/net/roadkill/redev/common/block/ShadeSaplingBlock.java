package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.roadkill.redev.common.world.tree.ShadeTree;
import net.roadkill.redev.core.init.BlockInit;

public class ShadeSaplingBlock extends SaplingBlock
{
    private final ShadeTree.Color color;

    public ShadeSaplingBlock(ShadeTree.Color color, Properties properties)
    {   super(BlockInit.SHADE_TREE_GROWER, properties);
        this.color = color;
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos)
    {   return state.is(BlockTags.DEAD_BUSH_MAY_PLACE_ON);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {   return mayPlaceOn(level.getBlockState(pos.below()), level, pos);
    }

    public ShadeTree.Color getColor()
    {   return this.color;
    }
}
