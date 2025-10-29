package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;

import net.minecraft.item.ItemStack;

import gregtech.api.enums.ItemList;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;

public class WG_arcane_calculator {

    public static void registerScanCamera() {
        if (WitchingGadgets.isGT5uLoaded) {
            registerArcaneRecipe(
                    "CALCULATOR",
                    "",
                    new ItemStack(WGContent.ItemMaterial, 1, 7),
                    new AspectList().add(Aspect.ORDER, 50),
                    "srs",
                    "sbs",
                    "sgs",
                    's',
                    "stickThaumium",
                    'r',
                    ItemList.Sensor_MV.get(1L),
                    'b',
                    "circuitGood",
                    'g',
                    new ItemStack(ConfigBlocks.blockStoneDevice, 1, 2));
        } else {
            registerArcaneRecipe(
                    "CALCULATOR",
                    "",
                    new ItemStack(WGContent.ItemMaterial, 1, 7),
                    new AspectList().add(Aspect.ORDER, 10),
                    "srs",
                    "sbs",
                    "sgs",
                    's',
                    "stickWood",
                    'r',
                    "dyeRed",
                    'b',
                    "dyeBlue",
                    'g',
                    "dyeGreen");
        }
    }
}
