package me.goldenkitten.upc;

import dev.technici4n.grandpower.api.ILongEnergyStorage;
import me.goldenkitten.upc.blockentity.ModBlockEntities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public final class ModCapabilities {

    private ModCapabilities() {}

    public static void register(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                ILongEnergyStorage.BLOCK,
                ModBlockEntities.UPC_BLOCK_ENTITY.get(),
                (blockEntity, side) -> blockEntity
        );
    }
}