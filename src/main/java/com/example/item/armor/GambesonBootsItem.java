package com.example.item.armor;

import com.example.armor.GambesonArmorMaterial;
import net.minecraft.world.item.ArmorItem;

public class GambesonBootsItem extends ArmorItem {
    public GambesonBootsItem() {
        super(GambesonArmorMaterial.INSTANCE, Type.BOOTS, new Properties()
                .stacksTo(1)
        );
    }
}