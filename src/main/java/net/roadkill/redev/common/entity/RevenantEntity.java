package net.roadkill.redev.common.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class RevenantEntity extends AbstractSkeleton
{
    public final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 2D, true)
    {
        public void start()
        {   super.start();
            RevenantEntity.this.setAggressive(true);
        }

        public void stop()
        {   super.stop();
            RevenantEntity.this.setAggressive(false);
        }
    };

    public RevenantEntity(EntityType<? extends AbstractSkeleton> entityType, Level level)
    {   super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes()
    {   return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    @Override
    public void tick()
    {
        super.tick();
        for (int i = 0; i < this.getDeltaMovement().horizontalDistance() * 20 + this.random.nextInt(2); ++i)
        {   double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level.addParticle(new DustParticleOptions(new Vector3f(0.4f, 0.7f, 1f), 1), this.getRandomX(1.0D), this.getRandomY(), this.getRandomZ(1.0D), d0, d1, d2);
        }
    }

    public void reassessWeaponGoal()
    {
        if (!this.level.isClientSide)
        {   this.goalSelector.removeGoal(this.bowGoal);
            ItemStack itemstack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof BowItem));

            if (itemstack.getItem() instanceof BowItem)
            {   int i = this.level.getDifficulty() == Difficulty.HARD ? 40 : 20;
                this.bowGoal.setMinAttackInterval(i);
                this.goalSelector.addGoal(4, this.bowGoal);
            }
            else
            {   this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 2D, true)
                {
                    public void start()
                    {   super.start();
                        RevenantEntity.this.setAggressive(true);
                    }

                    public void stop()
                    {   super.stop();
                        RevenantEntity.this.setAggressive(false);
                    }
                });
            }
        }
    }

    @Override
    public SoundEvent getStepSound()
    {   return SoundEvents.STRAY_STEP;
    }

    @Override
    protected boolean isSunBurnTick()
    {   return false;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource rand, DifficultyInstance pDifficulty)
    {
        if (rand.nextFloat() < 0.2F * (pDifficulty.getSpecialMultiplier() + 1))
        {
            for (EquipmentSlot equipmentslot : EquipmentSlot.values())
            {
                if (equipmentslot.getIndex() < 2) continue;
                if (rand.nextFloat() < 0.6F * (pDifficulty.getSpecialMultiplier() + 1))
                {
                    int armorValue = 3;
                    if (rand.nextFloat() < 0.3F)
                    {   --armorValue;
                    }
                    if (rand.nextFloat() < 0.3F)
                    {   ++armorValue;
                    }

                    if (equipmentslot.getType() == EquipmentSlot.Type.ARMOR)
                    {
                        if (this.getItemBySlot(equipmentslot).isEmpty())
                        {
                            Item item = getEquipmentForSlot(equipmentslot, armorValue);
                            if (item != null)
                            {   this.setItemSlot(equipmentslot, new ItemStack(item));
                            }
                        }
                    }
                }
            }
        }
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(this.random.nextInt(10) == 0 ? Items.DIAMOND_SWORD : Items.IRON_SWORD));
    }
}
