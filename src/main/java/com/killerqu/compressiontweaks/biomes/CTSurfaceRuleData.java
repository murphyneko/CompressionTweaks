package com.killerqu.compressiontweaks.biomes;

import com.killerqu.compressiontweaks.CompressionTweaks;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraftforge.registries.ForgeRegistries;
import vazkii.botania.common.block.BotaniaBlocks;

public class CTSurfaceRuleData {
    private static final SurfaceRules.RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final SurfaceRules.RuleSource ENDSTONE = makeStateRule(Blocks.END_STONE);
    private static final SurfaceRules.RuleSource SOULSOIL = makeStateRule(Blocks.SOUL_SOIL);
    private static final SurfaceRules.RuleSource BASALT = makeStateRule(Blocks.BASALT);
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
    private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.AIR);

    public static SurfaceRules.RuleSource makeRules() {
        //Handled separately in case of a mishap where it isn't there.
        ResourceLocation EV_END_GRASS_ID = new ResourceLocation("echovoids", "end_grass");
        Block EV_END_GRASS = ForgeRegistries.BLOCKS.getValue(EV_END_GRASS_ID);
        if(EV_END_GRASS == null) CompressionTweaks.LOGGER.error("Echovoids' Blonde Nullium was not found!");
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.VOID_BIOME), AIR),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("compressiontweaks:bedrock_floor", new VerticalAnchor.AboveBottom(0), new VerticalAnchor.AboveBottom(5)), BEDROCK),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.VALLEY_BIOME), SOULSOIL),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.CRIMSON_BIOME), SurfaceRules.sequence(
                        //Translation: If the block is exposed on top, make it nylium, else make it netherrack.
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR), makeStateRule(Blocks.CRIMSON_NYLIUM)),
                        NETHERRACK
                )),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.WARPED_BIOME), SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR), makeStateRule(Blocks.WARPED_NYLIUM)),
                        NETHERRACK
                )),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.SCULK_LANDS), SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR), makeStateRule(Blocks.SCULK)),
                        DEEPSLATE
                )),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.MYSTICAL_GROVE),
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
                                SurfaceRules.ifTrue(SurfaceRules.waterStartCheck(0,0),
                                        SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), makeStateRule(BotaniaBlocks.infusedGrass))))
                ),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.GLIMMERING_WEALD), DEEPSLATE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.IMPACT_SITE), DEEPSLATE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.DELTAS_BIOME), BASALT),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.WASTES_BIOME), NETHERRACK),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.END_BIOME), ENDSTONE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.PEARLESCENT_BIOME), ENDSTONE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.ENTROPIC_BIOME), ENDSTONE),
                //SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.BLONDE_BIOME), ENDSTONE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.BLONDE_BIOME), SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
                                EV_END_GRASS != null ? makeStateRule(EV_END_GRASS) : ENDSTONE
                                ),ENDSTONE
                ))
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
