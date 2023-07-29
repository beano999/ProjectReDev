package net.momostudios.redev.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.level.Level;
import net.momostudios.redev.core.entity.PhantomType;
import net.momostudios.redev.core.entity.SpecialPhantom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Phantom.class)
public abstract class MixinSpecialPhantom implements SpecialPhantom
{
    @Unique
    PhantomType phantomType;

    @Unique
    public PhantomType getPhantomType()
    {   return this.phantomType == null ? PhantomType.NORMAL : this.phantomType;
    }

    @Unique
    public void setPhantomType(PhantomType type)
    {   this.phantomType = type;
    }

    @Inject(method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V",
            at = @At("RETURN"))
    public void onInit(EntityType<Phantom> entityType, Level level, CallbackInfo ci)
    {
        if (!level.isClientSide)
        {   int rand = level.random.nextInt(10);
            PhantomType type = switch (rand)
            {   case 0  -> PhantomType.RED;
                case 1  -> PhantomType.BLUE;
                case 2  -> PhantomType.GREEN;
                case 3  -> PhantomType.HOLLOW;
                default -> PhantomType.NORMAL;
            };
            this.setPhantomType(type);
        }
    }

    @Redirect(method = "aiStep()V",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Phantom;setSecondsOnFire(I)V"))
    public void onPhantomBurn(Phantom phantom, int seconds)
    {   if (((SpecialPhantom) phantom).getPhantomType() == PhantomType.RED)
        {   phantom.clearFire();
        }
        else phantom.setSecondsOnFire(seconds);
    }
}
