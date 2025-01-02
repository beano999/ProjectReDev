package net.roadkill.redev.common.event;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.core.init.EntityInit;
import net.roadkill.redev.data.loot_modifiers.util.IntegerBounds;
import net.roadkill.redev.util.RDMath;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@EventBusSubscriber
public class CustomMobSpawner
{
    private static final Set<ResourceKey<Level>> ENABLED_DIMENSIONS = Set.of(Level.OVERWORLD);

    private static final WeightedRandomList<WeightedEntry.Wrapper<Pair<Holder<EntityType<?>>, IntegerBounds>>> STRONGHOLD_SPAWNS = WeightedRandomList.create(
            WeightedEntry.wrap(Pair.of(Holder.direct(EntityType.ZOMBIE), IntegerBounds.of(1, 1)), 100),
            WeightedEntry.wrap(Pair.of(Holder.direct(EntityType.SKELETON), IntegerBounds.of(1, 1)), 60),
            WeightedEntry.wrap(Pair.of(Holder.direct(EntityType.SILVERFISH), IntegerBounds.of(2, 6)), 70),
            WeightedEntry.wrap(Pair.of(Holder.direct(EntityType.ENDERMITE), IntegerBounds.of(1, 1)), 20),
            WeightedEntry.wrap(Pair.of(EntityInit.LITHICAN, IntegerBounds.of(1, 1)), 20),
            WeightedEntry.wrap(Pair.of(EntityInit.REVENANT, IntegerBounds.of(1, 1)), 60));

    private static final Map<ResourceLocation, WeightedRandomList<WeightedEntry.Wrapper<Pair<Holder<EntityType<?>>, IntegerBounds>>>> STRUCTURE_SPAWNS = Map.of(
            ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "stronghold"), STRONGHOLD_SPAWNS
    );

    @SubscribeEvent
    public static void spawnMobsInStructures(ServerTickEvent.Post event)
    {
        RegistryAccess registryAccess = event.getServer().registryAccess();
        Registry<Structure> structureRegistry = registryAccess.lookupOrThrow(Registries.STRUCTURE);

        // Iterate through all levels loaded on the server
        for (ServerLevel level : event.getServer().getAllLevels())
        {
            // Cancel if mob spawning is disabled or level is not enabled
            if (!level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) continue;
            if (!ENABLED_DIMENSIONS.contains(level.dimension())) continue;

            RandomSource rand = level.random;

            // Iterate through all loaded chunks and check random positions for spawnable locations
            for (ChunkHolder chunkHolder : level.getChunkSource().chunkMap.getChunks())
            {
                LevelChunk chunk = chunkHolder.getTickingChunk();
                if (chunk == null) continue;
                ChunkPos chunkPos = chunk.getPos();

                // 70% chance to spawn a mob
                if (rand.nextDouble() > 0.7) continue;

                BlockPos randomPos = new BlockPos(chunkPos.getMinBlockX() + rand.nextInt(16),
                                                  rand.nextInt(chunk.getMinY(), chunk.getMaxY()),
                                                  chunkPos.getMinBlockZ() + rand.nextInt(16));

                // Get structure at location
                Structure structure = RDMath.getStructureAt(level, randomPos);

                if (structure != null)
                {
                    // Spawn entity from structure's pool
                    var spawnList = STRUCTURE_SPAWNS.get(structureRegistry.getKey(structure));
                    if (spawnList != null)
                    {
                        spawnEntityFromPool(level, randomPos, MobCategory.MONSTER, spawnList, entity -> {});
                    }
                }
            }
        }
    }

    private static void spawnEntityFromPool(ServerLevel level, BlockPos pos, MobCategory category,
                                                                              WeightedRandomList<WeightedEntry.Wrapper<Pair<Holder<EntityType<?>>, IntegerBounds>>> pool,
                                                                              Consumer<Entity> entityBuilder)
    {
        // Get random entry from the pool
        Pair<Holder<EntityType<?>>, IntegerBounds> entry = pool.getRandom(level.random).get().data();
        EntityType<?> entityType = entry.getFirst().value();
        IntegerBounds countBounds = entry.getSecond();

        // Don't spawn entities within 16 blocks of a player
        if (!level.getNearbyPlayers(TargetingConditions.forNonCombat(), null, new AABB(pos).inflate(16)).isEmpty())
        {   return;
        }

        ChunkPos chunkPos = new ChunkPos(pos);
        AABB subChunkAABB = new AABB(chunkPos.getMinBlockX(), Math.floor(pos.getY() / 16.0) * 16, chunkPos.getMinBlockZ(),
                             chunkPos.getMaxBlockX(), Math.ceil(pos.getY() / 16.0) * 16 + 16, chunkPos.getMaxBlockZ());

        List<Entity> nearbyEntities = level.getEntities((Entity) null, subChunkAABB, EntitySelector.LIVING_ENTITY_STILL_ALIVE);
        // Check for mob crowding (max 10 mobs of the same category in 10-block radius)
        if (nearbyEntities.stream().filter(e -> e.getType().getCategory() == category).count() >= 5)
        {   return;
        }

        // Check if space is open and floor is solid
        if (level.isEmptyBlock(pos) && level.isEmptyBlock(pos.above())
        && level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP))
        {
            int count = countBounds.getRandom(level.random);
            for (int i = 0; i < count; i++)
            {
                Entity entity = entityType.create(level, EntitySpawnReason.STRUCTURE);
                if (entity == null) return;
                // Call finalizeSpawn() so mob will be properly initialized (skeletons get their bows, etc.)
                if (entity instanceof Mob mob)
                {   mob.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), EntitySpawnReason.STRUCTURE, null);
                }
                entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                // Fire entity builder
                entityBuilder.accept(entity);
                // Add entity
                level.addFreshEntity(entity);
            }
        }
    }
}
