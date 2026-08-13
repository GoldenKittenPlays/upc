package me.goldenkitten.upc.block;

import me.goldenkitten.upc.UPCReloaded;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(UPCReloaded.MODID);

    public static final DeferredBlock<UPCBlock> UPC_BLOCK = BLOCKS.registerBlock(
            "upc",
            UPCBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .sound(SoundType.METAL)
                    .strength(3.5f, 6.0f)
    );
}