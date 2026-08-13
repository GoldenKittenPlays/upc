package me.goldenkitten.upc;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;

public class Utils {
    public static boolean isModItem(ItemStack stack) {
        String itemNamespace = BuiltInRegistries.ITEM.getKey(stack.getItem()).getNamespace();

        return itemNamespace.equals(UPCReloaded.MODID);
    }
}
