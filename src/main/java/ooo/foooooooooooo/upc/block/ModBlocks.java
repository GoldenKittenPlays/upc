package ooo.foooooooooooo.upc.block;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ooo.foooooooooooo.upc.Upc;

public final class ModBlocks {
    public static final Block UPC_BLOCK = new UPCBlock();
    public static final RegistryKey<ItemGroup> UPC_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), new Identifier(Upc.MOD_ID, "item_group"));
    public static final ItemGroup UPC_ITEM_GROUP = FabricItemGroup.builder()
        .icon(() -> new ItemStack(UPC_BLOCK))
        .displayName(Text.translatable("itemGroup.upc"))
        .build();

    public static void registerItemGroup() {
        Registry.register(Registries.ITEM_GROUP, UPC_ITEM_GROUP_KEY, UPC_ITEM_GROUP);
    }

    public static void registerBlocks() {
        register("upc", UPC_BLOCK, new Item.Settings(), true);
    }

    public static void register(String name, Block block, Item.Settings settings, boolean shouldRegisterItem) {
        // Register the block and its item.
        Identifier id = new Identifier(Upc.MOD_ID, name);

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:air` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, settings);
            Registry.register(Registries.ITEM, id, blockItem);
            // Register items to the custom item group.
            ItemGroupEvents.modifyEntriesEvent(UPC_ITEM_GROUP_KEY).register(itemGroup -> itemGroup.add(blockItem));
        }

        Registry.register(Registries.BLOCK, id, block);
    }
}
