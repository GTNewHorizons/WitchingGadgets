package witchinggadgets.common.recipes.alchemic;

import static witchinggadgets.common.recipes.WG_alchemic_recipes.registerAlchemyRecipe;

import java.util.Arrays;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.utils.Utils;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.items.ItemClusters;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.registry.MetalFluidData;

public class WG_alchemic_clusters {

    public static void registerClusters() {

        AspectList alchemyAspects;

        for (int iOre = 0; iOre < witchinggadgets.common.WGContent.GT_Cluster.length; iOre++) {
            if (WGConfig.allowClusters) {
                ItemStack ingot = OreDictionary.getOres("ore" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                        .get(0);

                if (ingot == null) {
                    WitchingGadgets.logger.error(
                            witchinggadgets.common.WGContent.GT_Cluster[iOre] + " == null! This should not happen!");
                    continue;
                }

                try {
                    alchemyAspects = ThaumcraftApi.objectTags.get(Arrays.asList(ingot.getItem(), ingot.getItemDamage()))
                            .add(Aspect.ORDER, 1);
                } catch (NullPointerException e) {
                    WitchingGadgets.logger.error(
                            "Could not get the objectTags for" + witchinggadgets.common.WGContent.GT_Cluster[iOre]);
                    alchemyAspects = new AspectList().add(Aspect.METAL, 2).add(Aspect.ORDER, 1)
                            .add((Aspect) gregtech.api.enums.TC_Aspects.NEBRISUM.mAspect, 2);
                }

                if (alchemyAspects == null || alchemyAspects.equals(new AspectList()) || alchemyAspects.size() < 3)
                    alchemyAspects = new AspectList().add(Aspect.METAL, 2).add(Aspect.ORDER, 1)
                            .add((Aspect) gregtech.api.enums.TC_Aspects.NEBRISUM.mAspect, 2);
                else if (alchemyAspects.size() > 6)
                    alchemyAspects = new AspectList().add(Aspect.METAL, 2).add(Aspect.ORDER, 1).add(Aspect.GREED, 2);

                if (!OreDictionary.getOres("ore" + witchinggadgets.common.WGContent.GT_Cluster[iOre]).isEmpty())
                    registerAlchemyRecipe(
                            "METALLURGICPERFECTION_CLUSTERS",
                            "_" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            new ItemStack(WGContent.ItemCluster, 2, iOre),
                            "ore" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            alchemyAspects);

                if (!OreDictionary.getOres("oreNetherrack" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                        .isEmpty())
                    registerAlchemyRecipe(
                            "METALLURGICPERFECTION_CLUSTERS",
                            "_Netherrack_" + WGContent.GT_Cluster[iOre],
                            new ItemStack(WGContent.ItemCluster, 2, iOre),
                            "oreNetherrack" + WGContent.GT_Cluster[iOre],
                            alchemyAspects).hash += 1;

                if (!OreDictionary.getOres("oreEndstone" + witchinggadgets.common.WGContent.GT_Cluster[iOre]).isEmpty())
                    registerAlchemyRecipe(
                            "METALLURGICPERFECTION_CLUSTERS",
                            "_Endstone_" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            new ItemStack(WGContent.ItemCluster, 2, iOre),
                            "oreEndstone" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            alchemyAspects).hash += 2;

                if (!OreDictionary.getOres("oreBlackgranite" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                        .isEmpty())
                    registerAlchemyRecipe(
                            "METALLURGICPERFECTION_CLUSTERS",
                            "_Blackgranite_" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            new ItemStack(WGContent.ItemCluster, 2, iOre),
                            "oreBlackgranite" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            alchemyAspects).hash += 3;

                if (!OreDictionary.getOres("oreRedgranite" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                        .isEmpty())
                    registerAlchemyRecipe(
                            "METALLURGICPERFECTION_CLUSTERS",
                            "_Redgranite_" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            new ItemStack(WGContent.ItemCluster, 2, iOre),
                            "oreRedgranite" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            alchemyAspects).hash += 4;

                if (!OreDictionary.getOres("oreMarble" + witchinggadgets.common.WGContent.GT_Cluster[iOre]).isEmpty())
                    registerAlchemyRecipe(
                            "METALLURGICPERFECTION_CLUSTERS",
                            "_Marble_" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            new ItemStack(WGContent.ItemCluster, 2, iOre),
                            "oreMarble" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            alchemyAspects).hash += 5;

                if (!OreDictionary.getOres("oreBasalt" + witchinggadgets.common.WGContent.GT_Cluster[iOre]).isEmpty())
                    registerAlchemyRecipe(
                            "METALLURGICPERFECTION_CLUSTERS",
                            "_Basalt_" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            new ItemStack(WGContent.ItemCluster, 2, iOre),
                            "oreBasalt" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            alchemyAspects).hash += 6;

                if (!OreDictionary.getOres("rawOre" + witchinggadgets.common.WGContent.GT_Cluster[iOre]).isEmpty())
                    registerAlchemyRecipe(
                            "METALLURGICPERFECTION_CLUSTERS",
                            "_" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            new ItemStack(WGContent.ItemCluster, 2, iOre),
                            "rawOre" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            alchemyAspects);

                if (!OreDictionary.getOres("ore" + witchinggadgets.common.WGContent.GT_Cluster[iOre]).isEmpty()
                        || !OreDictionary.getOres("oreNetherrack" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                                .isEmpty()
                        || !OreDictionary.getOres("oreEndstone" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                                .isEmpty()
                        || !OreDictionary.getOres("oreBlackgranite" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                                .isEmpty()
                        || !OreDictionary.getOres("oreRedgranite" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                                .isEmpty()
                        || !OreDictionary.getOres("oreMarble" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                                .isEmpty()
                        || !OreDictionary.getOres("oreBasalt" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                                .isEmpty()
                        || !OreDictionary.getOres("rawOre" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                                .isEmpty())

                    setupCluster(witchinggadgets.common.WGContent.GT_Cluster[iOre]);
            }
            if (WGConfig.allowTransmutations) {
                boolean bb = !OreDictionary.getOres("nugget" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                        .isEmpty()
                        && !OreDictionary.getOres("ingot" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                                .isEmpty();
                if (bb) {
                    ItemStack ingot = OreDictionary.getOres("ingot" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                            .get(0);

                    if (ingot == null) {
                        WitchingGadgets.logger.error(
                                witchinggadgets.common.WGContent.GT_Cluster[iOre]
                                        + " == null! This should not happen!");
                        continue;
                    }
                    try {
                        alchemyAspects = ThaumcraftApi.objectTags
                                .get(Arrays.asList(ingot.getItem(), ingot.getItemDamage())).add(Aspect.EXCHANGE, 1);
                    } catch (NullPointerException e) {
                        WitchingGadgets.logger.error(
                                "Could not get the objectTags for" + witchinggadgets.common.WGContent.GT_Cluster[iOre]);
                        alchemyAspects = new AspectList().add(Aspect.METAL, 2).add(Aspect.ORDER, 1)
                                .add((Aspect) gregtech.api.enums.TC_Aspects.NEBRISUM.mAspect, 2);
                    }

                    if (alchemyAspects == null || alchemyAspects.equals(new AspectList()) || alchemyAspects.size() < 3)
                        alchemyAspects = new AspectList().add(Aspect.METAL, 2).add(Aspect.ORDER, 1)
                                .add((Aspect) gregtech.api.enums.TC_Aspects.NEBRISUM.mAspect, 2);
                    alchemyAspects.remove(Aspect.METAL);
                    alchemyAspects.add(Aspect.METAL, 2);
                    alchemyAspects.aspects.entrySet()
                            .removeIf(e -> e.getKey() == null || e.getValue() == null || e.getValue() <= 0);
                    int f = 0;
                    ItemStack rawnuggets = OreDictionary
                            .getOres("nugget" + witchinggadgets.common.WGContent.GT_Cluster[iOre]).get(f).copy();
                    if (rawnuggets.getDisplayName().contains("Oreberry")) ++f;
                    rawnuggets = OreDictionary.getOres("nugget" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                            .get(f).copy();
                    ItemStack nuggets = Utilities.copyStackWithSize(rawnuggets, 3);
                    registerAlchemyRecipe(
                            "METALLURGICPERFECTION_TRANSMUTATION",
                            "_" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            nuggets,
                            "nugget" + witchinggadgets.common.WGContent.GT_Cluster[iOre],
                            alchemyAspects);
                }
            }
        }
    }

    public static void setupCluster(String name) {
        String fluid = MetalFluidData.getOreFluidName(name);
        int fluidTemp = MetalFluidData.getOreFluidTemp(name);
        fluidTemp = fluidTemp > 0 ? fluidTemp : 550;

        String ore = "ore" + name;
        String cluster = "cluster" + name;
        String ingot = "ingot" + name;
        String nugget = "nugget" + name;
        ItemStack clusterStack = ItemClusters.getCluster(name);

        if (!OreDictionary.getOres(nugget).isEmpty()) {
            if (!OreDictionary.getOres(ore).isEmpty())
                ThaumcraftApi.addSmeltingBonus(ore, OreDictionary.getOres(nugget).get(0));
            if (!OreDictionary.getOres(cluster).isEmpty())
                ThaumcraftApi.addSmeltingBonus(cluster, OreDictionary.getOres(nugget).get(0));
        }

        if (!OreDictionary.getOres(cluster).isEmpty()) {
            if (!OreDictionary.getOres(ingot).isEmpty()) {
                ItemStack ingots = OreDictionary.getOres(ingot).get(0);
                if (clusterStack != null) {

                    if (!OreDictionary.getOres(ore).isEmpty())
                        Utils.addSpecialMiningResult(OreDictionary.getOres(ore).get(0), clusterStack, 1f);
                    if (WGContent.ClusterEBF.get(name) == null || !WGContent.ClusterEBF.get(name)) FurnaceRecipes
                            .smelting().func_151394_a(clusterStack, Utilities.copyStackWithSize(ingots, 2), 1.0F);
                }
            }
            if (WGModCompat.loaded_TCon && WGConfig.smelteryResultForClusters > 0
                    && FluidRegistry.getFluid(fluid) != null)
                WGModCompat.addTConSmelteryRecipe(
                        cluster,
                        "block" + name,
                        fluidTemp,
                        fluid,
                        WGConfig.smelteryResultForClusters);
        }
    }

}
