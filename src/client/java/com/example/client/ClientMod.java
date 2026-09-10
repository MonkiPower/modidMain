package com.example.client;

import com.example.ModMenu;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

@Environment(EnvType.CLIENT)
public class ClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Регистрация экрана для улучшенного верстака
        if (ModMenu.CUSTOM_CRAFTING_TABLE_MENU != null) {
            MenuScreens.register(ModMenu.CUSTOM_CRAFTING_TABLE_MENU, CustomCraftingTableScreen::new);
            System.out.println("CustomCraftingTableScreen registered!");
        } else {
            System.out.println("ERROR: CUSTOM_CRAFTING_TABLE_MENU is null!");
        }

        // Регистрация экрана для кастомной печи
        if (ModMenu.CUSTOM_FURNACE_MENU != null) {
            MenuScreens.register(ModMenu.CUSTOM_FURNACE_MENU, CustomFurnaceScreen::new);
            System.out.println("CustomFurnaceScreen registered!");
        } else {
            System.out.println("ERROR: CUSTOM_FURNACE_MENU is null!");
        }

        System.out.println("✅ All screens registered!");
    }
}