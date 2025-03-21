package com.markflynnman.cobblemon_gyms.worldgen.feature;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class GymTemplates {
    public static Map<String, ResourceLocation> resourceLocation = Map.ofEntries(
            entry("bug", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "bug_gym")),
            entry("dark", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "dark_gym")),
            entry("dragon", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "dragon_gym")),
            entry("electric", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "electric_gym")),
            entry("fairy", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "fairy_gym")),
            entry("fighting", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "fighting_gym")),
            entry("fire", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "fire_gym")),
            entry("flying", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "flying_gym")),
            entry("ghost", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "ghost_gym")),
            entry("grass", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "grass_gym")),
            entry("ground", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "ground_gym")),
            entry("ice", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "ice_gym")),
            entry("normal", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "normal_gym")),
            entry("poison", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "poison_gym")),
            entry("psychic", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "psychic_gym")),
            entry("rock", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "rock_gym")),
            entry("steel", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "steel_gym")),
            entry("water", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "water_gym")),
            entry("empty", ResourceLocation.fromNamespaceAndPath(CobblemonGyms.MODID, "empty"))
    );

    public static Optional<StructureTemplate> get(ServerLevelAccessor level, String type) {
        return level.getLevel().getStructureManager().get(resourceLocation.get(type));
    }

    public static Optional<StructureTemplate> getByResourceLocation(ServerLevelAccessor level, ResourceLocation resourceLocation) {
        return level.getLevel().getStructureManager().get(resourceLocation);
    }
}
