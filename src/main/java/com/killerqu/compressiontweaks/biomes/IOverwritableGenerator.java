package com.killerqu.compressiontweaks.biomes;

import net.minecraft.world.level.chunk.ChunkGenerator;

public interface IOverwritableGenerator {
    void setChunkGenerator(ChunkGenerator gen);
}
