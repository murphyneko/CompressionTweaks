package com.killerqu.compressiontweaks.biomes;

import com.killerqu.compressiontweaks.CompressionTweaks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.biome.EndBiomes;
import net.minecraft.data.worldgen.biome.NetherBiomes;
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
    public static final ResourceKey<Biome> WASTES_BIOME = register("nether_wastes");
    public static final ResourceKey<Biome> VOID_BIOME = register("void_biome");

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(CompressionTweaks.MODID, name));
    }

    public static void bootstrap(BootstapContext<Biome> ctx){
        HolderGetter<ConfiguredWorldCarver<?>> carver = ctx.lookup(Registries.CONFIGURED_CARVER);
        HolderGetter<PlacedFeature> placedFeature = ctx.lookup(Registries.PLACED_FEATURE);

        //ctx.register(END_BIOME, CTOverworldBiomes.endBiome(placedFeature, carver));
        //ctx.register(VALLEY_BIOME, CTOverworldBiomes.valleyBiome(placedFeature, carver));
        //ctx.register(DELTAS_BIOME, CTOverworldBiomes.deltasBiome(placedFeature, carver));
        //ctx.register(CRIMSON_BIOME, CTOverworldBiomes.crimsonBiome(placedFeature, carver));
        //ctx.register(WARPED_BIOME, CTOverworldBiomes.warpedBiome(placedFeature, carver));
        ctx.register(END_BIOME, EndBiomes.endHighlands(placedFeature, carver));
        ctx.register(VALLEY_BIOME, NetherBiomes.soulSandValley(placedFeature, carver));
        ctx.register(DELTAS_BIOME, NetherBiomes.basaltDeltas(placedFeature, carver));
        ctx.register(CRIMSON_BIOME, NetherBiomes.crimsonForest(placedFeature, carver));
        ctx.register(WARPED_BIOME, NetherBiomes.warpedForest(placedFeature, carver));
        ctx.register(WASTES_BIOME, NetherBiomes.netherWastes(placedFeature, carver));
        ctx.register(VOID_BIOME, CTOverworldBiomes.voidBiome(placedFeature, carver));
    }

}
