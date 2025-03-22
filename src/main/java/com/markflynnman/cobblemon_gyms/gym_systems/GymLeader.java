package com.markflynnman.cobblemon_gyms.gym_systems;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.gitlab.srcmc.rctapi.api.trainer.Trainer;

import java.util.UUID;

public class GymLeader {
    private final String name;
    private final String nameNPC;
    private final String badge;
    private final String badgePath;
    private final String modelType;
    private final ElementalType type;
    private final Trainer trainer;
    private final String trainerID;
    private final UUID uuid;

    public GymLeader(String name, String nameNPC, String badge, String badgePath, String modelType, ElementalType type, Trainer trainer, String trainerID, UUID uuid) {
        this.name = name;
        this.nameNPC = nameNPC;
        this.badge = badge;
        this.badgePath = badgePath;
        this.modelType = modelType;
        this.type = type;
        this.trainer = trainer;
        this.trainerID = trainerID;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public String getNameNPC() {
        return nameNPC;
    }

    public String getBadge() { return badge; }

    public String getBadgePath() { return badgePath; }

    public String getModelType() {
        return modelType;
    }

    public ElementalType getType() {
        return type;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public String getTrainerID() {
        return trainerID;
    }

    public UUID getUuid() {
        return uuid;
    }
}
