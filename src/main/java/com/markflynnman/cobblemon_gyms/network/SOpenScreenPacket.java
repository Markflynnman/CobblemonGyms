package com.markflynnman.cobblemon_gyms.network;

import com.markflynnman.cobblemon_gyms.badgeCollection.PlayerBadgeCollectionProvider;
import com.markflynnman.cobblemon_gyms.menus.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.Supplier;

public class SOpenScreenPacket {
    private final String screen;

    public SOpenScreenPacket(String screen) {
        this.screen = screen;
    }

    public SOpenScreenPacket(FriendlyByteBuf buffer) {
        this(buffer.readUtf());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUtf(this.screen);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
//      LocalPlayer player = Minecraft.getInstance().player; (If on client)
        ServerPlayer serverPlayer = context.get().getSender();
        if (serverPlayer == null) { return; }

        serverPlayer.getCapability(PlayerBadgeCollectionProvider.PLAYER_BADGE_COLLECTION).ifPresent(badgeCollection -> {
            PacketHandler.sendToPlayer(new CBadgeCollectionDataSyncPacket(badgeCollection.getBadgeCollection()), serverPlayer);
        });

        switch (this.screen) {
            case "indigo_league_gui":
                NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider(
                        (contentId, playerInventory, player) -> new IndigoLeagueMenu(contentId, playerInventory),
                        Component.literal("Indigo League GUI")
                ));
                break;
            case "johto_league_gui":
                NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider(
                        (contentId, playerInventory, player) -> new JohtoLeagueMenu(contentId, playerInventory),
                        Component.literal("Johto League GUI")
                ));
                break;
            case "hoenn_league_gui":
                NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider(
                        (contentId, playerInventory, player) -> new HoennLeagueMenu(contentId, playerInventory),
                        Component.literal("Indigo League GUI")
                ));
                break;
            case "sinnoh_league_gui":
                NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider(
                        (contentId, playerInventory, player) -> new SinnohLeagueMenu(contentId, playerInventory),
                        Component.literal("Indigo League GUI")
                ));
                break;
            case "unova_league_gui":
                NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider(
                        (contentId, playerInventory, player) -> new UnovaLeagueMenu(contentId, playerInventory),
                        Component.literal("Indigo League GUI")
                ));
                break;
            case "kalos_league_gui":
                NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider(
                        (contentId, playerInventory, player) -> new KalosLeagueMenu(contentId, playerInventory),
                        Component.literal("Indigo League GUI")
                ));
                break;
            case "galar_league_gui":
                NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider(
                        (contentId, playerInventory, player) -> new GalarLeagueMenu(contentId, playerInventory),
                        Component.literal("Indigo League GUI")
                ));
                break;
            case "paldea_league_gui":
                NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider(
                        (contentId, playerInventory, player) -> new PaldeaLeagueMenu(contentId, playerInventory),
                        Component.literal("Indigo League GUI")
                ));
                break;
            default:
                NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider(
                        (contentId, playerInventory, player) -> new CobblemonGymsMenu(contentId, playerInventory),
                        Component.literal("Cobblemon Gyms GUI")
                ));
        }

        context.get().setPacketHandled(true);
    }
}
