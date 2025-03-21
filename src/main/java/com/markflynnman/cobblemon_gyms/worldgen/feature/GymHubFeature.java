package com.markflynnman.cobblemon_gyms.worldgen.feature;

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

import java.util.List;
import java.util.Optional;

public class GymHubFeature {
    private static final List<StructureProcessor> PROCESSORS = List.of();

    public static boolean place(ServerLevelAccessor level, BlockPos pos, boolean dropBlocks) {
        return createGymHub(level, pos, dropBlocks);
    }

    private static final Logger LOGGER = LogUtils.getLogger();
    public static boolean createGymHub(ServerLevelAccessor level, BlockPos pos, boolean dropBlocks) {
        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "gym_hub");
        Optional<StructureTemplate> template = level.getLevel().getStructureManager().get(resourceLocation);
        if (template.isEmpty()) {
            LOGGER.warn(CobblemonGyms.MODID + ": Template is empty, could not spawn gym_hub. Resource location: "+resourceLocation);
            return false;
        }
        if (!level.getBlockState(pos).isAir()) {
            LOGGER.warn(CobblemonGyms.MODID + ": Block is not air, could not spawn gym_hub.");
            return false;
        }

        BlockPos offset = new BlockPos(0, 0, 0);
        StructurePlaceSettings structurePlaceSettings = new StructurePlaceSettings();
        PROCESSORS.forEach(structurePlaceSettings::addProcessor);
        structurePlaceSettings.setRotation(Rotation.NONE);
        structurePlaceSettings.setLiquidSettings(LiquidSettings.IGNORE_WATERLOGGING);
        // Not sure why but had to switch offset and pos to spawn in correct position... pos seems to do nothing???
        template.get().placeInWorld(level, new BlockPos(pos.getX() - template.get().getSize().getX()/2, pos.getY(), pos.getZ() - template.get().getSize().getZ()/2), offset, structurePlaceSettings, RandomSource.create(), 4);
        return true;
    }
}
