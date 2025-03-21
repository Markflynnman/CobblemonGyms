package com.markflynnman.cobblemon_gyms.data_attachments;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class PlayerWarpHistory implements INBTSerializable<CompoundTag> {
    private ResourceKey<Level> levelKey;
    private float x;
    private float y;
    private float z;
    private float yRot;
    private float xRot;
    private int gamemode;
    private boolean spawned;

    public PlayerWarpHistory() {
        levelKey = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath("minecraft", "overworld"));
        x = 0;
        y = 0;
        z = 0;
        xRot = 0;
        yRot = 0;
        gamemode = 0;
        spawned = false;
    }

    public ResourceKey<Level> getLevelKey() { return levelKey; }
    public Float getX() { return x; }
    public Float getY() { return y; }
    public Float getZ() { return z; }
    public Vec3 getPos() { return new Vec3(x, y, z); }
    public Float getYRot() { return yRot; }
    public Float getXRot() { return xRot; }
    public int getGamemode() { return gamemode; }
    public boolean getSpawned() { return spawned; }

    public void setLevelKey(ResourceKey<Level> levelKey) { this.levelKey = levelKey; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setZ(float z) { this.z = z; }
    public void setPos(Vec3 pos) {
        this.x = (float)pos.x;
        this.y = (float)pos.y;
        this.z = (float)pos.z;
    }
    public void setYRot(float yRot) { this.yRot = yRot; }
    public void setXRot(float xRot) { this.xRot = xRot; }
    public void setGamemode(int gamemode) { this.gamemode = gamemode; }
    public void setSpawned(boolean spawned) { this.spawned = spawned;}


    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putString("levelKey_locationName", levelKey.location().getNamespace());
        tag.putString("levelKey_locationPath", levelKey.location().getPath());
        tag.putFloat("x", x);
        tag.putFloat("y", y);
        tag.putFloat("z", z);
        tag.putFloat("yRot", yRot);
        tag.putFloat("xRot", xRot);
        tag.putInt("gamemode", gamemode);
        tag.putBoolean("spawned", spawned);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        levelKey = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(nbt.getString("levelKey_locationName"), nbt.getString("levelKey_locationPath")));
        x = nbt.getFloat("x");
        y = nbt.getFloat("y");
        z = nbt.getFloat("z");
        yRot = nbt.getFloat("yRot");
        xRot = nbt.getFloat("xRot");
        gamemode = nbt.getInt("gamemode");
        spawned = nbt.getBoolean("spawned");
    }
}
