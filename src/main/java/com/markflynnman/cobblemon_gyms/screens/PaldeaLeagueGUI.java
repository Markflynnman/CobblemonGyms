package com.markflynnman.cobblemon_gyms.screens;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.badgeCollection.ClientBadgeCollectionData;
import com.markflynnman.cobblemon_gyms.badgeCollection.PlayerBadgeCollection;
import com.markflynnman.cobblemon_gyms.components.CobblemonGymsGUITools;
import com.markflynnman.cobblemon_gyms.components.ItemButton;
import com.markflynnman.cobblemon_gyms.items.GymBadges;
import com.markflynnman.cobblemon_gyms.menus.PaldeaLeagueMenu;
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

public class PaldeaLeagueGUI extends AbstractContainerScreen<PaldeaLeagueMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(CobblemonGyms.MODID, "textures/gui/cobblemongymsgui.png");
    protected final Minecraft minecraftInstance;
    private static final int bgWidth = 208;
    private static final int bgHeight = 172;
    private static final int buttonWidth = 24;
    private static final int buttonHeight = 24;
    private static final int buttonPadding = 12;
    private boolean back = true;

    public PaldeaLeagueGUI(PaldeaLeagueMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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

        // 8 / 5 / 5 Badges
        // Top 10
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-66, (height/2)-(buttonHeight*2)-(buttonPadding/2)-buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Bug"); },
                new ItemStack(GymBadges.SV_BUG_BADGE.get()),
                false
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-30, (height/2)-(buttonHeight*2)-(buttonPadding/2)-buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Grass"); },
                new ItemStack(GymBadges.SV_GRASS_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Grass")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+6, (height/2)-(buttonHeight*2)-(buttonPadding/2)-buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Electric"); },
                new ItemStack(GymBadges.SV_ELECTRIC_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Electric")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+42, (height/2)-(buttonHeight*2)-(buttonPadding/2)-buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Water"); },
                new ItemStack(GymBadges.SV_WATER_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Water")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-66, (height/2)-buttonHeight-(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Normal"); },
                new ItemStack(GymBadges.SV_NORMAL_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Normal")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-30, (height/2)-buttonHeight-(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Ghost"); },
                new ItemStack(GymBadges.SV_GHOST_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Ghost")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+6, (height/2)-buttonHeight-(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Psychic"); },
                new ItemStack(GymBadges.SV_PSYCHIC_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Psychic")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+42, (height/2)-buttonHeight-(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Ice"); },
                new ItemStack(GymBadges.SV_ICE_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Ice")-1] == 0
        ));

        // Mid 5
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2)-(buttonPadding*2)-(buttonWidth*2), (height/2)+(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Dark"); },
                new ItemStack(GymBadges.SV_DARK_BADGE.get()),
                false
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2)-buttonPadding-buttonWidth, (height/2)+(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Fire"); },
                new ItemStack(GymBadges.SV_FIRE_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Fire")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2), (height/2)+(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Poison"); },
                new ItemStack(GymBadges.SV_POISON_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Poison")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+(buttonWidth/2)+buttonPadding, (height/2)+(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Fairy"); },
                new ItemStack(GymBadges.SV_FAIRY_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Fairy")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+(buttonWidth/2)+(buttonPadding*2)+buttonWidth, (height/2)+(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Fighting"); },
                new ItemStack(GymBadges.SV_FIGHTING_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Fighting")-1] == 0
        ));

        // Bottom 5
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2)-(buttonPadding*2)-(buttonWidth*2), (height/2)+(buttonPadding/2)+buttonHeight+buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Rock"); },
                new ItemStack(GymBadges.SV_ROCK_BADGE.get()),
                false
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2)-buttonPadding-buttonWidth, (height/2)+(buttonPadding/2)+buttonHeight+buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Flying"); },
                new ItemStack(GymBadges.SV_FLYING_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Flying")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2), (height/2)+(buttonPadding/2)+buttonHeight+buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Steel"); },
                new ItemStack(GymBadges.SV_STEEL_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Steel")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+(buttonWidth/2)+buttonPadding, (height/2)+(buttonPadding/2)+buttonHeight+buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Ground"); },
                new ItemStack(GymBadges.SV_GROUND_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Ground")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+(buttonWidth/2)+(buttonPadding*2)+buttonWidth, (height/2)+(buttonPadding/2)+buttonHeight+buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("Paldea_Dragon"); },
                new ItemStack(GymBadges.SV_DRAGON_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("Paldea_Dragon")-1] == 0
        ));

        // Back

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

        FormattedCharSequence title = Component.translatable("gui.cobblemongyms.paldea").getVisualOrderText();
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
