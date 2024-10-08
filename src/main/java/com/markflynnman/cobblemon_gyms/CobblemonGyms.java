package com.markflynnman.cobblemon_gyms;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.api.events.starter.StarterChosenEvent;
import com.markflynnman.cobblemon_gyms.capabilities.PlayerBadgeCollection;
import com.markflynnman.cobblemon_gyms.capabilities.PlayerBadgeCollectionProvider;
import com.markflynnman.cobblemon_gyms.capabilities.PlayerStarterPokemon;
import com.markflynnman.cobblemon_gyms.capabilities.PlayerStarterPokemonProvider;
import com.markflynnman.cobblemon_gyms.commands.BadgeCollectionCommands;
import com.markflynnman.cobblemon_gyms.commands.CobblemonGymsCommands;
import com.markflynnman.cobblemon_gyms.items.GymBadges;
import com.markflynnman.cobblemon_gyms.items.ModCreativeModTabs;
import com.markflynnman.cobblemon_gyms.menus.ModMenuTypes;
import com.markflynnman.cobblemon_gyms.network.CBadgeCollectionDataSyncPacket;
import com.markflynnman.cobblemon_gyms.network.CStarterPokemonDataSyncPacket;
import com.markflynnman.cobblemon_gyms.network.PacketHandler;
import com.mojang.logging.LogUtils;
import kotlin.Unit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.server.command.ConfigCommand;
import org.slf4j.Logger;

@Mod(CobblemonGyms.MODID)
public class CobblemonGyms
{
    public static final String MODID = "cobblemon_gyms";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CobblemonGyms()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        GymBadges.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC, "CobblemonGyms-common.toml");

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

        event.getPlayer().getCapability(PlayerStarterPokemonProvider.PLAYER_STARTER_POKEMON).ifPresent(starterPokemon -> {
            starterPokemon.setStarterPokemon(event.getPokemon());
        });
        return Unit.INSTANCE;
    }

    @Mod.EventBusSubscriber(modid = MODID)
    public static class ModEvents {
        @SubscribeEvent
        public static void RegisterCommands(RegisterCommandsEvent event) {
            new CobblemonGymsCommands(event.getDispatcher());
            new BadgeCollectionCommands(event.getDispatcher());

            ConfigCommand.register(event.getDispatcher());
        }

        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player) {
                if (!event.getObject().getCapability(PlayerBadgeCollectionProvider.PLAYER_BADGE_COLLECTION).isPresent()) {
                    event.addCapability(new ResourceLocation(CobblemonGyms.MODID, "badge_collection"), new PlayerBadgeCollectionProvider());
                }
                if (!event.getObject().getCapability(PlayerStarterPokemonProvider.PLAYER_STARTER_POKEMON).isPresent()) {
                    event.addCapability(new ResourceLocation(CobblemonGyms.MODID, "starter_pokemon"), new PlayerStarterPokemonProvider());
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {
            if (event.isWasDeath()) {
                event.getOriginal().getCapability(PlayerBadgeCollectionProvider.PLAYER_BADGE_COLLECTION).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerBadgeCollectionProvider.PLAYER_BADGE_COLLECTION).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerStarterPokemonProvider.PLAYER_STARTER_POKEMON).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerStarterPokemonProvider.PLAYER_STARTER_POKEMON).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }

        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(PlayerBadgeCollection.class);
            event.register(PlayerStarterPokemon.class);
        }

        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
            if (!event.getLevel().isClientSide()) {
                if (event.getEntity() instanceof ServerPlayer player) {
                    player.getCapability(PlayerBadgeCollectionProvider.PLAYER_BADGE_COLLECTION).ifPresent(badgeCollection -> {
                        PacketHandler.sendToPlayer(new CBadgeCollectionDataSyncPacket(badgeCollection.getBadgeCollection()), player);
                    });
                }
                if (event.getEntity() instanceof ServerPlayer player) {
                    player.getCapability(PlayerStarterPokemonProvider.PLAYER_STARTER_POKEMON).ifPresent(starterPokemon -> {
                        PacketHandler.sendToPlayer(new CStarterPokemonDataSyncPacket(starterPokemon.getStarterPokemon(), starterPokemon.getStarterPokemonType(), starterPokemon.getStarterPokemonDex()), player);
                    });
                }
            }
        }
    }
}
