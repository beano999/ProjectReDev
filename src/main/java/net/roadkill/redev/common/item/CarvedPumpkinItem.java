package net.roadkill.redev.common.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class CarvedPumpkinItem extends BlockItem
{
    private final int pumpkinType;
    private final boolean lit;

    public CarvedPumpkinItem(Block pBlock, Properties pProperties, int pumpkinType, boolean lit)
    {
        super(pBlock, pProperties);
        this.pumpkinType = pumpkinType;
        this.lit = lit;
    }

    public int getPumpkinType()
    {
        return pumpkinType;
    }

    public boolean isLit()
    {
        return lit;
    }
}
