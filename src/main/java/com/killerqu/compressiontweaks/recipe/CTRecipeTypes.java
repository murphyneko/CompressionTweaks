package com.killerqu.compressiontweaks.recipe;

import com.killerqu.compressiontweaks.CompressionTweaks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//How many of these have i done already?
//Anyway, this is where the recipe types are defined.
public class CTRecipeTypes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, CompressionTweaks.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, CompressionTweaks.MODID);

    public static final RegistryObject<RecipeType<BoulderInfoRecipe>> BOULDER_INFO = RECIPE_TYPES.register("boulder_info",
            () -> new RecipeType<BoulderInfoRecipe>() {
                @Override
                public String toString() {
                    return new ResourceLocation(CompressionTweaks.MODID, "boulder_info").toString();
                }
            });

    public static final RegistryObject<RecipeSerializer<BoulderInfoRecipe>> BOULDER_SERIALIZER = RECIPE_SERIALIZERS.register("boulder_info", BoulderInfoRecipe.Serializer::new);



    public static final RegistryObject<RecipeType<SmithingEnchantRecipe>> SMITHING_EHCHANT = RECIPE_TYPES.register("smithing_enchant",
            () -> new RecipeType<SmithingEnchantRecipe>() {
                @Override
                public String toString() {
                    return new ResourceLocation(CompressionTweaks.MODID, "smithing_enchant").toString();
                }
            });
    public static final RegistryObject<RecipeSerializer<SmithingEnchantRecipe>> SMITHING_EHCHANT_SERIALIZER = RECIPE_SERIALIZERS.register("smithing_enchant", SmithingEnchantRecipe.Serializer::new);




    public static final RegistryObject<RecipeType<LeftoversOverrideRecipe>> LEFTOVERS_OVERRIDE = RECIPE_TYPES.register("leftovers_override",
            () -> new RecipeType<LeftoversOverrideRecipe>() {
                @Override
                public String toString() {
                    return new ResourceLocation(CompressionTweaks.MODID, "leftovers_override").toString();
                }
            });
    public static final RegistryObject<RecipeSerializer<LeftoversOverrideRecipe>> LEFTOVERS_SERIALIZER = RECIPE_SERIALIZERS.register("leftovers_override", LeftoversOverrideRecipe.Serializer::new);


    public static final RegistryObject<RecipeType<OPAInfoRecipe>> OPA_INFO = RECIPE_TYPES.register("opa_info",
            () -> new RecipeType<OPAInfoRecipe>() {
                @Override
                public String toString() {
                    return new ResourceLocation(CompressionTweaks.MODID, "opa_info").toString();
                }
            });
    public static final RegistryObject<RecipeSerializer<OPAInfoRecipe>> OPA_INFO_SERIALIZER = RECIPE_SERIALIZERS.register("opa_info", OPAInfoRecipe.Serializer::new);

/*
    public static final RegistryObject<RecipeType<MicroblockStonecutterRecipe>> MICROBLOCK_STONECUTTING = RECIPE_TYPES.register("microblock_stonecutting",
            () -> new RecipeType<MicroblockStonecutterRecipe>() {
                @Override
                public String toString() {
                    return new ResourceLocation(CompressionTweaks.MODID, "microblock_stonecutting").toString();
                }
            });
    public static final RegistryObject<RecipeSerializer<MicroblockStonecutterRecipe>> MICROBLOCK_STIONECUTTING_SERIALIZER = RECIPE_SERIALIZERS.register("microblock_stonecutting", MicroblockStonecutterRecipe.Serializer::new);
*/

    public static final RegistryObject<RecipeType<MicroblockShapedRecipe>> SHAPED_MICROBLOCK = RECIPE_TYPES.register("shaped_microblock",
            () -> new RecipeType<MicroblockShapedRecipe>() {
                @Override
                public String toString() {
                    return new ResourceLocation(CompressionTweaks.MODID, "shaped_microblock").toString();
                }
            });
    public static final RegistryObject<RecipeSerializer<MicroblockShapedRecipe>> SHAPED_MICROBLOCK_SERIALIZER = RECIPE_SERIALIZERS.register("shaped_microblock", MicroblockShapedRecipe.Serializer::new);


    public static final RegistryObject<RecipeType<MicroblockShapelessRecipe>> SHAPELESS_MICROBLOCK = RECIPE_TYPES.register("shapeless_microblock",
            () -> new RecipeType<MicroblockShapelessRecipe>() {
                @Override
                public String toString() {
                    return new ResourceLocation(CompressionTweaks.MODID, "shapeless_microblock").toString();
                }
            });
    public static final RegistryObject<RecipeSerializer<MicroblockShapelessRecipe>> SHAPELESS_MICROBLOCK_SERIALIZER = RECIPE_SERIALIZERS.register("shapeless_microblock", MicroblockShapelessRecipe.Serializer::new);


}
