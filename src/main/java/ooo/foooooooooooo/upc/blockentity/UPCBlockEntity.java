package ooo.foooooooooooo.upc.blockentity;

import aztech.modern_industrialization.api.energy.CableTier;
import aztech.modern_industrialization.api.energy.MIEnergyStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class UPCBlockEntity extends BlockEntity implements MIEnergyStorage, UPCStorage {
    public final SimpleEnergyStorage storage = new SimpleEnergyStorage(16_384, 16_384, 16_384);

    public UPCBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.UPC_BLOCK_ENTITY, pos, state);
    }

    @Override
    @SuppressWarnings("UnstableApiUsage")
    public long extract(long maxAmount, TransactionContext transaction) {
        return storage.extract(maxAmount, transaction);
    }

    @Override
    @SuppressWarnings("UnstableApiUsage")
    public long insert(long maxAmount, TransactionContext transaction) {
        return storage.insert(maxAmount, transaction);
    }

    @Override
    public boolean canConnect(CableTier cableTier) {
        return true;
    }

    @Override
    public long getAmount() {
        return storage.getAmount();
    }

    @Override
    public long getStored() {
        return storage.getAmount();
    }

    @Override
    public long getCapacity() {
        return storage.getCapacity();
    }
}
