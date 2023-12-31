package net.roadkill.redev.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.roadkill.redev.mixin_interfaces.OldCombatPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public abstract class MixinHumanoidModel<T extends LivingEntity> extends AgeableListModel<T> implements ArmedModel, HeadedModel
{
    @Final
    @Shadow
    public ModelPart rightArm;

    @Final
    @Shadow
    public ModelPart leftArm;

    @Inject(method = "poseRightArm", at = @At(value = "HEAD"), cancellable = true)
    private void renderRight(T entity, CallbackInfo info)
    {
        Player player = Minecraft.getInstance().player;
        if (player != null && player.getMainHandItem().getItem() instanceof SwordItem
        && ((OldCombatPlayer) player).isSwordBlocking())
        {   rotateArm(entity.getMainArm(), this.rightArm);
            info.cancel();
        }
    }

    @Inject(method = "poseLeftArm", at = @At(value = "HEAD"), cancellable = true)
    private void renderLeft(T entity, CallbackInfo info){
        Player player = Minecraft.getInstance().player;
        if (player != null && player.getOffhandItem().getItem() instanceof SwordItem
        && ((OldCombatPlayer) player).isSwordBlocking())
        {   rotateArm(entity.getMainArm().getOpposite(), this.leftArm);
            info.cancel();
        }
    }

    @Inject(method = "setupAttackAnimation", at = @At(value = "HEAD"), cancellable = true)
    private void renderCancel(T entity, float ageInTicks, CallbackInfo info)
    {
        Player player = Minecraft.getInstance().player;
        if (player != null && player.getMainHandItem().getItem() instanceof SwordItem
        && ((OldCombatPlayer) player).isSwordBlocking())
        {   info.cancel();
        }
    }

    private static void rotateArm(HumanoidArm type, ModelPart arm)
    {
        arm.xRot = arm.xRot * 0.5F - 0.9424778F;
        arm.yRot = (type == HumanoidArm.RIGHT ? 1 : -1) * -0.5235988F;
    }
}
