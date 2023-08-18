package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.roadkill.redev.common.block_entity.ModCampfireBlockEntity;
import net.roadkill.redev.core.init.BlockEntityInit;

import javax.annotation.Nullable;

public class ModCampfireBlock extends CampfireBlock
{
    public ModCampfireBlock(boolean p_51236_, int damage, Properties properties)
    {   super(p_51236_, damage, properties);
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {   return new ModCampfireBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType)
    {
        if (level.isClientSide)
        {   return state.getValue(LIT) ? createTickerHelper(blockEntityType, BlockEntityInit.CAMPFIRE_BLOCK_ENTITY_TYPE.get(), ModCampfireBlockEntity::particleTick) : null;
        }
        else
        {   return state.getValue(LIT) ? createTickerHelper(blockEntityType, BlockEntityInit.CAMPFIRE_BLOCK_ENTITY_TYPE.get(), ModCampfireBlockEntity::cookTick)
                                       : createTickerHelper(blockEntityType, BlockEntityInit.CAMPFIRE_BLOCK_ENTITY_TYPE.get(), ModCampfireBlockEntity::cooldownTick);
        }
    }
}
