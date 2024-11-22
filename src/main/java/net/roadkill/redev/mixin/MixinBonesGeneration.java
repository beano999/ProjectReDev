package net.roadkill.redev.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.structures.NetherFossilPieces;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.common.block.WhispurRootBlock;
import net.roadkill.redev.core.init.BlockInit;
import net.roadkill.redev.util.registries.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(NetherFossilPieces.NetherFossilPiece.class)
public class MixinBonesGeneration
{
    @Inject(method = "postProcess", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/BoundingBox;encapsulate(Lnet/minecraft/world/level/levelgen/structure/BoundingBox;)Lnet/minecraft/world/level/levelgen/structure/BoundingBox;"))
    public void onPostProcess(WorldGenLevel level, StructureManager structureManager, ChunkGenerator chunkGenerator, RandomSource rand, BoundingBox box, ChunkPos chunkPos, BlockPos pos, CallbackInfo ci)
    {
        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
        for (int x = box.minX(); x < box.maxX(); x++)
        {
            for (int z = box.minZ(); z < box.maxZ(); z++)
            {
                for (int y = box.minY(); y < box.maxY(); y++)
                {
                    blockPos.set(x, y, z);
                    if (rand.nextInt(5) == 0 && Arrays.stream(Direction.values()).anyMatch(direction -> WhispurRootBlock.isDirectSupport(level.getBlockState(blockPos.relative(direction)))))
                    {
                        int rootLength = rand.nextInt(5) + 3;
                        for (int i = 0; i < rootLength; i++)
                        {
                            Direction direction = null;
                            List<Direction> possibleDirs = new ArrayList<>(List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST));
                            while (!possibleDirs.isEmpty())
                            {
                                Direction finalDir = direction = rand.nextInt(2) == 0 ? possibleDirs.get(rand.nextInt(0, possibleDirs.size())) : Direction.UP;
                                BlockPos newPos = blockPos.relative(direction);

                                if (level.getBlockState(newPos).isAir()
                                && Direction.stream().noneMatch(dir -> dir != finalDir.getOpposite() && level.getBlockState(newPos.relative(dir)).is(BlockInit.WHISPUR_ROOT)))
                                {
                                    BlockState state = BlockInit.WHISPUR_ROOT.value().defaultBlockState()
                                            .setValue(WhispurRootBlock.DISTANCE, i+1).setValue(WhispurRootBlock.GROWING, false)
                                            .setValue(WhispurRootBlock.NORTH, WhispurRootBlock.isSupportingBlock(level.getBlockState(blockPos.north())))
                                            .setValue(WhispurRootBlock.EAST, WhispurRootBlock.isSupportingBlock(level.getBlockState(blockPos.east())))
                                            .setValue(WhispurRootBlock.SOUTH, WhispurRootBlock.isSupportingBlock(level.getBlockState(blockPos.south())))
                                            .setValue(WhispurRootBlock.WEST, WhispurRootBlock.isSupportingBlock(level.getBlockState(blockPos.west())))
                                            .setValue(WhispurRootBlock.UP, WhispurRootBlock.isSupportingBlock(level.getBlockState(blockPos.above())))
                                            .setValue(WhispurRootBlock.DOWN, WhispurRootBlock.isSupportingBlock(level.getBlockState(blockPos.below())));

                                    level.setBlock(blockPos, state, 3);
                                    state.updateNeighbourShapes(level, blockPos, 3);
                                    break;
                                }
                                else if (direction != Direction.UP)
                                {   possibleDirs.remove(direction);
                                }
                            }
                            if (direction != null)
                            {   blockPos.move(direction);
                            }
                        }
                    }
                    blockPos.set(x, y, z);
                }
            }
        }
    }
}
