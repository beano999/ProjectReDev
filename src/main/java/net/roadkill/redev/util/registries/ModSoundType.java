package net.roadkill.redev.util.registries;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class ModSoundType
{
    public static final SoundType PETRIFIED_PLANKS = new SoundType(1.0F, 0.9F, SoundEvents.DECORATED_POT_FALL, SoundEvents.DECORATED_POT_STEP, SoundEvents.DECORATED_POT_FALL, SoundEvents.DECORATED_POT_HIT, SoundEvents.DECORATED_POT_PLACE);
    public static final SoundType PETRIFIED_LOG = new SoundType(1.0F, 0.65f, SoundEvents.GILDED_BLACKSTONE_BREAK, SoundEvents.DECORATED_POT_STEP, SoundEvents.GILDED_BLACKSTONE_PLACE, SoundEvents.DECORATED_POT_HIT, SoundEvents.DECORATED_POT_PLACE);
}
