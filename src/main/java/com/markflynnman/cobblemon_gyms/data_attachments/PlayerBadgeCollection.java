package com.markflynnman.cobblemon_gyms.data_attachments;

import com.mojang.logging.LogUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.slf4j.Logger;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;

public class PlayerBadgeCollection implements INBTSerializable<CompoundTag> {
    public static List<String> AllBadges = Arrays.asList(
        "Indigo_Boulder",
        "Indigo_Cascade",
        "Indigo_Thunder",
        "Indigo_Rainbow",
        "Indigo_Soul",
        "Indigo_Marsh",
        "Indigo_Volcano",
        "Indigo_Earth",

        "Johto_Zephyr",
        "Johto_Hive",
        "Johto_Plain",
        "Johto_Fog",
        "Johto_Storm",
        "Johto_Mineral",
        "Johto_Glacier",
        "Johto_Rising",

        "Hoenn_Stone",
        "Hoenn_Knuckle",
        "Hoenn_Dynamo",
        "Hoenn_Heat",
        "Hoenn_Balance",
        "Hoenn_Feather",
        "Hoenn_Mind",
        "Hoenn_Rain",

        "Sinnoh_Coal",
        "Sinnoh_Forest",
        "Sinnoh_Cobble",
        "Sinnoh_Fen",
        "Sinnoh_Relic",
        "Sinnoh_Mine",
        "Sinnoh_Icicle",
        "Sinnoh_Beacon",

        "Unova_Trio",
        "Unova_Basic",
        "Unova_Toxic",
        "Unova_Insect",
        "Unova_Bolt",
        "Unova_Quake",
        "Unova_Jet",
        "Unova_Freeze",
        "Unova_Legend",
        "Unova_Wave",

        "Kalos_Bug",
        "Kalos_Cliff",
        "Kalos_Rumble",
        "Kalos_Plant",
        "Kalos_Voltage",
        "Kalos_Fairy",
        "Kalos_Psychic",
        "Kalos_Iceberg",

        "Galar_Grass",
        "Galar_Water",
        "Galar_Fire",
        "Galar_Fighting",
        "Galar_Ghost",
        "Galar_Fairy",
        "Galar_Rock",
        "Galar_Ice",
        "Galar_Dark",
        "Galar_Dragon",

        "Paldea_Bug",
        "Paldea_Grass",
        "Paldea_Electric",
        "Paldea_Water",
        "Paldea_Normal",
        "Paldea_Ghost",
        "Paldea_Psychic",
        "Paldea_Ice",
        "Paldea_Dark",
        "Paldea_Fire",
        "Paldea_Poison",
        "Paldea_Fairy",
        "Paldea_Fighting",
        "Paldea_Rock",
        "Paldea_Flying",
        "Paldea_Steel",
        "Paldea_Ground",
        "Paldea_Dragon");

    private int[] badgeCollection = new int[AllBadges.size()];

    public PlayerBadgeCollection() {
        Arrays.fill(badgeCollection, 0);
    }

    public int[] getBadgeCollection() { return badgeCollection; }

    public boolean addBadge(int index) {
        try {
            Array.set(badgeCollection, index, 1);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
    public boolean addBadge(String badge) {
        int index = AllBadges.indexOf(badge);
        if (index == -1) { return false; }

        Array.set(badgeCollection, index, 1);
        return true;
    }

    public boolean removeBadge(int index) {
        try {
            Array.set(badgeCollection, index, 0);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
    public boolean removeBadge(String badge) {
        int index = AllBadges.indexOf(badge);
        if (index == -1) { return false; }

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

    private static final Logger LOGGER = LogUtils.getLogger();

//    public static byte[] toByteArray(int[] data) {
//        LOGGER.info("Starting toByteArray");
//        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length * 4);
//        IntBuffer intBuffer = byteBuffer.asIntBuffer();
//        intBuffer.put(data);
//
//        LOGGER.info("Finished toByteArray");
//        return byteBuffer.array();
//    }
//
//    public static int[] toIntArray(byte[] data) {
//        LOGGER.info("Starting toIntArray");
//        IntBuffer intBuffer = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).asIntBuffer();
//        int[] array = new int[intBuffer.remaining()];
//
//        intBuffer.get(array);
//        LOGGER.info("Finished toIntArray");
//        return ;
//    }

    // Remove
    public void saveNBTData(CompoundTag nbt) {
        nbt.putIntArray("badgeCollection", badgeCollection);
    }

    public void loadNBTData(CompoundTag nbt) {
        badgeCollection = nbt.getIntArray("badgeCollection");
    }
}
