package net.roadkill.redev.mixin;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.feature.TwistingVinesFeature;
import net.roadkill.redev.common.block.ModTwistingVinesPlantBlock;
import net.roadkill.redev.util.registries.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.concurrent.atomic.AtomicReference;

@Mixin(TwistingVinesFeature.class)
public class MixinTwistingVineGen
{
    @ModifyArg(method = "placeWeepingVinesColumn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/LevelAccessor;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"), index = 1)
    private static BlockState onPlace(BlockState state)
    {
        if (state.is(Blocks.TWISTING_VINES_PLANT))
        {   return copyValues(state, ModBlocks.TWISTING_VINES_PLANT.defaultBlockState().setValue(ModTwistingVinesPlantBlock.HAS_GROWN_TODAY, false));
        }
        else return state;
    }

    private static BlockState copyValues(BlockState state, BlockState newState)
    {   AtomicReference<BlockState> result = new AtomicReference<>(newState);
        state.getValues().forEach((property, value) -> result.set(result.get().setValue((Property) property, (Comparable) value)));
        return result.get();
    }
}
