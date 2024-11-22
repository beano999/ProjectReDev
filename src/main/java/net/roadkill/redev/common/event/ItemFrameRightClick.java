package net.roadkill.redev.common.event;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.stream.StreamSupport;

@EventBusSubscriber
public class ItemFrameRightClick
{
    @SubscribeEvent
    public static void onItemFrameRightClick(PlayerInteractEvent.EntityInteract event)
    {
        if (event.getTarget() instanceof ItemFrame itemFrame && event.getEntity().isCrouching())
        {   event.setCanceled(true);

            if (!itemFrame.isInvisible() && event.getItemStack().is(Items.POTION)
            && event.getItemStack().has(DataComponents.POTION_CONTENTS)
            && StreamSupport.stream(event.getItemStack().get(DataComponents.POTION_CONTENTS).getAllEffects().spliterator(), false).anyMatch(efffect -> efffect.getEffect() == MobEffects.INVISIBILITY))
            {
                itemFrame.setInvisible(true);
                event.getEntity().swing(event.getHand());
                event.getTarget().playSound(SoundEvents.BREWING_STAND_BREW);
                event.getEntity().setItemInHand(event.getHand(), Items.GLASS_BOTTLE.getDefaultInstance());
            }
        }
    }
}
