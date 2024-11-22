package net.roadkill.redev.common.event;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class SetWitherSkeletonRange
{
    @SubscribeEvent
    public static void onWitherSkeletonAttributes(EntityAttributeModificationEvent event)
    {   event.add(EntityType.WITHER_SKELETON, Attributes.FOLLOW_RANGE, 32.0);
    }
}
