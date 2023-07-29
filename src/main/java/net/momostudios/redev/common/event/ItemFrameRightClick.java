package net.momostudios.redev.common.event;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ItemFrameRightClick
{
    @SubscribeEvent
    public static void onItemFrameRightClick(PlayerInteractEvent.EntityInteract event)
    {
        if (event.getTarget() instanceof ItemFrame itemFrame && event.getEntity().isCrouching())
        {   event.setCanceled(true);

            if (!itemFrame.isInvisible() && event.getItemStack().is(Items.POTION)
            && PotionUtils.getPotion(event.getItemStack()).getEffects().stream().anyMatch(efffect -> efffect.getEffect() == MobEffects.INVISIBILITY))
            {   itemFrame.setInvisible(true);
                event.getEntity().swing(event.getHand());
                event.getTarget().playSound(SoundEvents.BREWING_STAND_BREW);
                event.getEntity().setItemInHand(event.getHand(), Items.GLASS_BOTTLE.getDefaultInstance());
            }
        }
    }
}
