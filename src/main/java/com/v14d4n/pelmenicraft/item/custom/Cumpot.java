package com.v14d4n.pelmenicraft.item.custom;
import com.v14d4n.pelmenicraft.item.ModCreativeModTab;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class Cumpot extends Item {
    public Cumpot(FoodProperties foodBuilder) {
        super(new Item.Properties().tab(ModCreativeModTab.PELMENICRAFT_TAB)
                .stacksTo(1)
                .food(foodBuilder));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        ItemStack itemstack = super.finishUsingItem(pStack, pLevel, pLivingEntity);
        return pLivingEntity instanceof Player && ((Player)pLivingEntity).getAbilities().instabuild ? itemstack : new ItemStack(Items.GLASS_BOTTLE);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    //TODO:
    // сделать возможным переносить сразу 16 бутылок
    // в том числе обработать выдачу бутылок при переполненном инвентаре
}
