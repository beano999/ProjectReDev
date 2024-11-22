package net.roadkill.redev.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.common.Tags;
import net.roadkill.redev.common.block.ShadeSaplingBlock;
import net.roadkill.redev.common.world.tree.ShadeTree;

public class ShadeTreeFeature extends Feature<NoneFeatureConfiguration>
{
    public ShadeTreeFeature(Codec<NoneFeatureConfiguration> codec)
    {   super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> placement)
    {
        BlockState blockAt = placement.level().getBlockState(placement.origin());
        ShadeTree.Color color;
        if (blockAt.getBlock() instanceof ShadeSaplingBlock sapling)
        {   color = sapling.getColor();
        }
        else
        {
            color = placement.random().nextInt(100) > 10
                    // 90% chance for normal tree
                    ? ShadeTree.Color.NORMAL
                    // 10% chance for colored tree
                    : ShadeTree.Color.values()[placement.random().nextIntBetweenInclusive(1, 3)];
        }
        return placement.level().getBlockState(placement.origin().below()).is(Tags.Blocks.SANDS)
        && ShadeTree.place(placement.level(), placement.origin(), color);
    }
}
