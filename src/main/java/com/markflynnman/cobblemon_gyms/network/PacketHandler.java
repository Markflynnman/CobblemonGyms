package com.markflynnman.cobblemon_gyms.network;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class PacketHandler {
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(CobblemonGyms.MODID);
        registrar.playToClient(
                CStarterPokemonDataSyncPacket.TYPE,
                CStarterPokemonDataSyncPacket.STREAM_CODEC,
                CStarterPokemonDataSyncPacket::handle
        );
        registrar.playToClient(
                CBadgeCollectionDataSyncPacket.TYPE,
                CBadgeCollectionDataSyncPacket.STREAM_CODEC,
                CBadgeCollectionDataSyncPacket::handle
        );
        registrar.playToServer(
                SGymSelectionPacket.TYPE,
                SGymSelectionPacket.STREAM_CODEC,
                SGymSelectionPacket::handle
        );
        registrar.playToServer(
                SOpenScreenPacket.TYPE,
                SOpenScreenPacket.STREAM_CODEC,
                SOpenScreenPacket::handle
        );
    }
}
