package me.goldenkitten.upc.block;

import com.mojang.serialization.MapCodec;
import me.goldenkitten.upc.UPCReloaded;
import me.goldenkitten.upc.Utils;
import me.goldenkitten.upc.blockentity.UPCBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UPCBlock extends BaseEntityBlock {
    public static final MapCodec<UPCBlock> CODEC = simpleCodec(UPCBlock::new);
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static UPCBlockEntity BLOCK_ENTITY = null;

    public UPCBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        BLOCK_ENTITY = new UPCBlockEntity(pos, state);
        return BLOCK_ENTITY;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        if (level.isClientSide) return null;
        return (lvl, pos, st, blockEntity) -> {
            if (blockEntity instanceof UPCBlockEntity upc) {
                upc.tick();
            }
        };
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof UPCBlockEntity upcBlockEntity) {
            // Get the item currently in the player's active hand
            if (stack != ItemStack.EMPTY) {
                // Check if they are holding your exact Tier 10 item registry object
                String[] itemName = stack.getHoverName().getString().split(" ");
                if (itemName.length > 1) {
                    String tier = itemName[2];
                    try {
                        if (Utils.isModItem(stack)) {
                            int num = 0;
                            if (!tier.equalsIgnoreCase("creative")) {
                                if (tier.matches(".*\\d.*")) {
                                    num = Integer.parseInt(tier);
                                }
                                else {
                                    return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                                }
                            }
                            UPCReloaded.LOGGER.info("{} {}", tier, num);
                            if (!level.isClientSide) {

                                // Update the block entity's tier
                                upcBlockEntity.setTier(num);

                                // Consume the item if the player is not in creative mode
                                if (!player.getAbilities().instabuild) {
                                    stack.shrink(1);
                                }
                            }
                        }
                        // SUCCESS tells Minecraft to swing the player's hand and stop further logic
                        return ItemInteractionResult.SUCCESS;
                    } catch (NumberFormatException ignored) {

                    }
                }
            }
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}