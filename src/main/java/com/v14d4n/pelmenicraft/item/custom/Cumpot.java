package com.v14d4n.pelmenicraft.item.custom;
import com.v14d4n.pelmenicraft.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class Cumpot extends Item {
    public Cumpot(Properties properties) {
        super(properties);

    }

    @Override
    public UseAction getUseAnimation(ItemStack itemStack) {
        return UseAction.DRINK;
    }


    //TODO:
    // сделать возможным переносить сразу 16 бутылок и реализовать выдачу пустой бутылки после использования
    // в том числе обработать выдачу бутылок при переполненном инвентаре

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, World world, LivingEntity livingEntity) {
        ItemStack itemstack = super.finishUsingItem(itemStack, world, livingEntity);
        return livingEntity instanceof PlayerEntity && ((PlayerEntity)livingEntity).abilities.instabuild ? itemstack : new ItemStack(Items.GLASS_BOTTLE);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return super.getContainerItem(itemStack);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return super.hasContainerItem(stack);
    }
}
