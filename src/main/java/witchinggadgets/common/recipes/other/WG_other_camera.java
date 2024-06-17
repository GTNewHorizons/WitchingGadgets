package witchinggadgets.common.recipes.other;

import static witchinggadgets.common.recipes.WG_other_recipes.registerShapelessOreRecipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import cpw.mods.fml.common.registry.GameRegistry;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.WGContent;

public class WG_other_camera {

    public static void registerScanCamera() {
        registerShapelessOreRecipe(
                "SCANCAMERA",
                "_CLEARPLATE",
                new ItemStack(ConfigItems.itemResource, 1, 10),
                new ItemStack(WGContent.ItemMaterial, 1, 9));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(WGContent.BlockMetalDevice, 1), "blockVoid"));
    }
}
