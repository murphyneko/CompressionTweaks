/*package com.killerqu.compressiontweaks.recipe;

import codechicken.microblock.api.BlockMicroMaterial;
import codechicken.microblock.api.MicroMaterial;
import codechicken.microblock.item.ItemMicroBlock;
import codechicken.microblock.recipe.MicroRecipe;
import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import static codechicken.microblock.init.CBMicroblockModContent.MICRO_BLOCK_ITEM;
import static com.killerqu.compressiontweaks.CompressionTweaks.mbDefault;

public class MicroblockStonecutterRecipe extends StonecutterRecipe{
    private ResourceLocation id;
    private int inputSize;
    private int inputFactory;
    private int outputSize;
    private int outputFactory;
    private int outputAmount;

    public MicroblockStonecutterRecipe(ResourceLocation id, int inSize, int inFactory, int outSize, int outFactory, int outAmount){
        super(id, "", Ingredient.EMPTY, placeholderResult());
        this.id = id;
        this.inputSize = inSize;
        this.inputFactory = inFactory;
        this.outputSize = outSize;
        this.outputFactory = outFactory;
        this.outputAmount = outAmount;
    }

    @Override
    public boolean matches(Container container, Level level) {
        ItemStack input = container.getItem(0);
        if(input.isEmpty() || MicroRecipe.microMaterial(input) == null) return false;
        int size = input.is(MICRO_BLOCK_ITEM.get()) ? ItemMicroBlock.getSize(input) : 8;
        int factory = input.is(MICRO_BLOCK_ITEM.get()) ? ItemMicroBlock.getFactoryID(input) : 0;
        return size == inputSize && factory == inputFactory;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess reg) {
        ItemStack input = container.getItem(0);
        MicroMaterial mat = MicroRecipe.microMaterial(input);
        if(input.isEmpty() || mat == null) return ItemStack.EMPTY;
        return ItemMicroBlock.createStack(outputAmount, outputFactory, outputSize, mat);
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {  return true; }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) { //This is the same default as the real MicroRecipes
        return ItemMicroBlock.createStack(outputAmount, outputFactory, outputSize, BlockMicroMaterial.makeMaterialKey(mbDefault().defaultBlockState()));
    }

    public ItemStack getInputAwareResultItem(ItemStack input){
        MicroMaterial mat = MicroRecipe.microMaterial(input);
        if(mat == null) return ItemStack.EMPTY;
        return ItemMicroBlock.createStack(outputAmount, outputFactory, outputSize, mat);
    }

    public static ItemStack placeholderResult(){
        return ItemMicroBlock.create(1, 1, BlockMicroMaterial.makeMaterialKey(mbDefault().defaultBlockState()));
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CTRecipeTypes.MICROBLOCK_STIONECUTTING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.STONECUTTING;
    }


    public static class Serializer implements RecipeSerializer<MicroblockStonecutterRecipe>{

        @Override
        public MicroblockStonecutterRecipe fromJson(ResourceLocation id, JsonObject json){
            int inSize = GsonHelper.getAsInt(json, "input_size");
            int inFactory = GsonHelper.getAsInt(json, "input_factory");
            int outSize = GsonHelper.getAsInt(json, "output_size");
            int outFactory = GsonHelper.getAsInt(json, "output_factory");
            int outAmount = GsonHelper.getAsInt(json, "output_amount");
            return new MicroblockStonecutterRecipe(id, inSize, inFactory, outSize, outFactory, outAmount);
        }

        @Override
        public @Nullable MicroblockStonecutterRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf){
            int inSize = buf.readInt();
            int inFactory = buf.readInt();
            int outSize = buf.readInt();
            int outFactory = buf.readInt();
            int outAmount = buf.readInt();
            return new MicroblockStonecutterRecipe(id, inSize, inFactory, outSize, outFactory, outAmount);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, MicroblockStonecutterRecipe recipe){
            buf.writeInt(recipe.inputSize);
            buf.writeInt(recipe.inputFactory);
            buf.writeInt(recipe.outputSize);
            buf.writeInt(recipe.outputFactory);
            buf.writeInt(recipe.outputFactory);
        }


    }


}
*/