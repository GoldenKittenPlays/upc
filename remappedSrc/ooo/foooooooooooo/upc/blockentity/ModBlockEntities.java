package ooo.foooooooooooo.upc.blockentity;

import aztech.modern_industrialization.api.energy.EnergyApi;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ooo.foooooooooooo.upc.Upc;
import ooo.foooooooooooo.upc.block.ModBlocks;
import team.reborn.energy.api.EnergyStorage;

public final class ModBlockEntities {
    public static void registerBlockEntities() {
        register("upc", UPC_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.storage, UPC_BLOCK_ENTITY);
        EnergyApi.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity, UPC_BLOCK_ENTITY);
    }

    @SuppressWarnings({ "SameParameterValue", "rawtypes" })
    private static void register(String path, BlockEntityType block) {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Upc.MOD_ID, path), block);
    }

    public static final BlockEntityType<UPCBlockEntity> UPC_BLOCK_ENTITY =
        FabricBlockEntityTypeBuilder.create(UPCBlockEntity::new, ModBlocks.UPC_BLOCK).build();
}
