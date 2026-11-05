package com.killerqu.compressiontweaks.mixin.emi;

import com.killerqu.compressiontweaks.CompressionTweaks;
import dev.emi.emi.jemi.JemiUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(JemiUtil.class)
public class JEMIUtilMixin {
    @Inject(method = "getHandledMods", at = @At("RETURN"), remap = false, cancellable = true)
    private static void reinstateJeiPlugin(CallbackInfoReturnable<Set<String>> cir){
        Set<String> values = cir.getReturnValue();
        values.remove(CompressionTweaks.MODID);
        cir.setReturnValue(values);
    }
}
