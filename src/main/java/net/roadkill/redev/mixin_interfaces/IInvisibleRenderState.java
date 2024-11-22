package net.roadkill.redev.mixin_interfaces;

import net.minecraft.world.scores.PlayerTeam;

public interface IInvisibleRenderState
{
    boolean isPartialInvisible();
    void setPartialInvisible(boolean invisible);

    PlayerTeam getTeam();
    void setTeam(PlayerTeam team);

    boolean isSpectator();
    void setSpectator(boolean spectator);
}
