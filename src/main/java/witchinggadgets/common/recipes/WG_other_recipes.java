package witchinggadgets.common.recipes;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import cpw.mods.fml.common.registry.GameRegistry;
import thaumcraft.api.aspects.AspectList;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.recipes.other.WG_other_camera;
import witchinggadgets.common.recipes.other.WG_other_clusters;
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
        WG_other_clusters.registerClusterRecipes();
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
        List<Object> compoundRecipe = Arrays.asList(
                new Object[] { creationAspects, Integer.valueOf(sizeX), Integer.valueOf(sizeY), Integer.valueOf(sizeZ),
                        Arrays.asList(recipe) });
        WGContent.recipeList.put(tag + tagAddon, compoundRecipe);
    }

    public static void addBlastTrippling(String name) {
        if (!OreDictionary.getOres("ingot" + name).isEmpty()) {
            InfernalBlastfurnaceRecipe r = InfernalBlastfurnaceRecipe.addRecipe(
                    Utilities.copyStackWithSize(OreDictionary.getOres("ingot" + name).get(0), 3),
                    "cluster" + name,
                    1,
                    440,
                    false);
            if (r != null && !OreDictionary.getOres("nugget" + name).isEmpty())
                r.addBonus(OreDictionary.getOres("nugget" + name).get(0));
        }
    }
}
