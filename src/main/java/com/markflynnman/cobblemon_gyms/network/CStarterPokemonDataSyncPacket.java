package com.markflynnman.cobblemon_gyms.network;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.data_attachments.ClientStarterPokemonData;
import com.mojang.logging.LogUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.slf4j.Logger;

public record CStarterPokemonDataSyncPacket(String starterPokemon, String starterPokemonType, int starterPokemonDex) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<CStarterPokemonDataSyncPacket> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "c_starter_pokemon_data_sync_packet")
    );

    public static final StreamCodec<ByteBuf, CStarterPokemonDataSyncPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            CStarterPokemonDataSyncPacket::starterPokemon,
            ByteBufCodecs.STRING_UTF8,
            CStarterPokemonDataSyncPacket::starterPokemonType,
            ByteBufCodecs.VAR_INT,
            CStarterPokemonDataSyncPacket::starterPokemonDex,
            CStarterPokemonDataSyncPacket::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final CStarterPokemonDataSyncPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            // ON CLIENT
            ClientStarterPokemonData.set(packet.starterPokemon, packet.starterPokemonType, packet.starterPokemonDex);
        });
    }
}
