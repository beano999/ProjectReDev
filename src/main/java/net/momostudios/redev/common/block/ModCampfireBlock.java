package net.momostudios.redev.common.block;

import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.momostudios.redev.common.block_entity.ModCampfireBlockEntity;
import net.momostudios.redev.core.init.BlockEntityInit;

import javax.annotation.Nullable;

public class ModCampfireBlock extends CampfireBlock
{
    public ModCampfireBlock(boolean p_51236_, int p_51237_, Properties p_51238_)
    {   super(p_51236_, p_51237_, p_51238_);
    }

    public BlockEntity newBlockEntity(BlockPos p_152759_, BlockState p_152760_) {
        return new ModCampfireBlockEntity(p_152759_, p_152760_);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152755_, BlockState p_152756_, BlockEntityType<T> p_152757_) {
        if (p_152755_.isClientSide)
        {   return p_152756_.getValue(LIT) ? createTickerHelper(p_152757_, BlockEntityInit.CAMPFIRE_BLOCK_ENTITY_TYPE.get(), ModCampfireBlockEntity::particleTick) : null;
        }
        else
        {   return p_152756_.getValue(LIT) ? createTickerHelper(p_152757_, BlockEntityInit.CAMPFIRE_BLOCK_ENTITY_TYPE.get(), ModCampfireBlockEntity::cookTick)
                                           : createTickerHelper(p_152757_, BlockEntityInit.CAMPFIRE_BLOCK_ENTITY_TYPE.get(), ModCampfireBlockEntity::cooldownTick);
        }
    }
}
