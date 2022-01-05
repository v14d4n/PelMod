package com.v14d4n.pelmenicraft.data.recipes;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeTypes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PelmeniCraft.MOD_ID);

    public static final RegistryObject<MeatGrinderRecipe.Serializer> GRINDING_SERIALIZER =
            RECIPE_SERIALIZER.register("grinding", MeatGrinderRecipe.Serializer::new);

    public static IRecipeType<MeatGrinderRecipe> GRINDING_RECIPE =
            new MeatGrinderRecipe.GrindingRecipeType();

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);

        Registry.register(Registry.RECIPE_TYPE, MeatGrinderRecipe.TYPE_ID, GRINDING_RECIPE);
    }
}
