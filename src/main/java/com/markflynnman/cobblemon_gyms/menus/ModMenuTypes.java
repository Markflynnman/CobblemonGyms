package com.markflynnman.cobblemon_gyms.menus;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, CobblemonGyms.MODID);

    public static final RegistryObject<MenuType<CobblemonGymsMenu>> COBBLEMON_GYMS_MENU =
            registerMenuType("cobblemon_gyms_menu", CobblemonGymsMenu::new);
    public static final RegistryObject<MenuType<IndigoLeagueMenu>> INDIGO_LEAGUE_MENU =
            registerMenuType("indigo_league_menu", IndigoLeagueMenu::new);
    public static final RegistryObject<MenuType<JohtoLeagueMenu>> JOHTO_LEAGUE_MENU =
            registerMenuType("johto_league_menu", JohtoLeagueMenu::new);
    public static final RegistryObject<MenuType<HoennLeagueMenu>> HOENN_LEAGUE_MENU =
            registerMenuType("hoenn_league_menu", HoennLeagueMenu::new);
    public static final RegistryObject<MenuType<SinnohLeagueMenu>> SINNOH_LEAGUE_MENU =
            registerMenuType("sinnoh_league_menu", SinnohLeagueMenu::new);
    public static final RegistryObject<MenuType<UnovaLeagueMenu>> UNOVA_LEAGUE_MENU =
            registerMenuType("unova_league_menu", UnovaLeagueMenu::new);
    public static final RegistryObject<MenuType<KalosLeagueMenu>> KALOS_LEAGUE_MENU =
            registerMenuType("kalos_league_menu", KalosLeagueMenu::new);
    public static final RegistryObject<MenuType<GalarLeagueMenu>> GALAR_LEAGUE_MENU =
            registerMenuType("galar_league_menu", GalarLeagueMenu::new);
    public static final RegistryObject<MenuType<PaldeaLeagueMenu>> PALDEA_LEAGUE_MENU =
            registerMenuType("paldea_league_menu", PaldeaLeagueMenu::new);
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, MenuType.MenuSupplier menu) {
        return MENUS.register(name, () -> new MenuType(menu, FeatureFlags.DEFAULT_FLAGS));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
