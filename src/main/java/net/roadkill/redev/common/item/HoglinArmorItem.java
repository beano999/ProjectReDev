package net.roadkill.redev.common.item;

import net.minecraft.client.model.Model;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.roadkill.redev.client.event.RegisterEntityRenderers;

public class HoglinArmorItem extends ArmorItem
{
    public HoglinArmorItem(ArmorMaterial material, ArmorType armorType, Properties properties)
    {   super(material, armorType, properties);
    }

    public static final IClientItemExtensions CLIENT_ITEM_EXTENSIONS = new IClientItemExtensions()
    {
        @Override
        public Model getHumanoidArmorModel(ItemStack itemStack, EquipmentClientInfo.LayerType layerType, Model original)
        {
            RegisterEntityRenderers.checkForInitModels();
            if (layerType == EquipmentClientInfo.LayerType.HUMANOID)
            {
                Equippable equippable = itemStack.get(DataComponents.EQUIPPABLE);
                if (equippable == null)
                {   return IClientItemExtensions.super.getHumanoidArmorModel(itemStack, layerType, original);
                }
                return switch (equippable.slot())
                {
                    case HEAD -> RegisterEntityRenderers.HOGLIN_HIDE_HEAD_MODEL;
                    case CHEST -> RegisterEntityRenderers.HOGLIN_HIDE_CLOAK_MODEL;
                    case FEET -> RegisterEntityRenderers.HOGLIN_HIDE_HOOVES_MODEL;
                    default -> IClientItemExtensions.super.getHumanoidArmorModel(itemStack, layerType, original);
                };
            }
            return IClientItemExtensions.super.getHumanoidArmorModel(itemStack, layerType, original);
        }
    };
}
