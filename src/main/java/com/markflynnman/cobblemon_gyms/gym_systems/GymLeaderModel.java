package com.markflynnman.cobblemon_gyms.gym_systems;

import org.jetbrains.annotations.NotNull;

public class GymLeaderModel {
    private String name;
    private String badge;
    private String nameNPC;
    private String modelType;
    private String elementalType;


    public GymLeaderModel(@NotNull String name,@NotNull String badge, @NotNull String nameNPC, @NotNull String modelType, @NotNull String elementalType) {
        this.name = name;
        this.badge = badge;
        this.nameNPC = nameNPC;
        this.modelType = modelType;
        this.elementalType = elementalType;
    }

    public String getName() {
        return name;
    }

    public String getBadge() {
        return badge;
    }

    public String getNameNPC() {
        return nameNPC;
    }

    public String getModelType() {
        return modelType;
    }

    public String getElementalType() {
        return elementalType;
    }
}
