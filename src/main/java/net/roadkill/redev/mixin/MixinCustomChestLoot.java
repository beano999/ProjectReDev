package net.roadkill.redev.mixin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.util.ItemStackBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(RandomizableContainerBlockEntity.class)
public class MixinCustomChestLoot
{
    private static final WeightedRandomList<WeightedEntry.Wrapper<ItemStack>> SPECIAL_ARTIFACTS = WeightedRandomList.create(
            WeightedEntry.wrap(new ItemStack(Items.DIAMOND_HORSE_ARMOR), 8),
            WeightedEntry.wrap(new ItemStack(Items.GOLDEN_HORSE_ARMOR), 10),
            WeightedEntry.wrap(ItemStackBuilder.create(new ItemStack(Items.SHIELD))
                                       .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier("Shield knockback resistance", 0.5, AttributeModifier.Operation.ADDITION), EquipmentSlot.OFFHAND)
                                       .setName("{\"text\":\"Shield of Unwavering\",\"italic\":false,\"color\":\"light_purple\"}")
                                       .build(), 5),
            WeightedEntry.wrap(ItemStackBuilder.create(new ItemStack(Items.CROSSBOW))
                                       .enchant(Enchantments.PIERCING, 10)
                                       .setName("{\"text\":\"Ballista of Decimation\",\"italic\":false,\"color\":\"light_purple\"}")
                                       .build(), 7),
            WeightedEntry.wrap(new ItemStack(Items.SPYGLASS), 10));

    private static ResourceLocation STORED_LOOT_TABLE = null;

    RandomizableContainerBlockEntity self = (RandomizableContainerBlockEntity) (Object) this;

    @Shadow
    protected ResourceLocation lootTable;

    @Inject(method = "unpackLootTable", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot/LootTable;fill(Lnet/minecraft/world/Container;Lnet/minecraft/world/level/storage/loot/LootContext;)V", shift = At.Shift.AFTER))
    public void unpackLootTable(Player player, CallbackInfo ci)
    {
        if (STORED_LOOT_TABLE == null || STORED_LOOT_TABLE.equals(new ResourceLocation(ReDev.MOD_ID, "chests/pyramid_tomb_special")))
        {
            WeightedRandomList<WeightedEntry.Wrapper<ItemStack>> artifacts = WeightedRandomList.create(SPECIAL_ARTIFACTS.unwrap());
            RandomSource random = RandomSource.create();

            for (int i = 0; i < 3; i++)
            {
                Optional<WeightedEntry.Wrapper<ItemStack>> stack = artifacts.getRandom(random);
                if (stack.isPresent())
                {   int tries = 0;
                    int slot;
                    while (self.getItem(slot = random.nextInt(self.getContainerSize())).getCount() > 0)
                    {   tries++;
                        if (tries > 20)
                        {   return;
                        }
                    }
                    self.setItem(slot, stack.get().getData());
                }
            }
        }
    }

    @Inject(method = "unpackLootTable", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;getLootTables()Lnet/minecraft/world/level/storage/loot/LootTables;", shift = At.Shift.AFTER))
    public void storeLootTable(Player pPlayer, CallbackInfo ci)
    {   STORED_LOOT_TABLE = lootTable;
    }
}
