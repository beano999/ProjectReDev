package net.roadkill.redev.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class DurianBlock extends FallingBlock
{
    public DurianBlock(Properties pProperties)
    {   super(pProperties);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving)
    {
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random)
    {
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState neighbor, LevelAccessor level, BlockPos pos, BlockPos neighborDir)
    {   BlockState aboveBlock = level.getBlockState(pos.above());
        if (!(aboveBlock.isFaceSturdy(level, pos, Direction.DOWN, SupportType.CENTER) || aboveBlock.is(BlockTags.LEAVES)))
        {   return super.updateShape(state, facing, neighbor, level, pos, neighborDir);
        }
        return state;
    }

    @Override
    public void onLand(Level level, BlockPos pos, BlockState state, BlockState oldState, FallingBlockEntity fallingBlock)
    {
        if (fallingBlock.time > 10)
        {
            AreaEffectCloud areaEffectCloud = new AreaEffectCloud(level, fallingBlock.getX(), fallingBlock.getY(), fallingBlock.getZ());
            areaEffectCloud.setRadius(2.5F);
            areaEffectCloud.setPotion(new Potion(new MobEffectInstance(MobEffects.CONFUSION, 1000, 0)));
            areaEffectCloud.setDuration(400);
            areaEffectCloud.setFixedColor(7312189);
            level.addFreshEntity(areaEffectCloud);

            for (Entity entity : level.getEntities(null, fallingBlock.getBoundingBox()))
            {   entity.hurt(level.damageSources().fallingBlock(fallingBlock), Math.min(fallingBlock.time / 5, 6));
            }
            level.destroyBlock(pos, false);
            level.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1, 1);
        }
        super.onLand(level, pos, state, oldState, fallingBlock);
    }

    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult raytrace, Projectile projectile)
    {   level.scheduleTick(raytrace.getBlockPos(), state.getBlock(), 1);
    }
}
