package com.example.armor;

import com.example.ExampleMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class GambesonArmorMaterial implements ArmorMaterial {

    public static final GambesonArmorMaterial INSTANCE = new GambesonArmorMaterial();

    private static final int[] BASE_DURABILITY = {11, 16, 15, 13}; // Ботинки, Поножи, Нагрудник, Шлем
    private static final int[] PROTECTION = {1, 2, 3, 1}; // Защита: ботинки, поножи, нагрудник, шлем

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * 15;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return PROTECTION[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return 15; // Зачаровываемость
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(ExampleMod.STEEL_PLATE);
    }

    @Override
    public String getName() {
        return "gambeson";
    }

    @Override
    public float getToughness() {
        return 0.5f; // Сопротивление урону
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0f; // Сопротивление отбрасыванию
    }
}