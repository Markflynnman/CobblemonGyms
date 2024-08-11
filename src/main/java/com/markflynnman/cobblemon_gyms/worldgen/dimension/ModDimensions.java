package com.markflynnman.cobblemon_gyms.worldgen.dimension;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.worldgen.biome.ModBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.flat.FlatLayerInfo;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class ModDimensions {
    public static final ResourceKey<LevelStem> COBBLEMON_GYMS_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(CobblemonGyms.MODID, "gym_dimension"));
    public static final ResourceKey<Level> COBBLEMON_GYMS_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(CobblemonGyms.MODID, "gym_dimension"));
    public static final ResourceKey<DimensionType> COBBLEMON_GYMS_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(CobblemonGyms.MODID, "gym_dimension_type"));

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(COBBLEMON_GYMS_DIM_TYPE, new DimensionType(
                OptionalLong.of(6000), // fixedTime
                true, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                384, // height
                384, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);

        FlatLevelGeneratorSettings flatLevelGeneratorSettings = new FlatLevelGeneratorSettings(
            Optional.empty(),
            biomeRegistry.getOrThrow(ModBiomes.GYM_BIOME),
            new ArrayList<Holder<PlacedFeature>>()
        ).withBiomeAndLayers(
            List.of(new FlatLayerInfo(
                    1, // height
                    ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "air")) // block
            )),
            Optional.empty(),
            biomeRegistry.getOrThrow(ModBiomes.GYM_BIOME)
        );


        FlatLevelSource flatLevelSource = new FlatLevelSource(flatLevelGeneratorSettings);

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.COBBLEMON_GYMS_DIM_TYPE), flatLevelSource);

        context.register(COBBLEMON_GYMS_KEY, stem);
    }
}
