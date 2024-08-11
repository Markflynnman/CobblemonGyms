package com.markflynnman.cobblemon_gyms.badgeCollection;

public class ClientBadgeCollectionData {
    private static int[] playerBadgeCollection;

    public static void set(int[] badgeCollection) {
        ClientBadgeCollectionData.playerBadgeCollection = badgeCollection;
    }

    public static int[] getPlayerBadgeCollection() {
        return playerBadgeCollection;
    }
}
