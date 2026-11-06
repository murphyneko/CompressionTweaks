package com.killerqu.compressiontweaks.emi;

import codechicken.microblock.api.MicroMaterial;
import codechicken.microblock.item.ItemMicroBlock;
import com.killerqu.compressiontweaks.CompressionTweaks;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EmiMicroblockMaterialRecipe implements EmiRecipe {
    private MicroMaterial material;
    private EmiIngredient input;
    private List<EmiStack> output;

    public EmiMicroblockMaterialRecipe(MicroMaterial mat){
        this.material = mat;
        input = EmiStack.of(material.getItem());
        output = new ArrayList<>();
        for(int factory : new int[]{0,1,3,2}) for(int size : new int[]{4,2,1}){
            output.add(EmiStack.of(ItemMicroBlock.create(factory, size, material)));
        }
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return CTEMIPlugin.MB_MATERIAL_CATEGORY;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return new ResourceLocation(CompressionTweaks.MODID, "emi_micromaterial_"+material.getRegistryName().getNamespace()+"_"+material.getRegistryName().getNamespace());
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of(input);
    }

    @Override
    public List<EmiStack> getOutputs() {
        return output;
    }

    @Override
    public int getDisplayWidth() {
        return 150;
    }

    @Override
    public int getDisplayHeight() {
        return 36;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addSlot(input, 12, 11);
        int x = 36;
        int y = 0;
        for(int i = 0;i<output.size();i++){
            widgets.addSlot(output.get(i), x, y).recipeContext(this);
            x+=18;
            if(x >= 144){
                x = 36;
                y += 18;
            }
        }
    }
}
