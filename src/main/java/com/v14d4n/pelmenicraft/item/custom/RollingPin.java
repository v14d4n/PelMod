package com.v14d4n.pelmenicraft.item.custom;

import com.v14d4n.pelmenicraft.item.ModItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.DamageSource;

import javax.annotation.Nullable;

public class RollingPin extends Item {
    public RollingPin() {
        super(new Item.Properties().group(ModItemGroup.PELMENICRAFT_GROUP).defaultMaxDamage(32));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack itemStack, PlayerEntity player, Entity entity) {
        entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 3);
        itemStack.damageItem(2, player, p -> p.sendBreakAnimation(p.getActiveHand()));
        return super.onLeftClickEntity(itemStack, player, entity);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if (container.attemptDamageItem(1, random, null)) {
            return ItemStack.EMPTY;
        } else {
            return container;
        }
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable IRecipeType<?> recipeType) {
        return 26;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
