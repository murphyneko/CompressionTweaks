package com.killerqu.compressiontweaks.jei;

import com.killerqu.compressiontweaks.CompressionTweaks;
import com.killerqu.compressiontweaks.recipe.OPAInfoRecipe;
import com.mojang.blaze3d.systems.RenderSystem;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class OPAInfoCategory implements IRecipeCategory<OPAInfoRecipe> {
    public static final RecipeType<OPAInfoRecipe> TYPE = RecipeType.create(CompressionTweaks.MODID, "froth_flotation", OPAInfoRecipe.class);
    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable slot;
    private static final ResourceLocation guiTexture = new ResourceLocation(CompressionTweaks.MODID, "textures/gui/opa_info_jei.png");

    public static final Component TEXT_PRIMARIES = Component.translatable("compressiontweaks.jei.opacategory.primaries").withStyle(ChatFormatting.UNDERLINE);
    public static final Component TEXT_SECONDARIES = Component.translatable("compressiontweaks.jei.opacategory.secondaries").withStyle(ChatFormatting.UNDERLINE);
    public static final Component TEXT_INPUT = Component.translatable("compressiontweaks.jei.opacategory.input_tooltip");
    public static final Component TEXT_OPA = Component.translatable("compressiontweaks.jei.opacategory.opa_tooltip");
    public static final Component TEXT_FROTH = Component.translatable("compressiontweaks.jei.opacategory.opa_froth_tooltip");
    public static final Component TEXT_CONTAMINATED = Component.translatable("compressiontweaks.jei.opacategory.contaminated_opa_tooltip");

    public OPAInfoCategory(IGuiHelper helper){
        this.title = Component.translatable("compressiontweaks.jei.opacategory.title");
        this.background = helper.createDrawable(guiTexture, 0,0, 162, 144);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("nyagibits_bytes", "sample_opa_1214"))));
        this.slot = helper.getSlotDrawable();
    }

    @Override public RecipeType<OPAInfoRecipe> getRecipeType(){ return TYPE; }
    @Override public Component getTitle() { return title; }
    @Override public IDrawable getBackground(){ return background; }
    @Override public IDrawable getIcon() { return icon; }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, OPAInfoRecipe recipe, IFocusGroup focuses){
        builder.addSlot(RecipeIngredientRole.INPUT, 37, 19).addItemStack(recipe.getInput())
                .addRichTooltipCallback((recipeSlotView, tooltip) -> {
                    tooltip.add(TEXT_INPUT); });
        builder.addSlot(RecipeIngredientRole.INPUT, 37, 55).addFluidStack(recipe.getOPA().getFluid(), recipe.getOPA().getAmount())
                .addRichTooltipCallback((recipeSlotView, tooltip) -> {
                   tooltip.add(TEXT_OPA); });
        builder.addSlot(RecipeIngredientRole.OUTPUT, 109, 19).addFluidStack(recipe.getOPAFroth().getFluid(), recipe.getOPAFroth().getAmount())
                .addRichTooltipCallback((recipeSlotView, tooltip) -> {
                    tooltip.add(TEXT_FROTH); });
        builder.addSlot(RecipeIngredientRole.OUTPUT, 109, 55).addFluidStack(recipe.getContaminatedOPA().getFluid(), recipe.getContaminatedOPA().getAmount())
                .addRichTooltipCallback((recipeSlotView, tooltip) -> {
                    tooltip.add(TEXT_CONTAMINATED); });

        int xOffset = background.getWidth()/2 - (9*(recipe.getPrimaryOutputs().size()))+2;
        for(int i = 0; i< recipe.getPrimaryOutputs().size(); i++){
            ItemStack item = recipe.getPrimaryOutputs().get(i);
            builder.addSlot(RecipeIngredientRole.OUTPUT, xOffset, 91).addItemStack(item);
            xOffset += 18;
        }

        xOffset = background.getWidth()/2 - (9*(recipe.getSecondaryOutputs().size()))+2;
        for(int i = 0; i< recipe.getSecondaryOutputs().size(); i++){
            ItemStack item = recipe.getSecondaryOutputs().get(i);
            builder.addSlot(RecipeIngredientRole.OUTPUT, xOffset, 127).addItemStack(item);
            xOffset += 18;
        }

    }

    @Override
    public void draw(OPAInfoRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics gui, double mouseX, double mouseY){
        RenderSystem.enableBlend();
        Font font = Minecraft.getInstance().font;
        //So it would be like "compressiontweaks.jei.opa.iron_opa", or whatever the recipe filenames end up being
        Component opaName = Component.translatable("compressiontweaks.jei.opa."+recipe.getId().getPath());
        int textX = (background.getWidth()/2) - (font.width(opaName.getString())/2);
        gui.drawString(font, opaName.getVisualOrderText(), textX, 3, 0x888888, false);

        textX = (background.getWidth()/2) - (font.width(TEXT_PRIMARIES.getString())/2);
        gui.drawString(font, TEXT_PRIMARIES.getVisualOrderText(), textX, 75, 0x888888, false);

        textX = (background.getWidth()/2) - (font.width(TEXT_SECONDARIES.getString())/2);
        gui.drawString(font, TEXT_SECONDARIES.getVisualOrderText(), textX, 111, 0x888888, false);

        slot.draw(gui, 36, 18);
        slot.draw(gui, 36, 54);
        slot.draw(gui, 108, 18);
        slot.draw(gui, 108, 54);

        int xOffset = background.getWidth()/2 - (9*(recipe.getPrimaryOutputs().size()))+1;
        for(int i = 0; i< recipe.getPrimaryOutputs().size(); i++){
            slot.draw(gui, xOffset, 90);
            xOffset += 18;
        }

        xOffset = background.getWidth()/2 - (9*(recipe.getSecondaryOutputs().size()))+1;
        for(int i = 0; i< recipe.getSecondaryOutputs().size(); i++){
            slot.draw(gui, xOffset, 126);
            xOffset += 18;
        }

        RenderSystem.disableBlend();
    }

    @Override
    public void getTooltip(ITooltipBuilder builder, OPAInfoRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY){
        List<Component> tooltips = new ArrayList<>();
        Font font = Minecraft.getInstance().font;

        float textPos = ((float) background.getWidth()/2) - ((float) font.width(TEXT_PRIMARIES.getString())/2);
        if(mouseX > textPos && mouseX < textPos+font.width(TEXT_PRIMARIES.getString()) && mouseY >= 75 && mouseY <= 75 + font.lineHeight){
            tooltips.add(Component.translatable("compressiontweaks.jei.opacategory.info.primaries"));
        }

        textPos = ((float) background.getWidth()/2) - ((float) font.width(TEXT_SECONDARIES.getString())/2);
        if(mouseX > textPos && mouseX < textPos+font.width(TEXT_SECONDARIES.getString()) && mouseY >= 111 && mouseY <= 111 + font.lineHeight){
            tooltips.add(Component.translatable("compressiontweaks.jei.opacategory.info.secondaries"));
        }

        builder.addAll(tooltips);
    }



}
