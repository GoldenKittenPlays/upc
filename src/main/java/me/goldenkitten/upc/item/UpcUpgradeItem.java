package me.goldenkitten.upc.item;

import me.goldenkitten.upc.Upc;
import me.goldenkitten.upc.blockentity.UPCBlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UpcUpgradeItem extends Item {
    private int tier;
    public UpcUpgradeItem(Settings settings, int tier) {
        super(settings);
        this.tier = tier;
        this.setTier(this.tier);
    }

    public void setTier(int newTier) {
        this.tier = newTier;
        NbtCompound nbt = this.getDefaultStack().getOrCreateNbt();
        nbt.putInt("Tier", this.tier);
    }

    public int getTier() {
        return this.tier;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        // Get the world and block position
        var world = context.getWorld();
        var pos = context.getBlockPos();

        // Get the block state and block
        var state = world.getBlockState(pos);
        var block = state.getBlock();

        // Example: print the block's name
        System.out.println("Clicked block: " + block);

        // Get the item name (current code)
        String itemName = context.getStack().getItem().getName().getString();
        String blockName = block.getName().getString();

        if (itemName.contains("Upc Upgrade") && blockName.equalsIgnoreCase("UPC")) {
            // This is incorrect: Integer.getInteger reads a *system property*, not from the string
            // You likely want Integer.parseInt with a cleaned-up string
            try {
                String tierName = itemName.split("Upc Upgrade ")[1];
                int itemTier;
                if (!tierName.equalsIgnoreCase("Creative")) {
                    itemTier = Integer.parseInt(itemName.split("Upc Upgrade ")[1]); // only digits
                    int blockTier = -2;
                    var blockEntity = world.getBlockEntity(pos);
                    if (blockEntity != null) {
                        // Example: check if it's your custom UPC block entity
                        if (blockEntity instanceof UPCBlockEntity upcEntity) {
                            blockTier = upcEntity.getTier();
                        }
                    }
                    if (itemTier == 1) {
                        if (blockTier == -1) {
                            if (blockEntity instanceof UPCBlockEntity upcEntity) {
                                upcEntity.setTier(1);
                            }
                        }
                    } else {
                        if (blockTier >= 1) {
                            if (itemTier == blockTier + 1) {
                                if (blockEntity instanceof UPCBlockEntity upcEntity) {
                                    upcEntity.setTier(upcEntity.getTier() + 1);
                                }
                            }
                        }
                    }
                }
                else {
                    var blockEntity = world.getBlockEntity(pos);
                    if (blockEntity != null) {
                        // Example: check if it's your custom UPC block entity
                        if (blockEntity instanceof UPCBlockEntity upcEntity) {
                             upcEntity.setTier(0);
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("Could not parse tier from item name: " + itemName);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String itemName = stack.getItem().getName().getString();
        try {
            String tier = itemName.split("Upc Upgrade ")[1].toLowerCase();
            if (tier.equalsIgnoreCase("creative")) {
                tooltip.add(Text.translatable("itemTooltip." + Upc.MOD_ID + ".upc_tier_" + tier + "_upgrade").formatted(Formatting.GOLD));
            }
            else {
                int itemTier = Integer.parseInt(tier);
                String[] text = Text.translatable("itemTooltip." + Upc.MOD_ID + ".upc_tier_" + itemTier + "_upgrade").getString().split("\n");
                for(String t : text) {
                    tooltip.add(Text.literal(t).formatted(Formatting.GOLD));
                }
            }
        }
        catch(NumberFormatException e) {
            System.err.println("Could not parse tier from item name: " + itemName);
        }
    }
}
