package net.roadkill.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.decoration.BlockAttachedEntity;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.Painting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockAttachedEntity.class)
public abstract class MixinHangingEntityLoad
{
    @Shadow
    protected BlockPos pos;

    @Shadow
    protected abstract void recalculateBoundingBox();

    @Shadow public abstract void addAdditionalSaveData(CompoundTag pCompound);

    @Shadow public abstract void readAdditionalSaveData(CompoundTag pCompound);

    HangingEntity self = (HangingEntity) (Object) this;

    @Inject(method = "readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V",
            at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V"))
    public void onLoadFault(CompoundTag tag, CallbackInfo ci)
    {   self.getPersistentData().putBoolean("LoadError", true);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci)
    {
        if (!self.level().isClientSide && self.getPersistentData().getBoolean("LoadError") && self instanceof Painting painting)
        {
            Direction direction = painting.getDirection();
            int width = painting.getVariant().value().width() / 16;
            int height = painting.getVariant().value().height() / 16;
            int x = 0;
            int y = 0;
            int z = 0;
            if (height % 2 == 0)
            {   y--;
            }
            if (width % 2 == 0)
            {
                if (direction == Direction.SOUTH)
                {   x--;
                }
                if (direction == Direction.WEST)
                {   z--;
                }
            }
            pos = pos.offset(x, y, z);
            recalculateBoundingBox();
            CompoundTag tag = new CompoundTag();
            painting.save(tag);
            painting.load(tag);
            self.getPersistentData().remove("LoadError");
        }
    }
}
