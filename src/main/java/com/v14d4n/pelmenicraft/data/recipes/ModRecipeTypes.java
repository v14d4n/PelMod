package com.v14d4n.pelmenicraft.data.recipes;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PelmeniCraft.MOD_ID);

    public static final RegistryObject<MeatGrinderRecipe.Serializer> GRINDING_SERIALIZER =
            RECIPE_SERIALIZER.register("grinding", MeatGrinderRecipe.Serializer::new);

    public static RecipeType<MeatGrinderRecipe> GRINDING_RECIPE =
            new MeatGrinderRecipe.GrindingRecipeType();

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);

        Registry.register(Registry.RECIPE_TYPE, MeatGrinderRecipe.TYPE_ID, GRINDING_RECIPE);
    }
}
