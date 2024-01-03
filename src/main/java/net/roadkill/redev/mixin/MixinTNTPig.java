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
import net.minecraft.world.entity.ItemBasedSteering;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.roadkill.redev.mixin_interfaces.IPigTNT;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(Pig.class)
@Unique
public abstract class MixinTNTPig extends Mob implements IPigTNT
{
    @Shadow @Final private ItemBasedSteering steering;

    @Shadow public abstract void addAdditionalSaveData(CompoundTag pCompound);

    private static final EntityDataAccessor<Boolean> HAS_TNT = SynchedEntityData.defineId(Pig.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> FUSE = SynchedEntityData.defineId(Pig.class, EntityDataSerializers.INT);

    protected MixinTNTPig(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    public void addData(CallbackInfo info)
    {
            this.getEntityData().define(HAS_TNT, false);
            this.getEntityData().define(FUSE, -1);
    }
    @Inject(method = "mobInteract", at = @At("HEAD"))
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir)
    {
        if(this.isBaby())
        {
            return;
        }
        if(player.getItemInHand(hand).getItem() == Items.TNT && !(getHasTNT()))
        {
            setHasTNT(true);
            player.swing(hand, true);
            if(!player.isCreative())
            {
                player.getItemInHand(hand).shrink(1);
            }
            this.playSound(SoundEvents.GRASS_PLACE);
        }
        else if (getHasTNT() && player.getItemInHand(hand).getItem() == Items.FLINT_AND_STEEL && player instanceof ServerPlayer)
        {
            player.swing(hand, true);
            setFuse(80);
            this.playSound(SoundEvents.FLINTANDSTEEL_USE);
            this.playSound(SoundEvents.TNT_PRIMED);
            player.getItemInHand(hand).hurt(1, player.getRandom(), ((ServerPlayer) player));
            Set<WrappedGoal> set = this.goalSelector.getAvailableGoals();
            set.forEach(p ->
            {
                if(p.getGoal() instanceof PanicGoal)
                {
                    this.setLastHurtByMob(player);
                    p.start();
                }
            });
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addData(CompoundTag pCompound, CallbackInfo ci)
    {
        pCompound.putBoolean("pig_has_tnt", getHasTNT());
        pCompound.putInt("pig_fuse", getFuse());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readData(CompoundTag pCompound, CallbackInfo ci)
    {
        setFuse(pCompound.getInt("pig_fuse"));
        setHasTNT(pCompound.getBoolean("pig_has_tnt"));
    }
    public boolean getHasTNT()
    {
        return this.getEntityData().get(HAS_TNT);

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
