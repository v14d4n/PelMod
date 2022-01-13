package com.v14d4n.pelmenicraft.block;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import com.v14d4n.pelmenicraft.block.custom.MeatGrinderBlock;
import com.v14d4n.pelmenicraft.item.ModCreativeModTab;
import com.v14d4n.pelmenicraft.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PelmeniCraft.MOD_ID);

    public static final RegistryObject<Block> PELMENI_BLOCK = registerBlock("pelmeni_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.SNOW).sound(SoundType.SNOW)
                    .strength(1.5f,  1.5f)));

    public static final RegistryObject<Block> FRIED_PELMENI_BLOCK = registerBlock("fried_pelmeni_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.SNOW).sound(SoundType.SNOW)
                    .strength(1.5f, 1.5f)));

    public static final RegistryObject<Block> MEAT_GRINDER = registerBlock("meat_grinder",
            () -> new MeatGrinderBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL) // FIXME: возможно надо будет что-то тут поменять
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
                new Item.Properties().tab(ModCreativeModTab.PELMENICRAFT_TAB)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
