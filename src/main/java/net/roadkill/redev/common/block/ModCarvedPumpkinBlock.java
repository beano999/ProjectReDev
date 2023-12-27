package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ModCarvedPumpkinBlock extends net.minecraft.world.level.block.CarvedPumpkinBlock
{
    public static final IntegerProperty property = IntegerProperty.create(
            "pumpkin_type", 0, 7);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public ModCarvedPumpkinBlock(Properties pProperties)
    {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(property, 0)
                .setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        pBuilder.add(property, FACING, LIT);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        if(!pLevel.isClientSide())
        {
            if(pPlayer.getItemInHand(pHand).getItem() instanceof ShearsItem)
            {
                BlockState state = pState.setValue(property,(pState.getValue(property) + 1) % 8).setValue(FACING, pPlayer.getDirection().getOpposite());
                pLevel.setBlock(pPos, state, 3);
                pLevel.playSound(null, pPos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
            else if (pPlayer.getItemInHand(pHand).getItem() == Items.TORCH && !pState.getValue(ModCarvedPumpkinBlock.LIT))
            {
               BlockState state = pState.setValue(LIT, true);
               pLevel.setBlock(pPos, state, 3);
               pPlayer.getItemInHand(pHand).shrink(1);
               pLevel.playSound(null, pPos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS);
               return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
