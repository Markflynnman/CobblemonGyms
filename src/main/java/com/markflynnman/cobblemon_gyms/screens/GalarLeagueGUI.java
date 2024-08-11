package com.markflynnman.cobblemon_gyms.screens;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.badgeCollection.ClientBadgeCollectionData;
import com.markflynnman.cobblemon_gyms.badgeCollection.PlayerBadgeCollection;
import com.markflynnman.cobblemon_gyms.components.CobblemonGymsGUITools;
import com.markflynnman.cobblemon_gyms.components.ItemButton;
import com.markflynnman.cobblemon_gyms.items.GymBadges;
import com.markflynnman.cobblemon_gyms.menus.GalarLeagueMenu;
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

public class GalarLeagueGUI extends AbstractContainerScreen<GalarLeagueMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(CobblemonGyms.MODID, "textures/gui/cobblemongymsgui.png");
    protected final Minecraft minecraftInstance;
    private static final int bgWidth = 208;
    private static final int bgHeight = 108;
    private static final int buttonWidth = 24;
    private static final int buttonHeight = 24;
    private static final int buttonPadding = 12;
    private boolean back = true;

    public GalarLeagueGUI(GalarLeagueMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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

        // Top
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2)-(buttonPadding*2)-(buttonWidth*2), (height/2)-buttonHeight-(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Galar_Grass"); },
                new ItemStack(GymBadges.SAS_GRASS_BADGE.get()),
                false
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2)-buttonPadding-buttonWidth, (height/2)-buttonHeight-(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Galar_Water"); },
                new ItemStack(GymBadges.SAS_WATER_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Galar_Water")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2), (height/2)-buttonHeight-(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Galar_Fire"); },
                new ItemStack(GymBadges.SAS_FIRE_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Galar_Fire")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+(buttonWidth/2)+buttonPadding, (height/2)-buttonHeight-(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Galar_Fighting"); },
                new ItemStack(GymBadges.SAS_FIGHTING_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Galar_Fighting")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+(buttonWidth/2)+(buttonPadding*2)+buttonWidth, (height/2)-buttonHeight-(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Galar_Ghost"); },
                new ItemStack(GymBadges.SAS_GHOST_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Galar_Ghost")-1] == 0
        ));

        // Bottom
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2)-(buttonPadding*2)-(buttonWidth*2), (height/2)+(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Galar_Fairy"); },
                new ItemStack(GymBadges.SAS_FAIRY_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Galar_Fairy")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2)-buttonPadding-buttonWidth, (height/2)+(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Galar_Rock"); },
                new ItemStack(GymBadges.SAS_ROCK_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Galar_Rock")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2), (height/2)+(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Galar_Ice"); },
                new ItemStack(GymBadges.SAS_ICE_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Galar_Ice")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+(buttonWidth/2)+buttonPadding, (height/2)+(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Galar_Dark"); },
                new ItemStack(GymBadges.SAS_DARK_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Galar_Dark")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+(buttonWidth/2)+(buttonPadding*2)+buttonWidth, (height/2)+(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Galar_Dragon"); },
                new ItemStack(GymBadges.SAS_DRAGON_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Galar_Dragon")-1] == 0
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

        FormattedCharSequence title = Component.translatable("gui.cobblemongyms.galar").getVisualOrderText();
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
