package net.momostudios.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.momostudios.redev.mixin_interfaces.ForgeBlockMixin;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IForgeBlock.class)
public interface MixinBookshelf extends ForgeBlockMixin
{
    @Override
    default float getEnchantPowerBonus(BlockState state, LevelReader level, BlockPos pos)
    {   return state.is(Tags.Blocks.BOOKSHELVES) ? 1f : 0f;
    }
}