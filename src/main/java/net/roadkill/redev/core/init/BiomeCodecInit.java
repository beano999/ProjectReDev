package net.roadkill.redev.core.init;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.data.biome_modifiers.AddSpawnsBiomeModifier;

public class BiomeCodecInit
{
    public static DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, ReDev.MOD_ID);

    public static RegistryObject<Codec<AddSpawnsBiomeModifier>> ADD_SPAWNS_CODEC = BIOME_MODIFIER_SERIALIZERS.register("add_spawns", () ->
    RecordCodecBuilder.create(builder -> builder.group(
            Codec.BOOL.fieldOf("dummy").forGetter(AddSpawnsBiomeModifier::dummy)
      ).apply(builder, AddSpawnsBiomeModifier::new)));
}
