package me.goldenkitten.upc;

import me.goldenkitten.upc.block.ModBlocks;
import me.goldenkitten.upc.blockentity.ModBlockEntities;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UPC implements ModInitializer {
	public static final String MOD_ID = "upc";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
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