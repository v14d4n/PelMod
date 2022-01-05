package com.v14d4n.pelmenicraft.container;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    public static DeferredRegister<ContainerType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, PelmeniCraft.MOD_ID);

    public static final RegistryObject<ContainerType<MeatGrinderContainer>> MEAT_GRINDER_CONTAINER =
            CONTAINERS.register("meat_grinder_container",
                    () -> IForgeContainerType.create(((windowId, inv, data) -> {
                        BlockPos pos = data.readBlockPos();
                        World world = inv.player.getEntityWorld();
                        return new MeatGrinderContainer(windowId, world, pos, inv, inv.player);
                    })));

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}
