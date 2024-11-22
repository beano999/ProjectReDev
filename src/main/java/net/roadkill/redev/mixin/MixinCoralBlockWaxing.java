package net.roadkill.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CoralBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.roadkill.redev.mixin_public.CoralBlockData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CoralBlock.class)
public class MixinCoralBlockWaxing
{
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void cancelTickIfWaxed(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci)
    {
        if (state.getValue(CoralBlockData.WAXED))
        {   ci.cancel();
        }
    }

    @ModifyArg(method = "getStateForPlacement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;scheduleTick(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;I)V", ordinal = 0), index = 2)
    private int increaseDecayTime(int delay)
    {   return 200 + RandomSource.create().nextInt(200);
    }

    @Mixin(BlockBehaviour.class)
    public static class MixinBaseBlock
    {
        @Inject(method = "useItemOn", at = @At("HEAD"))
        private void waxApply(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player,
                              InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir)
        {
            if (state.getBlock() instanceof CoralBlock && stack.getItem() == Items.HONEYCOMB)
            {
                level.setBlock(pos, state.setValue(CoralBlockData.WAXED, true), 3);
                player.swing(hand, true);
                level.playSound(null, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative())
                {   stack.shrink(1);
                }

                ParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.WAX_ON, UniformInt.of(3, 5));
            }
        }
    }

    @Mixin(Block.class)
    public static class MixinBlock
    {
        Block self = (Block) (Object) this;

        @Shadow
        protected final void registerDefaultState(BlockState state) {}

        @Inject(method = "getStateForPlacement", at = @At("HEAD"), cancellable = true)
        public void addWaxStateForPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir)
        {
            if (self instanceof CoralBlock)
            {   cir.setReturnValue(cir.getReturnValue().setValue(CoralBlockData.WAXED, false));
            }
        }

        @Inject(method = "<init>", at = @At("RETURN"))
        public void initWaxedProperty(BlockBehaviour.Properties properties, CallbackInfo ci)
        {
            if (self instanceof CoralBlock)
            {   registerDefaultState(self.defaultBlockState().setValue(CoralBlockData.WAXED, false));
            }
        }

        @Inject(method = "createBlockStateDefinition", at = @At("RETURN"))
        protected void addWaxedStateDefinition(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci)
        {
            if (self instanceof CoralBlock)
            {   builder.add(CoralBlockData.WAXED);
            }
        }
    }
}
