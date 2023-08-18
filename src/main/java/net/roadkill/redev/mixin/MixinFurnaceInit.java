package net.roadkill.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.roadkill.redev.common.block.ModFurnaceBlock;
import net.roadkill.redev.core.init.BlockEntityInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FurnaceBlockEntity.class)
public class MixinFurnaceInit
{
    @ModifyArg(method = "<init>(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V",
               at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;<init>(Lnet/minecraft/world/level/block/entity/BlockEntityType;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/item/crafting/RecipeType;)V"), index = 0)
    private static BlockEntityType<?> modifyArg(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state, RecipeType<?> recipeType)
    {
        return state.getBlock() instanceof ModFurnaceBlock ? BlockEntityInit.FURNACE_BLOCK_ENTITY_TYPE.get()
                                                           : blockEntity;
    }
}
