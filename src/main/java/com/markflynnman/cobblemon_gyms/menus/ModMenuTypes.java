package com.markflynnman.cobblemon_gyms.menus;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, CobblemonGyms.MODID);

    public static final Supplier<MenuType<CobblemonGymsMenu>> COBBLEMON_GYMS_MENU =
            registerMenuType("cobblemon_gyms_menu", CobblemonGymsMenu::new);
    public static final Supplier<MenuType<IndigoLeagueMenu>> INDIGO_LEAGUE_MENU =
            registerMenuType("indigo_league_menu", IndigoLeagueMenu::new);
    public static final Supplier<MenuType<JohtoLeagueMenu>> JOHTO_LEAGUE_MENU =
            registerMenuType("johto_league_menu", JohtoLeagueMenu::new);
    public static final Supplier<MenuType<HoennLeagueMenu>> HOENN_LEAGUE_MENU =
            registerMenuType("hoenn_league_menu", HoennLeagueMenu::new);
    public static final Supplier<MenuType<SinnohLeagueMenu>> SINNOH_LEAGUE_MENU =
            registerMenuType("sinnoh_league_menu", SinnohLeagueMenu::new);
    public static final Supplier<MenuType<UnovaLeagueMenu>> UNOVA_LEAGUE_MENU =
            registerMenuType("unova_league_menu", UnovaLeagueMenu::new);
    public static final Supplier<MenuType<KalosLeagueMenu>> KALOS_LEAGUE_MENU =
            registerMenuType("kalos_league_menu", KalosLeagueMenu::new);
    public static final Supplier<MenuType<GalarLeagueMenu>> GALAR_LEAGUE_MENU =
            registerMenuType("galar_league_menu", GalarLeagueMenu::new);
    public static final Supplier<MenuType<PaldeaLeagueMenu>> PALDEA_LEAGUE_MENU =
            registerMenuType("paldea_league_menu", PaldeaLeagueMenu::new);

    private static <T extends AbstractContainerMenu>Supplier<MenuType<T>> registerMenuType(String name, MenuType.MenuSupplier menu) {
        return MENUS.register(name, () -> new MenuType(menu, FeatureFlags.DEFAULT_FLAGS));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
