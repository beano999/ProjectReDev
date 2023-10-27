package net.roadkill.redev.common.entity;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import org.jetbrains.annotations.Nullable;

public class Lithican extends Zombie
{
    private static final EntityDataAccessor<Boolean> ACTIVE = SynchedEntityData.defineId(Lithican.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Lithican.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HEAT = SynchedEntityData.defineId(Lithican.class, EntityDataSerializers.INT);

    public Lithican(EntityType<? extends Zombie> pEntityType, Level pLevel)
    {   super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData()
    {   super.defineSynchedData();
        this.entityData.define(ACTIVE, true);
        this.entityData.define(VARIANT, this.random.nextInt(0, 2));
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
        super.tick();
        int heat = this.getHeat();
        if (this.getHeat() > 0)
        {   this.setHeat(heat - 1);
        }
    }

    @Override
    public void knockback(double strength, double x, double z)
    {   super.knockback(0, x, z);
    }

    @Override
    public void load(CompoundTag nbt)
    {   super.load(nbt);
        this.setActive(!nbt.contains("Active") || nbt.getBoolean("Active"));
        this.setVariant(nbt.contains("Variant") ? nbt.getInt("Variant") : this.random.nextInt(0, 2));
        this.setHeat(nbt.contains("Heat") ? nbt.getInt("Heat") : 0);
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
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty,
                                        MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData,
                                        @Nullable CompoundTag pDataTag)
    {   return pSpawnData;
    }

    @Override
    public boolean isAffectedByPotions()
    {   return false;
    }

    @Override
    protected void tickEffects()
    {   this.getActiveEffects().clear();
        super.tickEffects();
    }

    @Override
    protected void doAutoAttackOnTouch(LivingEntity target)
    {
        if (this.getHeat() > 20)
        {   target.setSecondsOnFire(this.getHeat() / 20);
        }
        super.doAutoAttackOnTouch(target);
    }

    @Override
    public boolean isBaby()
    {   return false;
    }

    @Override
    public boolean hurt(DamageSource damageSource, float amount)
    {
        if (!this.isActive())
        {   this.setActive(true);
            if (damageSource.getEntity() instanceof LivingEntity attacker)
            {   this.setTarget(attacker);
            }
            playBreakAnimation();
        }
        this.level.getEntitiesOfClass(Lithican.class, this.getBoundingBox().inflate(16)).forEach(lithican -> lithican.setActive(true));
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
        {   amount *= 5;
        }
        else if (damageSource.is(DamageTypes.IN_FIRE) || damageSource.is(DamageTypes.ON_FIRE) || damageSource.is(DamageTypes.LAVA))
        {   this.clearFire();
            this.setHeat(Math.min(100, this.getHeat() + (int) (amount * 2)));
            return false;
        }
        return super.hurt(damageSource, amount);
    }

    @Override
    public void die(DamageSource damageSource)
    {   this.playBreakAnimation();
        super.die(damageSource);
    }

    private void playBreakAnimation()
    {
        for (int i = 0; i < 40; i++)
        {   this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, this.getBlockForVariant()), this.getX() + this.random.nextDouble() - 0.5, this.getY() + this.random.nextDouble() * this.getBbHeight(), this.getZ() + this.random.nextDouble() - 0.5, 0, 0, 0);
        }
        this.level.playSound(null, this.blockPosition(), SoundEvents.STONE_BREAK, SoundSource.HOSTILE, 2, 0.7f);
    }

    @Override
    public boolean isSilent()
    {   return super.isSilent() || !this.isActive();
    }

    @Override
    public boolean isNoAi()
    {   return !this.isActive() || super.isNoAi();
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
