package net.roadkill.redev.util.registries;

import net.minecraft.world.level.GameRules;

public class ModGameRules
{
    public static GameRules.Key<GameRules.BooleanValue> DO_OLD_COMBAT;
    public static GameRules.Key<GameRules.BooleanValue> DO_VOID_FOG;

    public static void registerGameRules()
    {
        DO_OLD_COMBAT = GameRules.register("doOldCombat", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));
        DO_VOID_FOG = GameRules.register("doVoidFog", GameRules.Category.MISC, GameRules.BooleanValue.create(true));
    }
}
