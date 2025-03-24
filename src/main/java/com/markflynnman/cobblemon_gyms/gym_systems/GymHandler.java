package com.markflynnman.cobblemon_gyms.gym_systems;

import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.gitlab.srcmc.rctapi.api.RCTApi;
import com.gitlab.srcmc.rctapi.api.battle.BattleRules;
import com.gitlab.srcmc.rctapi.api.trainer.TrainerNPC;
import com.markflynnman.cobblemon_gyms.CobblemonGyms;
import com.markflynnman.cobblemon_gyms.worldgen.dimension.GymDimension;
import com.markflynnman.cobblemon_gyms.worldgen.feature.GymArenaFeatures;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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
    public static UUID professorOakUUID;
    public static UUID gymAttendantUUID;

    public static void initHubNPCs(MinecraftServer server) {
        // Spawn Professor Oak
        String command = "execute in cobblemon_gyms:gym_dimension run easy_npc preset import data cobblemon_gyms:preset/humanoid/professor_oak.npc.nbt 0.5 71 8.5 " + professorOakUUID;
        CommandSourceStack commandSourceStack = server.createCommandSourceStack().withSuppressedOutput().withPermission(4);
        CommandDispatcher<CommandSourceStack> commanddispatcher = server.getCommands().getDispatcher();
        ParseResults<CommandSourceStack> results = commanddispatcher.parse(command, commandSourceStack);
        server.getCommands().performCommand(results, command);

        // Spawn Gym Attendant
        command = "execute in cobblemon_gyms:gym_dimension run easy_npc preset import data cobblemon_gyms:preset/humanoid_slim/gym_attendant.npc.nbt 0.5 71 -7.5 " + gymAttendantUUID;
        results = commanddispatcher.parse(command, commandSourceStack);
        server.getCommands().performCommand(results, command);
    }

    public static void initGyms(ServerLevelAccessor level, MinecraftServer server) {
        if (gymArenas.isEmpty()) {
            for (BlockPos gymLocation : gymLocations) {
                GymArenaFeatures.place(level, gymLocation, false, false, ElementalTypes.INSTANCE.get("normal"));
                gymArenas.add(new GymArena(gymLocation, false, UUID.randomUUID()));
                BlockPos trainerSpawn = new BlockPos(gymLocation.getX() + 23, 0, gymLocation.getZ() + 14);
                int chunkX = level.getChunk(trainerSpawn).getPos().x;
                int chunkZ = level.getChunk(trainerSpawn).getPos().z;
                server.getLevel(GymDimension.COBBLEMON_GYMS_LEVEL_KEY).setChunkForced(chunkX, chunkZ, true);
            }
        }
    }

    public static void setProfessorOakUUID(UUID uuid) {
        professorOakUUID = uuid;
    }

    public static void setGymAttendantUUID(UUID uuid) {
        gymAttendantUUID = uuid;
    }

    public static void addLeader(String badge, GymLeader gymLeader) {
        gymLeaders.put(badge, gymLeader);
    }

    public static boolean startGym(ServerLevelAccessor level, ServerPlayer player, String badge) {
        GymArena gymArena = gymArenas.get(nextGymID%8);
        if (gymArena.getBattleInProgress()) {
            for (int i = 0; i < gymArenas.size(); i++) {
                int index = (nextGymID + 1) % gymArenas.size();
                if (!gymArenas.get(index).getBattleInProgress()) {
                    gymArena = gymArenas.get(index);
                    break;
                }
            }
        }
        if (!gymLeaders.containsKey(badge)) {
            player.sendSystemMessage(Component.literal("Failed to find " + badge + " gym leader."), true);
            CobblemonGyms.LOGGER.error("Failed to find " + badge + " in gymLeaders Map.");
            return false;
        }
        BlockPos trainerSpawn = new BlockPos(gymArena.getGymLocation().getX()+23, gymArena.getGymLocation().getY()+2, gymArena.getGymLocation().getZ()+14);
        GymLeader gymLeader = gymLeaders.get(badge);
        gymArena.setGymLeader(gymLeader);
        GymBattleHandler.getINSTANCE().addBattle(player.getUUID(), gymArena);

        // Spawn EasyNPC
        String command = "easy_npc preset import data cobblemon_gyms:preset/"+gymLeader.getModelType()+"/"+gymLeader.getNameNPC()+".npc.nbt "+trainerSpawn.getX()+" "+trainerSpawn.getY()+" "+trainerSpawn.getZ()+" "+gymArena.getTrainerUUID();
        CommandSourceStack commandSourceStack = player.createCommandSourceStack().withSuppressedOutput().withPermission(4);
        CommandDispatcher<CommandSourceStack> commanddispatcher = player.getServer().getCommands().getDispatcher();
        ParseResults<CommandSourceStack> results = commanddispatcher.parse(command, commandSourceStack);
        player.getServer().getCommands().performCommand(results, command);

        GymArenaFeatures.place(level, gymArena.getGymLocation(), false, true, gymLeader.getType());
        player.teleportTo(level.getLevel(), gymArena.getGymLocation().getX()+23.5, gymArena.getGymLocation().getY()+2, gymArena.getGymLocation().getZ()+32.5, 180, 0);
        LivingEntity trainer = (LivingEntity) player.getServer().getLevel(GymDimension.COBBLEMON_GYMS_LEVEL_KEY).getEntity(gymArena.getTrainerUUID());
        RCTApi.getInstance(CobblemonGyms.MODID).getTrainerRegistry().getById(gymLeader.getTrainerID(), TrainerNPC.class).setEntity(trainer);
        RCTApi.getInstance(CobblemonGyms.MODID).getBattleManager().startSingle(CobblemonGyms.RCT.getTrainerRegistry().getById(player.getName().getString()), gymLeaders.get(badge).getTrainer(), new BattleRules());

        gymArena.setBattleInProgress(true);
        nextGymID++;

        return true;
    }

    public static void cleanNPCS(MinecraftServer server) {
        for (GymArena gymArena: gymArenas) {
            // Delete EasyNPC
            Entity trainer = server.getLevel(GymDimension.COBBLEMON_GYMS_LEVEL_KEY).getEntity(gymArena.getTrainerUUID());
            if (trainer != null) {
                trainer.remove(Entity.RemovalReason.DISCARDED);
            }
        }

        // Delete Professor Oak and Gym Attendant
        Entity professorOak = server.getLevel(GymDimension.COBBLEMON_GYMS_LEVEL_KEY).getEntity(professorOakUUID);
        if (professorOak != null) {
            professorOak.remove(Entity.RemovalReason.DISCARDED);
        }

        Entity gymAttendant = server.getLevel(GymDimension.COBBLEMON_GYMS_LEVEL_KEY).getEntity(gymAttendantUUID);
        if (gymAttendant != null) {
            gymAttendant.remove(Entity.RemovalReason.DISCARDED);
        }
    }
}
