package net.roadkill.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.extensions.IBlockExtension;
import net.roadkill.redev.mixin_interfaces.IEnchantPowerProvider;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IBlockExtension.class)
public interface MixinBookshelf extends IEnchantPowerProvider
{
    @Override
    default float getEnchantPowerBonus(BlockState state, LevelReader level, BlockPos pos)
    {   return state.is(Tags.Blocks.BOOKSHELVES) ? 1f : 0f;
    }
}