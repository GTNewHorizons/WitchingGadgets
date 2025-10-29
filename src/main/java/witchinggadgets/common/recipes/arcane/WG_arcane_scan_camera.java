package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.ItemList;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.util.recipe.PhotoDevelopingRecipe;

public class WG_arcane_scan_camera {

    public static void registerScanCamera() {
        if (WitchingGadgets.isGT5uLoaded) {
            registerArcaneRecipe(
                    "SCANCAMERA",
                    "",
                    new ItemStack(WGContent.ItemScanCamera),
                    new AspectList().add(Aspect.AIR, 20).add(Aspect.EARTH, 20).add(Aspect.ORDER, 10),
                    "wlc",
                    "pmt",
                    "wlc",
                    't',
                    ConfigItems.itemThaumometer,
                    'm',
                    new ItemStack(ConfigItems.itemResource, 1, 10),
                    'p',
                    Blocks.glass_pane,
                    'w',
                    new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6),
                    'l',
                    Items.leather,
                    'c',
                    ItemList.Sensor_MV.get(1L));
        } else {
            registerArcaneRecipe(
                    "SCANCAMERA",
                    "",
                    new ItemStack(WGContent.ItemScanCamera),
                    new AspectList().add(Aspect.AIR, 20).add(Aspect.EARTH, 20).add(Aspect.ORDER, 10),
                    "wl ",
                    "pmt",
                    "wl ",
                    't',
                    ConfigItems.itemThaumometer,
                    'm',
                    new ItemStack(ConfigItems.itemResource, 1, 10),
                    'p',
                    Blocks.glass_pane,
                    'w',
                    new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6),
                    'l',
                    Items.leather);
        }

        IArcaneRecipe developingRecipe = new PhotoDevelopingRecipe();
        ThaumcraftApi.getCraftingRecipes().add(developingRecipe);
        WGContent.recipeList.put("SCANCAMERA_DEVELOP", developingRecipe);
    }
}
