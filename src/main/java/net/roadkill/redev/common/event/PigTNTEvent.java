package net.roadkill.redev.common.event;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.Level;
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
        if(entity instanceof Pig pig && ((IPigTNT) pig).getHasTNT())
        {
            IPigTNT pigTNT = ((IPigTNT) pig);
            if(pigTNT.getFuse() > 0)
            {
                pigTNT.setFuse(pigTNT.getFuse() - 1);
                if (entity.getLevel().isClientSide())
                {
                    entity.getLevel().addParticle(ParticleTypes.SMOKE, entity.getX(), entity.getY() + 2D, entity.getZ(), 0.0D, 0.0D, 0.0D);
                }
            }
            else if(pigTNT.getFuse() == 0 && !entity.getLevel().isClientSide())
            {
                pig.getLevel().explode(pig, pig.getX(), pig.getY(), pig.getZ(), 4F, Level.ExplosionInteraction.TNT);
                pigTNT.setHasTNT(false);
                pig.kill();
            }
        }
    }

    @SubscribeEvent
    public static void pigChainReaction(LivingDamageEvent event)
    {
        LivingEntity entity = event.getEntity();
        if(!entity.getLevel().isClientSide() && entity instanceof Pig pig)
        {
            IPigTNT tnt = ((IPigTNT) pig);
            if(tnt.getHasTNT() && (event.getSource().is(DamageTypes.EXPLOSION) || event.getSource().is(DamageTypes.PLAYER_EXPLOSION)))
            {
                if(entity.getHealth() - event.getAmount() < -4)
                {
                    tnt.setFuse(5);
                }
                else
                {
                    event.setAmount(0);
                    tnt.setFuse(40);
                }
            }
        }
    }
}
