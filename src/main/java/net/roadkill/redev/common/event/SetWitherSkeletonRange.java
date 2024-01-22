package net.roadkill.redev.common.event;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetWitherSkeletonRange
{
    @SubscribeEvent
    public static void onWitherSkeletonAttributes(EntityAttributeModificationEvent event)
    {   event.add(EntityType.WITHER_SKELETON, Attributes.FOLLOW_RANGE, 32.0);
    }
}
