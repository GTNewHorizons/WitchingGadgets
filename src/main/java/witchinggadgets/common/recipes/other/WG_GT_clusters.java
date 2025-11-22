package witchinggadgets.common.recipes.other;

import static gregtech.api.recipe.RecipeMaps.centrifugeRecipes;
import static gregtech.api.recipe.RecipeMaps.fluidExtractionRecipes;
import static gregtech.api.recipe.RecipeMaps.maceratorRecipes;
import static gregtech.api.recipe.RecipeMaps.sifterRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static witchinggadgets.common.recipes.WG_other_recipes.addBlastTripling;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.SubTag;
import gregtech.api.enums.TierEU;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTOreDictUnificator;
import witchinggadgets.common.recipes.alchemic.WG_alchemic_clusters;
import witchinggadgets.common.recipes.alchemic.WG_alchemic_clusters.ClusterInfo;
import witchinggadgets.common.util.Utilities;

public class WG_GT_clusters {

    public static void registerClusterRecipesGT() {
        for (ClusterInfo clusterInfo : WG_alchemic_clusters.CLUSTER_INFO.values()) {
            ItemStack cluster = clusterInfo.getPart(OrePrefixes.cluster, 1);

            if (cluster == null) continue;

            Materials material = clusterInfo.getGT5uMaterial();
            if (!clusterInfo.ebf()) {
                addBlastTripling(clusterInfo.matName());
            }

            int oreMultiplier = material == null ? 1 : material.mOreMultiplier;

            if (material == Materials.Oilsands) {
                GTValues.RA.stdBuilder().itemInputs(Utilities.copyStackWithSize(cluster, 1))
                        .fluidOutputs(Materials.OilHeavy.getFluid(4000L)).eut(TierEU.RECIPE_MV).duration(60 * SECONDS)
                        .addTo(centrifugeRecipes);
            } else if (material != null && material.contains(SubTag.ICE_ORE)) {
                GTValues.RA.stdBuilder().itemInputs(cluster.copy())
                        .fluidOutputs(material.getGas(1000L * material.mOreMultiplier)).duration(5 * SECONDS)
                        .eut(TierEU.RECIPE_MV).addTo(RecipeMaps.fluidExtractionRecipes);
            } else {
                ItemStack dusts = clusterInfo.getPart(OrePrefixes.dust, 1);
                ItemStack tinyDusts = clusterInfo.getPart(OrePrefixes.dustTiny, 1);
                ItemStack gems = clusterInfo.getPart(OrePrefixes.gem, 1);

                if (dusts != null && tinyDusts != null) {
                    int tinyDustCount = oreMultiplier * 22;

                    int dustCount = tinyDustCount / 9;
                    tinyDustCount -= dustCount * 9;

                    GTValues.RA.stdBuilder().itemInputs(cluster.copy())
                            .itemOutputs(
                                    Utilities.copyStackWithSize(dusts, dustCount),
                                    Utilities.copyStackWithSize(tinyDusts, tinyDustCount))
                            .eut(TierEU.RECIPE_LV).duration(30 * SECONDS).addTo(maceratorRecipes);
                }

                if (!clusterInfo.ebf() && clusterInfo.liquid() != null) {
                    int moltenAmount = oreMultiplier * 22 * (144 / 9);

                    GTValues.RA.stdBuilder().itemInputs(cluster.copy())
                            .fluidOutputs(Utilities.copyStackWithAmount(clusterInfo.liquid(), moltenAmount))
                            .eut(TierEU.RECIPE_MV).duration(60 * SECONDS).addTo(fluidExtractionRecipes);
                }

                if (gems != null) {
                    switch (clusterInfo.matName()) {
                        case "Tanzanite", "Sapphire", "Olivine", "GreenSapphire", "Opal", "Amethyst", "Emerald", "Ruby", "Diamond", "FoolsRuby", "BlueTopaz", "GarnetRed", "Topaz", "Jasper", "GarnetYellow" -> {
                            GTValues.RA.stdBuilder().itemInputs(cluster.copy())
                                    .itemOutputs(
                                            GTOreDictUnificator.get(OrePrefixes.gemExquisite, material, gems, 1L),
                                            GTOreDictUnificator.get(OrePrefixes.gemFlawless, material, gems, 1L),
                                            gems,
                                            GTOreDictUnificator.get(OrePrefixes.gemFlawed, material, gems, 1L),
                                            GTOreDictUnificator.get(OrePrefixes.gemChipped, material, gems, 1L),
                                            GTOreDictUnificator.get(OrePrefixes.dust, material, gems, 1L))
                                    .outputChances(600, 2400, 9000, 2800, 5600, 7000).duration(80 * SECONDS)
                                    .eut(TierEU.RECIPE_LV).addTo(sifterRecipes);
                        }
                        case "CertusQuartz", "NetherQuartz" -> {
                            GTValues.RA.stdBuilder().itemInputs(cluster.copy())
                                    .itemOutputs(
                                            GTOreDictUnificator.get(OrePrefixes.gemExquisite, material, gems, 2L),
                                            GTOreDictUnificator.get(OrePrefixes.gemFlawless, material, gems, 2L),
                                            gems,
                                            GTOreDictUnificator.get(OrePrefixes.gemFlawed, material, gems, 2L),
                                            GTOreDictUnificator.get(OrePrefixes.gemChipped, material, gems, 2L),
                                            GTOreDictUnificator.get(OrePrefixes.dust, material, gems, 2L))
                                    .outputChances(200, 400, 3000, 4000, 8000, 10000).duration(160 * SECONDS)
                                    .eut(TierEU.RECIPE_LV).addTo(sifterRecipes);
                        }
                        case "Apatite" -> {
                            GTValues.RA.stdBuilder().itemInputs(cluster.copy())
                                    .itemOutputs(
                                            GTOreDictUnificator.get(OrePrefixes.gemExquisite, material, gems, 4L),
                                            GTOreDictUnificator.get(OrePrefixes.gemFlawless, material, gems, 4L),
                                            gems,
                                            GTOreDictUnificator.get(OrePrefixes.gemFlawed, material, gems, 4L),
                                            GTOreDictUnificator.get(OrePrefixes.gemChipped, material, gems, 4L),
                                            GTOreDictUnificator.get(OrePrefixes.dust, material, gems, 4L))
                                    .outputChances(200, 400, 3000, 4000, 8000, 10000).duration(32 * SECONDS)
                                    .eut(TierEU.RECIPE_LV).addTo(sifterRecipes);
                        }
                        case "Amber" -> {
                            GTValues.RA.stdBuilder().itemInputs(cluster.copy())
                                    .itemOutputs(
                                            GTOreDictUnificator.get(OrePrefixes.gemExquisite, material, gems, 2L),
                                            GTOreDictUnificator.get(OrePrefixes.gemFlawless, material, gems, 2L),
                                            gems,
                                            GTOreDictUnificator.get(OrePrefixes.gemFlawed, material, gems, 2L),
                                            GTOreDictUnificator.get(OrePrefixes.gemChipped, material, gems, 2L),
                                            GTOreDictUnificator.get(OrePrefixes.dust, material, gems, 2L))
                                    .outputChances(600, 2400, 9000, 2800, 5600, 7000).duration(160 * SECONDS)
                                    .eut(TierEU.RECIPE_LV).addTo(sifterRecipes);
                        }
                        case "Lapis", "Sodalite", "Lazurite" -> {
                            GTValues.RA.stdBuilder().itemInputs(cluster.copy())
                                    .itemOutputs(
                                            GTOreDictUnificator.get(OrePrefixes.gemExquisite, material, gems, 6L),
                                            GTOreDictUnificator.get(OrePrefixes.gemFlawless, material, gems, 6L),
                                            gems,
                                            GTOreDictUnificator.get(OrePrefixes.gemFlawed, material, gems, 6L),
                                            GTOreDictUnificator.get(OrePrefixes.gemChipped, material, gems, 6L),
                                            GTOreDictUnificator.get(OrePrefixes.dust, material, gems, 6L))
                                    .outputChances(200, 400, 3000, 4000, 8000, 10000).duration(480 * SECONDS)
                                    .eut(TierEU.RECIPE_LV).addTo(sifterRecipes);
                        }
                        case "Monazite" -> {
                            GTValues.RA.stdBuilder().itemInputs(cluster.copy())
                                    .itemOutputs(
                                            GTOreDictUnificator.get(OrePrefixes.gemExquisite, material, gems, 8L),
                                            GTOreDictUnificator.get(OrePrefixes.gemFlawless, material, gems, 8L),
                                            gems,
                                            GTOreDictUnificator.get(OrePrefixes.gemFlawed, material, gems, 8L),
                                            GTOreDictUnificator.get(OrePrefixes.gemChipped, material, gems, 8L),
                                            GTOreDictUnificator.get(OrePrefixes.dust, material, gems, 8L))
                                    .outputChances(200, 400, 3000, 4000, 8000, 10000).duration(640 * SECONDS)
                                    .eut(TierEU.RECIPE_LV).addTo(sifterRecipes);
                        }
                        case "Coal" -> {
                            GTValues.RA.stdBuilder().itemInputs(cluster.copy())
                                    .itemOutputs(
                                            new ItemStack(Items.coal, 2, 0),
                                            new ItemStack(Items.coal, 2, 0),
                                            new ItemStack(Items.coal, 2, 0),
                                            new ItemStack(Items.coal, 2, 0),
                                            new ItemStack(Items.coal, 2, 0),
                                            GTOreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 2L))
                                    .outputChances(10000, 9000, 8000, 7000, 6000, 5000).duration(60 * SECONDS)
                                    .eut(TierEU.RECIPE_LV).addTo(sifterRecipes);
                        }
                        default -> {
                            GTValues.RA.stdBuilder().itemInputs(cluster.copy())
                                    .itemOutputs(
                                            GTOreDictUnificator.get(OrePrefixes.gemExquisite, material, gems, 1L),
                                            GTOreDictUnificator.get(OrePrefixes.gemFlawless, material, gems, 1L),
                                            gems,
                                            GTOreDictUnificator.get(OrePrefixes.gemFlawed, material, gems, 1L),
                                            GTOreDictUnificator.get(OrePrefixes.gemChipped, material, gems, 1L),
                                            GTOreDictUnificator.get(OrePrefixes.dust, material, gems, 1L))
                                    .outputChances(200, 400, 3000, 4000, 8000, 10000).duration(80 * SECONDS)
                                    .eut(TierEU.RECIPE_LV).addTo(sifterRecipes);
                        }
                    }
                }
            }
        }
    }
}
