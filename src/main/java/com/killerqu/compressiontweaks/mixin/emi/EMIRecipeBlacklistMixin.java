package com.killerqu.compressiontweaks.mixin.emi;

import com.killerqu.compressiontweaks.config.CTClientConfig;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.registry.EmiRegistryImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EmiRegistryImpl.class)
public class EMIRecipeBlacklistMixin {

    @Inject(remap = false, method = "addRecipe", at = @At("HEAD"), cancellable = true)
    private void unAddRecipe(EmiRecipe recipe, CallbackInfo ci){
        if(recipe != null && recipe.getId() != null){
            if(CTClientConfig.EMI_BLACKLIST.get().contains(recipe.getId().toString())){
                ci.cancel();
            }
        }
    }
}
