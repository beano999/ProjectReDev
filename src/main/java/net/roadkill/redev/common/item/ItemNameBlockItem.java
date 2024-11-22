package net.roadkill.redev.common.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class ItemNameBlockItem extends BlockItem
{
    public ItemNameBlockItem(Block block, Properties properties)
    {   super(block, properties.useBlockDescriptionPrefix());
    }
}
