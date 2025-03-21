package com.markflynnman.cobblemon_gyms.data_attachments;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PlayerBadgeCollection implements INBTSerializable<CompoundTag> {
    public static List<String> AllBadges = Arrays.asList(
            "indigo_boulder",
            "indigo_cascade",
            "indigo_thunder",
            "indigo_rainbow",
            "indigo_soul",
            "indigo_marsh",
            "indigo_volcano",
            "indigo_earth",

            "johto_zephyr",
            "johto_hive",
            "johto_plain",
            "johto_fog",
            "johto_storm",
            "johto_mineral",
            "johto_glacier",
            "johto_rising",

            "hoenn_stone",
            "hoenn_knuckle",
            "hoenn_dynamo",
            "hoenn_heat",
            "hoenn_balance",
            "hoenn_feather",
            "hoenn_mind",
            "hoenn_rain",

            "sinnoh_coal",
            "sinnoh_forest",
            "sinnoh_cobble",
            "sinnoh_fen",
            "sinnoh_relic",
            "sinnoh_mine",
            "sinnoh_icicle",
            "sinnoh_beacon",

            "unova_trio",
            "unova_basic",
            "unova_toxic",
            "unova_insect",
            "unova_bolt",
            "unova_quake",
            "unova_jet",
            "unova_freeze",
            "unova_legend",
            "unova_wave",

            "kalos_bug",
            "kalos_cliff",
            "kalos_rumble",
            "kalos_plant",
            "kalos_voltage",
            "kalos_fairy",
            "kalos_psychic",
            "kalos_iceberg",

            "galar_grass",
            "galar_water",
            "galar_fire",
            "galar_fighting",
            "galar_ghost",
            "galar_fairy",
            "galar_rock",
            "galar_ice",
            "galar_dark",
            "galar_dragon",

            "paldea_bug",
            "paldea_grass",
            "paldea_electric",
            "paldea_water",
            "paldea_normal",
            "paldea_ghost",
            "paldea_psychic",
            "paldea_ice",
            "paldea_dark",
            "paldea_fire",
            "paldea_poison",
            "paldea_fairy",
            "paldea_fighting",
            "paldea_rock",
            "paldea_flying",
            "paldea_steel",
            "paldea_ground",
            "paldea_dragon");

    private int[] badgeCollection = new int[AllBadges.size()];

    public PlayerBadgeCollection() {
        Arrays.fill(badgeCollection, 0);
    }

    public int[] getBadgeCollection() {
        return badgeCollection;
    }

    public boolean addBadge(int index) {
        try {
            Array.set(badgeCollection, index, 1);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean addBadge(String badge) {
        int index = AllBadges.indexOf(badge);
        if (index == -1) {
            return false;
        }

        Array.set(badgeCollection, index, 1);
        return true;
    }

    public boolean removeBadge(int index) {
        try {
            Array.set(badgeCollection, index, 0);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean removeBadge(String badge) {
        int index = AllBadges.indexOf(badge);
        if (index == -1) {
            return false;
        }

        Array.set(badgeCollection, index, 0);
        return true;
    }

    public void copyFrom(PlayerBadgeCollection source) {
        this.badgeCollection = source.badgeCollection;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putIntArray("badgeCollection", badgeCollection);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        badgeCollection = nbt.getIntArray("badgeCollection");
    }
}