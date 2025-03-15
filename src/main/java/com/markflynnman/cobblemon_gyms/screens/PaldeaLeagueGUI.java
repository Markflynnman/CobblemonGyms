package com.markflynnman.cobblemon_gyms.screens;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.data_attachments.ClientBadgeCollectionData;
import com.markflynnman.cobblemon_gyms.data_attachments.PlayerBadgeCollection;
import com.markflynnman.cobblemon_gyms.components.CobblemonGymsGUITools;
import com.markflynnman.cobblemon_gyms.components.ItemButton;
import com.markflynnman.cobblemon_gyms.items.GymBadges;
import com.markflynnman.cobblemon_gyms.menus.PaldeaLeagueMenu;
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
import net.neoforged.neoforge.network.PacketDistributor;

public class PaldeaLeagueGUI extends AbstractContainerScreen<PaldeaLeagueMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "textures/gui/cobblemongymsgui.png");
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
        PacketDistributor.sendToServer(new SGymSelectionPacket(pCommand));
        closeScreen();
    }

    public void openScreen(String screen) {
        PacketDistributor.sendToServer(new SOpenScreenPacket(screen));
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
                onPress -> { gymSelect("paldea_bug"); },
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
                onPress -> { gymSelect("paldea_grass"); },
                new ItemStack(GymBadges.SV_GRASS_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_grass")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+6, (height/2)-(buttonHeight*2)-(buttonPadding/2)-buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("paldea_electric"); },
                new ItemStack(GymBadges.SV_ELECTRIC_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_electric")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+42, (height/2)-(buttonHeight*2)-(buttonPadding/2)-buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("paldea_water"); },
                new ItemStack(GymBadges.SV_WATER_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_water")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-66, (height/2)-buttonHeight-(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("paldea_normal"); },
                new ItemStack(GymBadges.SV_NORMAL_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_normal")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-30, (height/2)-buttonHeight-(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("paldea_ghost"); },
                new ItemStack(GymBadges.SV_GHOST_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_ghost")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+6, (height/2)-buttonHeight-(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("paldea_psychic"); },
                new ItemStack(GymBadges.SV_PSYCHIC_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_psychic")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+42, (height/2)-buttonHeight-(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("paldea_ice"); },
                new ItemStack(GymBadges.SV_ICE_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_ice")-1] == 0
        ));

        // Mid 5
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2)-(buttonPadding*2)-(buttonWidth*2), (height/2)+(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("paldea_dark"); },
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
                onPress -> { gymSelect("paldea_fire"); },
                new ItemStack(GymBadges.SV_FIRE_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_fire")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2), (height/2)+(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("paldea_poison"); },
                new ItemStack(GymBadges.SV_POISON_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_poison")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+(buttonWidth/2)+buttonPadding, (height/2)+(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("paldea_fairy"); },
                new ItemStack(GymBadges.SV_FAIRY_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_fairy")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+(buttonWidth/2)+(buttonPadding*2)+buttonWidth, (height/2)+(buttonPadding/2),
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("paldea_fighting"); },
                new ItemStack(GymBadges.SV_FIGHTING_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_fighting")-1] == 0
        ));

        // Bottom 5
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2)-(buttonPadding*2)-(buttonWidth*2), (height/2)+(buttonPadding/2)+buttonHeight+buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("paldea_rock"); },
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
                onPress -> { gymSelect("paldea_flying"); },
                new ItemStack(GymBadges.SV_FLYING_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_flying")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)-(buttonWidth/2), (height/2)+(buttonPadding/2)+buttonHeight+buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("paldea_steel"); },
                new ItemStack(GymBadges.SV_STEEL_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_steel")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+(buttonWidth/2)+buttonPadding, (height/2)+(buttonPadding/2)+buttonHeight+buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("paldea_ground"); },
                new ItemStack(GymBadges.SV_GROUND_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_ground")-1] == 0
        ));
        this.addRenderableWidget(new ItemButton(
                TEXTURE,
                (width/2)+(buttonWidth/2)+(buttonPadding*2)+buttonWidth, (height/2)+(buttonPadding/2)+buttonHeight+buttonPadding,
                buttonWidth, buttonHeight,
                3, 9, 9,
                0, 21, 9,
                0, 12,
                onPress -> { gymSelect("paldea_dragon"); },
                new ItemStack(GymBadges.SV_DRAGON_BADGE.get()),
                ClientBadgeCollectionData.getPlayerBadgeCollection()[PlayerBadgeCollection.AllBadges.indexOf("paldea_dragon")-1] == 0
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
//        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);

        FormattedCharSequence title = Component.translatable("gui.cobblemon_gyms.paldea").getVisualOrderText();
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
