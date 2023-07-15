package witchinggadgets.common.items.tools;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import witchinggadgets.WitchingGadgets;

public class ItemVorpalSword extends ItemSword { // TODO: Extend Avarita Sword

    public ItemVorpalSword() {
        super(ToolMaterial.IRON);
        this.setHarvestLevel("Sword", Integer.MAX_VALUE);
        this.setMaxDamage(Integer.MAX_VALUE);
        this.setMaxStackSize(1);
        this.setCreativeTab(WitchingGadgets.tabWG);
    }

    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
        p_77644_2_.setHealth(0);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_,
            int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_) {
        return true;
    }

    public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        if (!p_77624_2_.getDisplayName().contains("Alice")) {
            p_77624_3_.add("Are you Alice?");
            p_77624_3_.add("You seem to be " + p_77624_2_.getDisplayName() + "!");
        } else p_77624_3_.add("You are Alice!");
    }
}
