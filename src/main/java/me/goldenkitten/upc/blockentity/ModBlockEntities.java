package me.goldenkitten.upc.blockentity;

import me.goldenkitten.upc.UPCReloaded;
import me.goldenkitten.upc.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, UPCReloaded.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UPCBlockEntity>> UPC_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("upc",
                    () -> BlockEntityType.Builder.of(UPCBlockEntity::new, ModBlocks.UPC_BLOCK.get()).build(null));
}