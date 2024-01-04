package net.roadkill.redev.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.roadkill.redev.mixin_interfaces.IPigTNT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(Pig.class)
@Unique
public abstract class MixinPigTNT extends Mob implements IPigTNT
{
    private static final EntityDataAccessor<Boolean> HAS_TNT = SynchedEntityData.defineId(Pig.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> FUSE = SynchedEntityData.defineId(Pig.class, EntityDataSerializers.INT);

    protected MixinPigTNT(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    public void addData(CallbackInfo info)
    {   this.getEntityData().define(HAS_TNT, false);
        this.getEntityData().define(FUSE, -1);
    }

    @Inject(method = "mobInteract", at = @At("HEAD"))
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir)
    {
        if (this.isBaby()) return;

        // TNT is placed
        if (player.getItemInHand(hand).getItem() == Items.TNT && !(hasTNT()))
        {
            setHasTNT(true);
            player.swing(hand, true);
            if (!player.isCreative())
            {   player.getItemInHand(hand).shrink(1);
            }
            this.playSound(SoundEvents.GRASS_PLACE, 1f, 0.8f);
            this.playSound(SoundEvents.PIG_HURT, 1f, 1.3f);
            // Set the pose so a hitbox update event is triggered
            this.setPose(Pose.CROAKING);
        }
        // TNT is lit
        else if (hasTNT() && player.getItemInHand(hand).getItem() == Items.FLINT_AND_STEEL && player instanceof ServerPlayer)
        {
            player.swing(hand, true);
            setFuse(80);
            this.playSound(SoundEvents.FLINTANDSTEEL_USE);
            this.playSound(SoundEvents.TNT_PRIMED);
            this.playSound(SoundEvents.PIG_DEATH, 1f, 1.5f);
            player.getItemInHand(hand).hurtAndBreak(1, player, (playerIn) -> playerIn.broadcastBreakEvent(hand));
            // Make the pig panic
            Set<WrappedGoal> set = this.goalSelector.getAvailableGoals();
            for (WrappedGoal wrappedGoal : set)
            {
                if (wrappedGoal.getGoal() instanceof PanicGoal)
                {   this.setLastHurtByMob(player);
                    wrappedGoal.start();
                    break;
                }
            }
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addData(CompoundTag pCompound, CallbackInfo ci)
    {   pCompound.putBoolean("pig_has_tnt", hasTNT());
        pCompound.putInt("pig_fuse", getFuse());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readData(CompoundTag pCompound, CallbackInfo ci)
    {   setFuse(pCompound.getInt("pig_fuse"));
        setHasTNT(pCompound.getBoolean("pig_has_tnt"));
    }

    public boolean hasTNT()
    {   return this.getEntityData().get(HAS_TNT);
    }

    public void setHasTNT(boolean bool)
    {
        this.getEntityData().set(HAS_TNT, bool);
    }

    @Override
    public int getFuse()
    {
        return this.getEntityData().get(FUSE);
    }

    @Override
    public void setFuse(int fuse)
    {
        this.getEntityData().set(FUSE, fuse);
    }
}
