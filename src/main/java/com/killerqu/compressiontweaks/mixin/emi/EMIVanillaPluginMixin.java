package com.killerqu.compressiontweaks.mixin.emi;

import com.evandev.reliable_remover.api.ReliableRemoverAPI;
import com.killerqu.compressiontweaks.CompressionTweaks.GemEnchant;
import com.killerqu.compressiontweaks.mixin.GemSmithingRecipeAccessor;
import com.killerqu.compressiontweaks.recipe.EmiGemSmithingRecipe;
import dev.emi.emi.EmiPort;
import dev.emi.emi.EmiUtil;
import dev.emi.emi.VanillaPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.recipe.EmiAnvilRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import org.infernalstudios.miningmaster.recipes.GemSmithingRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Mixin(VanillaPlugin.class)
public abstract class EMIVanillaPluginMixin {

    @Shadow(remap = false)
    protected static ResourceLocation synthetic(String type, String name) {
        return null;
    }

    @Shadow(remap = false)
    protected static void addRecipeSafe(EmiRegistry registry, Supplier<EmiRecipe> supplier) {
    }

    @Unique
    private static final List<GemSmithingRecipe> recipeCache = new ArrayList<>();
    @Unique
    private static final Map<GemEnchant, List<Item>> enchantMap = new HashMap<>();

    @Unique
    private static final TagKey<Item> ELYTRA_REPAIR_MATERIALS = ItemTags.create(new ResourceLocation("propertymodifier", "elytra_repair_material"));
    @Unique
    private static final TagKey<Item> SHIELD_REPAIR_MATERIALS = ItemTags.create(new ResourceLocation("propertymodifier", "shield_repair_material"));


    //Reliable Remover also purges the items from c:hidden_from_recipe_viewers. Forcibly. So the items get shoved into the hidden items list here.
    @Redirect(method = "register", at = @At(value = "INVOKE", target = "Ldev/emi/emi/EmiPort;getDisabledItems()Ljava/util/stream/Stream;"), remap = false)
    private Stream<Item> hideRemovedItems(){
        return Stream.concat(EmiPort.getDisabledItems(),
                EmiPort.getItemRegistry().stream().filter(i -> ReliableRemoverAPI.isItemHidden(i.getDefaultInstance()))
            );
    }


    //This alters the hardcoded repair material for elytras to be tag based. Note that this is just for EMI.
    @Inject(method = "lambda$addRepair$42", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void changeElytraRepairMaterial(CallbackInfoReturnable<EmiRecipe> cir){
        cir.setReturnValue(new EmiAnvilRecipe(EmiStack.of(Items.ELYTRA), EmiIngredient.of(ELYTRA_REPAIR_MATERIALS),
                synthetic("anvil/repairing/material", EmiUtil.subId(Items.ELYTRA) + "/" + EmiUtil.subId(Items.PHANTOM_MEMBRANE))));
    }
    //This alters the hardcoded repair material for shields to be tag based. Note that this is just for EMI.
    @Inject(method = "lambda$addRepair$43", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void changeShieldRepairMaterial(CallbackInfoReturnable<EmiRecipe> cir){
        cir.setReturnValue(new EmiAnvilRecipe(EmiStack.of(Items.SHIELD), EmiIngredient.of(SHIELD_REPAIR_MATERIALS),
                synthetic("anvil/repairing/material", EmiUtil.subId(Items.SHIELD) + "/" + EmiUtil.subId(Items.OAK_PLANKS))));
    }

    //The next few mixins build out the integration for MiningMaster's gem smithing recipes.

    //Gem smithing recipes don't have their own type, so we must strain them out of the pool of smithing recipes.
    @Inject(method = "addRepair", at = @At("HEAD"), remap = false)
    private static void captureGemSmithingRecipes(EmiRegistry registry, Set<Item> hiddenItems, CallbackInfo ci){
        enchantMap.clear();
        List<SmithingRecipe> smithing = registry.getRecipeManager().getAllRecipesFor(RecipeType.SMITHING);
        recipeCache.clear();
        smithing.forEach(recipe -> {
            if(recipe instanceof GemSmithingRecipe gemSmithing) recipeCache.add(gemSmithing);
        });
    }

    //This is where EMI would create the anvil enchanting recipes.
    // We have those nuked, but we can repurpose the item/enchant matching here to build out a map of which items belong to each recipe.
    @Inject(method = "lambda$addRepair$40", at = @At(value = "HEAD"), remap = false)
    private static void buildEnchantMap(EmiRegistry registry, Item i, Enchantment e, CallbackInfo ci){
        recipeCache.forEach(recipe -> {
            if(!((GemSmithingRecipeAccessor) recipe).getBlacklist().test(i.getDefaultInstance()) && ((GemSmithingRecipeAccessor) recipe).getEnchants().contains(e)){
                //GemEnchant is a record defined in the main class. We COULD have just stored the recipe but look at that type casting, don't want to do that every time.
                GemEnchant key = new GemEnchant(e, ((GemSmithingRecipeAccessor) recipe).getGem().getItem(), recipe);
                if(!enchantMap.containsKey(key)) enchantMap.put(key, new ArrayList<>());
                enchantMap.get(key).add(i);
            }
        });
    }

    //This takes the map and builds the actual recipes.
    @Inject(method = "addRepair", at = @At("TAIL"), remap = false)
    private static void addGemSmithingRecipes(EmiRegistry registry, Set<Item> hiddenItems, CallbackInfo ci){
        enchantMap.forEach((gemEnchant, items) -> {
            Enchantment ench = gemEnchant.ench();
            Item gem = gemEnchant.gem();

            for(int i = 0;i<ench.getMaxLevel();i++){
                List<EmiStack> baseList = new ArrayList<>();
                int lvl = i; //this is to make intellij stfu about using an int variable in lambdas
                items.forEach(item -> {
                    ItemStack base = item.getDefaultInstance().copy();
                    if(lvl > 0){
                        base.enchant(ench, lvl);
                    }
                    baseList.add(EmiStack.of(base));
                });
                //That's a custom EmiRecipe type. Explanation in its class.
                addRecipeSafe(registry, () -> new EmiGemSmithingRecipe(
                    EmiIngredient.of(Ingredient.EMPTY), //There's no template.
                    baseList,
                    EmiIngredient.of(Ingredient.of(gem)),
                    EmiStack.of(EmiPort.getOutput(gemEnchant.recipe())), //This is empty. Might replace it.
                    gemEnchant.recipe()
                ));
            }

        });
    }

}
