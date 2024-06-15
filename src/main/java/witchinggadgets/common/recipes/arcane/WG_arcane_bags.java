package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;
import static witchinggadgets.common.recipes.WG_arcane_recipes.registerShapelessArcaneRecipe;

import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;

public class WG_arcane_bags {

    public static void registerArcaneBags() {

        registerShapelessArcaneRecipe(
                "BAGOFTRICKS",
                "_CLOTH",
                new ItemStack(WGContent.ItemMaterial, 2, 3),
                new AspectList().add(Aspect.ENTROPY, 15).add(Aspect.ORDER, 15).add(Aspect.AIR, 15).add(Aspect.FIRE, 15)
                        .add(Aspect.EARTH, 15).add(Aspect.WATER, 15),
                new ItemStack(WGContent.ItemMaterial, 1, 0),
                new ItemStack(WGContent.ItemMaterial, 1, 0),
                new ItemStack(WGContent.ItemMaterial, 1, 2),
                new ItemStack(WGContent.ItemMaterial, 1, 2),
                new ItemStack(WGContent.ItemMaterial, 1, 2),
                new ItemStack(WGContent.ItemMaterial, 1, 2));

        registerArcaneRecipe(
                "BAGOFTRICKS",
                "_BAG",
                new ItemStack(WGContent.ItemBag),
                new AspectList().add(Aspect.ORDER, 50).add(Aspect.AIR, 30),
                "C C",
                "C C",
                "CCC",
                'C',
                new ItemStack(WGContent.ItemMaterial, 1, 3));

        if (WGConfig.bagHungry) {
            registerArcaneRecipe(
                    "HUNGERBAG",
                    "",
                    new ItemStack(WGContent.ItemBag, 1, 3),
                    new AspectList().add(Aspect.ORDER, 50).add(Aspect.AIR, 30).add(Aspect.EARTH, 10),
                    " H ",
                    "CBC",
                    'C',
                    new ItemStack(WGContent.ItemMaterial, 1, 3),
                    'H',
                    new ItemStack(ConfigBlocks.blockChestHungry),
                    'B',
                    new ItemStack(WGContent.ItemBag));
        }
    }
}
