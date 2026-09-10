package com.example.world;

import com.example.ExampleMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModOreGeneration {

    public static void generateOres() {
        // ResourceKey для твоей руды
        ResourceKey<PlacedFeature> oreKey = ResourceKey.create(
                Registries.PLACED_FEATURE,
                new ResourceLocation(ExampleMod.MOD_ID, "ore_alkaline_powder")
        );

        // Добавляем в мир
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                oreKey
        );

        System.out.println("✅ Ore generation added!");
    }
}