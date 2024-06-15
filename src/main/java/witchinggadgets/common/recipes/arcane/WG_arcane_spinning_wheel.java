package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;

import net.minecraft.item.ItemStack;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.common.WGContent;

public class WG_arcane_spinning_wheel {

    public static void registerSpinningWheel() {
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
                GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Steel, 1L),
                'D',
                "craftingToolScrewdriver",
                'I',
                "gearGtWoodSealed",
                'R',
                GT_OreDictUnificator.get(OrePrefixes.stickLong, Materials.WoodSealed, 1L),
                'S',
                GT_OreDictUnificator.get(OrePrefixes.stick, Materials.WoodSealed, 1L),
                'W',
                "gearGtSmallThaumium");
    }
}
