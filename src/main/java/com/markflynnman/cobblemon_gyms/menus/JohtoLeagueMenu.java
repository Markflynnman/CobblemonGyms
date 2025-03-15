package com.markflynnman.cobblemon_gyms.menus;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class JohtoLeagueMenu extends AbstractContainerMenu {
    private final Level level;
    private final ContainerData data;

    public JohtoLeagueMenu(int pContainerId, Inventory inv) {
        this(pContainerId, inv, new ItemStackHandler(2), new SimpleContainerData(2));
    }

    public JohtoLeagueMenu(int pContainerId, Inventory inv, IItemHandler dataInventory, ContainerData data) {
        super(ModMenuTypes.JOHTO_LEAGUE_MENU.get(), pContainerId);
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

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
