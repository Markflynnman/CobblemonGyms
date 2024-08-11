package com.markflynnman.cobblemon_gyms.items;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GymBadges {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CobblemonGyms.MODID);

    // Indigo
    public static final RegistryObject<Item> BOULDER_BADGE = registerItem("boulder_badge", "indigo");
    public static final RegistryObject<Item> CASCADE_BADGE = registerItem("cascade_badge", "indigo");
    public static final RegistryObject<Item> THUNDER_BADGE = registerItem("thunder_badge", "indigo");
    public static final RegistryObject<Item> RAINBOW_BADGE = registerItem("rainbow_badge", "indigo");
    public static final RegistryObject<Item> SOUL_BADGE = registerItem("soul_badge", "indigo");
    public static final RegistryObject<Item> MARSH_BADGE = registerItem("marsh_badge", "indigo");
    public static final RegistryObject<Item> VOLCANO_BADGE = registerItem("volcano_badge", "indigo");
    public static final RegistryObject<Item> EARTH_BADGE = registerItem("earth_badge", "indigo");

    // Johto
    public static final RegistryObject<Item> ZEPHYR_BADGE = registerItem("zephyr_badge", "johto");
    public static final RegistryObject<Item> HIVE_BADGE = registerItem("hive_badge", "johto");
    public static final RegistryObject<Item> PLAIN_BADGE = registerItem("plain_badge", "johto");
    public static final RegistryObject<Item> FOG_BADGE = registerItem("fog_badge", "johto");
    public static final RegistryObject<Item> STORM_BADGE = registerItem("storm_badge", "johto");
    public static final RegistryObject<Item> MINERAL_BADGE = registerItem("mineral_badge", "johto");
    public static final RegistryObject<Item> GLACIER_BADGE = registerItem("glacier_badge", "johto");
    public static final RegistryObject<Item> RISING_BADGE = registerItem("rising_badge", "johto");

    // Hoenn
    public static final RegistryObject<Item> STONE_BADGE = registerItem("stone_badge", "hoenn");
    public static final RegistryObject<Item> KNUCKLE_BADGE = registerItem("knuckle_badge", "hoenn");
    public static final RegistryObject<Item> DYNAMO_BADGE = registerItem("dynamo_badge", "hoenn");
    public static final RegistryObject<Item> HEAT_BADGE = registerItem("heat_badge", "hoenn");
    public static final RegistryObject<Item> BALANCE_BADGE = registerItem("balance_badge", "hoenn");
    public static final RegistryObject<Item> FEATHER_BADGE = registerItem("feather_badge", "hoenn");
    public static final RegistryObject<Item> MIND_BADGE = registerItem("mind_badge", "hoenn");
    public static final RegistryObject<Item> RAIN_BADGE = registerItem("rain_badge", "hoenn");

    // Sinnoh
    public static final RegistryObject<Item> COAL_BADGE = registerItem("coal_badge", "sinnoh");
    public static final RegistryObject<Item> FOREST_BADGE = registerItem("forest_badge", "sinnoh");
    public static final RegistryObject<Item> COBBLE_BADGE = registerItem("cobble_badge", "sinnoh");
    public static final RegistryObject<Item> FEN_BADGE = registerItem("fen_badge", "sinnoh");
    public static final RegistryObject<Item> RELIC_BADGE = registerItem("relic_badge", "sinnoh");
    public static final RegistryObject<Item> MINE_BADGE = registerItem("mine_badge", "sinnoh");
    public static final RegistryObject<Item> ICICLE_BADGE = registerItem("icicle_badge", "sinnoh");
    public static final RegistryObject<Item> BEACON_BADGE = registerItem("beacon_badge", "sinnoh");

    // Unova
    public static final RegistryObject<Item> TRIO_BADGE = registerItem("trio_badge", "unova");
    public static final RegistryObject<Item> BASIC_BADGE = registerItem("basic_badge", "unova");
    public static final RegistryObject<Item> TOXIC_BADGE = registerItem("toxic_badge", "unova");
    public static final RegistryObject<Item> INSECT_BADGE = registerItem("insect_badge", "unova");
    public static final RegistryObject<Item> BOLT_BADGE = registerItem("bolt_badge", "unova");
    public static final RegistryObject<Item> QUAKE_BADGE = registerItem("quake_badge", "unova");
    public static final RegistryObject<Item> JET_BADGE = registerItem("jet_badge", "unova");
    public static final RegistryObject<Item> FREEZE_BADGE = registerItem("freeze_badge", "unova");
    public static final RegistryObject<Item> LEGEND_BADGE = registerItem("legend_badge", "unova");
    public static final RegistryObject<Item> WAVE_BADGE = registerItem("wave_badge", "unova");

    // Kalos
    public static final RegistryObject<Item> BUG_BADGE = registerItem("bug_badge", "kalos");
    public static final RegistryObject<Item> CLIFF_BADGE = registerItem("cliff_badge", "kalos");
    public static final RegistryObject<Item> RUMBLE_BADGE = registerItem("rumble_badge", "kalos");
    public static final RegistryObject<Item> PLANT_BADGE = registerItem("plant_badge", "kalos");
    public static final RegistryObject<Item> VOLTAGE_BADGE = registerItem("voltage_badge", "kalos");
    public static final RegistryObject<Item> FAIRY_BADGE = registerItem("fairy_badge", "kalos");
    public static final RegistryObject<Item> PSYCHIC_BADGE = registerItem("psychic_badge", "kalos");
    public static final RegistryObject<Item> ICEBERG_BADGE = registerItem("iceberg_badge", "kalos");

    // Galar
    public static final RegistryObject<Item> SAS_GRASS_BADGE = registerItem("sas_grass_badge", "galar");
    public static final RegistryObject<Item> SAS_WATER_BADGE = registerItem("sas_water_badge", "galar");
    public static final RegistryObject<Item> SAS_FIRE_BADGE = registerItem("sas_fire_badge", "galar");
    public static final RegistryObject<Item> SAS_FIGHTING_BADGE = registerItem("sas_fighting_badge", "galar");
    public static final RegistryObject<Item> SAS_GHOST_BADGE = registerItem("sas_ghost_badge", "galar");
    public static final RegistryObject<Item> SAS_FAIRY_BADGE = registerItem("sas_fairy_badge", "galar");
    public static final RegistryObject<Item> SAS_ROCK_BADGE = registerItem("sas_rock_badge", "galar");
    public static final RegistryObject<Item> SAS_ICE_BADGE = registerItem("sas_ice_badge", "galar");
    public static final RegistryObject<Item> SAS_DARK_BADGE = registerItem("sas_dark_badge", "galar");
    public static final RegistryObject<Item> SAS_DRAGON_BADGE = registerItem("sas_dragon_badge", "galar");

    // Paldea
    // Gym
    public static final RegistryObject<Item> SV_BUG_BADGE = registerItem("sv_bug_badge", "paldea");
    public static final RegistryObject<Item> SV_GRASS_BADGE = registerItem("sv_grass_badge", "paldea");
    public static final RegistryObject<Item> SV_ELECTRIC_BADGE = registerItem("sv_electric_badge", "paldea");
    public static final RegistryObject<Item> SV_WATER_BADGE = registerItem("sv_water_badge", "paldea");
    public static final RegistryObject<Item> SV_NORMAL_BADGE = registerItem("sv_normal_badge", "paldea");
    public static final RegistryObject<Item> SV_GHOST_BADGE = registerItem("sv_ghost_badge", "paldea");
    public static final RegistryObject<Item> SV_PSYCHIC_BADGE = registerItem("sv_psychic_badge", "paldea");
    public static final RegistryObject<Item> SV_ICE_BADGE = registerItem("sv_ice_badge", "paldea");
    // Starfall Street
    public static final RegistryObject<Item> SV_DARK_BADGE = registerItem("sv_dark_badge", "paldea");
    public static final RegistryObject<Item> SV_FIRE_BADGE = registerItem("sv_fire_badge", "paldea");
    public static final RegistryObject<Item> SV_POISON_BADGE = registerItem("sv_poison_badge", "paldea");
    public static final RegistryObject<Item> SV_FAIRY_BADGE = registerItem("sv_fairy_badge", "paldea");
    public static final RegistryObject<Item> SV_FIGHTING_BADGE = registerItem("sv_fighting_badge", "paldea");
    // Titan
    public static final RegistryObject<Item> SV_ROCK_BADGE = registerItem("sv_rock_badge", "paldea");
    public static final RegistryObject<Item> SV_FLYING_BADGE = registerItem("sv_flying_badge", "paldea");
    public static final RegistryObject<Item> SV_STEEL_BADGE = registerItem("sv_steel_badge", "paldea");
    public static final RegistryObject<Item> SV_GROUND_BADGE = registerItem("sv_ground_badge", "paldea");
    public static final RegistryObject<Item> SV_DRAGON_BADGE = registerItem("sv_dragon_badge", "paldea");

    private static RegistryObject<Item> registerItem(String name, String league) {
        return ITEMS.register(name, () -> new GymBadgeItem(new Item.Properties().fireResistant().stacksTo(1), league));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
