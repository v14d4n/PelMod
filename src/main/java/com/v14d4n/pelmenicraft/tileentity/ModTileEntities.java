package com.v14d4n.pelmenicraft.tileentity;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import com.v14d4n.pelmenicraft.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {
    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, PelmeniCraft.MOD_ID);

    public static RegistryObject<BlockEntityType<MeatGrinderBlockEntity>> MEAT_GRINDER_TILE =
            BLOCK_ENTITIES.register("meat_grinder_tile", () -> BlockEntityType.Builder.of(
                    MeatGrinderBlockEntity::new,  ModBlocks.MEAT_GRINDER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
