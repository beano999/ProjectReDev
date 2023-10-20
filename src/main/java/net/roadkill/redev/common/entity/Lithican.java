package net.roadkill.redev.common.entity;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class Lithican extends Zombie
{
    private static final EntityDataAccessor<Boolean> ACTIVE = SynchedEntityData.defineId(Lithican.class, EntityDataSerializers.BOOLEAN);

    public Lithican(EntityType<? extends Zombie> pEntityType, Level pLevel)
    {   super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData()
    {   super.defineSynchedData();
        this.entityData.define(ACTIVE, true);
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

    @Override
    public void knockback(double strength, double x, double z)
    {   super.knockback(0, x, z);
    }

    @Override
    public void load(CompoundTag pCompound)
    {   super.load(pCompound);
        this.setActive(!pCompound.contains("Active") || pCompound.getBoolean("Active"));
    }

    @Override
    public boolean save(CompoundTag pCompound)
    {
        boolean canSave = super.save(pCompound);
        if (canSave)
        {   pCompound.putBoolean("Active", this.isActive());
            pCompound.putBoolean("NoAI", this.isReallyNoAi());
        }
        return canSave;
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
    public boolean hurt(DamageSource pSource, float pAmount)
    {
        if (!this.isActive())
        {   this.setActive(true);
            if (pSource.getEntity() instanceof LivingEntity attacker)
            {   this.setTarget(attacker);
            }
            //spawn stone break particles
            for (int i = 0; i < 20; i++)
            {   this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.STONE.defaultBlockState()), this.getX() + this.random.nextDouble() - 0.5, this.getY() + this.random.nextDouble() * this.getBbHeight(), this.getZ() + this.random.nextDouble() - 0.5, 0, 0, 0);
            }
            this.level.playSound(null, this.blockPosition(), SoundEvents.STONE_BREAK, SoundSource.HOSTILE, 2, 0.7f);
        }
        this.level.getEntitiesOfClass(Lithican.class, this.getBoundingBox().inflate(16)).forEach(lithican -> lithican.setActive(true));
        return super.hurt(pSource, pAmount);
    }

    @Override
    public boolean isSilent()
    {   return super.isSilent() || !this.isActive();
    }

    @Override
    public boolean isNoAi()
    {   return !this.isActive() || super.isNoAi();
    }

    public boolean isReallyNoAi()
    {   return super.isNoAi();
    }
}
