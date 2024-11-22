package net.roadkill.redev.client.model.entity;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.roadkill.redev.client.renderer.render_sate.LithicanRenderState;

public class LithicanModel<S extends LithicanRenderState> extends ZombieModel<S>
{
    public LithicanModel(ModelPart pRoot)
    {   super(pRoot);
    }

    @Override
    public void setupAnim(S renderState)
    {
        if (renderState.isActive)
        {   super.setupAnim(renderState);
            head.z = -1f;
            body.z = 0f;
            leftArm.z = 0f;
            rightArm.z = 0f;
        }
        else
        {   leftArm.xRot = 0;
            leftArm.yRot = 0;
            leftArm.zRot = 0;
            leftArm.z = -2f;

            rightArm.xRot = 0;
            rightArm.yRot = 0;
            rightArm.zRot = 0;
            rightArm.z = -2f;

            leftLeg.xRot = 0;
            leftLeg.yRot = 0;
            leftLeg.zRot = 0;

            rightLeg.xRot = 0;
            rightLeg.yRot = 0;
            rightLeg.zRot = 0;

            head.xRot = 0.5f;
            head.yRot = 0;
            head.z = -2.5f;

            body.xRot = 0.2f;
            body.z = -2f;
        }
        this.leftArm.yScale = 1.15f;
        this.rightArm.yScale = 1.15f;
        this.body.yScale = 0.9f;
        this.head.y = 1.25f;
        this.body.y = 1.25f;
        this.rightArm.y = 2.25f;
        this.leftArm.y = 2.25f;
        this.rightLeg.xScale = 1.05f;
        this.leftLeg.xScale = 1.05f;
    }

    @Override
    protected void setupAttackAnimation(S renderState, float pAgeInTicks)
    {
        float attackTime = renderState.attackTime;
        if (!(attackTime <= 0.0F))
        {
            float f = attackTime;
            this.body.yRot = Mth.sin(Mth.sqrt(f) * ((float)Math.PI * 2F)) * 0.2F;

            this.rightArm.z = Mth.sin(this.body.yRot) * 5.0F;
            this.rightArm.x = -Mth.cos(this.body.yRot) * 5.0F;
            this.leftArm.z = -Mth.sin(this.body.yRot) * 5.0F;
            this.leftArm.x = Mth.cos(this.body.yRot) * 5.0F;
            this.rightArm.yRot += this.body.yRot;
            this.leftArm.yRot += this.body.yRot;
            this.leftArm.xRot += this.body.yRot;
            f = 1.0F - attackTime;
            f *= f;
            f *= f;
            f = 1.0F - f;
            float f1 = Mth.sin(f * (float)Math.PI);
            float f2 = Mth.sin(attackTime * (float)Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;
            rightArm.xRot -= f1 * 1.5F + f2;
            rightArm.yRot += this.body.yRot * 2.0F;
            rightArm.zRot += Mth.sin(attackTime * (float)Math.PI) * -0.6F;
            leftArm.xRot  -= f1 * 1.5F + f2;
            leftArm.yRot  -= this.body.yRot * 2.0F;
            leftArm.zRot  -= Mth.sin(attackTime * (float)Math.PI) * -0.6F;
        }
    }
}
