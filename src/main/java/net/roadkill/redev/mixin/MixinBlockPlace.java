package net.roadkill.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.core.init.BlockInit;
import net.roadkill.redev.util.registries.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicReference;

@Mixin(BlockBehaviour.class)
public class MixinBlockPlace
{
    @Inject(method = "onPlace", at = @At(value = "TAIL"))
    private void onBlockPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston, CallbackInfo ci)
    {
        if (state.is(Blocks.CAMPFIRE))
        {   level.setBlock(pos, copyValues(state, BlockInit.OAK_CAMPFIRE.value().defaultBlockState()), 2);
        }
        else if (state.is(Blocks.SOUL_CAMPFIRE))
        {   level.setBlock(pos, copyValues(state, BlockInit.OAK_SOUL_CAMPFIRE.value().defaultBlockState()), 2);
        }
        else if (state.is(Blocks.TWISTING_VINES_PLANT))
        {   level.setBlock(pos, copyValues(state, BlockInit.TWISTING_VINES_PLANT.value().defaultBlockState()), 2);
        }
        else if (state.is(Blocks.WARPED_ROOTS))
        {   level.setBlock(pos, copyValues(state, BlockInit.WARPED_ROOTS.value().defaultBlockState()), 2);
        }
    }

    private static BlockState copyValues(BlockState state, BlockState newState)
    {   AtomicReference<BlockState> result = new AtomicReference<>(newState);
        state.getValues().forEach((property, value) -> result.set(result.get().setValue((Property) property, (Comparable) value)));
        return result.get();
    }
}
