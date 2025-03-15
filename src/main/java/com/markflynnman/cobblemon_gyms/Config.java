package com.markflynnman.cobblemon_gyms;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.*;


public class Config {
    public static final ModConfigSpec CONFIG_SPEC;
    public static final String DEFAULT_GYM_COMMAND = "say Setup gym command in the config or by typing \"/CobblemonGyms command set {badge} {command}\".";
    public static final Map<String, ModConfigSpec.ConfigValue<String>> GymConfigCommands = new HashMap<>();

    private static ModConfigSpec.IntValue gymGUICommand;
    private static ModConfigSpec.IntValue gymGUICommandOther;
    private static ModConfigSpec.IntValue badgesCommand;
    private static ModConfigSpec.IntValue badgesCommandOther;
    private static ModConfigSpec.IntValue badgesAddCommand;
    private static ModConfigSpec.IntValue badgesRemoveCommand;
    private static ModConfigSpec.IntValue badgesDevCommand;
    private static ModConfigSpec.IntValue gymCommandsSetCommand;
    private static ModConfigSpec.IntValue gymCommandsGetCommand;
    private static ModConfigSpec.IntValue starterSetCommand;
    private static ModConfigSpec.IntValue starterViewCommand;

    private static ModConfigSpec.ConfigValue<String> indigoBoulderGymCommand;
    private static ModConfigSpec.ConfigValue<String> indigoCascadeGymCommand;
    private static ModConfigSpec.ConfigValue<String> indigoThunderGymCommand;
    private static ModConfigSpec.ConfigValue<String> indigoRainbowGymCommand;
    private static ModConfigSpec.ConfigValue<String> indigoSoulGymCommand;
    private static ModConfigSpec.ConfigValue<String> indigoMarshGymCommand;
    private static ModConfigSpec.ConfigValue<String> indigoVolcanoGymCommand;
    private static ModConfigSpec.ConfigValue<String> indigoEarthGymCommand;

    private static ModConfigSpec.ConfigValue<String> johtoZephyrGymCommand;
    private static ModConfigSpec.ConfigValue<String> johtoHiveGymCommand;
    private static ModConfigSpec.ConfigValue<String> johtoPlainGymCommand;
    private static ModConfigSpec.ConfigValue<String> johtoFogGymCommand;
    private static ModConfigSpec.ConfigValue<String> johtoStormGymCommand;
    private static ModConfigSpec.ConfigValue<String> johtoMineralGymCommand;
    private static ModConfigSpec.ConfigValue<String> johtoGlacierGymCommand;
    private static ModConfigSpec.ConfigValue<String> johtoRisingGymCommand;

    private static ModConfigSpec.ConfigValue<String> hoennStoneGymCommand;
    private static ModConfigSpec.ConfigValue<String> hoennKnuckleGymCommand;
    private static ModConfigSpec.ConfigValue<String> hoennDynamoGymCommand;
    private static ModConfigSpec.ConfigValue<String> hoennHeatGymCommand;
    private static ModConfigSpec.ConfigValue<String> hoennBalanceGymCommand;
    private static ModConfigSpec.ConfigValue<String> hoennFeatherGymCommand;
    private static ModConfigSpec.ConfigValue<String> hoennMindGymCommand;
    private static ModConfigSpec.ConfigValue<String> hoennRainGymCommand;

    private static ModConfigSpec.ConfigValue<String> sinnohCoalGymCommand;
    private static ModConfigSpec.ConfigValue<String> sinnohForestGymCommand;
    private static ModConfigSpec.ConfigValue<String> sinnohCobbleGymCommand;
    private static ModConfigSpec.ConfigValue<String> sinnohFenGymCommand;
    private static ModConfigSpec.ConfigValue<String> sinnohRelicGymCommand;
    private static ModConfigSpec.ConfigValue<String> sinnohMineGymCommand;
    private static ModConfigSpec.ConfigValue<String> sinnohIcicleGymCommand;
    private static ModConfigSpec.ConfigValue<String> sinnohBeaconGymCommand;

    private static ModConfigSpec.ConfigValue<String> unovaTrioGymCommand;
    private static ModConfigSpec.ConfigValue<String> unovaBasicGymCommand;
    private static ModConfigSpec.ConfigValue<String> unovaToxicGymCommand;
    private static ModConfigSpec.ConfigValue<String> unovaInsectGymCommand;
    private static ModConfigSpec.ConfigValue<String> unovaBoltGymCommand;
    private static ModConfigSpec.ConfigValue<String> unovaQuakeGymCommand;
    private static ModConfigSpec.ConfigValue<String> unovaJetGymCommand;
    private static ModConfigSpec.ConfigValue<String> unovaFreezeGymCommand;
    private static ModConfigSpec.ConfigValue<String> unovaLegendGymCommand;
    private static ModConfigSpec.ConfigValue<String> unovaWaveGymCommand;

    private static ModConfigSpec.ConfigValue<String> kalosBugGymCommand;
    private static ModConfigSpec.ConfigValue<String> kalosCliffGymCommand;
    private static ModConfigSpec.ConfigValue<String> kalosRumbleGymCommand;
    private static ModConfigSpec.ConfigValue<String> kalosPlantGymCommand;
    private static ModConfigSpec.ConfigValue<String> kalosVoltageGymCommand;
    private static ModConfigSpec.ConfigValue<String> kalosFairyGymCommand;
    private static ModConfigSpec.ConfigValue<String> kalosPsychicGymCommand;
    private static ModConfigSpec.ConfigValue<String> kalosIcebergGymCommand;

    private static ModConfigSpec.ConfigValue<String> galarGrassGymCommand;
    private static ModConfigSpec.ConfigValue<String> galarWaterGymCommand;
    private static ModConfigSpec.ConfigValue<String> galarFireGymCommand;
    private static ModConfigSpec.ConfigValue<String> galarFightingGymCommand;
    private static ModConfigSpec.ConfigValue<String> galarGhostGymCommand;
    private static ModConfigSpec.ConfigValue<String> galarFairyGymCommand;
    private static ModConfigSpec.ConfigValue<String> galarRockGymCommand;
    private static ModConfigSpec.ConfigValue<String> galarIceGymCommand;
    private static ModConfigSpec.ConfigValue<String> galarDarkGymCommand;
    private static ModConfigSpec.ConfigValue<String> galarDragonGymCommand;

    private static ModConfigSpec.ConfigValue<String> paldeaBugGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaGrassGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaElectricGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaWaterGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaNormalGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaGhostGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaPsychicGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaIceGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaDarkGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaFireGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaPoisonGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaFairyGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaFightingGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaRockGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaFlyingGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaSteelGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaGroundGymCommand;
    private static ModConfigSpec.ConfigValue<String> paldeaDragonGymCommand;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        setupConfig(builder);
        setupCommandMap();
        CONFIG_SPEC = builder.build();
    }

    private static void setupConfig(ModConfigSpec.Builder builder) {
        builder.push("CobblemonGyms");
        builder.comment("Required permission levels for commands. [0 (all), 1 (moderator), 2 (gamemaster), 3 (admin), and 4 (owner)]");
        builder.push("Commands");
        builder.comment("\"/GymGUI\" command (Default: 3)");
        gymGUICommand = builder.defineInRange("gym_gui_command", 3, 0, 4);
        builder.comment("\"/GymGUI {player}\" command (Default: 4)");
        gymGUICommandOther = builder.defineInRange("gym_gui_command_other", 4, 0, 4);
        builder.comment("\"/badges\" command (Default: 0)");
        badgesCommand = builder.defineInRange("badges_command", 0, 0, 4);
        builder.comment("\"/badges {player}\" command (Default: 0)");
        badgesCommandOther = builder.defineInRange("badges_command_other", 0, 0, 4);
        builder.comment("\"/badges {player} add {badge}\" command (Default: 3)");
        badgesAddCommand = builder.defineInRange("badges_add_command", 3, 0, 4);
        builder.comment("\"/badges {player} remove {badge}\" command (Default: 3)");
        badgesRemoveCommand = builder.defineInRange("badges_remove_command", 3, 0, 4);
        builder.comment("\"/CobblemonGyms command set {badge} {command}\" command (This command could be used maliciously. Such as using \"/stop\" or \"/op {player}\" DO NOT SET THIS LOWER unless you know what your doing!!!) (Default: 4)");
        gymCommandsSetCommand = builder.defineInRange("gym_commands_set_command", 4, 0, 4);
        builder.comment("\"/CobblemonGyms command get {badge}\" command (Default: 3)");
        gymCommandsGetCommand = builder.defineInRange("gym_commands_get_command", 3, 0, 4);
        builder.comment("\"/CobblemonGyms starter set\" command (Default: 4)");
        starterSetCommand = builder.defineInRange("starter_set_command", 4, 0 ,4);
        builder.comment("\"/CobblemonGyms starter {player}\" command (Default: 3)");
        starterViewCommand = builder.defineInRange("starter_view_command", 3, 0, 4);
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
    public static int getGymCommandsGetCommand() { return gymCommandsGetCommand.get(); }
    public static int getStarterSetCommand() { return starterSetCommand.get(); }
    public static int getStarterViewCommand() { return starterViewCommand.get(); }

    public static String getGymCommand(String badge) {
        String command = (GymConfigCommands.get(badge.toLowerCase()) != null) ? GymConfigCommands.get(badge.toLowerCase()).get() : null;
        if (command != null && command.charAt(0) == '/') {
            command = command.replaceFirst("/", "").trim();
        }
        return command;
    }
    public static int setGymCommand(String badge, String pCommand) {
        String command = pCommand.trim();
        if (command.charAt(0) == '/' ) {
            command = command.replaceFirst("/", "").trim();
        }
        if (GymConfigCommands.get(badge.toLowerCase()) != null && !command.isEmpty()) {
            GymConfigCommands.get(badge.toLowerCase()).set(command);
            return 1;
        }
        else { return -1; }
    }

    static void setupCommandMap() {
        GymConfigCommands.put("indigo_boulder", indigoBoulderGymCommand);
        GymConfigCommands.put("indigo_cascade", indigoCascadeGymCommand);
        GymConfigCommands.put("indigo_thunder", indigoThunderGymCommand);
        GymConfigCommands.put("indigo_rainbow", indigoRainbowGymCommand);
        GymConfigCommands.put("indigo_soul", indigoSoulGymCommand);
        GymConfigCommands.put("indigo_marsh", indigoMarshGymCommand);
        GymConfigCommands.put("indigo_volcano", indigoVolcanoGymCommand);
        GymConfigCommands.put("indigo_earth", indigoEarthGymCommand);

        GymConfigCommands.put("johto_zephyr", johtoZephyrGymCommand);
        GymConfigCommands.put("johto_hive", johtoHiveGymCommand);
        GymConfigCommands.put("johto_plain", johtoPlainGymCommand);
        GymConfigCommands.put("johto_fog", johtoFogGymCommand);
        GymConfigCommands.put("johto_storm", johtoStormGymCommand);
        GymConfigCommands.put("johto_mineral", johtoMineralGymCommand);
        GymConfigCommands.put("johto_glacier", johtoGlacierGymCommand);
        GymConfigCommands.put("johto_rising", johtoRisingGymCommand);

        GymConfigCommands.put("hoenn_stone", hoennStoneGymCommand);
        GymConfigCommands.put("hoenn_knuckle", hoennKnuckleGymCommand);
        GymConfigCommands.put("hoenn_dynamo", hoennDynamoGymCommand);
        GymConfigCommands.put("hoenn_heat", hoennHeatGymCommand);
        GymConfigCommands.put("hoenn_balance", hoennBalanceGymCommand);
        GymConfigCommands.put("hoenn_feather", hoennFeatherGymCommand);
        GymConfigCommands.put("hoenn_mind", hoennMindGymCommand);
        GymConfigCommands.put("hoenn_rain", hoennRainGymCommand);

        GymConfigCommands.put("sinnoh_coal", sinnohCoalGymCommand);
        GymConfigCommands.put("sinnoh_forest", sinnohForestGymCommand);
        GymConfigCommands.put("sinnoh_cobble", sinnohCobbleGymCommand);
        GymConfigCommands.put("sinnoh_fen", sinnohFenGymCommand);
        GymConfigCommands.put("sinnoh_relic", sinnohRelicGymCommand);
        GymConfigCommands.put("sinnoh_mine", sinnohMineGymCommand);
        GymConfigCommands.put("sinnoh_icicle", sinnohIcicleGymCommand);
        GymConfigCommands.put("sinnoh_beacon", sinnohBeaconGymCommand);

        GymConfigCommands.put("unova_trio", unovaTrioGymCommand);
        GymConfigCommands.put("unova_basic", unovaBasicGymCommand);
        GymConfigCommands.put("unova_toxic", unovaToxicGymCommand);
        GymConfigCommands.put("unova_insect", unovaInsectGymCommand);
        GymConfigCommands.put("unova_bolt", unovaBoltGymCommand);
        GymConfigCommands.put("unova_quake", unovaQuakeGymCommand);
        GymConfigCommands.put("unova_jet", unovaJetGymCommand);
        GymConfigCommands.put("unova_freeze", unovaFreezeGymCommand);
        GymConfigCommands.put("unova_legend", unovaLegendGymCommand);
        GymConfigCommands.put("unova_wave", unovaWaveGymCommand);

        GymConfigCommands.put("kalos_bug", kalosBugGymCommand);
        GymConfigCommands.put("kalos_cliff", kalosCliffGymCommand);
        GymConfigCommands.put("kalos_rumble", kalosRumbleGymCommand);
        GymConfigCommands.put("kalos_plant", kalosPlantGymCommand);
        GymConfigCommands.put("kalos_voltage", kalosVoltageGymCommand);
        GymConfigCommands.put("kalos_fairy", kalosFairyGymCommand);
        GymConfigCommands.put("kalos_psychic", kalosPsychicGymCommand);
        GymConfigCommands.put("kalos_iceberg", kalosIcebergGymCommand);

        GymConfigCommands.put("galar_grass", galarGrassGymCommand);
        GymConfigCommands.put("galar_water", galarWaterGymCommand);
        GymConfigCommands.put("galar_fire", galarFireGymCommand);
        GymConfigCommands.put("galar_fighting", galarFightingGymCommand);
        GymConfigCommands.put("galar_ghost", galarGhostGymCommand);
        GymConfigCommands.put("galar_fairy", galarFairyGymCommand);
        GymConfigCommands.put("galar_rock", galarRockGymCommand);
        GymConfigCommands.put("galar_ice", galarIceGymCommand);
        GymConfigCommands.put("galar_dark", galarDarkGymCommand);
        GymConfigCommands.put("galar_dragon", galarDragonGymCommand);

        GymConfigCommands.put("paldea_bug", paldeaBugGymCommand);
        GymConfigCommands.put("paldea_grass", paldeaGrassGymCommand);
        GymConfigCommands.put("paldea_electric", paldeaElectricGymCommand);
        GymConfigCommands.put("paldea_water", paldeaWaterGymCommand);
        GymConfigCommands.put("paldea_normal", paldeaNormalGymCommand);
        GymConfigCommands.put("paldea_ghost", paldeaGhostGymCommand);
        GymConfigCommands.put("paldea_psychic", paldeaPsychicGymCommand);
        GymConfigCommands.put("paldea_ice", paldeaIceGymCommand);
        GymConfigCommands.put("paldea_dark", paldeaDarkGymCommand);
        GymConfigCommands.put("paldea_fire", paldeaFireGymCommand);
        GymConfigCommands.put("paldea_poison", paldeaPoisonGymCommand);
        GymConfigCommands.put("paldea_fairy", paldeaFairyGymCommand);
        GymConfigCommands.put("paldea_fighting", paldeaFightingGymCommand);
        GymConfigCommands.put("paldea_rock", paldeaRockGymCommand);
        GymConfigCommands.put("paldea_flying", paldeaFlyingGymCommand);
        GymConfigCommands.put("paldea_steel", paldeaSteelGymCommand);
        GymConfigCommands.put("paldea_ground", paldeaGroundGymCommand);
        GymConfigCommands.put("paldea_dragon", paldeaDragonGymCommand);
    }
}