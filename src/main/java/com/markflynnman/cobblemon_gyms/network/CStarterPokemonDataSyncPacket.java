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

    private static final Logger LOGGER = LogUtils.getLogger();
    public static void handle(final CStarterPokemonDataSyncPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            // ON CLIENT
            LOGGER.info("c_starter_pokemon_data_sync_packet received.");
            ClientStarterPokemonData.set(packet.starterPokemon, packet.starterPokemonType, packet.starterPokemonDex);
        });
    }
}

//public class CStarterPokemonDataSyncPacket {
//    private final String starterPokemon;
//    private final String starterPokemonType;
//    private final int starterPokemonDex;
//
//    public CStarterPokemonDataSyncPacket(String starterPokemon, String starterPokemonType, int starterPokemonDex) {
//        this.starterPokemon = starterPokemon;
//        this.starterPokemonType = starterPokemonType;
//        this.starterPokemonDex = starterPokemonDex;
//    }
//
//    public CStarterPokemonDataSyncPacket(FriendlyByteBuf buffer) {
//        this(buffer.readUtf(), buffer.readUtf(), buffer.readInt());
//    }
//
//    public void encode(FriendlyByteBuf buffer) {
//        buffer.writeUtf(this.starterPokemon);
//        buffer.writeUtf(this.starterPokemonType);
//        buffer.writeInt(this.starterPokemonDex);
//    }
//
//    public void handle(Supplier<NetworkEvent.Context> supplier) {
//        NetworkEvent.Context context = supplier.get();
//        context.enqueueWork(() -> {
//            // ON CLIENT
//            ClientStarterPokemonData.set(this.starterPokemon, this.starterPokemonType, this.starterPokemonDex);
//        });
//    }
//}
