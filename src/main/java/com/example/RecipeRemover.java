package com.example;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.Map;

public class RecipeRemover {

    private static final String[] RECIPES_TO_REMOVE = {
            // Железная броня
            "minecraft:iron_helmet",
            "minecraft:iron_chestplate",
            "minecraft:iron_leggings",
            "minecraft:iron_boots",
            // Золотая броня
            "minecraft:golden_helmet",
            "minecraft:golden_chestplate",
            "minecraft:golden_leggings",
            "minecraft:golden_boots",
            // Алмазная броня
            "minecraft:diamond_helmet",
            "minecraft:diamond_chestplate",
            "minecraft:diamond_leggings",
            "minecraft:diamond_boots",
            // Незеритовая броня
            "minecraft:netherite_helmet",
            "minecraft:netherite_chestplate",
            "minecraft:netherite_leggings",
            "minecraft:netherite_boots",
            // Инструменты (по желанию)
            // "minecraft:iron_pickaxe",
            // "minecraft:iron_sword",
            // и т.д.
    };

    public static void register() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            removeRecipes(server);
        });
    }

    private static void removeRecipes(MinecraftServer server) {
        RecipeManager recipeManager = server.getRecipeManager();

        // Получаем доступ к рецептам через рефлексию
        try {
            java.lang.reflect.Field recipesField = RecipeManager.class.getDeclaredField("recipes");
            recipesField.setAccessible(true);

            @SuppressWarnings("unchecked")
            Map<ResourceLocation, net.minecraft.world.item.crafting.Recipe<?>> recipes =
                    (Map<ResourceLocation, net.minecraft.world.item.crafting.Recipe<?>>) recipesField.get(recipeManager);

            int removed = 0;
            for (String recipeId : RECIPES_TO_REMOVE) {
                ResourceLocation id = new ResourceLocation(recipeId);
                if (recipes.remove(id) != null) {
                    removed++;
                    System.out.println("Removed recipe: " + recipeId);
                }
            }

            System.out.println("Total recipes removed: " + removed);

        } catch (Exception e) {
            System.err.println("Failed to remove recipes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}