package com.v14d4n.pelmenicraft.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModTab {

    public static final CreativeModeTab PELMENICRAFT_TAB = new CreativeModeTab("pelmeniCraftTab") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.PELMEN.get());
        }
    };
}
