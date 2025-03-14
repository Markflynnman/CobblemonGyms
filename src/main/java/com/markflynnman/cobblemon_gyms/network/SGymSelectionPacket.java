package com.markflynnman.cobblemon_gyms.network;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
//import com.markflynnman.cobblemon_gyms.commands.GymSelectionCommand;
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
        });
    }
}

//public class SGymSelectionPacket {
//    private final String gym_ID;
//
//    public SGymSelectionPacket(String gym_ID) { this.gym_ID = gym_ID; }
//
//    public SGymSelectionPacket(FriendlyByteBuf buffer) {
//        this(buffer.readUtf());
//    }
//
//    public void encode(FriendlyByteBuf buffer) {
//        buffer.writeUtf(this.gym_ID);
//    }
//
//    public void handle(Supplier<NetworkEvent.Context> context) {
//        ServerPlayer serverPlayer = context.get().getSender();
//        if (serverPlayer == null) {
//            return;
//        }
//
//        new GymSelectionCommand(this.gym_ID, serverPlayer);
//
//        context.get().setPacketHandled(true);
//    }
//}
