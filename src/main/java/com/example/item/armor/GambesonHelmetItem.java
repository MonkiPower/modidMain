package com.example.item.armor;

import com.example.armor.GambesonArmorMaterial;
import net.minecraft.world.item.ArmorItem;

public class GambesonHelmetItem extends ArmorItem {
    public GambesonHelmetItem() {
        super(GambesonArmorMaterial.INSTANCE, Type.HELMET, new Properties()
                .stacksTo(1)
        );
    }
}
