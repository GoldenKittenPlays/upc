package me.goldenkitten.upc.blockentity;

import aztech.modern_industrialization.api.energy.CableTier;
import aztech.modern_industrialization.api.energy.MIEnergyStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class UPCBlockEntity extends BlockEntity implements MIEnergyStorage, UPCStorage {
    public SimpleEnergyStorage storage = new SimpleEnergyStorage(20_000, 20_000, 20_000);
    public int tier;

    public UPCBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.UPC_BLOCK_ENTITY, pos, state);
        this.setTier(-1);
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

    // Save data
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("Tier", this.tier);
        nbt.putLong("Energy", this.storage.getAmount());
    }

    // Load data
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.setTier(nbt.getInt("Tier")); // restore storage capacity based on tier
        this.storage.amount = nbt.getLong("Energy");
    }

    public void setTier(int newTier) {
        this.tier = newTier;
        long am = this.getStored();
        this.setStorage(this.getTier());
        this.storage.amount = am;
        markDirty(); // Mark as changed so it gets saved
        this.sync();
    }

    public int getTier() {
        return this.tier;
    }

    public void setStorage(int tier) {
        switch(tier) {
            case -1:
                this.storage = new SimpleEnergyStorage(20_000, 20_000, 20_000);
                break;
            case 0:
                this.storage = new SimpleEnergyStorage(Long.MAX_VALUE - 1, Long.MAX_VALUE - 1, Long.MAX_VALUE - 1);
                break;
            case 1:
                this.storage = new SimpleEnergyStorage(50_000, 50_000, 50_000);
                break;
            case 2:
                this.storage = new SimpleEnergyStorage(100_000, 100_000, 100_000);
                break;
            case 3:
                this.storage = new SimpleEnergyStorage(250_000, 250_000, 250_000);
                break;
            case 4:
                this.storage = new SimpleEnergyStorage(500_000, 500_000, 500_000);
                break;
            case 5:
                this.storage = new SimpleEnergyStorage(1_000_000, 1_000_000, 1_000_000);
                break;
            case 6:
                this.storage = new SimpleEnergyStorage(5_000_000, 5_000_000, 5_000_000);
                break;
            case 7:
                this.storage = new SimpleEnergyStorage(25_000_000, 25_000_000, 25_000_000);
                break;
            case 8:
                this.storage = new SimpleEnergyStorage(100_000_000, 100_000_000, 100_000_000);
                break;
            case 9:
                this.storage = new SimpleEnergyStorage(500_000_000, 500_000_000, 500_000_000);
                break;
            case 10:
                this.storage = new SimpleEnergyStorage(1_000_000_000, 1_000_000_000, 1_000_000_000);
                break;
        }
    }

    public void sync() {
        if (world != null) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }
}
