package witchinggadgets.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockStoneDevice extends ItemBlock {

    public ItemBlockStoneDevice(Block b) {
        super(b);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damageValue) {
        return damageValue;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> itemList) {
        itemList.add(new ItemStack(item, 1, BlockWGStoneDevice.SubID.ETHEREAL_WALL.getMeta()));
        itemList.add(new ItemStack(item, 1, BlockWGStoneDevice.SubID.TIME_STONE.getMeta()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return getUnlocalizedName() + "." + BlockWGStoneDevice.SubID.fromMeta(itemstack.getItemDamage()).name;
    }
}
