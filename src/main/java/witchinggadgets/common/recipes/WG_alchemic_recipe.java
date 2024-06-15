package witchinggadgets.common.recipes;

import net.minecraft.item.ItemStack;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.recipes.alchemic.WG_alchemic_clusters;
import witchinggadgets.common.recipes.alchemic.WG_alchemic_gemcutting;
import witchinggadgets.common.recipes.alchemic.WG_alchemic_pure_cinnabar;
import witchinggadgets.common.recipes.alchemic.WG_alchemic_rose_vine;
import witchinggadgets.common.recipes.alchemic.WG_alchemic_transmogrify;

public class WG_alchemic_recipe {

    public static void registeralchemic() {

        // ALCHEMY REGISTRATION //

        WG_alchemic_rose_vine.registerRoseWine();
        WG_alchemic_transmogrify.registerTransmogrify();
        WG_alchemic_pure_cinnabar.registerPureCinnabar();
        WG_alchemic_gemcutting.registerGemcutting();
        WG_alchemic_clusters.registerClusters();

    }

    public static CrucibleRecipe registerAlchemyRecipe(String tag, String tagAddon, ItemStack result, Object catalyst,
            AspectList alchemyAspects) {
        CrucibleRecipe crucibleRecipe = ThaumcraftApi.addCrucibleRecipe(tag, result, catalyst, alchemyAspects);
        WGContent.recipeList.put(tag + tagAddon, crucibleRecipe);
        return crucibleRecipe;
    }
}
