package com.example.block;

import com.example.ExampleMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AlkalinePowderBlock extends Block {

    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public AlkalinePowderBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.SAND)
                .strength(1.5f, 6.0f) // Твёрдость как у камня (1.5) и сопротивление взрыву (6.0)
                .sound(SoundType.STONE) // Звук как у камня
                .requiresCorrectToolForDrops() // Нужна правильная кирка
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        ItemStack tool = player.getMainHandItem();

        // Если нет инструмента или это не кирка - очень медленно
        if (tool.isEmpty() || !(tool.getItem() instanceof PickaxeItem)) {
            return 0.005f; // Руками как камень
        }

        PickaxeItem pickaxe = (PickaxeItem) tool.getItem();
        int toolLevel = pickaxe.getTier().getLevel();

        // Скорость добычи как у камня для разных кирок
        return switch (toolLevel) {
            case 0 -> 0.01f;  // Деревянная - очень медленно (как камень деревянной)
            case 1 -> 0.1f;  // Каменная - нормальная скорость
            case 2 -> 0.2f;  // Железная - быстрее
            case 3 -> 0.3f;  // Алмазная - ещё быстрее
            case 4 -> 0.4f;  // Незеритовая - самая быстрая
            default -> 0.005f;
        };
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        // Проверяем, что блок добыт правильным инструментом
        ItemStack tool = player.getMainHandItem();

        if (!level.isClientSide && tool.getItem() instanceof PickaxeItem pickaxe) {
            int toolLevel = pickaxe.getTier().getLevel();

            // Только каменная кирка и выше (уровень >= 1)
            if (toolLevel >= 1) {
                // Выпадает 1-3 порошка
                int count = 4;

                // Создаём предмет на земле
                net.minecraft.world.entity.item.ItemEntity itemEntity =
                        new net.minecraft.world.entity.item.ItemEntity(
                                level,
                                pos.getX() + 0.5,
                                pos.getY() + 0.5,
                                pos.getZ() + 0.5,
                                new ItemStack(ExampleMod.ALKALINE_POWBER, count)
                        );
                level.addFreshEntity(itemEntity);
            }
        }

        super.playerWillDestroy(level, pos, state, player);
    }
}