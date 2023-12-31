package net.roadkill.redev.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.fml.LogicalSide;

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
        return ((1 / (rangeMax - rangeMin)) * (factor - rangeMin)) * (blendTo - blendFrom) + blendFrom;
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
}
