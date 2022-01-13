package com.v14d4n.pelmenicraft;

import com.mojang.blaze3d.platform.ScreenManager;
import com.v14d4n.pelmenicraft.block.ModBlocks;
import com.v14d4n.pelmenicraft.container.ModContainers;
import com.v14d4n.pelmenicraft.data.recipes.ModRecipeTypes;
import com.v14d4n.pelmenicraft.item.ModItems;
import com.v14d4n.pelmenicraft.screen.MeatGrinderScreen;
import com.v14d4n.pelmenicraft.tileentity.ModTileEntities;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PelmeniCraft.MOD_ID)
public class PelmeniCraft {
    public static final String MOD_ID = "pelmenicraft";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public PelmeniCraft() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModTileEntities.register(eventBus);
        ModContainers.register(eventBus);
        ModRecipeTypes.register(eventBus);

        eventBus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MEAT_GRINDER.get(), RenderType.cutout());

            MenuScreens.register(ModContainers.MEAT_GRINDER_CONTAINER.get(), MeatGrinderScreen::new);
        });
    }
}
