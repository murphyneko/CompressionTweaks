package com.killerqu.compressiontweaks.mixin.emi;

import com.killerqu.compressiontweaks.CompressionTweaks;
import dev.emi.emi.jemi.JemiPlugin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Set;

@Mixin(JemiPlugin.class)
public class JEMIPluginMixin {
    @Redirect(method = "register", at = @At(value = "INVOKE", target = "Ljava/util/Set;contains(Ljava/lang/Object;)Z", ordinal = 0), remap = false)
    private boolean whitelistCT(Set<String> instance, Object o){
        if(o instanceof String id){
            if(id.equals(CompressionTweaks.MODID)) return false;
        }
        return instance.contains(o);
    }
}
