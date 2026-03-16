package com.killerqu.compressiontweaks.recipe;

import dev.emi.emi.EmiUtil;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.render.EmiTooltipComponents;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.emi.emi.recipe.special.EmiSmithingTrimRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmithingRecipe;

import java.util.List;
import java.util.Random;


/*
So why is this a thing?
EMI does a wonderful little thing when making EmiIngredients.
If passing a list of it that fully contains an item tag, those items get collapsed into said tag.
However, doing so wipes any NBT they might have contained. Fine for most cases. Unless you actually needed that NBT. Which we do.
So this is a near copy of EmiSmithingTrimRecipe, modified to take in a List<EmiStack> instead of an EmiIngredient, skipping that merging step.
 */
public class EmiSmithingEnchantRecipe extends EmiSmithingTrimRecipe {

    private final SmithingRecipe recipe;
    private final int uniq = EmiUtil.RANDOM.nextInt();
    private final List<EmiStack> inputList;

    public EmiSmithingEnchantRecipe(EmiIngredient template, List<EmiStack> input, EmiIngredient addition, EmiStack output, SmithingRecipe recipe) {
        super(template, EmiIngredient.of(input), addition, output, recipe);
        this.recipe = recipe;
        this.inputList = input;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(EmiTexture.EMPTY_ARROW, 62, 1);
        widgets.addSlot(template, 0, 0);
        widgets.addGeneratedSlot(r -> getStack(r, 0), uniq, 18, 0).appendTooltip(() -> EmiTooltipComponents.getIngredientTooltipComponent(input.getEmiStacks()));
        widgets.addGeneratedSlot(r -> getStack(r, 1), uniq, 36, 0).appendTooltip(() -> EmiTooltipComponents.getIngredientTooltipComponent(addition.getEmiStacks()));
        widgets.addGeneratedSlot(r -> getStack(r, 2), uniq, 94, 0).recipeContext(this);
    }

    private EmiStack getStack(Random r, int i) {
        EmiStack inputStack = this.inputList.get(r.nextInt(this.inputList.size()));
        EmiStack addition = this.addition.getEmiStacks().get(r.nextInt(this.addition.getEmiStacks().size()));
        Container inv = new SimpleContainer(template.getEmiStacks().get(0).getItemStack(), inputStack.getItemStack(), addition.getItemStack(), ItemStack.EMPTY);
        Minecraft client = Minecraft.getInstance();
        return new EmiStack[] {
                inputStack,
                addition,
                EmiStack.of(recipe.assemble(inv, client.level.registryAccess()))
        }[i];
    }

}
