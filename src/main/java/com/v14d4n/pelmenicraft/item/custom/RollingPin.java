package com.v14d4n.pelmenicraft.item.custom;

import com.v14d4n.pelmenicraft.item.ModCreativeModTab;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;
import java.util.Random;

public class RollingPin extends Item {
    public RollingPin() {
        super(new Item.Properties().tab(ModCreativeModTab.PELMENICRAFT_TAB).defaultDurability(32));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        entity.hurt(DamageSource.playerAttack(player), 3);
        stack.hurtAndBreak(2, player, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if (container.hurt(1, new Random(), null)) {
            return ItemStack.EMPTY;
        } else {
            return container;
        }
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 26;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
