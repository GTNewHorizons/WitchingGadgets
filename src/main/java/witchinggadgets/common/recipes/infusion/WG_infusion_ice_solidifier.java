package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.ItemList;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;

public class WG_infusion_ice_solidifier {

    public static void registerSnowballer() {
        if (WitchingGadgets.isGT5uLoaded) {
            registerInfusionRecipe(
                    "ICESOLIDIFIER",
                    "",
                    new ItemStack(WGContent.BlockWoodenDevice, 1, 6),
                    7,
                    new AspectList().add(Aspect.WEATHER, 32).add(Aspect.COLD, 48).add(Aspect.TOOL, 64),
                    ItemList.Machine_HV_FluidSolidifier.get(1L),
                    new ItemStack[] { ItemList.Conveyor_Module_HV.get(1L),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 2), new ItemStack(Blocks.ice),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 4), ItemList.IC2_EnergyCrystal.getWildcard(1L),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 4), new ItemStack(Blocks.packed_ice),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 2) });
        } else {
            registerInfusionRecipe(
                    "ICESOLIDIFIER",
                    "",
                    new ItemStack(WGContent.BlockWoodenDevice, 1, 6),
                    4,
                    new AspectList().add(Aspect.WEATHER, 16).add(Aspect.COLD, 32).add(Aspect.TOOL, 32),
                    new ItemStack(Blocks.obsidian),
                    new ItemStack[] { new ItemStack(Blocks.sticky_piston),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 2), new ItemStack(Blocks.ice),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 4), new ItemStack(Items.redstone),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 4), new ItemStack(Blocks.packed_ice),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 2) });
        }
    }
}
