package com.example.block;

import com.example.ModMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CustomFurnaceMenu extends AbstractContainerMenu {

    private final Container furnaceContainer = new SimpleContainer(3);
    private final Level level;
    private final BlockPos pos;

    // Конструктор для регистрации (без BlockPos)
    public CustomFurnaceMenu(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, null);
    }

    // Основной конструктор
    public CustomFurnaceMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        super(ModMenu.CUSTOM_FURNACE_MENU, syncId);
        this.level = playerInventory.player.level();
        this.pos = pos != null ? pos : BlockPos.ZERO;

        // Слот для предмета на переплавку
        this.addSlot(new Slot(furnaceContainer, 0, 56, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return CustomFurnaceRecipes.isSmeltable(stack);
            }
        });

        // Слот для топлива
        this.addSlot(new Slot(furnaceContainer, 1, 56, 53) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return CustomFurnaceRecipes.isFuel(stack);
            }
        });

        // Слот для результата
        this.addSlot(new Slot(furnaceContainer, 2, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                ItemStack input = furnaceContainer.getItem(0);
                input.shrink(1);
                if (input.isEmpty()) {
                    furnaceContainer.setItem(0, ItemStack.EMPTY);
                }
                ItemStack fuel = furnaceContainer.getItem(1);
                fuel.shrink(1);
                if (fuel.isEmpty()) {
                    furnaceContainer.setItem(1, ItemStack.EMPTY);
                }
                super.onTake(player, stack);
            }
        });

        // Инвентарь игрока
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Горячая панель
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void slotsChanged(Container container) {
        super.slotsChanged(container);
        updateResult();
    }

    private void updateResult() {
        ItemStack input = furnaceContainer.getItem(0);
        ItemStack fuel = furnaceContainer.getItem(1);
        ItemStack result = ItemStack.EMPTY;

        if (!fuel.isEmpty() && CustomFurnaceRecipes.isFuel(fuel)) {
            result = CustomFurnaceRecipes.getResult(input);
        }

        furnaceContainer.setItem(2, result);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        Slot slot = this.slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            ItemStack copy = stack.copy();

            if (slotIndex == 2) {
                if (!this.moveItemStackTo(stack, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, copy);
            } else if (slotIndex >= 3 && slotIndex < 39) {
                if (CustomFurnaceRecipes.isSmeltable(stack)) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (CustomFurnaceRecipes.isFuel(stack)) {
                    if (!this.moveItemStackTo(stack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            return copy;
        }
        return ItemStack.EMPTY;
    }
}