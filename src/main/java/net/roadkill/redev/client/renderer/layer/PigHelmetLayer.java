package net.roadkill.redev.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmorStandArmorModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.roadkill.redev.mixin_interfaces.IPig;
import net.roadkill.redev.util.RDMath;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Map;

public class PigHelmetLayer<T extends Entity, M extends EntityModel<T>> extends RenderLayer<T, M>
{

    public PigHelmetLayer (RenderLayerParent<T, M> pRenderer)
    {
        super(pRenderer);
    }

    @Override
    public void render (PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing,
                       float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch)
    {
        if(pLivingEntity instanceof Pig pig)
        {
            IPig iPig = ((IPig) pig);
            ItemStack potentialHelmet = iPig.getHelmet();
            if(!potentialHelmet.isEmpty())
            {
                pPoseStack.pushPose();
                float xRot = RDMath.toRadians(pLivingEntity.getViewXRot(pPartialTick));
                float yRot = RDMath.toRadians(pLivingEntity.getViewYRot(pPartialTick));
                pPoseStack.mulPose(RDMath.getQuaternion(xRot, yRot, 0));
                //Axis.YP.rotationDegrees(-pLivingEntity.getViewYRot(pPartialTick))
                //pPoseStack.translate(0, -.3F, -.6F);
                //pPoseStack.mulPose(Axis.XP.rotationDegrees(-pLivingEntity.getViewXRot(pPartialTick)));
               // pPoseStack.scale(1.25F, 1.25F, 1.25F);

                renderArmorPiece(pPoseStack, pBuffer, pLivingEntity, EquipmentSlot.HEAD, pPackedLight,
                        new ArmorStandArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ARMOR_STAND_OUTER_ARMOR)), potentialHelmet);
                pPoseStack.popPose();
            }
        }
    }

    private void renderArmorPiece(PoseStack pPoseStack, MultiBufferSource pBuffer, T pLivingEntity, EquipmentSlot pSlot, int pPackedLight, HumanoidModel<?> pModel, ItemStack armor) {
        Item $$9 = armor.getItem();
        if ($$9 instanceof ArmorItem armoritem) {
            if (armoritem.getEquipmentSlot() == EquipmentSlot.HEAD) {
                this.getParentModel().copyPropertiesTo((EntityModel<T>) pModel);

                net.minecraft.client.model.Model model = ForgeHooksClient.getArmorModel((LivingEntity) pLivingEntity, armor, EquipmentSlot.HEAD,
                        new ArmorStandArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ARMOR_STAND_OUTER_ARMOR)));
                boolean flag1 = false;
                boolean flag = armor.hasFoil();
                if (armoritem instanceof net.minecraft.world.item.DyeableLeatherItem) {
                    int i = ((net.minecraft.world.item.DyeableLeatherItem)armoritem).getColor(armor);
                    float f = (float)(i >> 16 & 255) / 255.0F;
                    float f1 = (float)(i >> 8 & 255) / 255.0F;
                    float f2 = (float)(i & 255) / 255.0F;
                    this.renderModel(pPoseStack, pBuffer, pPackedLight, flag, model, f, f1, f2, this.getArmorResource(pLivingEntity, armor, pSlot, null));;
                    this.renderModel(pPoseStack, pBuffer, pPackedLight, flag, model, 1.0F, 1.0F, 1.0F, this.getArmorResource(pLivingEntity, armor, pSlot, "overlay"));
                } else {
                    this.renderModel(pPoseStack, pBuffer, pPackedLight, flag, model, 1.0F, 1.0F, 1.0F, this.getArmorResource(pLivingEntity, armor, pSlot, null));
                }
            }
        }
    }

    private void renderModel(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, boolean pWithGlint, net.minecraft.client.model.Model pModel, float pRed, float pGreen, float pBlue, ResourceLocation armorResource) {
        VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(pBuffer, RenderType.armorCutoutNoCull(armorResource), false, pWithGlint);
        pModel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, pRed, pGreen, pBlue, 1.0F);
    }

    public ResourceLocation getArmorResource(net.minecraft.world.entity.Entity entity, ItemStack stack, EquipmentSlot slot, @Nullable String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        String texture = item.getMaterial().getName();
        String domain = "minecraft";
        int idx = texture.indexOf(':');
        if (idx != -1) {
            domain = texture.substring(0, idx);
            texture = texture.substring(idx + 1);
        }
        String s1 = String.format(java.util.Locale.ROOT, "%s:textures/models/armor/%s_layer_%d%s.png", domain, texture, 1, type == null ? "" : String.format(java.util.Locale.ROOT, "_%s", type));

        s1 = net.minecraftforge.client.ForgeHooksClient.getArmorTexture(entity, stack, s1, slot, type);

        ResourceLocation resourcelocation = null;
        try
        {
            resourcelocation = ((Map<String, ResourceLocation>) ARMOR_LOCATION_CACHE.get(null)).get(s1);

            if (resourcelocation == null) {
                resourcelocation = new ResourceLocation(s1);
                ((Map<String, ResourceLocation>) ARMOR_LOCATION_CACHE.get(null)).put(s1, resourcelocation);

            }
        }
        catch (Exception e) {}

        return resourcelocation;
    }

    private static Field ARMOR_LOCATION_CACHE = ObfuscationReflectionHelper.findField(HumanoidArmorLayer.class, "f_117070_");
}
