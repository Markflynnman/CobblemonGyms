package com.markflynnman.cobblemon_gyms.network;

import com.markflynnman.cobblemon_gyms.commands.GymSelectionCommand;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SGymSelectionPacket {
    private final String gym_ID;

    public SGymSelectionPacket(String gym_ID) { this.gym_ID = gym_ID; }

    public SGymSelectionPacket(FriendlyByteBuf buffer) {
        this(buffer.readUtf());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUtf(this.gym_ID);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        ServerPlayer serverPlayer = context.get().getSender();
        if (serverPlayer == null) {
            return;
        }

        new GymSelectionCommand(this.gym_ID, serverPlayer);

        context.get().setPacketHandled(true);
    }
}
