package com.markflynnman.cobblemon_gyms.data_attachments;

import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class PlayerStarterPokemon implements INBTSerializable<CompoundTag> {
    private String starterPokemon;
    private String starterPokemonType;
    private int starterPokemonDex;

    public PlayerStarterPokemon() {
        starterPokemon = "none";
        starterPokemonType = "none";
        starterPokemonDex = -1;
    }

    public String getStarterPokemon() { return starterPokemon; }
    public String getStarterPokemonType() { return starterPokemonType; }
    public int getStarterPokemonDex() { return starterPokemonDex; }

    public void setStarterPokemon(Pokemon pokemon) {
        starterPokemon = pokemon.getSpecies().getName();
        starterPokemonType = pokemon.getPrimaryType().getName();
        starterPokemonDex = pokemon.getSpecies().getNationalPokedexNumber();
    }

    public void copyFrom(PlayerStarterPokemon source) {
        this.starterPokemon = source.starterPokemon;
        this.starterPokemonType = source.starterPokemonType;
        this.starterPokemonDex = source.starterPokemonDex;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putString("name", starterPokemon);
        tag.putString("type", starterPokemonType);
        tag.putInt("dex", starterPokemonDex);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        starterPokemon = nbt.getString("name");
        starterPokemonType = nbt.getString("type");
        starterPokemonDex = nbt.getInt("dex");
    }

    // Remove
    public void saveNBTData(CompoundTag nbt) {
        nbt.putString("name", starterPokemon);
        nbt.putString("type", starterPokemonType);
        nbt.putInt("dex", starterPokemonDex);
    }

    public void loadNBTData(CompoundTag nbt) {
        starterPokemon = nbt.getString("name");
        starterPokemonType = nbt.getString("type");
        starterPokemonDex = nbt.getInt("dex");
    }
}
