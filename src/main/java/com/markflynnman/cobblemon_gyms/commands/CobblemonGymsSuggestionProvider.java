package com.markflynnman.cobblemon_gyms.commands;

import com.markflynnman.cobblemon_gyms.Config;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class CobblemonGymsSuggestionProvider implements SuggestionProvider<CommandSourceStack> {

    public CobblemonGymsSuggestionProvider() {
    }

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
        Set<String> gyms = Config.GymConfigCommands.keySet();

        Objects.requireNonNull(builder);
        gyms.forEach(builder::suggest);
        return builder.buildFuture();
    }
}
