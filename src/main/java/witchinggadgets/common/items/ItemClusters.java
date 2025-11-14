package witchinggadgets.common.items;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.common.Optional;
import gregtech.api.GregTechAPI;
import gregtech.api.enums.Materials;
import lombok.Data;
import mods.railcraft.common.items.firestone.IItemFirestoneBurning;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.recipes.alchemic.WG_alchemic_clusters;
import witchinggadgets.common.util.Utilities;

@Optional.Interface(iface = "mods.railcraft.common.items.firestone.IItemFirestoneBurning", modid = "Railcraft")
public class ItemClusters extends Item implements IItemFirestoneBurning {

    private IIcon iconMetal;
    private final IIcon[] iconOverlay = new IIcon[3];

    public ItemClusters() {
        setUnlocalizedName("WG_Cluster");
        setHasSubtypes(true);
        setCreativeTab(WitchingGadgets.tabWG);
    }

    public enum Series {
        Legacy,
        GT5u,
        Misc,
        Error
    }

    @Data
    public static class MetaInfo {

        public final Series series;
        public final int matId;

        public int getMeta() {
            if (series == Series.Error) return 0;

            return series.ordinal() * 1000 + matId;
        }

        public String getMaterialName() {
            return switch (series) {
                case Legacy -> null;
                case GT5u -> GregTechAPI.sGeneratedMaterials[matId].mName;
                case Misc -> WG_alchemic_clusters.subNames[matId];
                case Error -> null;
            };
        }

        @Optional.Method(modid = "gregtech_nh")
        public Materials getGT5uMaterial() {
            if (series == Series.Legacy) return null;
            if (series == Series.GT5u) return GregTechAPI.sGeneratedMaterials[matId];
            if (series == Series.Error) return null;

            return Materials.get(getMaterialName());
        }

        @Optional.Method(modid = "gregtech_nh")
        public String getGT5uMatName() {
            Materials mat = getGT5uMaterial();

            return mat == null ? "NULL" : mat.getLocalizedName();
        }

        public static MetaInfo fromMeta(int metadata) {
            Series series = switch (metadata / 1000) {
                case 0 -> Series.Legacy;
                case 1 -> Series.GT5u;
                case 2 -> Series.Misc;
                default -> Series.Error;
            };

            return new MetaInfo(series, metadata % 1000);
        }
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        if (pass != 0) return 0xFFFFFF;

        WG_alchemic_clusters.ClusterInfo clusterInfo = WG_alchemic_clusters.CLUSTER_INFO_BY_META
                .get(stack.getItemDamage());

        return clusterInfo == null ? 0xFFFFFF : clusterInfo.vibrant();
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

        WG_alchemic_clusters.ClusterInfo clusterInfo = WG_alchemic_clusters.CLUSTER_INFO_BY_META.get(damage);

        return clusterInfo == null ? this.iconOverlay[0] : this.iconOverlay[clusterInfo.clusterOverlay().ordinal()];
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return getIconFromDamageForRenderPass(stack.getItemDamage(), pass);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        MetaInfo metaInfo = MetaInfo.fromMeta(stack.getItemDamage());

        switch (metaInfo.series) {
            case Legacy -> {
                return StatCollector.translateToLocal("item.WG_Material.clusterLegacy.name");
            }
            case GT5u -> {
                return StatCollector.translateToLocalFormatted(
                        "item.WG_Cluster.name",
                        WitchingGadgets.isGT5uLoaded ? metaInfo.getGT5uMatName() : "NULL");
            }
            case Misc -> {
                WG_alchemic_clusters.ClusterInfo clusterInfo = WG_alchemic_clusters.CLUSTER_INFO_BY_META
                        .get(stack.getItemDamage());

                if (clusterInfo == null) {
                    return StatCollector.translateToLocal("item.WG_Material.clusterInvalid.name");
                }

                String key = "item.WG_Material.cluster" + clusterInfo.matName() + ".name";

                if (StatCollector.canTranslate(key)) {
                    return StatCollector.translateToLocal(key);
                }

                ItemStack ingot = Utilities.getOredict("ingot" + clusterInfo.matName(), 1);

                if (ingot == null) {
                    return StatCollector.translateToLocal("item.WG_Material.clusterInvalid.name");
                }

                String name = ingot.getDisplayName();

                name = name.substring(0, name.lastIndexOf(' ')).trim();

                return StatCollector.translateToLocalFormatted("item.WG_Cluster.name", name);
            }
            case Error -> {
                return StatCollector.translateToLocal("item.WG_Material.clusterInvalid.name");
            }
        }

        return StatCollector.translateToLocal("item.WG_Material.clusterInvalid.name");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advancedTooltips) {
        super.addInformation(stack, player, tooltip, advancedTooltips);

        MetaInfo metaInfo = MetaInfo.fromMeta(stack.getItemDamage());

        if (metaInfo.series == Series.Error) {
            tooltip.add(StatCollector.translateToLocal("tooltip.WitchingGadgets.cluster.error1"));
            tooltip.add(StatCollector.translateToLocal("tooltip.WitchingGadgets.cluster.error2"));
        }
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> itemList) {
        List<ItemStack> out = new ArrayList<>();

        for (WG_alchemic_clusters.ClusterInfo clusterInfo : WG_alchemic_clusters.CLUSTER_INFO.values()) {
            out.add(new ItemStack(this, 1, clusterInfo.meta().getMeta()));
        }

        out.sort(Comparator.comparingInt(ItemStack::getItemDamage));

        itemList.addAll(out);
    }

    @Override
    @Optional.Method(modid = "Railcraft")
    public boolean shouldBurn(ItemStack itemStack) {
        WG_alchemic_clusters.ClusterInfo clusterInfo = WG_alchemic_clusters.CLUSTER_INFO_BY_META
                .get(itemStack.getItemDamage());

        return clusterInfo != null && clusterInfo.matName().equals("Firestone");
    }
}
