package witchinggadgets.common.recipes;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import cpw.mods.fml.common.registry.GameRegistry;
import thaumcraft.api.aspects.AspectList;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.recipes.other.WG_GT_clusters;
import witchinggadgets.common.recipes.other.WG_Tcon_clusters;
import witchinggadgets.common.recipes.other.WG_other_camera;
import witchinggadgets.common.recipes.other.WG_other_gemcutting;
import witchinggadgets.common.recipes.other.WG_other_infernal_blast_furnace;
import witchinggadgets.common.recipes.other.WG_other_loom;
import witchinggadgets.common.recipes.other.WG_other_spinning;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.recipe.InfernalBlastfurnaceRecipe;

public class WG_other_recipes {

    public static void register_others() {
        WG_other_gemcutting.registerGemcutting();
        WG_other_camera.registerScanCamera();
        WG_other_spinning.registerSpinningRecipes();
        WG_other_loom.registerLoom();
        WG_other_infernal_blast_furnace.registerInfernalBlastFurnace();
        if (WitchingGadgets.isGT5uLoaded) {
            WG_GT_clusters.registerClusterRecipesGT();
        } else {
            for (String name : OreDictionary.getOreNames()) if (name.startsWith("cluster")) {
                addBlastTripling(name.substring("cluster".length()));
            }
        }
        if (WGModCompat.loaded_TCon) {
            WG_Tcon_clusters.registerClusterRecipesTcon();
        }
    }

    public static boolean registerShapelessOreRecipe(String tag, String tagAddon, ItemStack result, Object... recipe) {
        ShapelessOreRecipe oreRecipe = new ShapelessOreRecipe(result, recipe);
        GameRegistry.addRecipe(oreRecipe);
        WGContent.recipeList.put(tag + tagAddon, oreRecipe);
        return (WGContent.recipeList.containsKey(tag + tagAddon) && WGContent.recipeList.containsValue(oreRecipe));
    }

    public static boolean registerShapedOreRecipe(String tag, String tagAddon, ItemStack result, Object... recipe) {
        ShapedOreRecipe oreRecipe = new ShapedOreRecipe(result, recipe);
        GameRegistry.addRecipe(oreRecipe);
        WGContent.recipeList.put(tag + tagAddon, oreRecipe);
        return (WGContent.recipeList.containsKey(tag + tagAddon) && WGContent.recipeList.containsValue(oreRecipe));
    }

    public static void registerCompoundRecipe(String tag, String tagAddon, AspectList creationAspects, int sizeX,
            int sizeY, int sizeZ, Object... recipe) {
        List<Object> compoundRecipe = Arrays.asList(creationAspects, sizeX, sizeY, sizeZ, Arrays.asList(recipe));
        WGContent.recipeList.put(tag + tagAddon, compoundRecipe);
    }

    public static void addBlastTripling(String matName) {
        ItemStack ingot = Utilities.getOredict("ingot" + matName, 3);
        ItemStack nugget = Utilities.getOredict("nugget" + matName, 1);

        if (ingot != null) {
            InfernalBlastfurnaceRecipe recipe = InfernalBlastfurnaceRecipe
                    .addRecipe(ingot, "cluster" + matName, 1, 440, false);

            if (recipe != null && nugget != null) {
                recipe.addBonus(nugget);
            }
        }
    }
}
