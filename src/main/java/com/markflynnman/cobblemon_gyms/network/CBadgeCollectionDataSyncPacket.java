package com.markflynnman.cobblemon_gyms.network;

import com.cobblemon.mod.relocations.oracle.truffle.regex.tregex.buffer.IntArrayBuffer;
import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.data_attachments.ClientBadgeCollectionData;
import com.markflynnman.cobblemon_gyms.data_attachments.PlayerBadgeCollection;
import com.mojang.logging.LogUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.slf4j.Logger;

import java.util.function.Supplier;

public record CBadgeCollectionDataSyncPacket(int[] badgeCollection) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<CBadgeCollectionDataSyncPacket> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "c_badge_collection_data_sync_packet")
    );

    public static final StreamCodec<FriendlyByteBuf, CBadgeCollectionDataSyncPacket> STREAM_CODEC = StreamCodec.of(
            (buf, _CBadgeCollectionDataSyncPacket) -> _CBadgeCollectionDataSyncPacket.encode(buf),
            CBadgeCollectionDataSyncPacket::decode);

//    public static final StreamCodec<ByteBuf, CBadgeCollectionDataSyncPacket> STREAM_CODEC = StreamCodec.composite(
//            ByteBufCodecs.BYTE_ARRAY,
//            CBadgeCollectionDataSyncPacket::badgeCollection,
//            CBadgeCollectionDataSyncPacket::new
//    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    private void encode(FriendlyByteBuf buffer) {
        buffer.writeVarIntArray(this.badgeCollection);
    }

    public static CBadgeCollectionDataSyncPacket decode(FriendlyByteBuf buffer) {
        return new CBadgeCollectionDataSyncPacket(buffer.readVarIntArray());
    }

    private static final Logger LOGGER = LogUtils.getLogger();
    public static void handle(final CBadgeCollectionDataSyncPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            // ON CLIENT
            LOGGER.info("Received c_badge_collection_data_sync_packet");
            ClientBadgeCollectionData.set(packet.badgeCollection);
        });
    }
}

//public class CBadgeCollectionDataSyncPacket {
//    private final int[] badgeCollection;
//
//    public CBadgeCollectionDataSyncPacket(int[] badgeCollection) {
//        this.badgeCollection = badgeCollection;
//    }
//
//    public CBadgeCollectionDataSyncPacket(FriendlyByteBuf buffer) {
//        this(buffer.readVarIntArray());
//    }
//
//    public void encode(FriendlyByteBuf buffer) {
//        buffer.writeVarIntArray(this.badgeCollection);
//    }
//
//    public void handle(Supplier<NetworkEvent.Context> supplier) {
//        NetworkEvent.Context context = supplier.get();
//        context.enqueueWork(() -> {
//            // ON CLIENT
//            ClientBadgeCollectionData.set(this.badgeCollection);
//        });
//    }
//}
