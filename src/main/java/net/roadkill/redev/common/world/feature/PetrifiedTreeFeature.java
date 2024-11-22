package net.roadkill.redev.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.roadkill.redev.ReDev;

import java.util.Optional;

public class PetrifiedTreeFeature extends Feature<NoneFeatureConfiguration>
{
    public PetrifiedTreeFeature(Codec<NoneFeatureConfiguration> codec)
    {   super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> placement)
    {
        WorldGenLevel level = placement.level();
        if (level.getServer() == null) return false;
        BlockPos.MutableBlockPos pos = placement.origin().mutable();
        int startY = pos.getY();
        int minHeight = level.getMinY();
        int maxHeight = level.getMaxY();
        for (int i = -10; i < 10; i++)
        {
            pos.setY(startY + i);
            if (pos.getY() < minHeight) continue;
            if (pos.getY() > maxHeight) break;
            if (level.getBlockState(pos).isAir())
            {   break;
            }
        }
        if (level.getBlockState(pos.below()).is(Blocks.BASALT) && level.getBlockState(pos.above()).isAir())
        {
            StructureTemplateManager structuretemplatemanager = level.getServer().getStructureManager();
            Optional<StructureTemplate> treeOpt = structuretemplatemanager.get(ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "petrified_tree/petrified_tree_" + level.getRandom().nextIntBetweenInclusive(0, 12)));
            treeOpt.ifPresent(tree ->
            {   Vec3i size = tree.getSize();
                pos.move(0, -level.getRandom().nextInt(1, size.getY() / 2), 0);
                tree.placeInWorld(level, pos.offset(size.getX() / -2, 0, size.getZ() / -2), pos, new StructurePlaceSettings(), placement.random(), 2);
            });
            return true;
        }
        return false;
    }
}
