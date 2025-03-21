package com.markflynnman.cobblemon_gyms.commands;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.markflynnman.cobblemon_gyms.Config;
import com.markflynnman.cobblemon_gyms.worldgen.feature.GymTemplates;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;


public class CobblemonGymsTypeSuggestionProvider implements SuggestionProvider<CommandSourceStack> {

    public CobblemonGymsTypeSuggestionProvider() {
    }

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
        Set<String> types = GymTemplates.resourceLocation.keySet();

        Objects.requireNonNull(builder);
        for (String type : types) {
            if (type.startsWith(builder.getRemaining().toLowerCase())) {
                builder.suggest(type);
            }
            else if (type.contains(builder.getRemaining().toLowerCase())) {
                builder.suggest(type);
            }
        }
        return builder.buildFuture();
    }
}
