package net.roadkill.redev.mixin_interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public interface IEnchantPowerProvider
{
    default float getEnchantPowerBonus(BlockState state, LevelReader level, BlockPos pos)
    {   return 0f;
    }
}
