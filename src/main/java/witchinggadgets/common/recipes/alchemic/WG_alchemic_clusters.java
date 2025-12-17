package witchinggadgets.common.recipes.alchemic;

import static witchinggadgets.common.recipes.WG_alchemic_recipes.registerAlchemyRecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizons.postea.api.ItemStackReplacementManager;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import gregtech.GTMod;
import gregtech.api.GregTechAPI;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OreMixes;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.SubTag;
import gregtech.api.enums.TCAspects;
import gregtech.api.util.GTOreDictUnificator;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.common.lib.utils.Utils;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.items.ItemClusters;
import witchinggadgets.common.items.ItemClusters.MetaInfo;
import witchinggadgets.common.util.Utilities;

public class WG_alchemic_clusters {

    public static final List<CrucibleRecipe> CLUSTER_RECIPES = new ArrayList<>(), TRANSMUTE_RECIPES = new ArrayList<>();

    public static String[] subNames = {
            // TCon
            "Aluminum", "Cobalt", "Ardite",
            // ThermalFoundation
            "Nickel",
            // Factorization
            "FZDarkIron",
            // Metallurgy
            "Manganese", "Zinc", "Platinum", "Ignatius", "ShadowIron", "Lemurite", "Midasium", "Vyroxeres", "Ceruclase",
            "Alduorite", "Kalendrite", "Vulcanite", "Sanguinite", "Prometheum", "DeepIron", "Infuscolium", "Oureclase",
            "AstralSilver", "Carmot", "Mithril", "Rubracium", "Orichalcum", "Adamantine", "Atlarus", "Eximite",
            "Meutoite",
            // Gregtech
            "Beryllium", "Cobalt", "Iridium", "Molybdenum", "Naquadah", "Neodymium", "Nickel", "Palladium", "Platinum",
            "Thorium", "Uranium235", "Uranium238", "Zinc", "Casserite" };

    public enum ClusterOverlay {

        Overworld,
        Nether,
        End;

        public static ClusterOverlay fromRGB(int rgb) {
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;

            if (r > b && g > b) {
                return ClusterOverlay.End;
            } else if (r > g && r > b) {
                return ClusterOverlay.Nether;
            } else {
                return ClusterOverlay.Overworld;
            }
        }
    }

    @EqualsAndHashCode
    @ToString
    @AllArgsConstructor
    public static final class ClusterInfo {

        private final String matName;
        private final MetaInfo meta;
        private final boolean ebf;
        private final FluidStack liquid;
        private final int vibrant;
        private final ClusterOverlay clusterOverlay;

        @Optional.Method(modid = "gregtech_nh")
        public Materials getGT5uMaterial() {
            return Materials.getMaterialsMap().get(matName);
        }

        public ItemStack getPart(String prefix, int amount) {
            return Utilities.getOredict(prefix + matName, amount);
        }

        @Optional.Method(modid = "gregtech_nh")
        public ItemStack getPart(OrePrefixes prefix, int amount) {
            return GTOreDictUnificator.get(prefix, getGT5uMaterial(), amount);
        }

        public String matName() {
            return matName;
        }

        public MetaInfo meta() {
            return meta;
        }

        public boolean ebf() {
            return ebf;
        }

        public FluidStack liquid() {
            return liquid;
        }

        public int vibrant() {
            return vibrant;
        }

        public ClusterOverlay clusterOverlay() {
            return clusterOverlay;
        }
    }

    public static final Object2ObjectOpenHashMap<String, ClusterInfo> CLUSTER_INFO = new Object2ObjectOpenHashMap<>();
    public static final Int2ObjectOpenHashMap<ClusterInfo> CLUSTER_INFO_BY_META = new Int2ObjectOpenHashMap<>();

    public static void registerClusters() {
        if (Loader.isModLoaded("postea")) {
            registerPosteaTransformer();
        }

        if (WitchingGadgets.isGT5uLoaded) {
            loadGT5uClusters();
        } else {
            loadMiscClusters();
        }

        CLUSTER_INFO.forEach((name, clusterInfo) -> {
            CLUSTER_INFO_BY_META.put(clusterInfo.meta.getMeta(), clusterInfo);

            setupCluster(clusterInfo);
        });
    }

    @Optional.Method(modid = "gregtech_nh")
    private static boolean clusterBlacklist(Materials material) {
        if (material == Materials.Iron) return true;
        if (material == Materials.Copper) return true;
        if (material == Materials.Tin) return true;
        if (material == Materials.Silver) return true;
        if (material == Materials.Lead) return true;
        if (material == Materials.Cinnabar) return true;
        if (material == Materials.Gold) return true;
        if (material == Materials.AnyCopper) return true;
        if (material == Materials.AnyIron) return true;
        for (String name : WGConfig.triplingClusterList) {
            if (material.mName.equals(name)) return true;
        }

        return false;
    }

    private static void loadMiscClusters() {
        for (int iOre = 0; iOre < subNames.length; iOre++) {
            String subName = subNames[iOre];

            if (WGConfig.allowClusters) {
                if (hasItem("ore", subName) && hasItem("ingot", subName)) {
                    AspectList alchemyAspects = new AspectList().add(Aspect.METAL, 1).add(Aspect.ORDER, 1);

                    MetaInfo metaInfo = new MetaInfo(ItemClusters.Series.Misc, iOre);

                    CLUSTER_RECIPES.add(
                            registerAlchemyRecipe(
                                    "METALLURGICPERFECTION_CLUSTERS",
                                    "_" + subName,
                                    new ItemStack(WGContent.ItemCluster, 1, metaInfo.getMeta()),
                                    "ore" + subName,
                                    alchemyAspects));

                    ItemStack ingot = Utilities.getOredict("ingot" + subName, 1);

                    int rgb = 0;

                    ClusterOverlay clusterOverlay = ClusterOverlay.Overworld;

                    if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
                        rgb = ingot.getItem().getColorFromItemStack(ingot, 0);

                        clusterOverlay = ClusterOverlay.fromRGB(rgb);

                        rgb = ClientUtilities.getVibrantColourToInt(rgb);
                    }

                    ClusterInfo clusterInfo = new ClusterInfo(subName, metaInfo, false, null, rgb, clusterOverlay);
                    CLUSTER_INFO.put(subName, clusterInfo);

                    setupCluster(clusterInfo);
                }
            }

            if (WGConfig.allowTransmutations) {
                if (hasItem("nugget", subName) && hasItem("ingot", subName)) {
                    ItemStack ingot = Utilities.getOredict("ingot" + subName, 1);

                    AspectList alchemyAspects = ThaumcraftApi.objectTags
                            .get(Arrays.asList(ingot.getItem(), ingot.getItemDamage()));
                    if (alchemyAspects == null) alchemyAspects = new AspectList();
                    if (Arrays.asList(alchemyAspects.getAspects()).contains(Aspect.METAL)
                            && alchemyAspects.size() > 1) {
                        alchemyAspects.remove(Aspect.METAL);
                    }
                    alchemyAspects.add(Aspect.METAL, 2);

                    alchemyAspects.aspects.entrySet().removeIf(e -> e.getKey() == null || e.getValue() == null);

                    ItemStack nuggets = Utilities.getOredict("nugget" + subName, 3);

                    TRANSMUTE_RECIPES.add(
                            registerAlchemyRecipe(
                                    "METALLURGICPERFECTION_TRANSMUTATION",
                                    "_" + subName,
                                    nuggets,
                                    "nugget" + subName,
                                    alchemyAspects));
                }
            }
        }
    }

    private static boolean hasItem(String prefix, String matName) {
        return !OreDictionary.getOres(prefix + matName).isEmpty();
    }

    @Optional.Method(modid = "gregtech_nh")
    private static void loadGT5uClusters() {
        HashSet<Materials> oresInVeins = new HashSet<>();

        // Only generate clusters for ores that are actually used in veins
        // This only makes them show up in NEI and adds the recipes for them
        for (OreMixes oreMix : OreMixes.values()) {
            if (oreMix.oreMixBuilder.primary instanceof Materials gtMat) {
                oresInVeins.add(gtMat);
            }

            if (oreMix.oreMixBuilder.between instanceof Materials gtMat) {
                oresInVeins.add(gtMat);
            }

            if (oreMix.oreMixBuilder.secondary instanceof Materials gtMat) {
                oresInVeins.add(gtMat);
            }

            if (oreMix.oreMixBuilder.sporadic instanceof Materials gtMat) {
                oresInVeins.add(gtMat);
            }
        }

        List<OrePrefixes> notRich = new ArrayList<>();
        List<OrePrefixes> rich = new ArrayList<>();

        notRich.add(OrePrefixes.ore);
        notRich.add(OrePrefixes.rawOre);

        if (GTMod.proxy.mNetherOreYieldMultiplier) {
            rich.add(OrePrefixes.oreNetherrack);
        } else {
            notRich.add(OrePrefixes.oreNetherrack);
        }

        if (GTMod.proxy.mEndOreYieldMultiplier) {
            rich.add(OrePrefixes.oreEndstone);
        } else {
            notRich.add(OrePrefixes.oreEndstone);
        }

        for (Materials material : GregTechAPI.sGeneratedMaterials) {
            if (material == null) continue;

            final AspectList baseAspects = new AspectList();

            material.mAspects.forEach(stack -> baseAspects.add(stack.mAspect.getAspect(), (int) stack.mAmount));

            if (WGConfig.allowClusters && !clusterBlacklist(material)
                    && oresInVeins.contains(material)
                    && hasItem("ore", material.mName)) {
                int rgb = ((material.getRGBA()[0] & 0xff) << 16) | ((material.getRGBA()[1] & 0xff) << 8)
                        | (material.getRGBA()[2] & 0xff);

                ClusterOverlay clusterOverlay = ClusterOverlay.fromRGB(rgb);

                rgb = ClientUtilities.getVibrantColourToInt(rgb);

                FluidStack liquid = null;

                if (!material.mBlastFurnaceRequired) {
                    liquid = material.getMolten(288);

                    if (liquid == null) {
                        liquid = material.getFluid(288);
                    }
                }

                MetaInfo metaInfo = new MetaInfo(ItemClusters.Series.GT5u, material.mMetaItemSubID);

                ClusterInfo clusterInfo = new ClusterInfo(
                        material.mName,
                        metaInfo,
                        material.mBlastFurnaceRequired,
                        liquid,
                        rgb,
                        clusterOverlay);

                CLUSTER_INFO.put(material.mName, clusterInfo);

                AspectList alchemyAspects = baseAspects.copy().add(Aspect.ORDER, 1);

                if (alchemyAspects.size() < 3) {
                    // Default aspect for unset (Cryolite, Oilsands, etc) is now the
                    // same as if it had too many aspects (Lucrum instead of
                    // Nebrisum). In the future for stuff like Callisto Ice , etc
                    // that is not aspected, either aspects can be added to the
                    // material or those special cases can be manually set here.
                    alchemyAspects = new AspectList().add(Aspect.METAL, 2).add(Aspect.ORDER, 1).add(Aspect.GREED, 2);
                } else if (alchemyAspects.size() > 6) {
                    alchemyAspects = new AspectList().add(Aspect.METAL, 2).add(Aspect.ORDER, 1).add(Aspect.GREED, 2);
                }

                List<ItemStack> normalOre = notRich.stream()
                        .flatMap(prefix -> Utilities.oredictStream(prefix.name() + material.mName))
                        .collect(Collectors.toList());

                if (!normalOre.isEmpty()) {
                    CLUSTER_RECIPES.add(
                            registerAlchemyRecipe(
                                    "METALLURGICPERFECTION_CLUSTERS",
                                    "_" + material.mName + "_Normal",
                                    new ItemStack(WGContent.ItemCluster, 2, metaInfo.getMeta()),
                                    normalOre,
                                    alchemyAspects));
                }

                List<ItemStack> richOre = rich.stream()
                        .flatMap(prefix -> Utilities.oredictStream(prefix.name() + material.mName))
                        .collect(Collectors.toList());

                if (!richOre.isEmpty()) {
                    CLUSTER_RECIPES.add(
                            registerAlchemyRecipe(
                                    "METALLURGICPERFECTION_CLUSTERS",
                                    "_" + material.mName + "_Rich",
                                    new ItemStack(WGContent.ItemCluster, 4, metaInfo.getMeta()),
                                    richOre,
                                    alchemyAspects));
                }
            }

            ItemStack nugget = GTOreDictUnificator.get(OrePrefixes.nugget, material, 1);

            if (WGConfig.allowTransmutations && nugget != null && material.contains(SubTag.TRANSMUTABLE_NUGGETS)) {
                AspectList transmuteAspects = baseAspects.copy();

                if (Arrays.asList(transmuteAspects.getAspects()).contains(null)) {
                    transmuteAspects = new AspectList().add(Aspect.METAL, 2).add(Aspect.ORDER, 1)
                            .add(TCAspects.NEBRISUM.getAspect(), 2);
                } else {
                    if (Arrays.asList(transmuteAspects.getAspects()).contains(Aspect.METAL)
                            && transmuteAspects.size() > 1) {
                        transmuteAspects.remove(Aspect.METAL);
                    }
                    transmuteAspects.add(Aspect.METAL, 2);
                }

                TRANSMUTE_RECIPES.add(
                        registerAlchemyRecipe(
                                "METALLURGICPERFECTION_TRANSMUTATION",
                                "_" + material.mName,
                                Utilities.copyStackWithSize(nugget, 3),
                                Utilities.copyStackWithSize(nugget, 1),
                                transmuteAspects));
            }
        }
    }

    private static void setupCluster(ClusterInfo clusterInfo) {
        ItemStack cluster = new ItemStack(WGContent.ItemCluster, 1, clusterInfo.meta().getMeta());
        OreDictionary.registerOre("cluster" + clusterInfo.matName, cluster.copy());

        ItemStack nugget = clusterInfo.getPart("nugget", 1);
        ItemStack ingot = clusterInfo.getPart("ingot", 1);
        ItemStack ore = clusterInfo.getPart("ore", 1);

        assert ore != null;

        if (nugget != null) {
            ThaumcraftApi.addSmeltingBonus(ore.copy(), nugget.copy());

            ThaumcraftApi.addSmeltingBonus(cluster.copy(), nugget.copy());
        }

        if (ingot != null) {
            Utils.addSpecialMiningResult(ore.copy(), cluster.copy(), 1f);

            if (!clusterInfo.ebf || !WitchingGadgets.isGT5uLoaded || clusterInfo.getGT5uMaterial() == null) {
                FurnaceRecipes.smelting().func_151394_a(cluster.copy(), Utilities.copyStackWithSize(ingot, 2), 1.0F);
            }
        }

        if (WGModCompat.loaded_TCon && WGConfig.smelteryResultForClusters > 0 && clusterInfo.liquid != null) {
            int fluidTemp = clusterInfo.liquid.getFluid().getTemperature(clusterInfo.liquid);

            if (fluidTemp <= 0) fluidTemp = 550;

            WGModCompat.addTConSmelteryRecipe(
                    "cluster" + clusterInfo.matName,
                    "block" + clusterInfo.matName,
                    fluidTemp,
                    clusterInfo.liquid.getFluid().getName(),
                    WGConfig.smelteryResultForClusters);
        }
    }

    @Optional.Method(modid = "postea")
    private static void registerPosteaTransformer() {
        ItemStackReplacementManager.addItemReplacement("WitchingGadgets:item.WG_Cluster", tag -> {
            MetaInfo metaInfo = MetaInfo.fromMeta(tag.getInteger("Damage"));

            if (metaInfo.series == ItemClusters.Series.Legacy) {
                // Set the damage to error, which will alert the player that this cluster is no longer valid
                tag.setInteger("Damage", new MetaInfo(ItemClusters.Series.Error, 0).getMeta());
            }

            return tag;
        });
    }
}
