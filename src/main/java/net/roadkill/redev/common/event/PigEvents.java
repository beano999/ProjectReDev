package net.roadkill.redev.common.event;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerExplosion;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.roadkill.redev.mixin_interfaces.IPig;
import net.roadkill.redev.util.ItemHelper;

import java.util.List;
import java.util.Optional;

@EventBusSubscriber
public class PigEvents
{
    @SubscribeEvent
    public static void tickCounter(EntityTickEvent.Pre event)
    {
        Entity entity = event.getEntity();
        if (entity instanceof Pig pig && pig instanceof IPig pigData && pigData.hasTNT())
        {
            if (pigData.getFuse() > 0)
            {
                pigData.setFuse(pigData.getFuse() - 1);

                if (pig.level().isClientSide())
                {
                    pig.level().addParticle(ParticleTypes.SMOKE, pig.getX(), pig.getY() + 1, pig.getZ() + -.375, 0.0D, 0.0D, 0.0D);
                    pig.level().addParticle(ParticleTypes.SMOKE, pig.getX(), pig.getY() + 1, pig.getZ() + .25, 0.0D, 0.0D, 0.0D);
                }
                pig.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 2, false, false));
            }
            else if (pigData.getFuse() == 0 && !pig.level().isClientSide())
            {
                pig.level().explode(null, pig.getX(), pig.getY(), pig.getZ(), 4F, Level.ExplosionInteraction.MOB);
                pigData.setHasTNT(false);
                pigData.setFuse(-1);
            }
        }
    }

    @SubscribeEvent
    public static void pigChainReaction(LivingIncomingDamageEvent event)
    {
        LivingEntity entity = event.getEntity();
        if(!entity.level().isClientSide() && entity instanceof Pig pig && pig instanceof IPig pigData)
        {
            if (pigData.hasTNT() && (event.getSource().is(DamageTypes.EXPLOSION) || event.getSource().is(DamageTypes.PLAYER_EXPLOSION)))
            {
                if (entity.getHealth() - event.getAmount() < -4)
                {   pigData.setFuse(5);
                }
                else
                {   event.setAmount(0);
                    pigData.setFuse(40);
                }
            }
        }
    }

    @SubscribeEvent
    public static void pigArmorDrop(LivingDeathEvent event)
    {
        if (event.getEntity().level() instanceof ServerLevel level && event.getEntity() instanceof Pig pig && pig instanceof IPig pigData)
        {
            if (!pigData.getHelmet().isEmpty())
            {   pig.spawnAtLocation(level, pigData.getHelmet());
            }
        }
    }
    
    @SubscribeEvent
    public static void pigArmor(LivingIncomingDamageEvent event)
    {
        if (event.getEntity() instanceof Pig pig && pig instanceof IPig pigData && !event.getEntity().level().isClientSide())
        {
            ItemStack helmet = pigData.getHelmet();
            if (!helmet.isEmpty() && helmet.getItem() instanceof ArmorItem)
            {
                double armorDefense = ItemHelper.getAttribute(helmet, Attributes.ARMOR).map(entry -> entry.modifier().amount()).orElse(0d);
                double armorToughness = ItemHelper.getAttribute(helmet, Attributes.ARMOR_TOUGHNESS).map(entry -> entry.modifier().amount()).orElse(0d);

                float actualDamage = CombatRules.getDamageAfterAbsorb(pig, event.getAmount(), event.getSource(), (float) armorDefense, (float) armorToughness);
                actualDamage = CombatRules.getDamageAfterMagicAbsorb(actualDamage, EnchantmentHelper.getDamageProtection((ServerLevel) pig.level(), pig, event.getSource()));

                if (helmet.has(DataComponents.ENCHANTMENTS)
                && helmet.get(DataComponents.ENCHANTMENTS).entrySet().stream().anyMatch(ench -> ench.getKey() == Enchantments.BLAST_PROTECTION && ench.getIntValue() >= 4))
                {
                    event.setAmount(Math.min(pig.getMaxHealth() - 1, actualDamage));
                }
                else
                {   event.setAmount(actualDamage);
                }
                helmet.hurtAndBreak(((int) actualDamage), (ServerLevel) pig.level(), pig, (a) -> {});
            }
        }
    }
}
