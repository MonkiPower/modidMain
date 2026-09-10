package com.example.item.armor;

import com.example.armor.GambesonArmorMaterial;
import net.minecraft.world.item.ArmorItem;

public class GambesonChestplateItem extends ArmorItem {
    public GambesonChestplateItem() {
        super(GambesonArmorMaterial.INSTANCE, Type.CHESTPLATE, new Properties()
                .stacksTo(1)
        );
    }
}