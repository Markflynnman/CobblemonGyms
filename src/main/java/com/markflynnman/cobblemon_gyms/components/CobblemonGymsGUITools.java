package com.markflynnman.cobblemon_gyms.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Divisor;
import it.unimi.dsi.fastutil.ints.IntIterator;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;


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
        RenderSystem.setShaderTexture(0, pTexture);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        Matrix4f matrix4f = guiGraphics.pose().last().pose();

        BufferBuilder buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

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
        // FIX

        // Top Border Fill
        blitRepeating(guiGraphics, pTexture, pX + pBorderSize, pY, pWidth - (pBorderSize * 2), pBorderSize, pXTexStart + pBorderSize, pYTexStart, pBorderSize, pBorderSize);
//        guiGraphics.blitRepeating(pTexture, pX + pBorderSize, pY, pWidth - (pBorderSize * 2), pBorderSize, pXTexStart + pBorderSize, pYTexStart, pBorderSize, pBorderSize);
        // Left Border Fill
        blitRepeating(guiGraphics, pTexture, pX, pY + pBorderSize, pBorderSize, pHeight - (pBorderSize * 2), pXTexStart, pYTexStart + pBorderSize, pBorderSize, pBorderSize);
//        guiGraphics.blitRepeating(pTexture, pX, pY + pBorderSize, pBorderSize, pHeight - (pBorderSize * 2), pXTexStart, pYTexStart + pBorderSize, pBorderSize, pBorderSize);
        // Right Border Fill
        blitRepeating(guiGraphics, pTexture, pX + width - pBorderSize, pY + pBorderSize, pBorderSize, pHeight - (pBorderSize * 2), pTexWidth - pBorderSize, pYTexStart + pBorderSize, pBorderSize, pBorderSize);
//        guiGraphics.blitRepeating(pTexture, pX + width - pBorderSize, pY + pBorderSize, pBorderSize, pHeight - (pBorderSize * 2), pTexWidth - pBorderSize, pYTexStart + pBorderSize, pBorderSize, pBorderSize);
        // Bottom Border Fill
        blitRepeating(guiGraphics, pTexture, pX + pBorderSize, pY + height - pBorderSize, pWidth - (pBorderSize * 2), pBorderSize, pXTexStart + pBorderSize, pYTexStart + pTexHeight - pBorderSize, pBorderSize, pBorderSize);
//        guiGraphics.blitRepeating(pTexture, pX + pBorderSize, pY + height - pBorderSize, pWidth - (pBorderSize * 2), pBorderSize, pXTexStart + pBorderSize, pYTexStart + pTexHeight - pBorderSize, pBorderSize, pBorderSize);
        // Internal Fill
        blitRepeating(guiGraphics, pTexture, pX + pBorderSize, pY + pBorderSize, pWidth - (pBorderSize * 2), pHeight - (pBorderSize * 2), pXTexStart + pBorderSize, pYTexStart + pBorderSize, pBorderSize, pBorderSize);
//        guiGraphics.blitRepeating(pTexture, pX + pBorderSize, pY + pBorderSize, pWidth - (pBorderSize * 2), pHeight - (pBorderSize * 2), pXTexStart + pBorderSize, pYTexStart + pBorderSize, pBorderSize, pBorderSize);
    }

    public void blitRepeating(
            GuiGraphics guiGraphics,
            ResourceLocation pTexture,
            int pX, int pY,
            int pWidth, int pHeight,
            int pUOffset, int pVOffset,
            int pSourceWidth, int pSourceHeight
    ) {
        int i = pX;

        int j;
        for (IntIterator intiterator = slices(pWidth, pSourceWidth); intiterator.hasNext(); i += j) {
            j = intiterator.nextInt();
            int k = (pSourceWidth - j) / 2;
            int l = pY;

            int i1;
            for (IntIterator intiterator1 = slices(pHeight, pSourceHeight); intiterator1.hasNext(); l += i1) {
                i1 = intiterator1.nextInt();
                int j1 = (pSourceHeight - i1) / 2;
                guiGraphics.blit(pTexture, i, l, pUOffset + k, pVOffset + j1, j, i1);
            }
        }
    }

    /**
     * Returns an iterator for dividing a value into slices of a specified size.
     * <p>
     *
     * @param pTarget the value to be divided.
     * @param pTotal  the size of each slice.
     *
     * @return An iterator for iterating over the slices.
     */
    private static IntIterator slices(int pTarget, int pTotal) {
        int i = Mth.positiveCeilDiv(pTarget, pTotal);
        return new Divisor(pTarget, i);
    }
}
