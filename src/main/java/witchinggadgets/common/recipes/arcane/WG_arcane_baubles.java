package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.WGContent;

public class WG_arcane_baubles {

    public static void registerArcaneBaubles() {
        registerArcaneRecipe(
                "WGBAUBLES",
                "_WOLFVAMBRACES",
                new ItemStack(WGContent.ItemMagicalBaubles, 1, 2),
                new AspectList().add(Aspect.ENTROPY, 25).add(Aspect.FIRE, 25).add(Aspect.AIR, 15),
                " P ",
                "PVP",
                " D ",
                'D',
                "ringDamascusSteel",
                'P',
                new ItemStack(WGContent.ItemMaterial, 1, 6),
                'V',
                "travelgearVambraceBase");

        registerArcaneRecipe(
                "WGBAUBLES",
                "_KNOCKBACKSHOULDERS",
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
                "travelgearShoulderBase",
                'T',
                "plateDenseLead");

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
