package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Loader;
import gregtech.api.util.GTModHandler;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;

public class WG_arcane_ageingstone {

    public static void registerAgeingStone() {
        if (Loader.isModLoaded("MagicBees") && WitchingGadgets.isGT5uLoaded) {
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
                    GTModHandler.getModItem("MagicBees", "miscResources", 1L, 9));
        } else {
            registerArcaneRecipe(
                    "AGEINGSTONE",
                    "",
                    new ItemStack(WGContent.BlockStoneDevice, 1, 1),
                    new AspectList().add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10),
                    " s ",
                    "SCS",
                    " s ",
                    'S',
                    new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6),
                    's',
                    new ItemStack(ConfigItems.itemShard, 1, 32767),
                    'C',
                    new ItemStack(Items.clock));
        }
    }
}
