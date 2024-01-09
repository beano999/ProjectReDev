package net.roadkill.redev.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.common.Tags;
import net.roadkill.redev.common.world.tree.ShadeTree;

public class ShadeTreeFeature extends Feature<NoneFeatureConfiguration>
{
    public ShadeTreeFeature(Codec<NoneFeatureConfiguration> codec)
    {   super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> placement)
    {
        return placement.level().getBlockState(placement.origin().below()).is(Tags.Blocks.SAND)
        && ShadeTree.place(placement.level(), placement.origin(), placement.random().nextInt(100) > 10
                                                                             // 90% chance for normal tree
                                                                             ? ShadeTree.Color.NORMAL
                                                                             // 10% chance for colored tree
                                                                             : ShadeTree.Color.values()[placement.random().nextIntBetweenInclusive(1, 3)]);
    }
}
