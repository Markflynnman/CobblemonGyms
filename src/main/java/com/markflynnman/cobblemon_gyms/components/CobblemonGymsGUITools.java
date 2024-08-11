package com.markflynnman.cobblemon_gyms.components;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class CobblemonGymsGUITools {
    protected final GuiGraphics guiGraphics;
    public CobblemonGymsGUITools(GuiGraphics guiGraphics) {
        this.guiGraphics = guiGraphics;
    }
    public void renderBg(
            ResourceLocation pTexture,
            int pX, int pY,
            int pWidth, int pHeight,
            int pBorderSize, int pTexWidth, int pTexHeight,
            int pXTexStart, int pYTexStart
    ) {
        int width = Math.max(pWidth, (pBorderSize * 2) + 1);
        int height = Math.max(pHeight, (pBorderSize * 2) + 1);
        int texWidth = Math.max(pTexWidth, (pBorderSize * 2) + 1);
        int texHeight = Math.max(pTexHeight, (pBorderSize * 2) + 1);

        // Top Left Corner
        guiGraphics.blit(pTexture, pX, pY, pXTexStart, pYTexStart, pBorderSize, pBorderSize);
        // Top Right Corner
        guiGraphics.blit(pTexture, pX + width - pBorderSize, pY, pXTexStart + texWidth - pBorderSize, pYTexStart, pBorderSize, pBorderSize);
        // Bottom Left Corner
        guiGraphics.blit(pTexture, pX, pY + height - pBorderSize, pXTexStart, pYTexStart + texHeight - pBorderSize, pBorderSize, pBorderSize);
        // Bottom Right Corner
        guiGraphics.blit(pTexture, pX + width - pBorderSize, pY + height - pBorderSize, pXTexStart + texWidth - pBorderSize, pYTexStart + texHeight - pBorderSize, pBorderSize, pBorderSize);
        // Top Border Fill
        guiGraphics.blitRepeating(pTexture, pX + pBorderSize, pY, pWidth - (pBorderSize * 2), pBorderSize, pXTexStart + pBorderSize, pYTexStart, pBorderSize, pBorderSize);
        // Left Border Fill
        guiGraphics.blitRepeating(pTexture, pX, pY + pBorderSize, pBorderSize, pHeight - (pBorderSize * 2), pXTexStart, pYTexStart + pBorderSize, pBorderSize, pBorderSize);
        // Right Border Fill
        guiGraphics.blitRepeating(pTexture, pX + width - pBorderSize, pY + pBorderSize, pBorderSize, pHeight - (pBorderSize * 2), pTexWidth - pBorderSize, pYTexStart + pBorderSize, pBorderSize, pBorderSize);
        // Bottom Border Fill
        guiGraphics.blitRepeating(pTexture, pX + pBorderSize, pY + height - pBorderSize, pWidth - (pBorderSize * 2), pBorderSize, pXTexStart + pBorderSize, pYTexStart + pTexHeight - pBorderSize, pBorderSize, pBorderSize);
        // Internal Fill
        guiGraphics.blitRepeating(pTexture, pX + pBorderSize, pY + pBorderSize, pWidth - (pBorderSize * 2), pHeight - (pBorderSize * 2), pXTexStart + pBorderSize, pYTexStart + pBorderSize, pBorderSize, pBorderSize);
    }
}
