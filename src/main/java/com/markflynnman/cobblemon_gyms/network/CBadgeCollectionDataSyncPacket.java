package com.markflynnman.cobblemon_gyms.network;

import com.markflynnman.cobblemon_gyms.capabilities.ClientBadgeCollectionData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CBadgeCollectionDataSyncPacket {
    private final int[] badgeCollection;

    public CBadgeCollectionDataSyncPacket(int[] badgeCollection) {
        this.badgeCollection = badgeCollection;
    }

    public CBadgeCollectionDataSyncPacket(FriendlyByteBuf buffer) {
        this(buffer.readVarIntArray());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeVarIntArray(this.badgeCollection);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // ON CLIENT
            ClientBadgeCollectionData.set(this.badgeCollection);
        });
    }
}
