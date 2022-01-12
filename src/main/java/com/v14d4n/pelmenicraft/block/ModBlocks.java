package com.v14d4n.pelmenicraft.block;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import com.v14d4n.pelmenicraft.block.custom.MeatGrinderBlock;
import com.v14d4n.pelmenicraft.item.ModCreativeModTab;
import com.v14d4n.pelmenicraft.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PelmeniCraft.MOD_ID);

    public static final RegistryObject<Block> PELMENI_BLOCK = registerBlock("pelmeni_block",
            () -> new Block(AbstractBlock.Properties.create(Material.SNOW_BLOCK).sound(SoundType.SNOW)
                    .hardnessAndResistance(1.5f).harvestLevel(0).harvestTool(ToolType.SHOVEL)));

    public static final RegistryObject<Block> FRIED_PELMENI_BLOCK = registerBlock("fried_pelmeni_block",
            () -> new Block(AbstractBlock.Properties.create(Material.SNOW_BLOCK).sound(SoundType.SNOW)
                    .hardnessAndResistance(1.5f).harvestLevel(0).harvestTool(ToolType.SHOVEL)));

    public static final RegistryObject<Block> MEAT_GRINDER = registerBlock("meat_grinder",
            () -> new MeatGrinderBlock(AbstractBlock.Properties.create(Material.IRON).sound(SoundType.METAL)
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(0)
                    .hardnessAndResistance(3f)
                    .setRequiresTool()
                    .notSolid()));

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
