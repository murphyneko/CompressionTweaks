package com.killerqu.compressiontweaks.emi;

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

import java.util.concurrent.atomic.AtomicInteger;

import static codechicken.microblock.init.CBMicroblockModContent.DIAMOND_SAW;

@EmiEntrypoint
public class CTEMIPlugin implements EmiPlugin {
    public static final EmiStack MB_ICON = EmiStack.of(DIAMOND_SAW.get());
    public static final EmiRecipeCategory MB_CATEGORY = new EmiRecipeCategory(new ResourceLocation("compressiontweaks", "microblock_crafting"), MB_ICON);

    public static final AtomicInteger COUNTER = new AtomicInteger(0);


    @Override
    public void register(EmiRegistry registry) {
        COUNTER.set(0);
        registry.addCategory(MB_CATEGORY);
        registry.addWorkstation(MB_CATEGORY, MB_ICON);

        RecipeManager manager = registry.getRecipeManager();
        for(CraftingRecipe recipe : manager.getAllRecipesFor(RecipeType.CRAFTING)){
            if(recipe instanceof MicroblockShapedRecipe shapedRecipe) registerMicroblockShapedRecipe(registry, shapedRecipe);
            if(recipe instanceof MicroblockShapelessRecipe) registerMicroblockRecipe(registry, recipe, true);
        }
        CompressionTweaks.LOGGER.info("EMI: Registered {} Microblock Recipes!", COUNTER.get());

    }

    public void registerMicroblockShapedRecipe(EmiRegistry registry, MicroblockShapedRecipe recipe){
        COUNTER.incrementAndGet();
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
        COUNTER.incrementAndGet();
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

/*

    @Redirect(method = "addRecipeSafe(Ldev/emi/emi/api/EmiRegistry;Ljava/util/function/Supplier;Lnet/minecraft/world/item/crafting/Recipe;)V", at = @At(value = "INVOKE", target = "Ldev/emi/emi/api/EmiRegistry;addRecipe(Ldev/emi/emi/api/recipe/EmiRecipe;)V"), remap = false)
    private static void addMicroblockRecipes(EmiRegistry instance, EmiRecipe emiRecipe, @Local(argsOnly = true) Recipe<?> recipe){
        if(recipe instanceof MicroblockShapedRecipe shaped){
            List<EmiIngredient> ingredients = new ArrayList<>();
            shaped.getIngredients().forEach(ingredient -> {
                if(ingredient instanceof MicroblockIngredient microblockIngredient) ingredients.add(EmiStack.of(microblockIngredient.getDisplayItem()));
                else ingredients.add(EmiIngredient.of(ingredient));
            });

            instance.addRecipe(new EmiCraftingRecipe(ingredients, EmiStack.of(EmiPort.getOutput(recipe)), EmiPort.getId(recipe), false));
        }
        if(recipe instanceof MicroblockShapelessRecipe shapeless){
            List<EmiIngredient> ingredients = new ArrayList<>();
            shapeless.getIngredients().forEach(ingredient -> {
                if(ingredient instanceof MicroblockIngredient microblockIngredient) ingredients.add(EmiStack.of(microblockIngredient.getDisplayItem()));
                else ingredients.add(EmiIngredient.of(ingredient));
            });

            instance.addRecipe(new EmiCraftingRecipe(ingredients, EmiStack.of(EmiPort.getOutput(recipe)), EmiPort.getId(recipe), true));

        }
    }


 */