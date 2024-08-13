package com.markflynnman.cobblemon_gyms.capabilities;

public class ClientStarterPokemonData {
    private static String starterPokemon;
    private static String starterPokemonType;
    private static int starterPokemonDex;

    public static void set(String starterPokemon, String starterPokemonType, int starterPokemonDex) {

        ClientStarterPokemonData.starterPokemon = starterPokemon;
        ClientStarterPokemonData.starterPokemonType = starterPokemonType;
        ClientStarterPokemonData.starterPokemonDex = starterPokemonDex;
    }

    public String getStarterPokemon() { return starterPokemon; }
    public String getStarterPokemonType() { return starterPokemonType; }
    public int getStarterPokemonDex() { return starterPokemonDex; }
}
