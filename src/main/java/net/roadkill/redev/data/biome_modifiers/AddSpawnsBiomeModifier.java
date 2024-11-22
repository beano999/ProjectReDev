package net.roadkill.redev.data.biome_modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;
import net.roadkill.redev.core.init.BiomeCodecInit;
import net.roadkill.redev.core.init.EntityInit;
import net.roadkill.redev.data.FunctionalSpawnerData;


public record AddSpawnsBiomeModifier(boolean dummy) implements BiomeModifier
{
    public static final FunctionalSpawnerData CAVE_SPIDER = new FunctionalSpawnerData(EntityType.CAVE_SPIDER, 500, 1, 3,
                                                            (level, structureManager, chunkGenerator, category, spawnerData, pos) ->
                                                            {
                                                                return level.random.nextInt(level.getMinY(), level.getSeaLevel()) > pos.getY();
                                                            });

    public static final FunctionalSpawnerData LITHICAN = new FunctionalSpawnerData(EntityInit.LITHICAN.get(), 100, 1, 3,
                                                         (level, structureManager, chunkGenerator, category, spawnerData, pos) ->
                                                         {
                                                             BlockState state = level.getBlockState(pos.below());
                                                             return state.is(Tags.Blocks.STONES) && level.getBrightness(LightLayer.SKY, pos) == 0
                                                                     || state.is(Blocks.BASALT) && level.dimensionTypeRegistration().equals(BuiltinDimensionTypes.NETHER);
                                                         });

    public static final FunctionalSpawnerData HOVERING_INFERNO = new FunctionalSpawnerData(EntityInit.HOVERING_INFERNO.get(), 60, 1, 1,
                                                                 (level, structureManager, chunkGenerator, category, spawnerData, pos) ->
                                                                 {
                                                                     return level.dimensionTypeRegistration().equals(BuiltinDimensionTypes.NETHER) && level.getBlockState(pos.below()).is(Blocks.NETHER_BRICKS);
                                                                 });

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder)
    {
        if (phase == Phase.ADD)
        {
            biome.unwrapKey().ifPresent(biomeKey ->
            {
                if (biome.is(BiomeTags.IS_OVERWORLD))
                {
                    builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, CAVE_SPIDER);
                    builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, LITHICAN);
                }
                else if (biome.is(BiomeTags.IS_NETHER))
                {
                    builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, LITHICAN);
                    builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, HOVERING_INFERNO);
                }
            });
        }
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec()
    {   return BiomeCodecInit.ADD_SPAWNS_CODEC.value();
    }
}
