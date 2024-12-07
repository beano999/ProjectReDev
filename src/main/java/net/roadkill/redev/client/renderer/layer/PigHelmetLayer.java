package net.roadkill.redev.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmorStandArmorModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.Equippable;
import net.roadkill.redev.mixin_interfaces.IPig;
import net.roadkill.redev.util.RDMath;

public class PigHelmetLayer<S extends LivingEntityRenderState, M extends EntityModel<S>> extends RenderLayer<S, M>
{
    private final EquipmentLayerRenderer equipmentRenderer;

    public PigHelmetLayer(RenderLayerParent<S, M> pRenderer, EntityRendererProvider.Context context)
    {
        super(pRenderer);
        this.equipmentRenderer = context.getEquipmentRenderer();
    }

    @Override
    public void render(PoseStack ps, MultiBufferSource buffer, int packedLight, S renderState, float p_117353_, float p_117354_)
    {
        ItemStackRenderState helmet = renderState.headItem;
        if (!helmet.isEmpty())
        {
            ps.pushPose();
            float yRot = RDMath.toRadians(renderState.yRot);
            float xRot = RDMath.toRadians(renderState.xRot);
            ps.translate(0, 0.75F, -0.375F);
            ps.mulPose(RDMath.getQuaternion(xRot, yRot, 0));
            ps.translate(0, 0.175f, -0.25F);
            ps.scale(1F, 1F, 1F);

            HumanoidModel<?> armorModel = new ArmorStandArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ARMOR_STAND_OUTER_ARMOR));
            armorModel.setAllVisible(false);
            armorModel.head.visible = true;

            this.renderArmorPiece(ps, buffer, ((IPig) renderState).getHelmet(), EquipmentSlot.HEAD, packedLight, armorModel);
            ps.popPose();
        }
    }

    private void renderArmorPiece(PoseStack ps, MultiBufferSource buffer, ItemStack armor, EquipmentSlot slot, int packedLight, HumanoidModel<?> armorModel)
    {
        Equippable equippable = armor.get(DataComponents.EQUIPPABLE);
        if (equippable != null && shouldRender(equippable, slot))
        {
            ResourceKey<EquipmentAsset> resourcelocation = equippable.assetId().orElseThrow();
            EquipmentClientInfo.LayerType equipmentmodel$layertype = EquipmentClientInfo.LayerType.HUMANOID;
            this.equipmentRenderer.renderLayers(equipmentmodel$layertype, resourcelocation, armorModel, armor, ps, buffer, packedLight);
        }
    }

    private static boolean shouldRender(Equippable equipment, EquipmentSlot slot)
    {   return equipment.assetId().isPresent() && equipment.slot() == slot;
    }
}
