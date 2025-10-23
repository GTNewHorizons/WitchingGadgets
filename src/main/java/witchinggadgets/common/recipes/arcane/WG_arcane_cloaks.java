package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Loader;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTOreDictUnificator;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.items.baubles.ItemCloak;

public class WG_arcane_cloaks {

    public static void registerCloaks() {

        ItemStack standardCloak = new ItemStack(WGContent.ItemCloak, 1, 0);

        if (WitchingGadgets.isGT5uLoaded) {
            registerArcaneRecipe(
                    "CLOAK",
                    "",
                    standardCloak,
                    new AspectList().add(Aspect.AIR, 10),
                    "RSR",
                    "GFG",
                    "GFG",
                    'F',
                    new ItemStack(ConfigItems.itemResource, 1, 7),
                    'G',
                    new ItemStack(WGContent.ItemMaterial, 1, 5),
                    'R',
                    "ringThaumium",
                    'S',
                    new ItemStack(WGContent.ItemMaterial, 1, 3));

            if (WGConfig.capeStorage) {
                registerArcaneRecipe(
                        "CLOAK_STORAGE",
                        "",
                        new ItemStack(WGContent.ItemCloak, 1, 2) /* ItemCloak.getCloakWithTag("STORAGE") */,
                        new AspectList().add(Aspect.AIR, 30).add(Aspect.ENTROPY, 20).add(Aspect.ORDER, 15),
                        "SCS",
                        " B ",
                        'C',
                        standardCloak,
                        'S',
                        new ItemStack(WGContent.ItemMaterial, 1, 3),
                        'B',
                        new ItemStack(WGContent.ItemBag));
            }

            if (WGConfig.capeWolf) {
                registerArcaneRecipe(
                        "CLOAK_WOLF",
                        "",
                        new ItemStack(WGContent.ItemCloak, 1, 3) /* ItemCloak.getCloakWithTag("WOLF") */,
                        new AspectList().add(Aspect.FIRE, 20).add(Aspect.ENTROPY, 30).add(Aspect.EARTH, 25),
                        "WWW",
                        "WCW",
                        "WSW",
                        'C',
                        standardCloak,
                        'S',
                        new ItemStack(ConfigItems.itemShard, 1, 3),
                        'W',
                        new ItemStack(WGContent.ItemMaterial, 1, 6));
            }

            if (WGConfig.capeRaven && Loader.isModLoaded("TwilightForest")) {
                registerArcaneRecipe(
                        "CLOAK_RAVEN",
                        "",
                        new ItemStack(WGContent.ItemCloak, 1, 4) /* ItemCloak.getCloakWithTag("RAVEN") */,
                        new AspectList().add(Aspect.AIR, 30).add(Aspect.ORDER, 25),
                        "FFF",
                        "FCF",
                        "FSF",
                        'C',
                        standardCloak,
                        'S',
                        new ItemStack(ConfigItems.itemShard, 1),
                        'F',
                        GTModHandler.getModItem("TwilightForest", "item.magicMapFocus", 1L));
            }

            if (WGConfig.moduleKama) {
                for (int cm = 0; cm < ItemCloak.subNames.length; cm++) {
                    registerArcaneRecipe(
                            "CLOAKKAMA",
                            ("_" + cm),
                            new ItemStack(WGContent.ItemKama, 1, cm),
                            new AspectList().add(Aspect.AIR, 45).add(Aspect.ORDER, 45),
                            "SBS",
                            "RCR",
                            'B',
                            new ItemStack(ConfigItems.itemBaubleBlanks, 1, 2),
                            'C',
                            new ItemStack(WGContent.ItemCloak, 1, cm),
                            'S',
                            GTOreDictUnificator.get(OrePrefixes.screw, Materials.Thaumium, 1L),
                            'R',
                            "ringThaumium");
                }
            }
        } else {
            registerArcaneRecipe(
                    "CLOAK",
                    "",
                    standardCloak,
                    new AspectList().add(Aspect.AIR, 7),
                    " F ",
                    "FFF",
                    "FFF",
                    'F',
                    new ItemStack(ConfigItems.itemResource, 1, 7));

            if (WGConfig.capeStorage) {
                registerArcaneRecipe(
                        "CLOAK_STORAGE",
                        "",
                        new ItemStack(WGContent.ItemCloak, 1, 2)/* ItemCloak.getCloakWithTag("STORAGE") */,
                        new AspectList().add(Aspect.AIR, 20).add(Aspect.ENTROPY, 15).add(Aspect.ORDER, 10),
                        "SCS",
                        " B ",
                        'C',
                        "travelgearCloakBase",
                        'S',
                        new ItemStack(WGContent.ItemMaterial, 1, 3),
                        'B',
                        new ItemStack(WGContent.ItemBag));
            }

            if (WGConfig.capeWolf) {
                registerArcaneRecipe(
                        "CLOAK_WOLF",
                        "",
                        new ItemStack(WGContent.ItemCloak, 1, 3)/* ItemCloak.getCloakWithTag("WOLF") */,
                        new AspectList().add(Aspect.FIRE, 10).add(Aspect.ENTROPY, 20).add(Aspect.EARTH, 15),
                        " W ",
                        "WCW",
                        'C',
                        standardCloak,
                        'W',
                        new ItemStack(WGContent.ItemMaterial, 1, 6));
            }
            if (WGModCompat.tfRavensFeather != null && WGConfig.capeRaven) {
                registerArcaneRecipe(
                        "CLOAK_RAVEN",
                        "",
                        new ItemStack(WGContent.ItemCloak, 1, 4)/* ItemCloak.getCloakWithTag("RAVEN") */,
                        new AspectList().add(Aspect.AIR, 15).add(Aspect.ORDER, 15),
                        " F ",
                        "FCF",
                        "FSF",
                        'C',
                        standardCloak,
                        'S',
                        new ItemStack(ConfigItems.itemShard, 1, 0),
                        'F',
                        new ItemStack(WGModCompat.tfRavensFeather));
            }

            if (WGConfig.moduleKama) {
                for (int cm = 0; cm < ItemCloak.subNames.length; cm++) registerArcaneRecipe(
                        "CLOAKKAMA",
                        ("_" + cm),
                        new ItemStack(WGContent.ItemKama, 1, cm),
                        new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 5),
                        "B",
                        "C",
                        'B',
                        "baubleBeltBase",
                        'C',
                        new ItemStack(WGContent.ItemCloak, 1, cm));
            }
        }
    }
}
