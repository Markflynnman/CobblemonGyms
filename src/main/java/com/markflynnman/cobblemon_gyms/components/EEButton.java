package com.markflynnman.cobblemon_gyms.components;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class EEButton extends Button {
    protected final ResourceLocation texture;
    protected final int xTexStart;
    protected final int yTexStart;
    protected final int highlightXTexStart;
    protected final int highlightYTexStart;
    protected final Holder<SoundEvent> sound;

    public EEButton(
            ResourceLocation pTexture,
            int pX, int pY,
            int pWidth, int pHeight,
            int pXTexStart, int pYTexStart,
            int pHighlightXTexStart, int pHighlightYTexStart,
            int pDisabledXTexStart, int pDisabledYTexStart,
            OnPress pOnPress,
            boolean pDisabled
    ) {
        super(pX, pY, pWidth, pHeight, CommonComponents.EMPTY, (pDisabled) ? (onPress -> {}) : pOnPress, DEFAULT_NARRATION);
        this.texture = pTexture;
        this.xTexStart = (pDisabled) ? pDisabledXTexStart : pXTexStart;
        this.yTexStart = (pDisabled) ? pDisabledYTexStart : pYTexStart;
        this.highlightXTexStart = (pDisabled) ? pXTexStart : pHighlightXTexStart;
        this.highlightYTexStart = (pDisabled) ? pYTexStart : pHighlightYTexStart;
        this.sound = (pDisabled) ? (Holder<SoundEvent>) SoundEvents.EMPTY : SoundEvents.AMBIENT_CAVE;
    }

    public void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        int xTexStart = (this.isHoveredOrFocused()) ? this.highlightXTexStart : this.xTexStart;
        int yTexStart = (this.isHoveredOrFocused()) ? this.highlightYTexStart : this.yTexStart;

        pGuiGraphics.blitRepeating(this.texture, this.getX(), this.getY(), this.width, this.height, xTexStart, yTexStart, 1, 1);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
    }

    @Override
    public void playDownSound(SoundManager pHandler) {
        pHandler.play(SimpleSoundInstance.forUI(this.sound, 1.0F));
    }
}
