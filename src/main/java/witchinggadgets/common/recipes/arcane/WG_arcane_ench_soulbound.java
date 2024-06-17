package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import witchinggadgets.common.WGContent;

public class WG_arcane_ench_soulbound {

    public static void registerEnchSoulbound() {

        ItemStack soulBook = Items.enchanted_book
                .getEnchantedItemStack(new EnchantmentData(WGContent.enc_soulbound, 1));
        registerArcaneRecipe(
                "ENCH_SOULBOUND",
                "_BOOK",
                soulBook,
                new AspectList().add(Aspect.ORDER, 30).add(Aspect.AIR, 15).add(Aspect.ENTROPY, 15),
                " E ",
                "GBG",
                " P ",
                'E',
                new ItemStack(Items.ender_eye),
                'B',
                new ItemStack(Items.enchanted_book),
                'P',
                new ItemStack(Items.ender_pearl),
                'G',
                new ItemStack(Items.gold_ingot));
    }
}
