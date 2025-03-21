package com.markflynnman.cobblemon_gyms.commands;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.storage.player.GeneralPlayerData;
import com.cobblemon.mod.common.api.storage.player.PlayerInstancedDataStoreTypes;
import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.command.argument.PokemonPropertiesArgumentType;
import com.cobblemon.mod.common.net.messages.client.starter.OpenStarterUIPacket;
import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.Config;
import com.markflynnman.cobblemon_gyms.data_attachments.Attachments;
import com.markflynnman.cobblemon_gyms.data_attachments.PlayerBadgeCollection;
import com.markflynnman.cobblemon_gyms.data_attachments.PlayerStarterPokemon;
import com.markflynnman.cobblemon_gyms.menus.CobblemonGymsMenu;
import com.markflynnman.cobblemon_gyms.network.CBadgeCollectionDataSyncPacket;
import com.markflynnman.cobblemon_gyms.network.PacketHandler;
import com.markflynnman.cobblemon_gyms.worldgen.dimension.GymDimension;
import com.markflynnman.cobblemon_gyms.worldgen.feature.GymArenaFeatures;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import com.mojang.logging.LogUtils;
import net.minecraft.world.SimpleMenuProvider;
import net.neoforged.neoforge.network.PacketDistributor;
import org.slf4j.Logger;

import java.awt.*;

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
        }))).then(Commands.literal("start").requires((p) -> {
            return p.hasPermission(3);
        }).executes((command) -> {
            return CobblemonGymsStart(command.getSource(), EntityArgument.getPlayer(command, "player"));
        })))).then(Commands.literal("command").then(Commands.literal("set").then(Commands.argument("gym", StringArgumentType.word()).suggests(new CobblemonGymsSuggestionProvider())
        .then(Commands.argument("command", StringArgumentType.greedyString()).requires((p) -> {
            return p.hasPermission(Config.getGymCommandsSetCommand());
        }).executes((command) -> {
            return SetGymCommand(command.getSource(), StringArgumentType.getString(command, "gym"), StringArgumentType.getString(command, "command"));
        })))).then(Commands.literal("get").then(Commands.argument("gym", StringArgumentType.word()).suggests(new CobblemonGymsSuggestionProvider()).requires((p) -> {
            return p.hasPermission(Config.getGymCommandsGetCommand());
        }).executes((command) -> {
            return GetGymCommand(command.getSource(), StringArgumentType.getString(command, "gym"));
        })))));

        dispatcher.register(Commands.literal("GymHub").requires((p) -> {
            return p.hasPermission(0);
        }).executes((command) -> {
            if (command.getSource().getPlayer() != null) {
                return cobblemonGymsHub(command.getSource(), command.getSource().getPlayer());
            } else { return -1; }
        }).then((Commands.literal("place").then((Commands.argument("type", StringArgumentType.word()).suggests(new CobblemonGymsTypeSuggestionProvider()).requires((p) -> {
            return p.hasPermission(3);
        }).executes((command) -> {
            if (command.getSource().getPlayer() != null) {
                return placeGym(command.getSource(), command.getSource().getPlayer(), StringArgumentType.getString(command, "type"));
            } else { return -1; }
        }).then(Commands.argument("x", IntegerArgumentType.integer()).then(Commands.argument("y", IntegerArgumentType.integer()).then(Commands.argument("z", IntegerArgumentType.integer()).requires((p) -> {
            return p.hasPermission(3);
        }).executes((command) -> {
            return placeGym(command.getSource(), command.getSource().getPlayer(), StringArgumentType.getString(command, "type"), IntegerArgumentType.getInteger(command, "x"), IntegerArgumentType.getInteger(command, "y"), IntegerArgumentType.getInteger(command, "z"));
        })))))))));

    }

    private int cobblemonGymsHub(CommandSourceStack source, ServerPlayer pPlayer) throws CommandSyntaxException {
        String player_name = pPlayer.getName().toString().replace("literal{", "").replace("}", "");

        if (source.getLevel() != source.getServer().getLevel(GymDimension.COBBLEMON_GYMS_LEVEL_KEY)) {
            GymDimension.toDimension(source.getServer(), pPlayer);
            source.sendSuccess(() -> {
                return Component.literal("Teleporting " + player_name + " to the Gym Dimension");
            }, true);
        }
        else {
            GymDimension.fromDimension(source.getServer(), pPlayer);
            source.sendSuccess(() -> {
                return Component.literal("Teleporting " + player_name + " out of the Gym Dimension");
            }, true);
        }

        return 1;
    }

    private int placeGym(CommandSourceStack source, ServerPlayer pPlayer, String type) throws CommandSyntaxException {
        BlockPos pos = new BlockPos((int)pPlayer.position().x, (int)pPlayer.position().y, (int)pPlayer.position().z);
        pos.below();

        source.sendSuccess(() -> {
            return Component.literal("Placing " + type + " gym at: " + pos);
        }, true);

        return  placeGym(source, pPlayer, type, pos.below());
    }

    private int placeGym(CommandSourceStack source, ServerPlayer pPlayer, String type, int x, int y, int z) throws CommandSyntaxException {
        source.sendSuccess(() -> {
            return Component.literal("Placing " + type + " gym at: " + x + " " + y + " " + z);
        }, true);

        return placeGym(source, pPlayer, type, new BlockPos(x, y, z));
    }

    private int placeGym(CommandSourceStack source, ServerPlayer pPlayer, String type, BlockPos pos) throws CommandSyntaxException {
        GymArenaFeatures.place(source.getLevel(), pos, false, false, ElementalTypes.INSTANCE.get(type));
        return 1;
    }

    private int cobblemonGymsGUI(CommandSourceStack source, ServerPlayer pPlayer) throws CommandSyntaxException {
        String player_name = pPlayer.getName().toString().replace("literal{", "").replace("}", "");

        source.sendSuccess(() -> {
            return Component.literal("Open GUI for "+player_name);
        }, true);
        LOGGER.warn("Open GUI for "+player_name);

        if (pPlayer.hasData(Attachments.PLAYER_BADGE_COLLECTION)) {
            PacketDistributor.sendToPlayer(pPlayer, new CBadgeCollectionDataSyncPacket(pPlayer.getData(Attachments.PLAYER_BADGE_COLLECTION).getBadgeCollection()));
        }

        pPlayer.openMenu(new SimpleMenuProvider(
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

        if (pPlayer.hasData(Attachments.PLAYER_STARTER_POKEMON)) {
            PlayerStarterPokemon starterPokemon = pPlayer.getData(Attachments.PLAYER_STARTER_POKEMON);
            source.sendSuccess(() -> {
                return Component.literal(((source.getPlayer() == pPlayer) ? "Your" : player_name + "'s") + " starter: " + starterPokemon.getStarterPokemon() + ", Type: " + starterPokemon.getStarterPokemonType() + ", Dex number: " + starterPokemon.getStarterPokemonDex());
            }, false);
        }

        return 1;
    }

    private int SetStarter(CommandSourceStack source, ServerPlayer pPlayer, PokemonProperties pPokemon) {
        String player_name = pPlayer.getName().toString().replace("literal{", "").replace("}", "");

        if (pPokemon.getSpecies() == null) {
            return -1;
        }

        if (pPlayer.hasData(Attachments.PLAYER_STARTER_POKEMON)) {
            PlayerStarterPokemon starterPokemon = pPlayer.getData(Attachments.PLAYER_STARTER_POKEMON);

            starterPokemon.setStarterPokemon(pPokemon.create());
            source.sendSuccess(() -> {
                return Component.literal("Set " + player_name + "'s starter pokemon to: " + starterPokemon.getStarterPokemon() + ", Type: " + starterPokemon.getStarterPokemonType() + ", Dex number: " + starterPokemon.getStarterPokemonDex());
            }, true);
        }

        return 1;
    }

    private int CobblemonGymsStart(CommandSourceStack source, ServerPlayer pPlayer) {
        // Start selection
        GeneralPlayerData playerData = Cobblemon.playerDataManager.getGenericData(pPlayer);
        String player_name = pPlayer.getName().toString().replace("literal{", "").replace("}", "");

        if (!playerData.getStarterSelected()) {
            if (playerData.getStarterLocked()) {
                playerData.setStarterLocked(false);
                playerData.sendToPlayer(pPlayer);
            }
            playerData.setStarterPrompted(true);
            Cobblemon.playerDataManager.saveSingle(playerData, PlayerInstancedDataStoreTypes.INSTANCE.getGENERAL());

            PacketDistributor.sendToPlayer(pPlayer, new OpenStarterUIPacket(Cobblemon.INSTANCE.getStarterHandler().getStarterList(pPlayer)));
            source.sendSuccess(() -> {
                return Component.literal("Opened starter selection screen for " + player_name + ".");
            }, true);
        } else if (!pPlayer.getData(Attachments.PLAYER_WARP_HISTORY).getSpawned()) {
            GymDimension.randomSpawn(source.getServer(), pPlayer);
            pPlayer.getData(Attachments.PLAYER_WARP_HISTORY).setSpawned(true);
            source.sendSuccess(() -> {
                return Component.literal("Teleporting " + player_name + " out of the Gym Dimension to a random location.");
            }, true);
        } else {
            GymDimension.fromDimension(source.getServer(), pPlayer);
            source.sendSuccess(() -> {
                return Component.literal("Teleporting " + player_name + " out of the Gym Dimension.");
            }, true);
        }

        return 1;
    }
}
