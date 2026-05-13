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

    private static void bwgDirectRemap(String value) {
        directRemap(bwgRemaps, "biomeswevegone", "byg", value);
    }

    public static HashMap<String, String> bwgRemaps = new HashMap<>();

    static {
/*

 /$$   /$$
| $$$ | $$
| $$$$| $$  /$$$$$$  /$$    /$$ /$$$$$$   /$$$$$$
| $$ $$ $$ /$$__  $$|  $$  /$$//$$__  $$ /$$__  $$
| $$  $$$$| $$$$$$$$ \  $$/$$/| $$$$$$$$| $$  \__/
| $$\  $$$| $$_____/  \  $$$/ | $$_____/| $$
| $$ \  $$|  $$$$$$$   \  $/  |  $$$$$$$| $$
|__/  \__/ \_______/    \_/    \_______/|__/



  /$$$$$$                      /$$
 /$$__  $$                    |__/
| $$  \ $$  /$$$$$$   /$$$$$$  /$$ /$$$$$$$
| $$$$$$$$ /$$__  $$ |____  $$| $$| $$__  $$
| $$__  $$| $$  \ $$  /$$$$$$$| $$| $$  \ $$
| $$  | $$| $$  | $$ /$$__  $$| $$| $$  | $$
| $$  | $$|  $$$$$$$|  $$$$$$$| $$| $$  | $$ /$$
|__/  |__/ \____  $$ \_______/|__/|__/  |__/|__/
           /$$  \ $$
          |  $$$$$$/
           \______/

*/

        // === Dacite
        bwgRemaps.put("byg:dacite", "biomeswevegone:white_dacite");
        bwgRemaps.put("byg:dacite_slab", "biomeswevegone:white_dacite_slab");
        bwgRemaps.put("byg:dacite_stairs", "biomeswevegone:white_dacite_stairs");
        bwgRemaps.put("byg:dacite_wall", "biomeswevegone:white_dacite_wall");
        bwgRemaps.put("byg:dacite_bricks", "biomeswevegone:white_dacite_bricks");
        bwgRemaps.put("byg:dacite_brick_stairs", "biomeswevegone:white_dacite_brick_stairs");
        bwgRemaps.put("byg:dacite_brick_slab", "biomeswevegone:white_dacite_brick_slab");
        bwgRemaps.put("byg:dacite_brick_wall", "biomeswevegone:white_dacite_brick_wall");
        bwgRemaps.put("byg:dacite_cobblestone", "biomeswevegone:white_dacite_cobblestone");
        bwgRemaps.put("byg:dacite_cobblestone_stairs", "biomeswevegone:white_dacite_cobblestone_stairs");
        bwgRemaps.put("byg:dacite_cobblestone_slab", "biomeswevegone:white_dacite_cobblestone_slab");
        bwgRemaps.put("byg:dacite_cobblestone_wall", "biomeswevegone:white_dacite_cobblestone_wall");
        bwgRemaps.put("byg:dacite_pillar", "biomeswevegone:white_dacite_pillar");
        bwgRemaps.put("byg:dacite_tile", "biomeswevegone:white_dacite_tiles");
        bwgRemaps.put("byg:dacite_tile_stairs", "biomeswevegone:white_dacite_tile_stairs");
        bwgRemaps.put("byg:dacite_tile_slab", "biomeswevegone:white_dacite_tile_slab");
        bwgRemaps.put("byg:dacite_tile_wall", "biomeswevegone:white_dacite_tile_wall");
        bwgRemaps.put("byg:podzol_dacite", "biomeswevegone:white_podzol_dacite");
        bwgRemaps.put("byg:overgrown_dacite", "biomeswevegone:white_overgrown_dacite");

        // === Peat/Lush
        bwgDirectRemap("peat");
        bwgDirectRemap("lush_dirt");
        bwgDirectRemap("lush_grass_block");
        bwgRemaps.put("byg:lush_grass_path", "biomeswevegone:lush_dirt_path");
        bwgDirectRemap("lush_farmland");

        // === Red Rock
        bwgDirectRemap("red_rock");
        bwgDirectRemap("red_rock_stairs");
        bwgDirectRemap("red_rock_slab");
        bwgDirectRemap("red_rock_wall");
        bwgDirectRemap("red_rock_bricks");
        bwgDirectRemap("red_rock_brick_stairs");
        bwgDirectRemap("red_rock_brick_slab");
        bwgDirectRemap("red_rock_brick_wall");
        bwgDirectRemap("mossy_red_rock_bricks");
        bwgDirectRemap("mossy_red_rock_brick_stairs");
        bwgDirectRemap("mossy_red_rock_brick_slab");
        bwgDirectRemap("mossy_red_rock_brick_wall");
        bwgDirectRemap("chiseled_red_rock_bricks");
        bwgDirectRemap("chiseled_red_rock_brick_stairs");
        bwgDirectRemap("chiseled_red_rock_brick_slab");
        bwgDirectRemap("chiseled_red_rock_brick_wall");
        bwgDirectRemap("cracked_red_rock_bricks");
        bwgDirectRemap("cracked_red_rock_brick_stairs");
        bwgDirectRemap("cracked_red_rock_brick_slab");
        bwgDirectRemap("cracked_red_rock_brick_wall");

        // === Rocky Stone
        bwgDirectRemap("rocky_stone");
        bwgDirectRemap("rocky_stone_stairs");
        bwgDirectRemap("rocky_stone_slab");
        bwgDirectRemap("rocky_stone_wall");

        // === Travertine
        bwgRemaps.put("byg:travertine", "atmospheric:ivory_travertine");
        bwgRemaps.put("byg:travertine_stairs", "atmospheric:ivory_travertine_stairs");
        bwgRemaps.put("byg:travertine_slab", "atmospheric:ivory_travertine_slab");
        bwgRemaps.put("byg:travertine_wall", "atmospheric:ivory_travertine_wall");
        bwgRemaps.put("byg:polished_travertine", "stonezone:c/atmospheric/polished_cut_ivory_travertine");
        bwgRemaps.put("byg:polished_travertine_stairs", "stonezone:c/atmospheric/polished_cut_ivory_travertine_stairs");
        bwgRemaps.put("byg:polished_travertine_slab", "stonezone:c/atmospheric/polished_cut_ivory_travertine_slab");
        bwgRemaps.put("byg:polished_travertine_wall", "stonezone:c/atmospheric/polished_cut_ivory_travertine_wall");
        bwgRemaps.put("byg:chiseled_travertine", "atmospheric:chiseled_ivory_travertine");
        bwgRemaps.put("byg:chiseled_travertine_stairs", "stonezone:c/atmospheric/small_ivory_travertine_brick_stairs");
        bwgRemaps.put("byg:chiseled_travertine_slab", "stonezone:c/atmospheric/small_ivory_travertine_brick_slab");
        bwgRemaps.put("byg:chiseled_travertine_wall", "stonezone:c/atmospheric/small_ivory_travertine_brick_wall");

        // === Scoria
        bwgRemaps.put("byg:scoria_stone", "create:cut_scoria");
        bwgRemaps.put("byg:scoria_stone_stairs", "create:cut_scoria_stairs");
        bwgRemaps.put("byg:scoria_stone_slab", "create:cut_scoria_slab");
        bwgRemaps.put("byg:scoria_stone_wall", "create:cut_scoria_wall");
        bwgRemaps.put("byg:scoria_cobblestone", "create:cut_scoria");
        bwgRemaps.put("byg:scoria_cobblestone_stairs", "create:cut_scoria_stairs");
        bwgRemaps.put("byg:scoria_cobblestone_slab", "create:cut_scoria_slab");
        bwgRemaps.put("byg:scoria_cobblestone_wall", "create:cut_scoria_wall");
        bwgRemaps.put("byg:scoria_pillar", "create:scoria_pillar");
        bwgRemaps.put("byg:scoria_stonebrick", "create:cut_scoria_bricks");
        bwgRemaps.put("byg:scoria_stonebrick_stairs", "create:cut_scoria_brick_stairs");
        bwgRemaps.put("byg:scoria_stonebrick_slab", "create:cut_scoria_brick_slab");
        bwgRemaps.put("byg:scoria_stonebrick_wall", "create:cut_scoria_brick_wall");
        bwgRemaps.put("byg:cracked_scoria_stone_bricks", "create:cut_scoria_bricks");

        // === Soapstone
        bwgRemaps.put("byg:soapstone", "minecraft:blackstone");
        bwgRemaps.put("byg:soapstone_stairs", "minecraft:blackstone_stairs");
        bwgRemaps.put("byg:soapstone_slab", "minecraft:blackstone_slab");
        bwgRemaps.put("byg:soapstone_wall", "minecraft:blackstone_wall");
        bwgRemaps.put("byg:polished_soapstone", "minecraft:polished_blackstone");
        bwgRemaps.put("byg:polished_soapstone_stairs", "minecraft:polished_blackstone_stairs");
        bwgRemaps.put("byg:polished_soapstone_slab", "minecraft:polished_blackstone_slab");
        bwgRemaps.put("byg:polished_soapstone_wall", "minecraft:polished_blackstone_wall");
        bwgRemaps.put("byg:soapstone_bricks", "minecraft:polished_blackstone_bricks");
        bwgRemaps.put("byg:soapstone_brick_stairs", "minecraft:polished_blackstone_brick_stairs");
        bwgRemaps.put("byg:soapstone_brick_slab", "minecraft:polished_blackstone_brick_slab");
        bwgRemaps.put("byg:soapstone_brick_wall", "minecraft:polished_blackstone_brick_wall");
        bwgRemaps.put("byg:soapstone_pillar", "minecraft:blackstone_pillar");
        bwgRemaps.put("byg:soapstone_tile", "supplementaries:blackstone_tile");
        bwgRemaps.put("byg:soapstone_tile_stairs", "supplementaries:blackstone_tile_stairs");
        bwgRemaps.put("byg:soapstone_tile_slab", "supplementaries:blackstone_tile_slab");
        bwgRemaps.put("byg:soapstone_tile_wall", "supplementaries:blackstone_tile_wall");

        // === Windswept
        bwgDirectRemap("windswept_sand");
        bwgDirectRemap("windswept_sandstone");
        bwgDirectRemap("windswept_sandstone_stairs");
        bwgDirectRemap("windswept_sandstone_slab");
        bwgDirectRemap("windswept_sandstone_wall");
        bwgDirectRemap("chiseled_windswept_sandstone");
        bwgRemaps.put("byg:chiseled_windswept_sandstone_stairs", "biomeswevegone:smooth_windswept_sandstone_stairs");
        bwgRemaps.put("byg:chiseled_windswept_sandstone_slab", "biomeswevegone:smooth_windswept_sandstone_slab");
        bwgRemaps.put("byg:chiseled_windswept_sandstone_wall", "biomeswevegone:windswept_sandstone_wall");
        bwgDirectRemap("cut_windswept_sandstone");
        bwgRemaps.put("byg:cut_windswept_sandstone_stairs", "biomeswevegone:smooth_windswept_sandstone_stairs");
        bwgDirectRemap("cut_windswept_sandstone_slab");
        bwgRemaps.put("byg:cut_windswept_sandstone_wall", "biomeswevegone:windswept_sandstone_wall");
        bwgDirectRemap("smooth_windswept_sandstone");
        bwgDirectRemap("smooth_windswept_sandstone_stairs");
        bwgDirectRemap("smooth_windswept_sandstone_slab");
        bwgRemaps.put("byg:smooth_windswept_sandstone_wall", "biomeswevegone:windswept_sandstone_wall");
        bwgDirectRemap("windswept_sandstone_pillar");

        // === Red Sand
        bwgDirectRemap("cracked_red_sand");

        // === Aspen
        bwgDirectRemap("aspen_log");
        bwgDirectRemap("aspen_wood");
        bwgDirectRemap("stripped_aspen_log");
        bwgDirectRemap("stripped_aspen_wood");
        bwgDirectRemap("aspen_planks");
        bwgDirectRemap("aspen_stairs");
        bwgDirectRemap("aspen_slab");
        bwgDirectRemap("aspen_fence");
        bwgDirectRemap("aspen_fence_gate");
        bwgDirectRemap("aspen_door");
        bwgDirectRemap("aspen_trapdoor");
        bwgDirectRemap("aspen_pressure_plate");
        bwgDirectRemap("aspen_button");
        bwgDirectRemap("aspen_bookshelf");
        bwgDirectRemap("aspen_crafting_table");
        bwgDirectRemap("aspen_leaves");
        bwgDirectRemap("aspen_sapling");
        bwgDirectRemap("aspen_sign");
        bwgDirectRemap("aspen_boat");
        bwgDirectRemap("aspen_chest_boat");

        // === Baobab
        bwgDirectRemap("baobab_fruit");
        bwgDirectRemap("baobab_log");
        bwgDirectRemap("baobab_wood");
        bwgDirectRemap("stripped_baobab_log");
        bwgDirectRemap("stripped_baobab_wood");
        bwgDirectRemap("baobab_planks");
        bwgDirectRemap("baobab_stairs");
        bwgDirectRemap("baobab_slab");
        bwgDirectRemap("baobab_fence");
        bwgDirectRemap("baobab_fence_gate");
        bwgDirectRemap("baobab_door");
        bwgDirectRemap("baobab_trapdoor");
        bwgDirectRemap("baobab_pressure_plate");
        bwgDirectRemap("baobab_button");
        bwgDirectRemap("baobab_bookshelf");
        bwgDirectRemap("baobab_crafting_table");
        bwgDirectRemap("baobab_leaves");
        bwgDirectRemap("baobab_sapling");
        bwgDirectRemap("baobab_sign");
        bwgDirectRemap("baobab_boat");
        bwgDirectRemap("baobab_chest_boat");
        bwgDirectRemap("ripe_baobab_leaves");
        bwgDirectRemap("flowering_baobab_leaves");

        // === Blue Enchanted
        bwgDirectRemap("blue_enchanted_log");
        bwgDirectRemap("blue_enchanted_wood");
        bwgDirectRemap("stripped_blue_enchanted_log");
        bwgDirectRemap("stripped_blue_enchanted_wood");
        bwgDirectRemap("blue_enchanted_planks");
        bwgDirectRemap("blue_enchanted_stairs");
        bwgDirectRemap("blue_enchanted_slab");
        bwgDirectRemap("blue_enchanted_fence");
        bwgDirectRemap("blue_enchanted_fence_gate");
        bwgDirectRemap("blue_enchanted_door");
        bwgDirectRemap("blue_enchanted_trapdoor");
        bwgDirectRemap("blue_enchanted_pressure_plate");
        bwgDirectRemap("blue_enchanted_button");
        bwgDirectRemap("blue_enchanted_bookshelf");
        bwgDirectRemap("blue_enchanted_crafting_table");
        bwgDirectRemap("blue_enchanted_leaves");
        bwgDirectRemap("blue_enchanted_sapling");
        bwgDirectRemap("blue_enchanted_sign");
        bwgDirectRemap("blue_enchanted_boat");
        bwgDirectRemap("blue_enchanted_chest_boat");
        bwgDirectRemap("imbued_blue_enchanted_wood");

        // === Cherry
        bwgRemaps.put("byg:pink_cherry_sapling", "minecraft:cherry_sapling");
        bwgRemaps.put("byg:pink_cherry_foliage", "quark:cherry_leaf_carpet");
        bwgRemaps.put("byg:pink_cherry_leaves", "minecraft:cherry_leaves");
        bwgRemaps.put("byg:white_cherry_sapling", "minecraft:cherry_sapling");
        bwgRemaps.put("byg:white_cherry_foliage", "quark:cherry_leaf_carpet");
        bwgRemaps.put("byg:white_cherry_leaves", "minecraft:cherry_leaves");
        bwgRemaps.put("byg:cherry_log", "minecraft:cherry_log");
        bwgRemaps.put("byg:cherry_wood", "minecraft:cherry_wood");
        bwgRemaps.put("byg:stripped_cherry_log", "minecraft:stripped_cherry_log");
        bwgRemaps.put("byg:stripped_cherry_wood", "minecraft:stripped_cherry_wood");
        bwgRemaps.put("byg:cherry_planks", "minecraft:cherry_planks");
        bwgRemaps.put("byg:cherry_bookshelf", "quark:cherry_bookshelf");
        bwgRemaps.put("byg:cherry_crafting_table", "minecraft:crafting_table");
        bwgRemaps.put("byg:cherry_stairs", "minecraft:cherry_stairs");
        bwgRemaps.put("byg:cherry_slab", "minecraft:cherry_slab");
        bwgRemaps.put("byg:cherry_fence", "minecraft:cherry_fence");
        bwgRemaps.put("byg:cherry_fence_gate", "minecraft:cherry_fence_gate");
        bwgRemaps.put("byg:cherry_door", "minecraft:cherry_door");
        bwgRemaps.put("byg:cherry_trapdoor", "minecraft:cherry_trapdoor");
        bwgRemaps.put("byg:cherry_pressure_plate", "minecraft:cherry_pressure_plate");
        bwgRemaps.put("byg:cherry_button", "minecraft:cherry_button");
        bwgRemaps.put("byg:cherry_sign", "minecraft:cherry_sign");
        bwgRemaps.put("byg:cherry_boat", "minecraft:cherry_boat");
        bwgRemaps.put("byg:cherry_chest_boat", "minecraft:cherry_chest_boat");

        // === Cika
        bwgDirectRemap("cika_log");
        bwgDirectRemap("cika_wood");
        bwgDirectRemap("stripped_cika_log");
        bwgDirectRemap("stripped_cika_wood");
        bwgDirectRemap("cika_planks");
        bwgDirectRemap("cika_stairs");
        bwgDirectRemap("cika_slab");
        bwgDirectRemap("cika_fence");
        bwgDirectRemap("cika_fence_gate");
        bwgDirectRemap("cika_door");
        bwgDirectRemap("cika_trapdoor");
        bwgDirectRemap("cika_pressure_plate");
        bwgDirectRemap("cika_button");
        bwgDirectRemap("cika_bookshelf");
        bwgDirectRemap("cika_crafting_table");
        bwgDirectRemap("cika_leaves");
        bwgDirectRemap("cika_sapling");
        bwgDirectRemap("cika_sign");
        bwgDirectRemap("cika_boat");
        bwgDirectRemap("cika_chest_boat");

        // Cypress
        bwgDirectRemap("cypress_log");
        bwgDirectRemap("cypress_wood");
        bwgDirectRemap("stripped_cypress_log");
        bwgDirectRemap("stripped_cypress_wood");
        bwgDirectRemap("cypress_planks");
        bwgDirectRemap("cypress_stairs");
        bwgDirectRemap("cypress_slab");
        bwgDirectRemap("cypress_fence");
        bwgDirectRemap("cypress_fence_gate");
        bwgDirectRemap("cypress_door");
        bwgDirectRemap("cypress_trapdoor");
        bwgDirectRemap("cypress_pressure_plate");
        bwgDirectRemap("cypress_button");
        bwgDirectRemap("cypress_bookshelf");
        bwgDirectRemap("cypress_crafting_table");
        bwgDirectRemap("cypress_leaves");
        bwgDirectRemap("cypress_sapling");
        bwgDirectRemap("cypress_sign");
        bwgDirectRemap("cypress_boat");
        bwgDirectRemap("cypress_chest_boat");

        // === Ebony
        bwgDirectRemap("ebony_log");
        bwgDirectRemap("ebony_wood");
        bwgDirectRemap("stripped_ebony_log");
        bwgDirectRemap("stripped_ebony_wood");
        bwgDirectRemap("ebony_planks");
        bwgDirectRemap("ebony_stairs");
        bwgDirectRemap("ebony_slab");
        bwgDirectRemap("ebony_fence");
        bwgDirectRemap("ebony_fence_gate");
        bwgDirectRemap("ebony_door");
        bwgDirectRemap("ebony_trapdoor");
        bwgDirectRemap("ebony_pressure_plate");
        bwgDirectRemap("ebony_button");
        bwgDirectRemap("ebony_bookshelf");
        bwgDirectRemap("ebony_crafting_table");
        bwgDirectRemap("ebony_leaves");
        bwgDirectRemap("ebony_sapling");
        bwgDirectRemap("ebony_sign");
        bwgDirectRemap("ebony_boat");
        bwgDirectRemap("ebony_chest_boat");

        // === Fir
        bwgDirectRemap("fir_log");
        bwgDirectRemap("fir_wood");
        bwgDirectRemap("stripped_fir_log");
        bwgDirectRemap("stripped_fir_wood");
        bwgDirectRemap("fir_planks");
        bwgDirectRemap("fir_stairs");
        bwgDirectRemap("fir_slab");
        bwgDirectRemap("fir_fence");
        bwgDirectRemap("fir_fence_gate");
        bwgDirectRemap("fir_door");
        bwgDirectRemap("fir_trapdoor");
        bwgDirectRemap("fir_pressure_plate");
        bwgDirectRemap("fir_button");
        bwgDirectRemap("fir_bookshelf");
        bwgDirectRemap("fir_crafting_table");
        bwgDirectRemap("fir_leaves");
        bwgDirectRemap("fir_sapling");
        bwgDirectRemap("fir_sign");
        bwgDirectRemap("fir_boat");
        bwgDirectRemap("fir_chest_boat");

        // === Green Enchanted
        bwgDirectRemap("green_enchanted_log");
        bwgDirectRemap("green_enchanted_wood");
        bwgDirectRemap("stripped_green_enchanted_log");
        bwgDirectRemap("stripped_green_enchanted_wood");
        bwgDirectRemap("green_enchanted_planks");
        bwgDirectRemap("green_enchanted_stairs");
        bwgDirectRemap("green_enchanted_slab");
        bwgDirectRemap("green_enchanted_fence");
        bwgDirectRemap("green_enchanted_fence_gate");
        bwgDirectRemap("green_enchanted_door");
        bwgDirectRemap("green_enchanted_trapdoor");
        bwgDirectRemap("green_enchanted_pressure_plate");
        bwgDirectRemap("green_enchanted_button");
        bwgDirectRemap("green_enchanted_bookshelf");
        bwgDirectRemap("green_enchanted_crafting_table");
        bwgDirectRemap("green_enchanted_leaves");
        bwgDirectRemap("green_enchanted_sapling");
        bwgDirectRemap("green_enchanted_sign");
        bwgDirectRemap("green_enchanted_boat");
        bwgDirectRemap("green_enchanted_chest_boat");
        bwgDirectRemap("imbued_green_enchanted_wood");

        // === Holly
        bwgDirectRemap("holly_log");
        bwgDirectRemap("holly_wood");
        bwgDirectRemap("stripped_holly_log");
        bwgDirectRemap("stripped_holly_wood");
        bwgDirectRemap("holly_planks");
        bwgDirectRemap("holly_stairs");
        bwgDirectRemap("holly_slab");
        bwgDirectRemap("holly_fence");
        bwgDirectRemap("holly_fence_gate");
        bwgDirectRemap("holly_door");
        bwgDirectRemap("holly_trapdoor");
        bwgDirectRemap("holly_pressure_plate");
        bwgDirectRemap("holly_button");
        bwgDirectRemap("holly_bookshelf");
        bwgDirectRemap("holly_crafting_table");
        bwgDirectRemap("holly_leaves");
        bwgDirectRemap("holly_sapling");
        bwgDirectRemap("holly_sign");
        bwgDirectRemap("holly_boat");
        bwgDirectRemap("holly_chest_boat");
        bwgDirectRemap("holly_berry_leaves");

        // === Jacaranda
        bwgDirectRemap("flowering_jacaranda_bush");
        bwgDirectRemap("jacaranda_bush");
        bwgDirectRemap("flowering_indigo_jacaranda_bush");
        bwgDirectRemap("indigo_jacaranda_bush");
        bwgDirectRemap("jacaranda_log");
        bwgDirectRemap("jacaranda_wood");
        bwgDirectRemap("stripped_jacaranda_log");
        bwgDirectRemap("stripped_jacaranda_wood");
        bwgDirectRemap("jacaranda_planks");
        bwgDirectRemap("jacaranda_stairs");
        bwgDirectRemap("jacaranda_slab");
        bwgDirectRemap("jacaranda_fence");
        bwgDirectRemap("jacaranda_fence_gate");
        bwgDirectRemap("jacaranda_door");
        bwgDirectRemap("jacaranda_trapdoor");
        bwgDirectRemap("jacaranda_pressure_plate");
        bwgDirectRemap("jacaranda_button");
        bwgDirectRemap("jacaranda_bookshelf");
        bwgDirectRemap("jacaranda_crafting_table");
        bwgDirectRemap("jacaranda_leaves");
        bwgDirectRemap("jacaranda_sapling");
        bwgDirectRemap("jacaranda_sign");
        bwgDirectRemap("jacaranda_boat");
        bwgDirectRemap("jacaranda_chest_boat");
        bwgDirectRemap("indigo_jacaranda_sapling");
        bwgDirectRemap("indigo_jacaranda_leaves");
        bwgDirectRemap("flowering_jacaranda_leaves");
        bwgDirectRemap("flowering_indigo_jacaranda_leaves");

        // === Mahogany
        bwgDirectRemap("mahogany_log");
        bwgDirectRemap("mahogany_wood");
        bwgDirectRemap("stripped_mahogany_log");
        bwgDirectRemap("stripped_mahogany_wood");
        bwgDirectRemap("mahogany_planks");
        bwgDirectRemap("mahogany_stairs");
        bwgDirectRemap("mahogany_slab");
        bwgDirectRemap("mahogany_fence");
        bwgDirectRemap("mahogany_fence_gate");
        bwgDirectRemap("mahogany_door");
        bwgDirectRemap("mahogany_trapdoor");
        bwgDirectRemap("mahogany_pressure_plate");
        bwgDirectRemap("mahogany_button");
        bwgDirectRemap("mahogany_bookshelf");
        bwgDirectRemap("mahogany_crafting_table");
        bwgDirectRemap("mahogany_leaves");
        bwgDirectRemap("mahogany_sapling");
        bwgDirectRemap("mahogany_sign");
        bwgDirectRemap("mahogany_boat");
        bwgDirectRemap("mahogany_chest_boat");

        // === White Mangrove
        bwgDirectRemap("white_mangrove_log");
        bwgDirectRemap("white_mangrove_wood");
        bwgDirectRemap("stripped_white_mangrove_log");
        bwgDirectRemap("stripped_white_mangrove_wood");
        bwgDirectRemap("white_mangrove_planks");
        bwgDirectRemap("white_mangrove_stairs");
        bwgDirectRemap("white_mangrove_slab");
        bwgDirectRemap("white_mangrove_fence");
        bwgDirectRemap("white_mangrove_fence_gate");
        bwgDirectRemap("white_mangrove_door");
        bwgDirectRemap("white_mangrove_trapdoor");
        bwgDirectRemap("white_mangrove_pressure_plate");
        bwgDirectRemap("white_mangrove_button");
        bwgDirectRemap("white_mangrove_bookshelf");
        bwgDirectRemap("white_mangrove_crafting_table");
        bwgDirectRemap("white_mangrove_leaves");
        bwgDirectRemap("white_mangrove_sapling");
        bwgDirectRemap("white_mangrove_sign");
        bwgDirectRemap("white_mangrove_boat");
        bwgDirectRemap("white_mangrove_chest_boat");

        // === Maple
        bwgDirectRemap("maple_log");
        bwgDirectRemap("maple_wood");
        bwgDirectRemap("stripped_maple_log");
        bwgDirectRemap("stripped_maple_wood");
        bwgDirectRemap("maple_planks");
        bwgDirectRemap("maple_stairs");
        bwgDirectRemap("maple_slab");
        bwgDirectRemap("maple_fence");
        bwgDirectRemap("maple_fence_gate");
        bwgDirectRemap("maple_door");
        bwgDirectRemap("maple_trapdoor");
        bwgDirectRemap("maple_pressure_plate");
        bwgDirectRemap("maple_button");
        bwgDirectRemap("maple_bookshelf");
        bwgDirectRemap("maple_crafting_table");
        bwgDirectRemap("maple_leaves");
        bwgDirectRemap("maple_sapling");
        bwgDirectRemap("maple_sign");
        bwgDirectRemap("maple_boat");
        bwgDirectRemap("maple_chest_boat");
        bwgDirectRemap("red_maple_sapling");
        bwgDirectRemap("silver_maple_sapling");
        bwgDirectRemap("red_maple_leaves");
        bwgDirectRemap("silver_maple_leaves");

        // === Palm
        bwgDirectRemap("palm_log");
        bwgDirectRemap("palm_wood");
        bwgDirectRemap("stripped_palm_log");
        bwgDirectRemap("stripped_palm_wood");
        bwgDirectRemap("palm_planks");
        bwgDirectRemap("palm_stairs");
        bwgDirectRemap("palm_slab");
        bwgDirectRemap("palm_fence");
        bwgDirectRemap("palm_fence_gate");
        bwgDirectRemap("palm_door");
        bwgDirectRemap("palm_trapdoor");
        bwgDirectRemap("palm_pressure_plate");
        bwgDirectRemap("palm_button");
        bwgDirectRemap("palm_bookshelf");
        bwgDirectRemap("palm_crafting_table");
        bwgDirectRemap("palm_leaves");
        bwgDirectRemap("palm_sapling");
        bwgDirectRemap("palm_sign");
        bwgDirectRemap("palm_boat");
        bwgDirectRemap("palm_chest_boat");

        // === Pine
        bwgDirectRemap("pine_log");
        bwgDirectRemap("pine_wood");
        bwgDirectRemap("stripped_pine_log");
        bwgDirectRemap("stripped_pine_wood");
        bwgDirectRemap("pine_planks");
        bwgDirectRemap("pine_stairs");
        bwgDirectRemap("pine_slab");
        bwgDirectRemap("pine_fence");
        bwgDirectRemap("pine_fence_gate");
        bwgDirectRemap("pine_door");
        bwgDirectRemap("pine_trapdoor");
        bwgDirectRemap("pine_pressure_plate");
        bwgDirectRemap("pine_button");
        bwgDirectRemap("pine_bookshelf");
        bwgDirectRemap("pine_crafting_table");
        bwgDirectRemap("pine_leaves");
        bwgDirectRemap("pine_sapling");
        bwgDirectRemap("pine_sign");
        bwgDirectRemap("pine_boat");
        bwgDirectRemap("pine_chest_boat");

        // === Eucalyptus
        bwgDirectRemap("rainbow_eucalyptus_log");
        bwgDirectRemap("rainbow_eucalyptus_wood");
        bwgDirectRemap("stripped_rainbow_eucalyptus_log");
        bwgDirectRemap("stripped_rainbow_eucalyptus_wood");
        bwgDirectRemap("rainbow_eucalyptus_planks");
        bwgDirectRemap("rainbow_eucalyptus_stairs");
        bwgDirectRemap("rainbow_eucalyptus_slab");
        bwgDirectRemap("rainbow_eucalyptus_fence");
        bwgDirectRemap("rainbow_eucalyptus_fence_gate");
        bwgDirectRemap("rainbow_eucalyptus_door");
        bwgDirectRemap("rainbow_eucalyptus_trapdoor");
        bwgDirectRemap("rainbow_eucalyptus_pressure_plate");
        bwgDirectRemap("rainbow_eucalyptus_button");
        bwgDirectRemap("rainbow_eucalyptus_bookshelf");
        bwgDirectRemap("rainbow_eucalyptus_crafting_table");
        bwgDirectRemap("rainbow_eucalyptus_leaves");
        bwgDirectRemap("rainbow_eucalyptus_sapling");
        bwgDirectRemap("rainbow_eucalyptus_sign");
        bwgDirectRemap("rainbow_eucalyptus_boat");
        bwgDirectRemap("rainbow_eucalyptus_chest_boat");

        // === Redwood
        bwgDirectRemap("redwood_log");
        bwgDirectRemap("redwood_wood");
        bwgDirectRemap("stripped_redwood_log");
        bwgDirectRemap("stripped_redwood_wood");
        bwgDirectRemap("redwood_planks");
        bwgDirectRemap("redwood_stairs");
        bwgDirectRemap("redwood_slab");
        bwgDirectRemap("redwood_fence");
        bwgDirectRemap("redwood_fence_gate");
        bwgDirectRemap("redwood_door");
        bwgDirectRemap("redwood_trapdoor");
        bwgDirectRemap("redwood_pressure_plate");
        bwgDirectRemap("redwood_button");
        bwgDirectRemap("redwood_bookshelf");
        bwgDirectRemap("redwood_crafting_table");
        bwgDirectRemap("redwood_leaves");
        bwgDirectRemap("redwood_sapling");
        bwgDirectRemap("redwood_sign");
        bwgDirectRemap("redwood_boat");
        bwgDirectRemap("redwood_chest_boat");

        // === Skyris
        bwgDirectRemap("skyris_vine");
        bwgDirectRemap("skyris_log");
        bwgDirectRemap("skyris_wood");
        bwgDirectRemap("stripped_skyris_log");
        bwgDirectRemap("stripped_skyris_wood");
        bwgDirectRemap("skyris_planks");
        bwgDirectRemap("skyris_stairs");
        bwgDirectRemap("skyris_slab");
        bwgDirectRemap("skyris_fence");
        bwgDirectRemap("skyris_fence_gate");
        bwgDirectRemap("skyris_door");
        bwgDirectRemap("skyris_trapdoor");
        bwgDirectRemap("skyris_pressure_plate");
        bwgDirectRemap("skyris_button");
        bwgDirectRemap("skyris_bookshelf");
        bwgDirectRemap("skyris_crafting_table");
        bwgDirectRemap("skyris_leaves");
        bwgDirectRemap("skyris_sapling");
        bwgDirectRemap("skyris_sign");
        bwgDirectRemap("skyris_boat");
        bwgDirectRemap("skyris_chest_boat");
        bwgDirectRemap("green_apple_skyris_leaves");
        bwgDirectRemap("flowering_skyris_leaves");

        // === Willow
        bwgDirectRemap("willow_log");
        bwgDirectRemap("willow_wood");
        bwgDirectRemap("stripped_willow_log");
        bwgDirectRemap("stripped_willow_wood");
        bwgDirectRemap("willow_planks");
        bwgDirectRemap("willow_stairs");
        bwgDirectRemap("willow_slab");
        bwgDirectRemap("willow_fence");
        bwgDirectRemap("willow_fence_gate");
        bwgDirectRemap("willow_door");
        bwgDirectRemap("willow_trapdoor");
        bwgDirectRemap("willow_pressure_plate");
        bwgDirectRemap("willow_button");
        bwgDirectRemap("willow_bookshelf");
        bwgDirectRemap("willow_crafting_table");
        bwgDirectRemap("willow_leaves");
        bwgDirectRemap("willow_sapling");
        bwgDirectRemap("willow_sign");
        bwgDirectRemap("willow_boat");
        bwgDirectRemap("willow_chest_boat");

        // === Witch Hazel
        bwgDirectRemap("witch_hazel_branch");
        bwgDirectRemap("witch_hazel_blossom");
        bwgDirectRemap("witch_hazel_log");
        bwgDirectRemap("witch_hazel_wood");
        bwgDirectRemap("stripped_witch_hazel_log");
        bwgDirectRemap("stripped_witch_hazel_wood");
        bwgDirectRemap("witch_hazel_planks");
        bwgDirectRemap("witch_hazel_stairs");
        bwgDirectRemap("witch_hazel_slab");
        bwgDirectRemap("witch_hazel_fence");
        bwgDirectRemap("witch_hazel_fence_gate");
        bwgDirectRemap("witch_hazel_door");
        bwgDirectRemap("witch_hazel_trapdoor");
        bwgDirectRemap("witch_hazel_pressure_plate");
        bwgDirectRemap("witch_hazel_button");
        bwgDirectRemap("witch_hazel_bookshelf");
        bwgDirectRemap("witch_hazel_crafting_table");
        bwgDirectRemap("witch_hazel_leaves");
        bwgDirectRemap("witch_hazel_sapling");
        bwgDirectRemap("witch_hazel_sign");
        bwgDirectRemap("witch_hazel_boat");
        bwgDirectRemap("witch_hazel_chest_boat");
        bwgDirectRemap("blooming_witch_hazel_leaves");

        // === Zelkova
        bwgDirectRemap("zelkova_log");
        bwgDirectRemap("zelkova_wood");
        bwgDirectRemap("stripped_zelkova_log");
        bwgDirectRemap("stripped_zelkova_wood");
        bwgDirectRemap("zelkova_planks");
        bwgDirectRemap("zelkova_stairs");
        bwgDirectRemap("zelkova_slab");
        bwgDirectRemap("zelkova_fence");
        bwgDirectRemap("zelkova_fence_gate");
        bwgDirectRemap("zelkova_door");
        bwgDirectRemap("zelkova_trapdoor");
        bwgDirectRemap("zelkova_pressure_plate");
        bwgDirectRemap("zelkova_button");
        bwgDirectRemap("zelkova_bookshelf");
        bwgDirectRemap("zelkova_crafting_table");
        bwgDirectRemap("zelkova_leaves");
        bwgDirectRemap("zelkova_sapling");
        bwgDirectRemap("zelkova_sign");
        bwgDirectRemap("zelkova_boat");
        bwgDirectRemap("zelkova_chest_boat");
        bwgDirectRemap("brown_zelkova_sapling");
        bwgDirectRemap("brown_zelkova_leaves");

        // === Cactus
        bwgDirectRemap("mini_cactus");
        bwgDirectRemap("prickly_pear_cactus");
        bwgDirectRemap("golden_spined_cactus");
        bwgDirectRemap("barrel_cactus");
        bwgDirectRemap("flowering_barrel_cactus");
        bwgDirectRemap("carved_barrel_cactus");

        // === Mushroom
        bwgDirectRemap("green_mushroom_block");
        bwgDirectRemap("weeping_milkcap_mushroom_block");
        bwgDirectRemap("wood_blewit_mushroom_block");
        bwgDirectRemap("white_mushroom_stem");
        bwgDirectRemap("brown_mushroom_stem");
        bwgDirectRemap("green_mushroom");

        // === Cattail
        bwgDirectRemap("cattail_thatch");
        bwgDirectRemap("cattail_thatch_slab");
        bwgDirectRemap("cattail_thatch_stairs");
        bwgDirectRemap("cattail_thatch_carpet");
        bwgDirectRemap("cattail_sprout");

        // === Sandstones
        // blue
        bwgDirectRemap("blue_sand");
        bwgDirectRemap("blue_sandstone");
        bwgRemaps.put("byg:blue_chiseled_sandstone", "biomeswevegone:chiseled_blue_sandstone");
        bwgRemaps.put("byg:blue_cut_sandstone", "biomeswevegone:cut_blue_sandstone");
        bwgRemaps.put("byg:blue_smooth_sandstone", "biomeswevegone:smooth_blue_sandstone");
        // pink
        bwgDirectRemap("pink_sand");
        bwgDirectRemap("pink_sandstone");
        bwgRemaps.put("byg:pink_chiseled_sandstone", "biomeswevegone:chiseled_pink_sandstone");
        bwgRemaps.put("byg:pink_cut_sandstone", "biomeswevegone:cut_pink_sandstone");
        bwgRemaps.put("byg:pink_smooth_sandstone", "biomeswevegone:smooth_pink_sandstone");
        // black
        bwgDirectRemap("black_sand");
        bwgDirectRemap("black_sandstone");
        bwgRemaps.put("byg:black_chiseled_sandstone", "biomeswevegone:chiseled_black_sandstone");
        bwgRemaps.put("byg:black_cut_sandstone", "biomeswevegone:cut_black_sandstone");
        bwgRemaps.put("byg:black_smooth_sandstone", "biomeswevegone:smooth_black_sandstone");
        // purple
        bwgDirectRemap("purple_sand");
        bwgDirectRemap("purple_sandstone");
        bwgRemaps.put("byg:purple_chiseled_sandstone", "biomeswevegone:chiseled_purple_sandstone");
        bwgRemaps.put("byg:purple_cut_sandstone", "biomeswevegone:cut_purple_sandstone");
        bwgRemaps.put("byg:purple_smooth_sandstone", "biomeswevegone:smooth_purple_sandstone");
        // white
        bwgDirectRemap("white_sand");
        bwgDirectRemap("white_sandstone");
        bwgRemaps.put("byg:white_chiseled_sandstone", "biomeswevegone:chiseled_white_sandstone");
        bwgRemaps.put("byg:white_cut_sandstone", "biomeswevegone:cut_white_sandstone");
        bwgRemaps.put("byg:white_smooth_sandstone", "biomeswevegone:smooth_white_sandstone");

        // === Buncha Bulk
        bwgDirectRemap("araucaria_sapling");
        bwgDirectRemap("araucaria_leaves");
        bwgDirectRemap("blue_spruce_sapling");
        bwgDirectRemap("blue_spruce_leaves");
        bwgDirectRemap("brown_birch_sapling");
        bwgDirectRemap("brown_birch_leaves");
        bwgDirectRemap("brown_oak_sapling");
        bwgDirectRemap("brown_oak_leaves");
        bwgDirectRemap("joshua_sapling");
        bwgDirectRemap("joshua_leaves");
        bwgDirectRemap("orange_birch_sapling");
        bwgDirectRemap("orange_birch_leaves");
        bwgDirectRemap("orange_oak_sapling");
        bwgDirectRemap("orange_oak_leaves");
        bwgDirectRemap("orange_spruce_sapling");
        bwgDirectRemap("orange_spruce_leaves");
        bwgDirectRemap("orchard_sapling");
        bwgDirectRemap("ripe_orchard_leaves");
        bwgDirectRemap("flowering_orchard_leaves");
        bwgDirectRemap("orchard_leaves");
        bwgDirectRemap("red_birch_sapling");
        bwgDirectRemap("red_birch_leaves");
        bwgDirectRemap("red_oak_sapling");
        bwgDirectRemap("red_oak_leaves");
        bwgDirectRemap("red_spruce_sapling");
        bwgDirectRemap("red_spruce_leaves");
        bwgDirectRemap("yellow_birch_sapling");
        bwgDirectRemap("yellow_birch_leaves");
        bwgDirectRemap("yellow_spruce_sapling");
        bwgDirectRemap("yellow_spruce_leaves");
        bwgDirectRemap("firecracker_flower_bush");
        bwgDirectRemap("firecracker_leaves");
        bwgDirectRemap("horseweed");
        bwgDirectRemap("poison_ivy");
        bwgDirectRemap("beach_grass");
        bwgDirectRemap("tall_prairie_grass");
        bwgDirectRemap("prairie_grass");
        bwgDirectRemap("tiny_lily_pads");
        bwgDirectRemap("flowering_tiny_lily_pads");
        bwgDirectRemap("water_silk");
        bwgDirectRemap("winter_succulent");
        bwgDirectRemap("leaf_pile");
        bwgDirectRemap("clover_patch");
        bwgDirectRemap("flower_patch");
        bwgDirectRemap("tall_allium");
        bwgDirectRemap("allium_flower_bush");
        bwgDirectRemap("alpine_bellflower");
        bwgDirectRemap("amaranth");
        bwgDirectRemap("angelica");
        bwgDirectRemap("hydrangea_bush");
        bwgDirectRemap("hydrangea_hedge");
        bwgDirectRemap("begonia");
        bwgDirectRemap("bistort");
        bwgDirectRemap("black_rose");
        bwgDirectRemap("blue_rose_bush");
        bwgDirectRemap("blue_sage");
        bwgDirectRemap("california_poppy");
        bwgDirectRemap("crocus");
        bwgDirectRemap("cyan_amaranth");
        bwgDirectRemap("cyan_rose");
        bwgDirectRemap("cyan_tulip");
        bwgDirectRemap("daffodil");
        bwgDirectRemap("delphinium");
        bwgDirectRemap("fairy_slipper");
        bwgDirectRemap("foxglove");
        bwgDirectRemap("green_tulip");
        bwgDirectRemap("guzmania");
        bwgDirectRemap("incan_lily");
        bwgDirectRemap("iris");
        bwgDirectRemap("japanese_orchid");
        bwgDirectRemap("kovan_flower");
        bwgDirectRemap("lazarus_bellflower");
        bwgDirectRemap("lollipop_flower");
        bwgDirectRemap("magenta_amaranth");
        bwgDirectRemap("magenta_tulip");
        bwgDirectRemap("orange_amaranth");
        bwgDirectRemap("orange_daisy");
        bwgDirectRemap("osiria_rose");
        bwgDirectRemap("peach_leather_flower");
        bwgDirectRemap("pink_allium");
        bwgDirectRemap("tall_pink_allium");
        bwgDirectRemap("pink_allium_flower_bush");
        bwgDirectRemap("pink_anemone");
        bwgDirectRemap("pink_daffodil");
        bwgDirectRemap("protea_flower");
        bwgDirectRemap("purple_amaranth");
        bwgDirectRemap("purple_sage");
        bwgDirectRemap("purple_tulip");
        bwgDirectRemap("richea");
        bwgDirectRemap("rose");
        bwgDirectRemap("silver_vase_flower");
        bwgDirectRemap("snowdrops");
        bwgDirectRemap("violet_leather_flower");
        bwgDirectRemap("white_anemone");
        bwgDirectRemap("white_sage");
        bwgDirectRemap("winter_cyclamen");
        bwgDirectRemap("winter_rose");
        bwgDirectRemap("winter_scilla");
        bwgDirectRemap("yellow_daffodil");
        bwgDirectRemap("yellow_tulip");
        bwgRemaps.put("byg:boric_campfire", "minecraft:campfire");
        bwgRemaps.put("byg:cryptic_campfire", "minecraft:campfire");
        bwgDirectRemap("overgrown_stone");
        bwgDirectRemap("shelf_fungi");

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
