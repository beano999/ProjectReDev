package net.roadkill.redev.data.loot_modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class RemoveDropsModifier extends LootModifier
{
    public static Codec<RemoveDropsModifier> CODEC = RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(ResourceLocation.CODEC.fieldOf("removal").forGetter(o -> o.removal))
            .apply(inst, RemoveDropsModifier::new));
    private final ResourceLocation removal;

    protected RemoveDropsModifier(LootItemCondition[] conditionsIn, ResourceLocation removal)
    {
        super(conditionsIn);
        this.removal = removal;
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
    {   Item toRemove = ForgeRegistries.ITEMS.getValue(removal);
        generatedLoot.removeIf(stack -> stack.is(toRemove));
        return generatedLoot;
    }

    @Override
    public Codec<RemoveDropsModifier> codec()
    {   return CODEC;
    }
}
