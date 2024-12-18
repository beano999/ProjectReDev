package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.Tags;
import net.roadkill.redev.core.init.BlockInit;

import javax.annotation.Nullable;

public class ModCampfireBlock extends CampfireBlock
{
    public static final BooleanProperty CLEAN = BooleanProperty.create("clean");

    public ModCampfireBlock(boolean spawnParticles, int damage, Properties properties)
    {   super(spawnParticles, damage, properties);
        this.registerDefaultState(this.stateDefinition.any()
                                          .setValue(LIT, true)
                                          .setValue(SIGNAL_FIRE, false)
                                          .setValue(WATERLOGGED, false)
                                          .setValue(FACING, Direction.NORTH)
                                          .setValue(CLEAN, false));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext)
    {   BlockState state = super.getStateForPlacement(pContext);
        if (state != null) return state.setValue(CLEAN, false);
        else return null;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        ItemStack handItem = player.getItemInHand(hand);
        if (handItem.getItem() instanceof ShovelItem && !state.getValue(LIT) && !state.getValue(CLEAN))
        {
            if (!level.isClientSide)
            {   level.setBlockAndUpdate(pos, state.setValue(CLEAN, true));
                handItem.hurtAndBreak(1, (ServerLevel) level, player, (playerEntity) -> {});
                level.playSound(null, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        }
        else if (handItem.is(Tags.Items.TOOLS_IGNITER) && !state.getValue(LIT))
        {
            if (!level.isClientSide)
            {   level.setBlockAndUpdate(pos, state.setValue(LIT, true));
                if (handItem.isStackable())
                {   player.getItemInHand(hand);
                }
                else
                {   handItem.hurtAndBreak(1, (ServerLevel) level, player, (playerEntity) -> {});
                }
                level.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        }
        BlockState newState = level.getBlockState(pos);
        if (newState.is(this) && newState.getValue(LIT) && newState.getValue(CLEAN))
        {   level.setBlockAndUpdate(pos, newState.setValue(CLEAN, false));
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {   pBuilder.add(LIT, SIGNAL_FIRE, WATERLOGGED, FACING, CLEAN);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player)
    {   return state.is(BlockInit.OAK_CAMPFIRE) ? Items.CAMPFIRE.getDefaultInstance() : super.getCloneItemStack(level, pos, state, includeData, player);
    }
}