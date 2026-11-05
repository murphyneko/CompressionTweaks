/*package com.killerqu.compressiontweaks.mixin;

import com.killerqu.compressiontweaks.recipe.MicroblockStonecutterRecipe;
import net.minecraft.client.gui.screens.inventory.StonecutterScreen;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.StonecutterMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StonecutterScreen.class)
public class StonecutterScreenMixin {
    @Redirect(method = "renderRecipes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/StonecutterRecipe;getResultItem(Lnet/minecraft/core/RegistryAccess;)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack checkForMicroblockRecipe(StonecutterRecipe instance, RegistryAccess registryAccess){
        return compressionTweaks$microblockRecipeCheck(instance, registryAccess);
    }

    @Redirect(method = "renderTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/StonecutterRecipe;getResultItem(Lnet/minecraft/core/RegistryAccess;)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack checkForMicroblockRecipeInTooltip(StonecutterRecipe instance, RegistryAccess registryAccess){
        return compressionTweaks$microblockRecipeCheck(instance, registryAccess);
    }

    @Unique
    private ItemStack compressionTweaks$microblockRecipeCheck(StonecutterRecipe recipe, RegistryAccess registryAccess){
        if(recipe instanceof MicroblockStonecutterRecipe micro){
            Slot input = ((StonecutterScreen)(Object)this).getMenu().getSlot(StonecutterMenu.INPUT_SLOT);
            if(!input.hasItem()) return recipe.getResultItem(registryAccess); //failsafe
            return micro.getInputAwareResultItem(input.getItem());
        }
        return recipe.getResultItem(registryAccess);
    }

}
*/