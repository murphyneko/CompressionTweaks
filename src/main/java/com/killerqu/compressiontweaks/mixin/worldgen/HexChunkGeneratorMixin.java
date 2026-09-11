package com.killerqu.compressiontweaks.mixin.worldgen;

import com.alcatrazescapee.hexlands.world.HexChunkGenerator;
import com.killerqu.compressiontweaks.biomes.ISeededBiomeSource;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.RandomState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HexChunkGenerator.class)
public class HexChunkGeneratorMixin {

    @Inject(method = "m_214194_", at = @At("HEAD"), remap = false)
    private void grabLevelSeed(WorldGenRegion level, StructureManager structureManager, RandomState randomState, ChunkAccess chunk, CallbackInfo ci){
        long seed = level.getSeed();
        BiomeSource source = ((HexChunkGenerator)(Object)this).getBiomeSource();
        if(source instanceof ISeededBiomeSource seedSource) {
            seedSource.setSeed(seed);
        }
    }


}


