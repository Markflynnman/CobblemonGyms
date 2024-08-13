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

public class PlayerBadgeCollectionProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerBadgeCollection> PLAYER_BADGE_COLLECTION = CapabilityManager.get(new CapabilityToken<PlayerBadgeCollection>() { });

    private PlayerBadgeCollection badgeCollection = null;
    private final LazyOptional<PlayerBadgeCollection> optional = LazyOptional.of(this::createPlayerBadgeCollection);

    private PlayerBadgeCollection createPlayerBadgeCollection() {
        if (this.badgeCollection == null) {
            this.badgeCollection = new PlayerBadgeCollection();
        }

        return this.badgeCollection;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if(capability == PLAYER_BADGE_COLLECTION) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerBadgeCollection().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerBadgeCollection().loadNBTData(nbt);

    }
}
