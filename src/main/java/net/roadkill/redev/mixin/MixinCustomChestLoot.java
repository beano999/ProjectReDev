package net.roadkill.redev.mixin;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.CommonHooks;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.core.init.ItemInit;
import net.roadkill.redev.util.ItemStackBuilder;
import net.roadkill.redev.util.registries.ModItems;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Optional;

@Mixin(CommonHooks.class)
public abstract class MixinCustomChestLoot
{
    private static WeightedRandomList<WeightedEntry.Wrapper<ItemStack>> SPECIAL_ARTIFACTS = WeightedRandomList.create();
    private static final ResourceKey<LootTable> PYRAMID_SPECIAL = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "chests/pyramid_tomb_special"));

    @Inject(method = "modifyLoot(Lnet/minecraft/resources/ResourceLocation;Lit/unimi/dsi/fastutil/objects/ObjectArrayList;Lnet/minecraft/world/level/storage/loot/LootContext;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;",
            at = @At("HEAD"))
    private static void modifyLoot(ResourceLocation lootTable, ObjectArrayList<ItemStack> stacks, LootContext context, CallbackInfoReturnable<ObjectArrayList<ItemStack>> cir)
    {
        if (PYRAMID_SPECIAL.location().equals(lootTable))
        {   generateSpecialArtifacts(stacks, context);
        }
    }

    private static void generateSpecialArtifacts(ObjectArrayList<ItemStack> stacks, LootContext context)
    {
        calculateSpecialArtifacts(context);

        WeightedRandomList<WeightedEntry.Wrapper<ItemStack>> artifacts = WeightedRandomList.create(SPECIAL_ARTIFACTS.unwrap());
        RandomSource random = RandomSource.create();

        for (int i = 0; i < 3; i++)
        {
            Optional<WeightedEntry.Wrapper<ItemStack>> stack = artifacts.getRandom(random);
            if (stack.isPresent())
            {
                stacks.add(stack.get().data());
                artifacts = WeightedRandomList.create(artifacts.unwrap().stream().filter(e -> e.data() != stack.get().data()).toArray(WeightedEntry.Wrapper[]::new));
            }
        }
    }

    private static void calculateSpecialArtifacts(LootContext context)
    {
        RegistryAccess registryAccess = context.getLevel().registryAccess();

        SPECIAL_ARTIFACTS = WeightedRandomList.create(
                WeightedEntry.wrap(new ItemStack(ItemInit.NETHERITE_HORSE_ARMOR), 8),
                WeightedEntry.wrap(new ItemStack(Items.DIAMOND_HORSE_ARMOR), 10),
                WeightedEntry.wrap(ItemStackBuilder.create(new ItemStack(Items.SHIELD), registryAccess)
                                           .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "knockback_resist"),
                                                                                                                        0.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.OFFHAND)
                                           .setName(Component.literal("Shield of Unwavering").withStyle(Style.EMPTY.withColor(ChatFormatting.LIGHT_PURPLE).withItalic(false)))
                                           .build(), 5),
                WeightedEntry.wrap(ItemStackBuilder.create(new ItemStack(Items.CROSSBOW), registryAccess)
                                           .enchant(Enchantments.PIERCING, 10)
                                           .setName(Component.literal("Ballista of Decimation").withStyle(Style.EMPTY.withColor(ChatFormatting.LIGHT_PURPLE).withItalic(false)))
                                           .build(), 7),
                WeightedEntry.wrap(new ItemStack(Items.SPYGLASS), 10));
    }
}
