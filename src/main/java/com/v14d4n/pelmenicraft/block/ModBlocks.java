package com.v14d4n.pelmenicraft.block;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import com.v14d4n.pelmenicraft.block.custom.MeatGrinderBlock;
import com.v14d4n.pelmenicraft.item.ModItemGroup;
import com.v14d4n.pelmenicraft.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PelmeniCraft.MOD_ID);

    public static final RegistryObject<Block> PELMENI_BLOCK = registerBlock("pelmeni_block",
            () -> new Block(AbstractBlock.Properties.of(Material.SNOW).sound(SoundType.SNOW)
                    .strength(1.5f).harvestLevel(0).harvestTool(ToolType.SHOVEL)));

    public static final RegistryObject<Block> FRIED_PELMENI_BLOCK = registerBlock("fried_pelmeni_block",
            () -> new Block(AbstractBlock.Properties.of(Material.SNOW).sound(SoundType.SNOW)
                    .strength(1.5f).harvestLevel(0).harvestTool(ToolType.SHOVEL)));

    public static final RegistryObject<Block> MEAT_GRINDER = registerBlock("meat_grinder",
            () -> new MeatGrinderBlock(AbstractBlock.Properties.of(Material.METAL)
                    .sound(SoundType.METAL)
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(0)
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()));

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ModItemGroup.PELMENICRAFT_GROUP)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
