package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;
import net.roadkill.redev.data.ModTags;

public class NetherBristleBlock extends BushBlock implements IForgeShearable
{
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public NetherBristleBlock(BlockBehaviour.Properties properties)
    {   super(properties);
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {   return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos)
    {   return state.is(ModTags.Blocks.NETHER_BRISTLE_PLACEABLE);
    }
}
