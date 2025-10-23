package witchinggadgets.common.recipes.other;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Loader;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTOreDictUnificator;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.util.recipe.SpinningRecipe;

public class WG_other_spinning {

    public static void registerSpinningRecipes() {
        if (WitchingGadgets.isGT5uLoaded) {
            if (Loader.isModLoaded("Natura")) {
                SpinningRecipe spin_String = new SpinningRecipe(
                        new ItemStack(Items.string, 5),
                        GTModHandler.getModItem("Natura", "barleyFood", 1L, 3),
                        GTModHandler.getModItem("Natura", "barleyFood", 1L, 3),
                        GTModHandler.getModItem("Natura", "barleyFood", 1L, 3),
                        GTModHandler.getModItem("Natura", "barleyFood", 1L, 3),
                        GTModHandler.getModItem("Natura", "barleyFood", 1L, 3));
                SpinningRecipe.addRecipe(spin_String);

                SpinningRecipe spin_flameString = new SpinningRecipe(
                        GTModHandler.getModItem("Natura", "barleyFood", 2L, 7),
                        new ItemStack(WGContent.ItemMaterial, 1, 2),
                        GTOreDictUnificator.get(OrePrefixes.bolt, Materials.InfusedFire, 1L),
                        new ItemStack(WGContent.ItemMaterial, 1, 2),
                        GTOreDictUnificator.get(OrePrefixes.bolt, Materials.InfusedFire, 1L),
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
                    GTOreDictUnificator.get(OrePrefixes.wireFine, Materials.Gold, 1L),
                    GTOreDictUnificator.get(OrePrefixes.wireFine, Materials.Gold, 1L));
            SpinningRecipe.addRecipe(spin_goldThread);

            SpinningRecipe spin_thaumiumThread = new SpinningRecipe(
                    new ItemStack(WGContent.ItemMaterial, 2, 2),
                    Items.string,
                    Items.string,
                    "wireFineThaumium",
                    "wireFineThaumium");
            SpinningRecipe.addRecipe(spin_thaumiumThread);
        } else {
            if (Loader.isModLoaded("Natura")) {
                SpinningRecipe spin_String = new SpinningRecipe(
                        new ItemStack(Items.string, 5),
                        new ItemStack(WGModCompat.natBarleyFood, 1, 3),
                        new ItemStack(WGModCompat.natBarleyFood, 1, 3),
                        new ItemStack(WGModCompat.natBarleyFood, 1, 3),
                        new ItemStack(WGModCompat.natBarleyFood, 1, 3),
                        new ItemStack(WGModCompat.natBarleyFood, 1, 3));
                SpinningRecipe.addRecipe(spin_String);
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
                    new Object[] { Items.string, Items.string, Items.gold_nugget, Items.gold_nugget });
            SpinningRecipe.addRecipe(spin_goldThread);

            SpinningRecipe spin_thaumiumThread = new SpinningRecipe(
                    new ItemStack(WGContent.ItemMaterial, 2, 2),
                    Items.string,
                    Items.string,
                    "nuggetThaumium",
                    "nuggetThaumium");
            SpinningRecipe.addRecipe(spin_thaumiumThread);
        }
    }
}
