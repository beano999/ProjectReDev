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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.roadkill.redev.common.item.CarvedPumpkinItem;
import net.roadkill.redev.core.init.ItemInit;

public class ModCarvedPumpkinBlock extends net.minecraft.world.level.block.CarvedPumpkinBlock
{
    public static final IntegerProperty PUMPKIN_TYPE = IntegerProperty.create(
            "pumpkin_type", 0, 7);
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
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
    protected InteractionResult useItemOn(ItemStack stack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if(!level.isClientSide())
        {
            if(player.getItemInHand(hand).getItem() instanceof ShearsItem)
            {
                BlockState state = blockState.setValue(PUMPKIN_TYPE,(blockState.getValue(PUMPKIN_TYPE) + 1) % 8).setValue(FACING, player.getDirection().getOpposite());
                level.setBlock(pos, state, 3);
                level.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
            else if (player.getItemInHand(hand).getItem() == Items.TORCH && !blockState.getValue(ModCarvedPumpkinBlock.LIT))
            {
                BlockState state = blockState.setValue(LIT, true);
                level.setBlock(pos, state, 3);
                player.getItemInHand(hand).shrink(1);
                level.playSound(null, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
        }
        return super.useItemOn(stack, blockState, level, pos, player, hand, hitResult);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player)
    {
        int pumpkinType = state.getValue(PUMPKIN_TYPE);
        Boolean lit = state.getValue(LIT);

        return (switch (pumpkinType)
        {
            case 0 -> lit ? ItemInit.CARVED_PUMPKIN_LIT
                          : ItemInit.CARVED_PUMPKIN;

            case 1 -> lit ? ItemInit.CARVED_PUMPKIN_CREEPY_LIT
                          : ItemInit.CARVED_PUMPKIN_CREEPY;

            case 2 -> lit ? ItemInit.CARVED_PUMPKIN_HORRIFIED_LIT
                          : ItemInit.CARVED_PUMPKIN_HORRIFIED;

            case 3 -> lit ? ItemInit.CARVED_PUMPKIN_CREEPER_LIT
                          : ItemInit.CARVED_PUMPKIN_CREEPER;

            case 4 -> lit ? ItemInit.CARVED_PUMPKIN_SCOWLING_LIT
                          : ItemInit.CARVED_PUMPKIN_SCOWLING;

            case 5 -> lit ? ItemInit.CARVED_PUMPKIN_TWISTED_LIT
                          : ItemInit.CARVED_PUMPKIN_TWISTED;

            case 6 -> lit ? ItemInit.CARVED_PUMPKIN_FURIOUS_LIT
                          : ItemInit.CARVED_PUMPKIN_FURIOUS;

            case 7 -> lit ? ItemInit.CARVED_PUMPKIN_ZOMBIE_LIT
                          : ItemInit.CARVED_PUMPKIN_ZOMBIE;

            default -> throw new RuntimeException("CUMON SUN!!");
        }).get().getDefaultInstance();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext)
    {
        CarvedPumpkinItem item = (CarvedPumpkinItem) pContext.getItemInHand().getItem();
        return super.getStateForPlacement(pContext).setValue(PUMPKIN_TYPE, item.getPumpkinType()).setValue(LIT, item.isLit());
    }
}
