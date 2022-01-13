package com.v14d4n.pelmenicraft.item.custom;

import com.v14d4n.pelmenicraft.item.ModCreativeModTab;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Random;

public class PelmeniBowl extends Item {
    public PelmeniBowl(int maxUses, FoodProperties foodBuilder) {
        super(new Item.Properties()
                .tab(ModCreativeModTab.PELMENICRAFT_TAB).setNoRepair()
                .stacksTo(1).durability(maxUses).food(foodBuilder));
    }

    public PelmeniBowl(FoodProperties foodBuilder) {
        this(2, foodBuilder);
    }

    @Override
    public boolean isEdible() {
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        ItemStack container = pStack.copy();
        ItemStack itemStack = super.finishUsingItem(pStack, pLevel, pLivingEntity);

        if (pLivingEntity instanceof Player && ((Player)pLivingEntity).getAbilities().instabuild) {
            return itemStack;
        } else if (!container.hurt(1, new Random(), null)) {
            container.getOrCreateTag().putBoolean("Damaged", true);
            return container;
        }
        return new ItemStack(Items.BOWL);
    }
}
