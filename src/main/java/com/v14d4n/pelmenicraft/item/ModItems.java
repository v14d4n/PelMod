package com.v14d4n.pelmenicraft.item;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import com.v14d4n.pelmenicraft.item.custom.RollingPin;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.SoupItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PelmeniCraft.MOD_ID);

    public static final RegistryObject<Item> ROLLING_PIN = ITEMS.register("rolling_pin",
            () -> new RollingPin(new Item.Properties().tab(ModItemGroup.PELMENICRAFT_GROUP).durability(8)));

    public static final RegistryObject<Item> PELMEN = ITEMS.register("pelmen",
            () -> new Item(new Item.Properties()
                    .tab(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .fast().meat()
                            .nutrition(3)
                            .saturationMod(0.6f)
                            .build())));

    public static final RegistryObject<Item> FRIED_PELMEN = ITEMS.register("fried_pelmen",
            () -> new Item(new Item.Properties()
                    .tab(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .fast().meat()
                            .nutrition(4)
                            .saturationMod(0.8f)
                            .effect(() -> new EffectInstance(
                                    Effects.REGENERATION, 60, 0), 0.8f)
                            .build())));

    public static final RegistryObject<Item> MAYONNAISE = ITEMS.register("mayonnaise",
            () -> new Item(new Item.Properties().tab(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .fast()
                            .nutrition(1)
                            .saturationMod(0.2f)
                            .build())));

    public static final RegistryObject<Item> PELMENI_BOWL = ITEMS.register("pelmeni_bowl",
            () -> new SoupItem(new Item.Properties().stacksTo(1)
                    .tab(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .nutrition(14)
                            .saturationMod(2f)
                            .effect(() -> new EffectInstance(Effects.REGENERATION, 100, 0), 1f)
                            .effect(() -> new EffectInstance(Effects.DIG_SPEED, 600, 0), 1f)
                            .build())));

    public static final RegistryObject<Item> FRIED_PELMENI_BOWL = ITEMS.register("fried_pelmeni_bowl",
            () -> new SoupItem(new Item.Properties().stacksTo(1)
                    .tab(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .nutrition(16)
                            .saturationMod(2.6f)
                            .effect(() -> new EffectInstance(Effects.REGENERATION, 150, 0), 1f)
                            .effect(() -> new EffectInstance(Effects.DAMAGE_BOOST, 600, 0), 1f)
                            .build())));

    public static final RegistryObject<Item> DOUGH = ITEMS.register("dough",
            () -> new Item(new Item.Properties().tab(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .nutrition(3)
                            .saturationMod(0.6f)
                            .build())));

    public static final RegistryObject<Item> ROLLED_DOUGH = ITEMS.register("rolled_dough",
            () -> new Item(new Item.Properties().tab(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .nutrition(3)
                            .saturationMod(0.6f)
                            .build())));

    public static final RegistryObject<Item> GROUNDMEAT = ITEMS.register("groundmeat",
            () -> new Item(new Item.Properties().tab(ModItemGroup.PELMENICRAFT_GROUP)
                    .food(new Food.Builder()
                            .meat()
                            .nutrition(2)
                            .saturationMod(0.6f)
                            .effect(() -> new EffectInstance(
                                    Effects.HUNGER, 600, 0), 0.3f)
                            .build())));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}