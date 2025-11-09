package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;

public class WG_arcane_baubles {

    public static void registerArcaneBaubles() {

        if (WitchingGadgets.isGT5uLoaded) {
            registerArcaneRecipe(
                    "WGBAUBLES",
                    "_BLANKCHARM",
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 7),
                    new AspectList().add(Aspect.ENTROPY, 15).add(Aspect.ORDER, 15).add(Aspect.AIR, 15),
                    "SDS",
                    "PDP",
                    "P P",
                    'D',
                    "ringSteel",
                    'P',
                    new ItemStack(Items.leather, 1, 0),
                    'S',
                    new ItemStack(Items.string, 1, 0));

            registerArcaneRecipe(
                    "WGBAUBLES",
                    "_BLANKVAMBRACES",
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 8),
                    new AspectList().add(Aspect.ENTROPY, 25).add(Aspect.ORDER, 25).add(Aspect.AIR, 25),
                    " P ",
                    "PDP",
                    " P ",
                    'D',
                    "ringSteel",
                    'P',
                    new ItemStack(Items.leather, 1, 0));

            registerArcaneRecipe(
                    "WGBAUBLES",
                    "_KNOCKBACKCHARM",
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 1),
                    new AspectList().add(Aspect.EARTH, 50).add(Aspect.AIR, 15),
                    "BSB",
                    "ETE",
                    "BHB",
                    'H',
                    "craftingToolHardHammer",
                    'E',
                    new ItemStack(ConfigBlocks.blockCrystal, 1, 3),
                    'B',
                    "boltStainlessSteel",
                    'S',
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 7),
                    'T',
                    "plateDenseLead");

            registerArcaneRecipe(
                    "WGBAUBLES",
                    "_WOLFVAMBRACES",
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 2),
                    new AspectList().add(Aspect.ENTROPY, 25).add(Aspect.FIRE, 25).add(Aspect.AIR, 15),
                    " P ",
                    "PVP",
                    " D ",
                    'D',
                    "ringSteel",
                    'P',
                    new ItemStack(WGContent.ItemMaterial, 1, 6),
                    'V',
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 8));
        } else {
            registerArcaneRecipe(
                    "WGBAUBLES",
                    "_BLANKCHARM",
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 7),
                    new AspectList().add(Aspect.ENTROPY, 15).add(Aspect.ORDER, 15).add(Aspect.AIR, 15),
                    "SDS",
                    "PDP",
                    "P P",
                    'D',
                    new ItemStack(Items.iron_ingot, 1, 0),
                    'P',
                    new ItemStack(Items.leather, 1, 0),
                    'S',
                    new ItemStack(Items.string, 1, 0));

            registerArcaneRecipe(
                    "WGBAUBLES",
                    "_BLANKVAMBRACES",
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 8),
                    new AspectList().add(Aspect.ENTROPY, 25).add(Aspect.ORDER, 25).add(Aspect.AIR, 25),
                    " P ",
                    "PDP",
                    " P ",
                    'D',
                    new ItemStack(Items.iron_ingot, 1, 0),
                    'P',
                    new ItemStack(Items.leather, 1, 0));

            registerArcaneRecipe(
                    "WGBAUBLES",
                    "_KNOCKBACKCHARM",
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 1),
                    new AspectList().add(Aspect.EARTH, 50).add(Aspect.AIR, 15),
                    "BSB",
                    "EEE",
                    "B B",
                    'E',
                    new ItemStack(ConfigBlocks.blockCrystal, 1, 3),
                    'B',
                    new ItemStack(Items.iron_ingot, 1, 0),
                    'S',
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 7));

            registerArcaneRecipe(
                    "WGBAUBLES",
                    "_WOLFVAMBRACES",
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 2),
                    new AspectList().add(Aspect.ENTROPY, 25).add(Aspect.FIRE, 25).add(Aspect.AIR, 15),
                    " P ",
                    "PVP",
                    " D ",
                    'D',
                    new ItemStack(Items.iron_ingot, 1, 0),
                    'P',
                    new ItemStack(WGContent.ItemMaterial, 1, 6),
                    'V',
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 8));
        }

        ItemStack luckyCoin = new ItemStack(ConfigItems.itemResource, 1, 18);
        luckyCoin.addEnchantment(Enchantment.fortune, 1);
        luckyCoin.addEnchantment(Enchantment.looting, 1);
        registerArcaneRecipe(
                "WGBAUBLES",
                "_COIN",
                luckyCoin,
                new AspectList().add(Aspect.ORDER, 30),
                "BCB",
                "CCC",
                "BCB",
                'C',
                new ItemStack(ConfigItems.itemResource, 1, 18),
                'B',
                Items.enchanted_book);
    }
}
