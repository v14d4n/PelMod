package com.v14d4n.pelmenicraft.tileentity;

import com.v14d4n.pelmenicraft.data.recipes.MeatGrinderRecipe;
import com.v14d4n.pelmenicraft.data.recipes.ModRecipeTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MeatGrinderTile extends TileEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public MeatGrinderTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public MeatGrinderTile() {
        this(ModTileEntities.MEAT_GRINDER_TILE.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.write(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return world.getRecipeManager().getRecipesForType(ModRecipeTypes.GRINDING_RECIPE).stream()
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
        return world.getRecipeManager().getRecipesForType(ModRecipeTypes.GRINDING_RECIPE).stream()
                .flatMap(ing -> ing.getIngredients().stream())
                .anyMatch(ing -> ing.test(itemStack));
    }

    public boolean isItemInSlot() {
        return this.itemHandler.getStackInSlot(0).getCount() > 0;
    }

    private boolean compareItemInSlot(ItemStack itemIn) {
        return itemIn.getItem() == itemHandler.getStackInSlot(0).getItem();
    }

    public boolean addItemToSlot(ItemStack itemStack) {
        if ((compareItemInSlot(itemStack)) && this.itemHandler.getStackInSlot(0).getCount() < itemHandler.getStackInSlot(0).getMaxStackSize()
                || (isItemValid(itemStack) && compareItemInSlot(ItemStack.EMPTY))) {
            this.itemHandler.insertItem(0, itemStack, false);
            return true;
        }
        return false;
    }

    public ItemStack craftAndGetCraftedItem() {
        if (!world.isRemote) {
            Inventory inv = new Inventory(itemHandler.getSlots());
            for (int i = 0; i < itemHandler.getSlots(); i++) {
                inv.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
            }

            Optional<MeatGrinderRecipe> recipe = world.getRecipeManager().getRecipe(ModRecipeTypes.GRINDING_RECIPE, inv, world);

            if (recipe.isPresent()) {
                itemHandler.extractItem(0, 1, false);
                return recipe.get().getRecipeOutput();
            }
        }
        return ItemStack.EMPTY;
    }
}
