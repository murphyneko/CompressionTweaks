package com.killerqu.compressiontweaks.mixin.worldgen;

import com.killerqu.compressiontweaks.biomes.IOverwritableGenerator;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.world.level.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ChunkMap.class)
public class ChunkMapMixin implements IOverwritableGenerator {

    @Shadow
    private ChunkGenerator generator;

    public void setChunkGenerator(ChunkGenerator gen){
        generator = gen;
    }

}
