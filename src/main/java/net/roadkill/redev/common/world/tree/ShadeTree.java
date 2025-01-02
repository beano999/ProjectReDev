package net.roadkill.redev.common.world.tree;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.JigsawBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.common.block.ShadeSaplingBlock;

import java.util.Optional;

public class ShadeTree
{
    public static boolean place(WorldGenLevel level, BlockPos origin, Color variant)
    {
        BlockPos.MutableBlockPos pos = origin.mutable();
        StructureTemplateManager structuretemplatemanager = level.getServer().getStructureManager();
        int treeVariant = level.getRandom().nextIntBetweenInclusive(0, 4);

        String color = switch (variant)
        {   case NORMAL -> "";
            case TEAL -> "teal_";
            case RED -> "red_";
            case PURPLE -> "purple_";
        };
        Rotation rotation = Rotation.getRandom(level.getRandom());
        Optional<StructureTemplate> tree = structuretemplatemanager.get(ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "shade_tree/" + color + "shade_tree_" + treeVariant));

        if (tree.isPresent())
        {
            // Get block palette for tree variant
            StructurePlaceSettings randRotationSettings = new StructurePlaceSettings().setRotation(rotation);
            StructureTemplate.Palette palette = randRotationSettings.getRandomPalette(tree.get().palettes, origin.offset(getRotatedOffset(treeVariant, rotation)));

            BlockPos treePos = origin.offset(getOffset(treeVariant));
            for (StructureTemplate.StructureBlockInfo blockInfo : palette.blocks())
            {
                if (blockInfo.state().getBlock() instanceof JigsawBlock)
                {
                    treePos = pos.set(origin).move(blockInfo.pos());
                    break;
                }
            }

            for (StructureTemplate.StructureBlockInfo blockInfo : palette.blocks())
            {
                BlockState state = blockInfo.state();
                pos.set(treePos).move(blockInfo.pos());
                BlockState blockAt = level.getBlockState(pos);
                if (blockAt.canBeReplaced() || blockAt.getBlock() instanceof ShadeSaplingBlock)
                {   level.setBlock(pos, state, 3);
                }
            }
            return true;
        }
        return false;
    }

    public static BlockPos getOffset(int variant)
    {
        return switch (variant)
        {
            case 1 -> new BlockPos(-6, 0, -7);
            case 2 -> new BlockPos(-8, 0, -5);
            case 3 -> new BlockPos(-8, 0, -6);
            case 4 -> new BlockPos(-7, 0, -6);
            default -> new BlockPos(-5, 0, -6);
        };
    }

    public static Vec3i getRotatedOffset(int variant, Rotation rotation)
    {
        Vec3i offset = switch (variant)
        {
            case 1 -> new Vec3i(-6, 0, -7);
            case 2 -> new Vec3i(-8, 0, -5);
            case 3 -> new Vec3i(-8, 0, -6);
            case 4 -> new Vec3i(-7, 0, -6);
            default -> new Vec3i(-5, 0, -6);
        };
        return switch (rotation)
        {
            case CLOCKWISE_90 -> new Vec3i(offset.getZ(), offset.getY(), -offset.getX());
            case CLOCKWISE_180 -> new Vec3i(-offset.getX(), offset.getY(), -offset.getZ());
            case COUNTERCLOCKWISE_90 -> new Vec3i(-offset.getZ(), offset.getY(), offset.getX());
            default -> offset;
        };
    }

    public enum Color
    {
        NORMAL,
        TEAL,
        RED,
        PURPLE
    }
}
