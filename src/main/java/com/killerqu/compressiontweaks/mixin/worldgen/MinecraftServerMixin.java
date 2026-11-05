package com.killerqu.compressiontweaks.mixin.worldgen;

import com.killerqu.compressiontweaks.biomes.CTBiomeSource;
import com.killerqu.compressiontweaks.config.CTCommonConfig;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//This is somehow worse
//Need to switch over existing worlds to the new chunk generator.
//The previous iteration did this at a ServerStartingEvent. Didn't do great on servers. This one seems to do better.
//Note: Neither implementation is permanent. In other words, the changes aren't saved to level.dat.
@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Shadow
    public abstract RegistryAccess.Frozen registryAccess();

    @Redirect(method = "createLevels", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Registry;get(Lnet/minecraft/resources/ResourceKey;)Ljava/lang/Object;"))
    private Object swapLevelStem(Registry<LevelStem> registry, ResourceKey<LevelStem> key){
        LevelStem original = registry.get(key);
        if(CTCommonConfig.DISABLE_CHUNKGEN_SWITCH.get() || key != LevelStem.OVERWORLD || original.generator().getBiomeSource() instanceof CTBiomeSource) return original;
        WorldPreset preset = registryAccess().registryOrThrow(Registries.WORLD_PRESET).get(new ResourceLocation("hexlands", "hexlands"));
        assert preset != null;
        return preset.overworld().get();
    }
}
