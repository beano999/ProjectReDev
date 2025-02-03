package net.roadkill.redev.util.registries;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.roadkill.redev.data.ModTags;

public class ModItems
{
    public static final class ToolMaterials
    {
        public static final ToolMaterial AURUM = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 300, 3.0F, 1f, 50, ModTags.Items.AURUM_REPAIR_MATERIALS);
        public static final ToolMaterial WITHERED = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 800, 9.0F, 2f, 18, ModTags.Items.WITHERED_REPAIR_MATERIALS);
        public static final ToolMaterial COPPER = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL,320 , 3.5F, 1f, 50, ModTags.Items.COPPER_REPAIR_MATERIALS);
    }
}