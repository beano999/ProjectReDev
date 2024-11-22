package net.roadkill.redev.client.renderer.render_sate;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.roadkill.redev.common.entity.HoveringInfernoEntity;

@OnlyIn(Dist.CLIENT)
public class HoveringInfernoRenderState extends LivingEntityRenderState
{
    public HoveringInfernoEntity entity;
}
