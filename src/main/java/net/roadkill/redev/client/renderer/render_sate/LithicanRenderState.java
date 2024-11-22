package net.roadkill.redev.client.renderer.render_sate;

import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.roadkill.redev.common.entity.LithicanEntity;

@OnlyIn(Dist.CLIENT)
public class LithicanRenderState extends ZombieRenderState
{
    public boolean isActive;
    public LithicanEntity.Variant variant;
    public float heat;
    public int arrowCount;
    public int entityId;
}
