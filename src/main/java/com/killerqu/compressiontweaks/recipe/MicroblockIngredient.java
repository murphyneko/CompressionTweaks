package com.killerqu.compressiontweaks.recipe;

import codechicken.microblock.api.BlockMicroMaterial;
import codechicken.microblock.item.ItemMicroBlock;
import codechicken.microblock.recipe.MicroRecipe;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.killerqu.compressiontweaks.CompressionTweaks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.AbstractIngredient;
import net.minecraftforge.common.crafting.IIngredientSerializer;

import static com.killerqu.compressiontweaks.CompressionTweaks.mbDefault;

public class MicroblockIngredient extends AbstractIngredient {

    private int size;
    private int factory;

    public static final MicroblockIngredient.Serializer MICROBLOCK_SERIALIZER = new Serializer();
    public static final ResourceLocation ID = new ResourceLocation(CompressionTweaks.MODID, "microblock");

    public MicroblockIngredient(int size, int factory){
        this.size = size;
        this.factory = factory;
    }

    @Override
    public boolean test(ItemStack stack){
        if(stack.isEmpty() || MicroRecipe.microMaterial(stack) == null) return false;
        return ItemMicroBlock.getFactoryID(stack) == factory && ItemMicroBlock.getSize(stack) == size;
    }


    @Override
    public ItemStack[] getItems(){
        return new ItemStack[] {getDisplayItem()};
    }

    public ItemStack getDisplayItem(){
        return size == -1 ? new ItemStack(mbDefault()) :
                ItemMicroBlock.createStack(1, factory, size, BlockMicroMaterial.makeMaterialKey(mbDefault().defaultBlockState()));
    }


    @Override
    public boolean isSimple() {
        return false;
    }

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer() {
        return MICROBLOCK_SERIALIZER;
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("type", "compressiontweaks:microblock");
        json.addProperty("size", size);
        json.addProperty("factory", factory);
        return json;
    }

    public static class Serializer implements IIngredientSerializer<MicroblockIngredient>{

        @Override
        public MicroblockIngredient parse(JsonObject json) {
            int size = GsonHelper.getAsInt(json, "size");
            int factory = GsonHelper.getAsInt(json, "factory");
            return new MicroblockIngredient(size, factory);
        }

        @Override
        public MicroblockIngredient parse(FriendlyByteBuf buffer) {
            int size = buffer.readInt();
            int factory = buffer.readInt();
            return new MicroblockIngredient(size, factory);
        }

        @Override
        public void write(FriendlyByteBuf buffer, MicroblockIngredient ingredient) {
            buffer.writeInt(ingredient.size);
            buffer.writeInt(ingredient.factory);
        }
    }

}
