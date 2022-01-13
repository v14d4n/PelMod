package com.v14d4n.pelmenicraft.data.recipes;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface IMeatGrinderRecipe extends Recipe<Container> {
    ResourceLocation TYPE_ID = new ResourceLocation(PelmeniCraft.MOD_ID, "grinding");

    @Override
    default RecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    @Override
    default boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    default boolean isSpecial() {
        return true;
    }
}
