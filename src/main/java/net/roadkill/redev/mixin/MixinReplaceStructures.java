package net.roadkill.redev.mixin;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.datafixers.util.Pair;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.ResourceOrTagKeyArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.SectionPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.commands.LocateCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.Set;

@Mixin({ChunkGenerator.class, LocateCommand.class})
public class MixinReplaceStructures
{
    @Mixin(ChunkGenerator.class)
    public static class DisableVanillaStrongholdsMixin
    {
        /**
         * Prevents vanilla strongholds from generating.
         */
        @Inject(method = "tryGenerateStructure", at = @At(value = "HEAD"), cancellable = true)
        void disableVanillaStrongholds(StructureSet.StructureSelectionEntry structureSetEntry, StructureManager structureManager, RegistryAccess registryAccess,
                                       RandomState randomState, StructureTemplateManager structureTemplateManager, long seed, ChunkAccess chunkAccess,
                                       ChunkPos chunkPos, SectionPos sectionPos, CallbackInfoReturnable<Boolean> cir)
        {
            if (structureSetEntry.structure().value().type() == StructureType.STRONGHOLD
            ||  structureSetEntry.structure().value().type() == StructureType.DESERT_PYRAMID)
            {   cir.setReturnValue(false);
            }
        }

        /**
         * Fixes a NPE being thrown as a result of no valid vanilla stronghold positions being found when using an Eye of Ender.
         * When this function is called w/ vanilla strongholds, we use an absurd position in the return value that should never impact
         * normal gameplay.
         * This should allow {@link ChunkGenerator#findNearestMapStructure} to continue as normal in virtually any circumstance.
         */
        @Inject(
                method = "getNearestGeneratedStructure(Ljava/util/Set;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/core/BlockPos;ZLnet/minecraft/world/level/levelgen/structure/placement/ConcentricRingsStructurePlacement;)Lcom/mojang/datafixers/util/Pair;",
                at = @At(value = "HEAD"),
                cancellable = true)
        public void disableVanillaStrongholds2(Set<Holder<Structure>> structureHolders, ServerLevel serverLevel, StructureManager structureManager, BlockPos blockPos,
                                               boolean bl, ConcentricRingsStructurePlacement structurePlacement, CallbackInfoReturnable<Pair<BlockPos, Holder<Structure>>> cir)
        {
            for (Holder<Structure> structureHolder : structureHolders)
            {
                if (structureHolder.is(ResourceLocation.withDefaultNamespace("stronghold"))
                ||  structureHolder.is(ResourceLocation.withDefaultNamespace("desert_pyramid")))
                {   cir.setReturnValue(Pair.of(new BlockPos(29000000, 0, 29000000), structureHolder));
                }
            }
        }
    }

    @Mixin(LocateCommand.class)
    public static class LocateStrongholdCommandMixin
    {
        @Inject(method = "locateStructure", at = @At(value = "HEAD"))
        private static void overrideLocateVanillaStronghold(CommandSourceStack cmdSource, ResourceOrTagKeyArgument.Result<Structure> result, CallbackInfoReturnable<Integer> ci)
        throws CommandSyntaxException
        {
            Optional<ResourceKey<Structure>> optional = result.unwrap().left();
            if (optional.isPresent()
            && (optional.get().location().equals(ResourceLocation.withDefaultNamespace("stronghold")))
            ||  optional.get().location().equals(ResourceLocation.withDefaultNamespace("desert_pyramid")))
            {   throw new SimpleCommandExceptionType(Component.literal(String.format("Use /locate structure redev:%s instead!", optional.get().location().getPath()))).create();
            }
        }
    }
}
