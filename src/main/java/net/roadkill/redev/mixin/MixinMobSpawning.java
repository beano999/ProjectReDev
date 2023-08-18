package net.roadkill.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.roadkill.redev.data.FunctionalSpawnerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(NaturalSpawner.class)
public class MixinMobSpawning
{
    @Shadow
    private static WeightedRandomList<MobSpawnSettings.SpawnerData> mobsAt(ServerLevel p_220444_, StructureManager p_220445_, ChunkGenerator p_220446_, MobCategory p_220447_, BlockPos p_220448_, @Nullable Holder<Biome> p_220449_)
    {   return null;
    }

    @Inject(method = "canSpawnMobAt(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/world/entity/MobCategory;Lnet/minecraft/world/level/biome/MobSpawnSettings$SpawnerData;Lnet/minecraft/core/BlockPos;)Z",
            at = @At("HEAD"), cancellable = true)
    private static void canSpawnMobAt(ServerLevel level, StructureManager structureManager, ChunkGenerator chunkGenerator, MobCategory category,
                                      MobSpawnSettings.SpawnerData spawnerData, BlockPos pos, CallbackInfoReturnable<Boolean> cir)
    {
        cir.setReturnValue(mobsAt(level, structureManager, chunkGenerator, category, pos, null).unwrap().contains(spawnerData)
                       && (!(spawnerData instanceof FunctionalSpawnerData functional) || functional.canSpawn(level, structureManager, chunkGenerator, category, spawnerData, pos)));
    }
}
