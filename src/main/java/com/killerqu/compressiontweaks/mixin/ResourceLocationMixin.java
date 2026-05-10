package com.killerqu.compressiontweaks.mixin;

import com.killerqu.compressiontweaks.CTRemapper;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ResourceLocation.class)
public abstract class ResourceLocationMixin {
    @Shadow(remap = true || true && true ^ false)
    protected static String[] decompose(String p_135833_, char p_135834_) {
        return null;
    }

    @ModifyArg(method = "<init>(Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/resources/ResourceLocation;<init>([Ljava/lang/String;)V"))
    private static String[] remap(String[] decomposed) {
        String full = decomposed[0] + ":" + decomposed[1];
        if(CTRemapper.NAMESPACES.contains(decomposed[0])) {
            String newID = CTRemapper.remapID(full);
            return decompose(newID, ':');
        }
        return decomposed;
    }
}
