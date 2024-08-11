package com.markflynnman.cobblemon_gyms.datagen;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.items.GymBadges;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CobblemonGyms.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(GymBadges.BOULDER_BADGE, "indigo");
        simpleItem(GymBadges.CASCADE_BADGE, "indigo");
        simpleItem(GymBadges.THUNDER_BADGE, "indigo");
        simpleItem(GymBadges.RAINBOW_BADGE, "indigo");
        simpleItem(GymBadges.SOUL_BADGE, "indigo");
        simpleItem(GymBadges.MARSH_BADGE, "indigo");
        simpleItem(GymBadges.VOLCANO_BADGE, "indigo");
        simpleItem(GymBadges.EARTH_BADGE, "indigo");

        simpleItem(GymBadges.ZEPHYR_BADGE, "johto");
        simpleItem(GymBadges.HIVE_BADGE, "johto");
        simpleItem(GymBadges.PLAIN_BADGE, "johto");
        simpleItem(GymBadges.FOG_BADGE, "johto");
        simpleItem(GymBadges.STORM_BADGE, "johto");
        simpleItem(GymBadges.MINERAL_BADGE, "johto");
        simpleItem(GymBadges.GLACIER_BADGE, "johto");
        simpleItem(GymBadges.RISING_BADGE, "johto");

        simpleItem(GymBadges.STONE_BADGE, "hoenn");
        simpleItem(GymBadges.KNUCKLE_BADGE, "hoenn");
        simpleItem(GymBadges.DYNAMO_BADGE, "hoenn");
        simpleItem(GymBadges.HEAT_BADGE, "hoenn");
        simpleItem(GymBadges.BALANCE_BADGE, "hoenn");
        simpleItem(GymBadges.FEATHER_BADGE, "hoenn");
        simpleItem(GymBadges.MIND_BADGE, "hoenn");
        simpleItem(GymBadges.RAIN_BADGE, "hoenn");

        simpleItem(GymBadges.COAL_BADGE, "sinnoh");
        simpleItem(GymBadges.FOREST_BADGE, "sinnoh");
        simpleItem(GymBadges.COBBLE_BADGE, "sinnoh");
        simpleItem(GymBadges.FEN_BADGE, "sinnoh");
        simpleItem(GymBadges.RELIC_BADGE, "sinnoh");
        simpleItem(GymBadges.MINE_BADGE, "sinnoh");
        simpleItem(GymBadges.ICICLE_BADGE, "sinnoh");
        simpleItem(GymBadges.BEACON_BADGE, "sinnoh");

        simpleItem(GymBadges.TRIO_BADGE, "unova");
        simpleItem(GymBadges.BASIC_BADGE, "unova");
        simpleItem(GymBadges.TOXIC_BADGE, "unova");
        simpleItem(GymBadges.INSECT_BADGE, "unova");
        simpleItem(GymBadges.BOLT_BADGE, "unova");
        simpleItem(GymBadges.QUAKE_BADGE, "unova");
        simpleItem(GymBadges.JET_BADGE, "unova");
        simpleItem(GymBadges.FREEZE_BADGE, "unova");
        simpleItem(GymBadges.LEGEND_BADGE, "unova");
        simpleItem(GymBadges.WAVE_BADGE, "unova");

        simpleItem(GymBadges.BUG_BADGE, "kalos");
        simpleItem(GymBadges.CLIFF_BADGE, "kalos");
        simpleItem(GymBadges.RUMBLE_BADGE, "kalos");
        simpleItem(GymBadges.PLANT_BADGE, "kalos");
        simpleItem(GymBadges.VOLTAGE_BADGE, "kalos");
        simpleItem(GymBadges.FAIRY_BADGE, "kalos");
        simpleItem(GymBadges.PSYCHIC_BADGE, "kalos");
        simpleItem(GymBadges.ICEBERG_BADGE, "kalos");

        simpleItem(GymBadges.SAS_GRASS_BADGE, "galar");
        simpleItem(GymBadges.SAS_WATER_BADGE, "galar");
        simpleItem(GymBadges.SAS_FIRE_BADGE, "galar");
        simpleItem(GymBadges.SAS_FIGHTING_BADGE, "galar");
        simpleItem(GymBadges.SAS_GHOST_BADGE, "galar");
        simpleItem(GymBadges.SAS_FAIRY_BADGE, "galar");
        simpleItem(GymBadges.SAS_ROCK_BADGE, "galar");
        simpleItem(GymBadges.SAS_ICE_BADGE, "galar");
        simpleItem(GymBadges.SAS_DARK_BADGE, "galar");
        simpleItem(GymBadges.SAS_DRAGON_BADGE, "galar");

        simpleItem(GymBadges.SV_BUG_BADGE, "paldea");
        simpleItem(GymBadges.SV_GRASS_BADGE, "paldea");
        simpleItem(GymBadges.SV_ELECTRIC_BADGE, "paldea");
        simpleItem(GymBadges.SV_WATER_BADGE, "paldea");
        simpleItem(GymBadges.SV_NORMAL_BADGE, "paldea");
        simpleItem(GymBadges.SV_GHOST_BADGE, "paldea");
        simpleItem(GymBadges.SV_PSYCHIC_BADGE, "paldea");
        simpleItem(GymBadges.SV_ICE_BADGE, "paldea");

        simpleItem(GymBadges.SV_DARK_BADGE, "paldea");
        simpleItem(GymBadges.SV_FIRE_BADGE, "paldea");
        simpleItem(GymBadges.SV_POISON_BADGE, "paldea");
        simpleItem(GymBadges.SV_FAIRY_BADGE, "paldea");
        simpleItem(GymBadges.SV_FIGHTING_BADGE, "paldea");

        simpleItem(GymBadges.SV_ROCK_BADGE, "paldea");
        simpleItem(GymBadges.SV_FLYING_BADGE, "paldea");
        simpleItem(GymBadges.SV_STEEL_BADGE, "paldea");
        simpleItem(GymBadges.SV_GROUND_BADGE, "paldea");
        simpleItem(GymBadges.SV_DRAGON_BADGE, "paldea");
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item, String league) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CobblemonGyms.MODID, "item/badges/" + league + "/" + item.getId().getPath()));
    }
}
