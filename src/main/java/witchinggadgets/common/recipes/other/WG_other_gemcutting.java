package witchinggadgets.common.recipes.other;

import static witchinggadgets.common.recipes.WG_other_recipes.registerCompoundRecipe;
import static witchinggadgets.common.recipes.WG_other_recipes.registerShapedOreRecipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.ruling_0.materiallib.api.MaterialLibAPI;

import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;
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
                    MaterialLibAPI.getStack(Materials.Ruby, Shapes.gemChipped, 1),
                    'f',
                    MaterialLibAPI.getStack(Materials.Diamond, Shapes.gemChipped, 1),
                    'i',
                    MaterialLibAPI.getStack(Materials.Emerald, Shapes.gemChipped, 1),
                    's',
                    MaterialLibAPI.getStack(Materials.Thaumium, Shapes.stick, 1));
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
