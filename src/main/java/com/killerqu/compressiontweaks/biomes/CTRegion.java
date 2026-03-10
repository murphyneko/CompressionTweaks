package com.killerqu.compressiontweaks.biomes;

import com.killerqu.compressiontweaks.CompressionTweaks;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class CTRegion extends Region {
    public static final ResourceLocation LOCATION = new ResourceLocation(CompressionTweaks.MODID, "overworld_main");
    public CTRegion(int weight) {
        super(LOCATION, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {

        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.DESERT, CTBiomes.VALLEY_BIOME);
            builder.replaceBiome(Biomes.PLAINS, CTBiomes.END_BIOME);
            builder.replaceBiome(Biomes.FOREST, CTBiomes.DELTAS_BIOME);
            builder.replaceBiome(Biomes.TAIGA, CTBiomes.CRIMSON_BIOME);
            builder.replaceBiome(Biomes.JUNGLE, CTBiomes.WARPED_BIOME);
            builder.replaceBiome(Biomes.BEACH, CTBiomes.VOID_BIOME);
            builder.replaceBiome(Biomes.SAVANNA, CTBiomes.WASTES_BIOME);
        });
    }
}