package ooo.foooooooooooo.upc;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import ooo.foooooooooooo.upc.block.ModBlocks;
import ooo.foooooooooooo.upc.blockentity.ModBlockEntities;

public class Upc implements ModInitializer {
    public static final String MOD_ID = "upc";
    //public static final CreativeModeTab TAB =
    //    FabricItemGroupBuilderImpl(new ResourceLocation(MOD_ID, "tab"), () -> new ItemStack(ModBlocks.UPC_BLOCK));

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        ModBlocks.registerBlocks();
        ModBlockEntities.registerBlockEntities();
    }
}
