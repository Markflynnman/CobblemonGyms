package com.markflynnman.cobblemon_gyms.commands;

import com.markflynnman.cobblemon_gyms.badgeCollection.PlayerBadgeCollection;
import com.markflynnman.cobblemon_gyms.badgeCollection.PlayerBadgeCollectionProvider;
import com.markflynnman.cobblemon_gyms.network.CBadgeCollectionDataSyncPacket;
import com.markflynnman.cobblemon_gyms.network.PacketHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class BadgeCollectionCommands {
    public BadgeCollectionCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("badges").executes((command) -> {
            if (command.getSource().getPlayer() != null) {
                return PlayerBadges(command.getSource(), command.getSource().getPlayer());
            }
            else { return -1; }
        }).then(Commands.argument("player", EntityArgument.player()).executes((command) -> {
            return PlayerBadges(command.getSource(), EntityArgument.getPlayer(command, "player"));
        }).then(Commands.literal("dev").requires((p) -> {
            return p.hasPermission(4);
        }).executes((command) -> {
            return PlayerBadges(command.getSource(), EntityArgument.getPlayer(command, "player"), true);
        })).then(Commands.literal("add").requires((p) -> {
            return p.hasPermission(4);
        }).then(Commands.argument("badge", StringArgumentType.word()).suggests(new CobblemonGymsSuggestionProvider()).executes((command) -> {
            return PlayerBadgesAdd(command.getSource(), EntityArgument.getPlayer(command, "player"), StringArgumentType.getString(command, "badge"));
        }))).then(Commands.literal("remove").requires((p) -> {
            return p.hasPermission(4);
        }).then(Commands.argument("badge", StringArgumentType.word()).suggests(new CobblemonGymsSuggestionProvider()).executes((command) -> {
            return PlayerBadgesRemove(command.getSource(), EntityArgument.getPlayer(command, "player"), StringArgumentType.getString(command, "badge"));
        })))));
    }

    private int PlayerBadges(CommandSourceStack source, ServerPlayer pPlayer) throws CommandSyntaxException {
        return PlayerBadges(source, pPlayer, false);
    }

    private int PlayerBadges(CommandSourceStack source, ServerPlayer pPlayer, boolean dev) throws CommandSyntaxException {
        String player_name = pPlayer.getName().toString().replace("literal{", "").replace("}", "");

        pPlayer.getCapability(PlayerBadgeCollectionProvider.PLAYER_BADGE_COLLECTION).ifPresent(badgeCollection -> {
            PacketHandler.sendToPlayer(new CBadgeCollectionDataSyncPacket(badgeCollection.getBadgeCollection()), pPlayer);
        });

        pPlayer.getCapability(PlayerBadgeCollectionProvider.PLAYER_BADGE_COLLECTION).ifPresent(badgeCollection -> {
            String output;
            int[] badges = IntStream.range(0, badgeCollection.getBadgeCollection().length)
                    .filter(i -> badgeCollection.getBadgeCollection()[i] == 1)
                    .toArray();

            output = BadgeString(badges, (source.getPlayer() == pPlayer) ? "" : player_name);

            source.sendSuccess(() -> {
                if (dev) {
                    return Component.literal(((source.getPlayer() == pPlayer) ? "Your" : player_name) +" badges:\n"+ output + "\n" + Arrays.toString(badgeCollection.getBadgeCollection()));
                }
                return Component.literal(((source.getPlayer() == pPlayer) ? "Your" : player_name) +" badges:\n"+ output);
            }, false);
        });

        return 1;
    }

    private int PlayerBadgesAdd(CommandSourceStack source, ServerPlayer pPlayer, String badge) throws CommandSyntaxException {
        AtomicBoolean response = new AtomicBoolean(false);
        pPlayer.getCapability(PlayerBadgeCollectionProvider.PLAYER_BADGE_COLLECTION).ifPresent(badgeCollection -> {
            response.set(badgeCollection.addBadge(badge));
        });
        if (response.get()) {
            source.sendSuccess(() -> {
                return Component.literal("ADD: "+badge);
            }, true);
            return 1;
        }
        else {
            source.sendFailure(Component.literal("Could not find badge: "+badge));
            return -1;
        }
    }

    private int PlayerBadgesRemove(CommandSourceStack source, ServerPlayer pPlayer, String badge) throws CommandSyntaxException {
        AtomicBoolean response = new AtomicBoolean(false);
        pPlayer.getCapability(PlayerBadgeCollectionProvider.PLAYER_BADGE_COLLECTION).ifPresent(badgeCollection -> {
            response.set(badgeCollection.removeBadge(badge));
        });
        if (response.get()) {
            source.sendSuccess(() -> {
                return Component.literal("REMOVE: "+badge);
            }, true);
            return 1;
        }
        else {
            source.sendFailure(Component.literal("Could not find badge: "+badge));
            return -1;
        }
    }

    private String BadgeString(int[] badges, String player) {
        StringBuilder output = new StringBuilder();

        if (badges.length == 0) {
            if (player.isEmpty()) {
                output = new StringBuilder("You have no badges. :(");
            }
            else {
                output = new StringBuilder(player + " has no badges.");
            }
            return output.toString();
        }

        for (int i = 0; i < badges.length; i++) {
            String[] badge = PlayerBadgeCollection.AllBadges.get(badges[i]).split("_");
            if (i == 0) {
                output = new StringBuilder(badge[0] + ":\n    " + badge[1]);
                continue;
            }
            if (!badge[0].equals(PlayerBadgeCollection.AllBadges.get(badges[i-1]).split("_")[0])) {
                output.append("\n").append(badge[0]).append(":");
            }
            output.append("\n    ").append(badge[1]);
        }

        return output.toString();
    }
}
