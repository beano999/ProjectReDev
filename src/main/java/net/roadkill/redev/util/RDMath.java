package net.roadkill.redev.util;

import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public final class RDMath
{
    public static boolean allEquals(Object... objects)
    {   if (objects.length == 0)
        {   return true;
        }
        Object first = objects[0];
        for (Object object : objects)
        {   if (!object.equals(first))
            {   return false;
            }
        }
        return true;
    }

    @SafeVarargs
    public static <T> boolean allMatch (Predicate<T> condition, T... values)
    {
        for (T value : values)
        {   if (!condition.test(value))
            {   return false;
            }
        }
        return true;
    }

    public static double blend(double blendFrom, double blendTo, double factor, double rangeMin, double rangeMax)
    {
        if (factor <= rangeMin) return blendFrom;
        if (factor >= rangeMax) return blendTo;
        return (blendTo - blendFrom) / (rangeMax - rangeMin) * (factor - rangeMin) + blendFrom;
    }

    public static double blendExp(double blendFrom, double blendTo, double factor, double rangeMin, double rangeMax)
    {
        if (factor <= rangeMin) return blendFrom;
        if (factor >= rangeMax) return blendTo;
        return (blendTo - blendFrom) / Math.pow(rangeMax - rangeMin, 2) * Math.pow(factor - rangeMin, 2) + blendFrom;
    }

    public static double blendLog(double blendFrom, double blendTo, double factor, double rangeMin, double rangeMax, double intensity)
    {
        factor = clamp(factor, rangeMin, rangeMax);

        double normalizedFactor = (factor - rangeMin) / (rangeMax - rangeMin);
        double logFactor = Math.log(intensity * normalizedFactor + 1) / Math.log(intensity + 1);

        return blendFrom + (blendTo - blendFrom) * logFactor;
    }

    public static boolean withinRange(int value, int min, int max)
    {   return value >= min && value <= max;
    }

    public static boolean withinRange(double value, double min, double max)
    {   return value >= min && value <= max;
    }

    public static boolean withinRange(long value, long min, long max)
    {   return value >= min && value <= max;
    }

    public static float clamp (float value, float min, float max)
    {   return Math.max(min, Math.min(max, value));
    }

    public static double clamp (double value, double min, double max)
    {   return Math.max(min, Math.min(max, value));
    }

    public static int clamp (int value, int min, int max)
    {   return Math.max(min, Math.min(max, value));
    }

    public static Quaternionf getQuaternion(double x, double y, double z)
    {
        double cy = Math.cos(z * 0.5);
        double sy = Math.sin(z * 0.5);
        double cp = Math.cos(y * 0.5);
        double sp = Math.sin(y * 0.5);
        double cr = Math.cos(x * 0.5);
        double sr = Math.sin(x * 0.5);

        return new Quaternionf(
                (float) (sr * cp * cy - cr * sp * sy),
                (float) (cr * sp * cy + sr * cp * sy),
                (float) (cr * cp * sy - sr * sp * cy),
                (float) (cr * cp * cy + sr * sp * sy));
    }

    public static float toRadians(float input)
    {   return input * (float) (Math.PI / 180);
    }

    public static void dropItem(Level level, Vec3 position, ItemStack stack)
    {
        double xVel = Mth.nextDouble(level.random, -0.15, 0.15);
        double yVel = 0.1;
        double zVel = Mth.nextDouble(level.random, -0.15, 0.15);
        ItemEntity item = new ItemEntity(level, position.x, position.y, position.z, stack);
        item.setDeltaMovement(xVel, yVel, zVel);
        level.addFreshEntity(item);
    }

    public static Vec3 randomVector3f(RandomSource random, float magnitude)
    {   return new Vec3(Mth.nextFloat(random, -1, 1), Mth.nextFloat(random, -1, 1), Mth.nextFloat(random, -1, 1)).normalize().scale(magnitude);
    }

    public static double randomDouble(RandomSource rand, double min, double max)
    {   return rand.nextDouble() * (max - min) + min;
    }

    public static float randomFloat(RandomSource rand, float min, float max)
    {   return rand.nextFloat() * (max - min) + min;
    }

    @Nullable
    public static Structure getStructureAt(Level level, BlockPos pos)
    {
        if (!(level instanceof ServerLevel serverLevel)) return null;

        StructureManager structureManager = serverLevel.structureManager();

        // Iterate over all structures at the position (ignores Y level)
        for (Map.Entry<Structure, LongSet> entry : structureManager.getAllStructuresAt(pos).entrySet())
        {
            Structure structure = entry.getKey();
            LongSet strucCoordinates = entry.getValue();

            // Iterate over all chunk coordinates within the structures
            for (long coordinate : strucCoordinates)
            {
                SectionPos sectionpos = SectionPos.of(new ChunkPos(coordinate), level.getMinSectionY());
                // Get the structure start
                StructureStart structurestart = structureManager.getStartForStructure(sectionpos, structure, level.getChunk(sectionpos.x(), sectionpos.z(), ChunkStatus.STRUCTURE_STARTS));

                if (structurestart != null && structurestart.isValid())
                {
                    // If the structure has a piece at the position, get the temperature
                    if (structureManager.structureHasPieceAt(pos, structurestart))
                    {   return structure;
                    }
                }
            }
        }
        return null;
    }

    public static List<BlockPos> getPositionGrid(BlockPos pos, int samples, int interval)
    {
        List<BlockPos> posList = new ArrayList<>();
        int sampleRoot = (int) Math.sqrt(samples);
        int radius = (sampleRoot * interval) / 2;

        for (int x = -radius; x < radius; x += interval)
        {
            for (int z = -radius; z < radius; z += interval)
            {   posList.add(pos.offset(x + interval / 2, 0, z + interval / 2));
            }
        }

        return posList;
    }

    public static double getAverageDepth(LivingEntity entity)
    {
        double depth = 0;
        for (BlockPos pos : getPositionGrid(entity.blockPosition(), 36, 10))
        {   depth += entity.level().getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ()) - entity.getY();
        }
        return depth / 36;
    }
}
