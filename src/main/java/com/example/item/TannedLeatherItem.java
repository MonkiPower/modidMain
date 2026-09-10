package com.example.item;

import net.minecraft.world.item.Item;

public class TannedLeatherItem extends Item {
    public TannedLeatherItem()
    {
        super(new Item.Properties()
                .stacksTo(64) // Максимум 16 в стаке
        );
    }
}
