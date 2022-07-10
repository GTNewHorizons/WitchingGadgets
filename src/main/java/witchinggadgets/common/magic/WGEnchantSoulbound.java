package witchinggadgets.common.magic;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class WGEnchantSoulbound extends Enchantment {

    public static int id;

    public WGEnchantSoulbound(int id) {
        super(id, 0, EnumEnchantmentType.all);
        this.setName("wg.soulbound");
        this.id = id;
    }

    @Override
    public int getMinEnchantability(int lvl) {
        return 6;
    }

    @Override
    public int getMaxEnchantability(int lvl) {
        return getMinEnchantability(lvl) + 20;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}
