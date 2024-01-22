package net.roadkill.redev.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.fml.LogicalSide;
import org.joml.Quaternionf;

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

    public static double blendLog(double blendFrom, double blendTo, double factor, double rangeMin, double rangeMax)
    {
        if (factor <= rangeMin) return blendFrom;
        if (factor >= rangeMax) return blendTo;
        return (blendTo - blendFrom) / Math.log(rangeMax - rangeMin) * Math.log(factor - rangeMin + 1) + blendFrom;
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

    public static Level getClientLevel()
    {   return ((Minecraft) LogicalSidedProvider.WORKQUEUE.get(LogicalSide.CLIENT)).level;
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
}
