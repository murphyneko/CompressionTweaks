package com.killerqu.compressiontweaks.biomes;

import com.killerqu.compressiontweaks.CompressionTweaks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class CTBiomes {
    public static final ResourceKey<Biome> END_BIOME = register("end");
    public static final ResourceKey<Biome> VALLEY_BIOME = register("soulsand_valley");
    public static final ResourceKey<Biome> DELTAS_BIOME = register("basalt_deltas");
    public static final ResourceKey<Biome> CRIMSON_BIOME = register("crimson_forest");
    public static final ResourceKey<Biome> WARPED_BIOME = register("warped_forest");
    public static final ResourceKey<Biome> VOID_BIOME = register("void_biome");

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(CompressionTweaks.MODID, name));
    }

    public static void bootstrap(BootstapContext<Biome> ctx){
        HolderGetter<ConfiguredWorldCarver<?>> carver = ctx.lookup(Registries.CONFIGURED_CARVER);
        HolderGetter<PlacedFeature> placedFeature = ctx.lookup(Registries.PLACED_FEATURE);

        ctx.register(END_BIOME, OverworldBiomes.endBiome(placedFeature, carver));
        ctx.register(VALLEY_BIOME, OverworldBiomes.valleyBiome(placedFeature, carver));
        ctx.register(DELTAS_BIOME, OverworldBiomes.deltasBiome(placedFeature, carver));
        ctx.register(CRIMSON_BIOME, OverworldBiomes.crimsonBiome(placedFeature, carver));
        ctx.register(WARPED_BIOME, OverworldBiomes.warpedBiome(placedFeature, carver));
        ctx.register(VOID_BIOME, OverworldBiomes.voidBiome(placedFeature, carver));
    }

}
