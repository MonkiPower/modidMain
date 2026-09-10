package com.example.block;

import com.example.ExampleMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;

public class CustomFurnaceRecipes {

    // Карта рецептов: вход → результат (ItemStack)
    private static final Map<Item, ItemStack> SMELTING_RECIPES = new HashMap<>();

    // Список топлива
    private static final Map<Item, Integer> FUEL_MAP = new HashMap<>();

    static {
        // ═══════════════════════════════════════════════
        // РЕЦЕПТЫ ПЕРЕПЛАВКИ (ТОЛЬКО ДЛЯ ЭТОЙ ПЕЧИ!)
        // ═══════════════════════════════════════════════

        // Гнилая плоть → Кожа
        SMELTING_RECIPES.put(Items.ROTTEN_FLESH, new ItemStack(Items.LEATHER, 1));

        // Стальная пластина → Железный слиток
        SMELTING_RECIPES.put(ExampleMod.STEEL_PLATE.asItem(), new ItemStack(Items.IRON_INGOT, 2));

        // Стальное кольцо → Железный слиток
        SMELTING_RECIPES.put(ExampleMod.STEEL_RING.asItem(), new ItemStack(Items.IRON_INGOT, 1));

        // Блок щелочного порошка → 4 порошка
        SMELTING_RECIPES.put(ExampleMod.ALKALINE_POWDER_BLOCK.asItem(), new ItemStack(ExampleMod.ALKALINE_POWBER, 4));

        // Каменный уголь → Алмаз (шутка, но можно!)
        // SMELTING_RECIPES.put(Items.COAL, new ItemStack(Items.DIAMOND, 1));

        // Кирпич → Глина
        SMELTING_RECIPES.put(Items.BRICK, new ItemStack(Items.CLAY_BALL, 1));

        // Стекло → Песок
        SMELTING_RECIPES.put(Items.GLASS, new ItemStack(Items.SAND, 1));

        // ═══════════════════════════════════════════════
        // ТОПЛИВО (можете добавить своё)
        // ═══════════════════════════════════════════════

        // Стандартное топливо
        FUEL_MAP.put(Items.COAL, 1600);        // Уголь (80 предметов)
        FUEL_MAP.put(Items.CHARCOAL, 1600);     // Древесный уголь
        FUEL_MAP.put(Items.LAVA_BUCKET, 20000); // Лава (1000 предметов)
        FUEL_MAP.put(Items.BLAZE_ROD, 2400);    // Огненный стержень (120 предметов)

        // Древесина
        FUEL_MAP.put(Items.OAK_LOG, 300);
        FUEL_MAP.put(Items.SPRUCE_LOG, 300);
        FUEL_MAP.put(Items.BIRCH_LOG, 300);
        FUEL_MAP.put(Items.JUNGLE_LOG, 300);
        FUEL_MAP.put(Items.ACACIA_LOG, 300);
        FUEL_MAP.put(Items.DARK_OAK_LOG, 300);
        FUEL_MAP.put(Items.MANGROVE_LOG, 300);

        // Доски
        FUEL_MAP.put(Items.OAK_PLANKS, 150);
        FUEL_MAP.put(Items.SPRUCE_PLANKS, 150);
        FUEL_MAP.put(Items.BIRCH_PLANKS, 150);
        FUEL_MAP.put(Items.JUNGLE_PLANKS, 150);
        FUEL_MAP.put(Items.ACACIA_PLANKS, 150);
        FUEL_MAP.put(Items.DARK_OAK_PLANKS, 150);
        FUEL_MAP.put(Items.MANGROVE_PLANKS, 150);

        // Палки
        FUEL_MAP.put(Items.STICK, 100);

        // Сундуки
        FUEL_MAP.put(Items.CHEST, 300);

        // Ваше топливо
        FUEL_MAP.put(ExampleMod.STEEL_PLATE.asItem(), 400);   // 20 предметов
        FUEL_MAP.put(ExampleMod.STEEL_RING.asItem(), 200);    // 10 предметов
        FUEL_MAP.put(ExampleMod.ALKALINE_POWBER.asItem(), 100); // 5 предметов
    }

    public static boolean isSmeltable(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return SMELTING_RECIPES.containsKey(stack.getItem());
    }

    public static boolean isFuel(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return FUEL_MAP.containsKey(stack.getItem());
    }

    public static ItemStack getResult(ItemStack input) {
        if (input.isEmpty()) return ItemStack.EMPTY;
        ItemStack result = SMELTING_RECIPES.get(input.getItem());
        return result != null ? result.copy() : ItemStack.EMPTY;
    }

    public static int getFuelTime(ItemStack stack) {
        if (stack.isEmpty()) return 0;
        return FUEL_MAP.getOrDefault(stack.getItem(), 0);
    }
}