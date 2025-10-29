package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;

public class WG_arcane_label_lib {

    public static void registerLabelLib() {
        if (WitchingGadgets.isGT5uLoaded) {
            registerArcaneRecipe(
                    "LABELLIB",
                    "",
                    new ItemStack(WGContent.BlockWoodenDevice, 1, 5),
                    new AspectList().add(Aspect.ORDER, 30).add(Aspect.EARTH, 10).add(Aspect.WATER, 10),
                    "BLW",
                    "sTs",
                    " D ",
                    'B',
                    new ItemStack(Items.book),
                    's',
                    GTOreDictUnificator.get(OrePrefixes.screw, Materials.Steel, 1L),
                    'D',
                    "craftingToolScrewdriver",
                    'L',
                    new ItemStack(ConfigItems.itemResource, 1, 13),
                    'W',
                    "scribingTools",
                    'T',
                    new ItemStack(ConfigBlocks.blockTable));
        } else {
            registerArcaneRecipe(
                    "LABELLIB",
                    "",
                    new ItemStack(WGContent.BlockWoodenDevice, 1, 5),
                    new AspectList().add(Aspect.ORDER, 30),
                    "BLW",
                    " T ",
                    'B',
                    new ItemStack(Items.book),
                    'L',
                    new ItemStack(ConfigItems.itemResource, 1, 13),
                    'W',
                    new ItemStack(ConfigItems.itemInkwell),
                    'T',
                    new ItemStack(ConfigBlocks.blockTable));
        }
    }
}
