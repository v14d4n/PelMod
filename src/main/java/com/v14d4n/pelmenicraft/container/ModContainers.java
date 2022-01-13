package com.v14d4n.pelmenicraft.container;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {
    public static DeferredRegister<MenuType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, PelmeniCraft.MOD_ID);

    public static final RegistryObject<MenuType<MeatGrinderContainer>> MEAT_GRINDER_CONTAINER =
            CONTAINERS.register("meat_grinder_container",
                    () -> IForgeMenuType.create(((windowId, inv, data) -> {
                        BlockPos pos = data.readBlockPos();
                        Level level = inv.player.level;
                        return new MeatGrinderContainer(windowId, level, pos, inv, inv.player);
                    })));

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}
