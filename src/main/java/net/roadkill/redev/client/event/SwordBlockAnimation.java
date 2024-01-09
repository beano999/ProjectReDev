package net.roadkill.redev.client.event;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.roadkill.redev.mixin_interfaces.OldCombatPlayer;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class SwordBlockAnimation
{
    static final Field MAIN_HAND_HEIGHT = ObfuscationReflectionHelper.findField(ItemInHandRenderer.class, "f_109302_");
    static final Field O_MAIN_HAND_HEIGHT = ObfuscationReflectionHelper.findField(ItemInHandRenderer.class, "f_109303_");
    static final Field OFF_HAND_HEIGHT = ObfuscationReflectionHelper.findField(ItemInHandRenderer.class, "f_109304_");
    static final Field O_OFF_HAND_HEIGHT = ObfuscationReflectionHelper.findField(ItemInHandRenderer.class, "f_109305_");

    public static boolean IS_MAIN_BLOCKING = false;
    public static boolean IS_OFF_BLOCKING = false;

    @SubscribeEvent
    public static void onRenderSword(RenderHandEvent event)
    {
        AbstractClientPlayer player = Minecraft.getInstance().player;
        if (player != null
        && event.getItemStack().getItem() instanceof SwordItem
        && ((OldCombatPlayer) player).isSwordBlocking())
        {
            PoseStack ps = event.getPoseStack();
            boolean isMainHand = (event.getHand() == InteractionHand.MAIN_HAND);
            HumanoidArm arm = isMainHand ? player.getMainArm() : player.getMainArm().getOpposite();
            int horizontal = arm == HumanoidArm.RIGHT ? 1 : -1;
            ps.translate(horizontal * -0.14142136F, 0.4+0.08F, -0.5 + 0.14142136F);
            ps.mulPose(Axis.XP.rotationDegrees(-102.25F));
            ps.mulPose(Axis.YP.rotationDegrees(horizontal * 13.365F));
            ps.mulPose(Axis.ZP.rotationDegrees(horizontal * 78.05F));
            try
            {
                if (isMainHand && !IS_MAIN_BLOCKING)
                {
                    MAIN_HAND_HEIGHT.set(Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer(), 1);
                    O_MAIN_HAND_HEIGHT.set(Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer(), 1);
                    IS_MAIN_BLOCKING = true;
                }
                else if (!isMainHand && !IS_OFF_BLOCKING)
                {
                    OFF_HAND_HEIGHT.set(Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer(), 1);
                    O_OFF_HAND_HEIGHT.set(Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer(), 1);
                    IS_OFF_BLOCKING = true;
                }
            }
            catch (Exception ignored) {}
        }
    }
}
