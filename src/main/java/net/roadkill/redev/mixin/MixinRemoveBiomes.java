package net.roadkill.redev.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class MixinRemoveBiomes
{
    @Mixin(OverworldBiomeBuilder.class)
    public static class MangroveSwamp
    {
        @ModifyArg(method = "addMidSlice", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/OverworldBiomeBuilder;addSurfaceBiome(Ljava/util/function/Consumer;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;FLnet/minecraft/resources/ResourceKey;)V",
                ordinal = 2), index = 7)
        private ResourceKey<Biome> removeMidSlice(ResourceKey<Biome> pKey)
        {   return Biomes.SWAMP;
        }

        @ModifyArg(method = "addLowSlice", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/OverworldBiomeBuilder;addSurfaceBiome(Ljava/util/function/Consumer;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;FLnet/minecraft/resources/ResourceKey;)V",
                ordinal = 2), index = 7)
        private ResourceKey<Biome> removeLowSlice(ResourceKey<Biome> pKey)
        {   return Biomes.SWAMP;
        }

        @ModifyArg(method = "addValleys", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/OverworldBiomeBuilder;addSurfaceBiome(Ljava/util/function/Consumer;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;FLnet/minecraft/resources/ResourceKey;)V",
                ordinal = 9), index = 7)
        private ResourceKey<Biome> removeValleys(ResourceKey<Biome> pKey)
        {   return Biomes.SWAMP;
        }
    }

    @Mixin(OverworldBiomeBuilder.class)
    public static class CherryGrove
    {
        @Shadow @Final private ResourceKey<Biome>[][] PLATEAU_BIOMES_VARIANT;

        @Inject(method = "<init>", at = @At("TAIL"))
        private void removeCherryGrove(CallbackInfo ci)
        {
            for (int i = 0; i < this.PLATEAU_BIOMES_VARIANT.length; i++)
            {
                for (int j = 0; j < this.PLATEAU_BIOMES_VARIANT[i].length; j++)
                {
                    ResourceKey<Biome> biome = this.PLATEAU_BIOMES_VARIANT[i][j];
                    if (biome == Biomes.CHERRY_GROVE)
                    {   this.PLATEAU_BIOMES_VARIANT[i][j] = null;
                    }
                }
            }
        }
    }
}
