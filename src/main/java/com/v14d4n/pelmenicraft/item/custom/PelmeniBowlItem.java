package com.v14d4n.pelmenicraft.item.custom;

import com.v14d4n.pelmenicraft.item.ModItemGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class PelmeniBowlItem extends Item {
    public PelmeniBowlItem(int maxUses, Food foodBuilder) {
        super(new Item.Properties()
                .group(ModItemGroup.PELMENICRAFT_GROUP)
                .maxStackSize(1).maxDamage(maxUses).food(foodBuilder));
    }

    public PelmeniBowlItem(Food foodBuilder) {
        this(2, foodBuilder);
    }

    @Override
    public boolean isFood() {
        return true;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        ItemStack container = stack.copy();
        ItemStack itemStack = super.onItemUseFinish(stack, worldIn, entityLiving);

        if (container.attemptDamageItem(1, random, null)) {
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemStack : new ItemStack(Items.BOWL);
        } else {
            return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemStack : container;
        }
    }
}
