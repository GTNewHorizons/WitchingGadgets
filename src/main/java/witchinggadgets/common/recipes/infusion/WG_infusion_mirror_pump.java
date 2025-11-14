package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.ItemList;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;

public class WG_infusion_mirror_pump {

    public static void registerMirrorPump() {
        if (WitchingGadgets.isGT5uLoaded) {
            registerInfusionRecipe(
                    "MIRRORPUMP",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 0),
                    8,
                    new AspectList().add(Aspect.MOTION, 32).add(Aspect.TRAVEL, 64).add(Aspect.ORDER, 16),
                    new ItemStack(ConfigBlocks.blockTube, 1, 4),
                    new ItemStack[] { new ItemStack(ConfigBlocks.blockWoodenDevice), ItemList.Electric_Pump_MV.get(1L),
                            new ItemStack(ConfigBlocks.blockWoodenDevice), ItemList.Electric_Pump_MV.get(1L),
                            new ItemStack(ConfigBlocks.blockWoodenDevice), ItemList.Electric_Pump_MV.get(1L),
                            new ItemStack(ConfigBlocks.blockWoodenDevice), ItemList.Electric_Pump_MV.get(1L) });
        } else {
            registerInfusionRecipe(
                    "MIRRORPUMP",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 0),
                    4,
                    new AspectList().add(Aspect.MOTION, 16).add(Aspect.TRAVEL, 32).add(Aspect.ORDER, 8),
                    new ItemStack(ConfigBlocks.blockTube, 1, 4),
                    new ItemStack[] { new ItemStack(ConfigBlocks.blockWoodenDevice),
                            new ItemStack(Blocks.sticky_piston), new ItemStack(ConfigBlocks.blockWoodenDevice),
                            new ItemStack(Blocks.sticky_piston), new ItemStack(ConfigBlocks.blockWoodenDevice),
                            new ItemStack(Blocks.hopper), new ItemStack(ConfigBlocks.blockWoodenDevice),
                            new ItemStack(Blocks.hopper) });
        }
    }
}
