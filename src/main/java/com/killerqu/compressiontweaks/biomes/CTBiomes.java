package com.killerqu.compressiontweaks.biomes;

import com.killerqu.compressiontweaks.CompressionTweaks;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.biome.EndBiomes;
import net.minecraft.data.worldgen.biome.NetherBiomes;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class CTBiomes {
    //NETHER BIOMES
    public static final ResourceKey<Biome> VALLEY_BIOME = register("soulsand_valley");
    public static final ResourceKey<Biome> DELTAS_BIOME = register("basalt_deltas");
    public static final ResourceKey<Biome> CRIMSON_BIOME = register("crimson_forest");
    public static final ResourceKey<Biome> WARPED_BIOME = register("warped_forest");
    public static final ResourceKey<Biome> WASTES_BIOME = register("nether_wastes");
    //END BIOMES
    public static final ResourceKey<Biome> END_BIOME = register("end");
    public static final ResourceKey<Biome> PEARLESCENT_BIOME = register("pearlescent_cliffs");
    public static final ResourceKey<Biome> ENTROPIC_BIOME = register("entropic_delta");
    public static final ResourceKey<Biome> BLONDE_BIOME = register("blonde_grove");
    //OTHER BIOMES
    public static final ResourceKey<Biome> VOID_BIOME = register("void_biome");
    public static final ResourceKey<Biome> SCULK_LANDS = register("sculk_lands");
    public static final ResourceKey<Biome> GLIMMERING_WEALD = register("glimmering_weald");
    public static final ResourceKey<Biome> MYSTICAL_GROVE = register("mystical_grove");
    //Special
    public static final ResourceKey<Biome> IMPACT_SITE = register("impact_site");

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
        ctx.register(PEARLESCENT_BIOME, EndBiomes.endBarrens(placedFeature, carver));
        ctx.register(ENTROPIC_BIOME, EndBiomes.theEnd(placedFeature, carver));
        ctx.register(BLONDE_BIOME, EndBiomes.endHighlands(placedFeature, carver));
        ctx.register(SCULK_LANDS, OverworldBiomes.deepDark(placedFeature, carver));
        ctx.register(GLIMMERING_WEALD, OverworldBiomes.dripstoneCaves(placedFeature, carver));
        ctx.register(MYSTICAL_GROVE, OverworldBiomes.meadowOrCherryGrove(placedFeature, carver, false));
        ctx.register(IMPACT_SITE, OverworldBiomes.dripstoneCaves(placedFeature, carver));
    }

}
