package com.killerqu.compressiontweaks.mixin.emi;

import com.killerqu.compressiontweaks.mixin.GemSmithingRecipeAccessor;
import dev.emi.emi.EmiPort;
import dev.emi.emi.EmiUtil;
import dev.emi.emi.VanillaPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.recipe.EmiAnvilRecipe;
import dev.emi.emi.recipe.EmiSmithingRecipe;
import mezz.jei.api.constants.RecipeTypes;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.infernalstudios.miningmaster.recipes.GemSmithingRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

//This mixin alters the hardcoded repair recipes in EMI for elytras and shields to use the tags from Property Modifier
@Mixin(VanillaPlugin.class)
public abstract class EMIVanillaMixin {

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
    private static final TagKey<Item> ELYTRA_REPAIR_MATERIALS = ItemTags.create(new ResourceLocation("propertymodifier", "elytra_repair_material"));
    @Unique
    private static final TagKey<Item> SHIELD_REPAIR_MATERIALS = ItemTags.create(new ResourceLocation("propertymodifier", "shield_repair_material"));

    //These two target the specific suppliers to actually make the EmiAnvilRecipe. So we just cancel them with the "correct" ones.
    @Inject(method = "lambda$addRepair$42", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void changeElytraRepairMaterial(CallbackInfoReturnable<EmiRecipe> cir){
        cir.setReturnValue(new EmiAnvilRecipe(EmiStack.of(Items.ELYTRA), EmiIngredient.of(ELYTRA_REPAIR_MATERIALS),
                synthetic("anvil/repairing/material", EmiUtil.subId(Items.ELYTRA) + "/" + EmiUtil.subId(Items.PHANTOM_MEMBRANE))));
    }
    @Inject(method = "lambda$addRepair$43", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void changeShieldRepairMaterial(CallbackInfoReturnable<EmiRecipe> cir){
        cir.setReturnValue(new EmiAnvilRecipe(EmiStack.of(Items.SHIELD), EmiIngredient.of(SHIELD_REPAIR_MATERIALS),
                synthetic("anvil/repairing/material", EmiUtil.subId(Items.SHIELD) + "/" + EmiUtil.subId(Items.OAK_PLANKS))));
    }

    //Gem smithing recipes don't have their own type, so we must strain them out of the pool of smithing recipes.
    @Inject(method = "addRepair", at = @At("HEAD"), remap = false)
    private static void captureGemSmithingRecipes(EmiRegistry registry, Set<Item> hiddenItems, CallbackInfo ci){
        List<SmithingRecipe> smithing = registry.getRecipeManager().getAllRecipesFor(RecipeType.SMITHING);
        recipeCache.clear();
        smithing.forEach(recipe -> {
            if(recipe instanceof GemSmithingRecipe gemSmithing) recipeCache.add(gemSmithing);
        });
    }

    //This is where EMI would create the anvil enchanting recipes. We have those nuked, but we can repurpose the item/enchant matching here.
    @Inject(method = "lambda$addRepair$40", at = @At(value = "HEAD"), remap = false)
    private static void addMMSmithingRecipes(EmiRegistry registry, Item i, Enchantment e, CallbackInfo ci){
        recipeCache.forEach(recipe -> {
            if(!((GemSmithingRecipeAccessor) recipe).getBlacklist().test(i.getDefaultInstance()) && ((GemSmithingRecipeAccessor) recipe).getEnchants().contains(e)){
                //There's a matching gem smithing recipe, make the smithing recipes
                ItemStack output = new ItemStack(i);
                output.enchant(e,1);
                if(e.getMaxLevel() > 1){
                    Component tooltip = Component.translatable("compressiontweaks.emi.gem_smithing.max_level", e.getFullname(e.getMaxLevel()));
                    ListTag lore = new ListTag();
                    lore.add(StringTag.valueOf(Component.Serializer.toJson(tooltip)));
                    output.getOrCreateTagElement("display").put("Lore", lore);
                }
                addRecipeSafe(registry, () -> new EmiSmithingRecipe(
                        EmiIngredient.of(Ingredient.EMPTY),
                        EmiIngredient.of(Ingredient.of(i)),
                        EmiIngredient.of(Ingredient.of(((GemSmithingRecipeAccessor) recipe).getGem())),
                        EmiStack.of(output),
                        //EmiPort.getId(recipe)),
                        synthetic("smithing/gem_enchanting", EmiUtil.subId(i) + "/" + EmiUtil.subId(EmiPort.getEnchantmentRegistry().getKey(e)))
                )
                );
            }
        });
    }

}
