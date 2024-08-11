package com.markflynnman.cobblemon_gyms.commands;

import com.markflynnman.cobblemon_gyms.Config;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;

public class GymSelectionCommand {
    public GymSelectionCommand(String pGym_ID, ServerPlayer pPlayer) {
        String command = Config.GymConfigCommands.get(pGym_ID).get();

        CommandSourceStack source = pPlayer.server.createCommandSourceStack().withSuppressedOutput().withPermission(4);
        CommandDispatcher<CommandSourceStack> commandDispatcher = pPlayer.getServer().getCommands().getDispatcher();
        ParseResults<CommandSourceStack> parseResults = commandDispatcher.parse(command, source);

        int result = pPlayer.server.getCommands().performCommand(parseResults, command);
    }
}
