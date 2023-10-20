package net.roadkill.redev.mixin;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.QuartPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.PacketDistributor;
import net.roadkill.redev.core.network.ReDevPacketHandler;
import net.roadkill.redev.core.network.message.EntityPosMessage;
import net.roadkill.redev.util.RDMath;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HangingEntity.class)
public abstract class MixinHangingEntityLoad
{
    @Shadow
    protected Direction direction;

    @Shadow
    protected BlockPos pos;

    @Shadow
    protected abstract void recalculateBoundingBox();

    @Shadow public abstract void addAdditionalSaveData(CompoundTag pCompound);

    @Shadow public abstract void readAdditionalSaveData(CompoundTag pCompound);

    HangingEntity self = (HangingEntity) (Object) this;

    @Inject(method = "readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V",
            at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V"), cancellable = true)
    public void onLoadFault(CompoundTag tag, CallbackInfo ci)
    {   self.getPersistentData().putBoolean("LoadError", true);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci)
    {
        if (!self.level.isClientSide && self.getPersistentData().getBoolean("LoadError") && self instanceof Painting painting)
        {   int width = painting.getWidth() / 16;
            int height = painting.getHeight() / 16;
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
