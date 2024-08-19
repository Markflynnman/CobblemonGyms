package com.markflynnman.cobblemon_gyms.commands;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.command.argument.PokemonPropertiesArgumentType;
import com.markflynnman.cobblemon_gyms.Config;
import com.markflynnman.cobblemon_gyms.capabilities.PlayerBadgeCollectionProvider;
import com.markflynnman.cobblemon_gyms.capabilities.PlayerStarterPokemonProvider;
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

public class CobblemonGymsCommands {
    private static final Logger LOGGER = LogUtils.getLogger();

    public CobblemonGymsCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
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

        dispatcher.register(Commands.literal("CobblemonGyms").requires((p) -> {
            return p.hasPermission(Config.getStarterViewCommand());
        }).then(Commands.literal("starter").executes((command) -> {
            if (command.getSource().getPlayer() != null) {
                return ViewStarter(command.getSource(), command.getSource().getPlayer());
            } else {
                return -1;
            }
        }).then(Commands.argument("player", EntityArgument.player()).executes((command) -> {
            return ViewStarter(command.getSource(), EntityArgument.getPlayer(command, "player"));
        }).then(Commands.literal("set").then(Commands.argument("pokemon", PokemonPropertiesArgumentType.Companion.properties()).requires((p) -> {
            return p.hasPermission(Config.getStarterSetCommand());
        }).executes((command) -> {
            return SetStarter(command.getSource(), EntityArgument.getPlayer(command, "player"), PokemonPropertiesArgumentType.Companion.getPokemonProperties(command, "pokemon"));
        }))))).then(Commands.literal("command").then(Commands.literal("set").then(Commands.argument("gym", StringArgumentType.word()).suggests(new CobblemonGymsSuggestionProvider())
        .then(Commands.argument("command", StringArgumentType.greedyString()).requires((p) -> {
            return p.hasPermission(Config.getGymCommandsSetCommand());
        }).executes((command) -> {
            return SetGymCommand(command.getSource(), StringArgumentType.getString(command, "gym"), StringArgumentType.getString(command, "command"));
        })))).then(Commands.literal("get").then(Commands.argument("gym", StringArgumentType.word()).suggests(new CobblemonGymsSuggestionProvider()).requires((p) -> {
            return p.hasPermission(Config.getGymCommandsGetCommand());
        }).executes((command) -> {
            return GetGymCommand(command.getSource(), StringArgumentType.getString(command, "gym"));
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

    private int SetGymCommand(CommandSourceStack source, String gym, String command) throws CommandSyntaxException {
        int response = Config.setGymCommand(gym.toLowerCase(), command);
        if (response == -1) {
            source.sendFailure(Component.literal("Gym badge \"" + gym.toLowerCase() + "\" not found!"));
            return -1;
        } else {
            source.sendSuccess(() -> {
                return Component.literal(gym.toLowerCase() + " command set to: " + command);
            }, true);
            return 1;
        }
    }

    private int GetGymCommand(CommandSourceStack source, String gym) throws CommandSyntaxException {
        String command = Config.getGymCommand(gym.toLowerCase());
        if (command != null && !command.trim().isEmpty()) {
            source.sendSuccess(() -> {
                return Component.literal(gym.toLowerCase() + " Command: /" + command);
            }, false);
            return 1;
        } else {
            source.sendFailure(Component.literal("Gym badge \"" + gym.toLowerCase() + "\" not found!"));
            return -1;
        }
    }

    private int ViewStarter(CommandSourceStack source, ServerPlayer pPlayer) {
        String player_name = pPlayer.getName().toString().replace("literal{", "").replace("}", "");

        pPlayer.getCapability(PlayerStarterPokemonProvider.PLAYER_STARTER_POKEMON).ifPresent(starterPokemon -> {
            source.sendSuccess(() -> {
                return Component.literal(((source.getPlayer() == pPlayer) ? "Your" : player_name + "'s") + " starter: " + starterPokemon.getStarterPokemon() + ", Type: " + starterPokemon.getStarterPokemonType() + ", Dex number: " + starterPokemon.getStarterPokemonDex());
            }, false);
        });

        return 1;
    }

    private int SetStarter(CommandSourceStack source, ServerPlayer pPlayer, PokemonProperties pPokemon) {
        String player_name = pPlayer.getName().toString().replace("literal{", "").replace("}", "");

        if (pPokemon.getSpecies() == null) {
            return -1;
        }

        pPlayer.getCapability(PlayerStarterPokemonProvider.PLAYER_STARTER_POKEMON).ifPresent(starterPokemon -> {
            starterPokemon.setStarterPokemon(pPokemon.create());
            source.sendSuccess(() -> {
                return Component.literal("Set " + player_name + "'s starter pokemon to: " + starterPokemon.getStarterPokemon() + ", Type: " + starterPokemon.getStarterPokemonType() + ", Dex number: " + starterPokemon.getStarterPokemonDex());
            }, true);
        });

        return 1;
    }
}
