package witchinggadgets.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import thaumcraft.api.IRepairable;
import thaumcraft.api.ThaumcraftApi;

public class ItemThaumiumShears extends ItemShears implements IRepairable {
    public ItemThaumiumShears() {
        super();
        this.setMaxDamage(ThaumcraftApi.toolMatThaumium.getMaxUses());
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("witchinggadgets:thaumiumShears");
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.uncommon;
    }
}
