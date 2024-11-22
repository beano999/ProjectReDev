package net.roadkill.redev.util;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.Optional;

public class ItemHelper
{
    public static Optional<ItemAttributeModifiers.Entry> getAttribute(ItemStack stack, Holder<Attribute> attribute)
    {   return Optional.ofNullable(stack.getAttributeModifiers().modifiers().stream().filter(entry -> entry.attribute().equals(attribute)).findFirst().orElse(null));
    }
}
