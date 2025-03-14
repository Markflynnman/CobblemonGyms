package com.markflynnman.cobblemon_gyms;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.api.events.starter.StarterChosenEvent;
//import com.markflynnman.cobblemon_gyms.data_attachments.*;
//import com.markflynnman.cobblemon_gyms.commands.BadgeCollectionCommands;
//import com.markflynnman.cobblemon_gyms.commands.CobblemonGymsCommands;
import com.markflynnman.cobblemon_gyms.items.GymBadges;
import com.markflynnman.cobblemon_gyms.items.ModCreativeModTabs;
//import com.markflynnman.cobblemon_gyms.menus.ModMenuTypes;
//import com.markflynnman.cobblemon_gyms.network.CBadgeCollectionDataSyncPacket;
//import com.markflynnman.cobblemon_gyms.network.CStarterPokemonDataSyncPacket;
import com.mojang.logging.LogUtils;
import net.minecraft.server.level.ServerPlayer;
import kotlin.Unit;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.command.ConfigCommand;

import org.slf4j.Logger;

@Mod(CobblemonGyms.MODID)
public class CobblemonGyms
{
    public static final String MODID = "cobblemon_gyms";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CobblemonGyms(IEventBus modEventBus, ModContainer modContainer)
    {

        ModCreativeModTabs.register(modEventBus);
//        ModMenuTypes.register(modEventBus);
        GymBadges.register(modEventBus);
//        Attachments.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
//        NeoForge.EVENT_BUS.register(this);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC, "CobblemonGyms-common.toml");

        if (ModList.get().isLoaded("cobblemon")) {
            LOGGER.info("Cobblemon loaded.");

            CobblemonEvents.STARTER_CHOSEN.subscribe(Priority.NORMAL, this::onStarterChosenEvent);
        }
        else {
            LOGGER.info("Cobblemon not loaded.");
        }
    }

    public Unit onStarterChosenEvent(StarterChosenEvent event) {
        LOGGER.warn(String.valueOf(event.getPokemon().getSpecies()) + " : " + String.valueOf(event.getPokemon().getPrimaryType().getName()) + " : " + event.getPokemon().getSpecies().getNationalPokedexNumber());

//        event.getPlayer().getData(Attachments.PLAYER_STARTER_POKEMON.get()).setStarterPokemon(event.getPokemon());

//        event.getPlayer().getCapability(PLAYER_STARTER_POKEMON).ifPresent(starterPokemon -> {
//            starterPokemon.setStarterPokemon(event.getPokemon());
//        });
        return Unit.INSTANCE;
    }

    @EventBusSubscriber(modid = MODID)
    public static class ModEvents {
        @SubscribeEvent
        public static void RegisterCommands(RegisterCommandsEvent event) {
//            new CobblemonGymsCommands(event.getDispatcher());
//            new BadgeCollectionCommands(event.getDispatcher());

            ConfigCommand.register(event.getDispatcher());
        }

        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
//            if (!event.getLevel().isClientSide()) {
//                if (event.getEntity() instanceof ServerPlayer player) {
//                    if (player.hasData(Attachments.PLAYER_BADGE_COLLECTION)) {
//                        PacketDistributor.sendToPlayer(
//                                player,
//                                new CBadgeCollectionDataSyncPacket(PlayerBadgeCollection.toByteArray(player.getData(Attachments.PLAYER_BADGE_COLLECTION.get()).getBadgeCollection()))
//                        );
//                    }
////                    player.getCapability(PlayerBadgeCollectionProvider.PLAYER_BADGE_COLLECTION).ifPresent(badgeCollection -> {
////                        PacketHandler.sendToPlayer(new CBadgeCollectionDataSyncPacket(badgeCollection.getBadgeCollection()), player);
////                    });
//                }
//                if (event.getEntity() instanceof ServerPlayer player) {
//                    if (player.hasData(Attachments.PLAYER_STARTER_POKEMON)) {
//                        PlayerStarterPokemon starterPokemon = player.getData(Attachments.PLAYER_STARTER_POKEMON);
//                        PacketDistributor.sendToPlayer(
//                                player,
//                                new CStarterPokemonDataSyncPacket(starterPokemon.getStarterPokemon(), starterPokemon.getStarterPokemonType(), starterPokemon.getStarterPokemonDex())
//                        );
//                    }
////                    player.getCapability(PLAYER_STARTER_POKEMON).ifPresent(starterPokemon -> {
////                        PacketHandler.sendToPlayer(new CStarterPokemonDataSyncPacket(starterPokemon.getStarterPokemon(), starterPokemon.getStarterPokemonType(), starterPokemon.getStarterPokemonDex()), player);
////                    });
//                }
//            }
        }
    }
}
