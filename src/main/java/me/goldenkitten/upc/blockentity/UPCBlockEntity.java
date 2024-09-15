package me.goldenkitten.upc.blockentity;

import aztech.modern_industrialization.api.energy.CableTier;
import aztech.modern_industrialization.api.energy.MIEnergyStorage;
import me.goldenkitten.upc.UPC;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class UPCBlockEntity extends BlockEntity implements MIEnergyStorage, UPCStorage {
    public final SimpleEnergyStorage storage = new SimpleEnergyStorage(16_384, 16_384, 16_384);

    public UPCBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.UPC_BLOCK_ENTITY, pos, state);
    }

    @Override
    @SuppressWarnings("UnstableApiUsage")
    public long extract(long maxAmount, TransactionContext transaction) {
        try {
            Transaction tAction = transaction.openNested();
            var energy = storage.extract(maxAmount, transaction);
            transaction.addCloseCallback((context, result) -> {
                if (result.wasAborted()) {
                    // Treat as a simulation
                    UPC.LOGGER.debug("Extract transaction was simulated and aborted.");
                    tAction.abort();
                } else if (result.wasCommitted()) {
                    // Treat as a real operation
                    UPC.LOGGER.debug("Extract transaction was committed.");
                    tAction.commit();
                }
            });

            tAction.close();

            return energy;
        } catch (Exception e) {
            UPC.LOGGER.debug("Extract transaction encountered error: {}", String.valueOf(e));
        }
        return 0;
    }

    @Override
    @SuppressWarnings("UnstableApiUsage")
    public long insert(long maxAmount, TransactionContext transaction) {
        try {
            Transaction tAction;
            tAction = Transaction.openOuter();

            long energy;
            energy = storage.insert(maxAmount, transaction);
            transaction.addCloseCallback((context, result) -> {
                if (result.wasAborted()) {
                    // Treat as a simulation
                    UPC.LOGGER.debug("Insert transaction was simulated and aborted.");
                    tAction.abort();
                } else if (result.wasCommitted()) {
                    // Treat as a real operation
                    UPC.LOGGER.debug("Insert transaction was committed.");
                    tAction.commit();
                }
            });

            tAction.close();

            return energy;
        }
        catch(Exception e) {
            UPC.LOGGER.debug("Insert transaction encountered error: {}", String.valueOf(e));
        }
        return 0;
    }

    /*
    @Override
    @SuppressWarnings("UnstableApiUsage")
    public long extractEnergy(long amount, Simulation simulation) {
        var transaction = Transaction.openOuter();

        var energy = storage.extract(amount, transaction);

        if (simulation.isSimulating()) {
            transaction.abort();
        } else {
            transaction.commit();
        }

        transaction.close();

        return energy;
    }

    @Override
    @SuppressWarnings("UnstableApiUsage")
    public long insertEnergy(long amount, Simulation simulation) {
        var transaction = Transaction.openOuter();

        var energy = storage.insert(amount, transaction);

        if (simulation.isSimulating()) {
            transaction.abort();
        } else {
            transaction.commit();
        }

        transaction.close();

        return energy;
    }
     */

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

