package witchinggadgets.common.recipes;

import cpw.mods.fml.common.Loader;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.items.baubles.ItemCloak;
import witchinggadgets.common.util.recipe.PhotoDevelopingRecipe;

public class WG_arcane_recipes {
    static AspectList infusionAspects;
    static AspectList craftingAspects;
    static AspectList alchemyAspects;

    public static void regist_arcane() {
        ItemStack standardCloak = new ItemStack(WGContent.ItemCloak, 1, 0);
        /**
         * infusionAspects = new AspectList().add(Aspect.DEATH, 40).add(Aspect.METAL, 40).add(Aspect.WEAPON, 75).add(Aspect.ENTROPY, 50).add(Aspect.HUNGER, 25);
         * InfusionRecipe infR_vorp = ThaumcraftApi.addInfusionCraftingRecipe("VORPALBLADE",new ItemStack(Item.swordIron),4,infusionAspects,new ItemStack(ConfigItems.itemFocusPortableHole", 0),new ItemStack[] {new ItemStack(ConfigItems.itemShard", 5),new ItemStack(ConfigItems.itemShard", 5),new ItemStack(Item.enderPearl),new ItemStack(Item.enderPearl),new ItemStack(Item.ghastTear)});
         */

        // ADVANCEDSCRIBINGTOOLS
        registerShapelessArcaneRecipe(
                "ADVANCEDSCRIBINGTOOLS",
                "refill",
                new ItemStack(WGContent.ItemAdvancedScribingTools),
                new AspectList().add(Aspect.AIR, 10).add(Aspect.FIRE, 10),
                new Object[] {
                    new ItemStack(WGContent.ItemAdvancedScribingTools, 1, 32767), "dustSmallGold", Items.glowstone_dust
                });

        if (WGModCompat.loaded_Twilight)
            registerShapelessArcaneRecipe(
                    "ADVANCEDSCRIBINGTOOLS",
                    "",
                    new ItemStack(WGContent.ItemAdvancedScribingTools),
                    new AspectList()
                            .add(Aspect.AIR, 50)
                            .add(Aspect.FIRE, 50)
                            .add(Aspect.ORDER, 50)
                            .add(Aspect.ENTROPY, 50)
                            .add(Aspect.EARTH, 50)
                            .add(Aspect.WATER, 50),
                    new Object[] {
                        "dustGold",
                        "dustGold",
                        "dustGold",
                        Items.glowstone_dust,
                        Items.glowstone_dust,
                        Items.glowstone_dust,
                        Items.glowstone_dust,
                        new ItemStack(WGModCompat.tfMagicMapFocus),
                        thaumcraft.api.ItemApi.getItem("itemEssence", 0)
                    });
        else
            registerShapelessArcaneRecipe(
                    "ADVANCEDSCRIBINGTOOLS",
                    "",
                    new ItemStack(WGContent.ItemAdvancedScribingTools),
                    new AspectList()
                            .add(Aspect.AIR, 50)
                            .add(Aspect.FIRE, 50)
                            .add(Aspect.ORDER, 50)
                            .add(Aspect.ENTROPY, 50)
                            .add(Aspect.EARTH, 50)
                            .add(Aspect.WATER, 50),
                    new Object[] {
                        "dustGold", new ItemStack(Items.feather), thaumcraft.api.ItemApi.getItem("itemEssence", 0)
                    });

        // SCANCAMERA
        craftingAspects =
                new AspectList().add(Aspect.AIR, 20).add(Aspect.EARTH, 20).add(Aspect.ORDER, 10);
        registerArcaneRecipe(
                "SCANCAMERA",
                "",
                new ItemStack(WGContent.ItemScanCamera),
                craftingAspects,
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
        IArcaneRecipe developingRecipe = new PhotoDevelopingRecipe();
        ThaumcraftApi.getCraftingRecipes().add(developingRecipe);
        WGContent.recipeList.put("SCANCAMERA_DEVELOP", developingRecipe);

        // CALCULATOR
        craftingAspects = new AspectList().add(Aspect.ORDER, 50);
        registerArcaneRecipe(
                "CALCULATOR",
                "",
                new ItemStack(WGContent.ItemMaterial, 1, 7),
                craftingAspects,
                "srs",
                "sbs",
                "sgs",
                's',
                "stickThaumium",
                'r',
                ItemList.Sensor_HV.get(1L),
                'b',
                "circuitAdvanced",
                'g',
                new ItemStack(ConfigBlocks.blockStoneDevice, 1, 2));

        // CLOAKS
        if (WGConfig.moduleCloak) {
            craftingAspects = new AspectList().add(Aspect.AIR, 10);
            registerArcaneRecipe(
                    "CLOAK",
                    "",
                    standardCloak,
                    craftingAspects,
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
                craftingAspects = new AspectList()
                        .add(Aspect.AIR, 30)
                        .add(Aspect.ENTROPY, 20)
                        .add(Aspect.ORDER, 15);
                registerArcaneRecipe(
                        "CLOAK_STORAGE",
                        "",
                        new ItemStack(WGContent.ItemCloak, 1, 2) /*ItemCloak.getCloakWithTag("STORAGE")*/,
                        craftingAspects,
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
                craftingAspects = new AspectList()
                        .add(Aspect.FIRE, 20)
                        .add(Aspect.ENTROPY, 30)
                        .add(Aspect.EARTH, 25);
                registerArcaneRecipe(
                        "CLOAK_WOLF",
                        "",
                        new ItemStack(WGContent.ItemCloak, 1, 3) /*ItemCloak.getCloakWithTag("WOLF")*/,
                        craftingAspects,
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
            if (WGConfig.capeRaven) {
                craftingAspects = new AspectList().add(Aspect.AIR, 30).add(Aspect.ORDER, 25);
                registerArcaneRecipe(
                        "CLOAK_RAVEN",
                        "",
                        new ItemStack(WGContent.ItemCloak, 1, 4) /*ItemCloak.getCloakWithTag("RAVEN")*/,
                        craftingAspects,
                        "FFF",
                        "FCF",
                        "FSF",
                        'C',
                        standardCloak,
                        'S',
                        new ItemStack(ConfigItems.itemShard, 1),
                        'F',
                        GT_ModHandler.getModItem("TwilightForest", "item.magicMapFocus", 1L));
            }

            if (WGConfig.moduleKama) {
                craftingAspects = new AspectList().add(Aspect.AIR, 45).add(Aspect.ORDER, 45);
                for (int cm = 0; cm < ItemCloak.subNames.length; cm++)
                    registerArcaneRecipe(
                            "CLOAKKAMA",
                            ("_" + cm),
                            new ItemStack(WGContent.ItemKama, 1, cm),
                            craftingAspects,
                            "SBS",
                            "RCR",
                            'B',
                            "baubleBeltBase",
                            'C',
                            new ItemStack(WGContent.ItemCloak, 1, cm),
                            'S',
                            GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Thaumium, 1L),
                            'R',
                            "ringThaumium");
            }
        }
        // ETHEREALWALL
        craftingAspects = new AspectList().add(Aspect.ORDER, 15).add(Aspect.EARTH, 5);
        registerArcaneRecipe(
                "ETHEREALWALL",
                "",
                new ItemStack(WGContent.BlockStoneDevice, 6, 0),
                craftingAspects,
                "SsS",
                "STS",
                "SsS",
                'S',
                new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6),
                's',
                new ItemStack(ConfigBlocks.blockCrystal, 1, 32767),
                'T',
                new ItemStack(Blocks.redstone_lamp));

        if (Loader.isModLoaded("magicbees")) {
            // AGEINGSTONE
            craftingAspects = new AspectList().add(Aspect.ORDER, 15).add(Aspect.ENTROPY, 15);
            registerArcaneRecipe(
                    "AGEINGSTONE",
                    "",
                    new ItemStack(WGContent.BlockStoneDevice, 1, 1),
                    craftingAspects,
                    " s ",
                    "SCS",
                    " s ",
                    'S',
                    new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6),
                    's',
                    new ItemStack(ConfigBlocks.blockCrystal, 1, 32767),
                    'C',
                    GT_ModHandler.getModItem("MagicBees", "miscResources", 1L, 9));
        }

        // SPINNINGWHEEL
        craftingAspects = new AspectList().add(Aspect.ORDER, 10).add(Aspect.AIR, 10);
        registerArcaneRecipe(
                "SPINNINGWHEEL",
                "",
                new ItemStack(WGContent.BlockWoodenDevice),
                craftingAspects,
                "IsW",
                "RDS",
                "TTT",
                'T',
                new ItemStack(ConfigBlocks.blockTable),
                's',
                GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Steel, 1L),
                'D',
                "craftingToolScrewdriver",
                'I',
                "gearGtWoodSealed",
                'R',
                GT_OreDictUnificator.get(OrePrefixes.stickLong, Materials.WoodSealed, 1L),
                'S',
                GT_OreDictUnificator.get(OrePrefixes.stick, Materials.WoodSealed, 1L),
                'W',
                "gearGtSmallThaumium");
        // LABELLIB
        craftingAspects =
                new AspectList().add(Aspect.ORDER, 30).add(Aspect.EARTH, 10).add(Aspect.WATER, 10);
        registerArcaneRecipe(
                "LABELLIB",
                "",
                new ItemStack(WGContent.BlockWoodenDevice, 1, 5),
                craftingAspects,
                "BLW",
                "sTs",
                " D ",
                'B',
                new ItemStack(Items.book),
                's',
                GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Steel, 1L),
                'D',
                "craftingToolScrewdriver",
                'L',
                new ItemStack(ConfigItems.itemResource, 1, 13),
                'W',
                "scribingTools",
                'T',
                new ItemStack(ConfigBlocks.blockTable));

        if (WGConfig.moduleBag) {
            craftingAspects = new AspectList()
                    .add(Aspect.ENTROPY, 15)
                    .add(Aspect.ORDER, 15)
                    .add(Aspect.AIR, 15)
                    .add(Aspect.FIRE, 15)
                    .add(Aspect.EARTH, 15)
                    .add(Aspect.WATER, 15);
            registerShapelessArcaneRecipe(
                    "BAGOFTRICKS",
                    "_CLOTH",
                    new ItemStack(WGContent.ItemMaterial, 2, 3),
                    craftingAspects,
                    new ItemStack(WGContent.ItemMaterial, 1, 0),
                    new ItemStack(WGContent.ItemMaterial, 1, 0),
                    new ItemStack(WGContent.ItemMaterial, 1, 2),
                    new ItemStack(WGContent.ItemMaterial, 1, 2),
                    new ItemStack(WGContent.ItemMaterial, 1, 2),
                    new ItemStack(WGContent.ItemMaterial, 1, 2));

            craftingAspects = new AspectList().add(Aspect.ORDER, 50).add(Aspect.AIR, 30);
            registerArcaneRecipe(
                    "BAGOFTRICKS",
                    "_BAG",
                    new ItemStack(WGContent.ItemBag),
                    craftingAspects,
                    "C C",
                    "C C",
                    "CCC",
                    'C',
                    new ItemStack(WGContent.ItemMaterial, 1, 3));

            if (WGConfig.bagHungry) {
                craftingAspects = new AspectList()
                        .add(Aspect.ORDER, 50)
                        .add(Aspect.AIR, 30)
                        .add(Aspect.EARTH, 10);
                registerArcaneRecipe(
                        "HUNGERBAG",
                        "",
                        new ItemStack(WGContent.ItemBag, 1, 3),
                        craftingAspects,
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

        craftingAspects = new AspectList()
                .add(Aspect.ENTROPY, 15)
                .add(Aspect.ORDER, 15)
                .add(Aspect.AIR, 15)
                .add(Aspect.FIRE, 15)
                .add(Aspect.EARTH, 15)
                .add(Aspect.WATER, 15);
        registerShapelessArcaneRecipe(
                "ADVANCEDROBES",
                "_CLOTH",
                new ItemStack(WGContent.ItemMaterial, 1, 5),
                craftingAspects,
                new ItemStack(WGContent.ItemMaterial, 1, 0),
                new ItemStack(WGContent.ItemMaterial, 1, 2),
                new ItemStack(WGContent.ItemMaterial, 1, 2),
                new ItemStack(WGContent.ItemMaterial, 1, 1));

        craftingAspects =
                new AspectList().add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25).add(Aspect.AIR, 15);
        registerArcaneRecipe(
                "ADVANCEDROBES",
                "_CHEST",
                new ItemStack(WGContent.ItemAdvancedRobeChest),
                craftingAspects,
                " C ",
                "CRC",
                'C',
                new ItemStack(WGContent.ItemMaterial, 1, 5),
                'R',
                new ItemStack(ConfigItems.itemChestRobe));
        craftingAspects =
                new AspectList().add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25).add(Aspect.AIR, 15);
        registerArcaneRecipe(
                "ADVANCEDROBES",
                "_LEGS",
                new ItemStack(WGContent.ItemAdvancedRobeLegs),
                craftingAspects,
                " C ",
                "CRC",
                'C',
                new ItemStack(WGContent.ItemMaterial, 1, 5),
                'R',
                new ItemStack(ConfigItems.itemLegsRobe));

        craftingAspects =
                new AspectList().add(Aspect.ENTROPY, 25).add(Aspect.FIRE, 25).add(Aspect.AIR, 15);
        registerArcaneRecipe(
                "WGBAUBLES",
                "_WOLFVAMBRACES",
                new ItemStack(WGContent.ItemMagicalBaubles, 1, 2),
                craftingAspects,
                " P ",
                "PVP",
                " D ",
                'D',
                "ringDamascusSteel",
                'P',
                new ItemStack(WGContent.ItemMaterial, 1, 6),
                'V',
                "travelgearVambraceBase");

        craftingAspects = new AspectList().add(Aspect.EARTH, 50).add(Aspect.AIR, 15);
        registerArcaneRecipe(
                "WGBAUBLES",
                "_KNOCKBACKSHOULDERS",
                new ItemStack(WGContent.ItemMagicalBaubles, 1, 1),
                craftingAspects,
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
        craftingAspects = new AspectList().add(Aspect.ORDER, 30);
        registerArcaneRecipe(
                "WGBAUBLES",
                "_COIN",
                luckyCoin,
                craftingAspects,
                "BCB",
                "CCC",
                "BCB",
                'C',
                new ItemStack(ConfigItems.itemResource, 1, 18),
                'B',
                Items.enchanted_book);

        // book special
        ItemStack soulBook =
                Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(WGContent.enc_soulbound, 1));
        craftingAspects =
                new AspectList().add(Aspect.ORDER, 30).add(Aspect.AIR, 15).add(Aspect.ENTROPY, 15);
        registerArcaneRecipe(
                "ENCH_SOULBOUND",
                "_BOOK",
                soulBook,
                craftingAspects,
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

    private static void registerArcaneRecipe(
            String research, String tagAddon, ItemStack result, AspectList craftingAspects, Object... recipe) {
        ShapedArcaneRecipe arcaneRecipe =
                ThaumcraftApi.addArcaneCraftingRecipe(research, result, craftingAspects, recipe);
        WGContent.recipeList.put(research + tagAddon, arcaneRecipe);
    }

    private static void registerShapelessArcaneRecipe(
            String research, String tagAddon, ItemStack result, AspectList craftingAspects, Object... recipe) {
        ShapelessArcaneRecipe arcaneRecipe =
                ThaumcraftApi.addShapelessArcaneCraftingRecipe(research, result, craftingAspects, recipe);
        WGContent.recipeList.put(research + tagAddon, arcaneRecipe);
    }
}
