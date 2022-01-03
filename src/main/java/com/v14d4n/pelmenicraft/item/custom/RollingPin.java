package com.v14d4n.pelmenicraft.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class RollingPin extends Item {
    public RollingPin(Properties properties) {
        super(properties);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack itemStack, PlayerEntity player, Entity entity) {
        entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 3);
        itemStack.damageItem(1, player, p -> p.sendBreakAnimation(p.getActiveHand()));
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
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
