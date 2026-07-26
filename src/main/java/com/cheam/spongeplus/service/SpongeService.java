package com.cheam.spongeplus.service;

import com.cheam.spongeplus.SpongePlus;
import com.cheam.spongeplus.config.SpongeConfig;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class SpongeService {

    private static final Direction[] DIRECTIONS = Direction.values();
    private static final int MAX_VISITED_BLOCKS = 64;

    public static boolean absorb(World world, BlockPos pos) {

        SpongePlus.LOGGER.info("SpongeService.absorb() called");

        return BlockPos.iterateRecursively(pos, SpongeConfig.getRadius(), MAX_VISITED_BLOCKS, (currentPos, queuer) -> {

                    // Add all six neighboring blocks to the BFS queue
                    for (Direction direction : DIRECTIONS) {
                        queuer.accept(currentPos.offset(direction));
                    }

                }, currentPos -> {

                    // Skip the sponge block itself
                    if (currentPos.equals(pos)) {
                        return BlockPos.IterationState.ACCEPT;
                    }

                    BlockState blockState = world.getBlockState(currentPos);
                    FluidState fluidState = world.getFluidState(currentPos);

                    // Accept both water and lava
            boolean supportedFluid =
                    fluidState.isIn(FluidTags.WATER)
                            || (
                            SpongeConfig.shouldAbsorbLava()
                                    && fluidState.isIn(FluidTags.LAVA)
                    );

                    if (!supportedFluid) {
                        return BlockPos.IterationState.SKIP;
                    }

                    Block block = blockState.getBlock();

                    // Drain waterlogged blocks
                    if (block instanceof FluidDrainable drainable) {
                        if (!drainable.tryDrainFluid((LivingEntity) null, world, currentPos, blockState).isEmpty()) {

                            return BlockPos.IterationState.ACCEPT;
                        }
                    }

                    // Remove normal water source blocks
                    if (block instanceof FluidBlock) {

                        world.setBlockState(currentPos, Blocks.AIR.getDefaultState(), 3);

                        return BlockPos.IterationState.ACCEPT;
                    }

                    // Remove kelp and seagrass
                    if (blockState.isOf(Blocks.KELP) || blockState.isOf(Blocks.KELP_PLANT) || blockState.isOf(Blocks.SEAGRASS) || blockState.isOf(Blocks.TALL_SEAGRASS)) {

                        BlockEntity blockEntity = blockState.hasBlockEntity() ? world.getBlockEntity(currentPos) : null;

                        Block.dropStacks(blockState, world, currentPos, blockEntity);

                        world.setBlockState(currentPos, Blocks.AIR.getDefaultState(), 3);

                        return BlockPos.IterationState.ACCEPT;
                    }

                    return BlockPos.IterationState.SKIP;
                }

        ) > 1;
    }

}