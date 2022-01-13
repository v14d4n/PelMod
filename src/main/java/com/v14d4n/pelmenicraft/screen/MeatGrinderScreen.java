package com.v14d4n.pelmenicraft.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.v14d4n.pelmenicraft.PelmeniCraft;
import com.v14d4n.pelmenicraft.container.MeatGrinderContainer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MeatGrinderScreen extends AbstractContainerScreen<MeatGrinderContainer> {
    private final ResourceLocation GUI = new ResourceLocation(PelmeniCraft.MOD_ID,
            "textures/gui/meat_grinder_gui.png");

    public MeatGrinderScreen(MeatGrinderContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderLabels(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pX, int pY) {
        RenderSystem.setShaderFogColor(1f, 1f, 1f, 1f); // FIXME: возможны не те цвета (clearcolor)
        this.minecraft.getTextureManager().bindForSetup(GUI);
        int i = this.getGuiLeft();
        int j = this.getGuiTop();
        this.blit(pPoseStack, i, j, 0, 0, this.getXSize(), this.getYSize());
    }
}
