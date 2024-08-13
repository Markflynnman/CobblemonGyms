package com.markflynnman.cobblemon_gyms.network;

import com.markflynnman.cobblemon_gyms.capabilities.ClientBadgeCollectionData;
import com.markflynnman.cobblemon_gyms.capabilities.ClientStarterPokemonData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CStarterPokemonDataSyncPacket {
    private final String starterPokemon;
    private final String starterPokemonType;
    private final int starterPokemonDex;

    public CStarterPokemonDataSyncPacket(String starterPokemon, String starterPokemonType, int starterPokemonDex) {
        this.starterPokemon = starterPokemon;
        this.starterPokemonType = starterPokemonType;
        this.starterPokemonDex = starterPokemonDex;
    }

    public CStarterPokemonDataSyncPacket(FriendlyByteBuf buffer) {
        this(buffer.readUtf(), buffer.readUtf(), buffer.readInt());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUtf(this.starterPokemon);
        buffer.writeUtf(this.starterPokemonType);
        buffer.writeInt(this.starterPokemonDex);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // ON CLIENT
            ClientStarterPokemonData.set(this.starterPokemon, this.starterPokemonType, this.starterPokemonDex);
        });
    }
}
