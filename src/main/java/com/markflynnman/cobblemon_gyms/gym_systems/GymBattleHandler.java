package com.markflynnman.cobblemon_gyms.gym_systems;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.data_attachments.Attachments;
import com.markflynnman.cobblemon_gyms.worldgen.dimension.GymDimension;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public class GymBattleHandler {
    private static final GymBattleHandler INSTANCE = new GymBattleHandler();
    private MinecraftServer server;
    private Map<UUID, GymArena> battleMap = new HashMap<>();

    public static GymBattleHandler getINSTANCE() {
        return INSTANCE;
    }

    public void setServer(MinecraftServer server) {
        this.server = server;
    }

    public void addBattle(UUID playerUUID, GymArena gymArena) {
        battleMap.put(playerUUID, gymArena);
    }

    public void removeBattle(UUID playerUUID) {
        battleMap.remove(playerUUID);
    }

    public void battleEnd(List<BattleActor> winners, List<BattleActor> losers) {
        for (BattleActor winner : winners) {
            Iterable<UUID> uuids = winner.getPlayerUUIDs();
            uuids.forEach((uuid) -> {
                ServerPlayer player = server.getPlayerList().getPlayer(uuid);
                GymArena gymArena = battleMap.get(uuid);
                if (player != null) {
                    if (!player.getData(Attachments.PLAYER_BADGE_COLLECTION).hasBadge(gymArena.getGymLeader().getBadge())) {
                        player.getInventory().add(new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, gymArena.getGymLeader().getBadgePath()))));
                        player.getData(Attachments.PLAYER_BADGE_COLLECTION).addBadge(gymArena.getGymLeader().getBadge());
                    }
                    player.teleportTo(server.getLevel(GymDimension.COBBLEMON_GYMS_LEVEL_KEY), 0.5, 71, 0.5, 180, 0);
                    Cobblemon.INSTANCE.getStorage().getParty(player).heal();
                    gymArena.setBattleInProgress(false);
                    removeBattle(uuid);
                }
            });
        }

        for (BattleActor loser : losers) {
            Iterable<UUID> uuids = loser.getPlayerUUIDs();
            uuids.forEach((uuid) -> {
                ServerPlayer player = server.getPlayerList().getPlayer(uuid);
                if (player != null) {
                    player.teleportTo(server.getLevel(GymDimension.COBBLEMON_GYMS_LEVEL_KEY), 0.5, 71, 0.5, 180, 0);
                    Cobblemon.INSTANCE.getStorage().getParty(player).heal();
                    battleMap.get(uuid).setBattleInProgress(false);
                    removeBattle(uuid);
                }
            });
        }
    }
}
