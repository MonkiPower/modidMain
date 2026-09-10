package com.example;

import com.example.block.AlkalinePowderBlock;
import com.example.block.CustomCraftingTableBlock;
import com.example.block.CustomFurnaceBlock;
import com.example.item.*;
import com.example.item.armor.GambesonBootsItem;
import com.example.item.armor.GambesonChestplateItem;
import com.example.item.armor.GambesonHelmetItem;
import com.example.item.armor.GambesonLeggingsItem;
import com.example.world.ModOreGeneration;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	public static final String MOD_ID = "modid";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Предметы
	public static final Item BLACKSMITH_HAMMER = new BlacksmithHammerItem();
	public static final Item STEEL_RING = new SteelRingItem();
	public static final Item STEEL_PLATE = new SteelPlateItem();
	public static final Item ALKALINE_POWBER = new AlkalinePowderItem();
	public static final Item TANNED_LEATHER = new TannedLeatherItem();

	// Броня "Стёганка"
	public static final Item GAMBESON_HELMET = new GambesonHelmetItem();
	public static final Item GAMBESON_CHESTPLATE = new GambesonChestplateItem();
	public static final Item GAMBESON_LEGGINGS = new GambesonLeggingsItem();
	public static final Item GAMBESON_BOOTS = new GambesonBootsItem();

	// Блоки
	public static final Block CUSTOM_CRAFTING_TABLE_BLOCK = new CustomCraftingTableBlock();
	public static final Item CUSTOM_CRAFTING_TABLE_ITEM = new BlockItem(CUSTOM_CRAFTING_TABLE_BLOCK, new Item.Properties());

	public static final Block ALKALINE_POWDER_BLOCK = new AlkalinePowderBlock();
	public static final Item ALKALINE_POWDER_BLOCK_ITEM = new BlockItem(ALKALINE_POWDER_BLOCK, new Item.Properties());

	public static final Block CUSTOM_FURNACE_BLOCK = new CustomFurnaceBlock();
	public static final Item CUSTOM_FURNACE_BLOCK_ITEM = new BlockItem(CUSTOM_FURNACE_BLOCK, new Item.Properties());
	@Override
	public void onInitialize() {
		ModMenu.register();
		ModCreativeTabs.register();

		// Регистрация предметов
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "blacksmith_hammer"), BLACKSMITH_HAMMER);
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "steel_ring"), STEEL_RING);
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "steel_plate"), STEEL_PLATE);
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "alkaline_powder"), ALKALINE_POWBER);
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "tanned_leather"), TANNED_LEATHER);

		// Регистрация брони
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "gambeson_helmet"), GAMBESON_HELMET);
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "gambeson_chestplate"), GAMBESON_CHESTPLATE);
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "gambeson_leggings"), GAMBESON_LEGGINGS);
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "gambeson_boots"), GAMBESON_BOOTS);

		// Регистрация блоков
		Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MOD_ID, "custom_crafting_table"), CUSTOM_CRAFTING_TABLE_BLOCK);
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "custom_crafting_table"), CUSTOM_CRAFTING_TABLE_ITEM);

		Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MOD_ID, "alkaline_powder_block"), ALKALINE_POWDER_BLOCK);
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "alkaline_powder_block"), ALKALINE_POWDER_BLOCK_ITEM);

		Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MOD_ID, "custom_furnace"), CUSTOM_FURNACE_BLOCK);
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "custom_furnace"), CUSTOM_FURNACE_BLOCK_ITEM);

		ModOreGeneration.generateOres();
		LOGGER.info("Проект загружен успешно");
	}
}