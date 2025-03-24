package com.markflynnman.cobblemon_gyms.gym_systems;

import net.minecraft.core.BlockPos;

import java.util.UUID;

public class GymArena {
    private final BlockPos gymLocation;
    private final UUID trainerUUID;
    private Boolean battleInProgress;
    private GymLeader gymLeader;

    public GymArena(BlockPos gymLocation, Boolean battleInProgress, UUID trainerUUID) {
        this.gymLocation = gymLocation;
        this.battleInProgress = battleInProgress;
        this.trainerUUID = trainerUUID;
    }

    public BlockPos getGymLocation() {
        return gymLocation;
    }

    public UUID getTrainerUUID() { return trainerUUID; }

    public Boolean getBattleInProgress() {
        return battleInProgress;
    }

    public GymLeader getGymLeader() { return gymLeader; }

    public void setBattleInProgress(Boolean battleInProgress) {
        this.battleInProgress = battleInProgress;
    }

    public void setGymLeader(GymLeader gymLeader) { this.gymLeader = gymLeader; }
}
