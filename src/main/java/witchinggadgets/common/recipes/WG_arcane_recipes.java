package witchinggadgets.common.recipes;

import net.minecraft.item.ItemStack;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.recipes.arcane.WG_arcane_advanced_robes;
import witchinggadgets.common.recipes.arcane.WG_arcane_advanced_scribing_tools;
import witchinggadgets.common.recipes.arcane.WG_arcane_ageingstone;
import witchinggadgets.common.recipes.arcane.WG_arcane_bags;
import witchinggadgets.common.recipes.arcane.WG_arcane_baubles;
import witchinggadgets.common.recipes.arcane.WG_arcane_calculator;
import witchinggadgets.common.recipes.arcane.WG_arcane_cloaks;
import witchinggadgets.common.recipes.arcane.WG_arcane_ench_soulbound;
import witchinggadgets.common.recipes.arcane.WG_arcane_ethereal_wall;
import witchinggadgets.common.recipes.arcane.WG_arcane_label_lib;
import witchinggadgets.common.recipes.arcane.WG_arcane_scan_camera;
import witchinggadgets.common.recipes.arcane.WG_arcane_spinning_wheel;

public class WG_arcane_recipes {

    public static void register_arcane() {

        WG_arcane_advanced_scribing_tools.registerAdvancedScribingTools();
        WG_arcane_scan_camera.registerScanCamera();
        WG_arcane_calculator.registerScanCamera();

        if (WGConfig.moduleCloak) {
            WG_arcane_cloaks.registerCloaks();
        }

        WG_arcane_ethereal_wall.registerEtherealWall();

        WG_arcane_ageingstone.registerAgeingStone();

        WG_arcane_spinning_wheel.registerSpinningWheel();
        WG_arcane_label_lib.registerLabelLib();

        if (WGConfig.moduleBag) {
            WG_arcane_bags.registerArcaneBags();
        }

        WG_arcane_advanced_robes.registerAdvancedRobes();
        WG_arcane_baubles.registerArcaneBaubles();
        WG_arcane_ench_soulbound.registerEnchSoulbound();
    }

    public static void registerArcaneRecipe(String research, String tagAddon, ItemStack result,
            AspectList craftingAspects, Object... recipe) {
        if (result == null) {
            throw new IllegalArgumentException(research + tagAddon + ": Result cannot be null");
        }
        if (craftingAspects == null) {
            throw new IllegalArgumentException(research + tagAddon + ": craftingAspects cannot be null");
        }
        for (Aspect aspect : craftingAspects.aspects.keySet()) {
            if (aspect == null) {
                throw new IllegalArgumentException(research + tagAddon + ": Aspect in craftingAspects cannot be null");
            }
        }
        if (recipe == null) {
            throw new IllegalArgumentException(research + tagAddon + ": Recipe cannot be null");
        }
        ShapedArcaneRecipe arcaneRecipe = ThaumcraftApi
                .addArcaneCraftingRecipe(research, result, craftingAspects, recipe);
        WGContent.recipeList.put(research + tagAddon, arcaneRecipe);
    }

    public static void registerShapelessArcaneRecipe(String research, String tagAddon, ItemStack result,
            AspectList craftingAspects, Object... recipe) {
        if (result == null) {
            throw new IllegalArgumentException(research + tagAddon + ": Result cannot be null");
        }
        if (craftingAspects == null) {
            throw new IllegalArgumentException(research + tagAddon + ": craftingAspects cannot be null");
        }
        for (Aspect aspect : craftingAspects.aspects.keySet()) {
            if (aspect == null) {
                throw new IllegalArgumentException(research + tagAddon + ": Aspect in craftingAspects cannot be null");
            }
        }
        if (recipe == null) {
            throw new IllegalArgumentException(research + tagAddon + ": Recipe cannot be null");
        }
        ShapelessArcaneRecipe arcaneRecipe = ThaumcraftApi
                .addShapelessArcaneCraftingRecipe(research, result, craftingAspects, recipe);
        WGContent.recipeList.put(research + tagAddon, arcaneRecipe);
    }
}
