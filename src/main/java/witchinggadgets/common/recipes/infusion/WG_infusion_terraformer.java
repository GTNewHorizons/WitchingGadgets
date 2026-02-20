package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionRecipe;
import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import fox.spiteful.forbidden.DarkAspects;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTOreDictUnificator;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;

public class WG_infusion_terraformer {

    public static void registerTerraformer() {
        ItemStack salisMundusIngredient = null;
        if (WitchingGadgets.isGT5uLoaded) {
            salisMundusIngredient = GameRegistry.makeItemStack("Thaumcraft:ItemResource", 14, 1, null);
            if (Loader.isModLoaded("thaumicbases")) {
                salisMundusIngredient = gregtech.api.util.GTModHandler
                        .getModItem("thaumicbases", "blockSalisMundus", 1);
            }
        }

        if (WitchingGadgets.isGT5uLoaded) {
            if (Loader.isModLoaded("dreamcraft")) {
                registerInfusionRecipe(
                        "TERRAFORMER",
                        "",
                        new ItemStack(WGContent.BlockMetalDevice, 1, 2),
                        10,
                        new AspectList().add(Aspect.WEATHER, 64).add(Aspect.EXCHANGE, 256)
                                .add((Aspect) gregtech.api.enums.TCAspects.NEBRISUM.mAspect, 32),
                        new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9),
                        new ItemStack[] { Materials.Blaze.getBlocks(1),
                                GTOreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),
                                GTModHandler.getModItem("dreamcraft", "CallistoColdIce", 1L),
                                Materials.Knightmetal.getBlocks(1),
                                gregtech.api.enums.ItemList.Field_Generator_MV.get(1L),
                                GTOreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L) });
            } else {
                registerInfusionRecipe(
                        "TERRAFORMER",
                        "",
                        new ItemStack(WGContent.BlockMetalDevice, 1, 2),
                        10,
                        new AspectList().add(Aspect.WEATHER, 64).add(Aspect.EXCHANGE, 256)
                                .add((Aspect) gregtech.api.enums.TCAspects.NEBRISUM.mAspect, 32),
                        new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9),
                        new ItemStack[] { Materials.Blaze.getBlocks(1),
                                GTOreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),
                                new ItemStack(Blocks.packed_ice), Materials.Knightmetal.getBlocks(1),
                                gregtech.api.enums.ItemList.Field_Generator_MV.get(1L),
                                GTOreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L) });
            }

            registerInfusionRecipe(
                    "TERRAFORMFOCUS_TAINT",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 8),
                    9,
                    new AspectList().add(Aspect.TAINT, 32).add(Aspect.EXCHANGE, 16),
                    new ItemStack(ConfigBlocks.blockTaint, 1, 0),
                    new ItemStack[] { salisMundusIngredient,
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            new ItemStack(ConfigBlocks.blockTube, 1, 0),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L) });
            ThaumcraftApi.addWarpToItem(new ItemStack(WGContent.BlockMetalDevice, 1, 8), 2);

            registerInfusionRecipe(
                    "TERRAFORMFOCUS_RIVER",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 10),
                    4,
                    new AspectList().add(Aspect.WATER, 32).add(Aspect.EXCHANGE, 16),
                    new ItemStack(Items.water_bucket, 1, 0),
                    new ItemStack[] { salisMundusIngredient,
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            new ItemStack(ConfigBlocks.blockTube, 1, 0),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L) });

            registerInfusionRecipe(
                    "TERRAFORMFOCUS_OCEAN",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 11),
                    5,
                    new AspectList().add(Aspect.WATER, 64).add(Aspect.EXCHANGE, 16),
                    new ItemStack(WGContent.BlockMetalDevice, 1, 10),
                    new ItemStack[] { salisMundusIngredient,
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            new ItemStack(ConfigBlocks.blockTube, 1, 0),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L) });

            registerInfusionRecipe(
                    "TERRAFORMFOCUS_END",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 12),
                    6,
                    new AspectList().add(Aspect.ELDRITCH, 32).add(Aspect.EXCHANGE, 64),
                    new ItemStack(Blocks.end_stone, 1, 0),
                    new ItemStack[] { salisMundusIngredient,
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            new ItemStack(ConfigBlocks.blockTube, 1, 0),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L) });
            ThaumcraftApi.addWarpToItem(new ItemStack(WGContent.BlockMetalDevice, 1, 12), 2);

            registerInfusionRecipe(
                    "TERRAFORMFOCUS_PLAINS",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 3),
                    3,
                    new AspectList().add(Aspect.EARTH, 32).add(Aspect.EXCHANGE, 16),
                    new ItemStack(Blocks.grass),
                    new ItemStack[] { salisMundusIngredient,
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            new ItemStack(ConfigBlocks.blockTube, 1, 0),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L) });

            registerInfusionRecipe(
                    "TERRAFORMFOCUS_COLDTAIGA",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 4),
                    5,
                    new AspectList().add(Aspect.COLD, 32).add(Aspect.EXCHANGE, 16),
                    new ItemStack(Blocks.ice),
                    new ItemStack[] { salisMundusIngredient,
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            new ItemStack(ConfigBlocks.blockTube, 1, 0),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L) });

            registerInfusionRecipe(
                    "TERRAFORMFOCUS_DESERT",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 5),
                    5,
                    new AspectList().add(Aspect.FIRE, 32).add(Aspect.EXCHANGE, 16),
                    new ItemStack(Blocks.sand),
                    new ItemStack[] { salisMundusIngredient,
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            new ItemStack(ConfigBlocks.blockTube, 1, 0),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L) });

            registerInfusionRecipe(
                    "TERRAFORMFOCUS_JUNGLE",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 6),
                    5,
                    new AspectList().add(Aspect.LIFE, 16).add(Aspect.FIRE, 16).add(Aspect.EXCHANGE, 16),
                    new ItemStack(Blocks.log, 1, 3),
                    new ItemStack[] { salisMundusIngredient,
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            new ItemStack(ConfigBlocks.blockTube, 1, 0),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L) });

            if (Loader.isModLoaded("ForbiddenMagic")) {
                registerInfusionRecipe(
                        "TERRAFORMFOCUS_HELL",
                        "",
                        new ItemStack(WGContent.BlockMetalDevice, 1, 7),
                        8,
                        new AspectList().add(DarkAspects.NETHER, 32).add(Aspect.EXCHANGE, 16),
                        new ItemStack(Blocks.nether_brick),
                        new ItemStack[] { salisMundusIngredient,
                                GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                                GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                                new ItemStack(ConfigBlocks.blockTube, 1, 0),
                                GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                                GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L) });
            } else {
                registerInfusionRecipe(
                        "TERRAFORMFOCUS_HELL",
                        "",
                        new ItemStack(WGContent.BlockMetalDevice, 1, 7),
                        8,
                        new AspectList().add(Aspect.FIRE, 32).add(Aspect.EXCHANGE, 16),
                        new ItemStack(Blocks.nether_brick),
                        new ItemStack[] { salisMundusIngredient,
                                GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                                GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                                new ItemStack(ConfigBlocks.blockTube, 1, 0),
                                GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                                GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L) });
            }
            ThaumcraftApi.addWarpToItem(new ItemStack(WGContent.BlockMetalDevice, 1, 7), 1);

            registerInfusionRecipe(
                    "TERRAFORMFOCUS_MUSHROOM",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 9),
                    6,
                    new AspectList().add(Aspect.SLIME, 32).add(Aspect.EXCHANGE, 16),
                    new ItemStack(Blocks.mycelium),
                    new ItemStack[] { salisMundusIngredient,
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            new ItemStack(ConfigBlocks.blockTube, 1, 0),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L) });

            registerInfusionRecipe(
                    "TERRAFORMFOCUS_MAGIC",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 13),
                    8,
                    new AspectList().add(Aspect.HEAL, 32).add(Aspect.EXCHANGE, 8).add(Aspect.ORDER, 16),
                    new ItemStack(ConfigBlocks.blockMagicalLog, 1, 1),
                    new ItemStack[] { new ItemStack(ConfigBlocks.blockCrystal, 1, 6),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            new ItemStack(ConfigBlocks.blockTube, 1, 0),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L),
                            GTOreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L) });
        } else {
            registerInfusionRecipe(
                    "TERRAFORMER",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 2),
                    3,
                    new AspectList().add(Aspect.ORDER, 16).add(Aspect.EXCHANGE, 8).add(Aspect.EARTH, 16),
                    new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9),
                    new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(Items.iron_ingot),
                            new ItemStack(ConfigBlocks.blockTube), new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1),
                            new ItemStack(ConfigBlocks.blockTube), new ItemStack(Items.iron_ingot) });

            registerInfusionRecipe(
                    "TERRAFORMFOCUS_TAINT",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 8),
                    3,
                    new AspectList().add(Aspect.TAINT, 32).add(Aspect.EXCHANGE, 8),
                    new ItemStack(ConfigBlocks.blockTaint, 1, 0),
                    new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(Items.iron_ingot),
                            new ItemStack(Items.iron_ingot), new ItemStack(ConfigBlocks.blockTube, 1, 0),
                            new ItemStack(Items.iron_ingot), new ItemStack(Items.iron_ingot) });
            ThaumcraftApi.addWarpToItem(new ItemStack(WGContent.BlockMetalDevice, 1, 8), 2);

            registerInfusionRecipe(
                    "TERRAFORMFOCUS_RIVER",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 10),
                    3,
                    new AspectList().add(Aspect.WATER, 32).add(Aspect.EXCHANGE, 8),
                    new ItemStack(Items.water_bucket, 1, 0),
                    new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(Items.iron_ingot),
                            new ItemStack(Items.iron_ingot), new ItemStack(ConfigBlocks.blockTube, 1, 0),
                            new ItemStack(Items.iron_ingot), new ItemStack(Items.iron_ingot) });

            registerInfusionRecipe(
                    "TERRAFORMFOCUS_OCEAN",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 11),
                    3,
                    new AspectList().add(Aspect.WATER, 64).add(Aspect.EXCHANGE, 8),
                    new ItemStack(WGContent.BlockMetalDevice, 1, 10),
                    new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(Items.iron_ingot),
                            new ItemStack(Items.iron_ingot), new ItemStack(ConfigBlocks.blockTube, 1, 0),
                            new ItemStack(Items.iron_ingot), new ItemStack(Items.iron_ingot) });

            registerInfusionRecipe(
                    "TERRAFORMFOCUS_END",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 12),
                    3,
                    new AspectList().add(Aspect.ELDRITCH, 32).add(Aspect.EXCHANGE, 8),
                    new ItemStack(Blocks.end_stone, 1, 0),
                    new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(Items.iron_ingot),
                            new ItemStack(Items.iron_ingot), new ItemStack(ConfigBlocks.blockTube, 1, 0),
                            new ItemStack(Items.iron_ingot), new ItemStack(Items.iron_ingot) });
            ThaumcraftApi.addWarpToItem(new ItemStack(WGContent.BlockMetalDevice, 1, 12), 2);

            registerArcaneRecipe(
                    "TERRAFORMFOCUS_PLAINS",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 3),
                    new AspectList().add(Aspect.EARTH, 10),
                    " S ",
                    "IBI",
                    "ITI",
                    'B',
                    new ItemStack(Blocks.grass),
                    'I',
                    "ingotIron",
                    'S',
                    new ItemStack(ConfigItems.itemShard, 1, 6),
                    'T',
                    new ItemStack(ConfigBlocks.blockTube));

            registerArcaneRecipe(
                    "TERRAFORMFOCUS_COLDTAIGA",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 4),
                    new AspectList().add(Aspect.WATER, 10).add(Aspect.ORDER, 10),
                    " S ",
                    "IBI",
                    "ITI",
                    'B',
                    new ItemStack(Blocks.ice),
                    'I',
                    "ingotIron",
                    'S',
                    new ItemStack(ConfigItems.itemShard, 1, 6),
                    'T',
                    new ItemStack(ConfigBlocks.blockTube));

            registerArcaneRecipe(
                    "TERRAFORMFOCUS_DESERT",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 5),
                    new AspectList().add(Aspect.FIRE, 10).add(Aspect.EARTH, 10),
                    " S ",
                    "IBI",
                    "ITI",
                    'B',
                    new ItemStack(Blocks.sand),
                    'I',
                    "ingotIron",
                    'S',
                    new ItemStack(ConfigItems.itemShard, 1, 6),
                    'T',
                    new ItemStack(ConfigBlocks.blockTube));

            registerArcaneRecipe(
                    "TERRAFORMFOCUS_JUNGLE",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 6),
                    new AspectList().add(Aspect.WATER, 10).add(Aspect.EARTH, 10),
                    " S ",
                    "IBI",
                    "ITI",
                    'B',
                    new ItemStack(Blocks.log, 1, 3),
                    'I',
                    "ingotIron",
                    'S',
                    new ItemStack(ConfigItems.itemShard, 1, 6),
                    'T',
                    new ItemStack(ConfigBlocks.blockTube));

            registerArcaneRecipe(
                    "TERRAFORMFOCUS_HELL",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 7),
                    new AspectList().add(Aspect.FIRE, 10).add(Aspect.ENTROPY, 10),
                    " S ",
                    "IBI",
                    "ITI",
                    'B',
                    new ItemStack(Blocks.nether_brick),
                    'I',
                    "ingotIron",
                    'S',
                    new ItemStack(ConfigItems.itemShard, 1, 6),
                    'T',
                    new ItemStack(ConfigBlocks.blockTube));
            ThaumcraftApi.addWarpToItem(new ItemStack(WGContent.BlockMetalDevice, 1, 7), 1);

            registerArcaneRecipe(
                    "TERRAFORMFOCUS_MUSHROOM",
                    "",
                    new ItemStack(WGContent.BlockMetalDevice, 1, 9),
                    new AspectList().add(Aspect.EARTH, 10).add(Aspect.WATER, 10),
                    " S ",
                    "IBI",
                    "ITI",
                    'B',
                    new ItemStack(Blocks.mycelium),
                    'I',
                    "ingotIron",
                    'S',
                    new ItemStack(ConfigItems.itemShard, 1, 6),
                    'T',
                    new ItemStack(ConfigBlocks.blockTube));
        }
    }
}
