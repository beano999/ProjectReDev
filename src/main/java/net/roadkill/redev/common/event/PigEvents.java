package net.roadkill.redev.common.event;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.mixin_interfaces.IPig;

import java.util.List;

@Mod.EventBusSubscriber
public class PigEvents
{
    @SubscribeEvent
    public static void tickCounter (LivingEvent.LivingTickEvent event)
    {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Pig pig && ((IPig) pig).hasTNT())
        {
            IPig pigTNT = ((IPig) pig);
            if(pigTNT.getFuse() > 0)
            {
                pigTNT.setFuse(pigTNT.getFuse() - 1);
                if (entity.getLevel().isClientSide())
                {
                    entity.getLevel().addParticle(ParticleTypes.SMOKE, entity.getX(), entity.getY() + 1, entity.getZ() + -.375, 0.0D, 0.0D, 0.0D);
                    entity.getLevel().addParticle(ParticleTypes.SMOKE, entity.getX(), entity.getY() + 1, entity.getZ() + .25, 0.0D, 0.0D, 0.0D);
                }
                boolean hasSpeed = false;
                for (MobEffectInstance activeEffect : entity.getActiveEffects())
                {
                    if(activeEffect.getEffect() == MobEffects.MOVEMENT_SPEED)
                    {
                        hasSpeed = true;
                    }
                }
                if(!hasSpeed)
                {
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 2, true, false));
                }
            }
            else if(pigTNT.getFuse() == 0 && !entity.getLevel().isClientSide())
            {
                pig.getLevel().explode(null, pig.getX(), pig.getY(), pig.getZ(), 4F, Level.ExplosionInteraction.TNT);
                pigTNT.setHasTNT(false);
                pigTNT.setFuse(-1);
            }
        }
    }

    @SubscribeEvent
    public static void pigChainReaction (LivingDamageEvent event)
    {
        LivingEntity entity = event.getEntity();
        if(!entity.getLevel().isClientSide() && entity instanceof Pig pig)
        {
            IPig tnt = ((IPig) pig);
            if(tnt.hasTNT() && (event.getSource().is(DamageTypes.EXPLOSION) || event.getSource().is(DamageTypes.PLAYER_EXPLOSION)))
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

    @SubscribeEvent
    public static void pigArmorDrop (LivingDeathEvent event)
    {
        if(!event.getEntity().getLevel().isClientSide() && event.getEntity() instanceof Pig pig)
        {
            IPig iPig = ((IPig) pig);
            if(!iPig.getHelmet().isEmpty())
            {
                pig.spawnAtLocation(iPig.getHelmet());
            }
        }
    }
    
    @SubscribeEvent
    public static void pigArmor (LivingHurtEvent event)
    {
        if (event.getEntity() instanceof Pig pig && !event.getEntity().getLevel().isClientSide())
        {
            IPig pig1 = ((IPig) pig);
            ItemStack potentialHelmet = pig1.getHelmet();
            if(!potentialHelmet.isEmpty() && potentialHelmet.getItem() instanceof ArmorItem armorItem)
            {
                float actualDamage = CombatRules.getDamageAfterAbsorb(event.getAmount(), armorItem.getDefense(), armorItem.getToughness());
                actualDamage = CombatRules.getDamageAfterMagicAbsorb(actualDamage, EnchantmentHelper.getDamageProtection(List.of(potentialHelmet), event.getSource()));

                if(EnchantmentHelper.getEnchantments(potentialHelmet).entrySet().stream().anyMatch(enchantmentIntegerEntry ->
                {
                    Enchantment enchantment = enchantmentIntegerEntry.getKey();
                    int level = enchantmentIntegerEntry.getValue();
                    if(enchantment == Enchantments.BLAST_PROTECTION && level >= 4)
                    {

                        return true;
                    }
                    return false;
                }))
                {
                    event.setAmount(Math.min(pig.getMaxHealth() - 1, actualDamage));
                }
                else
                {
                    event.setAmount(actualDamage);
                }
                potentialHelmet.hurtAndBreak(((int) actualDamage), pig, (a) -> a.broadcastBreakEvent(EquipmentSlot.HEAD));
            }
        }
    }
}
