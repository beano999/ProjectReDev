package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.roadkill.redev.common.block_entity.ModSignBlockEntity;

public class ModStandingSignBlock extends StandingSignBlock
{
    public ModStandingSignBlock(Properties properties, WoodType woodType)
    {   super(properties, woodType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {   return new ModSignBlockEntity(pos, state);
    }
}
