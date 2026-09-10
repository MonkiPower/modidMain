package com.example.item;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;

public class BlacksmithHammerItem extends PickaxeItem {

    public BlacksmithHammerItem() {
        super(BlacksmithHammerToolMaterial.INSTANCE, 3, -2.8f, new Properties()
                .stacksTo(1));
    }

    // Материал инструмента
    private static class BlacksmithHammerToolMaterial implements Tier {
        public static final BlacksmithHammerToolMaterial INSTANCE = new BlacksmithHammerToolMaterial();

        @Override
        public int getUses() {
            return 100; // Прочность
        }

        @Override
        public float getSpeed() {
            return 1.0f; // Скорость добычи
        }

        @Override
        public float getAttackDamageBonus() {
            return 2.0f; // Базовый урон
        }

        @Override
        public int getLevel() {
            return 0; // Уровень добычи (-)
        }

        @Override
        public int getEnchantmentValue() {
            return 0; // Зачаровываемость (-)
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.IRON_INGOT); // Чинится железом
        }
    }
}