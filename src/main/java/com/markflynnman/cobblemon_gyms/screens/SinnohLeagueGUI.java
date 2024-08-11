package com.markflynnman.cobblemon_gyms.screens;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.badgeCollection.ClientBadgeCollectionData;
import com.markflynnman.cobblemon_gyms.badgeCollection.PlayerBadgeCollection;
import com.markflynnman.cobblemon_gyms.components.CobblemonGymsGUITools;
import com.markflynnman.cobblemon_gyms.components.ItemButton;
import com.markflynnman.cobblemon_gyms.items.GymBadges;
import com.markflynnman.cobblemon_gyms.menus.SinnohLeagueMenu;
import com.markflynnman.cobblemon_gyms.network.PacketHandler;
import com.markflynnman.cobblemon_gyms.network.SGymSelectionPacket;
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

public class SinnohLeagueGUI extends AbstractContainerScreen<SinnohLeagueMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(CobblemonGyms.MODID, "textures/gui/cobblemongymsgui.png");
    protected final Minecraft minecraftInstance;
    private static final int bgWidth = 180;
    private static final int bgHeight = 108;
    private static final int buttonWidth = 24;
    private static final int buttonHeight = 24;
    private boolean back = true;

    public SinnohLeagueGUI(SinnohLeagueMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        this.minecraftInstance = Minecraft.getInstance();
    }

    public void closeScreen() {
        this.back = false;
        if (this.minecraftInstance != null) {
            this.minecraftInstance.setScreen(null);
        }
        this.onClose();
    }

    public void gymSelect(String pCommand) {
        PacketHandler.sendToServer(new SGymSelectionPacket(pCommand));
        closeScreen();
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
                (width/2)-66, (height/2)-30,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Sinnoh_Coal"); },
                new ItemStack(GymBadges.COAL_BADGE.get()),
                false
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-30, (height/2)-30,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Sinnoh_Forest"); },
                new ItemStack(GymBadges.FOREST_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Sinnoh_Forest")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+6, (height/2)-30,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Sinnoh_Cobble"); },
                new ItemStack(GymBadges.COBBLE_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Sinnoh_Cobble")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+42, (height/2)-30,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Sinnoh_Fen"); },
                new ItemStack(GymBadges.FEN_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Sinnoh_Fen")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-66, (height/2)+6,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Sinnoh_Relic"); },
                new ItemStack(GymBadges.RELIC_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Sinnoh_Relic")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-30, (height/2)+6,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Sinnoh_Mine"); },
                new ItemStack(GymBadges.MINE_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Sinnoh_Mine")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+6, (height/2)+6,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Sinnoh_Icicle"); },
                new ItemStack(GymBadges.ICICLE_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Sinnoh_Icicle")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+42, (height/2)+6,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Sinnoh_Beacon"); },
                new ItemStack(GymBadges.BEACON_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Sinnoh_Beacon")-1] == 0
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

        FormattedCharSequence title = Component.translatable("gui.cobblemongyms.sinnoh").getVisualOrderText();
        guiGraphics.drawString(this.font, title, width/2 - this.font.width(title)/2, ((height-bgHeight)/2)+5, 4210752, false);
    }

    @Override
    public void onClose() {
        if (this.back) {
            openScreen("");
        }
        else {
            super.onClose();
        }
    }
}
