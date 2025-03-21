package com.markflynnman.cobblemon_gyms.network;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.commands.GymSelectionCommand;
import com.markflynnman.cobblemon_gyms.gym_systems.GymHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SGymSelectionPacket(String gymID) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SGymSelectionPacket> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "s_gym_selection_packet")
    );

    public static final StreamCodec<ByteBuf, SGymSelectionPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SGymSelectionPacket::gymID,
            SGymSelectionPacket::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final SGymSelectionPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            // ON SERVER
            ServerPlayer serverPlayer = (ServerPlayer)context.player();

//            new GymSelectionCommand(packet.gymID, serverPlayer);

            GymHandler.startGym(serverPlayer.serverLevel(), serverPlayer, packet.gymID);
        });
    }
}
