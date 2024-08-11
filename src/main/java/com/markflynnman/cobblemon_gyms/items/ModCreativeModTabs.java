package com.markflynnman.cobblemon_gyms.items;

import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CobblemonGyms.MODID);

    public static final RegistryObject<CreativeModeTab> COBBLEMON_GYMS_TABS = CREATIVE_MODE_TABS.register("cobblemon_gyms_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(GymBadges.BOULDER_BADGE.get()))
                    .title(Component.translatable("creativetab.cobblemon_gyms_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(GymBadges.BOULDER_BADGE.get());
                        pOutput.accept(GymBadges.CASCADE_BADGE.get());
                        pOutput.accept(GymBadges.THUNDER_BADGE.get());
                        pOutput.accept(GymBadges.RAINBOW_BADGE.get());
                        pOutput.accept(GymBadges.SOUL_BADGE.get());
                        pOutput.accept(GymBadges.MARSH_BADGE.get());
                        pOutput.accept(GymBadges.VOLCANO_BADGE.get());
                        pOutput.accept(GymBadges.EARTH_BADGE.get());

                        pOutput.accept(GymBadges.ZEPHYR_BADGE.get());
                        pOutput.accept(GymBadges.HIVE_BADGE.get());
                        pOutput.accept(GymBadges.PLAIN_BADGE.get());
                        pOutput.accept(GymBadges.FOG_BADGE.get());
                        pOutput.accept(GymBadges.STORM_BADGE.get());
                        pOutput.accept(GymBadges.MINERAL_BADGE.get());
                        pOutput.accept(GymBadges.GLACIER_BADGE.get());
                        pOutput.accept(GymBadges.RISING_BADGE.get());

                        pOutput.accept(GymBadges.STONE_BADGE.get());
                        pOutput.accept(GymBadges.KNUCKLE_BADGE.get());
                        pOutput.accept(GymBadges.DYNAMO_BADGE.get());
                        pOutput.accept(GymBadges.HEAT_BADGE.get());
                        pOutput.accept(GymBadges.BALANCE_BADGE.get());
                        pOutput.accept(GymBadges.FEATHER_BADGE.get());
                        pOutput.accept(GymBadges.MIND_BADGE.get());
                        pOutput.accept(GymBadges.RAIN_BADGE.get());

                        pOutput.accept(GymBadges.COAL_BADGE.get());
                        pOutput.accept(GymBadges.FOREST_BADGE.get());
                        pOutput.accept(GymBadges.COBBLE_BADGE.get());
                        pOutput.accept(GymBadges.FEN_BADGE.get());
                        pOutput.accept(GymBadges.RELIC_BADGE.get());
                        pOutput.accept(GymBadges.MINE_BADGE.get());
                        pOutput.accept(GymBadges.ICICLE_BADGE.get());
                        pOutput.accept(GymBadges.BEACON_BADGE.get());

                        pOutput.accept(GymBadges.TRIO_BADGE.get());
                        pOutput.accept(GymBadges.BASIC_BADGE.get());
                        pOutput.accept(GymBadges.TOXIC_BADGE.get());
                        pOutput.accept(GymBadges.INSECT_BADGE.get());
                        pOutput.accept(GymBadges.BOLT_BADGE.get());
                        pOutput.accept(GymBadges.QUAKE_BADGE.get());
                        pOutput.accept(GymBadges.JET_BADGE.get());
                        pOutput.accept(GymBadges.FREEZE_BADGE.get());
                        pOutput.accept(GymBadges.LEGEND_BADGE.get());
                        pOutput.accept(GymBadges.WAVE_BADGE.get());

                        pOutput.accept(GymBadges.BUG_BADGE.get());
                        pOutput.accept(GymBadges.CLIFF_BADGE.get());
                        pOutput.accept(GymBadges.RUMBLE_BADGE.get());
                        pOutput.accept(GymBadges.PLANT_BADGE.get());
                        pOutput.accept(GymBadges.VOLTAGE_BADGE.get());
                        pOutput.accept(GymBadges.FAIRY_BADGE.get());
                        pOutput.accept(GymBadges.PSYCHIC_BADGE.get());
                        pOutput.accept(GymBadges.ICEBERG_BADGE.get());

                        pOutput.accept(GymBadges.SAS_GRASS_BADGE.get());
                        pOutput.accept(GymBadges.SAS_WATER_BADGE.get());
                        pOutput.accept(GymBadges.SAS_FIRE_BADGE.get());
                        pOutput.accept(GymBadges.SAS_FIGHTING_BADGE.get());
                        pOutput.accept(GymBadges.SAS_GHOST_BADGE.get());
                        pOutput.accept(GymBadges.SAS_FAIRY_BADGE.get());
                        pOutput.accept(GymBadges.SAS_ROCK_BADGE.get());
                        pOutput.accept(GymBadges.SAS_ICE_BADGE.get());
                        pOutput.accept(GymBadges.SAS_DARK_BADGE.get());
                        pOutput.accept(GymBadges.SAS_DRAGON_BADGE.get());

                        pOutput.accept(GymBadges.SV_BUG_BADGE.get());
                        pOutput.accept(GymBadges.SV_GRASS_BADGE.get());
                        pOutput.accept(GymBadges.SV_ELECTRIC_BADGE.get());
                        pOutput.accept(GymBadges.SV_WATER_BADGE.get());
                        pOutput.accept(GymBadges.SV_NORMAL_BADGE.get());
                        pOutput.accept(GymBadges.SV_GHOST_BADGE.get());
                        pOutput.accept(GymBadges.SV_PSYCHIC_BADGE.get());
                        pOutput.accept(GymBadges.SV_ICE_BADGE.get());

                        pOutput.accept(GymBadges.SV_DARK_BADGE.get());
                        pOutput.accept(GymBadges.SV_FIRE_BADGE.get());
                        pOutput.accept(GymBadges.SV_POISON_BADGE.get());
                        pOutput.accept(GymBadges.SV_FAIRY_BADGE.get());
                        pOutput.accept(GymBadges.SV_FIGHTING_BADGE.get());

                        pOutput.accept(GymBadges.SV_ROCK_BADGE.get());
                        pOutput.accept(GymBadges.SV_FLYING_BADGE.get());
                        pOutput.accept(GymBadges.SV_STEEL_BADGE.get());
                        pOutput.accept(GymBadges.SV_GROUND_BADGE.get());
                        pOutput.accept(GymBadges.SV_DRAGON_BADGE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
