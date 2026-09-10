package com.example.item.armor;

import com.example.armor.GambesonArmorMaterial;
import net.minecraft.world.item.ArmorItem;

public class GambesonLeggingsItem extends ArmorItem {
    public GambesonLeggingsItem() {
        super(GambesonArmorMaterial.INSTANCE, Type.LEGGINGS, new Properties()
                .stacksTo(1)
        );
    }
}