package me.goldenkitten.upc.blockentity;

import appeng.api.config.Actionable;
import appeng.api.config.PowerMultiplier;
import appeng.api.networking.IGridNode;
import appeng.api.networking.energy.IEnergyService;
import appeng.api.util.AECableType;
import appeng.blockentity.grid.AENetworkedBlockEntity;
import dev.technici4n.grandpower.api.ILongEnergyStorage;
import me.goldenkitten.upc.ModDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class UPCBlockEntity extends AENetworkedBlockEntity implements ILongEnergyStorage, UPCStorage {
    public long amount = 0;
    public long capacity = 20_000;
    public int tier;

    public UPCBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.UPC_BLOCK_ENTITY.get(), pos, state);
        this.getMainNode().setIdlePowerUsage(0);
        this.setTier(-1);
    }

    @Override
    public AECableType getCableConnectionType(Direction dir) {
        return AECableType.COVERED;
    }

    // Called from UPCBlock's BlockEntityTicker
    public void tick() {
        if (level == null || level.isClientSide) return;
        IGridNode node = getMainNode().getNode();
        if (node == null) return;
        getMainNode().setGridColor(node.getGridColor());

        if (!node.isActive()) return;

        IEnergyService energyService = node.getGrid().getEnergyService();
        if (energyService == null) return;

        long freeSpace = capacity - amount;
        if (freeSpace <= 0) return;

        double toPull = Math.min(freeSpace, capacity);
        double extracted = energyService.extractAEPower(toPull, Actionable.MODULATE, PowerMultiplier.ONE);
        if (extracted > 0) {
            amount += (long) extracted;
            setChanged();
        }
    }

    @Override
    public long receive(long maxReceive, boolean simulate) {
        long accepted = Math.min(maxReceive, capacity - amount);
        if (!simulate && accepted > 0) {
            amount += accepted;
            setChanged();
        }
        return accepted;
    }

    @Override
    public long extract(long maxExtract, boolean simulate) {
        long extracted = Math.min(maxExtract, amount);
        if (!simulate && extracted > 0) {
            amount -= extracted;
            setChanged();
        }
        return extracted;
    }

    @Override
    public long getAmount() {
        return amount;
    }

    @Override
    public long getCapacity() {
        return capacity;
    }

    // UPCStorage (used by your Megane provider — getStored() just mirrors getAmount())
    @Override
    public long getStored() {
        return amount;
    }

    @Override
    public void loadTag(CompoundTag data, HolderLookup.Provider registries) {
        super.loadTag(data, registries);
        this.setTier(data.getInt("Tier"));
        this.amount = data.getLong("Energy");
        this.getMainNode().loadFromNBT(data);
    }

    @Override
    public void saveAdditional(CompoundTag data, HolderLookup.Provider registries) {
        super.saveAdditional(data, registries);
        data.putInt("Tier", this.tier);
        data.putLong("Energy", this.amount);
    }

    public void setTier(int newTier) {
        this.tier = newTier;
        long stored = this.amount;
        this.setCapacityForTier(newTier);
        this.amount = Math.min(stored, capacity);
        setChanged();
    }

    public int getTier() {
        return this.tier;
    }

    public void setCapacityForTier(int tier) {
        this.capacity = switch (tier) {
            case -1 -> 20_000L;
            case 0 -> Integer.MAX_VALUE - 1;
            case 1 -> 40_000L;
            case 2 -> 60_000L;
            case 3 -> 80_000L;
            case 4 -> 100_000L;
            case 5 -> 200_000L;
            case 6 -> 500_000L;
            case 7 -> 1_000_000L;
            case 8 -> 5_000_000L;
            case 9 -> 10_000_000L;
            case 10 -> 25_000_000L;
            default -> capacity;
        };
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.@NotNull Builder builder) {
        super.collectImplicitComponents(builder);
        builder.set(ModDataComponents.TIER.get(), this.tier);
        builder.set(ModDataComponents.ENERGY.get(), this.amount);
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.@NotNull DataComponentInput input) {
        super.applyImplicitComponents(input);
        this.setTier(input.getOrDefault(ModDataComponents.TIER.get(), -1));
        this.amount = input.getOrDefault(ModDataComponents.ENERGY.get(), 0L);
    }
}