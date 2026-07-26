package com.cheam.spongeplus.mixin;

import com.cheam.spongeplus.service.SpongeService;
import net.minecraft.block.SpongeBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SpongeBlock.class)
public class SpongeBlockMixin {

    /**
     * Replace Minecraft's absorbWater().
     * @author @cheamnorakpanha
     * @reason ...
     */
    @Overwrite
    private boolean absorbWater(World world, BlockPos pos) {

        return SpongeService.absorb(world, pos);

    }

}