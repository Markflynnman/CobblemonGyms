package com.markflynnman.cobblemon_gyms.worldgen.dimension;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;

public class GymDimensionTransition {
    public static DimensionTransition destination(ServerLevel newLevel, Vec3 pos) {
        float yRot = 180;
        float xRot = 0;
        return destination(newLevel, pos, yRot, xRot);
    }

    public static DimensionTransition destination(ServerLevel newLevel, Vec3 pos, float yRot, float xRot) {
        return new DimensionTransition(
                newLevel,
                pos,
                Vec3.ZERO,
                yRot, xRot,
                DimensionTransition.DO_NOTHING
        );
    }
}
