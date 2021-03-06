package com.v14d4n.pelmenicraft.item;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import com.v14d4n.pelmenicraft.item.custom.Cumpot;
import com.v14d4n.pelmenicraft.item.custom.PelmeniBowl;
import com.v14d4n.pelmenicraft.item.custom.RollingPin;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PelmeniCraft.MOD_ID);

    public static final RegistryObject<Item> ROLLING_PIN = ITEMS.register("rolling_pin", RollingPin::new);

    public static final RegistryObject<Item> CUMPOT = ITEMS.register("cumpot",
            () -> new Cumpot(new Food.Builder()
                    .setAlwaysEdible()
                    .hunger(3)
                    .saturation(0.6f)
                    .effect(() -> new EffectInstance(
                            Effects.STRENGTH, 200, 0), 1f)
                    .build()));

    public static final RegistryObject<Item> PELMEN = ITEMS.register("pelmen",
            () -> new Item(new Item.Properties().group(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .fastToEat()
                            .meat()
                            .hunger(3)
                            .saturation(0.6f)
                            .build())));

    public static final RegistryObject<Item> FRIED_PELMEN = ITEMS.register("fried_pelmen",
            () -> new Item(new Item.Properties().group(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .fastToEat()
                            .meat()
                            .hunger(4)
                            .saturation(0.8f)
                            .effect(() -> new EffectInstance(
                                    Effects.REGENERATION, 60, 0), 0.8f)
                            .build())));

    public static final RegistryObject<Item> MAYONNAISE = ITEMS.register("mayonnaise",
            () -> new Item(new Item.Properties().group(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .fastToEat()
                            .hunger(2)
                            .saturation(0.2f)
                            .build())));

    public static final RegistryObject<Item> SMETANA = ITEMS.register("smetana",
            () -> new Item(new Item.Properties().group(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .fastToEat()
                            .hunger(2)
                            .saturation(0.2f)
                            .build())));

    public static final RegistryObject<Item> PELMENI_BOWL = ITEMS.register("pelmeni_bowl",
            () -> new PelmeniBowl(new Food.Builder()
                    .hunger(9)
                    .saturation(2.1f)
                    .effect(() -> new EffectInstance(Effects.REGENERATION, 100, 0), 1f)
                    .effect(() -> new EffectInstance(Effects.HASTE, 300, 0), 1f)
                    .build()));

    public static final RegistryObject<Item> PELMENI_BOWL_WITH_MAYONNAISE = ITEMS.register("pelmeni_bowl_with_mayonnaise",
            () -> new PelmeniBowl(new Food.Builder()
                    .hunger(11)
                    .saturation(2.3f)
                    .effect(() -> new EffectInstance(Effects.REGENERATION, 100, 0), 1f)
                    .effect(() -> new EffectInstance(Effects.HASTE, 300, 0), 1f)
                    .effect(() -> new EffectInstance(Effects.SPEED, 300, 0), 1f)
                    .build()));

    public static final RegistryObject<Item> PELMENI_BOWL_WITH_SMETANA = ITEMS.register("pelmeni_bowl_with_smetana",
            () -> new PelmeniBowl(new Food.Builder()
                    .hunger(11)
                    .saturation(2.3f)
                    .effect(() -> new EffectInstance(Effects.REGENERATION, 100, 0), 1f)
                    .effect(() -> new EffectInstance(Effects.HASTE, 300, 0), 1f)
                    .effect(() -> new EffectInstance(Effects.SATURATION, 800, 0), 1f)
                    .build()));

    public static final RegistryObject<Item> FRIED_PELMENI_BOWL = ITEMS.register("fried_pelmeni_bowl",
            () -> new PelmeniBowl(new Food.Builder()
                    .hunger(12)
                    .saturation(2.4f)
                    .effect(() -> new EffectInstance(Effects.REGENERATION, 150, 0), 1f)
                    .effect(() -> new EffectInstance(Effects.STRENGTH, 300, 0), 1f)
                    .build()));

    public static final RegistryObject<Item> FRIED_PELMENI_BOWL_WITH_MAYONNAISE = ITEMS.register("fried_pelmeni_bowl_with_mayonnaise",
            () -> new PelmeniBowl(new Food.Builder()
                    .hunger(14)
                    .saturation(2.6f)
                    .effect(() -> new EffectInstance(Effects.REGENERATION, 150, 0), 1f)
                    .effect(() -> new EffectInstance(Effects.STRENGTH, 300, 0), 1f)
                    .effect(() -> new EffectInstance(Effects.SPEED, 450, 0), 1f)
                    .build()));

    public static final RegistryObject<Item> FRIED_PELMENI_BOWL_WITH_SMETANA = ITEMS.register("fried_pelmeni_bowl_with_smetana",
            () -> new PelmeniBowl(new Food.Builder()
                    .hunger(14)
                    .saturation(2.6f)
                    .effect(() -> new EffectInstance(Effects.REGENERATION, 150, 0), 1f)
                    .effect(() -> new EffectInstance(Effects.STRENGTH, 300, 0), 1f)
                    .effect(() -> new EffectInstance(Effects.SATURATION, 1200, 0), 1f)
                    .build()));

    public static final RegistryObject<Item> DOUGH = ITEMS.register("dough",
            () -> new Item(new Item.Properties().group(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .hunger(3)
                            .saturation(0.6f)
                            .build())));

    public static final RegistryObject<Item> ROLLED_DOUGH = ITEMS.register("rolled_dough",
            () -> new Item(new Item.Properties().group(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .hunger(3)
                            .saturation(0.6f)
                            .build())));

    public static final RegistryObject<Item> GROUNDMEAT = ITEMS.register("groundmeat",
            () -> new Item(new Item.Properties().group(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .meat()
                            .hunger(2)
                            .saturation(0.6f)
                            .build())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
