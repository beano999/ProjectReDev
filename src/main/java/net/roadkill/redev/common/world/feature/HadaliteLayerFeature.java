package net.roadkill.redev.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.roadkill.redev.util.registries.ModBlocks;

public class HadaliteLayerFeature extends Feature<NoneFeatureConfiguration>
{
    public HadaliteLayerFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext)
    {
        BlockPos origin = pContext.origin();
        for (int x = 0; x < 16; x++)
        {
            for(int z = 0; z < 16; z++)
            {
                for(int y = 0; y < 5; y++)
                {
                    BlockPos currentPos = new BlockPos(origin.getX() + x, y, origin.getZ() + z);
                    if(y == 0 || pContext.level().getBlockState(currentPos).is(Blocks.BEDROCK))
                    {
                        pContext.level().setBlock(currentPos, ModBlocks.HADALITE.defaultBlockState(), 2);
                    }
                }
            }
        }
        return true;
    }
}
