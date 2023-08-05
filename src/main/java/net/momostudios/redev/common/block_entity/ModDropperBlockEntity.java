package net.momostudios.redev.common.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.entity.DropperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ModDropperBlockEntity extends DropperBlockEntity
{
    public ModDropperBlockEntity(BlockPos pos, BlockState state)
    {   super(pos, state);
    }
}
