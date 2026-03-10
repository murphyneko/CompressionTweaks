package com.killerqu.compressiontweaks.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class CTCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> BOULDER_COMPASS;
    public static final ForgeConfigSpec.ConfigValue<Integer> CLOUD_BOTTLING_MIN_Y;
    public static final ForgeConfigSpec.ConfigValue<Integer> CLOUD_BOTTLING_MAX_Y;
    public static final ForgeConfigSpec.ConfigValue<Integer> COMPRESSOR_FLUID_CAPACITY;
    public static final ForgeConfigSpec.ConfigValue<Integer> TABLE_OUTPUT_SLOTS;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> EMI_BLACKLIST;

    static {
        BUILDER.push("CompressionTweaks Config");

        BOULDER_COMPASS = BUILDER.comment("Whether to enable the AE2 meteor compass to also track tagged blocks spawned in jigsaw structures. Note that disabling this option down the line cannot automatically cleanup existing targets.")
                .define("Enable Boulder Compass", true);
        CLOUD_BOTTLING_MIN_Y = BUILDER.comment("The minimum y-level where bottling up clouds is possible")
                .define("Cloud in a Bottle Minimum Y-Level", 191);
        CLOUD_BOTTLING_MAX_Y = BUILDER.comment("The maximum y-level where bottling up clouds is possible")
                .define("Cloud in a Bottle Maximum Y-Level", 196);
        COMPRESSOR_FLUID_CAPACITY = BUILDER.comment("The capacity of the internal tanks for the Vintage Improvements compressor")
                .define("VI Compressor Fluid Capacity", 1000);
        TABLE_OUTPUT_SLOTS = BUILDER.comment("The amount of output slots in the Vintage Improvements vibrating table")
                .define("VI Vibrating Table Output Slots", 9);
        EMI_BLACKLIST = BUILDER.comment("A list of EMI recipe IDs to be blacklisted. Should be used for hardcoded integrations.")
                        .defineList("EMI Recipe Blacklist", List.of(), o -> o instanceof String s && ResourceLocation.isValidResourceLocation(s));

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
