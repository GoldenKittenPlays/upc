package ooo.foooooooooooo.upc;

import net.fabricmc.api.ModInitializer;
import ooo.foooooooooooo.upc.block.ModBlocks;
import ooo.foooooooooooo.upc.blockentity.ModBlockEntities;

public class Upc implements ModInitializer {
    public static final String MOD_ID = "upc";

    @Override
    public void onInitialize() {
        ModBlocks.registerItemGroup();
        ModBlocks.registerBlocks();
        ModBlockEntities.registerBlockEntities();
    }
}
