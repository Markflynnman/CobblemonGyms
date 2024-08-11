package com.markflynnman.cobblemon_gyms.components;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class CustomButton extends Button {
    protected final ResourceLocation texture;
    protected final int textX;
    protected final int textY;
    protected final int xTexStart;
    protected final int yTexStart;
    protected final int yDiffTex;
    protected final int texWidth;
    protected final int texHeight;
    protected final int borderSize;
    protected final Component text;
    private final int textPadding;
    private final Font font;
    protected final Minecraft minecraftInstance;

    public CustomButton(ResourceLocation pTexture, int pX, int pY, int pWidth, int pHeight, int pBorderSize, int pTexWidth, int pTexHeight, int pXTexStart, int pYTexStart, int pYDiffTex, int disabledXTexStart, int disabledYTexStart, OnPress pOnPress, boolean pDisabled) {
        this(pTexture, pX, pY, pWidth, pHeight, pBorderSize, pTexWidth, pTexHeight, pXTexStart, pYTexStart, pYDiffTex, disabledXTexStart, disabledYTexStart, pOnPress, pX + pWidth/2, pY + pHeight/2, 8, CommonComponents.EMPTY, pDisabled);
    }

    public CustomButton(
            ResourceLocation pTexture,
            int pX, int pY,
            int pWidth, int pHeight,
            int pBorderSize, int pTexWidth, int pTexHeight,
            int pXTexStart, int pYTexStart, int pYDiffTex,
            int disabledXTexStart, int disabledYTexStart,
            OnPress pOnPress,
            int pTextX, int pTextY, int pTextPadding,
            Component pText,
            boolean pDisabled
    ) {
        super(pX, pY, pWidth, pHeight, CommonComponents.EMPTY, (pDisabled) ? (onPress -> {}) : pOnPress, DEFAULT_NARRATION);
        this.texture = pTexture;
        this.borderSize = pBorderSize;
        this.texWidth = pTexWidth;
        this.texHeight = pTexHeight;
        this.xTexStart = (pDisabled) ? disabledXTexStart : pXTexStart;
        this.yTexStart = (pDisabled) ? disabledYTexStart : pYTexStart;
        this.yDiffTex = (pDisabled) ? 0 : pYDiffTex;
        this.textX = pTextX;
        this.textY = pTextY;
        this.textPadding = pTextPadding;
        this.text = pText;

        this.minecraftInstance = Minecraft.getInstance();
        this.font = this.minecraftInstance.font;
    }

    public void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        int width = Math.max(this.width, (this.borderSize * 2) + 1);
        int height = Math.max(this.height, (this.borderSize * 2) + 1);
        int texWidth = Math.max(this.texWidth, (this.borderSize * 2) + 1);
        int texHeight = Math.max(this.texHeight, (this.borderSize * 2) + 1);
        int highlightY = (this.isHoveredOrFocused()) ? this.yDiffTex : 0;

        // Top Left Corner
        pGuiGraphics.blit(this.texture, this.getX(), this.getY(), this.xTexStart, this.yTexStart + highlightY, this.borderSize, this.borderSize);
        // Top Right Corner
        pGuiGraphics.blit(this.texture, this.getX() + width - this.borderSize, this.getY(), this.xTexStart + texWidth - this.borderSize, this.yTexStart + highlightY, this.borderSize, this.borderSize);
        // Bottom Left Corner
        pGuiGraphics.blit(this.texture, this.getX(), this.getY() + height - this.borderSize, this.xTexStart, this.yTexStart + highlightY + texHeight - this.borderSize, this.borderSize, this.borderSize);
        // Bottom Right Corner
        pGuiGraphics.blit(this.texture, this.getX() + width - this.borderSize, this.getY() + height - this.borderSize, this.xTexStart + texWidth - this.borderSize, this.yTexStart + highlightY + texHeight - this.borderSize, this.borderSize, this.borderSize);
        // Top Border Fill
        pGuiGraphics.blitRepeating(this.texture, this.getX() + this.borderSize, this.getY(), width - (this.borderSize * 2), this.borderSize, this.xTexStart + this.borderSize, this.yTexStart + highlightY, this.borderSize, this.borderSize);
        // Left Border Fill
        pGuiGraphics.blitRepeating(this.texture, this.getX(), this.getY() + this.borderSize, this.borderSize, height - (this.borderSize * 2), this.xTexStart, this.yTexStart + highlightY + this.borderSize, this.borderSize, this.borderSize);
        // Right Border Fill
        pGuiGraphics.blitRepeating(this.texture, this.getX() + width - this.borderSize, this.getY() + this.borderSize, this.borderSize, height - (this.borderSize * 2), texWidth - this.borderSize, this.yTexStart + highlightY + this.borderSize, this.borderSize, this.borderSize);
        // Bottom Border Fill
        pGuiGraphics.blitRepeating(this.texture, this.getX() + this.borderSize, this.getY() + height - this.borderSize, width - (this.borderSize * 2), this.borderSize, this.xTexStart + this.borderSize, this.yTexStart + highlightY + texHeight - this.borderSize, this.borderSize, this.borderSize);
        // Internal Fill
        pGuiGraphics.blitRepeating(this.texture, this.getX() + this.borderSize, this.getY() + this.borderSize, width - (this.borderSize * 2), height - (this.borderSize * 2), this.xTexStart + this.borderSize, this.yTexStart + highlightY + this.borderSize, this.borderSize, this.borderSize);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
//        int maxText =

        if (this.text != CommonComponents.EMPTY) {
            guiGraphics.drawString(this.font, this.text, this.textX + this.textPadding, this.textY + this.textPadding, ChatFormatting.WHITE.getColor(), true);
        }
    }
}
