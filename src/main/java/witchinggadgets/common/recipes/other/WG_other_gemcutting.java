package witchinggadgets.common.recipes.other;

import static witchinggadgets.common.recipes.WG_other_recipes.registerCompoundRecipe;
import static witchinggadgets.common.recipes.WG_other_recipes.registerShapedOreRecipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;

public class WG_other_gemcutting {

    public static void registerGemcutting() {
        if (WitchingGadgets.isGT5uLoaded) {
            registerShapedOreRecipe(
                    "GEMCUTTING",
                    "_TOOLS",
                    new ItemStack(WGContent.ItemMaterial, 1, 8),
                    "qfi",
                    "sss",
                    'q',
                    GTOreDictUnificator.get(OrePrefixes.gemChipped, Materials.Ruby, 1L),
                    'f',
                    GTOreDictUnificator.get(OrePrefixes.gemChipped, Materials.Diamond, 1L),
                    'i',
                    GTOreDictUnificator.get(OrePrefixes.gemChipped, Materials.Emerald, 1L),
                    's',
                    GTOreDictUnificator.get(OrePrefixes.stick, Materials.Thaumium, 1L));
        } else {
            registerShapedOreRecipe(
                    "GEMCUTTING",
                    "_TOOLS",
                    new ItemStack(WGContent.ItemMaterial, 1, 8),
                    "qfi",
                    "sss",
                    'q',
                    "gemQuartz",
                    'f',
                    Items.flint,
                    'i',
                    "ingotIron",
                    's',
                    "stickWood");
        }

        registerCompoundRecipe(
                "GEMCUTTING",
                "",
                new AspectList(),
                1,
                2,
                1,
                new ItemStack(WGContent.ItemMaterial, 1, 8),
                new ItemStack(ConfigBlocks.blockTable));
    }
}
