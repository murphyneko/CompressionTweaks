package com.killerqu.compressiontweaks.recipe;

import codechicken.microblock.api.BlockMicroMaterial;
import codechicken.microblock.api.MicroMaterial;
import codechicken.microblock.item.ItemMicroBlock;
import codechicken.microblock.recipe.MicroRecipe;
import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import static com.killerqu.compressiontweaks.CompressionTweaks.mbDefault;

public class MicroblockShapedRecipe extends ShapedRecipe{
    int outputSize;
    int outputFactory;

    public MicroblockShapedRecipe(ShapedRecipe r, int size, int factory){
        super(r.getId(), r.getGroup(), CraftingBookCategory.MISC, r.getRecipeWidth(), r.getRecipeHeight(), r.getIngredients(),
                r.getResultItem(null));
        this.outputSize = size;
        this.outputFactory = factory;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return outputSize == 8 ? new ItemStack(mbDefault(), super.getResultItem(registryAccess).getCount()) :
         ItemMicroBlock.createStack(super.getResultItem(registryAccess).getCount(), outputFactory, outputSize, BlockMicroMaterial.makeMaterialKey(mbDefault().defaultBlockState()));
    }

    @Override
    public boolean matches(CraftingContainer container, Level level){
        if(!super.matches(container, level)) return false;
        MicroMaterial last = null;
        for(int i = 0; i<container.getContainerSize(); i++){
            ItemStack stack = container.getItem(i);
            MicroMaterial mat = MicroRecipe.microMaterial(stack);
            if(mat != null){
                if(last == null) last = mat;
                if(!last.getRegistryName().equals(mat.getRegistryName())) return false; //Material mismatch, abort here.
            }
        }
        return last != null; //This would mean the recipe has no item with a material.
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess){
        for(int i = 0; i<container.getContainerSize(); i++){
            if(container.getItem(i).isEmpty()) continue;
            MicroMaterial mat = MicroRecipe.microMaterial(container.getItem(i));
            if(mat != null){
                return outputSize == 8 ? new ItemStack(mat.getItem().getItem(), super.getResultItem(registryAccess).getCount()) :
                        ItemMicroBlock.createStack(super.getResultItem(registryAccess).getCount(), outputFactory, outputSize, mat);
            }
        }
        //No item with a material found, This should not happen as it is checked for in matches
        return ItemStack.EMPTY;
    }


    @Override
    public RecipeType<?> getType(){
        return RecipeType.CRAFTING;
    }

    @Override
    public RecipeSerializer<?> getSerializer(){
        return CTRecipeTypes.SHAPED_MICROBLOCK_SERIALIZER.get();
    }

    public static class Serializer implements RecipeSerializer<MicroblockShapedRecipe> {
        public static ShapedRecipe.Serializer serializer = new ShapedRecipe.Serializer();
        public MicroblockShapedRecipe fromJson(ResourceLocation id, JsonObject json){
            ShapedRecipe r = serializer.fromJson(id, json);
            int size = GsonHelper.getAsInt(json, "output_size");
            int factory = GsonHelper.getAsInt(json, "output_factory");
            return new MicroblockShapedRecipe(r, size, factory);
        }
        public @Nullable MicroblockShapedRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf){
            int size = buf.readInt();
            int factory = buf.readInt();
            return new MicroblockShapedRecipe(serializer.fromNetwork(id, buf), size, factory);
        }
        public void toNetwork(FriendlyByteBuf buf, MicroblockShapedRecipe recipe){
            buf.writeInt(recipe.outputSize);
            buf.writeInt(recipe.outputFactory);
            serializer.toNetwork(buf, recipe);
        }
    }

}