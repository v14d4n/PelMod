package com.v14d4n.pelmenicraft.data.recipes;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public interface IMeatGrinderRecipe extends IRecipe<IInventory> {
    ResourceLocation TYPE_ID = new ResourceLocation(PelmeniCraft.MOD_ID, "grinding");

    @Override
    default IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    @Override
    default boolean canFit(int width, int height) {
        return true;
    }

    @Override
    default boolean isDynamic() {
        return true;
    }
}
