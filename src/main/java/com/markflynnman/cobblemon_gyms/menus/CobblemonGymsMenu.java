package com.markflynnman.cobblemon_gyms.menus;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CobblemonGymsMenu extends AbstractContainerMenu {
    private final Level level;
    private final ContainerData data;

    public CobblemonGymsMenu(int pContainerId, Inventory inv) {
        this(pContainerId, inv, new ItemStackHandler(2), new SimpleContainerData(2));
    }

    public CobblemonGymsMenu(int pContainerId, Inventory inv, IItemHandler dataInventory, ContainerData data) {
        super(ModMenuTypes.COBBLEMON_GYMS_MENU.get(), pContainerId);
        checkContainerSize(inv, 2);
        this.level = inv.player.level();
        this.data = data;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
