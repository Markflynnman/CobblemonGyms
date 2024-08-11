package com.markflynnman.cobblemon_gyms.menus;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.screens.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

public class CobblemonGymsMenuScreens {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Mod.EventBusSubscriber(modid = CobblemonGyms.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            MenuScreens.register(ModMenuTypes.COBBLEMON_GYMS_MENU.get(), CobblemonGymsGUI::new);
            MenuScreens.register(ModMenuTypes.INDIGO_LEAGUE_MENU.get(), IndigoLeagueGUI::new);
            MenuScreens.register(ModMenuTypes.JOHTO_LEAGUE_MENU.get(), JohtoLeagueGUI::new);
            MenuScreens.register(ModMenuTypes.HOENN_LEAGUE_MENU.get(), HoennLeagueGUI::new);
            MenuScreens.register(ModMenuTypes.SINNOH_LEAGUE_MENU.get(), SinnohLeagueGUI::new);
            MenuScreens.register(ModMenuTypes.UNOVA_LEAGUE_MENU.get(), UnovaLeagueGUI::new);
            MenuScreens.register(ModMenuTypes.KALOS_LEAGUE_MENU.get(), KalosLeagueGUI::new);
            MenuScreens.register(ModMenuTypes.GALAR_LEAGUE_MENU.get(), GalarLeagueGUI::new);
            MenuScreens.register(ModMenuTypes.PALDEA_LEAGUE_MENU.get(), PaldeaLeagueGUI::new);
        }
    }
}
