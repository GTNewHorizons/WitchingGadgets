package witchinggadgets.common.recipes.other;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Loader;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.util.recipe.SpinningRecipe;

public class WG_other_spinning {

    public static void registerSpinningRecipes() {
        if (Loader.isModLoaded("Natura")) {
            SpinningRecipe spin_String = new SpinningRecipe(
                    new ItemStack(Items.string, 5),
                    GT_ModHandler.getModItem("Natura", "barleyFood", 1L, 3),
                    GT_ModHandler.getModItem("Natura", "barleyFood", 1L, 3),
                    GT_ModHandler.getModItem("Natura", "barleyFood", 1L, 3),
                    GT_ModHandler.getModItem("Natura", "barleyFood", 1L, 3),
                    GT_ModHandler.getModItem("Natura", "barleyFood", 1L, 3));
            SpinningRecipe.addRecipe(spin_String);

            SpinningRecipe spin_flameString = new SpinningRecipe(
                    GT_ModHandler.getModItem("Natura", "barleyFood", 2L, 7),
                    new ItemStack(WGContent.ItemMaterial, 1, 2),
                    GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.InfusedFire, 1L),
                    new ItemStack(WGContent.ItemMaterial, 1, 2),
                    GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.InfusedFire, 1L),
                    new ItemStack(WGContent.ItemMaterial, 1, 2));
            SpinningRecipe.addRecipe(spin_flameString);
        }

        SpinningRecipe spin_Thread = new SpinningRecipe(
                new ItemStack(WGContent.ItemMaterial, 2, 0),
                Items.string,
                Items.string,
                Items.string,
                Items.string);
        SpinningRecipe.addRecipe(spin_Thread);

        SpinningRecipe spin_goldThread = new SpinningRecipe(
                new ItemStack(WGContent.ItemMaterial, 2, 1),
                Items.string,
                Items.string,
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Gold, 1L),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Gold, 1L));
        SpinningRecipe.addRecipe(spin_goldThread);

        SpinningRecipe spin_thaumiumThread = new SpinningRecipe(
                new ItemStack(WGContent.ItemMaterial, 2, 2),
                Items.string,
                Items.string,
                "wireFineThaumium",
                "wireFineThaumium");
        SpinningRecipe.addRecipe(spin_thaumiumThread);
    }
}
