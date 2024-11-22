package net.roadkill.redev.core.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.roadkill.redev.ReDev;

public class SoundInit
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, ReDev.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> PETRIFIED_DOOR_OPEN = SOUNDS.register("block.petrified_door.open", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "block.petrified_door.open")));
    public static final DeferredHolder<SoundEvent, SoundEvent> PETRIFIED_DOOR_CLOSE = SOUNDS.register("block.petrified_door.close", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "block.petrified_door.close")));

    public static final DeferredHolder<SoundEvent, SoundEvent> LITHICAN_AMBIENT = SOUNDS.register("entity.lithican.ambient", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "entity.lithican.ambient")));
    public static final DeferredHolder<SoundEvent, SoundEvent> LITHICAN_HURT = SOUNDS.register("entity.lithican.hurt", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "entity.lithican.hurt")));
    public static final DeferredHolder<SoundEvent, SoundEvent> LITHICAN_DEATH = SOUNDS.register("entity.lithican.death", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "entity.lithican.death")));
    public static final DeferredHolder<SoundEvent, SoundEvent> LITHICAN_AWAKEN = SOUNDS.register("entity.lithican.awaken", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "entity.lithican.awaken")));
}
