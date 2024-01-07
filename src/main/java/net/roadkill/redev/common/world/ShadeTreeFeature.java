package net.roadkill.redev.common.world;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.commands.PlaceCommand;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.roadkill.redev.ReDev;

import java.util.Optional;

public class ShadeTreeFeature extends Feature<NoneFeatureConfiguration>
{
    public ShadeTreeFeature(Codec<NoneFeatureConfiguration> codec)
    {   super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> placement)
    {
        WorldGenLevel level = placement.level();
        if (level.getServer() == null) return false;
        BlockPos.MutableBlockPos pos = placement.origin().mutable();
        if (level.getBlockState(pos.below()).is(BlockTags.SAND))
        {
            StructureTemplateManager structuretemplatemanager = level.getServer().getStructureManager();
            int treeVariant = level.getRandom().nextIntBetweenInclusive(0, 4);

            String color = switch (level.getRandom().nextInt(3))
            {   default -> "teal";
                case 1 -> "red";
                case 2 -> "purple";
            };
            Optional<StructureTemplate> treeOpt = structuretemplatemanager.get(new ResourceLocation(ReDev.MOD_ID, "shade_tree/" + color + "_shade_tree_" + treeVariant));

            treeOpt.ifPresent(tree ->
            {   Vec3i size = tree.getSize();
                tree.placeInWorld(level, pos.offset(size.getX() / -2, 0, size.getZ() / -2), pos, new StructurePlaceSettings(), placement.random(), 2);
            });
            return true;
        }
        return false;
    }
}
