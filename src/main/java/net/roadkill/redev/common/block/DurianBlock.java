package net.roadkill.redev.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ARGB;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.roadkill.redev.common.entity.DurianThornEntity;
import net.roadkill.redev.core.init.EntityInit;
import net.roadkill.redev.util.RDMath;

public class DurianBlock extends FallingBlock
{
    public static final MapCodec<DurianBlock> CODEC = simpleCodec(DurianBlock::new);

    public DurianBlock(Properties pProperties)
    {   super(pProperties);
    }

    @Override
    protected MapCodec<? extends FallingBlock> codec()
    {   return CODEC;
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
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos,
                                     Direction neighborDir, BlockPos neighborPos, BlockState neighborState, RandomSource random)
    {
        BlockState aboveBlock = level.getBlockState(pos.above());
        if (!(aboveBlock.isFaceSturdy(level, pos, Direction.DOWN, SupportType.CENTER) || aboveBlock.is(BlockTags.LEAVES)))
        {   return super.updateShape(state, level, tickAccess, pos, neighborDir, neighborPos, neighborState, random);
        }
        return state;
    }

    @Override
    public void onLand(Level level, BlockPos pos, BlockState state, BlockState oldState, FallingBlockEntity fallingBlock)
    {
        if (fallingBlock.time > 10)
        {
            // Spawn stink cloud
            AreaEffectCloud areaEffectCloud = new AreaEffectCloud(level, fallingBlock.getX(), fallingBlock.getY(), fallingBlock.getZ());
            areaEffectCloud.setRadius(2.5F);
            areaEffectCloud.setPotionContents(new PotionContents(Holder.direct(new Potion("nausea", new MobEffectInstance(MobEffects.CONFUSION, 1000, 0)))));
            areaEffectCloud.setDuration(100);
            areaEffectCloud.setParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, ARGB.opaque(7312189)));
            level.addFreshEntity(areaEffectCloud);

            // Shoot thorns in random directions
            int thornCount = level.random.nextIntBetweenInclusive(10, 16);
            for (int i = 0; i < thornCount; i++)
            {
                DurianThornEntity thorn = new DurianThornEntity(EntityInit.DURIAN_THORN.get(), level);
                thorn.setPos(fallingBlock.getX(), fallingBlock.getY(), fallingBlock.getZ());
                thorn.setBaseDamage(4);
                thorn.setSoundEvent(SoundEvents.ARROW_HIT);
                Vec3 motion = RDMath.randomVector3f(level.random, 0.5f);
                thorn.setDeltaMovement(new Vec3(motion.x, 0.3f, motion.y));
                level.addFreshEntity(thorn);
            }

            // Hurt entities directly below
            for (Entity entity : level.getEntities(null, fallingBlock.getBoundingBox()))
            {   entity.hurt(level.damageSources().fallingBlock(fallingBlock), Math.min(fallingBlock.time / 5, 6));
            }
            level.destroyBlock(pos, true);
            level.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1, 1);
        }
        super.onLand(level, pos, state, oldState, fallingBlock);
    }

    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult raytrace, Projectile projectile)
    {
        if (level.getBlockState(raytrace.getBlockPos().below()).isAir())
        {
            level.scheduleTick(raytrace.getBlockPos(), state.getBlock(), 1);
            if (projectile instanceof AbstractArrow arrow)
            {
                RDMath.dropItem(level, arrow.position(), arrow.getPickupItemStackOrigin().copy());
                projectile.remove(Entity.RemovalReason.KILLED);
            }
        }
    }
}
