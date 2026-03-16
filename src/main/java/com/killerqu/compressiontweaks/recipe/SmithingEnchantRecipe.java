package com.killerqu.compressiontweaks.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.killerqu.compressiontweaks.CompressionTweaks;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SmithingEnchantRecipe implements SmithingRecipe {

    private final ResourceLocation recipeId;
    private final NonNullList<Enchantment> enchantments;
    private final Ingredient addition;
    private final Ingredient template;
    private final Ingredient blacklist;
    private final Ingredient whitelist;


    public SmithingEnchantRecipe(ResourceLocation recipeId, NonNullList<Enchantment> enchantments, Ingredient addition, Ingredient template, Ingredient blacklist, Ingredient whitelist){
        this.recipeId = recipeId;
        this.enchantments = enchantments;
        //This should not be replaced by Ingredient.EMPTY
        this.addition = addition;
        //These ones CAN be replaced by Ingredient.EMPTY if there's no need to use them.
        this.template = template;
        this.blacklist = blacklist;
        this.whitelist = whitelist;
    }

    public boolean matches(Container inv, Level world){
        return (addition.test(inv.getItem(2)) && //Addition must match
                template.test(inv.getItem(0)) && //Template must match. If template is empty, slot should be empty.
                this.assemble(inv, world.registryAccess()) != null //Recipe result must be valid
        );
    }

    public ItemStack assemble(Container inv, RegistryAccess reg){
        ItemStack base = inv.getItem(1).copy();
        if(base.isEmpty()){
            return null;
        }
        //This list will already be filtered by what CAN be applied.
        List<Enchantment> toBeApplied = filterCompatibleEnchants(base);
        if(toBeApplied.isEmpty()){
            return null;
        }
        //DO NOT use itemstack.enchant for already enchanted items. It will create duplicate enchant entries.
        Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(base);
        toBeApplied.forEach(enchantment -> {
            if(enchants.containsKey(enchantment)){ //Increase level
                enchants.put(enchantment, enchants.get(enchantment) + 1);
            } else enchants.put(enchantment, 1); //Apply new one
        });
        EnchantmentHelper.setEnchantments(enchants, base);
        return base;
    }

    //This method takes an ItemStack and returns the list of enchants that can be applied to the item.
    private List<Enchantment> filterCompatibleEnchants(ItemStack item){
        List<Enchantment> output = new ArrayList<>();
        Map<Enchantment, Integer> existingEnchants = item.getAllEnchantments();
        this.enchantments.forEach(enchantment -> {
            if(existingEnchants.containsKey(enchantment)){
                //If the enchant is already there, just check if it's not max level.
                if(existingEnchants.get(enchantment) < enchantment.getMaxLevel()){
                    output.add(enchantment);
                    existingEnchants.put(enchantment, existingEnchants.get(enchantment) + 1);
                }
            } else { //Otherwise, check if it's compatible.
                if(enchantment.canEnchant(item) && areEhchantsCompatible(enchantment, existingEnchants)){
                    output.add(enchantment);
                    existingEnchants.put(enchantment, 1);
                }
            }
        });
        return output;
    }

    private static boolean areEhchantsCompatible(Enchantment tested, Map<Enchantment, Integer> map){
        List<Enchantment> list = map.keySet().stream().toList();
        for(int i = 0; i<list.size();i++){
            if(!list.get(i).isCompatibleWith(tested)) return false;
        }
        return true;
    }

    public boolean isTemplateIngredient(ItemStack stack){
        return template.test(stack);
    }

    public boolean isAdditionIngredient(ItemStack stack){
        return addition.test(stack);
    }

    public boolean isBaseIngredient(ItemStack stack){
        return !stack.isEmpty() && //This should fix the helve hammer crash.
                (whitelist.isEmpty() || whitelist.test(stack)) && //If the whitelist is not empty, must match.
                (blacklist.isEmpty() || !blacklist.test(stack)) && //If the blacklist is not empty, must NOT match.
                !filterCompatibleEnchants(stack).isEmpty(); //Must be able to receive at least one enchantment.
    }


    //Getters, ALL the getters.
    public ResourceLocation getId(){
        return this.recipeId;
    }
    public NonNullList<Enchantment> getEnchantments(){
        return enchantments;
    }
    public Ingredient getAddition(){
        return addition;
    }
    public Ingredient getTemplate(){
        return template;
    }
    public Ingredient getBlacklist(){
        return blacklist;
    }
    public Ingredient getWhitelist(){
        return whitelist;
    }
    public boolean isSpecial(){
        return true;
    }


    //Boring mandatory stuff
    public RecipeType<?> getRecipeType(){
        return RecipeType.SMITHING;
    }
    public RecipeSerializer<?> getSerializer(){
        return CTRecipeTypes.SMITHING_EHCHANT_SERIALIZER.get();
    }
    public boolean canCraftInDimensions(int w, int h) {
        return w * h >= 2;
    }
    public ItemStack getResultItem(RegistryAccess reg){
        return ItemStack.EMPTY;
    }


    public static class Serializer implements RecipeSerializer<SmithingEnchantRecipe> {

        @Override
        public SmithingEnchantRecipe fromJson(ResourceLocation id, JsonObject json) {
            NonNullList<Enchantment> enchantments = NonNullList.create();
            JsonArray enchantsJson = GsonHelper.getAsJsonArray(json, "enchantments");
            if(enchantsJson.isEmpty()) throw new JsonSyntaxException("Enchantments list is empty!");
            enchantsJson.forEach(enchantElement -> {
                Enchantment e = ForgeRegistries.ENCHANTMENTS.getValue(ResourceLocation.tryParse(enchantElement.getAsString()));
                if(e == null) throw new JsonSyntaxException("Invalid enchant found in list!");
                else enchantments.add(e);
            });

            Ingredient addition = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "addition"));
            Ingredient template = json.has("template") ? Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "template")) : Ingredient.EMPTY;
            Ingredient blacklist = json.has("blacklist") ? Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "blacklist")) : Ingredient.EMPTY;
            Ingredient whitelist = json.has("whitelist") ? Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "whitelist")) : Ingredient.EMPTY;
            return new SmithingEnchantRecipe(id, enchantments, addition, template, blacklist, whitelist);
        }

        @Override
        public @Nullable SmithingEnchantRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            int enchSize = buf.readInt();
            NonNullList<Enchantment> enchahts = NonNullList.create();
            for(int i=0;i<enchSize;i++){
                Enchantment e = ForgeRegistries.ENCHANTMENTS.getValue(buf.readResourceLocation());
                if(e!=null) enchahts.add(e);
            }
            Ingredient addition = Ingredient.fromNetwork(buf);
            Ingredient template = Ingredient.fromNetwork(buf);
            Ingredient blacklist = Ingredient.fromNetwork(buf);
            Ingredient whitelist = Ingredient.fromNetwork(buf);
            return new SmithingEnchantRecipe(id, enchahts, addition, template, blacklist, whitelist);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, SmithingEnchantRecipe recipe) {
            buf.writeInt(recipe.getEnchantments().size());
            for(Enchantment e : recipe.getEnchantments()) buf.writeResourceLocation(ForgeRegistries.ENCHANTMENTS.getKey(e));
            recipe.getAddition().toNetwork(buf);
            recipe.getTemplate().toNetwork(buf);
            recipe.getBlacklist().toNetwork(buf);
            recipe.getWhitelist().toNetwork(buf);
        }
    }

}
