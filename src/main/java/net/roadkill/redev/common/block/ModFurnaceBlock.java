package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.roadkill.redev.common.block_entity.ModFurnaceBlockEntity;
import net.roadkill.redev.core.init.BlockEntityInit;

import javax.annotation.Nullable;

public class ModFurnaceBlock extends FurnaceBlock
{
    public ModFurnaceBlock(Properties properties)
    {   super(properties);
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {   return new ModFurnaceBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType)
    {   return createFurnaceTicker(level, blockEntityType, BlockEntityInit.FURNACE_BLOCK_ENTITY_TYPE.get());
    }
}
