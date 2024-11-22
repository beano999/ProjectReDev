package net.roadkill.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.BasaltPillarFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.roadkill.redev.core.init.BlockInit;
import net.roadkill.redev.util.registries.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BasaltPillarFeature.class)
public class MixinBasaltPillarGen
{
    @Inject(method = "place", at = @At(value = "RETURN"))
    private void place(FeaturePlaceContext<NoneFeatureConfiguration> pContext, CallbackInfoReturnable<Boolean> cir)
    {
        if (cir.getReturnValue())
        {
            BlockPos.MutableBlockPos pos = pContext.origin().mutable();
            for (int x = -1; x <= 1; x++)
            {
                for (int y = -1; y <= 1; y++)
                {
                    for (int z = -1; z <= 1; z++)
                    {   pos.setWithOffset(pContext.origin(), x, y, z);
                        if (pContext.level().getRandom().nextInt(40) < 5 && pContext.level().getBlockState(pos).is(Blocks.BASALT))
                        {   pContext.level().setBlock(pos, BlockInit.BASALT_DIAMOND_ORE.value().defaultBlockState(), 2);
                        }
                    }
                }
            }
        }
    }
}
