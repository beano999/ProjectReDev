package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.level.BlockGrowFeatureEvent;
import net.roadkill.redev.common.world.tree.ShadeTree;
import net.roadkill.redev.core.init.BlockInit;
import net.roadkill.redev.core.init.FeatureInit;

public class ShadeSaplingBlock extends SaplingBlock
{
    private final ShadeTree.Color color;

    public ShadeSaplingBlock(ShadeTree.Color color, Properties properties)
    {   super(BlockInit.SHADE_TREE_GROWER, properties);
        this.color = color;
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos)
    {   return state.is(BlockTags.DEAD_BUSH_MAY_PLACE_ON);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {   return mayPlaceOn(level.getBlockState(pos.below()), level, pos);
    }

    @Override
    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, RandomSource random)
    {
        if (state.getValue(STAGE) == 0)
        {   level.setBlock(pos, state.cycle(STAGE), 4);
        }
        else
        {
            Holder<ConfiguredFeature<?, ?>> shadeTreeHolder = level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(FeatureInit.CONFIGURED_SHADE_TREE).orElse(null);
            BlockGrowFeatureEvent event = EventHooks.fireBlockGrowFeature(level, random, pos, shadeTreeHolder);
            shadeTreeHolder = event.getFeature();
            if (event.isCanceled()) return;
            else if (shadeTreeHolder == null) return;

            shadeTreeHolder.value().place(level, level.getChunkSource().getGenerator(), random, pos);
        }
    }

    public ShadeTree.Color getColor()
    {   return this.color;
    }
}
