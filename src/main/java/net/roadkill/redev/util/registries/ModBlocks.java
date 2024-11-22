package net.roadkill.redev.util.registries;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.roadkill.redev.common.block.CaramineRyeBlock;
import net.roadkill.redev.common.block.WhispurRootBlock;
import net.roadkill.redev.core.init.BlockInit;

public class ModBlocks
{
    public static final class WoodTypes
    {
        public static final WoodType SCRAPWOOD = WoodType.register(new WoodType("scrapwood", BlockSets.SCRAPWOOD));
        public static final WoodType WHISPUR = WoodType.register(new WoodType("whispur", BlockSets.WHISPUR));
        public static final WoodType PETRIFIED = WoodType.register(new WoodType("petrified", BlockSets.PETRIFIED));
        public static final WoodType SHADE = WoodType.register(new WoodType("shade", BlockSets.SHADE));
    }

    public static final class BlockSets
    {
        public static final BlockSetType SCRAPWOOD = BlockSetType.register(new BlockSetType("scrapwood",
                                                                                            true, true, true,
                                                                                            BlockSetType.PressurePlateSensitivity.EVERYTHING,
                                                                                            SoundType.NETHER_WOOD,
                                                                                            SoundEvents.NETHER_WOOD_DOOR_CLOSE, SoundEvents.NETHER_WOOD_DOOR_OPEN,
                                                                                            SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE, SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN,
                                                                                            SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON,
                                                                                            SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF, SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON));

        public static final BlockSetType WHISPUR = BlockSetType.register(new BlockSetType("whispur",
                                                                                          true, true, true,
                                                                                          BlockSetType.PressurePlateSensitivity.EVERYTHING,
                                                                                          SoundType.NETHER_WOOD,
                                                                                          SoundEvents.NETHER_WOOD_DOOR_CLOSE, SoundEvents.NETHER_WOOD_DOOR_OPEN,
                                                                                          SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE, SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN,
                                                                                          SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON,
                                                                                          SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF, SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON));

        public static final BlockSetType PETRIFIED = BlockSetType.register(new BlockSetType("petrified",
                                                                                            true, true, true,
                                                                                            BlockSetType.PressurePlateSensitivity.EVERYTHING,
                                                                                            ModSoundType.PETRIFIED_PLANKS,
                                                                                            ModSounds.PETRIFIED_DOOR_CLOSE, ModSounds.PETRIFIED_DOOR_OPEN,
                                                                                            ModSounds.PETRIFIED_DOOR_CLOSE, ModSounds.PETRIFIED_DOOR_OPEN,
                                                                                            SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON,
                                                                                            SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON));

        public static BlockSetType SHADE = BlockSetType.register(new BlockSetType("shade",
                                                                                  true, true, true,
                                                                                  BlockSetType.PressurePlateSensitivity.EVERYTHING,
                                                                                  SoundType.WOOD,
                                                                                  SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN,
                                                                                  SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN,
                                                                                  SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON,
                                                                                  SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON));
    }
}
