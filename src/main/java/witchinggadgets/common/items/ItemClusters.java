package witchinggadgets.common.items;

import java.util.HashMap;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

import gregtech.api.enums.Materials;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;

public class ItemClusters extends Item {

    @Deprecated
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

    public static HashMap<String, String> loccodename = new HashMap<>();

    @Deprecated
    public static HashMap<String, Integer[]> materialMap = new HashMap<>();

    IIcon iconMetal;
    IIcon[] iconOverlay = new IIcon[3];

    public ItemClusters() {
        super();
        maxStackSize = 64;
        setCreativeTab(WitchingGadgets.tabWG);
        setHasSubtypes(true);
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        if (witchinggadgets.common.WGContent.GT_Cluster != null) {
            if (pass == 0 && stack.getItemDamage() < witchinggadgets.common.WGContent.GT_Cluster.length) {
                if (WGContent.GT_Cluster_Color.get(witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()])
                        != null)
                    return WGContent.GT_Cluster_Color
                            .get(witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()])[0];
            }
        } else {
            if (pass == 0) {
                if (materialMap.get(subNames[stack.getItemDamage()]) != null) {
                    Integer[] mat = materialMap.get(subNames[stack.getItemDamage()]);
                    if (mat != null && mat.length > 0) {
                        return mat[0];
                    }
                }
            }
        }
        return 0xffffff;
    }

    public static ItemStack getCluster(String ore) {
        if (WGConfig.allowClusters) {
            if (witchinggadgets.common.WGContent.GT_Cluster != null) {
                for (int sn = 0; sn < witchinggadgets.common.WGContent.GT_Cluster.length; sn++)
                    if (witchinggadgets.common.WGContent.GT_Cluster[sn].equalsIgnoreCase(ore))
                        return new ItemStack(WGContent.ItemCluster, 1, sn);
            } else {
                for (int sn = 0; sn < subNames.length; sn++)
                    if (subNames[sn].equalsIgnoreCase(ore)) return new ItemStack(WGContent.ItemCluster, 1, sn);
            }
        }
        return null;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.iconMetal = iconRegister.registerIcon("witchinggadgets:cluster_metal");
        this.iconOverlay[0] = iconRegister.registerIcon("witchinggadgets:cluster_overlay");
        this.iconOverlay[1] = iconRegister.registerIcon("witchinggadgets:cluster_overlayNether");
        this.iconOverlay[2] = iconRegister.registerIcon("witchinggadgets:cluster_overlayEnd");
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public IIcon getIconFromDamageForRenderPass(int damage, int pass) {
        if (pass == 0) {
            return this.iconMetal;
        }
        if (witchinggadgets.common.WGContent.GT_Cluster != null) {
            if (damage < witchinggadgets.common.WGContent.GT_Cluster.length
                    && WGContent.GT_Cluster_Color.get(witchinggadgets.common.WGContent.GT_Cluster[damage]) != null) {
                return this.iconOverlay[WGContent.GT_Cluster_Color
                        .get(witchinggadgets.common.WGContent.GT_Cluster[damage])[1]];
            } else {
                return this.iconOverlay[0];
            }
        } else {
            if (materialMap.get(subNames[damage]) != null) {
                return this.iconOverlay[materialMap.get(subNames[damage])[1]];
            } else {
                return this.iconOverlay[0];
            }
        }
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return getIconFromDamageForRenderPass(stack.getItemDamage(), pass);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        if (witchinggadgets.common.WGContent.GT_Cluster != null) {
            String ss = Materials
                    .get(witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()]).mLocalizedName;
            return StatCollector.translateToLocalFormatted(this.getUnlocalizedNameInefficiently(stack) + ".name", ss)
                    .trim();
        } else {
            String ss = "";
            if (!OreDictionary.getOres("ingot" + subNames[stack.getItemDamage()]).isEmpty()) {
                ItemStack ingot = OreDictionary.getOres("ingot" + subNames[stack.getItemDamage()]).get(0);
                int limit = ingot.getDisplayName().lastIndexOf(" ");
                ss = ingot.getDisplayName().substring(0, Math.max(0, limit));
            }
            return StatCollector.translateToLocalFormatted(this.getUnlocalizedNameInefficiently(stack) + ".name", ss)
                    .trim();
        }
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> itemList) {
        if (WGConfig.allowClusters) if (witchinggadgets.common.WGContent.GT_Cluster != null) {
            for (int iOre = 0; iOre < witchinggadgets.common.WGContent.GT_Cluster.length; iOre++)
                if (!OreDictionary.getOres("ore" + witchinggadgets.common.WGContent.GT_Cluster[iOre]).isEmpty()) // &&
                    itemList.add(new ItemStack(item, 1, iOre));
        } else {
            for (int iOre = 0; iOre < subNames.length; iOre++)
                if (!OreDictionary.getOres("ore" + subNames[iOre]).isEmpty()
                        && !OreDictionary.getOres("ingot" + subNames[iOre]).isEmpty())
                    itemList.add(new ItemStack(item, 1, iOre));
        }
    }
}
