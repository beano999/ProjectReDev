package net.roadkill.redev.common.event;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.mixin_interfaces.IWitherSkeletonArsenal;

import java.util.function.Supplier;

@Mod.EventBusSubscriber
public class WitherSkeletonFighting
{
    @SubscribeEvent
    public static void witherSkeletonDrawSword(LivingEvent.LivingTickEvent event)
    {
        if (event.getEntity() instanceof WitherSkeleton skeleton && !skeleton.level.isClientSide && skeleton.tickCount > 20)
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
                ItemStack defaultRanged = new ItemStack(Items.BOW);
                defaultRanged.enchant(Enchantments.PUNCH_ARROWS, 1);
                ItemStack rangedItem = getOrCreateItem(() -> ((IWitherSkeletonArsenal) skeleton).getRangedItem(), ((IWitherSkeletonArsenal) skeleton).setRangedItem(defaultRanged));
                getOrCreateItem(() -> ((IWitherSkeletonArsenal) skeleton).getMeleeItem(), ((IWitherSkeletonArsenal) skeleton).setMeleeItem(skeleton.getItemInHand(InteractionHand.MAIN_HAND)));
                skeleton.setItemInHand(InteractionHand.MAIN_HAND, rangedItem);
            }
            else if (!shouldUseBow && !(skeleton.getItemInHand(InteractionHand.MAIN_HAND).equals(((IWitherSkeletonArsenal) skeleton).getMeleeItem())))
            {
                // set the melee item in the skeleton's main hand, and stash the item it is currently holding in the ranged slot
                ItemStack defaultMelee = new ItemStack(Items.STONE_SWORD);
                defaultMelee.enchant(Enchantments.KNOCKBACK, 1);
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
    public static void witherSkeletonAttackFar(LivingHurtEvent event)
    {
        if (event.getEntity() instanceof WitherSkeleton skeleton && event.getSource().isIndirect() && event.getSource().getEntity() instanceof LivingEntity attacker
        && !(attacker instanceof Player player && player.isCreative()))
        {   skeleton.setTarget(attacker);
        }
    }
}
