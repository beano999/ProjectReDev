package net.roadkill.redev.common.entity;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class ModAttributes {
    public static final Attribute ILLUSION_ATTRIBUTE = new RangedAttribute("attribute.name.generic.illusion", 0.0, 0.0, 1.0).setSyncable(true);
}