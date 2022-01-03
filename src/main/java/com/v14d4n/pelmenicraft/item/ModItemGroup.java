package com.v14d4n.pelmenicraft.item;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup PELMENICRAFT_GROUP = new ItemGroup("pelmeniCraftTab") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.PELMEN.get());
        }
    };
}
