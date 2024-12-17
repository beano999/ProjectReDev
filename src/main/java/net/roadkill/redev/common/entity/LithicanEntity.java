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
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.Tags;
import net.roadkill.redev.util.RDMath;
import net.roadkill.redev.util.registries.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class LithicanEntity extends Zombie
{
    private static final EntityDataAccessor<Boolean> ACTIVE = SynchedEntityData.defineId(LithicanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(LithicanEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> HEAT = SynchedEntityData.defineId(LithicanEntity.class, EntityDataSerializers.FLOAT);

    private int awakenDelay = -1;

    public LithicanEntity(EntityType<? extends Zombie> pEntityType, Level pLevel)
    {   super(pEntityType, pLevel);
        this.setPathfindingMalus(PathType.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, 0.0F);
    }

    public static AttributeSupplier.Builder createAttributes()
    {   return Zombie.createAttributes().add(Attributes.ATTACK_DAMAGE, 3).add(Attributes.ARMOR, 5).add(Attributes.KNOCKBACK_RESISTANCE, 1);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(ACTIVE, true);
        builder.define(VARIANT, 0);
        builder.define(HEAT, 0f);
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

    public Variant getVariant()
    {   return Variant.fromId(this.entityData.get(VARIANT));
    }
    public void setVariant(Variant variant)
    {   this.entityData.set(VARIANT, variant.getId());
    }

    public float getHeat()
    {   return this.entityData.get(HEAT);
    }
    public void setHeat(float heat)
    {   this.entityData.set(HEAT, heat);
    }

    @Override
    public void tick()
    {
        // Decrease heat gradually
        float heat = this.getHeat();
        if (heat > 0)
        {   this.setHeat(heat - 0.2f);
            if (this.level().isClientSide && this.random.nextInt(100) < heat)
            {
                //spawn dripping lava particles within the entity's hitbox
                for (int i = 0; i < 2; i++)
                {   this.level().addParticle(ParticleTypes.FALLING_LAVA, this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5), 0, 0, 0);
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
            for (Player player : this.level().players())
            {
                if (player.distanceTo(this) < 6 && !player.isCreative() && !player.isSpectator())
                {   this.setActive(true);
                    this.playBreakAnimation();
                    this.playSound(SoundEvents.UI_STONECUTTER_TAKE_RESULT, 1, 1);
                    this.setTarget(player);
                    break;
                }
            }
        }
        super.tick();
    }

    @Override
    public void load(CompoundTag nbt)
    {   super.load(nbt);
        if (nbt.contains("Active"))
        {   this.setActive(nbt.getBoolean("Active"));
        }
        if (nbt.contains("Variant"))
        {   this.setVariant(Variant.fromId(nbt.getInt("Variant")));
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
            nbt.putInt("Variant", this.getVariant().getId());
            nbt.putFloat("Heat", this.getHeat());
        }
        return canSave;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        EntitySpawnReason spawnReason, @Nullable SpawnGroupData spawnData)
    {
        BlockState groundState = level.getBlockState(this.blockPosition().below());
        if (level.getBiome(this.blockPosition()).is(BiomeTags.HAS_DESERT_PYRAMID)
        || groundState.is(Tags.Blocks.SANDSTONE_BLOCKS) || groundState.is(Tags.Blocks.SANDS))
        {   this.setVariant(Variant.SANDSTONE);
        }
        else if (groundState.is(BlockTags.DEEPSLATE_ORE_REPLACEABLES))
        {   this.setVariant(Variant.DEEPSLATE);
        }
        else if (groundState.is(Blocks.BASALT))
        {   this.setVariant(Variant.BASALT);
        }
        else this.setVariant(Variant.STONE);
        return spawnData;
    }

    @Override
    public boolean isAffectedByPotions()
    {   return false;
    }

    @Override
    public boolean addEffect(MobEffectInstance pEffectInstance, @Nullable Entity pEntity)
    {   return super.addEffect(pEffectInstance, pEntity);
    }

    @Override
    protected void tickEffects()
    {   this.getActiveEffects().clear();
        super.tickEffects();
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target)
    {
        if (this.getHeat() > 5)
        {
            target.invulnerableTime = 0;
            target.setRemainingFireTicks((int) Math.max(target.getRemainingFireTicks(), this.getHeat()));
            target.hurt(this.damageSources().onFire(), 1f);
        }
        return super.doHurtTarget(level, target);
    }

    @Override
    public boolean isBaby()
    {   return false;
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource damageSource, float amount)
    {
        // Immune to arrows
        if (damageSource.getDirectEntity() instanceof Arrow arrow)
        {
            this.setArrowCount(this.getArrowCount() + 1);
            level.playSound(null, this.blockPosition(), SoundEvents.ARROW_HIT, SoundSource.NEUTRAL, 1, 1);
            arrow.remove(RemovalReason.KILLED);
            return false;
        }
        else if ((damageSource.is(DamageTypes.MOB_ATTACK) || damageSource.is(DamageTypes.PLAYER_ATTACK))
        && Optional.ofNullable(damageSource.getWeaponItem()).map(weapon -> weapon.is(ItemTags.PICKAXES)).orElse(false))
        {   amount *= 2;
        }
        else if (damageSource.is(DamageTypes.FALL) && amount > 5)
        {   amount = 999;
        }
        else if (damageSource.is(DamageTypes.FIREWORKS) || damageSource.is(DamageTypes.EXPLOSION))
        {   amount *= 5;
        }
        else if (damageSource.type().effects() == DamageEffects.BURNING)
        {   this.clearFire();
            this.setHeat(Math.min(100, this.getHeat() + (int) (amount * 2)));
            return false;
        }
        // Trigger the super method
        if (super.hurtServer(level, damageSource, (float) RDMath.blend(amount, amount * 2, this.getHeat(), 0, 100)))
        {
            // Make all nearby lithicans active if damage passes
            level.getEntitiesOfClass(LithicanEntity.class, new AABB(this.blockPosition()).inflate(8)).forEach(lithican ->
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

    private static final SimpleWeightedRandomList<ItemStack> STONE_VARIANT_DROPS = SimpleWeightedRandomList.<ItemStack>builder()
            .add(new ItemStack(Items.STONE),       100)
            .add(new ItemStack(Items.COAL_ORE),    10)
            .add(new ItemStack(Items.IRON_ORE),    5)
            .add(new ItemStack(Items.COPPER_ORE),  5)
            .add(new ItemStack(Items.GOLD_ORE),    3)
            .add(new ItemStack(Items.DIAMOND_ORE), 1)
            .add(new ItemStack(Items.EMERALD_ORE), 1)
            .build();

    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean recentlyHit)
    {
        ItemStack drop = switch (this.getVariant())
        {
            case SANDSTONE -> new ItemStack(Items.SANDSTONE);
            case DEEPSLATE -> new ItemStack(Items.DEEPSLATE);
            case BASALT    -> new ItemStack(Items.BASALT);
            default -> STONE_VARIANT_DROPS.getRandomValue(this.random).orElse(ItemStack.EMPTY);
        };
        if (!drop.isEmpty() && this.level() instanceof ServerLevel serverLevel)
        {   this.spawnAtLocation(serverLevel, drop);
        }
        super.dropCustomDeathLoot(level, damageSource, recentlyHit);
    }

    private void playBreakAnimation()
    {
        if (!level().isClientSide)
        {   ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, this.getBlockForVariant()), this.getX(), this.getY() + this.getBbHeight() / 2, this.getZ(), 50, this.getBbWidth() / 2, this.getBbHeight() / 2, this.getBbWidth() / 2, 0.1);
        }
        this.playSound(SoundEvents.DECORATED_POT_SHATTER, 1, 0.7f);
    }

    private void playHitAnimation()
    {
        if (!level().isClientSide)
        {   ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, this.getBlockForVariant()), this.getX(), this.getY() + this.getBbHeight() / 2, this.getZ(), 10, this.getBbWidth() / 2, this.getBbHeight() / 4, this.getBbWidth() / 2, 0.1);
        }
        this.playSound(SoundEvents.DECORATED_POT_FALL, 1, 1f);
    }

    private void wakeUp()
    {   if (!this.level().isClientSide)
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
            case SANDSTONE -> Blocks.SANDSTONE.defaultBlockState();
            case DEEPSLATE -> Blocks.DEEPSLATE.defaultBlockState();
            case BASALT    -> Blocks.BASALT.defaultBlockState();
            default -> Blocks.STONE.defaultBlockState();
        };
    }

    public enum Variant
    {
        STONE(0),
        SANDSTONE(1),
        DEEPSLATE(2),
        BASALT(3);

        private final int id;

        Variant(int id)
        {   this.id = id;
        }

        public int getId()
        {   return this.id;
        }

        public static Variant fromId(int id)
        {
            return switch (id)
            {
                case 1 -> SANDSTONE;
                case 2 -> DEEPSLATE;
                case 3 -> BASALT;
                default -> STONE;
            };
        }
    }
}
