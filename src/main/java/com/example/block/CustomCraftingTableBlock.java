package com.example.block;

import com.example.ModMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class CustomCraftingTableBlock extends Block {

    private static final Component TITLE = Component.translatable("container.modid.custom_crafting_table");

    public CustomCraftingTableBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .strength(2.5f, 3.0f)
                .sound(SoundType.WOOD)
        );
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        player.openMenu(new SimpleMenuProvider(
                (syncId, inventory, p) -> new CustomCraftingTableMenu(syncId, inventory),
                TITLE
        ));

        return InteractionResult.CONSUME;
    }
}