package me.goldenkitten.upc.item;

import me.goldenkitten.upc.Upc;
import me.goldenkitten.upc.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item UPC_TIER_1_UPGRADE = register(
        // Ignore the food component for now, we'll cover it later in the food section.
        new UpcUpgradeItem(new FabricItemSettings().fireproof().maxCount(1), 1),
        "upc_tier_1_upgrade"
    );
    public static final Item UPC_TIER_2_UPGRADE = register(
        // Ignore the food component for now, we'll cover it later in the food section.
        new UpcUpgradeItem(new FabricItemSettings().fireproof().maxCount(1), 2),
        "upc_tier_2_upgrade"
    );
    public static final Item UPC_TIER_3_UPGRADE = register(
        // Ignore the food component for now, we'll cover it later in the food section.
        new UpcUpgradeItem(new FabricItemSettings().fireproof().maxCount(1), 3),
        "upc_tier_3_upgrade"
    );
    public static final Item UPC_TIER_4_UPGRADE = register(
        // Ignore the food component for now, we'll cover it later in the food section.
        new UpcUpgradeItem(new FabricItemSettings().fireproof().maxCount(1), 4),
        "upc_tier_4_upgrade"
    );
    public static final Item UPC_TIER_5_UPGRADE = register(
        // Ignore the food component for now, we'll cover it later in the food section.
        new UpcUpgradeItem(new FabricItemSettings().fireproof().maxCount(1), 5),
        "upc_tier_5_upgrade"
    );
    public static final Item UPC_TIER_6_UPGRADE = register(
        // Ignore the food component for now, we'll cover it later in the food section.
        new UpcUpgradeItem(new FabricItemSettings().fireproof().maxCount(1), 6),
        "upc_tier_6_upgrade"
    );
    public static final Item UPC_TIER_7_UPGRADE = register(
        // Ignore the food component for now, we'll cover it later in the food section.
        new UpcUpgradeItem(new FabricItemSettings().fireproof().maxCount(1), 7),
        "upc_tier_7_upgrade"
    );
    public static final Item UPC_TIER_8_UPGRADE = register(
        // Ignore the food component for now, we'll cover it later in the food section.
        new UpcUpgradeItem(new FabricItemSettings().fireproof().maxCount(1), 8),
        "upc_tier_8_upgrade"
    );
    public static final Item UPC_TIER_9_UPGRADE = register(
        // Ignore the food component for now, we'll cover it later in the food section.
        new UpcUpgradeItem(new FabricItemSettings().fireproof().maxCount(1), 9),
        "upc_tier_9_upgrade"
    );
    public static final Item UPC_TIER_10_UPGRADE = register(
        // Ignore the food component for now, we'll cover it later in the food section.
        new UpcUpgradeItem(new FabricItemSettings().fireproof().maxCount(1), 10),
        "upc_tier_10_upgrade"
    );

    public static final Item UPC_TIER_CREATIVE_UPGRADE = register(
        // Ignore the food component for now, we'll cover it later in the food section.
        new UpcUpgradeItem(new FabricItemSettings().fireproof().maxCount(1), 0),
        "upc_tier_creative_upgrade"
    );

    public static Item register(Item item, String id) {
        Identifier itemID = new Identifier(Upc.MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ModBlocks.UPC_ITEM_GROUP_KEY)
            .register((itemGroup) -> itemGroup.add(ModItems.UPC_TIER_1_UPGRADE));
        ItemGroupEvents.modifyEntriesEvent(ModBlocks.UPC_ITEM_GROUP_KEY)
            .register((itemGroup) -> itemGroup.add(ModItems.UPC_TIER_2_UPGRADE));
        ItemGroupEvents.modifyEntriesEvent(ModBlocks.UPC_ITEM_GROUP_KEY)
            .register((itemGroup) -> itemGroup.add(ModItems.UPC_TIER_3_UPGRADE));
        ItemGroupEvents.modifyEntriesEvent(ModBlocks.UPC_ITEM_GROUP_KEY)
            .register((itemGroup) -> itemGroup.add(ModItems.UPC_TIER_4_UPGRADE));
        ItemGroupEvents.modifyEntriesEvent(ModBlocks.UPC_ITEM_GROUP_KEY)
            .register((itemGroup) -> itemGroup.add(ModItems.UPC_TIER_5_UPGRADE));
        ItemGroupEvents.modifyEntriesEvent(ModBlocks.UPC_ITEM_GROUP_KEY)
            .register((itemGroup) -> itemGroup.add(ModItems.UPC_TIER_6_UPGRADE));
        ItemGroupEvents.modifyEntriesEvent(ModBlocks.UPC_ITEM_GROUP_KEY)
            .register((itemGroup) -> itemGroup.add(ModItems.UPC_TIER_7_UPGRADE));
        ItemGroupEvents.modifyEntriesEvent(ModBlocks.UPC_ITEM_GROUP_KEY)
            .register((itemGroup) -> itemGroup.add(ModItems.UPC_TIER_8_UPGRADE));
        ItemGroupEvents.modifyEntriesEvent(ModBlocks.UPC_ITEM_GROUP_KEY)
            .register((itemGroup) -> itemGroup.add(ModItems.UPC_TIER_9_UPGRADE));
        ItemGroupEvents.modifyEntriesEvent(ModBlocks.UPC_ITEM_GROUP_KEY)
            .register((itemGroup) -> itemGroup.add(ModItems.UPC_TIER_10_UPGRADE));
        ItemGroupEvents.modifyEntriesEvent(ModBlocks.UPC_ITEM_GROUP_KEY)
            .register((itemGroup) -> itemGroup.add(ModItems.UPC_TIER_CREATIVE_UPGRADE));
    }
}
