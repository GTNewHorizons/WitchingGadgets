package witchinggadgets.common.recipes.other;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.ruling_0.materiallib.api.MaterialLibAPI;

import cpw.mods.fml.common.Loader;
import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;
import gregtech.api.util.GTModHandler;
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
                        MaterialLibAPI.getStack(Materials.InfusedFire, Shapes.bolt, 1),
                        new ItemStack(WGContent.ItemMaterial, 1, 2),
                        MaterialLibAPI.getStack(Materials.InfusedFire, Shapes.bolt, 1),
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
                    MaterialLibAPI.getStack(Materials.Gold, Shapes.wireFine, 1),
                    MaterialLibAPI.getStack(Materials.Gold, Shapes.wireFine, 1));
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
