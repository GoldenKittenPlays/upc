package me.goldenkitten.upc.blockentity;

import aztech.modern_industrialization.api.energy.EnergyApi;
import me.goldenkitten.upc.UPC;
import me.goldenkitten.upc.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import team.reborn.energy.api.EnergyStorage;

public final class ModBlockEntities {
    public static void registerBlockEntities() {
        register("upc", UPC_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.storage, UPC_BLOCK_ENTITY);
        EnergyApi.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity, UPC_BLOCK_ENTITY);
    }

    @SuppressWarnings({"SameParameterValue"})
    private static void register(String path, BlockEntityType block) {
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, UPC.id(path), block);
    }

    public static final BlockEntityType<UPCBlockEntity> UPC_BLOCK_ENTITY =
            FabricBlockEntityTypeBuilder.create(UPCBlockEntity::new, ModBlocks.UPC_BLOCK).build();
}
