package net.roadkill.redev.util;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

import javax.annotation.Nullable;

public class ItemStackBuilder
{
    private ItemStack stack;
    private RegistryAccess registryAccess;

    public static ItemStackBuilder create(ItemStack stack, RegistryAccess registryAccess)
    {   ItemStackBuilder builder = new ItemStackBuilder();
        builder.registryAccess = registryAccess;
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

    public ItemStackBuilder addAttributeModifier(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot)
    {
        stack.get(DataComponents.ATTRIBUTE_MODIFIERS).withModifierAdded(attribute, modifier, slot);
        return this;
    }

    public ItemStackBuilder enchant(ResourceKey<Enchantment> enchantment, int level)
    {
        Holder<Enchantment> enchHolder = registryAccess.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(enchantment);
        stack.enchant(enchHolder, level);
        return this;
    }

    public ItemStackBuilder setName(Component name)
    {   stack.set(DataComponents.CUSTOM_NAME, name);
        return this;
    }

    public ItemStack build()
    {   return stack;
    }
}