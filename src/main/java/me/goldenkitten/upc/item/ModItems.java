package me.goldenkitten.upc.item;

import me.goldenkitten.upc.UPCReloaded;
import me.goldenkitten.upc.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UPCReloaded.MODID);

    public static final DeferredItem<BlockItem> UPC_BLOCK_ITEM =
            ITEMS.registerSimpleBlockItem(ModBlocks.UPC_BLOCK);
    public static final DeferredItem<Item> UPC_TIER_1 = ITEMS.registerSimpleItem("upc_tier_1_upgrade",
            new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> UPC_TIER_2 = ITEMS.registerSimpleItem("upc_tier_2_upgrade",
            new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> UPC_TIER_3 = ITEMS.registerSimpleItem("upc_tier_3_upgrade",
            new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> UPC_TIER_4 = ITEMS.registerSimpleItem("upc_tier_4_upgrade",
            new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> UPC_TIER_5 = ITEMS.registerSimpleItem("upc_tier_5_upgrade",
            new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> UPC_TIER_6 = ITEMS.registerSimpleItem("upc_tier_6_upgrade",
            new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> UPC_TIER_7 = ITEMS.registerSimpleItem("upc_tier_7_upgrade",
            new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> UPC_TIER_8 = ITEMS.registerSimpleItem("upc_tier_8_upgrade",
            new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> UPC_TIER_9 = ITEMS.registerSimpleItem("upc_tier_9_upgrade",
            new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> UPC_TIER_10 = ITEMS.registerSimpleItem("upc_tier_10_upgrade",
            new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> UPC_TIER_CREATIVE = ITEMS.registerSimpleItem("upc_tier_creative_upgrade",
            new Item.Properties().stacksTo(1));
}