package witchinggadgets.common.recipes.other;

import static gregtech.api.recipe.RecipeMaps.*;

import net.minecraft.item.ItemStack;

import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;

public class WG_Tcon_clusters {

    public static void registerClusterRecipesTcon() {
        if (WGModCompat.loaded_TCon) {
            if (WGConfig.smelteryResultForClusters > 0) {
                WGModCompat.addTConSmelteryRecipe(
                        "clusterIron",
                        "blockIron",
                        600,
                        "iron.molten",
                        WGConfig.smelteryResultForClusters);
                WGModCompat.addTConSmelteryRecipe(
                        "clusterGold",
                        "blockGold",
                        400,
                        "gold.molten",
                        WGConfig.smelteryResultForClusters);
                WGModCompat.addTConSmelteryRecipe(
                        "clusterCopper",
                        "blockCopper",
                        350,
                        "copper.molten",
                        WGConfig.smelteryResultForClusters);
                WGModCompat.addTConSmelteryRecipe(
                        "clusterTin",
                        "blockTin",
                        400,
                        "tin.molten",
                        WGConfig.smelteryResultForClusters);
                WGModCompat.addTConSmelteryRecipe(
                        "clusterSilver",
                        "blockSilver",
                        550,
                        "silver.molten",
                        WGConfig.smelteryResultForClusters);
            }
            WGModCompat.addTConDryingRecipe(
                    new ItemStack(ConfigItems.itemZombieBrain),
                    20 * 6 * 5,
                    new ItemStack(WGContent.ItemMagicFoodstuffs, 1, 2));
        }
    }
}
