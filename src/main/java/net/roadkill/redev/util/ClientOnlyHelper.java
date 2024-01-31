package net.roadkill.redev.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;

public class ClientOnlyHelper
{
    public static Level getClientLevel()
    {   return Minecraft.getInstance().level;
    }
}
