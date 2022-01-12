package com.v14d4n.pelmenicraft.item.custom;

import com.v14d4n.pelmenicraft.item.ModCreativeModTab;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class PelmeniBowl extends Item {
    public PelmeniBowl(int maxUses, Food foodBuilder) {
        super(new Item.Properties()
                .group(ModCreativeModTab.PELMENICRAFT_TAB).setNoRepair()
                .maxStackSize(1).maxDamage(maxUses).food(foodBuilder));
    }

    public PelmeniBowl(Food foodBuilder) {
        this(2, foodBuilder);
    }

    @Override
    public boolean isFood() {
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        ItemStack container = stack.copy();
        ItemStack itemStack = super.onItemUseFinish(stack, worldIn, entityLiving);

        if (entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode) {
            return itemStack;
        } else if (!container.attemptDamageItem(1, random, null)) {
            container.getOrCreateTag().put("Damaged", StringNBT.valueOf("true"));
            return container;
        }
        return new ItemStack(Items.BOWL);
    }
}
