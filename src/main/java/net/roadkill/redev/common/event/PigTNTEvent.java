package net.roadkill.redev.common.event;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.mixin_interfaces.IPigTNT;

@Mod.EventBusSubscriber
public class PigTNTEvent
{
    @SubscribeEvent
    public static void tickCounter(LivingEvent.LivingTickEvent event)
    {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Pig pig && ((IPigTNT) pig).hasTNT())
        {
            IPigTNT pigTNT = ((IPigTNT) pig);
            if (pigTNT.getFuse() > 0)
            {
                pigTNT.setFuse(pigTNT.getFuse() - 1);
                if (entity.getLevel().isClientSide())
                {   entity.getLevel().addParticle(ParticleTypes.SMOKE, entity.getX(), entity.getY() + 2D, entity.getZ(), 0.0D, 0.0D, 0.0D);
                }
            }
            else if (pigTNT.getFuse() == 0 && !entity.getLevel().isClientSide())
            {   pig.getLevel().explode(pig, pig.getX(), pig.getY(), pig.getZ(), 4F, Level.ExplosionInteraction.TNT);
                pigTNT.setHasTNT(false);
                pig.kill();
            }
        }
    }

    @SubscribeEvent
    public static void pigChainReaction(LivingDamageEvent event)
    {
        LivingEntity entity = event.getEntity();
        if (!entity.getLevel().isClientSide() && entity instanceof Pig pig)
        {
            IPigTNT tnt = ((IPigTNT) pig);
            if (tnt.hasTNT() && (event.getSource().is(DamageTypes.EXPLOSION) || event.getSource().is(DamageTypes.PLAYER_EXPLOSION)))
            {
                if (entity.getHealth() - event.getAmount() < -4)
                {   tnt.setFuse(5);
                }
                else
                {   event.setAmount(0);
                    tnt.setFuse(20);
                }
            }
        }
    }

    @SubscribeEvent
    public static void setTNTPigSize(EntityEvent.Size event)
    {
        if (event.getEntity() instanceof Pig pig && ((IPigTNT) pig).hasTNT())
        {   EntityDimensions size = event.getOldSize();
            event.setNewSize(EntityDimensions.fixed(size.width, 1.9f));
        }
    }
}
