package net.roadkill.redev.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

import javax.annotation.Nullable;

public class ItemStackBuilder
{
    private ItemStack stack;

    public static ItemStackBuilder create(ItemStack stack)
    {   ItemStackBuilder builder = new ItemStackBuilder();
        builder.stack = stack;
        return builder;
    }

    public ItemStackBuilder setCount(int count)
    {   stack.setCount(count);
        return this;
    }

    public ItemStackBuilder setDamage(int damage)
    {   stack.setDamageValue(damage);
        return this;
    }

    public ItemStackBuilder setTag(String key, Tag value)
    {   CompoundTag tag = stack.getOrCreateTag();
        tag.put(key, value);
        stack.setTag(tag);
        return this;
    }

    public ItemStackBuilder addAttributeModifier(Attribute attribute, AttributeModifier modifier, @Nullable EquipmentSlot slot)
    {   stack.addAttributeModifier(attribute, modifier, slot);
        return this;
    }

    public ItemStackBuilder enchant(Enchantment enchantment, int level)
    {   stack.enchant(enchantment, level);
        return this;
    }

    public ItemStackBuilder setName(String name)
    {   stack.setHoverName(Component.Serializer.fromJson(name));
        return this;
    }

    public ItemStack build()
    {   return stack;
    }
}