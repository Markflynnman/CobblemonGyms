package com.markflynnman.cobblemon_gyms.commands;

import com.markflynnman.cobblemon_gyms.Config;
import com.markflynnman.cobblemon_gyms.badgeCollection.PlayerBadgeCollectionProvider;
import com.markflynnman.cobblemon_gyms.menus.CobblemonGymsMenu;
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
import com.mojang.logging.LogUtils;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.network.NetworkHooks;
import org.slf4j.Logger;

public class CobblemonGymsGUICommand {
    private static final Logger LOGGER = LogUtils.getLogger();

    public CobblemonGymsGUICommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("GymGUI").requires((p) -> {
            return p.hasPermission(Config.getGymGUICommand());
        }).executes((command) -> {
            if (command.getSource().getPlayer() != null) {
                return cobblemonGymsGUI(command.getSource(), command.getSource().getPlayer());
            }
            else { return -1; }
        }).then((Commands.argument("target", EntityArgument.player()).requires((p) -> {
            return p.hasPermission(Config.getGymGUICommandOther());
        }).executes((command) -> {
            return cobblemonGymsGUI(command.getSource(), EntityArgument.getPlayer(command, "target"));
        }))));

        dispatcher.register(Commands.literal("GymCommands").requires((p) -> {
            return p.hasPermission(Config.getGymCommandsSetCommand());
        }).then(Commands.literal("set").then(Commands.argument("gym", StringArgumentType.word()).suggests(new CobblemonGymsSuggestionProvider())
        .then(Commands.argument("command", StringArgumentType.greedyString()).executes((command) -> {
            return GymCommands(command.getSource(), StringArgumentType.getString(command, "gym"), StringArgumentType.getString(command, "command"));
        })))));
    }

    private int cobblemonGymsGUI(CommandSourceStack source, ServerPlayer pPlayer) throws CommandSyntaxException {
        String player_name = pPlayer.getName().toString().replace("literal{", "").replace("}", "");

        source.sendSuccess(() -> {
            return Component.literal("Open GUI for "+player_name);
        }, true);
        LOGGER.warn("Open GUI for "+player_name);

        pPlayer.getCapability(PlayerBadgeCollectionProvider.PLAYER_BADGE_COLLECTION).ifPresent(badgeCollection -> {
            PacketHandler.sendToPlayer(new CBadgeCollectionDataSyncPacket(badgeCollection.getBadgeCollection()), pPlayer);
        });

        NetworkHooks.openScreen(pPlayer, new SimpleMenuProvider(
                (contentId, playerInventory, player) -> new CobblemonGymsMenu(contentId, playerInventory),
                Component.literal("Cobblemon Gyms GUI")
        ));

        return 1;
    }

    private int GymCommands(CommandSourceStack source, String gym, String command) throws CommandSyntaxException {
        if (command != null && !command.trim().isEmpty()) {
            String gymCommand = command.replace("/", "").trim();

            Config.GymConfigCommands.get(gym).set(gymCommand);
        }

        source.sendSuccess(() -> {
            return Component.literal(gym+" Command set to: "+ command);
        }, true);

        return 1;
    }
}
