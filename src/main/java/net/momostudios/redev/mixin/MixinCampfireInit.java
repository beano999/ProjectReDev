package net.momostudios.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.momostudios.redev.common.block.ModCampfireBlock;
import net.momostudios.redev.common.block_entity.ModCampfireBlockEntity;
import net.momostudios.redev.core.init.BlockEntityInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CampfireBlockEntity.class)
public class MixinCampfireInit
{
    @ModifyArg(method = "<init>(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V",
               at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/BlockEntity;<init>(Lnet/minecraft/world/level/block/entity/BlockEntityType;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V"), index = 0)
    private static BlockEntityType<?> modifyArg(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state)
    {
        if (state.getBlock() instanceof ModCampfireBlock)
        {   return BlockEntityInit.CAMPFIRE_BLOCK_ENTITY_TYPE.get();
        }
        return blockEntity;
    }
}
