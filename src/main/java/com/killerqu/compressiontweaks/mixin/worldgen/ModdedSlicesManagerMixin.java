package com.killerqu.compressiontweaks.mixin.worldgen;

import com.teamabnormals.blueprint.common.world.modification.ModdedBiomeSlicesManager;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModdedBiomeSlicesManager.class)
public class ModdedSlicesManagerMixin {
    @Inject(method = "onServerAboutToStart", at = @At("HEAD"), remap = false, cancellable = true)
    private static void blockBiomeSourceOverlaying(MinecraftServer server, CallbackInfo ci){
        /*Yep. That's right.
        That's what it gets for forcibly replacing biome sources.*/
        ci.cancel();
    }
}
