package me.goldenkitten.upc.compat.megane;

import lol.bai.megane.api.provider.EnergyProvider;
import me.goldenkitten.upc.blockentity.UPCStorage;

public class UPCEnergyProvider<T extends UPCStorage> extends EnergyProvider<T> {
    @Override
    public long getStored() {
        return getObject().getStored();
    }

    @Override
    public long getMax() {
        return getObject().getCapacity();
    }
}
