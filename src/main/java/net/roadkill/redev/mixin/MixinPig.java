package net.roadkill.redev.mixin;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;
import net.roadkill.redev.mixin_interfaces.IPig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(Pig.class)
public abstract class MixinPig extends Mob implements IPig
{
    private static final EntityDataAccessor<Boolean> HAS_TNT = SynchedEntityData.defineId(Pig.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> FUSE = SynchedEntityData.defineId(Pig.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<ItemStack> HELMET = SynchedEntityData.defineId(Pig.class, EntityDataSerializers.ITEM_STACK);

    protected MixinPig(EntityType<? extends Mob> entityType, Level level)
    {   super(entityType, level);
    }

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir)
    {
        if (this.isBaby() || this.level().isClientSide()) return;

        ItemStack heldItem = player.getItemInHand(hand);
        ItemStack heldItemCopy = heldItem.copy();

        // Add TNT to pig
        if (heldItem.getItem() == Items.TNT && !(this.hasTNT()))
        {
            setHasTNT(true);
            player.swing(hand, true);
            if (!player.isCreative())
            {   heldItem.shrink(1);
            }
            this.playSound(SoundEvents.GRASS_PLACE);
            cir.setReturnValue(InteractionResult.SUCCESS_SERVER);
        }
        // Light TNT on pig
        else if (this.hasTNT() && heldItem.is(Tags.Items.TOOLS_IGNITER) && player instanceof ServerPlayer)
        {
            player.swing(hand, true);
            this.setFuse(80);
            this.playSound(SoundEvents.FLINTANDSTEEL_USE);
            this.playSound(SoundEvents.TNT_PRIMED);
            heldItem.hurtAndBreak(1, (ServerLevel) this.level(), player, (user) -> {});
            Set<WrappedGoal> set = this.goalSelector.getAvailableGoals();
            set.forEach(p ->
            {
                if (p.getGoal() instanceof PanicGoal)
                {   this.setLastHurtByMob(player);
                    p.start();
                }
            });
            cir.setReturnValue(InteractionResult.SUCCESS_SERVER);
        }
        // Add/swap helmet on pig
        else if (player.getEquipmentSlotForItem(heldItem) == EquipmentSlot.HEAD && heldItem.has(DataComponents.EQUIPPABLE))
        {
            player.getItemInHand(hand).shrink(1);
            if (!this.getHelmet().isEmpty())
            {   player.setItemInHand(hand, this.getHelmet());
            }
            this.setHelmet(heldItemCopy);
            this.playSound(heldItem.get(DataComponents.EQUIPPABLE).equipSound().value());
            player.swing(hand, true);
            cir.setReturnValue(InteractionResult.SUCCESS_SERVER);
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addData(CompoundTag pCompound, CallbackInfo ci)
    {
        pCompound.putBoolean("HasTnt", this.hasTNT());
        pCompound.putInt("TntFuse", this.getFuse());
        if (!this.getHelmet().isEmpty())
        {   pCompound.put("HelmetItem", this.getHelmet().save(this.registryAccess()));
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readData(CompoundTag pCompound, CallbackInfo ci)
    {
        if (pCompound.contains("TntFuse"))
        {   this.setFuse(pCompound.getInt("TntFuse"));
        }
        this.setHasTNT(pCompound.getBoolean("HasTnt"));
        ItemStack itemStack = ItemStack.parseOptional(this.registryAccess(), pCompound.getCompound("HelmetItem"));
        if (this.getEquipmentSlotForItem(itemStack) == EquipmentSlot.HEAD)
        {   this.setHelmet(itemStack);
        }
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    public void addData(SynchedEntityData.Builder builder, CallbackInfo info)
    {
        builder.define(HAS_TNT, false);
        builder.define(FUSE, -1);
        builder.define(HELMET, ItemStack.EMPTY);
    }

    public boolean hasTNT()
    {   return this.getEntityData().get(HAS_TNT);
    }

    public void setHasTNT(boolean bool)
    {   this.getEntityData().set(HAS_TNT, bool);
    }

    @Override
    public int getFuse()
    {   return this.getEntityData().get(FUSE);
    }

    @Override
    public void setFuse(int fuse)
    {   this.getEntityData().set(FUSE, fuse);
    }

    @Override
    public void setHelmet(ItemStack armorItem)
    {   this.getEntityData().set(HELMET, armorItem);
    }

    @Override
    public ItemStack getHelmet()
    {   return this.entityData.get(HELMET);
    }
}
