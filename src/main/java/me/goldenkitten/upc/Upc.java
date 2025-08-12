package me.goldenkitten.upc;

import me.goldenkitten.upc.item.ModItems;
import net.fabricmc.api.ModInitializer;
import me.goldenkitten.upc.block.ModBlocks;
import me.goldenkitten.upc.blockentity.ModBlockEntities;

public class Upc implements ModInitializer {
    public static final String MOD_ID = "upc";

    @Override
    public void onInitialize() {
        ModBlocks.registerItemGroup();
        ModItems.initialize();
        ModBlocks.registerBlocks();
        ModBlockEntities.registerBlockEntities();
    }
}
