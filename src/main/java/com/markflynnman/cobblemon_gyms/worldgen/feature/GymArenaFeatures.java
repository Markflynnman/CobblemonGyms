package com.markflynnman.cobblemon_gyms.worldgen.feature;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GymArenaFeatures {
    private static final List<StructureProcessor> PROCESSORS = List.of();

    public static boolean place(ServerLevelAccessor level, BlockPos pos, boolean dropBlocks, boolean replace, ElementalType type) {
        return createGym(level, pos, dropBlocks, replace, type);
    }

    private static final Logger LOGGER = LogUtils.getLogger();
    public static boolean createGym(ServerLevelAccessor level, BlockPos pos, boolean dropBlocks, boolean replace, ElementalType type) {
        Optional<StructureTemplate> template = GymTemplates.get(level, type.getName());
        if (template.isEmpty()) {
            LOGGER.warn(CobblemonGyms.MODID + ": Template is empty, could not spawn " + template + ". Trying default gym.");
            template = GymTemplates.get(level, "normal");
            if (template.isEmpty()) {
                LOGGER.warn(CobblemonGyms.MODID + ": Default template is empty, could not spawn " + template + ".");
                return false;
            }
        }
        if (!level.getBlockState(pos).isAir()) {
            if (!replace) {
                LOGGER.warn(CobblemonGyms.MODID + ": Block is not air, could not spawn " + template + ".");
                return false;
            }
        }

        BlockPos offset = new BlockPos(0, 0, 0);
        StructurePlaceSettings structurePlaceSettings = new StructurePlaceSettings();
        PROCESSORS.forEach(structurePlaceSettings::addProcessor);
        structurePlaceSettings.setRotation(Rotation.NONE);
        structurePlaceSettings.setLiquidSettings(LiquidSettings.IGNORE_WATERLOGGING);
        // Not sure why but had to switch offset and pos to spawn in correct position... position seems to do nothing???
        template.get().placeInWorld(level, pos, offset, structurePlaceSettings, RandomSource.create(), 4);
        return true;
    }
}
