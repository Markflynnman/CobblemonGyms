package com.markflynnman.cobblemon_gyms;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.*;


public class Config {
    public static final ForgeConfigSpec CONFIG_SPEC;
    public static final String DEFAULT_GYM_COMMAND = "say Setup gym command in the config or by typing \"/GymCommands set [gym] [command]\".";
    public static final Map<String, ForgeConfigSpec.ConfigValue<String>> GymConfigCommands = new HashMap<>();

    private static ForgeConfigSpec.IntValue gymGUICommand;
    private static ForgeConfigSpec.IntValue gymGUICommandOther;
    private static ForgeConfigSpec.IntValue badgesCommand;
    private static ForgeConfigSpec.IntValue badgesCommandOther;
    private static ForgeConfigSpec.IntValue badgesAddCommand;
    private static ForgeConfigSpec.IntValue badgesRemoveCommand;
    private static ForgeConfigSpec.IntValue badgesDevCommand;
    private static ForgeConfigSpec.IntValue gymCommandsSetCommand;

    private static ForgeConfigSpec.ConfigValue<String> indigoBoulderGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> indigoCascadeGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> indigoThunderGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> indigoRainbowGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> indigoSoulGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> indigoMarshGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> indigoVolcanoGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> indigoEarthGymCommand;

    private static ForgeConfigSpec.ConfigValue<String> johtoZephyrGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> johtoHiveGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> johtoPlainGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> johtoFogGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> johtoStormGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> johtoMineralGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> johtoGlacierGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> johtoRisingGymCommand;

    private static ForgeConfigSpec.ConfigValue<String> hoennStoneGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> hoennKnuckleGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> hoennDynamoGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> hoennHeatGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> hoennBalanceGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> hoennFeatherGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> hoennMindGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> hoennRainGymCommand;

    private static ForgeConfigSpec.ConfigValue<String> sinnohCoalGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> sinnohForestGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> sinnohCobbleGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> sinnohFenGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> sinnohRelicGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> sinnohMineGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> sinnohIcicleGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> sinnohBeaconGymCommand;

    private static ForgeConfigSpec.ConfigValue<String> unovaTrioGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> unovaBasicGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> unovaToxicGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> unovaInsectGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> unovaBoltGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> unovaQuakeGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> unovaJetGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> unovaFreezeGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> unovaLegendGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> unovaWaveGymCommand;

    private static ForgeConfigSpec.ConfigValue<String> kalosBugGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> kalosCliffGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> kalosRumbleGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> kalosPlantGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> kalosVoltageGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> kalosFairyGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> kalosPsychicGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> kalosIcebergGymCommand;

    private static ForgeConfigSpec.ConfigValue<String> galarGrassGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> galarWaterGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> galarFireGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> galarFightingGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> galarGhostGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> galarFairyGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> galarRockGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> galarIceGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> galarDarkGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> galarDragonGymCommand;

    private static ForgeConfigSpec.ConfigValue<String> paldeaBugGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaGrassGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaElectricGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaWaterGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaNormalGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaGhostGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaPsychicGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaIceGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaDarkGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaFireGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaPoisonGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaFairyGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaFightingGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaRockGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaFlyingGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaSteelGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaGroundGymCommand;
    private static ForgeConfigSpec.ConfigValue<String> paldeaDragonGymCommand;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        setupConfig(builder);
        setupCommandMap();
        CONFIG_SPEC = builder.build();
    }

    private static void setupConfig(ForgeConfigSpec.Builder builder) {
        builder.push("CobblemonGyms");
        builder.comment("Required permission levels for commands. [0 (all), 1 (moderator), 2 (gamemaster), 3 (admin), and 4 (owner)]");
        builder.push("Commands");
        builder.comment("\"/GymGUI\" command");
        gymGUICommand = builder.defineInRange("gym_gui_command", 3, 0, 4);
        builder.comment("\"/GymGUI {player}\" command");
        gymGUICommandOther = builder.defineInRange("gym_gui_command_other", 4, 0, 4);
        builder.comment("\"/badges\" command");
        badgesCommand = builder.defineInRange("badges_command", 0, 0, 4);
        builder.comment("\"/badges {player}\" command");
        badgesCommandOther = builder.defineInRange("badges_command_other", 0, 0, 4);
        builder.comment("\"/badges {player} add {badge}\" command");
        badgesAddCommand = builder.defineInRange("badges_add_command", 3, 0, 4);
        builder.comment("\"/badges {player} remove {badge}\" command");
        badgesRemoveCommand = builder.defineInRange("badges_remove_command", 3, 0, 4);
        builder.comment("\"/GymCommands set {command}\" command (This command could be used maliciously. Such as using \"/stop\" or \"/op {player}\" DO NOT SET THIS LOWER unless you know what your doing!!!)");
        gymCommandsSetCommand = builder.defineInRange("gym_commands_set_command", 4, 0, 4);
        builder.pop();
        builder.comment("Commands to run when a gym is selected in the GUI.");
        builder.push("GUI_Commands");
        builder.push("Indigo");
        indigoBoulderGymCommand = builder.define("indigo_boulder_gym_command", DEFAULT_GYM_COMMAND);
        indigoCascadeGymCommand = builder.define("indigo_cascade_gym_command", DEFAULT_GYM_COMMAND);
        indigoThunderGymCommand = builder.define("indigo_thunder_gym_command", DEFAULT_GYM_COMMAND);
        indigoRainbowGymCommand = builder.define("indigo_rainbow_gym_command", DEFAULT_GYM_COMMAND);
        indigoSoulGymCommand = builder.define("indigo_soul_gym_command", DEFAULT_GYM_COMMAND);
        indigoMarshGymCommand = builder.define("indigo_marsh_gym_command", DEFAULT_GYM_COMMAND);
        indigoVolcanoGymCommand = builder.define("indigo_volcano_gym_command", DEFAULT_GYM_COMMAND);
        indigoEarthGymCommand = builder.define("indigo_earth_gym_command", DEFAULT_GYM_COMMAND);
        builder.pop();
        builder.push("Johto");
        johtoZephyrGymCommand = builder.define("johto_zephyr_gym_command", DEFAULT_GYM_COMMAND);
        johtoHiveGymCommand = builder.define("johto_hive_gym_command", DEFAULT_GYM_COMMAND);
        johtoPlainGymCommand = builder.define("johto_plain_gym_command", DEFAULT_GYM_COMMAND);
        johtoFogGymCommand = builder.define("johto_fog_gym_command", DEFAULT_GYM_COMMAND);
        johtoStormGymCommand = builder.define("johto_storm_gym_command", DEFAULT_GYM_COMMAND);
        johtoMineralGymCommand = builder.define("johto_mineral_gym_command", DEFAULT_GYM_COMMAND);
        johtoGlacierGymCommand = builder.define("johto_glacier_gym_command", DEFAULT_GYM_COMMAND);
        johtoRisingGymCommand = builder.define("johto_rising_gym_command", DEFAULT_GYM_COMMAND);
        builder.pop();
        builder.push("Hoenn");
        hoennStoneGymCommand = builder.define("hoenn_stone_gym_command", DEFAULT_GYM_COMMAND);
        hoennKnuckleGymCommand = builder.define("hoenn_knuckle_gym_command", DEFAULT_GYM_COMMAND);
        hoennDynamoGymCommand = builder.define("hoenn_dynamo_gym_command", DEFAULT_GYM_COMMAND);
        hoennHeatGymCommand = builder.define("hoenn_heat_gym_command", DEFAULT_GYM_COMMAND);
        hoennBalanceGymCommand = builder.define("hoenn_balance_gym_command", DEFAULT_GYM_COMMAND);
        hoennFeatherGymCommand = builder.define("hoenn_feather_gym_command", DEFAULT_GYM_COMMAND);
        hoennMindGymCommand = builder.define("hoenn_mind_gym_command", DEFAULT_GYM_COMMAND);
        hoennRainGymCommand = builder.define("hoenn_rain_gym_command", DEFAULT_GYM_COMMAND);
        builder.pop();
        builder.push("Sinnoh");
        sinnohCoalGymCommand = builder.define("sinnoh_coal_gym_command", DEFAULT_GYM_COMMAND);
        sinnohForestGymCommand = builder.define("sinnoh_forest_gym_command", DEFAULT_GYM_COMMAND);
        sinnohCobbleGymCommand = builder.define("sinnoh_cobble_gym_command", DEFAULT_GYM_COMMAND);
        sinnohFenGymCommand = builder.define("sinnoh_fen_gym_command", DEFAULT_GYM_COMMAND);
        sinnohRelicGymCommand = builder.define("sinnoh_relic_gym_command", DEFAULT_GYM_COMMAND);
        sinnohMineGymCommand = builder.define("sinnoh_mine_gym_command", DEFAULT_GYM_COMMAND);
        sinnohIcicleGymCommand = builder.define("sinnoh_icicle_gym_command", DEFAULT_GYM_COMMAND);
        sinnohBeaconGymCommand = builder.define("sinnoh_beacon_gym_command", DEFAULT_GYM_COMMAND);
        builder.pop();
        builder.push("Unova");
        unovaTrioGymCommand = builder.define("unova_trio_gym_command", DEFAULT_GYM_COMMAND);
        unovaBasicGymCommand = builder.define("unova_basic_gym_command", DEFAULT_GYM_COMMAND);
        unovaToxicGymCommand = builder.define("unova_toxic_gym_command", DEFAULT_GYM_COMMAND);
        unovaInsectGymCommand = builder.define("unova_insect_gym_command", DEFAULT_GYM_COMMAND);
        unovaBoltGymCommand = builder.define("unova_bolt_gym_command", DEFAULT_GYM_COMMAND);
        unovaQuakeGymCommand = builder.define("unova_quake_gym_command", DEFAULT_GYM_COMMAND);
        unovaJetGymCommand = builder.define("unova_jet_gym_command", DEFAULT_GYM_COMMAND);
        unovaFreezeGymCommand = builder.define("unova_freeze_gym_command", DEFAULT_GYM_COMMAND);
        unovaLegendGymCommand = builder.define("unova_legend_gym_command", DEFAULT_GYM_COMMAND);
        unovaWaveGymCommand = builder.define("unova_wave_gym_command", DEFAULT_GYM_COMMAND);
        builder.pop();
        builder.push("Kalos");
        kalosBugGymCommand = builder.define("kalos_bug_gym_command", DEFAULT_GYM_COMMAND);
        kalosCliffGymCommand = builder.define("kalos_cliff_gym_command", DEFAULT_GYM_COMMAND);
        kalosRumbleGymCommand = builder.define("kalos_rumble_gym_command", DEFAULT_GYM_COMMAND);
        kalosPlantGymCommand = builder.define("kalos_plant_gym_command", DEFAULT_GYM_COMMAND);
        kalosVoltageGymCommand = builder.define("kalos_voltage_gym_command", DEFAULT_GYM_COMMAND);
        kalosFairyGymCommand = builder.define("kalos_fairy_gym_command", DEFAULT_GYM_COMMAND);
        kalosPsychicGymCommand = builder.define("kalos_psychic_gym_command", DEFAULT_GYM_COMMAND);
        kalosIcebergGymCommand = builder.define("kalos_iceberg_gym_command", DEFAULT_GYM_COMMAND);
        builder.pop();
        builder.push("Galar");
        galarGrassGymCommand = builder.define("galar_grass_gym_command", DEFAULT_GYM_COMMAND);
        galarWaterGymCommand = builder.define("galar_water_gym_command", DEFAULT_GYM_COMMAND);
        galarFireGymCommand = builder.define("galar_fire_gym_command", DEFAULT_GYM_COMMAND);
        galarFightingGymCommand = builder.define("galar_fighting_gym_command", DEFAULT_GYM_COMMAND);
        galarGhostGymCommand = builder.define("galar_ghost_gym_command", DEFAULT_GYM_COMMAND);
        galarFairyGymCommand = builder.define("galar_fairy_gym_command", DEFAULT_GYM_COMMAND);
        galarRockGymCommand = builder.define("galar_rock_gym_command", DEFAULT_GYM_COMMAND);
        galarIceGymCommand = builder.define("galar_ice_gym_command", DEFAULT_GYM_COMMAND);
        galarDarkGymCommand = builder.define("galar_dark_gym_command", DEFAULT_GYM_COMMAND);
        galarDragonGymCommand = builder.define("galar_dragon_gym_command", DEFAULT_GYM_COMMAND);
        builder.pop();
        builder.push("Paldea");
        paldeaBugGymCommand = builder.define("paldea_bug_gym_command", DEFAULT_GYM_COMMAND);
        paldeaGrassGymCommand = builder.define("paldea_grass_gym_command", DEFAULT_GYM_COMMAND);
        paldeaElectricGymCommand = builder.define("paldea_electric_gym_command", DEFAULT_GYM_COMMAND);
        paldeaWaterGymCommand = builder.define("paldea_water_gym_command", DEFAULT_GYM_COMMAND);
        paldeaNormalGymCommand = builder.define("paldea_normal_gym_command", DEFAULT_GYM_COMMAND);
        paldeaGhostGymCommand = builder.define("paldea_ghost_gym_command", DEFAULT_GYM_COMMAND);
        paldeaPsychicGymCommand = builder.define("paldea_psychic_gym_command", DEFAULT_GYM_COMMAND);
        paldeaIceGymCommand = builder.define("paldea_ice_gym_command", DEFAULT_GYM_COMMAND);
        paldeaDarkGymCommand = builder.define("paldea_dark_gym_command", DEFAULT_GYM_COMMAND);
        paldeaFireGymCommand = builder.define("paldea_fire_gym_command", DEFAULT_GYM_COMMAND);
        paldeaPoisonGymCommand = builder.define("paldea_poison_gym_command", DEFAULT_GYM_COMMAND);
        paldeaFairyGymCommand = builder.define("paldea_fairy_gym_command", DEFAULT_GYM_COMMAND);
        paldeaFightingGymCommand = builder.define("paldea_fighting_gym_command", DEFAULT_GYM_COMMAND);
        paldeaRockGymCommand = builder.define("paldea_rock_gym_command", DEFAULT_GYM_COMMAND);
        paldeaFlyingGymCommand = builder.define("paldea_flying_gym_command", DEFAULT_GYM_COMMAND);
        paldeaSteelGymCommand = builder.define("paldea_steel_gym_command", DEFAULT_GYM_COMMAND);
        paldeaGroundGymCommand = builder.define("paldea_ground_gym_command", DEFAULT_GYM_COMMAND);
        paldeaDragonGymCommand = builder.define("paldea_dragon_gym_command", DEFAULT_GYM_COMMAND);
        builder.pop();
        builder.pop();
        builder.push("Dev");
        builder.comment("\"/badges {player} dev\" command. (displays PlayerBadgeCollection array... only useful for debugging)");
        badgesDevCommand = builder.defineInRange("badges_dev_command", 4, 0, 4);
        builder.pop();
    }

    public static int getGymGUICommand() { return gymGUICommand.get(); }
    public static int getGymGUICommandOther() { return gymGUICommandOther.get(); }
    public static int getBadgesCommand() { return badgesCommand.get(); }
    public static int getBadgesCommandOther() { return badgesCommandOther.get(); }
    public static int getBadgesAddCommand() { return badgesAddCommand.get(); }
    public static int getBadgesRemoveCommand() { return badgesRemoveCommand.get(); }
    public static int getBadgesDevCommand() { return badgesDevCommand.get(); }
    public static int getGymCommandsSetCommand() { return gymCommandsSetCommand.get(); }

    static void setupCommandMap() {
        GymConfigCommands.put("Indigo_Boulder", indigoBoulderGymCommand);
        GymConfigCommands.put("Indigo_Cascade", indigoCascadeGymCommand);
        GymConfigCommands.put("Indigo_Thunder", indigoThunderGymCommand);
        GymConfigCommands.put("Indigo_Rainbow", indigoRainbowGymCommand);
        GymConfigCommands.put("Indigo_Soul", indigoSoulGymCommand);
        GymConfigCommands.put("Indigo_Marsh", indigoMarshGymCommand);
        GymConfigCommands.put("Indigo_Volcano", indigoVolcanoGymCommand);
        GymConfigCommands.put("Indigo_Earth", indigoEarthGymCommand);

        GymConfigCommands.put("Johto_Zephyr", johtoZephyrGymCommand);
        GymConfigCommands.put("Johto_Hive", johtoHiveGymCommand);
        GymConfigCommands.put("Johto_Plain", johtoPlainGymCommand);
        GymConfigCommands.put("Johto_Fog", johtoFogGymCommand);
        GymConfigCommands.put("Johto_Storm", johtoStormGymCommand);
        GymConfigCommands.put("Johto_Mineral", johtoMineralGymCommand);
        GymConfigCommands.put("Johto_Glacier", johtoGlacierGymCommand);
        GymConfigCommands.put("Johto_Rising", johtoRisingGymCommand);

        GymConfigCommands.put("Hoenn_Stone", hoennStoneGymCommand);
        GymConfigCommands.put("Hoenn_Knuckle", hoennKnuckleGymCommand);
        GymConfigCommands.put("Hoenn_Dynamo", hoennDynamoGymCommand);
        GymConfigCommands.put("Hoenn_Heat", hoennHeatGymCommand);
        GymConfigCommands.put("Hoenn_Balance", hoennBalanceGymCommand);
        GymConfigCommands.put("Hoenn_Feather", hoennFeatherGymCommand);
        GymConfigCommands.put("Hoenn_Mind", hoennMindGymCommand);
        GymConfigCommands.put("Hoenn_Rain", hoennRainGymCommand);

        GymConfigCommands.put("Sinnoh_Coal", sinnohCoalGymCommand);
        GymConfigCommands.put("Sinnoh_Forest", sinnohForestGymCommand);
        GymConfigCommands.put("Sinnoh_Cobble", sinnohCobbleGymCommand);
        GymConfigCommands.put("Sinnoh_Fen", sinnohFenGymCommand);
        GymConfigCommands.put("Sinnoh_Relic", sinnohRelicGymCommand);
        GymConfigCommands.put("Sinnoh_Mine", sinnohMineGymCommand);
        GymConfigCommands.put("Sinnoh_Icicle", sinnohIcicleGymCommand);
        GymConfigCommands.put("Sinnoh_Beacon", sinnohBeaconGymCommand);

        GymConfigCommands.put("Unova_Trio", unovaTrioGymCommand);
        GymConfigCommands.put("Unova_Basic", unovaBasicGymCommand);
        GymConfigCommands.put("Unova_Toxic", unovaToxicGymCommand);
        GymConfigCommands.put("Unova_Insect", unovaInsectGymCommand);
        GymConfigCommands.put("Unova_Bolt", unovaBoltGymCommand);
        GymConfigCommands.put("Unova_Quake", unovaQuakeGymCommand);
        GymConfigCommands.put("Unova_Jet", unovaJetGymCommand);
        GymConfigCommands.put("Unova_Freeze", unovaFreezeGymCommand);
        GymConfigCommands.put("Unova_Legend", unovaLegendGymCommand);
        GymConfigCommands.put("Unova_Wave", unovaWaveGymCommand);

        GymConfigCommands.put("Kalos_Bug", kalosBugGymCommand);
        GymConfigCommands.put("Kalos_Cliff", kalosCliffGymCommand);
        GymConfigCommands.put("Kalos_Rumble", kalosRumbleGymCommand);
        GymConfigCommands.put("Kalos_Plant", kalosPlantGymCommand);
        GymConfigCommands.put("Kalos_Voltage", kalosVoltageGymCommand);
        GymConfigCommands.put("Kalos_Fairy", kalosFairyGymCommand);
        GymConfigCommands.put("Kalos_Psychic", kalosPsychicGymCommand);
        GymConfigCommands.put("Kalos_Iceberg", kalosIcebergGymCommand);

        GymConfigCommands.put("Galar_Grass", galarGrassGymCommand);
        GymConfigCommands.put("Galar_Water", galarWaterGymCommand);
        GymConfigCommands.put("Galar_Fire", galarFireGymCommand);
        GymConfigCommands.put("Galar_Fighting", galarFightingGymCommand);
        GymConfigCommands.put("Galar_Ghost", galarGhostGymCommand);
        GymConfigCommands.put("Galar_Fairy", galarFairyGymCommand);
        GymConfigCommands.put("Galar_Rock", galarRockGymCommand);
        GymConfigCommands.put("Galar_Ice", galarIceGymCommand);
        GymConfigCommands.put("Galar_Dark", galarDarkGymCommand);
        GymConfigCommands.put("Galar_Dragon", galarDragonGymCommand);

        GymConfigCommands.put("Paldea_Bug", paldeaBugGymCommand);
        GymConfigCommands.put("Paldea_Grass", paldeaGrassGymCommand);
        GymConfigCommands.put("Paldea_Electric", paldeaElectricGymCommand);
        GymConfigCommands.put("Paldea_Water", paldeaWaterGymCommand);
        GymConfigCommands.put("Paldea_Normal", paldeaNormalGymCommand);
        GymConfigCommands.put("Paldea_Ghost", paldeaGhostGymCommand);
        GymConfigCommands.put("Paldea_Psychic", paldeaPsychicGymCommand);
        GymConfigCommands.put("Paldea_Ice", paldeaIceGymCommand);
        GymConfigCommands.put("Paldea_Dark", paldeaDarkGymCommand);
        GymConfigCommands.put("Paldea_Fire", paldeaFireGymCommand);
        GymConfigCommands.put("Paldea_Poison", paldeaPoisonGymCommand);
        GymConfigCommands.put("Paldea_Fairy", paldeaFairyGymCommand);
        GymConfigCommands.put("Paldea_Fighting", paldeaFightingGymCommand);
        GymConfigCommands.put("Paldea_Rock", paldeaRockGymCommand);
        GymConfigCommands.put("Paldea_Flying", paldeaFlyingGymCommand);
        GymConfigCommands.put("Paldea_Steel", paldeaSteelGymCommand);
        GymConfigCommands.put("Paldea_Ground", paldeaGroundGymCommand);
        GymConfigCommands.put("Paldea_Dragon", paldeaDragonGymCommand);
    }
}