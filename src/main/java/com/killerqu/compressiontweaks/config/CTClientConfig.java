package com.killerqu.compressiontweaks.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class CTClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> EMI_BLACKLIST;

    static {
        BUILDER.push("CompressionTweaks Client Config");

        EMI_BLACKLIST = BUILDER.comment("A list of EMI recipe IDs to be blacklisted. Should be used for hardcoded integrations.")
                .defineList("EMI Recipe Blacklist", List.of(), o -> o instanceof String);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
