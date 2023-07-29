package net.momostudios.redev.util;

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
}
