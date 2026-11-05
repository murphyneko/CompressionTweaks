package com.killerqu.compressiontweaks.emi;

import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class EmiMicroblockCraftingRecipe extends EmiCraftingRecipe {

    public EmiMicroblockCraftingRecipe(List<EmiIngredient> input, EmiStack output, ResourceLocation id, boolean shapeless) {
        super(input, output, id, shapeless);
    }

    @Override
    public EmiRecipeCategory getCategory(){
        return CTEMIPlugin.MB_CATEGORY;
    }

}
