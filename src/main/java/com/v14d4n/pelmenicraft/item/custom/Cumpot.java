package com.v14d4n.pelmenicraft.item.custom;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class Cumpot extends Item {
    public Cumpot(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
        return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(Items.GLASS_BOTTLE);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    //TODO:
    // сделать возможным переносить сразу 16 бутылок и реализовать выдачу пустой бутылки после использования
    // в том числе обработать выдачу бутылок при переполненном инвентаре
}
