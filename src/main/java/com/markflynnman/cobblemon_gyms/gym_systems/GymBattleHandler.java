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
import com.cobblemon.mod.common.util.*;

import java.util.*;

public class GymBattleHandler {
    private static final GymBattleHandler INSTANCE = new GymBattleHandler();
    private MinecraftServer server;
    private Map<UUID, GymLeader> battleMap = new HashMap<>();

    public static GymBattleHandler getINSTANCE() {
        return INSTANCE;
    }

    public void setServer(MinecraftServer server) {
        this.server = server;
    }

    public void addBattle(UUID playerUUID, GymLeader gymLeader) {
        battleMap.put(playerUUID, gymLeader);
    }

    public void removeBattle(UUID playerUUID) {
        battleMap.remove(playerUUID);
    }

    public void battleEnd(List<BattleActor> winners, List<BattleActor> losers) {
        for (BattleActor winner : winners) {
            Iterable<UUID> uuids = winner.getPlayerUUIDs();
            uuids.forEach((uuid) -> {
                ServerPlayer player = server.getPlayerList().getPlayer(uuid);
                if (player != null) {
                    player.getInventory().add(new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, battleMap.get(uuid).getBadgePath()))));
                    player.getData(Attachments.PLAYER_BADGE_COLLECTION).addBadge(battleMap.get(uuid).getBadge());
                    player.teleportTo(server.getLevel(GymDimension.COBBLEMON_GYMS_LEVEL_KEY), 0, 71, 0, 180, 0);
                    Cobblemon.INSTANCE.getStorage().getParty(player).heal();
                    removeBattle(uuid);
                }
            });
        }

        for (BattleActor loser : losers) {
            Iterable<UUID> uuids = loser.getPlayerUUIDs();
            uuids.forEach((uuid) -> {
                ServerPlayer player = server.getPlayerList().getPlayer(uuid);
                if (player != null) {
                    player.teleportTo(server.getLevel(GymDimension.COBBLEMON_GYMS_LEVEL_KEY), 0, 71, 0, 180, 0);
                    Cobblemon.INSTANCE.getStorage().getParty(player).heal();
                    removeBattle(uuid);
                }
            });
        }
    }
}
