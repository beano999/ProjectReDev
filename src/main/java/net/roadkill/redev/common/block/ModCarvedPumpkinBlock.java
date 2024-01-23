package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.roadkill.redev.common.item.CarvedPumpkinItem;
import net.roadkill.redev.core.init.ItemInit;

public class ModCarvedPumpkinBlock extends net.minecraft.world.level.block.CarvedPumpkinBlock
{
    public static final IntegerProperty PUMPKIN_TYPE = IntegerProperty.create(
            "pumpkin_type", 0, 7);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public ModCarvedPumpkinBlock(Properties pProperties)
    {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(PUMPKIN_TYPE, 0)
                .setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        pBuilder.add(PUMPKIN_TYPE, FACING, LIT);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        if(!pLevel.isClientSide())
        {
            if(pPlayer.getItemInHand(pHand).getItem() instanceof ShearsItem)
            {
                BlockState state = pState.setValue(PUMPKIN_TYPE,(pState.getValue(PUMPKIN_TYPE) + 1) % 8).setValue(FACING, pPlayer.getDirection().getOpposite());
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

    @Override
    public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState)
    {
        int pumpkinType = pState.getValue(PUMPKIN_TYPE);
        Boolean lit = pState.getValue(LIT);
        return switch(pumpkinType)
        {
            case 0 ->
            {
                if(lit)
                {
                    yield ItemInit.CARVED_PUMPKIN_LIT.get().getDefaultInstance();
                }
                yield ItemInit.CARVED_PUMPKIN.get().getDefaultInstance();

            }
            case 1 ->
            {
                if(lit)
                {
                    yield ItemInit.CARVED_PUMPKIN_CREEPY_LIT.get().getDefaultInstance();
                }
                yield ItemInit.CARVED_PUMPKIN_CREEPY.get().getDefaultInstance();
            }
            case 2 ->
            {
                if(lit)
                {
                    yield ItemInit.CARVED_PUMPKIN_HORRIFIED_LIT.get().getDefaultInstance();
                }
                yield ItemInit.CARVED_PUMPKIN_HORRIFIED.get().getDefaultInstance();
            }
            case 3 ->
            {
                if(lit)
                {
                    yield ItemInit.CARVED_PUMPKIN_CREEPER_LIT.get().getDefaultInstance();
                }
                yield ItemInit.CARVED_PUMPKIN_CREEPER.get().getDefaultInstance();
            }
            case 4 ->
            {
                if(lit)
                {
                    yield ItemInit.CARVED_PUMPKIN_SCOWLING_LIT.get().getDefaultInstance();
                }
                yield ItemInit.CARVED_PUMPKIN_SCOWLING.get().getDefaultInstance();
            }
            case 5 ->
            {
                if(lit)
                {
                    yield ItemInit.CARVED_PUMPKIN_TWISTED_LIT.get().getDefaultInstance();
                }
                yield ItemInit.CARVED_PUMPKIN_TWISTED.get().getDefaultInstance();
            }
            case 6 ->
            {
                if(lit)
                {
                    yield ItemInit.CARVED_PUMPKIN_FURIOUS_LIT.get().getDefaultInstance();
                }
                yield ItemInit.CARVED_PUMPKIN_FURIOUS.get().getDefaultInstance();
            }
            case 7 ->
            {
                if(lit)
                {
                    yield ItemInit.CARVED_PUMPKIN_ZOMBIE_LIT.get().getDefaultInstance();
                }
                yield ItemInit.CARVED_PUMPKIN_ZOMBIE.get().getDefaultInstance();
            }
            default -> {throw new RuntimeException("CUMON SUN!!");}
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext)
    {
        CarvedPumpkinItem item = (CarvedPumpkinItem) pContext.getItemInHand().getItem();
        return super.getStateForPlacement(pContext).setValue(PUMPKIN_TYPE, item.getPumpkinType()).setValue(LIT, item.isLit());
    }
}
