package net.roadkill.redev.mixin;

import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.phys.Vec3;
import net.roadkill.redev.data.FunctionalSpawnerData;
import net.roadkill.redev.util.RDMath;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;

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

    @Inject(method = "isRightDistanceToPlayerAndSpawnPoint(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/core/BlockPos$MutableBlockPos;D)Z",
            at = @At("HEAD"), cancellable = true)
    private static void isRightDistanceToPlayerAndSpawnPoint(ServerLevel level, ChunkAccess chunk, BlockPos.MutableBlockPos pos, double distance, CallbackInfoReturnable<Boolean> cir)
    {
        if (level.getSharedSpawnPos().closerToCenterThan(new Vec3((double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D), 24.0D))
        {   cir.setReturnValue(false);
        }
        else
        {   cir.setReturnValue(Objects.equals(new ChunkPos(pos), chunk.getPos()) || level.isNaturalSpawningAllowed(pos));
        }
    }

    @Inject(method = "isValidPositionForMob(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Mob;D)Z",
            at = @At("HEAD"), cancellable = true)
    private static void isValidPositionForMob(ServerLevel level, Mob mob, double distance, CallbackInfoReturnable<Boolean> cir)
    {
        if (distance > (double)(mob.getType().getCategory().getDespawnDistance() * mob.getType().getCategory().getDespawnDistance()) && mob.removeWhenFarAway(distance))
        {   cir.setReturnValue(false);
        }
        if (!(mob.checkSpawnRules(level, EntitySpawnReason.NATURAL) && mob.checkSpawnObstruction(level))) cir.setReturnValue(false);

        MutableBoolean canSpawnInStructure = new MutableBoolean(false);
        for (Map.Entry<Structure, LongSet> entry : level.structureManager().getAllStructuresAt(mob.blockPosition()).entrySet())
        {
            StructureSpawnOverride override = entry.getKey().spawnOverrides().get(mob.getType().getCategory());
            if (override != null && override.spawns().unwrap().stream().anyMatch(spawnerData -> spawnerData.type == mob.getType()))
            {   canSpawnInStructure.setValue(true);
                break;
            }
        }
        cir.setReturnValue(canSpawnInStructure.getValue() ? RDMath.withinRange(distance, 8*8, 80*80) : distance > 24*24);
    }
}
