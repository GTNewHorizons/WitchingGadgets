package witchinggadgets.common.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import org.apache.commons.lang3.ArrayUtils;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import witchinggadgets.common.util.Utilities;

public class WGResearchUtils {

    public static void addResearchPage(final String research, ResearchPage page) {
        ResearchItem ri = ResearchCategories.getResearch(research);
        ri.setPages(ArrayUtils.add(ri.getPages(), page));
    }

    public static void refreshResearchPages(final String research) {
        ResearchItem target = ResearchCategories.getResearch(research);
        ResearchPage[] pages = target.getPages();
        for (int x = 0; x < pages.length; x++) {
            if (pages[x].recipe != null) {
                if (pages[x].recipe instanceof IRecipe) {
                    IRecipe recipe = (IRecipe) pages[x].recipe;
                    for (Object craft : CraftingManager.getInstance().getRecipeList()) {
                        if (craft instanceof IRecipe) {
                            IRecipe theCraft = (IRecipe) craft;
                            if (theCraft.getRecipeOutput() != null
                                    && Utilities.areStacksEqual(theCraft.getRecipeOutput(), recipe.getRecipeOutput())) {
                                pages[x] = new ResearchPage(theCraft);
                                break;
                            }
                        }
                    }
                } else if (pages[x].recipe instanceof IArcaneRecipe) {
                    IArcaneRecipe recipe = (IArcaneRecipe) pages[x].recipe;
                    for (Object craft : ThaumcraftApi.getCraftingRecipes()) {
                        if (craft instanceof IArcaneRecipe) {
                            IArcaneRecipe theCraft = (IArcaneRecipe) craft;
                            if (theCraft.getRecipeOutput() != null
                                    && Utilities.areStacksEqual(theCraft.getRecipeOutput(), recipe.getRecipeOutput())) {
                                pages[x] = new ResearchPage(theCraft);
                                break;
                            }
                        }
                    }
                } else if (pages[x].recipe instanceof CrucibleRecipe) {
                    CrucibleRecipe recipe = (CrucibleRecipe) pages[x].recipe;
                    for (Object craft : ThaumcraftApi.getCraftingRecipes()) {
                        if (craft instanceof CrucibleRecipe) {
                            CrucibleRecipe theCraft = (CrucibleRecipe) craft;
                            if (theCraft.getRecipeOutput() != null
                                    && Utilities.areStacksEqual(theCraft.getRecipeOutput(), recipe.getRecipeOutput())) {
                                pages[x] = new ResearchPage(theCraft);
                                break;
                            }
                        }
                    }
                } else if (pages[x].recipe instanceof InfusionRecipe) {
                    InfusionRecipe recipe = (InfusionRecipe) pages[x].recipe;
                    if (recipe.getRecipeOutput() instanceof ItemStack) {
                        for (Object craft : ThaumcraftApi.getCraftingRecipes()) {
                            if (craft instanceof InfusionRecipe) {
                                InfusionRecipe theCraft = (InfusionRecipe) craft;
                                if (theCraft.getRecipeOutput() instanceof ItemStack && Utilities.areStacksEqual(
                                        ((ItemStack) theCraft.getRecipeOutput()),
                                        (ItemStack) recipe.getRecipeOutput())) {
                                    pages[x] = new ResearchPage(theCraft);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
