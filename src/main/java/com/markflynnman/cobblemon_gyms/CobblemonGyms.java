package com.markflynnman.cobblemon_gyms;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.api.events.battles.BattleVictoryEvent;
import com.cobblemon.mod.common.api.events.starter.StarterChosenEvent;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.cobblemon.mod.common.platform.events.ServerEvent;
import com.gitlab.srcmc.rctapi.api.RCTApi;
import com.gitlab.srcmc.rctapi.api.models.TrainerModel;
import com.gitlab.srcmc.rctapi.api.trainer.TrainerRegistry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.markflynnman.cobblemon_gyms.data_attachments.*;
import com.markflynnman.cobblemon_gyms.commands.BadgeCollectionCommands;
import com.markflynnman.cobblemon_gyms.commands.CobblemonGymsCommands;
import com.markflynnman.cobblemon_gyms.gym_systems.GymBattleHandler;
import com.markflynnman.cobblemon_gyms.gym_systems.GymHandler;
import com.markflynnman.cobblemon_gyms.gym_systems.GymLeader;
import com.markflynnman.cobblemon_gyms.gym_systems.GymLeaderModel;
import com.markflynnman.cobblemon_gyms.items.GymBadges;
import com.markflynnman.cobblemon_gyms.items.ModCreativeModTabs;
import com.markflynnman.cobblemon_gyms.menus.ModMenuTypes;
import com.markflynnman.cobblemon_gyms.network.CBadgeCollectionDataSyncPacket;
import com.markflynnman.cobblemon_gyms.network.CStarterPokemonDataSyncPacket;
import com.markflynnman.cobblemon_gyms.network.PacketHandler;
import com.markflynnman.cobblemon_gyms.worldgen.dimension.GymDimension;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import kotlin.Unit;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.command.ConfigCommand;

import org.slf4j.Logger;

import java.io.*;
import java.util.UUID;

@Mod(CobblemonGyms.MODID)
public class CobblemonGyms
{
    public static final String MODID = "cobblemon_gyms";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final RCTApi RCT = RCTApi.getInstance(MODID);
    private static MinecraftServer server;

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    private static String fileToId(File file) {
        var name = file.getName().toLowerCase().trim();
        var i = name.lastIndexOf('.');
        return (i < 0 ? name : name.substring(0, i)).replace(' ', '_');
    }



    public CobblemonGyms(IEventBus modEventBus, ModContainer modContainer)
    {
        ModCreativeModTabs.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        GymBadges.register(modEventBus);
        Attachments.register(modEventBus);
        modEventBus.addListener(PacketHandler::register);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
//        NeoForge.EVENT_BUS.register(this);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC, "CobblemonGyms-common.toml");

        if (ModList.get().isLoaded("cobblemon")) {
            LOGGER.info(MODID + ": Cobblemon loaded.");

            CobblemonEvents.STARTER_CHOSEN.subscribe(Priority.NORMAL, this::onStarterChosenEvent);
            CobblemonEvents.BATTLE_VICTORY.subscribe(Priority.NORMAL, this::onBattleVictory);
        }
        else {
            LOGGER.info(MODID + ": Cobblemon not loaded.");
        }
    }

    public Unit onStarterChosenEvent(StarterChosenEvent event) {
        LOGGER.warn(String.valueOf(event.getPokemon().getSpecies()) + " : " + String.valueOf(event.getPokemon().getPrimaryType().getName()) + " : " + event.getPokemon().getSpecies().getNationalPokedexNumber());

        event.getPlayer().getData(Attachments.PLAYER_STARTER_POKEMON.get()).setStarterPokemon(event.getPokemon());

        return Unit.INSTANCE;
    }

    public Unit onBattleVictory(BattleVictoryEvent event) {
        if (event.getBattle().isPvN()) {
            GymBattleHandler.getINSTANCE().battleEnd(event.getWinners(), event.getLosers());
        }

        return Unit.INSTANCE;
    }

    @EventBusSubscriber(modid = MODID)
    public static class ModEvents {
        @SubscribeEvent
        public static void RegisterCommands(RegisterCommandsEvent event) {
            new CobblemonGymsCommands(event.getDispatcher());
            new BadgeCollectionCommands(event.getDispatcher());

            ConfigCommand.register(event.getDispatcher());
        }

        @SubscribeEvent
        public static void onServerStarting(ServerStartingEvent event) {
            MinecraftServer server = event.getServer();
            GymBattleHandler.getINSTANCE().setServer(server);

            GymHandler.initGyms(server.getLevel(GymDimension.COBBLEMON_GYMS_LEVEL_KEY));
            GymHandler.setGymAttendantUUID(UUID.randomUUID());
            GymHandler.setProfessorOakUUID(UUID.randomUUID());
            GymHandler.initHubNPCs(server);

            TrainerRegistry trainerRegistry = RCT.getTrainerRegistry();
            trainerRegistry.init(server);

            // Loading trainers
            ResourceManager resourceManager = server.getResourceManager();
            resourceManager.listPacks()
                    .filter(pack -> pack.getNamespaces(PackType.SERVER_DATA).contains(MODID))
                    .forEach(pack -> pack.listResources(PackType.SERVER_DATA, MODID, "trainers", (k,v) ->
                            registerTrainer(server, trainerRegistry, k, v)));
        }

        @SubscribeEvent
        public static void onServerStopping(ServerStoppingEvent event) {
            GymHandler.cleanNPCS(event.getServer());
        }

        public static void registerTrainer(MinecraftServer server, TrainerRegistry trainerRegistry, ResourceLocation resourceLocation, IoSupplier<InputStream> io) {
            String trainerID = resourceLocation.getPath().toLowerCase().trim();
            trainerID = trainerID.substring(trainerID.indexOf("/") +1);
            trainerID = trainerID.substring(0, trainerID.indexOf("."));
            trainerID = trainerID.trim();
            LOGGER.warn("Trainer Resource Location: " + trainerID);

            try {
                JsonObject jsonObject = GsonHelper.parse(new InputStreamReader(io.get()));
                trainerRegistry.registerNPC(trainerID, GSON.fromJson(jsonObject, TrainerModel.class));

                GymLeaderModel gymLeaderModel = GSON.fromJson(jsonObject, GymLeaderModel.class);
                GymHandler.addLeader(gymLeaderModel.getBadge(), new GymLeader(
                        gymLeaderModel.getName(),
                        gymLeaderModel.getNameNPC(),
                        gymLeaderModel.getBadge(),
                        gymLeaderModel.getBadgePath(),
                        gymLeaderModel.getModelType(),
                        ElementalTypes.INSTANCE.get(gymLeaderModel.getElementalType()),
                        trainerRegistry.getById(trainerID),
                        trainerID,
                        UUID.randomUUID()));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @SubscribeEvent
        public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
            ServerPlayer player = (ServerPlayer) event.getEntity();
            RCT.getTrainerRegistry().registerPlayer(player.getName().getString(), player);

            if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                if (!serverPlayer.getData(Attachments.PLAYER_WARP_HISTORY).getSpawned()) {
                    GymDimension.firstSpawn(serverPlayer.server, serverPlayer);
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
            ServerPlayer player = (ServerPlayer) event.getEntity();
            RCT.getTrainerRegistry().unregisterById(player.getName().getString());
        }

        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
            if (!event.getLevel().isClientSide()) {
                if (event.getEntity() instanceof ServerPlayer player) {
                    if (player.hasData(Attachments.PLAYER_BADGE_COLLECTION)) {
                        PacketDistributor.sendToPlayer(
                                player,
                                new CBadgeCollectionDataSyncPacket(player.getData(Attachments.PLAYER_BADGE_COLLECTION.get()).getBadgeCollection())
                        );
                    }
                    else {
                        // Init Badge Collection
                        player.getData(Attachments.PLAYER_BADGE_COLLECTION);
                        PacketDistributor.sendToPlayer(
                                player,
                                new CBadgeCollectionDataSyncPacket(player.getData(Attachments.PLAYER_BADGE_COLLECTION.get()).getBadgeCollection())
                        );
                    }
                }
                if (event.getEntity() instanceof ServerPlayer player) {
                    if (player.hasData(Attachments.PLAYER_STARTER_POKEMON)) {
                        PlayerStarterPokemon starterPokemon = player.getData(Attachments.PLAYER_STARTER_POKEMON);
                        PacketDistributor.sendToPlayer(
                                player,
                                new CStarterPokemonDataSyncPacket(starterPokemon.getStarterPokemon(), starterPokemon.getStarterPokemonType(), starterPokemon.getStarterPokemonDex())
                        );
                    }
                }
            }
        }
    }
}
