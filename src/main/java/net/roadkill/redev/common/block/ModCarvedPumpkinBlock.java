package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ModCarvedPumpkinBlock extends net.minecraft.world.level.block.CarvedPumpkinBlock
{
    public static final IntegerProperty property = IntegerProperty.create(
            "pumpkin_type", 0, 7);

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public ModCarvedPumpkinBlock(Properties pProperties)
    {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        this.registerDefaultState(this.defaultBlockState().setValue(property, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        pBuilder.add(property, FACING);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        if(!pLevel.isClientSide())
        {
            if(pPlayer.getItemInHand(pHand).getItem() instanceof ShearsItem)
            {
                BlockState state = pState.setValue(property,(pState.getValue(property) + 1) % 7).setValue(FACING, pPlayer.getDirection().getOpposite());
                pLevel.setBlock(pPos, state, 3);
                pPlayer.playSound(SoundEvents.PUMPKIN_CARVE, 1, 1);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
