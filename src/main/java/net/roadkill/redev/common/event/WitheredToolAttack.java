package net.roadkill.redev.common.event;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.roadkill.redev.data.ModTags;

@EventBusSubscriber
public class WitheredToolAttack
{
    @SubscribeEvent
    public static void onAttack(LivingDamageEvent.Post event)
    {
        if (event.getSource().getEntity() instanceof Player player)
        {
            ItemStack item = player.getMainHandItem();
            if (item.is(ModTags.Items.WITHERED))
            {   event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 50, 2));
            }
        }
    }
}
