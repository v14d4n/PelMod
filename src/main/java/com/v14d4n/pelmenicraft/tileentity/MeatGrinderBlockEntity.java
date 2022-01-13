package com.v14d4n.pelmenicraft.tileentity;

import com.v14d4n.pelmenicraft.data.recipes.MeatGrinderRecipe;
import com.v14d4n.pelmenicraft.data.recipes.ModRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class MeatGrinderBlockEntity extends BlockEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public MeatGrinderBlockEntity(BlockPos blockPos, BlockState blockState) { // затычка
        super(ModTileEntities.MEAT_GRINDER_TILE.get(), blockPos, blockState); // FIXME: еще тут могуть быть ошибки
    }

    @Override
    public void load(CompoundTag pTag) {
        itemHandler.deserializeNBT(pTag.getCompound("inv"));
        super.load(pTag);
    }

    @Override
    public CompoundTag save(CompoundTag pTag) {
        pTag.put("inv", itemHandler.serializeNBT());
        return super.save(pTag);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {

                return level.getRecipeManager().getAllRecipesFor(ModRecipeTypes.GRINDING_RECIPE).stream()
                        .flatMap(ing -> ing.getIngredients().stream())
                        .anyMatch(ing -> ing.test(stack));
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    public boolean isItemValid(ItemStack itemStack) {
        return level.getRecipeManager().getAllRecipesFor(ModRecipeTypes.GRINDING_RECIPE).stream()
                .flatMap(ing -> ing.getIngredients().stream())
                .anyMatch(ing -> ing.test(itemStack));
    }

    public boolean isItemInSlot() {
        return this.itemHandler.getStackInSlot(0).getCount() > 0;
    }

    private boolean compareItemInSlot(ItemStack itemIn) {
        return itemIn.getItem() == itemHandler.getStackInSlot(0).getItem();
    }

    public boolean isItemHandlerFull() {
        return itemHandler.getStackInSlot(0).getCount() >= itemHandler.getStackInSlot(0).getMaxStackSize();
    }

    public ItemStack getStackInSlot() {
        return itemHandler.getStackInSlot(0);
    }

    public int addItemToSlot(ItemStack itemStack) {
        if (((compareItemInSlot(itemStack)) && !isItemHandlerFull()) || (isItemValid(itemStack) && compareItemInSlot(ItemStack.EMPTY))) {
            int mgCapacity = itemHandler.getStackInSlot(0).getMaxStackSize() - itemHandler.getStackInSlot(0).getCount();

            itemHandler.insertItem(0, itemStack.copy(), false);

            return Math.max(itemStack.getCount() - mgCapacity, 0);
        }
        return itemStack.getCount();
    }

    public ItemStack craftAndGetCraftedItem() {
        if (!level.isClientSide) {
            SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
            for (int i = 0; i < itemHandler.getSlots(); i++) {
                inv.setItem(i, itemHandler.getStackInSlot(i));
            }

            Optional<MeatGrinderRecipe> recipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.GRINDING_RECIPE, inv, level);

            if (recipe.isPresent()) {
                itemHandler.extractItem(0, 1, false);
                return recipe.get().getResultItem();
            }
        }
        return ItemStack.EMPTY;
    }
}
