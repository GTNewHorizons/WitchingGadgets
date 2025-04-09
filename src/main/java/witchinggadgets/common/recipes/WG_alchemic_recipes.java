package witchinggadgets.common.recipes;

import net.minecraft.item.ItemStack;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.recipes.alchemic.WG_alchemic_clusters;
import witchinggadgets.common.recipes.alchemic.WG_alchemic_crystal_capsule;
import witchinggadgets.common.recipes.alchemic.WG_alchemic_pure_cinnabar;
import witchinggadgets.common.recipes.alchemic.WG_alchemic_rose_vine;
import witchinggadgets.common.recipes.alchemic.WG_alchemic_tc_clusters;
import witchinggadgets.common.recipes.alchemic.WG_alchemic_transmogrify;
import witchinggadgets.common.util.Utilities;

public class WG_alchemic_recipes {

    public static void registeralchemic() {

        // ALCHEMY REGISTRATION //

        WG_alchemic_rose_vine.registerRoseWine();
        WG_alchemic_transmogrify.registerTransmogrify();
        WG_alchemic_pure_cinnabar.registerPureCinnabar();

        if (WGConfig.moduleGemcutting) {
            WG_alchemic_crystal_capsule.registerCrystalCapsule();
        }

        WG_alchemic_clusters.registerClusters();
        WG_alchemic_tc_clusters.registerExistingClusters();

    }

    public static CrucibleRecipe registerAlchemyRecipe(String tag, String tagAddon, ItemStack result, Object catalyst,
            AspectList alchemyAspects) {
        if (result == null) {
            throw new IllegalArgumentException(tag + tagAddon + ": Result cannot be null");
        }
        if (alchemyAspects == null) {
            throw new IllegalArgumentException(tag + tagAddon + ": alchemyAspects cannot be null");
        }
        for (Aspect aspect : alchemyAspects.aspects.keySet()) {
            if (aspect == null) {
                throw new IllegalArgumentException(tag + tagAddon + ": Aspect in alchemyAspects cannot be null");
            }
        }
        if (catalyst == null) {
            throw new IllegalArgumentException(tag + tagAddon + ": Catalyst cannot be null");
        }
        CrucibleRecipe crucibleRecipe = ThaumcraftApi.addCrucibleRecipe(tag, result, catalyst, alchemyAspects);
        WGContent.recipeList.put(tag + tagAddon, crucibleRecipe);
        return crucibleRecipe;
    }

    public static void removeCrucibleRecipe(final ItemStack output) {
        ThaumcraftApi.getCraftingRecipes().removeIf(recipe -> {
            if (recipe instanceof CrucibleRecipe) return ((CrucibleRecipe) recipe).getRecipeOutput() != null
                    && Utilities.areStacksEqual(((CrucibleRecipe) recipe).getRecipeOutput(), output);
            return false;
        });
    }
}
