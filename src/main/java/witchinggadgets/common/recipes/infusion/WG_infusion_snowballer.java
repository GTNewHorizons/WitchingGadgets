package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.ItemList;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.common.WGContent;

public class WG_infusion_snowballer {

    public static void registerSnowballer() {
        registerInfusionRecipe(
                "SNOWBALLER",
                "",
                new ItemStack(WGContent.BlockWoodenDevice, 1, 1),
                7,
                new AspectList().add(Aspect.WEATHER, 32).add(Aspect.COLD, 48).add(Aspect.TOOL, 64),
                ItemList.Machine_HV_FluidSolidifier.get(1L),
                new ItemStack[] { ItemList.Conveyor_Module_HV.get(1L), new ItemStack(ConfigBlocks.blockCrystal, 1, 2),
                        new ItemStack(Items.snowball), new ItemStack(ConfigBlocks.blockCrystal, 1, 4),
                        ItemList.IC2_EnergyCrystal.getWildcard(1L), new ItemStack(ConfigBlocks.blockCrystal, 1, 4),
                        new ItemStack(Blocks.packed_ice), new ItemStack(ConfigBlocks.blockCrystal, 1, 2) });
    }
}
