package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class HadaliteBlock extends Block {
    public HadaliteBlock(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity)
    {
       pEntity.hurt(pLevel.damageSources().hotFloor(), 4F);
    }
}
