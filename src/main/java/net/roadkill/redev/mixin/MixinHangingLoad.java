package net.roadkill.redev.mixin;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HangingEntity.class)
public abstract class MixinHangingLoad
{
    @Shadow
    protected Direction direction;

    @Shadow
    protected BlockPos pos;

    @Shadow
    protected abstract void recalculateBoundingBox();

    HangingEntity self = (HangingEntity) (Object) this;

    @Inject(method = "readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V",
            at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V"), cancellable = true)
    public void onLoadFault(CompoundTag tag, CallbackInfo ci)
    {
        if (self.getServer() != null)
        self.getServer().execute(() ->
        {
            AABB bb = self.getBoundingBox();
            int width = (int) Math.max(bb.maxX - bb.minX, bb.maxZ - bb.minZ);
            int height = (int) (bb.maxY - bb.minY);
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
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
            self.setPos(x, y, z);
            recalculateBoundingBox();
        });
        LogUtils.getLogger().debug("Hanging entity at invalid position: {}", new BlockPos(tag.getInt("TileX"), tag.getInt("TileY"), tag.getInt("TileZ")));
        ci.cancel();
    }
}
