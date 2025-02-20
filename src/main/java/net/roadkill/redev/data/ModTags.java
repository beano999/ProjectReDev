package net.roadkill.redev.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.roadkill.redev.ReDev;

public final class ModTags
{
    public final static class Blocks
    {
        public static final TagKey<Block> NETHER_BRISTLE_PLACEABLE = create("nether_bristle_placeable");
        public static final TagKey<Block> BOILS_CAULDRON = create("boils_cauldron");
        public static final TagKey<Block> NETHER_BRICKS = createCommon("bricks/nether");

        private static TagKey<Block> create(String name)
        {   return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, name));
        }

        private static TagKey<Block> createCommon(String name)
        {   return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public static final class Biomes
    {
        public static final TagKey<Biome> HAS_NETHER_BRISTLE = create("has_nether_bristle");
        public static final TagKey<Biome> HAS_NETHER_BRISTLE_SPARSE = create("has_nether_bristle_sparse");

        private static TagKey<Biome> create(String name)
        {   return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, name));
        }
    }

    public static final class EntityTypes
    {
        public static final TagKey<EntityType<?>> GHOSTLY = create("ghostly");

        private static TagKey<EntityType<?>> create(String name)
        {   return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, name));
        }
    }

    public static final class Items
    {
        public static final TagKey<Item> AURUM_REPAIR_MATERIALS = create("aurum_repair_materials");
        public static final TagKey<Item> WITHERED_REPAIR_MATERIALS = create("withered_repair_materials");
        public static final TagKey<Item> HOGLIN_HIDE_REPAIR_MATERIALS = create("hoglin_hide_repair_materials");
        public static final TagKey<Item> WITHERED = create("withered");

        private static TagKey<Item> create(String name)
        {   return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, name));
        }

        private static TagKey<Item> createCommon(String name)
        {   return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }
}
