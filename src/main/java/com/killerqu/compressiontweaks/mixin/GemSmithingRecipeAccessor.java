package com.killerqu.compressiontweaks.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import org.infernalstudios.miningmaster.recipes.GemSmithingRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GemSmithingRecipe.class)
public interface GemSmithingRecipeAccessor {
    @Accessor(value = "enchantments", remap = false)
    NonNullList<Enchantment> getEnchants();
    @Accessor(value = "gem", remap = false)
    ItemStack getGem();
    @Accessor(value = "blacklist", remap = false)
    Ingredient getBlacklist();
}
