package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RootsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.roadkill.redev.core.init.ItemInit;
import net.roadkill.redev.util.RDMath;
import net.roadkill.redev.util.registries.ModItems;

public class ModWarpedRootsBlock extends RootsBlock
{
    public static final BooleanProperty DRUPEL = BooleanProperty.create("drupel");
    public static final BooleanProperty HAS_GROWN_TODAY = BooleanProperty.create("has_grown_today");

    public ModWarpedRootsBlock(Properties properties)
    {   super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DRUPEL, false).setValue(HAS_GROWN_TODAY, false));
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState)
    {   return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        if (RDMath.withinRange(level.getDayTime(), 12000L, 24000L))
        {
            if (state.getValue(HAS_GROWN_TODAY))
            {   level.setBlock(pos, state.setValue(HAS_GROWN_TODAY, false), 2);
            }
        }
        else if (!state.getValue(HAS_GROWN_TODAY))
        {
            boolean passChance = random.nextInt(40) == 0;
            level.setBlock(pos, state.setValue(HAS_GROWN_TODAY, true).setValue(DRUPEL, passChance), 3);
            if (passChance)
            {
                level.sendParticles(ParticleTypes.REVERSE_PORTAL, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 20, 0.1D, 0.1D, 0.1D, 1D);
                level.playSound(null, pos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 0.5f, 2f);
            }
        }

        if (Math.random() < 1/50d && state.getValue(DRUPEL))
        {
            level.setBlock(pos, state.setValue(DRUPEL, false), 3);
            level.sendParticles(ParticleTypes.REVERSE_PORTAL, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 20, 0.1D, 0.1D, 0.1D, 1D);
            level.playSound(null, pos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 0.5f, 2f);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
    {
        if (state.getValue(DRUPEL))
        {
            level.setBlock(pos, state.setValue(DRUPEL, false), 3);

            ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, ItemInit.WARPED_DRUPEL.value().getDefaultInstance());
            itemEntity.setDeltaMovement((Math.random() - 0.5) * 0.2, 0.2, (Math.random() - 0.5) * 0.2);
            itemEntity.setPickUpDelay(10);
            level.addFreshEntity(itemEntity);

            level.playSound(null, pos, SoundEvents.HANGING_ROOTS_PLACE, SoundSource.BLOCKS, 0.5f, 2f);

            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player)
    {   return new ItemStack(Items.WARPED_ROOTS);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {   pBuilder.add(DRUPEL, HAS_GROWN_TODAY);
    }
}
