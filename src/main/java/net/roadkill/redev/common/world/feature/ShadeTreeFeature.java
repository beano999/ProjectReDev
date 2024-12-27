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
        boolean isSaplingAt;
        ShadeTree.Color color;
        if (blockAt.getBlock() instanceof ShadeSaplingBlock sapling)
        {   color = sapling.getColor();
            isSaplingAt = true;
        }
        else
        {   color = ShadeTree.Color.NORMAL;
            isSaplingAt = false;
        }
        return (placement.level().getBlockState(placement.origin().below()).is(Tags.Blocks.SANDS) || isSaplingAt)
        && ShadeTree.place(placement.level(), placement.origin(), color);
    }
}
