package net.roadkill.redev.data.biome_modifiers;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.roadkill.redev.core.init.BiomeCodecInit;
import net.roadkill.redev.core.init.EntityInit;
import net.roadkill.redev.data.FunctionalSpawnerData;


public record AddSpawnsBiomeModifier(boolean dummy) implements BiomeModifier
{
    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder)
    {
        if (phase == Phase.ADD)
        {
            biome.unwrapKey().ifPresent(biomeKey ->
            {
                if (biome.is(BiomeTags.IS_OVERWORLD))
                {
                    builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, new FunctionalSpawnerData(EntityType.CAVE_SPIDER, 500, 1, 3,
                    (level, structureManager, chunkGenerator, category, spawnerData, pos) ->
                    {   return level.random.nextInt(level.getMinBuildHeight(), level.getSeaLevel()) > pos.getY();
                    }));

                    builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, new FunctionalSpawnerData(EntityInit.LITHICAN.get(), 100, 1, 3,
                    (level, structureManager, chunkGenerator, category, spawnerData, pos) ->
                    {
                        BlockState state = level.getBlockState(pos.below());
                        return state.is(Tags.Blocks.STONE) && level.getBrightness(LightLayer.SKY, pos) == 0
                            || state.is(Blocks.BASALT) && level.dimensionTypeId().equals(BuiltinDimensionTypes.NETHER);
                    }));
                }
            });
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec()
    {   return BiomeCodecInit.ADD_SPAWNS_CODEC.get();
    }
}
