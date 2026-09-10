package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
    public static final String MOD_ID = "modid";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Создаём экземпляр молота
    public static final Item HAMMER = new HammerItem(
        ModMaterials.HAMMER_MATERIAL,
        new Item.Settings().maxCount(1)
    );

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");

        // Регистрируем предмет
        Registry.register(
            Registries.ITEM,
            new Identifier(MOD_ID, "hammer"),
            HAMMER
        );

        // Добавляем в творческий инвентарь (вкладка "Инструменты")
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
            .register(content -> content.add(HAMMER));

        LOGGER.info("Кузнечный молот загружен!");
    }

    // Вспомогательный метод для создания Identifier
    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}