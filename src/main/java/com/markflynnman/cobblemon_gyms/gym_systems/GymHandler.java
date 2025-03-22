package com.markflynnman.cobblemon_gyms.gym_systems;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.gitlab.srcmc.rctapi.api.RCTApi;
import com.gitlab.srcmc.rctapi.api.battle.BattleRules;
import com.gitlab.srcmc.rctapi.api.trainer.TrainerNPC;
import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.worldgen.feature.GymArenaFeatures;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.ServerLevelAccessor;

import java.util.*;

public class GymHandler {
//    [[-111, -111], [-23, -111], [65, -111], [65, -23], [65, 65], [-23, 65], [-111, 65], [-111, -23]]
    private static final BlockPos[] gymLocations = {
            new BlockPos(-111,70,-111),
            new BlockPos(-23,70,-111),
            new BlockPos(65,70,-111),
            new BlockPos(65,70,-23),
            new BlockPos(65,70,65),
            new BlockPos(-23,70,65),
            new BlockPos(-111,70,65),
            new BlockPos(-111,70,-23)
    };
    public static List<GymArena> gymArenas = new ArrayList<GymArena>();
    public static Map<String, GymLeader> gymLeaders = new HashMap<>();
    public static int nextGymID = 0;
    public static final UUID professorOakUUID = UUID.randomUUID();
    public static final UUID gymAttendantUUID = UUID.randomUUID();

    public static void initHubNPCs(ServerPlayer player) {
        // Spawn Professor Oak if not spawned already
        String command = "execute in cobblemon_gyms:gym_dimension unless entity " + professorOakUUID + " run easy_npc preset import data cobblemon_gyms:preset/humanoid/professor_oak.npc.nbt 0.5 71 8.5 " + professorOakUUID;
        CommandSourceStack commandSourceStack = player.createCommandSourceStack().withSuppressedOutput().withPermission(4);
        CommandDispatcher<CommandSourceStack> commanddispatcher = player.getServer().getCommands().getDispatcher();
        ParseResults<CommandSourceStack> results = commanddispatcher.parse(command, commandSourceStack);
        player.getServer().getCommands().performCommand(results, command);

        // Spawn Gym Attendant if not spawned already
        command = "execute in cobblemon_gyms:gym_dimension unless entity " + gymAttendantUUID + " run easy_npc preset import data cobblemon_gyms:preset/humanoid_slim/gym_attendant.npc.nbt 0.5 71 -7.5 " + gymAttendantUUID;
        results = commanddispatcher.parse(command, commandSourceStack);
        player.getServer().getCommands().performCommand(results, command);
    }

    public static void initGyms(ServerLevelAccessor level) {
        for (BlockPos gymLocation : gymLocations) {
            GymArenaFeatures.place(level, gymLocation, false, false, ElementalTypes.INSTANCE.get("normal"));
            gymArenas.add(new GymArena(gymLocation, false, UUID.randomUUID()));
        }
    }

    public static void addLeader(String badge, GymLeader gymLeader) {
        gymLeaders.put(badge, gymLeader);
    }

    public static boolean startGym(ServerLevelAccessor level, ServerPlayer player, String badge) {
        GymArena gymArena = gymArenas.get(nextGymID);
        if (gymArena.getBattleInProgress()) {
            for (int i = 0; i < gymArenas.size(); i++) {
                int index = (nextGymID + 1) % gymArenas.size();
                if (!gymArenas.get(index).getBattleInProgress()) {
                    gymArena = gymArenas.get(index);
                    break;
                }
            }
        }
        for (String key: gymLeaders.keySet()) {
            CobblemonGyms.LOGGER.warn(key);
        }
        if (!gymLeaders.containsKey(badge)) {
            player.sendSystemMessage(Component.literal("Failed to find " + badge + " gym leader."), true);
            CobblemonGyms.LOGGER.error("Failed to find " + badge + " in gymLeaders Map.");
            return false;
        }
        BlockPos trainerSpawn = new BlockPos(gymArena.getGymLocation().getX()+23, gymArena.getGymLocation().getY()+2, gymArena.getGymLocation().getZ()+14);
        GymLeader gymLeader = gymLeaders.get(badge);
        GymBattleHandler.getINSTANCE().addBattle(player.getUUID(), gymLeader);

        // TODO Fix NPCs spawning when they already exist
        // Spawn EasyNPC
        String command = "easy_npc preset import data cobblemon_gyms:preset/"+gymLeader.getModelType()+"/"+gymLeader.getNameNPC()+".npc.nbt "+trainerSpawn.getX()+" "+trainerSpawn.getY()+" "+trainerSpawn.getZ()+" "+gymArena.getTrainerUUID();
        CommandSourceStack commandSourceStack = player.createCommandSourceStack().withSuppressedOutput().withPermission(4);
        CommandDispatcher<CommandSourceStack> commanddispatcher = player.getServer().getCommands().getDispatcher();
        ParseResults<CommandSourceStack> results = commanddispatcher.parse(command, commandSourceStack);
        player.getServer().getCommands().performCommand(results, command);

        player.sendSystemMessage(Component.literal(command));

        GymArenaFeatures.place(level, gymArena.getGymLocation(), false, true, gymLeader.getType());
        player.teleportTo(level.getLevel(), gymArena.getGymLocation().getX()+23.5, gymArena.getGymLocation().getY()+2, gymArena.getGymLocation().getZ()+32.5, 180, 0);
        LivingEntity trainer = EntityType.VILLAGER.spawn(level.getLevel(), trainerSpawn.offset(0,-10,0), MobSpawnType.EVENT); // Spawn out of sight
        RCTApi.getInstance(CobblemonGyms.MODID).getTrainerRegistry().getById(gymLeader.getTrainerID(), TrainerNPC.class).setEntity(trainer);
        RCTApi.getInstance(CobblemonGyms.MODID).getBattleManager().startSingle(CobblemonGyms.RCT.getTrainerRegistry().getById(player.getName().getString()), gymLeaders.get(badge).getTrainer(), new BattleRules());
        trainer.remove(Entity.RemovalReason.DISCARDED);
        return true;
    }

    // TODO Fix NPCs not getting removed
    public static void cleanNPCS(MinecraftServer server) {
        for (GymArena gymArena: gymArenas) {
            // Delete EasyNPC
            String command = "easy_npc delete " + gymArena.getTrainerUUID();
            CommandSourceStack commandSourceStack = server.createCommandSourceStack().withSuppressedOutput().withPermission(4);
            CommandDispatcher<CommandSourceStack> commanddispatcher = server.getCommands().getDispatcher();
            ParseResults<CommandSourceStack> results = commanddispatcher.parse(command, commandSourceStack);
            server.getCommands().performCommand(results, command);
        }
    }
}
