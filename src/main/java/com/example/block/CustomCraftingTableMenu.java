package com.example.block;

import com.example.ExampleMod;
import com.example.ModMenu;
import com.example.recipe.CustomRecipeManager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

public class CustomCraftingTableMenu extends AbstractContainerMenu {

    private final Level level;
    private final TransientCraftingContainer craftSlots;
    private final ResultContainer resultSlots = new ResultContainer();

    // Статический блок для регистрации рецептов (выполняется 1 раз при загрузке)
    static {
        registerRecipes();
    }

    // ──────────────────────────────────────────────────
    // РЕГИСТРАЦИЯ ВСЕХ КАСТОМНЫХ РЕЦЕПТОВ
    // ──────────────────────────────────────────────────
    private static void registerRecipes() {
        // ─── ПЛАСТИНА ───
        // 4 железных слитка в квадрате → 4 стальные пластины
        Map<Character, Item> plateKeyMap = new HashMap<>();
        plateKeyMap.put('I', Items.IRON_INGOT);

        String[] platePattern = new String[]{
                "II ",
                "II ",
                "   "
        };

        CustomRecipeManager.addRecipeFromPattern(
                platePattern,
                plateKeyMap,
                ExampleMod.STEEL_PLATE,
                4
        );

        // ─── КОЛЬЦО ───
        // 4 кусочка железа в форме кольца → 4 стальных кольца
        Map<Character, Item> ringKeyMap = new HashMap<>();
        ringKeyMap.put('N', Items.IRON_NUGGET);

        String[] ringPattern = new String[]{
                " N ",
                "N N",
                " N "
        };
        CustomRecipeManager.addRecipeFromPattern(
                ringPattern,
                ringKeyMap,
                ExampleMod.STEEL_RING,
                4
        );
        // ─── Кальчуга ───
        Map<Character, Item> ChainHelmetMap = new HashMap<>();
        ChainHelmetMap.put('N', ExampleMod.STEEL_RING);
        String[] ChainHelmet = new String[]{
                "NNN",
                "N N",
                "   "
        };
        CustomRecipeManager.addRecipeFromPattern(
                ChainHelmet,
                ChainHelmetMap,
                Items.CHAINMAIL_HELMET,
                1
        );
        // ───  ───
        Map<Character, Item> ChainBreastplateMap = new HashMap<>();
        ChainBreastplateMap.put('N', ExampleMod.STEEL_RING);
        String[] ChainBreastplate = new String[]{
                "N N",
                "NNN",
                "NNN"
        };
        CustomRecipeManager.addRecipeFromPattern(
                ChainBreastplate,
                ChainBreastplateMap,
                Items.CHAINMAIL_CHESTPLATE,
                1
        );
        // ───  ───
        Map<Character, Item> ChainLeggingsMap = new HashMap<>();
        ChainLeggingsMap.put('N', ExampleMod.STEEL_RING);
        String[] ChainLeggings = new String[]{
                "NNN",
                "N N",
                "N N"
        };
        CustomRecipeManager.addRecipeFromPattern(
                ChainLeggings,
                ChainLeggingsMap,
                Items.CHAINMAIL_LEGGINGS,
                1
        );
        // ───  ───
        Map<Character, Item> ChainBootsMap = new HashMap<>();
        ChainBootsMap.put('N', ExampleMod.STEEL_RING);
        String[] ChainBoots = new String[]{
                "   ",
                "N N",
                "N N"
        };
        CustomRecipeManager.addRecipeFromPattern(
                ChainBoots,
                ChainBootsMap,
                Items.CHAINMAIL_BOOTS,
                1
        );
        // ───  ───
        Map<Character, Item> TannedLeatherMap = new HashMap<>();
        TannedLeatherMap.put('A', ExampleMod.ALKALINE_POWBER);
        TannedLeatherMap.put('L', Items.LEATHER);
        TannedLeatherMap.put('W', Items.WATER_BUCKET);
        String[] TannedLeather = new String[]{
                "LLL",
                "AAA",
                "AWA"
        };
        CustomRecipeManager.addRecipeFromPattern(
                TannedLeather,
                TannedLeatherMap,
                ExampleMod.TANNED_LEATHER,
                3
        );
        // ───  ───
        Map<Character, Item> GambesonHelmetMap = new HashMap<>();
        GambesonHelmetMap.put('S', ExampleMod.STEEL_RING);
        GambesonHelmetMap.put('T', ExampleMod.TANNED_LEATHER);
        String[] GambesonHelmet = new String[]{
                "SSS",
                "T T",
                "   "
        };
        CustomRecipeManager.addRecipeFromPattern(
                GambesonHelmet,
                GambesonHelmetMap,
                ExampleMod.GAMBESON_HELMET,
                1
        );
        // ───  ───
        Map<Character, Item> GambesonChestplateMap = new HashMap<>();
        GambesonChestplateMap.put('S', ExampleMod.STEEL_RING);
        GambesonChestplateMap.put('T', ExampleMod.TANNED_LEATHER);
        String[] GambesonChestp = new String[]{
                "T T",
                "SSS",
                "SSS"
        };
        CustomRecipeManager.addRecipeFromPattern(
                GambesonChestp,
                GambesonHelmetMap,
                ExampleMod.GAMBESON_CHESTPLATE,
                1
        );
        // ───  ───
        Map<Character, Item> GambesonLeggingsMap = new HashMap<>();
        GambesonLeggingsMap.put('S', ExampleMod.STEEL_RING);
        GambesonLeggingsMap.put('T', ExampleMod.TANNED_LEATHER);
        String[] GambesonLeggings = new String[]{
                "STS",
                "S S",
                "S S"
        };
        CustomRecipeManager.addRecipeFromPattern(
                GambesonLeggings,
                GambesonHelmetMap,
                ExampleMod.GAMBESON_LEGGINGS,
                1
        );
        // ───  ───
        Map<Character, Item> GambesonBootsMap = new HashMap<>();
        GambesonBootsMap.put('S', ExampleMod.STEEL_RING);
        GambesonBootsMap.put('T', ExampleMod.TANNED_LEATHER);
        String[] GambesonBoots = new String[]{
                "   ",
                "T T",
                "S S"
        };
        CustomRecipeManager.addRecipeFromPattern(
                GambesonBoots,
                GambesonBootsMap,
                ExampleMod.GAMBESON_BOOTS,
                1
        );
    }

    public CustomCraftingTableMenu(int syncId, Inventory playerInventory) {
        super(ModMenu.CUSTOM_CRAFTING_TABLE_MENU, syncId);
        this.level = playerInventory.player.level();
        this.craftSlots = new TransientCraftingContainer(this, 3, 3);

        // Слоты для крафта 3x3
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.addSlot(new Slot(this.craftSlots, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        // Слот результата с кастомной логикой
        this.addSlot(new ResultSlot(playerInventory.player, this.craftSlots, this.resultSlots, 0, 124, 35) {
            @Override
            public void onTake(Player player, ItemStack stack) {
                // Убираем по 1 предмету из каждого слота крафта
                for (int i = 0; i < craftSlots.getContainerSize(); ++i) {
                    ItemStack slotStack = craftSlots.getItem(i);
                    if (!slotStack.isEmpty()) {
                        slotStack.shrink(1);
                        if (slotStack.isEmpty()) {
                            craftSlots.setItem(i, ItemStack.EMPTY);
                        }
                    }
                }
                CustomCraftingTableMenu.this.slotsChanged(craftSlots);
            }
        });

        // Инвентарь игрока
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Горячая панель
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void slotsChanged(net.minecraft.world.Container container) {
        super.slotsChanged(container);

        // ПРОВЕРЯЕМ ТОЛЬКО КАСТОМНЫЕ РЕЦЕПТЫ
        ItemStack customResult = CustomRecipeManager.checkRecipes(this.craftSlots);

        if (!customResult.isEmpty()) {
            this.resultSlots.setItem(0, customResult);
        } else {
            // ВАНИЛЬНЫЕ РЕЦЕПТЫ НЕ РАБОТАЮТ!
            // Просто показываем пустой слот
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        Slot slot = this.slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            ItemStack copy = stack.copy();

            if (slotIndex == 0) {
                if (!this.moveItemStackTo(stack, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, copy);
            } else if (slotIndex >= 1 && slotIndex < 10) {
                if (!this.moveItemStackTo(stack, 10, 46, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(stack, 1, 10, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            return copy;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.clearContainer(player, this.craftSlots);
    }
}