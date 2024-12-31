package net.roadkill.redev.common.event;

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
import net.roadkill.redev.util.RDMath;

import java.util.Map;
import java.util.function.Consumer;

@EventBusSubscriber
public class CustomMobSpawner
{
    private static final ResourceLocation STRONGHOLD_ID = ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "stronghold");

    private static final Map<ResourceKey<Level>, Boolean> ENABLED_DIMENSIONS = Map.of(
            Level.OVERWORLD, true,
            Level.NETHER, false,
            Level.END, false);

    private static final WeightedRandomList<WeightedEntry.Wrapper<Holder<EntityType<?>>>> STRONGHOLD_SPAWNS = WeightedRandomList.create(
            WeightedEntry.wrap(Holder.direct(EntityType.ZOMBIE), 100),
            WeightedEntry.wrap(Holder.direct(EntityType.SKELETON), 60),
            WeightedEntry.wrap(Holder.direct(EntityType.SILVERFISH), 70),
            WeightedEntry.wrap(Holder.direct(EntityType.ENDERMITE), 20),
            WeightedEntry.wrap(EntityInit.LITHICAN, 20),
            WeightedEntry.wrap(EntityInit.REVENANT, 60));

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
            if (!ENABLED_DIMENSIONS.getOrDefault(level.dimension(), false)) continue;

            RandomSource rand = level.random;

            // Iterate through all loaded chunks and check random positions for spawnable locations
            for (ChunkHolder chunkHolder : level.getChunkSource().chunkMap.getChunks())
            {
                LevelChunk chunk = chunkHolder.getTickingChunk();
                if (chunk == null) continue;
                ChunkPos chunkPos = chunk.getPos();

                // Check 2 blocks in each chunk per tick
                for (int i = 0; i < 2; i++)
                {
                    BlockPos randomPos = new BlockPos(chunkPos.getMinBlockX() + rand.nextInt(16),
                                                      rand.nextInt(chunk.getMinY(), chunk.getMaxY()),
                                                      chunkPos.getMinBlockZ() + rand.nextInt(16));

                    // Get structure at location
                    Structure structure = RDMath.getStructureAt(level, randomPos);

                    if (structure != null)
                    {
                        // Stronghold
                        if (isStructure(STRONGHOLD_ID, structure, structureRegistry))
                        {   spawnEntityFromPool(level, randomPos, MobCategory.MONSTER, STRONGHOLD_SPAWNS, entity -> {});
                        }
                    }
                }
            }
        }
    }

    private static <T extends Holder<EntityType<?>>> void spawnEntityFromPool(ServerLevel level, BlockPos pos, MobCategory category,
                                                                              WeightedRandomList<WeightedEntry.Wrapper<T>> pool,
                                                                              Consumer<Entity> entityBuilder)
    {
        // Create random entity from given pool
        EntityType<?> entityType = pool.getRandom(level.random).get().data().value();
        Entity entity = entityType.create(level, EntitySpawnReason.STRUCTURE);
        if (entity == null) return;
        entityBuilder.accept(entity);

        // Check for mob crowding (max 10 mobs of the same category in 10-block radius)
        AABB aabb = new AABB(pos).inflate(10);
        if (level.getEntities(entity, aabb, EntitySelector.LIVING_ENTITY_STILL_ALIVE).stream().filter(e -> e.getType().getCategory() == category).count() > 10)
        {   return;
        }

        // Check if space is open and floor is solid
        if (level.isEmptyBlock(pos) && level.isEmptyBlock(pos.above())
        && level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP))
        {
            // Call finalizeSpawn() so mob will be properly initialized (skeletons get their bows, etc.)
            if (entity instanceof Mob mob)
            {   mob.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), EntitySpawnReason.STRUCTURE, null);
            }
            entity.setPos(pos.getX(), pos.getY(), pos.getZ());
            // Add entity
            level.addFreshEntity(entity);
        }
    }

    private static boolean isStructure(ResourceLocation id, Structure structure, Registry<Structure> structureRegistry)
    {   return id.equals(structureRegistry.getKey(structure));
    }
}
