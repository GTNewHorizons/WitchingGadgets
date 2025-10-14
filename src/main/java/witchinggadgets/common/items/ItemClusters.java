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

    public static HashMap<String, String> loccodename = new HashMap<>();

    @Deprecated
    public static HashMap<String, Integer[]> materialMap = new HashMap<>();

    IIcon iconMetal;
    IIcon[] iconOverlay = new IIcon[3];

    public ItemClusters() {
        super();
        setCreativeTab(WitchingGadgets.tabWG);
        setHasSubtypes(true);
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        if (pass == 0 && stack.getItemDamage() < witchinggadgets.common.WGContent.GT_Cluster.length) {
            if (WGContent.GT_Cluster_Color.get(witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()])
                    != null)
                return WGContent.GT_Cluster_Color
                        .get(witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()])[0];
        }
        return 0xffffff;
    }

    public static ItemStack getCluster(String ore) {
        if (WGConfig.allowClusters) for (int sn = 0; sn < witchinggadgets.common.WGContent.GT_Cluster.length; sn++)
            if (witchinggadgets.common.WGContent.GT_Cluster[sn].equalsIgnoreCase(ore))
                return new ItemStack(WGContent.ItemCluster, 1, sn);
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
        if (pass == 0) return this.iconMetal;
        else if (damage < witchinggadgets.common.WGContent.GT_Cluster.length
                && WGContent.GT_Cluster_Color.get(witchinggadgets.common.WGContent.GT_Cluster[damage]) != null)
            return this.iconOverlay[WGContent.GT_Cluster_Color
                    .get(witchinggadgets.common.WGContent.GT_Cluster[damage])[1]];
        else return this.iconOverlay[0];
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return getIconFromDamageForRenderPass(stack.getItemDamage(), pass);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String ss = Materials.get(witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()]).mLocalizedName;
        return StatCollector.translateToLocalFormatted(this.getUnlocalizedNameInefficiently(stack) + ".name", ss)
                .trim();
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> itemList) {
        if (WGConfig.allowClusters)
            for (int iOre = 0; iOre < witchinggadgets.common.WGContent.GT_Cluster.length; iOre++)
                if (!OreDictionary.getOres("ore" + witchinggadgets.common.WGContent.GT_Cluster[iOre]).isEmpty()) // &&
                    itemList.add(new ItemStack(item, 1, iOre));
    }
}
