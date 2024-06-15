package witchinggadgets.common.recipes.alchemic;

import static witchinggadgets.common.recipes.WG_alchemic_recipe.registerAlchemyRecipe;

import net.minecraft.item.ItemStack;

import gregtech.api.util.GT_ModHandler;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import witchinggadgets.common.WGContent;

public class WG_alchemic_rose_vine {

    public static void registerRoseWine() {

        registerAlchemyRecipe(
                "ROSEVINE.1",
                "",
                new ItemStack(WGContent.BlockRoseVine),
                GT_ModHandler.getModItem("TwilightForest", "tile.TFThornRose", 1L),
                new AspectList().add(Aspect.PLANT, 64).add(Aspect.LIFE, 16).add(Aspect.MAGIC, 8));

        registerAlchemyRecipe(
                "ROSEVINE.2",
                "",
                new ItemStack(WGContent.BlockRoseVine),
                GT_ModHandler.getModItem("TwilightForest", "tile.TFThorns", 1L),
                new AspectList().add(Aspect.PLANT, 64).add(Aspect.LIFE, 16).add(Aspect.MAGIC, 8));

        registerAlchemyRecipe(
                "ROSEVINE.3",
                "",
                new ItemStack(WGContent.BlockRoseVine),
                GT_ModHandler.getModItem("TwilightForest", "tile.TFThorns", 1L, 1),
                new AspectList().add(Aspect.PLANT, 64).add(Aspect.LIFE, 16).add(Aspect.MAGIC, 8));
    }
}
