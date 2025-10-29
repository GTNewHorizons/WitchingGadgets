package witchinggadgets.common.recipes.alchemic;

import static witchinggadgets.common.recipes.WG_alchemic_recipes.registerAlchemyRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Loader;
import gregtech.api.util.GTModHandler;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;

public class WG_alchemic_rose_vine {

    public static void registerRoseWine() {
        if (WitchingGadgets.isGT5uLoaded && Loader.isModLoaded("TwilightForest")) {
            registerAlchemyRecipe(
                    "ROSEVINE",
                    ".1",
                    new ItemStack(WGContent.BlockRoseVine),
                    GTModHandler.getModItem("TwilightForest", "tile.TFThornRose", 1L),
                    new AspectList().add(Aspect.PLANT, 64).add(Aspect.LIFE, 16).add(Aspect.MAGIC, 8));

            registerAlchemyRecipe(
                    "ROSEVINE",
                    ".2",
                    new ItemStack(WGContent.BlockRoseVine),
                    GTModHandler.getModItem("TwilightForest", "tile.TFThorns", 1L),
                    new AspectList().add(Aspect.PLANT, 64).add(Aspect.LIFE, 16).add(Aspect.MAGIC, 8));

            registerAlchemyRecipe(
                    "ROSEVINE",
                    ".3",
                    new ItemStack(WGContent.BlockRoseVine),
                    GTModHandler.getModItem("TwilightForest", "tile.TFThorns", 1L, 1),
                    new AspectList().add(Aspect.PLANT, 64).add(Aspect.LIFE, 16).add(Aspect.MAGIC, 8));
        } else {
            registerAlchemyRecipe(
                    "ROSEVINE",
                    "",
                    new ItemStack(WGContent.BlockRoseVine),
                    new ItemStack(Blocks.double_plant, 1, 4),
                    new AspectList().add(Aspect.PLANT, 4).add(Aspect.ENTROPY, 4).add(Aspect.MAGIC, 4));
        }
    }
}
