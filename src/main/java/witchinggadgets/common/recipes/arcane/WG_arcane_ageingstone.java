package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;

import net.minecraft.item.ItemStack;

import gregtech.api.util.GT_ModHandler;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.common.WGContent;

public class WG_arcane_ageingstone {

    public static void registerAgeingStone() {
        registerArcaneRecipe(
                "AGEINGSTONE",
                "",
                new ItemStack(WGContent.BlockStoneDevice, 1, 1),
                new AspectList().add(Aspect.ORDER, 15).add(Aspect.ENTROPY, 15),
                " s ",
                "SCS",
                " s ",
                'S',
                new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6),
                's',
                new ItemStack(ConfigBlocks.blockCrystal, 1, 32767),
                'C',
                GT_ModHandler.getModItem("MagicBees", "miscResources", 1L, 9));
    }
}
