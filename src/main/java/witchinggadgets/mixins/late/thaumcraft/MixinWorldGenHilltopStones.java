package witchinggadgets.mixins.late.thaumcraft;

import net.minecraft.block.Block;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import thaumcraft.common.lib.world.WorldGenHilltopStones;
import witchinggadgets.common.WGConfig;

@Mixin(WorldGenHilltopStones.class)
public class MixinWorldGenHilltopStones {

    @ModifyReturnValue(method = "GetValidSpawnBlocks", at = @At("RETURN"), remap = false)
    private Block[] witchinggadgets$modifyWorldgen(Block[] original) {
        if (WGConfig.coremod_worldgenValidBase_HilltopStones != null) {
            return WGConfig.coremod_worldgenValidBase_HilltopStones;
        }
        return original;
    }

}
