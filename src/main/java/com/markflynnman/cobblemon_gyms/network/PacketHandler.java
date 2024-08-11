package com.markflynnman.cobblemon_gyms.network;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static int id = 0;
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(CobblemonGyms.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        INSTANCE.messageBuilder(SOpenScreenPacket.class, id)
                .encoder(SOpenScreenPacket::encode)
                .decoder(SOpenScreenPacket::new)
                .consumerMainThread(SOpenScreenPacket::handle)
                .add();
        id++;
        INSTANCE.messageBuilder(SGymSelectionPacket.class, id)
                .encoder(SGymSelectionPacket::encode)
                .decoder(SGymSelectionPacket::new)
                .consumerMainThread(SGymSelectionPacket::handle)
                .add();
        id++;
        INSTANCE.messageBuilder(CBadgeCollectionDataSyncPacket.class, id)
                .encoder(CBadgeCollectionDataSyncPacket::encode)
                .decoder(CBadgeCollectionDataSyncPacket::new)
                .consumerMainThread(CBadgeCollectionDataSyncPacket::handle)
                .add();
        id++;
    }

    public static void sendToServer(Object msg) {
        INSTANCE.send(PacketDistributor.SERVER.noArg(), msg);
    }

    public static void sendToPlayer(Object msg, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }

    public static void sendToAllClients(Object msg) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }
}


