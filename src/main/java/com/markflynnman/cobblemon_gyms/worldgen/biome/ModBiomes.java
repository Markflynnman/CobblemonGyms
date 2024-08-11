package com.markflynnman.cobblemon_gyms.worldgen.biome;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;

public class ModBiomes {
    public static final ResourceKey<Biome> GYM_BIOME = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(CobblemonGyms.MODID, "gym_biome"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(GYM_BIOME, gymBiome(context));
    }

    public static Biome gymBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.8f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .skyColor(0x78A7FF)
                        .fogColor(0xC0D8FF)
                        .waterColor(0x3F76E4)
                        .waterFogColor(0x050533)
                        .build())
                .build();
    }
}
