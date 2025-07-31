package ooo.foooooooooooo.upc.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ooo.foooooooooooo.upc.Upc;

public final class ModBlocks {
    public static final Block UPC_BLOCK = new UPCBlock();

    public static void registerBlocks() {
        register("upc", UPC_BLOCK, true);
    }

    public static void register(String name, Block block, boolean shouldRegisterItem) {
        // Register the block and its item.
        Identifier id = new Identifier(Upc.MOD_ID, name);

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:air` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        Registry.register(Registries.BLOCK, id, block);
    }
}
