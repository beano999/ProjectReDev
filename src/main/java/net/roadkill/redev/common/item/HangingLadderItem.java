package net.roadkill.redev.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HangingLadderItem extends BlockItem
{
    public HangingLadderItem(Block pBlock, Properties properties)
    {   super(pBlock, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        Level level = context.getLevel();
        BlockState state = level.getBlockState(context.getClickedPos());
        if (state.getBlock() == this.getBlock())
        {   int i = 1;
            while (level.getBlockState(context.getClickedPos().below(i)).getBlock() == this.getBlock())
            {   i++;
            }
            BlockPos pos = context.getClickedPos().below(i);

            if (level.getBlockState(pos).canBeReplaced(new BlockPlaceContext(context)))
            {   BlockPlaceContext ctx = BlockPlaceContext.at(new BlockPlaceContext(context), context.getClickedPos().below(i), state.getValue(LadderBlock.FACING));
                return this.place(ctx);
            }
        }
        return super.useOn(context);
    }
}
