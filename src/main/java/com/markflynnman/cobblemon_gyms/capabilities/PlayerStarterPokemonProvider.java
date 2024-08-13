package com.markflynnman.cobblemon_gyms.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerStarterPokemonProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerStarterPokemon> PLAYER_STARTER_POKEMON = CapabilityManager.get(new CapabilityToken<PlayerStarterPokemon>() { });

    private PlayerStarterPokemon starterPokemon = null;
    private final LazyOptional<PlayerStarterPokemon> optional = LazyOptional.of(this::createPlayerStarterPokemon);

    private PlayerStarterPokemon createPlayerStarterPokemon() {
        if (this.starterPokemon == null) {
            this.starterPokemon = new PlayerStarterPokemon();
        }
        return this.starterPokemon;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if(capability == PLAYER_STARTER_POKEMON) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerStarterPokemon().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerStarterPokemon().loadNBTData(nbt);
    }
}
