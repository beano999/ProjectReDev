package net.roadkill.redev.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.roadkill.redev.util.registries.ModSounds;
import org.jetbrains.annotations.Nullable;

public class LithicanEntity extends Zombie
{
    private static final EntityDataAccessor<Boolean> ACTIVE = SynchedEntityData.defineId(LithicanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(LithicanEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HEAT = SynchedEntityData.defineId(LithicanEntity.class, EntityDataSerializers.INT);

    private int awakenDelay = -1;

    public LithicanEntity(EntityType<? extends Zombie> pEntityType, Level pLevel)
    {   super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData()
    {   super.defineSynchedData();
        this.entityData.define(ACTIVE, true);
        this.entityData.define(VARIANT, 0);
        this.entityData.define(HEAT, 0);
    }

    @Override
    public boolean isSunSensitive()
    {   return false;
    }

    @Override
    public boolean isUnderWaterConverting()
    {   return false;
    }

    public boolean isActive()
    {   return this.entityData.get(ACTIVE);
    }

    public void setActive(boolean active)
    {   this.entityData.set(ACTIVE, active);
    }

    public int getVariant()
    {   return this.entityData.get(VARIANT);
    }
    public void setVariant(int variant)
    {   this.entityData.set(VARIANT, variant);
    }

    public int getHeat()
    {   return this.entityData.get(HEAT);
    }
    public void setHeat(int heat)
    {   this.entityData.set(HEAT, heat);
    }

    @Override
    public void tick()
    {
        // Decrease heat gradually
        int heat = this.getHeat();
        if (heat > 0)
        {   this.setHeat(heat - 1);
            if (this.level.isClientSide && this.random.nextInt(100) < heat)
            {
                //spawn dripping lava particles within the entity's hitbox
                for (int i = 0; i < 2; i++)
                {   this.level.addParticle(ParticleTypes.FALLING_LAVA, this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5), 0, 0, 0);
                }
            }
        }

        // If lithican is scheduled to be awakened
        if (this.awakenDelay > 0)
        {
            this.awakenDelay--;
            if (this.awakenDelay == 0)
            {
                this.playBreakAnimation();
                this.setActive(true);
                this.playSound(SoundEvents.UI_STONECUTTER_TAKE_RESULT, 1, 1);
                for (WrappedGoal wrappedGoal : this.goalSelector.getAvailableGoals())
                {
                    if (wrappedGoal.getGoal() instanceof MeleeAttackGoal meleeAttackGoal)
                    {   meleeAttackGoal.canUse();
                        meleeAttackGoal.start();
                        break;
                    }
                }
                this.awakenDelay = -1;
            }
        }

        if (this.tickCount % 10 == 0 && !this.isActive() && this.random.nextInt(5) == 0)
        {
            for (Player player : this.level.players())
            {
                if (player.distanceTo(this) < 6 && !player.isCreative() && !player.isSpectator())
                {   this.setActive(true);
                    this.playBreakAnimation();
                    this.setTarget(player);
                    break;
                }
            }
        }
        super.tick();
    }

    @Override
    public void serverAiStep()
    {
        if (this.isActive())
        {   super.serverAiStep();
        }
    }

    @Override
    public void knockback(double strength, double x, double z)
    {   super.knockback(0, x, z);
    }

    @Override
    public void load(CompoundTag nbt)
    {   super.load(nbt);
        if (nbt.contains("Active"))
        {   this.setActive(nbt.getBoolean("Active"));
        }
        if (nbt.contains("Variant"))
        {   this.setVariant(nbt.getInt("Variant"));
        }
        if (nbt.contains("Heat"))
        {   this.setHeat(nbt.getInt("Heat"));
        }
    }

    @Override
    public boolean save(CompoundTag nbt)
    {
        boolean canSave = super.save(nbt);
        if (canSave)
        {   nbt.putBoolean("Active", this.isActive());
            nbt.putInt("Variant", this.getVariant());
            nbt.putInt("Heat", this.getHeat());
        }
        return canSave;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficultyInstance,
                                        MobSpawnType spawnReason, @Nullable SpawnGroupData spawnData,
                                        @Nullable CompoundTag nbt)
    {
        BlockState groundState = level.getBlockState(this.blockPosition().below());
        if (level.getBiome(this.blockPosition()).is(BiomeTags.HAS_DESERT_PYRAMID)
        || groundState.is(Tags.Blocks.SANDSTONE) || groundState.is(Tags.Blocks.SAND))
        {   this.setVariant(1);
        }
        else this.setVariant(0);
        return spawnData;
    }

    @Override
    public boolean isAffectedByPotions()
    {   return false;
    }

    @Override
    public boolean addEffect(MobEffectInstance pEffectInstance, @Nullable Entity pEntity)
    {
        return super.addEffect(pEffectInstance, pEntity);
    }

    @Override
    protected void tickEffects()
    {   this.getActiveEffects().clear();
        super.tickEffects();
    }

    @Override
    public boolean doHurtTarget(Entity target)
    {
        if (this.getHeat() > 20)
        {   target.invulnerableTime = 0;
            target.setSecondsOnFire(Math.max((int) Math.ceil(target.getRemainingFireTicks() / 20d), this.getHeat() / 20));
            target.hurt(this.damageSources().onFire(), 1f);
        }
        return super.doHurtTarget(target);
    }

    @Override
    public boolean isBaby()
    {   return false;
    }

    @Override
    public boolean hurt(DamageSource damageSource, float amount)
    {
        // Immune to arrows
        if (damageSource.getDirectEntity() instanceof Arrow arrow)
        {   this.setArrowCount(this.getArrowCount() + 1);
            this.level.playSound(null, this.blockPosition(), SoundEvents.ARROW_HIT, SoundSource.NEUTRAL, 1, 1);
            arrow.remove(RemovalReason.KILLED);
            return false;
        }
        else if (damageSource.is(DamageTypes.FALL) && amount > 5)
        {   amount = 999;
        }
        else if (damageSource.is(DamageTypes.FIREWORKS) || damageSource.is(DamageTypes.EXPLOSION))
        {   amount *= 3;
        }
        else if (damageSource.type().effects() == DamageEffects.BURNING)
        {   this.clearFire();
            this.setHeat(Math.min(100, this.getHeat() + (int) (amount * 2)));
            return false;
        }
        // Trigger the super method
        if (super.hurt(damageSource, amount))
        {
            // Make all nearby lithicans active if damage passes
            this.level.getEntitiesOfClass(LithicanEntity.class, this.getBoundingBox().inflate(16)).forEach(lithican ->
            {
                if (!lithican.isActive())
                {   lithican.wakeUp();
                }
            });
            if (!this.isActive())
            {   this.awakenDelay = 1;
            }
            this.playHitAnimation();
            return true;
        }
        return false;
    }

    @Override
    public SoundEvent getDeathSound()
    {   return ModSounds.LITHICAN_DEATH;
    }

    @Override
    public SoundEvent getAmbientSound()
    {   return ModSounds.LITHICAN_AMBIENT;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource pDamageSource)
    {   return ModSounds.LITHICAN_HURT;
    }

    @Override
    public SoundEvent getStepSound()
    {   return SoundEvents.GILDED_BLACKSTONE_PLACE;
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pBlock)
    {   this.playSound(this.getStepSound(), 0.3F, 0.5F);
    }

    @Override
    public void die(DamageSource damageSource)
    {   this.playBreakAnimation();
        super.die(damageSource);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit)
    {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        switch (this.getVariant())
        {
            case 0 -> this.spawnAtLocation(new ItemStack(Blocks.STONE, this.random.nextIntBetweenInclusive(1, 3 + pLooting)));
            case 1 -> this.spawnAtLocation(new ItemStack(Blocks.SANDSTONE, this.random.nextIntBetweenInclusive(1, 3 + pLooting)));
        }
    }

    private void playBreakAnimation()
    {
        if (!level.isClientSide)
        {   ((ServerLevel) this.level).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, this.getBlockForVariant()), this.getX(), this.getY() + this.getBbHeight() / 2, this.getZ(), 50, this.getBbWidth() / 2, this.getBbHeight() / 2, this.getBbWidth() / 2, 0.1);
        }
        this.playSound(SoundEvents.DECORATED_POT_SHATTER, 1, 0.7f);
    }

    private void playHitAnimation()
    {
        if (!level.isClientSide)
        {   ((ServerLevel) this.level).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, this.getBlockForVariant()), this.getX(), this.getY() + this.getBbHeight() / 2, this.getZ(), 10, this.getBbWidth() / 2, this.getBbHeight() / 4, this.getBbWidth() / 2, 0.1);
        }
        this.playSound(SoundEvents.DECORATED_POT_FALL, 1, 1f);
    }

    private void wakeUp()
    {   if (!this.level.isClientSide)
        {   this.awakenDelay = this.random.nextInt(5, 20);
        }
    }

    @Override
    public boolean isSilent()
    {   return super.isSilent() || !this.isActive();
    }

    public BlockState getBlockForVariant()
    {
        return switch (this.getVariant())
        {
            case 0 -> Blocks.STONE.defaultBlockState();
            default -> Blocks.SANDSTONE.defaultBlockState();
        };
    }
}
