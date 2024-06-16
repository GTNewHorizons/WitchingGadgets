package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;
import static witchinggadgets.common.recipes.WG_arcane_recipes.registerShapelessArcaneRecipe;

import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.WGContent;

public class WG_arcane_advanced_robes {

    public static void registerAdvancedRobes() {

        registerShapelessArcaneRecipe(
                "ADVANCEDROBES",
                "_CLOTH",
                new ItemStack(WGContent.ItemMaterial, 1, 5),
                new AspectList().add(Aspect.ENTROPY, 15).add(Aspect.ORDER, 15).add(Aspect.AIR, 15).add(Aspect.FIRE, 15)
                        .add(Aspect.EARTH, 15).add(Aspect.WATER, 15),
                new ItemStack(WGContent.ItemMaterial, 1, 0),
                new ItemStack(WGContent.ItemMaterial, 1, 2),
                new ItemStack(WGContent.ItemMaterial, 1, 2),
                new ItemStack(WGContent.ItemMaterial, 1, 1));

        registerArcaneRecipe(
                "ADVANCEDROBES",
                "_CHEST",
                new ItemStack(WGContent.ItemAdvancedRobeChest),
                new AspectList().add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25).add(Aspect.AIR, 15),
                " C ",
                "CRC",
                'C',
                new ItemStack(WGContent.ItemMaterial, 1, 5),
                'R',
                new ItemStack(ConfigItems.itemChestRobe));

        registerArcaneRecipe(
                "ADVANCEDROBES",
                "_LEGS",
                new ItemStack(WGContent.ItemAdvancedRobeLegs),
                new AspectList().add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25).add(Aspect.AIR, 15),
                " C ",
                "CRC",
                'C',
                new ItemStack(WGContent.ItemMaterial, 1, 5),
                'R',
                new ItemStack(ConfigItems.itemLegsRobe));
    }
}
