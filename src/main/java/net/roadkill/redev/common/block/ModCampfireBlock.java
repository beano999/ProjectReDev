package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.roadkill.redev.common.block_entity.ModCampfireBlockEntity;
import net.roadkill.redev.core.init.BlockEntityInit;
import net.roadkill.redev.util.registries.ModBlocks;
import net.roadkill.redev.util.registries.ModItems;

import javax.annotation.Nullable;

public class ModCampfireBlock extends CampfireBlock
{
    public static final BooleanProperty CLEAN = BooleanProperty.create("clean");

    public ModCampfireBlock(boolean spawnParticles, int damage, Properties properties)
    {   super(spawnParticles, damage, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, true).setValue(SIGNAL_FIRE, false).setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH).setValue(CLEAN, false));
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

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext)
    {   BlockState state = super.getStateForPlacement(pContext);
        if (state != null) return state.setValue(CLEAN, false);
        else return null;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        ItemStack handItem = player.getItemInHand(hand);
        if (handItem.getItem() instanceof ShovelItem && !state.getValue(LIT) && !state.getValue(CLEAN))
        {
            if (!level.isClientSide)
            {   level.setBlockAndUpdate(pos, state.setValue(CLEAN, true));
                handItem.hurtAndBreak(1, player, (playerEntity) -> playerEntity.broadcastBreakEvent(hand));
                level.playSound(null, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        }
        else if (handItem.is(ItemTags.CREEPER_IGNITERS) && !state.getValue(LIT))
        {
            if (!level.isClientSide)
            {   level.setBlockAndUpdate(pos, state.setValue(LIT, true));
                if (handItem.isStackable())
                {   player.getItemInHand(hand);
                }
                else
                {   handItem.hurtAndBreak(1, player, (playerEntity) -> playerEntity.broadcastBreakEvent(hand));
                }
                level.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        }
        BlockState newState = level.getBlockState(pos);
        if (newState.is(this) && newState.getValue(LIT) && newState.getValue(CLEAN))
        {   level.setBlockAndUpdate(pos, newState.setValue(CLEAN, false));
        }
        return super.use(state, level, pos, player, hand, hitResult);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {   pBuilder.add(LIT, SIGNAL_FIRE, WATERLOGGED, FACING, CLEAN);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player)
    {   return state.is(ModBlocks.OAK_CAMPFIRE) ? Items.CAMPFIRE.getDefaultInstance() : super.getCloneItemStack(state, target, level, pos, player);
    }
}
