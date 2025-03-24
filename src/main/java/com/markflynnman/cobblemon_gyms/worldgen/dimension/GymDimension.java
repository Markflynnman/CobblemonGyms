package com.markflynnman.cobblemon_gyms.worldgen.dimension;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.data_attachments.Attachments;
import com.markflynnman.cobblemon_gyms.data_attachments.PlayerWarpHistory;
import com.markflynnman.cobblemon_gyms.gym_systems.GymHandler;
import com.markflynnman.cobblemon_gyms.worldgen.biome.ModBiomes;
import com.markflynnman.cobblemon_gyms.worldgen.feature.GymHubFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.entity.ai.util.RandomPos;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.flat.FlatLayerInfo;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.phys.Vec3;


import java.util.*;

public class GymDimension {
    public static final ResourceKey<LevelStem> COBBLEMON_GYMS_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "gym_dimension"));
    public static final ResourceKey<Level> COBBLEMON_GYMS_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "gym_dimension"));
    public static final ResourceKey<DimensionType> COBBLEMON_GYMS_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "gym_dimension_type"));

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
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

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);

        FlatLevelGeneratorSettings flatLevelGeneratorSettings = new FlatLevelGeneratorSettings(
            Optional.empty(),
            biomeRegistry.getOrThrow(ModBiomes.GYM_BIOME),
            new ArrayList<Holder<PlacedFeature>>()
        ).withBiomeAndLayers(
            List.of(new FlatLayerInfo(
                    1, // height
                    BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("minecraft", "air"))
            )),
            Optional.empty(),
            biomeRegistry.getOrThrow(ModBiomes.GYM_BIOME)
        );


        FlatLevelSource flatLevelSource = new FlatLevelSource(flatLevelGeneratorSettings);

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(GymDimension.COBBLEMON_GYMS_DIM_TYPE), flatLevelSource);

        context.register(COBBLEMON_GYMS_KEY, stem);
    }

    public static void toDimension(MinecraftServer server, ServerPlayer player) {
        ServerLevel level = server.getLevel(COBBLEMON_GYMS_LEVEL_KEY);
        BlockPos blockPos = new BlockPos(0, 70, 0);
        Vec3 pos = new Vec3(0.5, 71, 0.5);
        PlayerWarpHistory playerWarpHistory = player.getData(Attachments.PLAYER_WARP_HISTORY);

        playerWarpHistory.setLevelKey(player.level().dimension());
        playerWarpHistory.setPos(player.position());
        playerWarpHistory.setYRot(player.getYRot());
        playerWarpHistory.setXRot(player.getXRot());
        playerWarpHistory.setGamemode(player.gameMode.getGameModeForPlayer().getId());

        player.changeDimension(GymDimensionTransition.destination(level, pos));
        if (!player.hasPermissions(3)) {
            player.setGameMode(GameType.ADVENTURE);
        }
        GymHubFeature.place(level, blockPos, false);
        GymHandler.initGyms(level, server);
        GymHandler.initHubNPCs(server);
    }

    public static void fromDimension(MinecraftServer server, ServerPlayer player) {
        PlayerWarpHistory playerWarpHistory = player.getData(Attachments.PLAYER_WARP_HISTORY);
        ServerLevel level = server.getLevel(playerWarpHistory.getLevelKey());
        Vec3 pos = playerWarpHistory.getPos();
        Float yRot = playerWarpHistory.getYRot();
        Float xRot = playerWarpHistory.getXRot();
        GameType gameType = GameType.byId(playerWarpHistory.getGamemode());

        player.changeDimension(GymDimensionTransition.destination(level, pos, yRot, xRot));
        player.setGameMode(gameType);
    }

    public static void firstSpawn(MinecraftServer server, ServerPlayer player) {
        ServerLevel level = server.getLevel(COBBLEMON_GYMS_LEVEL_KEY);
        BlockPos blockPos = new BlockPos(0, 70, 0);
        Vec3 pos = new Vec3(0.5, 71, 0.5);

        player.changeDimension(GymDimensionTransition.destination(level, pos, 0, 0));
        if (!player.hasPermissions(3)) {
            player.setGameMode(GameType.ADVENTURE);
        }
        GymHubFeature.place(level, blockPos, false);
        GymHandler.initGyms(level, server);
    }

    public static void randomSpawn(MinecraftServer server, ServerPlayer player) {
        ServerLevel level = server.getLevel(Level.OVERWORLD);
        BlockPos spawnPos = level.getSharedSpawnPos();
        Random rand = new Random();

        int max = 500;
        int min = -500;
        int x = rand.nextInt((spawnPos.getX() + max) - (spawnPos.getX() + min) + 1) + (spawnPos.getX() + min);
        int z = rand.nextInt((spawnPos.getZ() + max) - (spawnPos.getZ() + min) + 1) + (spawnPos.getZ() + min);

        BlockPos randPos = new BlockPos(spawnPos.getX() + x, spawnPos.getY(), spawnPos.getZ() + z);
        Vec3 vec3 = randPos.getCenter();
        int i = level.getChunkAt(randPos).getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, randPos.getX(), randPos.getZ()) + 1;

        player.changeDimension(GymDimensionTransition.destination(level, new Vec3(vec3.x, (double)i, vec3.z), player.getYRot(), 0));
        player.setGameMode(GameType.SURVIVAL);
    }
}
