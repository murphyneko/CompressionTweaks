package com.killerqu.compressiontweaks;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CTRemapper {
    public static final Set<String> NAMESPACES;
    // Map from original namespace to (map from original location to new)
    public static final Map<String, Map<String, String>> REMAPS = new HashMap<>();

    private static void directRemap(Map<String, String> map, String to, String from, String value) {
        map.put(from + ":" + value, to + ":" + value);
    }

    private static void bwgDirectRemap(Map<String, String> map, String value) {
        directRemap(map, "biomeswevegone", "byg", value);
    }

    static {
        HashMap<String, String> bwgRemaps = new HashMap<>();
        bwgDirectRemap(bwgRemaps, "apple_fruit_block");
        bwgDirectRemap(bwgRemaps, "green_apple_fruit_block");
        bwgDirectRemap(bwgRemaps, "fir_chest_boat");
        bwgDirectRemap(bwgRemaps, "cypress_chest_boat");
        bwgDirectRemap(bwgRemaps, "skyris_chest_boat");
        bwgDirectRemap(bwgRemaps, "witch_hazel_chest_boat");
        bwgDirectRemap(bwgRemaps, "ebony_chest_boat");
        bwgDirectRemap(bwgRemaps, "aspen_chest_boat");
        bwgDirectRemap(bwgRemaps, "baobab_chest_boat");
        bwgDirectRemap(bwgRemaps, "cika_chest_boat");
        bwgDirectRemap(bwgRemaps, "dacite_tile");
        bwgDirectRemap(bwgRemaps, "holly_chest_boat");
        bwgDirectRemap(bwgRemaps, "jacaranda_chest_boat");
        bwgDirectRemap(bwgRemaps, "redwood_chest_boat");
        bwgDirectRemap(bwgRemaps, "rainbow_eucalyptus_chest_boat");
        bwgDirectRemap(bwgRemaps, "palm_chest_boat");
        bwgDirectRemap(bwgRemaps, "pine_chest_boat");
        bwgDirectRemap(bwgRemaps, "maple_chest_boat");
        bwgDirectRemap(bwgRemaps, "mahogany_chest_boat");
        bwgDirectRemap(bwgRemaps, "zelkova_chest_boat");
        bwgDirectRemap(bwgRemaps, "willow_chest_boat");
        bwgDirectRemap(bwgRemaps, "white_mangrove_chest_boat");
        bwgDirectRemap(bwgRemaps, "imbued_blue_enchanted_log");
        bwgDirectRemap(bwgRemaps, "imbued_green_enchanted_log");
        bwgDirectRemap(bwgRemaps, "blue_enchanted_chest_boat");
        bwgDirectRemap(bwgRemaps, "green_enchanted_chest_boat");
        bwgDirectRemap(bwgRemaps, "blue_chiseled_sandstone");
        bwgDirectRemap(bwgRemaps, "blue_cut_sandstone");
        bwgDirectRemap(bwgRemaps, "blue_smooth_sandstone");
        bwgDirectRemap(bwgRemaps, "pink_cut_sandstone");
        bwgDirectRemap(bwgRemaps, "pink_chiseled_sandstone");
        bwgDirectRemap(bwgRemaps, "tiny_lilypads");

        bwgRemaps.put("byg:lush_grass_path", "biomeswevegone:lush_dirt_path");

        REMAPS.put("byg", bwgRemaps);

        NAMESPACES = Set.copyOf(REMAPS.keySet());
    }

    // Basically 1-to-1 from the BMC Patcher because there's really not a better way
    public static String remapID(String s) {
        int idx = s.indexOf(':');
        if(idx == -1)
            return s;

        String ns = s.substring(0, idx);
        if(!NAMESPACES.contains(ns))
            return s;

        return REMAPS.get(ns).getOrDefault(s, s);
    }
}
