package com.markflynnman.cobblemon_gyms.screens;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.components.CobblemonGymsGUITools;
import com.markflynnman.cobblemon_gyms.components.EEButton;
import com.markflynnman.cobblemon_gyms.components.ItemButton;
import com.markflynnman.cobblemon_gyms.items.GymBadges;
import com.markflynnman.cobblemon_gyms.menus.CobblemonGymsMenu;
import com.markflynnman.cobblemon_gyms.network.PacketHandler;
import com.markflynnman.cobblemon_gyms.network.SOpenScreenPacket;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class CobblemonGymsGUI extends AbstractContainerScreen<CobblemonGymsMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(CobblemonGyms.MODID, "textures/gui/cobblemongymsgui.png");
    protected final Minecraft minecraftInstance;
    private static final int bgWidth = 180;
    private static final int bgHeight = 172;
    private static final int buttonWidth = 64;
    private static final int buttonHeight = 24;
    private static final int buttonPadding = 6;

    public CobblemonGymsGUI(CobblemonGymsMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        this.minecraftInstance = Minecraft.getInstance();
    }

    public void openScreen(String screen) {
        PacketHandler.sendToServer(new SOpenScreenPacket(screen));
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;

        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-buttonWidth-buttonPadding, (height/2)-(buttonHeight*2)-(buttonPadding*3),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { openScreen("indigo_league_gui"); },
                new ItemStack(GymBadges.BOULDER_BADGE.get()),
                (width/2)-buttonWidth-buttonPadding, (height/2)-(buttonHeight*2)-(buttonPadding*3), 4,
                Component.literal("Indigo"),
                false
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+buttonPadding, (height/2)-(buttonHeight*2)-(buttonPadding*3),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { openScreen("johto_league_gui"); },
                new ItemStack(GymBadges.ZEPHYR_BADGE.get()),
                (width/2)+buttonPadding, (height/2)-(buttonHeight*2)-(buttonPadding*3), 4,
                Component.literal("Johto"),
                false
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-buttonWidth-buttonPadding, (height/2)-buttonHeight-buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { openScreen("hoenn_league_gui"); },
                new ItemStack(GymBadges.STONE_BADGE.get()),
                (width/2)-buttonWidth-buttonPadding, (height/2)-buttonHeight-buttonPadding, 4,
                Component.literal("Hoenn"),
                false
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+buttonPadding, (height/2)-buttonHeight-buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { openScreen("sinnoh_league_gui"); },
                new ItemStack(GymBadges.COAL_BADGE.get()),
                (width/2)+buttonPadding, (height/2)-buttonHeight-buttonPadding, 4,
                Component.literal("Sinnoh"),
                false
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-buttonWidth-buttonPadding, (height/2)+buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { openScreen("unova_league_gui"); },
                new ItemStack(GymBadges.TRIO_BADGE.get()),
                (width/2)-buttonWidth-buttonPadding, (height/2)+buttonPadding, 4,
                Component.literal("Unova"),
                false
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+buttonPadding, (height/2)+buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { openScreen("kalos_league_gui"); },
                new ItemStack(GymBadges.BUG_BADGE.get()),
                (width/2)+buttonPadding, (height/2)+buttonPadding, 4,
                Component.literal("Kalos"),
                false
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-buttonWidth-buttonPadding, (height/2)+buttonHeight+(buttonPadding*3),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { openScreen("galar_league_gui"); },
                new ItemStack(GymBadges.SAS_GRASS_BADGE.get()),
                (width/2)-buttonWidth-buttonPadding, (height/2)+buttonHeight+(buttonPadding*3), 4,
                Component.literal("Galar"),
                false
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+buttonPadding, (height/2)+buttonHeight+(buttonPadding*3),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { openScreen("paldea_league_gui"); },
                new ItemStack(GymBadges.SV_BUG_BADGE.get()),
                (width/2)+buttonPadding, (height/2)+buttonHeight+(buttonPadding*3), 4,
                Component.literal("Paldea"),
                false
        ));

        this.addRenderableWidget(new EEButton(
                TEXTURE,
                (width/2)+1, ((height-bgHeight)/2)+8,
                3, 3,
                4, 4,
                12, 0,
                4, 4,
                onPress -> { openScreen(""); },
                false
        ));
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        CobblemonGymsGUITools guiTools = new CobblemonGymsGUITools(guiGraphics);
        guiTools.renderBg(
                TEXTURE,
                (width-bgWidth)/2, (height-bgHeight)/2,
                bgWidth, bgHeight,
                4, 12,12,
                0,0
        );
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);

        FormattedCharSequence title = Component.translatable("gui.cobblemon_gyms.gym").getVisualOrderText();
        guiGraphics.drawString(this.font, title, width/2 - this.font.width(title)/2, ((height-bgHeight)/2)+5, 4210752, false);
    }
}
