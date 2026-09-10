package com.example.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class CustomFurnaceMenuProvider implements MenuProvider {

    private final BlockPos pos;
    private static final Component TITLE = Component.translatable("container.modid.custom_furnace");

    public CustomFurnaceMenuProvider(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public Component getDisplayName() {
        return TITLE;
    }

    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory inventory, Player player) {
        return new CustomFurnaceMenu(syncId, inventory, pos);
    }
}