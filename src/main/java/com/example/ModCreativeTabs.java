package com.example;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {

    // ВКЛАДКА 1: КУЗНЕЧНАЯ (все кузнечные предметы)
    public static final CreativeModeTab SMITHING_TAB = FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.modid.smithing"))
            .icon(() -> new ItemStack(ExampleMod.BLACKSMITH_HAMMER))
            .displayItems((parameters, output) -> {
                // Кузнечные предметы
                output.accept(ExampleMod.BLACKSMITH_HAMMER);
                output.accept(ExampleMod.STEEL_RING);
                output.accept(ExampleMod.STEEL_PLATE);
                output.accept(ExampleMod.CUSTOM_CRAFTING_TABLE_ITEM);
                output.accept(ExampleMod.TANNED_LEATHER);
                output.accept(ExampleMod.CUSTOM_FURNACE_BLOCK_ITEM);
                // Броня
                output.accept(ExampleMod.GAMBESON_HELMET);
                output.accept(ExampleMod.GAMBESON_CHESTPLATE);
                output.accept(ExampleMod.GAMBESON_LEGGINGS);
                output.accept(ExampleMod.GAMBESON_BOOTS);
            })
            .build();

    // ВКЛАДКА 2: РУДЫ (все руды и связанные предметы)
    public static final CreativeModeTab ORES_TAB = FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.modid.ores"))
            .icon(() -> new ItemStack(ExampleMod.ALKALINE_POWDER_BLOCK_ITEM))
            .displayItems((parameters, output) -> {
                // Руды и порошки
                output.accept(ExampleMod.ALKALINE_POWDER_BLOCK_ITEM);
                output.accept(ExampleMod.ALKALINE_POWBER);

                //Monki: Добавь и другие руды позже
            })
            .build();

    public static void register() {
        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                new ResourceLocation(ExampleMod.MOD_ID, "smithing"),
                SMITHING_TAB
        );

        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                new ResourceLocation(ExampleMod.MOD_ID, "ores"),
                ORES_TAB
        );
    }
}