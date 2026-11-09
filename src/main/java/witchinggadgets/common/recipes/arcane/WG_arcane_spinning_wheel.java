package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.ruling_0.materiallib.api.MaterialLibAPI;

import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;

public class WG_arcane_spinning_wheel {

    public static void registerSpinningWheel() {
        if (WitchingGadgets.isGT5uLoaded) {
            registerArcaneRecipe(
                    "SPINNINGWHEEL",
                    "",
                    new ItemStack(WGContent.BlockWoodenDevice),
                    new AspectList().add(Aspect.ORDER, 10).add(Aspect.AIR, 10),
                    "IsW",
                    "RDS",
                    "TTT",
                    'T',
                    new ItemStack(ConfigBlocks.blockTable),
                    's',
                    MaterialLibAPI.getStack(Materials.Steel, Shapes.screw, 1),
                    'D',
                    "craftingToolScrewdriver",
                    'I',
                    "gearGtWoodSealed",
                    'R',
                    MaterialLibAPI.getStack(Materials.WoodSealed, Shapes.stickLong, 1),
                    'S',
                    MaterialLibAPI.getStack(Materials.WoodSealed, Shapes.stick, 1),
                    'W',
                    "gearGtSmallThaumium");
        } else {
            registerArcaneRecipe(
                    "SPINNINGWHEEL",
                    "",
                    new ItemStack(WGContent.BlockWoodenDevice),
                    new AspectList().add(Aspect.ORDER, 5).add(Aspect.AIR, 5),
                    "I W",
                    " T ",
                    'T',
                    new ItemStack(ConfigBlocks.blockTable),
                    'I',
                    new ItemStack(Items.iron_ingot),
                    'W',
                    "plankWood");
        }
    }
}
