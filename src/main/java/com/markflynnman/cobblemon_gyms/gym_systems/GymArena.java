package com.markflynnman.cobblemon_gyms.gym_systems;

import net.minecraft.core.BlockPos;

import java.util.UUID;

public class GymArena {
    private final BlockPos gymLocation;
    private Boolean battleInProgress;
    private final UUID trainerUUID;

    public GymArena(BlockPos gymLocation, Boolean battleInProgress, UUID trainerUUID) {
        this.gymLocation = gymLocation;
        this.battleInProgress = battleInProgress;
        this.trainerUUID = trainerUUID;
    }

    public BlockPos getGymLocation() {
        return gymLocation;
    }

    public Boolean getBattleInProgress() {
        return battleInProgress;
    }

    public void setBattleInProgress(Boolean battleInProgress) {
        this.battleInProgress = battleInProgress;
    }

    public UUID getTrainerUUID() {
        return trainerUUID;
    }
}
