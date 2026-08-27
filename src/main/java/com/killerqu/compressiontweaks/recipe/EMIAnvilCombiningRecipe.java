package com.killerqu.compressiontweaks.recipe;

import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.emi.emi.recipe.EmiAnvilRecipe;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

//Yep, we doing this again.
//This is a variant of EMI's anvil recipe, but instead of repairing, it combines two items.
//It is used to properly depict the Gliders upgrade and repair recipes.
public class EMIAnvilCombiningRecipe extends EmiAnvilRecipe {
    private final EmiStack input;
    private final EmiIngredient addition;
    private final EmiStack output;

    public EMIAnvilCombiningRecipe(EmiStack tool, EmiIngredient resource, EmiStack output, ResourceLocation id) {
        super(tool, resource, id);
        this.input = tool;
        this.addition = resource;
        this.output = output;
    }

    @Override
    public List<EmiStack> getOutputs(){
        return List.of(output);
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(EmiTexture.PLUS, 27, 3);
        widgets.addTexture(EmiTexture.EMPTY_ARROW, 75, 1);
        widgets.addSlot(input, 0, 0);
        widgets.addSlot(addition, 49, 0);
        widgets.addSlot(output, 107, 0).recipeContext(this);
    }

}
