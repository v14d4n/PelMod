package com.v14d4n.pelmenicraft.item.custom;

import net.minecraft.client.renderer.texture.ITickable;
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
        entity.hurt(DamageSource.playerAttack(player), 3);
        itemStack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
        return super.onLeftClickEntity(itemStack, player, entity);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if (container.hurt(1, random, null)) {
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
