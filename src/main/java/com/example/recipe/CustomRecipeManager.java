package com.example.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.inventory.TransientCraftingContainer;

import java.util.ArrayList;
import java.util.List;

public class CustomRecipeManager {

    // Список всех кастомных рецептов
    private static final List<CustomRecipe> RECIPES = new ArrayList<>();

    // ──────────────────────────────────────────────────
    // КЛАСС ДЛЯ ХРАНЕНИЯ ОДНОГО РЕЦЕПТА
    // ──────────────────────────────────────────────────
    public static class CustomRecipe {
        public final ItemStack[] pattern; // 9 слотов (3x3)
        public final ItemStack result;    // Что выдаётся
        public final int count;           // Количество

        public CustomRecipe(ItemStack[] pattern, ItemStack result, int count) {
            this.pattern = pattern;
            this.result = result;
            this.count = count;
        }
    }

    // ──────────────────────────────────────────────────
    // ДОБАВЛЕНИЕ РЕЦЕПТОВ
    // ──────────────────────────────────────────────────

    /**
     * Добавить рецепт
     * @param pattern массив из 9 предметов (слоты 0-8)
     * @param result предмет, который выдаётся
     * @param count количество
     */
    public static void addRecipe(ItemStack[] pattern, ItemStack result, int count) {
        // Копируем результат с правильным количеством
        ItemStack resultCopy = result.copy();
        resultCopy.setCount(count);
        RECIPES.add(new CustomRecipe(pattern, resultCopy, count));
    }

    /**
     * Упрощённый способ добавить рецепт
     * @param pattern массив из 9 предметов (используйте Items.AIR для пустых слотов)
     * @param resultItem предмет, который выдаётся
     * @param count количество
     */
    public static void addRecipeSimple(Item[] pattern, Item resultItem, int count) {
        ItemStack[] itemStacks = new ItemStack[9];
        for (int i = 0; i < 9; i++) {
            if (pattern[i] != null && pattern[i] != Items.AIR) {
                itemStacks[i] = new ItemStack(pattern[i]);
            } else {
                itemStacks[i] = ItemStack.EMPTY;
            }
        }
        addRecipe(itemStacks, new ItemStack(resultItem), count);
    }

    /**
     * Добавить рецепт из строкового паттерна (как в JSON)
     * @param pattern строки 3x3 (используйте " " для пустых слотов)
     * @param keyMap словарь: символ -> предмет
     * @param resultItem предмет, который выдаётся
     * @param count количество
     */
    public static void addRecipeFromPattern(String[] pattern, java.util.Map<Character, Item> keyMap, Item resultItem, int count) {
        if (pattern.length != 3) {
            throw new IllegalArgumentException("Pattern must have 3 rows");
        }

        ItemStack[] slots = new ItemStack[9];
        for (int row = 0; row < 3; row++) {
            if (pattern[row].length() != 3) {
                throw new IllegalArgumentException("Each row must have 3 characters");
            }
            for (int col = 0; col < 3; col++) {
                char c = pattern[row].charAt(col);
                if (c == ' ') {
                    slots[row * 3 + col] = ItemStack.EMPTY;
                } else {
                    Item item = keyMap.get(c);
                    if (item == null) {
                        throw new IllegalArgumentException("Unknown character: " + c);
                    }
                    slots[row * 3 + col] = new ItemStack(item);
                }
            }
        }
        addRecipe(slots, new ItemStack(resultItem), count);
    }

    // ──────────────────────────────────────────────────
    // ПРОВЕРКА РЕЦЕПТОВ
    // ──────────────────────────────────────────────────

    /**
     * Проверить, есть ли подходящий рецепт для текущей сетки крафта
     * @param craftSlots слоты крафта (9 штук)
     * @return результат крафта или пустой ItemStack
     */
    public static ItemStack checkRecipes(TransientCraftingContainer craftSlots) {
        if (craftSlots == null) return ItemStack.EMPTY;

        for (CustomRecipe recipe : RECIPES) {
            if (matches(recipe, craftSlots)) {
                ItemStack result = recipe.result.copy();
                result.setCount(recipe.count);
                return result;
            }
        }
        return ItemStack.EMPTY;
    }

    /**
     * Проверить, совпадает ли один конкретный рецепт
     */
    private static boolean matches(CustomRecipe recipe, TransientCraftingContainer craftSlots) {
        // Проверяем все 9 слотов
        for (int i = 0; i < 9; i++) {
            ItemStack recipeStack = recipe.pattern[i];
            ItemStack slotStack = craftSlots.getItem(i);

            // Если в рецепте пустой слот
            if (recipeStack == null || recipeStack.isEmpty()) {
                if (!slotStack.isEmpty()) {
                    return false; // В слоте есть предмет, а должен быть пусто
                }
            } else {
                // В рецепте есть предмет
                if (slotStack.isEmpty()) {
                    return false; // Слот пуст, а должен быть предмет
                }
                if (slotStack.getItem() != recipeStack.getItem()) {
                    return false; // Не тот предмет
                }
                // Проверяем количество (должно быть достаточно)
                if (slotStack.getCount() < recipeStack.getCount()) {
                    return false;
                }
            }
        }
        return true; // Все слоты совпадают!
    }

    // ──────────────────────────────────────────────────
    // ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ДЛЯ УДОБНОГО СОЗДАНИЯ РЕЦЕПТОВ
    // ──────────────────────────────────────────────────

    /**
     * Создать массив предметов из строкового паттерна
     */
    public static ItemStack[] createPattern(String[] pattern, java.util.Map<Character, Item> keyMap) {
        if (pattern.length != 3) {
            throw new IllegalArgumentException("Pattern must have 3 rows");
        }

        ItemStack[] slots = new ItemStack[9];
        for (int row = 0; row < 3; row++) {
            if (pattern[row].length() != 3) {
                throw new IllegalArgumentException("Each row must have 3 characters");
            }
            for (int col = 0; col < 3; col++) {
                char c = pattern[row].charAt(col);
                if (c == ' ') {
                    slots[row * 3 + col] = ItemStack.EMPTY;
                } else {
                    Item item = keyMap.get(c);
                    if (item == null) {
                        throw new IllegalArgumentException("Unknown character: " + c);
                    }
                    slots[row * 3 + col] = new ItemStack(item);
                }
            }
        }
        return slots;
    }

    /**
     * Упрощённый паттерн из двумерного массива символов
     */
    public static ItemStack[] createPatternFromChars(char[][] pattern, java.util.Map<Character, Item> keyMap) {
        if (pattern.length != 3) {
            throw new IllegalArgumentException("Pattern must have 3 rows");
        }

        ItemStack[] slots = new ItemStack[9];
        for (int row = 0; row < 3; row++) {
            if (pattern[row].length != 3) {
                throw new IllegalArgumentException("Each row must have 3 characters");
            }
            for (int col = 0; col < 3; col++) {
                char c = pattern[row][col];
                if (c == ' ') {
                    slots[row * 3 + col] = ItemStack.EMPTY;
                } else {
                    Item item = keyMap.get(c);
                    if (item == null) {
                        throw new IllegalArgumentException("Unknown character: " + c);
                    }
                    slots[row * 3 + col] = new ItemStack(item);
                }
            }
        }
        return slots;
    }
}