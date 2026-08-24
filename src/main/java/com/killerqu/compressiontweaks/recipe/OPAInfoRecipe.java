package com.killerqu.compressiontweaks.recipe;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.killerqu.compressiontweaks.CompressionTweaks;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class OPAInfoRecipe implements Recipe<Container> {

    private final ResourceLocation id;
    // The impure dust to be tossed in
    private final ItemStack input;
    // The OPA fluid itself. Defined anyway to not have to import NB&B
    private final FluidStack OPA;
    // The contaminated OPA fluid
    private final FluidStack contaminatedOPA;
    // The sourced froth fluid
    private final FluidStack OPAFroth;
    // The important outputs of this process. Should include stuff like the pure dust
    // (skipping intermediate steps) and other important outputs, like manganese.
    private final List<ItemStack> primaryOutputs;
    // A list of items that can be derived from the process but aren't as important.
    private final List<ItemStack> secondaryOutputs;


    public OPAInfoRecipe(ResourceLocation id, ItemStack input, FluidStack OPA, FluidStack contaminatedOPA, FluidStack OPAFroth, List<ItemStack> primaryOutputs, List<ItemStack> secondaryOutputs){
        this.id = id;
        this.input = input;
        this.OPA = OPA;
        this.contaminatedOPA = contaminatedOPA;
        this.OPAFroth = OPAFroth;
        this.primaryOutputs = primaryOutputs;
        this.secondaryOutputs = secondaryOutputs;
    }

    public ResourceLocation getId(){
        return id; }
    public ItemStack getInput(){
        return input; }
    public FluidStack getOPA(){
        return OPA; }
    public FluidStack getContaminatedOPA(){
        return contaminatedOPA; }
    public FluidStack getOPAFroth(){
        return OPAFroth; }
    public List<ItemStack> getPrimaryOutputs(){
        return primaryOutputs; }
    public List<ItemStack> getSecondaryOutputs(){
        return secondaryOutputs; }


    public static class Serializer implements RecipeSerializer<OPAInfoRecipe>{

        @Override
        public OPAInfoRecipe fromJson(ResourceLocation id, JsonObject json){
            ItemStack input = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("impure"));
            FluidStack OPA = BoulderInfoRecipe.jsonToFluidStack(GsonHelper.getAsJsonObject(json, "opa"));
            FluidStack contaminatedOPA = BoulderInfoRecipe.jsonToFluidStack(GsonHelper.getAsJsonObject(json, "contaminated_opa"));
            FluidStack OPAFroth = BoulderInfoRecipe.jsonToFluidStack(GsonHelper.getAsJsonObject(json, "opa_froth"));

            JsonArray primariesList = json.getAsJsonArray("primary_outputs");
            List<ItemStack> primaryOutputs = new ArrayList<>();
            primariesList.forEach(jsonElement -> {
                primaryOutputs.add(ShapedRecipe.itemStackFromJson(jsonElement.getAsJsonObject()));
            });

            JsonArray secondariesList = json.getAsJsonArray("secondary_outputs");
            List<ItemStack> secondaryOutputs = new ArrayList<>();
            secondariesList.forEach(jsonElement -> {
                secondaryOutputs.add(ShapedRecipe.itemStackFromJson(jsonElement.getAsJsonObject()));
            });

            return new OPAInfoRecipe(id, input, OPA, contaminatedOPA, OPAFroth, primaryOutputs, secondaryOutputs);
        }

        @Override
        public @Nullable OPAInfoRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf){
            ItemStack input = buf.readItem();
            FluidStack OPA = buf.readFluidStack();
            FluidStack contaminatedOPA = buf.readFluidStack();
            FluidStack OPAFroth = buf.readFluidStack();
            int primariesLength = buf.readInt();
            List<ItemStack> primaryOutputs = new ArrayList<>();
            for(int i = 0;i<primariesLength;i++){
                ItemStack item = buf.readItem();
                primaryOutputs.add(item);
            }
            int secondariesLength = buf.readInt();
            List<ItemStack> secondaryOutputs = new ArrayList<>();
            for(int i = 0;i<secondariesLength;i++){
                ItemStack item = buf.readItem();
                secondaryOutputs.add(item);
            }
            return new OPAInfoRecipe(id, input, OPA, contaminatedOPA, OPAFroth, primaryOutputs, secondaryOutputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, OPAInfoRecipe recipe){
            buf.writeItem(recipe.getInput());
            buf.writeFluidStack(recipe.getOPA());
            buf.writeFluidStack(recipe.getContaminatedOPA());
            buf.writeFluidStack(recipe.getOPAFroth());
            buf.writeInt(recipe.getPrimaryOutputs().size());
            for(int i = 0;i < recipe.getPrimaryOutputs().size();i++){
                buf.writeItem(recipe.getPrimaryOutputs().get(i));
            }
            buf.writeInt(recipe.getSecondaryOutputs().size());
            for(int i = 0;i < recipe.getSecondaryOutputs().size();i++){
                buf.writeItem(recipe.getSecondaryOutputs().get(i));
            }
        }


    }


    @Override public boolean matches(Container p_44002_, Level p_44003_) {return false;}
    @Override public ItemStack assemble(Container p_44001_, RegistryAccess p_267165_) {return null;}
    @Override public boolean canCraftInDimensions(int p_43999_, int p_44000_) {return false;}
    @Override public ItemStack getResultItem(RegistryAccess p_267052_) {return ItemStack.EMPTY;}
    @Override public RecipeSerializer<?> getSerializer() { return CTRecipeTypes.OPA_INFO_SERIALIZER.get(); }
    @Override public RecipeType<?> getType() { return CTRecipeTypes.OPA_INFO.get(); }
}
