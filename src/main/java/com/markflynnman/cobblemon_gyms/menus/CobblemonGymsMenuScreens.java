package com.markflynnman.cobblemon_gyms.menus;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.screens.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.slf4j.Logger;

public class CobblemonGymsMenuScreens {
    private static final Logger LOGGER = LogUtils.getLogger();

    @EventBusSubscriber(modid = CobblemonGyms.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }

        @SubscribeEvent
        public static void registerMenuScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.COBBLEMON_GYMS_MENU.get(), CobblemonGymsGUI::new);
            event.register(ModMenuTypes.INDIGO_LEAGUE_MENU.get(), IndigoLeagueGUI::new);
            event.register(ModMenuTypes.JOHTO_LEAGUE_MENU.get(), JohtoLeagueGUI::new);
            event.register(ModMenuTypes.HOENN_LEAGUE_MENU.get(), HoennLeagueGUI::new);
            event.register(ModMenuTypes.SINNOH_LEAGUE_MENU.get(), SinnohLeagueGUI::new);
            event.register(ModMenuTypes.UNOVA_LEAGUE_MENU.get(), UnovaLeagueGUI::new);
            event.register(ModMenuTypes.KALOS_LEAGUE_MENU.get(), KalosLeagueGUI::new);
            event.register(ModMenuTypes.GALAR_LEAGUE_MENU.get(), GalarLeagueGUI::new);
            event.register(ModMenuTypes.PALDEA_LEAGUE_MENU.get(), PaldeaLeagueGUI::new);
        }
    }
}
