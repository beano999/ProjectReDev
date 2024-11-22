package net.roadkill.redev.common.event;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.roadkill.redev.mixin_interfaces.IWitherSkeletonArsenal;
import net.roadkill.redev.util.ItemStackBuilder;

import java.util.function.Supplier;

@EventBusSubscriber
public class WitherSkeletonFighting
{
    @SubscribeEvent
    public static void witherSkeletonDrawSword(EntityTickEvent.Pre event)
    {
        if (event.getEntity() instanceof WitherSkeleton skeleton && !skeleton.level().isClientSide && skeleton.tickCount > 20)
        {
            boolean shouldUseBow = false;
            if (skeleton.getTarget() != null)
            {
                if (skeleton.distanceTo(skeleton.getTarget()) > 10)
                {   shouldUseBow = true;
                }
            }
            if (shouldUseBow && !(skeleton.getItemInHand(InteractionHand.MAIN_HAND).equals(((IWitherSkeletonArsenal) skeleton).getRangedItem())))
            {
                // set the ranged item in the skeleton's main hand, and stash the item it is currently holding in the melee slot
                ItemStack defaultRanged = ItemStackBuilder.create(Items.BOW.getDefaultInstance(), skeleton.registryAccess()).enchant(Enchantments.PUNCH, 1).build();
                ItemStack rangedItem = getOrCreateItem(() -> ((IWitherSkeletonArsenal) skeleton).getRangedItem(), ((IWitherSkeletonArsenal) skeleton).setRangedItem(defaultRanged));
                getOrCreateItem(() -> ((IWitherSkeletonArsenal) skeleton).getMeleeItem(), ((IWitherSkeletonArsenal) skeleton).setMeleeItem(skeleton.getItemInHand(InteractionHand.MAIN_HAND)));
                skeleton.setItemInHand(InteractionHand.MAIN_HAND, rangedItem);
            }
            else if (!shouldUseBow && !(skeleton.getItemInHand(InteractionHand.MAIN_HAND).equals(((IWitherSkeletonArsenal) skeleton).getMeleeItem())))
            {
                // set the melee item in the skeleton's main hand, and stash the item it is currently holding in the ranged slot
                ItemStack defaultMelee = ItemStackBuilder.create(Items.STONE_SWORD.getDefaultInstance(), skeleton.registryAccess()).enchant(Enchantments.KNOCKBACK, 1).build();
                ItemStack meleeItem = getOrCreateItem(() -> ((IWitherSkeletonArsenal) skeleton).getMeleeItem(), ((IWitherSkeletonArsenal) skeleton).setMeleeItem(defaultMelee));
                getOrCreateItem(() -> ((IWitherSkeletonArsenal) skeleton).getRangedItem(), ((IWitherSkeletonArsenal) skeleton).setRangedItem(skeleton.getItemInHand(InteractionHand.MAIN_HAND)));
                skeleton.setItemInHand(InteractionHand.MAIN_HAND, meleeItem);
            }
        }
    }

    private static ItemStack getOrCreateItem(Supplier<ItemStack> supplier, ItemStack defaultItem)
    {
        ItemStack itemStack = supplier.get();
        if (itemStack.isEmpty())
        {   return defaultItem;
        }
        return itemStack;
    }

    @SubscribeEvent
    public static void witherSkeletonAttackFar(LivingDamageEvent.Post event)
    {
        if (event.getEntity() instanceof WitherSkeleton skeleton && event.getSource().getEntity() instanceof LivingEntity attacker
        && skeleton.getTarget() == null && !(attacker instanceof Player player && player.isCreative()))
        {   skeleton.setTarget(attacker);
        }
    }
}
