package com.example;

import com.example.block.CustomCraftingTableMenu;
import com.example.block.CustomFurnaceMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Inventory;

public class ModMenu {

    // Меню для улучшенного верстака
    public static final MenuType<CustomCraftingTableMenu> CUSTOM_CRAFTING_TABLE_MENU =
            new MenuType<>(
                    new MenuType.MenuSupplier<CustomCraftingTableMenu>() {
                        @Override
                        public CustomCraftingTableMenu create(int syncId, Inventory inventory) {
                            return new CustomCraftingTableMenu(syncId, inventory);
                        }
                    },
                    FeatureFlags.VANILLA_SET
            );

    // Меню для кастомной печи
    public static final MenuType<CustomFurnaceMenu> CUSTOM_FURNACE_MENU =
            new MenuType<>(
                    new MenuType.MenuSupplier<CustomFurnaceMenu>() {
                        @Override
                        public CustomFurnaceMenu create(int syncId, Inventory inventory) {
                            // Для печи нужен также BlockPos, поэтому используем конструктор с параметрами
                            return new CustomFurnaceMenu(syncId, inventory, null);
                        }
                    },
                    FeatureFlags.VANILLA_SET
            );

    public static void register() {
        Registry.register(
                BuiltInRegistries.MENU,
                new ResourceLocation(ExampleMod.MOD_ID, "custom_crafting_table_menu"),
                CUSTOM_CRAFTING_TABLE_MENU
        );

        Registry.register(
                BuiltInRegistries.MENU,
                new ResourceLocation(ExampleMod.MOD_ID, "custom_furnace_menu"),
                CUSTOM_FURNACE_MENU
        );
    }
}