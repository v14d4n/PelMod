package com.v14d4n.pelmenicraft.integration.jei;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import com.v14d4n.pelmenicraft.block.ModBlocks;
import com.v14d4n.pelmenicraft.data.recipes.MeatGrinderRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class MeatGrinderRecipeCategory implements IRecipeCategory<MeatGrinderRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(PelmeniCraft.MOD_ID, "grinding");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(PelmeniCraft.MOD_ID, "textures/gui/meat_grinder_gui_jei.png");

    private final IDrawable background;
    private final IDrawable icon;

    public MeatGrinderRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0,0,176, 84);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.MEAT_GRINDER.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends MeatGrinderRecipe> getRecipeClass() {
        return MeatGrinderRecipe.class;
    }

    @Override
    public Component getTitle() {
        return ModBlocks.MEAT_GRINDER.get().getName();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(MeatGrinderRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, MeatGrinderRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 79, 34);

        recipeLayout.getItemStacks().init(1, false, 79, 56);
        recipeLayout.getItemStacks().set(ingredients);
    }
}
