package net.momostudios.redev.core.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.momostudios.redev.ReDev;
import net.momostudios.redev.common.block_entity.ModCampfireBlockEntity;
import net.momostudios.redev.util.registries.ModBlocks;

public class BlockEntityInit
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ReDev.MOD_ID);

    public static final RegistryObject<BlockEntityType<ModCampfireBlockEntity>> CAMPFIRE_BLOCK_ENTITY_TYPE =
            BLOCK_ENTITY_TYPES.register("campfire", () -> BlockEntityType.Builder.of(ModCampfireBlockEntity::new,
                                        ModBlocks.ACACIA_CAMPFIRE,  ModBlocks.BIRCH_CAMPFIRE,
                                        ModBlocks.CRIMSON_CAMPFIRE, ModBlocks.DARK_OAK_CAMPFIRE,
                                        ModBlocks.JUNGLE_CAMPFIRE,  ModBlocks.MANGROVE_CAMPFIRE,
                                        ModBlocks.SPRUCE_CAMPFIRE,  ModBlocks.WARPED_CAMPFIRE,
                                        ModBlocks.ACACIA_SOUL_CAMPFIRE,  ModBlocks.BIRCH_SOUL_CAMPFIRE,
                                        ModBlocks.CRIMSON_SOUL_CAMPFIRE, ModBlocks.DARK_OAK_SOUL_CAMPFIRE,
                                        ModBlocks.JUNGLE_SOUL_CAMPFIRE,  ModBlocks.MANGROVE_SOUL_CAMPFIRE,
                                        ModBlocks.SPRUCE_SOUL_CAMPFIRE,  ModBlocks.WARPED_SOUL_CAMPFIRE).build(null));
}