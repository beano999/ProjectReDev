package net.roadkill.redev.common.entity;

// import java.lang.module.ModuleDescriptor.Builder;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SyncedDataHolder;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType; import net.minecraft.world.entity.Mob; import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PlayerRideable;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier; import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.server.commands.data.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel; import net.minecraft.sounds.SoundEvents; import net.minecraft.sounds.SoundSource; import net.minecraft.world.entity.player.Player; import net.minecraft.world.level.Level;

public class Illusion extends Entity {
    // Transformation settings
private final int changeTimer = 9000; // number of ticks until forced transformation (e.g. 9000 ticks ~7.5 minutes)
private int ticksExistedIllusion = 0;
private boolean changeOnHit = true;

private Entity currentEntity = null;


private Level place;

// Flags indicating the behavior of the underlying (mimicked) entity
private boolean isNonHurtable;  // For entities that normally cannot be damaged (boats, minecarts, falling blocks)
private boolean isRideable;     // For entities that can be ridden (horses, boats, minecarts)
// Chance for ride-triggered transformation (15% chance per tick when ridden)
private final float rideTransformChance = 0.15f;

private final EntityType<?> acceptableGDamageSource = EntityType.PLAYER;

// The target entity type to transform into
private Entity targetEntityType;

public Illusion(EntityType<? extends Entity> type, Level level) {
    this(type, level, null, null, null);
}

public Illusion(EntityType<? extends Entity> type, Level level, Entity targetType, Boolean nonHurtable, Boolean rideable) {
    super(type, level);
    this.targetEntityType = targetType;
    this.isNonHurtable = nonHurtable != null ? nonHurtable : determineNonHurtable(targetType);
    this.isRideable = rideable != null ? rideable : determineRideable(targetType);
    this.place = (ServerLevel) level;
    // SynchedEntityData.Builder illusionData;
    // illusionData = new SynchedEntityData.Builder(this);
    this.currentEntity = this;
}

private boolean determineNonHurtable(Entity entityType) {
    // Add logic to determine if the entity type is non-hurtable
    // Example: boats, minecarts, falling blocks, etc.
    return !(entityType instanceof LivingEntity);
}

private boolean determineRideable(Entity entityType) {
    // Add logic to determine if the entity type is rideable
    // Example: horses, boats, minecarts, etc.
    return (entityType instanceof PlayerRideable);
}

// public static AttributeSupplier.Builder createAttributes() {
//     // Minimal attributes; these can be adjusted based on your needs or the target entity.
//     return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0D).add(Attributes.MOVEMENT_SPEED, 0.0D);
// }

// private AttributeSupplier.Builder getAttributesFromMob(EntityType<?> entityType) {
//     if (this instanceof Mob) {
//         return Mob.createMobAttributes()
//                 .add(Attributes.MAX_HEALTH, 20.0D)
//                 .add(Attributes.MOVEMENT_SPEED, 0.25D)
//                 .add(Attributes.ATTACK_DAMAGE, 5.0D);
//     }
//     return createAttributes();
// }

// @Override
// protected void redefineSynchedData(SynchedEntityData.Builder builder) {
//     // Define any synched data if needed.
//     if (LivingEntity.class.isAssignableFrom(this.getClass())) {
//         defineSynchedData(builder);
//     }
// }

@Override
public void tick() {
    super.tick();
    ticksExistedIllusion++;
    
    // Forced transformation after the timer expires.
    if (ticksExistedIllusion >= changeTimer) {
        commitTransformation();
    }
    
    // If this is a rideable entity and there is a passenger (a player),
    // there’s a 15% chance per tick to transform immediately.
    if (isRideable && !this.place.isClientSide && !this.getPassengers().isEmpty()) {
        for (Entity passenger : this.getPassengers()) {
            if (passenger instanceof Player) {
                if (this.random.nextFloat() < rideTransformChance) {
                    commitTransformation();
                    break;
                }
            }
        }
    }
}

@Override
protected void addAdditionalSaveData(CompoundTag compound) {
    // Add any additional data to save here
    compound.putInt("ticksExistedIllusion", this.ticksExistedIllusion);
    compound.putBoolean("changeOnHit", this.changeOnHit);
    compound.putBoolean("isNonHurtable", this.isNonHurtable);
    compound.putBoolean("isRideable", this.isRideable);
    compound.putUUID("currentEntity", currentEntity.getUUID());
    compound.putUUID("targetEntityType", targetEntityType.getUUID());
}

@Override
protected void readAdditionalSaveData(CompoundTag arg0) {
    // Read any additional data from save here
    this.ticksExistedIllusion = arg0.getInt("ticksExistedIllusion");
    this.changeOnHit = arg0.getBoolean("changeOnHit");
    this.isNonHurtable = arg0.getBoolean("isNonHurtable");
    this.isRideable = arg0.getBoolean("isRideable");
    this.currentEntity = ((ServerLevel) this.place).getEntity(arg0.getUUID("currentEntity"));
    this.targetEntityType = ((ServerLevel) this.place).getEntity(arg0.getUUID("targetEntityType"));    
}

protected void defineSynchedData(SynchedEntityData.Builder builder) {
 }

@Override
public boolean hurtServer(ServerLevel l, DamageSource source, float amount) {
    // For non-hurtable mobs (like boats, minecarts, falling blocks), ignore standard damage.
    if (isNonHurtable) {
        return false;
    }
    // For hurtable mobs, if change-on-hit is enabled, transform immediately.
    if (changeOnHit && !this.place.isClientSide && (source.getEntity() instanceof Player || source.getDirectEntity() instanceof Player)) {
        commitTransformation();
        return true;
    }
    return basicHurt(source, amount);
}

private boolean basicHurt(DamageSource source, float amount) {
    super.hurt(source, amount);
    return true;
}

@Override
public void remove(RemovalReason reason) {
    // For non-hurtable entities, if they are removed (i.e. “destroyed”) we want to commit the transformation.
    if (isNonHurtable && reason == RemovalReason.KILLED && !this.place.isClientSide) {
        commitTransformation();
    }
    // For any entity, we also prevent standard drops if desired.
    super.remove(reason);
}

// @Override
// public void onRemoval() {
//     // For non-hurtable entities, if they are removed (i.e. “destroyed”) we want to commit the transformation.
//     if (isNonHurtable && !this.place.isClientSide) {
//         commitTransformation();
//     }
//     // For any entity, we also prevent standard drops if desired.
//     super.onRemoval();
// }

// Override drop methods so that no extra loot or placement occurs.
// @Override
// protected void dropEquipment() {
//     // Prevent any equipment drops.
// }

// @Override
// protected void dropFromLootTable(DamageSource damageSource, boolean attackedRecently) {
//     // Prevent standard mob drops.
// }

/**
 * commitTransformation spawns the true entity and removes this illusion.
 */
private void commitTransformation() {
    if (!this.place.isClientSide) {
        // Optionally, display particle effects and play a sound.
        ((ServerLevel)this.place).sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY() + this.getBbHeight()/2, this.getZ(),
                20, 0.5, 0.5, 0.5, 0.01);
        this.playSound(SoundEvents.PHANTOM_BITE, 1.0F, 1.0F);
        
        // Create the target “true form” entity.
        Entity trueForm = targetEntityType.getType().create(this.place, EntitySpawnReason.MOB_SUMMONED);
        if (trueForm != null) {
            trueForm.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
            this.place.addFreshEntity(trueForm);
        }
        // Remove this illusion.
        this.discard();
    }
}
}
