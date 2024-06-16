package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionRecipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.common.WGContent;

public class WG_infusion_labyrinth_string {

    public static void registerLabyrinthString() {
        registerInfusionRecipe(
                "LABYRINTHSTRING",
                "",
                new ItemStack(WGContent.ItemMaterial, 1, 11),
                2,
                new AspectList().add(Aspect.TRAVEL, 24).add(Aspect.MIND, 16).add(Aspect.TOOL, 8),
                new ItemStack(ConfigBlocks.blockMagicalLog, 1, 1),
                new ItemStack[] { new ItemStack(Items.ender_pearl), new ItemStack(WGContent.ItemMaterial, 1, 2),
                        new ItemStack(WGContent.ItemMaterial, 1, 2), new ItemStack(WGContent.ItemMaterial, 1, 1),
                        new ItemStack(WGContent.ItemMaterial, 1, 1), new ItemStack(ConfigBlocks.blockCrystal, 1, 4) });
    }
}
