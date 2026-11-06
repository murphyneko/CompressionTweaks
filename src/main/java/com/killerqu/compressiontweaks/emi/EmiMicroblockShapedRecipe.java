package com.killerqu.compressiontweaks.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.recipe.EmiShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class EmiMicroblockShapedRecipe extends EmiShapedRecipe {

    public EmiMicroblockShapedRecipe(ShapedRecipe recipe) {
        super(recipe);
    }

    @Override
    public EmiRecipeCategory getCategory(){
        return CTEMIPlugin.MB_CATEGORY;
    }
}
