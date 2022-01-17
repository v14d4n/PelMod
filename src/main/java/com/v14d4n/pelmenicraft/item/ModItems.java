package com.v14d4n.pelmenicraft.item;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import com.v14d4n.pelmenicraft.item.custom.Cumpot;
import com.v14d4n.pelmenicraft.item.custom.PelmeniBowl;
import com.v14d4n.pelmenicraft.item.custom.RollingPin;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PelmeniCraft.MOD_ID);

    public static final RegistryObject<Item> ROLLING_PIN = ITEMS.register("rolling_pin",
            RollingPin::new);

    public static final RegistryObject<Item> CUMPOT = ITEMS.register("cumpot",
            () -> new Cumpot(new FoodProperties.Builder()
                    .alwaysEat()
                    .nutrition(3)
                    .saturationMod(0.6f)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0), 1f) // Strength
                    .build()));

    public static final RegistryObject<Item> PELMEN = ITEMS.register("pelmen",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.PELMENICRAFT_TAB)
                    .food(new FoodProperties.Builder()
                            .fast()
                            .meat()
                            .nutrition(3)
                            .saturationMod(0.6f)
                            .build())));

    public static final RegistryObject<Item> FRIED_PELMEN = ITEMS.register("fried_pelmen",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.PELMENICRAFT_TAB)
                    .food(new FoodProperties.Builder()
                            .fast()
                            .meat()
                            .nutrition(4)
                            .saturationMod(0.8f)
                            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60, 0), 0.8f)
                            .build())));

    public static final RegistryObject<Item> MAYONNAISE = ITEMS.register("mayonnaise",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.PELMENICRAFT_TAB)
                    .food(new FoodProperties.Builder()
                            .fast()
                            .nutrition(2)
                            .saturationMod(0.2f)
                            .build())));

    public static final RegistryObject<Item> SMETANA = ITEMS.register("smetana",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.PELMENICRAFT_TAB)
                    .food(new FoodProperties.Builder()
                            .fast()
                            .nutrition(2)
                            .saturationMod(0.2f)
                            .build())));

    public static final RegistryObject<Item> PELMENI_BOWL = ITEMS.register("pelmeni_bowl",
            () -> new PelmeniBowl(new FoodProperties.Builder()
                    .nutrition(9)
                    .saturationMod(2.1f)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1f)
                    .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 300, 0), 1f) // Haste
                    .build()));

    public static final RegistryObject<Item> PELMENI_BOWL_WITH_MAYONNAISE = ITEMS.register("pelmeni_bowl_with_mayonnaise",
            () -> new PelmeniBowl(new FoodProperties.Builder()
                    .nutrition(11)
                    .saturationMod(2.3f)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1f)
                    .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 300, 0), 1f) // Haste
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 0), 1f)
                    .build()));

    public static final RegistryObject<Item> PELMENI_BOWL_WITH_SMETANA = ITEMS.register("pelmeni_bowl_with_smetana",
            () -> new PelmeniBowl(new FoodProperties.Builder()
                    .nutrition(11)
                    .saturationMod(2.3f)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1f)
                    .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 300, 0), 1f) // Haste
                    .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 800, 0), 1f)
                    .build()));

    public static final RegistryObject<Item> FRIED_PELMENI_BOWL = ITEMS.register("fried_pelmeni_bowl",
            () -> new PelmeniBowl(new FoodProperties.Builder()
                    .nutrition(12)
                    .saturationMod(2.4f)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 150, 0), 1f)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 0), 1f) // Strength
                    .build()));

    public static final RegistryObject<Item> FRIED_PELMENI_BOWL_WITH_MAYONNAISE = ITEMS.register("fried_pelmeni_bowl_with_mayonnaise",
            () -> new PelmeniBowl(new FoodProperties.Builder()
                    .nutrition(14)
                    .saturationMod(2.6f)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 150, 0), 1f)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 0), 1f) // Strength
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 450, 0), 1f)
                    .build()));

    public static final RegistryObject<Item> FRIED_PELMENI_BOWL_WITH_SMETANA = ITEMS.register("fried_pelmeni_bowl_with_smetana",
            () -> new PelmeniBowl(new FoodProperties.Builder()
                    .nutrition(14)
                    .saturationMod(2.6f)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 150, 0), 1f)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 0), 1f) // Strength
                    .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 1200, 0), 1f)
                    .build()));

    public static final RegistryObject<Item> DOUGH = ITEMS.register("dough",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.PELMENICRAFT_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationMod(0.6f)
                            .build())));

    public static final RegistryObject<Item> ROLLED_DOUGH = ITEMS.register("rolled_dough",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.PELMENICRAFT_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationMod(0.6f)
                            .build())));

    public static final RegistryObject<Item> GROUNDMEAT = ITEMS.register("groundmeat",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.PELMENICRAFT_TAB)
                    .food(new FoodProperties.Builder()
                            .meat()
                            .nutrition(2)
                            .saturationMod(0.6f)
                            .build())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
