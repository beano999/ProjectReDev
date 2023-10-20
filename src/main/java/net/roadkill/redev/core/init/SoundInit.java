package net.roadkill.redev.core.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roadkill.redev.ReDev;

public class SoundInit
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ReDev.MOD_ID);

    public static final RegistryObject<SoundEvent> PETRIFIED_DOOR_OPEN = SOUNDS.register("block.petrified_door.open", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ReDev.MOD_ID, "block.petrified_door.open")));
    public static final RegistryObject<SoundEvent> PETRIFIED_DOOR_CLOSE = SOUNDS.register("block.petrified_door.close", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ReDev.MOD_ID, "block.petrified_door.close")));
}
