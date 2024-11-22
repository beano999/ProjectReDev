package net.roadkill.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructureTemplate.class)
public class MixinPlaceStructure
{
    @Inject(method = "placeInWorld", at = @At("HEAD"))
    public void placeInWorld(ServerLevelAccessor pServerLevel, BlockPos pPos, BlockPos p_230331_, StructurePlaceSettings pSettings, RandomSource pRandom, int pFlags, CallbackInfoReturnable<Boolean> cir)
    {   pSettings.setLiquidSettings(LiquidSettings.IGNORE_WATERLOGGING);
    }
}