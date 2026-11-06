package com.killerqu.compressiontweaks.emi;

import codechicken.microblock.api.BlockMicroMaterial;
import codechicken.microblock.item.ItemMicroBlock;
import codechicken.microblock.recipe.MicroRecipe;
import codechicken.microblock.util.MicroMaterialRegistry;
import com.killerqu.compressiontweaks.CompressionTweaks;
import com.killerqu.compressiontweaks.recipe.MicroblockIngredient;
import com.killerqu.compressiontweaks.recipe.MicroblockShapedRecipe;
import com.killerqu.compressiontweaks.recipe.MicroblockShapelessRecipe;
import dev.emi.emi.EmiPort;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;

import static codechicken.microblock.init.CBMicroblockModContent.DIAMOND_SAW;
import static codechicken.microblock.init.CBMicroblockModContent.MICRO_BLOCK_ITEM;
import static com.killerqu.compressiontweaks.CompressionTweaks.mbDefault;

@EmiEntrypoint
public class CTEMIPlugin implements EmiPlugin {
    public static final EmiStack MB_CRAFTING_ICON = EmiStack.of(DIAMOND_SAW.get());
    public static final EmiRecipeCategory MB_CRAFTING_CATEGORY = new EmiRecipeCategory(new ResourceLocation(CompressionTweaks.MODID, "microblock_crafting"), MB_CRAFTING_ICON);
    public static final EmiStack MB_MATERIAL_ICON = EmiStack.of(ItemMicroBlock.create(1,1, BlockMicroMaterial.makeMaterialKey(mbDefault().defaultBlockState())));
    public static final EmiRecipeCategory MB_MATERIAL_CATEGORY = new EmiRecipeCategory(new ResourceLocation(CompressionTweaks.MODID, "microblock_material"), MB_MATERIAL_ICON);


    @Override
    public void register(EmiRegistry registry) {
        registry.removeEmiStacks(emiStack -> {
            if(emiStack.isEmpty() || !emiStack.getItemStack().is(MICRO_BLOCK_ITEM.get()) || MicroRecipe.microMaterial(emiStack.getItemStack())== null) return false;
            return !MicroRecipe.microMaterial(emiStack.getItemStack()).getRegistryName().toString().contains("minecraft:grass_block");
        });

        registry.addCategory(MB_CRAFTING_CATEGORY);
        registry.addWorkstation(MB_CRAFTING_CATEGORY, MB_CRAFTING_ICON);
        registry.addCategory(MB_MATERIAL_CATEGORY);
        registry.addWorkstation(MB_MATERIAL_CATEGORY, MB_CRAFTING_ICON);

        RecipeManager manager = registry.getRecipeManager();
        for(CraftingRecipe recipe : manager.getAllRecipesFor(RecipeType.CRAFTING)){
            if(recipe instanceof MicroblockShapedRecipe shapedRecipe) registerMicroblockShapedRecipe(registry, shapedRecipe);
            if(recipe instanceof MicroblockShapelessRecipe) registerMicroblockRecipe(registry, recipe, true);
        }

        MicroMaterialRegistry.MICRO_MATERIALS.forEach(microMaterial -> {
            registry.addRecipe(new EmiMicroblockMaterialRecipe(microMaterial));
        });


    }

    public void registerMicroblockShapedRecipe(EmiRegistry registry, MicroblockShapedRecipe recipe){
        CompressionTweaks.LOGGER.info("Registering: {}", recipe.getId());
        NonNullList<Ingredient> ingredients = NonNullList.withSize(recipe.getIngredients().size(), Ingredient.EMPTY);
        for(int i = 0; i<recipe.getIngredients().size();i++){
            if(recipe.getIngredients().get(i) instanceof MicroblockIngredient microblockIngredient) ingredients.set(i, Ingredient.of(microblockIngredient.getDisplayItem()));
            else ingredients.set(i, recipe.getIngredients().get(i));
        }
        registry.addRecipe(new EmiMicroblockShapedRecipe(
                new ShapedRecipe(
                        new ResourceLocation(EmiPort.getId(recipe).getNamespace(), "emi_"+EmiPort.getId(recipe).getPath()),
                        recipe.getGroup(), recipe.category(), recipe.getWidth(), recipe.getHeight(), ingredients, EmiPort.getOutput(recipe), false)
        ));
    }


    public void registerMicroblockRecipe(EmiRegistry registry, CraftingRecipe recipe, boolean shapeless){
        CompressionTweaks.LOGGER.info("Registering: {}", recipe.getId());
        NonNullList<EmiIngredient> ingredients = NonNullList.withSize(recipe.getIngredients().size(), EmiStack.EMPTY);
        for(int i = 0; i<recipe.getIngredients().size();i++){
            if(recipe.getIngredients().get(i) instanceof MicroblockIngredient microblockIngredient) ingredients.set(i, EmiStack.of(microblockIngredient.getDisplayItem()));
            else ingredients.set(i, EmiIngredient.of(recipe.getIngredients().get(i)));
        }
        registry.addRecipe(new EmiMicroblockCraftingRecipe(ingredients, EmiStack.of(EmiPort.getOutput(recipe)),
                new ResourceLocation(EmiPort.getId(recipe).getNamespace(), "emi_"+EmiPort.getId(recipe).getPath())
                ,shapeless));
    }


}
