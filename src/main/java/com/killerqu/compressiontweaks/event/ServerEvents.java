package com.killerqu.compressiontweaks.event;

import com.killerqu.compressiontweaks.CompressionTweaks;
import com.killerqu.compressiontweaks.biomes.IOverwritableGenerator;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//hahaaaaaaa. oh god.
@Mod.EventBusSubscriber(modid = CompressionTweaks.MODID)
public class ServerEvents {
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event){
        ServerLevel level = event.getServer().getLevel(ServerLevel.OVERWORLD);
        WorldPreset preset = event.getServer().registryAccess().registryOrThrow(Registries.WORLD_PRESET).get(new ResourceLocation("hexlands", "hexlands"));
        //Writing this at 1:50 am. Not to justify to anyone really. Except maybe to myself while re-reading this. ~Barza
        assert level != null;
        assert preset != null;
        if(preset.overworld().isPresent()){
            ((IOverwritableGenerator) level.getChunkSource().chunkMap).setChunkGenerator(
                    preset.overworld().get().generator()
            );
        }
    }
}
