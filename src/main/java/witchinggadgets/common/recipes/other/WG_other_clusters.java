package witchinggadgets.common.recipes.other;

import static gregtech.api.recipe.RecipeMaps.centrifugeRecipes;
import static gregtech.api.recipe.RecipeMaps.fluidExtractionRecipes;
import static gregtech.api.recipe.RecipeMaps.maceratorRecipes;
import static gregtech.api.recipe.RecipeMaps.sifterRecipes;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;
import static witchinggadgets.common.recipes.WG_other_recipes.addBlastTrippling;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.Loader;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;

public class WG_other_clusters {

    public static void registerClusterRecipes() {
        if (Loader.isModLoaded("gregtech") && !Loader.isModLoaded("gregapi")) {
            for (String name : OreDictionary.getOreNames()) {
                if (name.startsWith("cluster")) {
                    String aMaterial = name.substring("cluster".length());
                    ItemStack[] outputs;
                    if (!OreDictionary.getOres(name).isEmpty()
                            && !OreDictionary.getOres("dustTiny" + aMaterial).isEmpty())
                        if (WGContent.ClusterEBF.get(aMaterial) == null || !WGContent.ClusterEBF.get(aMaterial)) {
                            addBlastTrippling(aMaterial);
                            if (!OreDictionary.getOres(name).isEmpty()
                                    && !OreDictionary.getOres("dustTiny" + aMaterial).isEmpty()) {
                                switch (aMaterial) {
                                    // x2
                                    case "Salt":
                                    case "RockSalt":
                                    case "CassiteriteSand":
                                    case "Cassiterite":
                                    case "CertusQuartz":
                                    case "Amber":
                                    case "NetherQuartz":
                                    case "Tungstate":
                                    case "Scheelite": {
                                        outputs = new ItemStack[] {
                                                OreDictionary.getOres("dust" + aMaterial).get(0).copy().splitStack(4),
                                                OreDictionary.getOres("dustTiny" + aMaterial).get(0).copy()
                                                        .splitStack(8) };
                                        break;
                                    }
                                    // x3
                                    case "Phosphorus": {
                                        outputs = new ItemStack[] {
                                                OreDictionary.getOres("dust" + aMaterial).get(0).copy().splitStack(7),
                                                OreDictionary.getOres("dustTiny" + aMaterial).get(0).copy()
                                                        .splitStack(3) };
                                        break;
                                    }
                                    // x4
                                    case "Saltpeter":
                                    case "Apatite": {
                                        outputs = new ItemStack[] {
                                                OreDictionary.getOres("dust" + aMaterial).get(0).copy().splitStack(9),
                                                OreDictionary.getOres("dustTiny" + aMaterial).get(0).copy()
                                                        .splitStack(7) };
                                        break;
                                    }
                                    // x5
                                    case "Redstone":
                                    case "Electrotine":
                                    case "Cryolite": {
                                        outputs = new ItemStack[] {
                                                OreDictionary.getOres("dust" + aMaterial).get(0).copy().splitStack(12),
                                                OreDictionary.getOres("dustTiny" + aMaterial).get(0).copy()
                                                        .splitStack(2) };
                                        break;
                                    }
                                    // x6
                                    case "Lapis":
                                    case "Sodalite":
                                    case "Lazurite": {
                                        outputs = new ItemStack[] {
                                                OreDictionary.getOres("dust" + aMaterial).get(0).copy().splitStack(14),
                                                OreDictionary.getOres("dustTiny" + aMaterial).get(0).copy()
                                                        .splitStack(6) };
                                        break;
                                    }
                                    // x8
                                    case "Monazite": {
                                        outputs = new ItemStack[] {
                                                OreDictionary.getOres("dust" + aMaterial).get(0).copy().splitStack(19),
                                                OreDictionary.getOres("dustTiny" + aMaterial).get(0).copy()
                                                        .splitStack(5) };
                                        break;
                                    }
                                    default: {
                                        outputs = new ItemStack[] { OreDictionary.getOres("dustTiny" + aMaterial).get(0)
                                                .copy().splitStack(22) };
                                        break;
                                    }
                                }

                                if (!aMaterial.equalsIgnoreCase("Oilsands")) {
                                    GT_Values.RA.stdBuilder().itemInputs(OreDictionary.getOres(name).get(0).copy())
                                            .itemOutputs(outputs).eut(30).duration(30 * SECONDS)
                                            .addTo(maceratorRecipes);
                                }

                                if (WGContent.ClusterSmeltable.get(aMaterial) != null
                                        || aMaterial.equalsIgnoreCase("Oilsands")) {
                                    if (aMaterial.equals("Redstone") || aMaterial.equals("Electrotine")) {
                                        GT_Values.RA.stdBuilder().itemInputs(OreDictionary.getOres(name).get(0).copy())
                                                .fluidOutputs(
                                                        new FluidStack(WGContent.ClusterSmeltable.get(aMaterial), 1720))
                                                .eut(120).duration(60 * SECONDS).addTo(fluidExtractionRecipes);

                                    } else if (!aMaterial.equalsIgnoreCase("Oilsands")) {
                                        GT_Values.RA.stdBuilder().itemInputs(OreDictionary.getOres(name).get(0).copy())
                                                .fluidOutputs(
                                                        new FluidStack(WGContent.ClusterSmeltable.get(aMaterial), 344))
                                                .eut(120).duration(60 * SECONDS).addTo(fluidExtractionRecipes);

                                    } else {
                                        GT_Values.RA.stdBuilder().itemInputs(OreDictionary.getOres(name).get(0).copy())
                                                .fluidOutputs(Materials.OilHeavy.getFluid(4000L)).eut(120)
                                                .duration(60 * SECONDS).addTo(centrifugeRecipes);
                                    }
                                }
                            }
                        } else if (!OreDictionary.getOres(name).isEmpty()
                                && !OreDictionary.getOres("dustTiny" + aMaterial).isEmpty()) {
                                    switch (aMaterial) {
                                        // x2
                                        case "Salt":
                                        case "RockSalt":
                                        case "CassiteriteSand":
                                        case "Cassiterite":
                                        case "CertusQuartz":
                                        case "Amber":
                                        case "NetherQuartz":
                                        case "Tungstate":
                                        case "Scheelite": {
                                            outputs = new ItemStack[] {
                                                    OreDictionary.getOres("dust" + aMaterial).get(0).copy()
                                                            .splitStack(4),
                                                    OreDictionary.getOres("dustTiny" + aMaterial).get(0).copy()
                                                            .splitStack(8) };
                                            break;
                                        }
                                        // x3
                                        case "Phosphorus": {
                                            outputs = new ItemStack[] {
                                                    OreDictionary.getOres("dust" + aMaterial).get(0).copy()
                                                            .splitStack(7),
                                                    OreDictionary.getOres("dustTiny" + aMaterial).get(0).copy()
                                                            .splitStack(3) };
                                            break;
                                        }
                                        // x4
                                        case "Saltpeter":
                                        case "Apatite": {
                                            outputs = new ItemStack[] {
                                                    OreDictionary.getOres("dust" + aMaterial).get(0).copy()
                                                            .splitStack(9),
                                                    OreDictionary.getOres("dustTiny" + aMaterial).get(0).copy()
                                                            .splitStack(7) };
                                            break;
                                        }
                                        // x5
                                        case "Redstone":
                                        case "Electrotine": {
                                            outputs = new ItemStack[] {
                                                    OreDictionary.getOres("dust" + aMaterial).get(0).copy()
                                                            .splitStack(12),
                                                    OreDictionary.getOres("dustTiny" + aMaterial).get(0).copy()
                                                            .splitStack(2) };
                                            break;
                                        }
                                        // x6
                                        case "Lapis":
                                        case "Sodalite":
                                        case "Lazurite": {
                                            outputs = new ItemStack[] {
                                                    OreDictionary.getOres("dust" + aMaterial).get(0).copy()
                                                            .splitStack(14),
                                                    OreDictionary.getOres("dustTiny" + aMaterial).get(0).copy()
                                                            .splitStack(6) };
                                            break;
                                        }
                                        // x8
                                        case "Monazite": {
                                            outputs = new ItemStack[] {
                                                    OreDictionary.getOres("dust" + aMaterial).get(0).copy()
                                                            .splitStack(19),
                                                    OreDictionary.getOres("dustTiny" + aMaterial).get(0).copy()
                                                            .splitStack(5) };
                                            break;
                                        }
                                        default: {
                                            outputs = new ItemStack[] { OreDictionary.getOres("dustTiny" + aMaterial)
                                                    .get(0).copy().splitStack(22) };
                                            break;
                                        }
                                    }
                                    GT_Values.RA.stdBuilder().itemInputs(OreDictionary.getOres(name).get(0).copy())
                                            .itemOutputs(outputs).eut(30).duration(30 * SECONDS)
                                            .addTo(maceratorRecipes);
                                }

                    if (!OreDictionary.getOres(name).isEmpty() && !OreDictionary.getOres("gem" + aMaterial).isEmpty()) {

                        switch (aMaterial) {
                            case "Tanzanite":
                            case "Sapphire":
                            case "Olivine":
                            case "GreenSapphire":
                            case "Opal":
                            case "Amethyst":
                            case "Emerald":
                            case "Ruby":
                            case "Diamond":
                            case "FoolsRuby":
                            case "BlueTopaz":
                            case "GarnetRed":
                            case "Topaz":
                            case "Jasper":
                            case "GarnetYellow": {

                                ItemStack tGem = GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 1L);

                                GT_Values.RA.stdBuilder().itemInputs(OreDictionary.getOres(name).get(0).copy())
                                        .itemOutputs(
                                                GT_OreDictUnificator.get(OrePrefixes.gemExquisite, aMaterial, tGem, 1L),
                                                GT_OreDictUnificator.get(OrePrefixes.gemFlawless, aMaterial, tGem, 1L),
                                                tGem,
                                                GT_OreDictUnificator.get(OrePrefixes.gemFlawed, aMaterial, tGem, 1L),
                                                GT_OreDictUnificator.get(OrePrefixes.gemChipped, aMaterial, tGem, 1L),
                                                GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, tGem, 1L))
                                        .outputChances(600, 2400, 9000, 2400, 5600, 7000).duration(80 * SECONDS).eut(30)
                                        .addTo(sifterRecipes);
                                break;
                            }
                            case "CertusQuartz":
                            case "NetherQuartz": {

                                ItemStack tGem = GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 2L);

                                GT_Values.RA.stdBuilder().itemInputs(OreDictionary.getOres(name).get(0).copy())
                                        .itemOutputs(
                                                GT_OreDictUnificator.get(OrePrefixes.gemExquisite, aMaterial, tGem, 2L),
                                                GT_OreDictUnificator.get(OrePrefixes.gemFlawless, aMaterial, tGem, 2L),
                                                tGem,
                                                GT_OreDictUnificator.get(OrePrefixes.gemFlawed, aMaterial, tGem, 2L),
                                                GT_OreDictUnificator.get(OrePrefixes.gemChipped, aMaterial, tGem, 2L),
                                                GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, tGem, 2L))
                                        .outputChances(200, 400, 3000, 4000, 8000, 10000).duration(160 * SECONDS)
                                        .eut(30).addTo(sifterRecipes);
                                break;
                            }
                            case "Apatite": {

                                ItemStack tGem = GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 4L);

                                GT_Values.RA.stdBuilder().itemInputs(OreDictionary.getOres(name).get(0).copy())
                                        .itemOutputs(
                                                GT_OreDictUnificator.get(OrePrefixes.gemExquisite, aMaterial, tGem, 4L),
                                                GT_OreDictUnificator.get(OrePrefixes.gemFlawless, aMaterial, tGem, 4L),
                                                tGem,
                                                GT_OreDictUnificator.get(OrePrefixes.gemFlawed, aMaterial, tGem, 4L),
                                                GT_OreDictUnificator.get(OrePrefixes.gemChipped, aMaterial, tGem, 4L),
                                                GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, tGem, 4L))
                                        .outputChances(200, 400, 3000, 4000, 8000, 10000).duration(32 * SECONDS).eut(30)
                                        .addTo(sifterRecipes);
                                break;
                            }
                            case "Amber": {

                                ItemStack tGem = GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 2L);

                                GT_Values.RA.stdBuilder().itemInputs(OreDictionary.getOres(name).get(0).copy())
                                        .itemOutputs(
                                                GT_OreDictUnificator.get(OrePrefixes.gemExquisite, aMaterial, tGem, 2L),
                                                GT_OreDictUnificator.get(OrePrefixes.gemFlawless, aMaterial, tGem, 2L),
                                                tGem,
                                                GT_OreDictUnificator.get(OrePrefixes.gemFlawed, aMaterial, tGem, 2L),
                                                GT_OreDictUnificator.get(OrePrefixes.gemChipped, aMaterial, tGem, 2L),
                                                GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, tGem, 2L))
                                        .outputChances(600, 2400, 9000, 2400, 5600, 7000).duration(160 * SECONDS)
                                        .eut(30).addTo(sifterRecipes);
                                break;
                            }
                            case "Lapis":
                            case "Sodalite":
                            case "Lazurite": {

                                ItemStack tGem = GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 6L);

                                GT_Values.RA.stdBuilder().itemInputs(OreDictionary.getOres(name).get(0).copy())
                                        .itemOutputs(
                                                GT_OreDictUnificator.get(OrePrefixes.gemExquisite, aMaterial, tGem, 6L),
                                                GT_OreDictUnificator.get(OrePrefixes.gemFlawless, aMaterial, tGem, 6L),
                                                tGem,
                                                GT_OreDictUnificator.get(OrePrefixes.gemFlawed, aMaterial, tGem, 6L),
                                                GT_OreDictUnificator.get(OrePrefixes.gemChipped, aMaterial, tGem, 6L),
                                                GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, tGem, 6L))
                                        .outputChances(200, 400, 3000, 4000, 8000, 10000).duration(480 * SECONDS)
                                        .eut(30).addTo(sifterRecipes);
                                break;
                            }
                            case "Monazite": {

                                ItemStack tGem = GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 8L);

                                GT_Values.RA.stdBuilder().itemInputs(OreDictionary.getOres(name).get(0).copy())
                                        .itemOutputs(
                                                GT_OreDictUnificator.get(OrePrefixes.gemExquisite, aMaterial, tGem, 8L),
                                                GT_OreDictUnificator.get(OrePrefixes.gemFlawless, aMaterial, tGem, 8L),
                                                tGem,
                                                GT_OreDictUnificator.get(OrePrefixes.gemFlawed, aMaterial, tGem, 8L),
                                                GT_OreDictUnificator.get(OrePrefixes.gemChipped, aMaterial, tGem, 8L),
                                                GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, tGem, 8L))
                                        .outputChances(200, 400, 3000, 4000, 8000, 10000).duration(640 * SECONDS)
                                        .eut(30).addTo(sifterRecipes);
                                break;
                            }
                            case "Coal": {
                                GT_Values.RA.stdBuilder().itemInputs(OreDictionary.getOres(name).get(0).copy())
                                        .itemOutputs(
                                                new ItemStack(Items.coal, 2, 0),
                                                new ItemStack(Items.coal, 2, 0),
                                                new ItemStack(Items.coal, 2, 0),
                                                new ItemStack(Items.coal, 2, 0),
                                                new ItemStack(Items.coal, 2, 0),
                                                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 2L))
                                        .outputChances(10000, 9000, 8000, 7000, 6000, 5000).duration(60 * SECONDS)
                                        .eut(30).addTo(sifterRecipes);
                                break;
                            }
                            default: {

                                ItemStack tGem = GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 1L);

                                GT_Values.RA.stdBuilder().itemInputs(OreDictionary.getOres(name).get(0).copy())
                                        .itemOutputs(
                                                GT_OreDictUnificator.get(OrePrefixes.gemExquisite, aMaterial, tGem, 1L),
                                                GT_OreDictUnificator.get(OrePrefixes.gemFlawless, aMaterial, tGem, 1L),
                                                tGem,
                                                GT_OreDictUnificator.get(OrePrefixes.gemFlawed, aMaterial, tGem, 1L),
                                                GT_OreDictUnificator.get(OrePrefixes.gemChipped, aMaterial, tGem, 1L),
                                                GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, tGem, 1L))
                                        .outputChances(200, 400, 3000, 4000, 8000, 10000).duration(80 * SECONDS).eut(30)
                                        .addTo(sifterRecipes);
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            for (String name : OreDictionary.getOreNames()) if (name.startsWith("cluster")) {
                addBlastTrippling(name.substring("cluster".length()));
            }
        }

        if (WGModCompat.loaded_TCon) {
            if (WGConfig.smelteryResultForClusters > 0) {
                WGModCompat.addTConSmelteryRecipe(
                        "clusterIron",
                        "blockIron",
                        600,
                        "iron.molten",
                        WGConfig.smelteryResultForClusters);
                WGModCompat.addTConSmelteryRecipe(
                        "clusterGold",
                        "blockGold",
                        400,
                        "gold.molten",
                        WGConfig.smelteryResultForClusters);
                WGModCompat.addTConSmelteryRecipe(
                        "clusterCopper",
                        "blockCopper",
                        350,
                        "copper.molten",
                        WGConfig.smelteryResultForClusters);
                WGModCompat.addTConSmelteryRecipe(
                        "clusterTin",
                        "blockTin",
                        400,
                        "tin.molten",
                        WGConfig.smelteryResultForClusters);
                WGModCompat.addTConSmelteryRecipe(
                        "clusterSilver",
                        "blockSilver",
                        550,
                        "silver.molten",
                        WGConfig.smelteryResultForClusters);
            }
            WGModCompat.addTConDryingRecipe(
                    new ItemStack(ConfigItems.itemZombieBrain),
                    20 * 6 * 5,
                    new ItemStack(WGContent.ItemMagicFoodstuffs, 1, 2));
        }
    }
}
