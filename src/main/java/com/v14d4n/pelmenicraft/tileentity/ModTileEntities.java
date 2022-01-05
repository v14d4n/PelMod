package com.v14d4n.pelmenicraft.tileentity;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import com.v14d4n.pelmenicraft.block.ModBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, PelmeniCraft.MOD_ID);

    public static RegistryObject<TileEntityType<MeatGrinderTile>> MEAT_GRINDER_TILE =
            TILE_ENTITIES.register("meat_grinder_tile", () -> TileEntityType.Builder.create(
                    MeatGrinderTile::new, ModBlocks.MEAT_GRINDER.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
