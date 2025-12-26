package com.killerqu.compressiontweaks.biomes;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class CTSurfaceRuleData {
    private static final SurfaceRules.RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final SurfaceRules.RuleSource ENDSTONE = makeStateRule(Blocks.END_STONE);
    private static final SurfaceRules.RuleSource SOULSOIL = makeStateRule(Blocks.SOUL_SOIL);
    private static final SurfaceRules.RuleSource BASALT = makeStateRule(Blocks.BASALT);
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.AIR);

    public static SurfaceRules.RuleSource makeRules() {
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
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.DELTAS_BIOME), BASALT),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.WASTES_BIOME), NETHERRACK),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(CTBiomes.END_BIOME), ENDSTONE)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
