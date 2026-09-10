package com.example.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class SteelRingItem extends Item {

    public SteelRingItem() {
        super(new Properties()
                .stacksTo(16) // Максимум 16 в стаке
                .rarity(Rarity.UNCOMMON) // Редкость (обычный, необычный, редкий, эпический)
        );
    }
}