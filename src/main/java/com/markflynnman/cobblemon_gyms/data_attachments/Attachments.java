package com.markflynnman.cobblemon_gyms.data_attachments;


import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class Attachments {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, CobblemonGyms.MODID);

    public static final Supplier<AttachmentType<PlayerBadgeCollection>> PLAYER_BADGE_COLLECTION = ATTACHMENT_TYPES.register(
            "player_badge_collection", () -> AttachmentType.serializable(PlayerBadgeCollection::new).copyOnDeath().build()
    );
    public static final Supplier<AttachmentType<PlayerStarterPokemon>> PLAYER_STARTER_POKEMON = ATTACHMENT_TYPES.register(
            "player_starter_pokemon", () -> AttachmentType.serializable(PlayerStarterPokemon::new).copyOnDeath().build()
    );
    public static final Supplier<AttachmentType<PlayerWarpHistory>> PLAYER_WARP_HISTORY = ATTACHMENT_TYPES.register(
            "player_warp_history", () -> AttachmentType.serializable(PlayerWarpHistory::new).copyOnDeath().build()
    );

    public static void register(IEventBus modEventBus) {
        ATTACHMENT_TYPES.register(modEventBus);
    }
}
