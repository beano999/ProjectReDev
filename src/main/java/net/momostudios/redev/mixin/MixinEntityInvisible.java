package net.momostudios.redev.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.momostudios.redev.ReDev;
import net.momostudios.redev.util.registries.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class MixinEntityInvisible
{
    @Shadow
    protected abstract boolean getSharedFlag(int flag);

    Entity entity = (Entity) (Object) this;

    @Inject(method = "isInvisible", at = @At("HEAD"), cancellable = true, remap = ReDev.REMAP_MIXINS)
    public void onInvisible(CallbackInfoReturnable<Boolean> cir)
    {
        Player player = Minecraft.getInstance().player;
        if (player != null && player.hasEffect(ModEffects.SIGHT))
        {   cir.setReturnValue(false);
            entity.getPersistentData().putBoolean("PartialInvisible", this.getSharedFlag(5));
        }
        else entity.getPersistentData().putBoolean("PartialInvisible", false);
    }
}