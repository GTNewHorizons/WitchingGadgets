package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionRecipe;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;

public class WG_infusion_baubles {

    static ItemStack luckyCoin = new ItemStack(ConfigItems.itemResource, 1, 18);

    static ItemStack silverFallbackIron() {
        return (!OreDictionary.getOres("ingotSilver").isEmpty() ? OreDictionary.getOres("ingotSilver").get(0)
                : new ItemStack(Items.iron_ingot));
    }

    public static void registerInfusionBaubles() {

        luckyCoin.addEnchantment(Enchantment.fortune, 1);
        luckyCoin.addEnchantment(Enchantment.looting, 1);

        if (WitchingGadgets.isGT5uLoaded) {
            registerInfusionRecipe(
                    "WGBAUBLES",
                    "_HASTEVAMBRACES",
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 3),
                    2,
                    new AspectList().add(Aspect.MINE, 48).add(Aspect.TOOL, 24).add(Aspect.MOTION, 24)
                            .add(Aspect.AIR, 16).add((Aspect) gregtech.api.enums.TCAspects.NEBRISUM.mAspect, 8),
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 8),
                    new ItemStack[] { Materials.Platinum.getIngots(1),
                            ItemList.IC2_CoffeePowder.get(1L, Materials.Coffee.getDust(1)),
                            new ItemStack(Items.potionitem, 1, 8194),
                            ItemList.IC2_CoffeePowder.get(1L, Materials.Coffee.getDust(1)) });

            if (WGModCompat.loaded_Twilight) {
                registerInfusionRecipe(
                        "WGBAUBLES",
                        "_DOUBLEJUMPCHARM",
                        new ItemStack(WGContent.ItemMagicalBaubles, 1, 0),
                        2,
                        new AspectList().add(Aspect.FLIGHT, 16).add(Aspect.MOTION, 8).add(Aspect.AIR, 16)
                                .add((Aspect) gregtech.api.enums.TCAspects.NEBRISUM.mAspect, 8),
                        new ItemStack(WGContent.ItemMagicalBaubles, 1, 7),
                        new ItemStack[] { new ItemStack(WGModCompat.tfMagicMapFocus),
                                ItemList.Electric_Piston_MV.get(1L), new ItemStack(WGModCompat.tfMagicMapFocus),
                                new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(WGModCompat.tfMagicMapFocus),
                                ItemList.Electric_Piston_MV.get(1L) });
            } else {
                registerInfusionRecipe(
                        "WGBAUBLES",
                        "_DOUBLEJUMPCHARM",
                        new ItemStack(WGContent.ItemMagicalBaubles, 1, 0),
                        2,
                        new AspectList().add(Aspect.FLIGHT, 16).add(Aspect.MOTION, 8).add(Aspect.AIR, 16)
                                .add((Aspect) gregtech.api.enums.TCAspects.NEBRISUM.mAspect, 8),
                        new ItemStack(WGContent.ItemMagicalBaubles, 1, 7),
                        new ItemStack[] { new ItemStack(Items.feather), ItemList.Electric_Piston_MV.get(1L),
                                new ItemStack(Items.feather), new ItemStack(ConfigItems.itemShard, 1, 0),
                                new ItemStack(Items.feather), ItemList.Electric_Piston_MV.get(1L) });
            }

            registerInfusionRecipe(
                    "WGBAUBLES",
                    "_SNIPERRING",
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 6),
                    2,
                    new AspectList().add(Aspect.AIR, 32).add(Aspect.WEAPON, 16).add(Aspect.ORDER, 8)
                            .add((Aspect) gregtech.api.enums.TCAspects.NEBRISUM.mAspect, 8),
                    new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1),
                    new ItemStack[] { GTOreDictUnificator.get(OrePrefixes.lens, Materials.InfusedAir, 1L),
                            new ItemStack(ConfigItems.itemPrimalArrow, 1, 0),
                            new ItemStack(ConfigItems.itemPrimalArrow, 1, 1),
                            GTOreDictUnificator.get(OrePrefixes.lens, Materials.InfusedAir, 1L),
                            new ItemStack(ConfigItems.itemPrimalArrow, 1, 2),
                            new ItemStack(ConfigItems.itemPrimalArrow, 1, 3),
                            GTOreDictUnificator.get(OrePrefixes.lens, Materials.InfusedAir, 1L),
                            new ItemStack(ConfigItems.itemPrimalArrow, 1, 4),
                            new ItemStack(ConfigItems.itemPrimalArrow, 1, 5) });

            registerInfusionRecipe(
                    "WGBAUBLES",
                    "_LUCKRING",
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 4),
                    3,
                    new AspectList().add(Aspect.GREED, 32).add(Aspect.TOOL, 16)
                            .add((Aspect) gregtech.api.enums.TCAspects.NEBRISUM.mAspect, 8),
                    new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1),
                    new ItemStack[] { luckyCoin, GTOreDictUnificator.get(OrePrefixes.bolt, Materials.Silver, 1L),
                            luckyCoin, GTOreDictUnificator.get(OrePrefixes.bolt, Materials.Silver, 1L), luckyCoin,
                            GTOreDictUnificator.get(OrePrefixes.bolt, Materials.Silver, 1L), luckyCoin,
                            GTOreDictUnificator.get(OrePrefixes.bolt, Materials.Silver, 1L) });
        } else {
            registerInfusionRecipe(
                    "WGBAUBLES",
                    "_HASTEVAMBRACES",
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 3),
                    2,
                    new AspectList().add(Aspect.MINE, 8).add(Aspect.TOOL, 4).add(Aspect.MOTION, 4).add(Aspect.AIR, 8),
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 8),
                    new ItemStack[] { new ItemStack(Items.gold_ingot), new ItemStack(Items.sugar),
                            new ItemStack(Items.potionitem, 1, 8194), new ItemStack(Items.sugar) });

            if (WGModCompat.loaded_Twilight) {
                registerInfusionRecipe(
                        "WGBAUBLES",
                        "_DOUBLEJUMPCHARM",
                        new ItemStack(WGContent.ItemMagicalBaubles, 1, 0),
                        2,
                        new AspectList().add(Aspect.FLIGHT, 16).add(Aspect.MOTION, 8).add(Aspect.AIR, 16),
                        new ItemStack(WGContent.ItemMagicalBaubles, 1, 7),
                        new ItemStack[] { new ItemStack(WGModCompat.tfMagicMapFocus), silverFallbackIron(),
                                new ItemStack(WGModCompat.tfMagicMapFocus), new ItemStack(ConfigItems.itemShard, 1, 0),
                                new ItemStack(WGModCompat.tfMagicMapFocus), silverFallbackIron() });
            } else {
                registerInfusionRecipe(
                        "WGBAUBLES",
                        "_DOUBLEJUMPCHARM",
                        new ItemStack(WGContent.ItemMagicalBaubles, 1, 0),
                        2,
                        new AspectList().add(Aspect.FLIGHT, 16).add(Aspect.MOTION, 8).add(Aspect.AIR, 16),
                        new ItemStack(WGContent.ItemMagicalBaubles, 1, 7),
                        new ItemStack[] { new ItemStack(Items.feather), silverFallbackIron(),
                                new ItemStack(Items.feather), new ItemStack(ConfigItems.itemShard, 1, 0),
                                new ItemStack(Items.feather), silverFallbackIron() });
            }

            registerInfusionRecipe(
                    "WGBAUBLES",
                    "_SNIPERRING",
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 6),
                    2,
                    new AspectList().add(Aspect.AIR, 16).add(Aspect.WEAPON, 8).add(Aspect.ORDER, 8),
                    new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1),
                    new ItemStack[] { new ItemStack(ConfigItems.itemPrimalArrow, 1, 0),
                            new ItemStack(ConfigItems.itemPrimalArrow, 1, 1),
                            new ItemStack(ConfigItems.itemPrimalArrow, 1, 2),
                            new ItemStack(ConfigItems.itemPrimalArrow, 1, 3),
                            new ItemStack(ConfigItems.itemPrimalArrow, 1, 4),
                            new ItemStack(ConfigItems.itemPrimalArrow, 1, 5) });

            registerInfusionRecipe(
                    "WGBAUBLES",
                    "_LUCKRING",
                    new ItemStack(WGContent.ItemMagicalBaubles, 1, 4),
                    3,
                    new AspectList().add(Aspect.GREED, 32).add(Aspect.TOOL, 16),
                    luckyCoin,
                    new ItemStack[] { new ItemStack(Items.gold_ingot), new ItemStack(Items.dye, 1, 4),
                            silverFallbackIron(), new ItemStack(Items.dye, 1, 4), silverFallbackIron(),
                            new ItemStack(Items.dye, 1, 4), silverFallbackIron(), new ItemStack(Items.dye, 1, 4) });

        }
    }
}
