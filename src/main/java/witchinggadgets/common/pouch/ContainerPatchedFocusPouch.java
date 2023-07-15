package witchinggadgets.common.pouch;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import baubles.api.BaublesApi;
import thaumcraft.common.container.ContainerFocusPouch;
import thaumcraft.common.container.InventoryFocusPouch;
import thaumcraft.common.items.wands.ItemFocusPouch;
import witchinggadgets.mixins.late.thaumcraft.ContainerFocusPouchAccessor;

public class ContainerPatchedFocusPouch extends ContainerFocusPouch {

    public ContainerPatchedFocusPouch(InventoryPlayer iinventory, World world, int par3, int par4, int par5) {
        super(iinventory, world, par3, par4, par5);
        ((ContainerFocusPouchAccessor) this).setBlockSlot(-1);
        ItemStack beltPouch = null;
        IInventory baubles = BaublesApi.getBaubles(iinventory.player);
        for (int a = 0; a < 4; a++) {
            if (baubles.getStackInSlot(a) != null && baubles.getStackInSlot(a).getItem() instanceof ItemFocusPouch)
                beltPouch = baubles.getStackInSlot(a);
        }
        if (beltPouch != null) {
            ((ContainerFocusPouchAccessor) this).setPouch(beltPouch);
            if (!world.isRemote) {
                ((InventoryFocusPouch) this.input).stackList = ((ItemFocusPouch) beltPouch.getItem())
                        .getInventory(beltPouch);
            }
        }
        onCraftMatrixChanged(this.input);
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        if (!player.worldObj.isRemote) {
            // ((ItemFocusPouch)this.pouch.getItem()).setInventory(this.pouch,
            // ((InventoryFocusPouch)this.input).stackList);
            // if (this.player == null) {
            // return;
            // }
            // if ((this.player.getHeldItem() != null) && (this.player.getHeldItem().isItemEqual(this.pouch))) {
            // this.player.setCurrentItemOrArmor(0, this.pouch);
            // }
            // this.player.inventory.markDirty();
            ItemStack beltPouch = ((ContainerFocusPouchAccessor) this).getPouch();
            if (beltPouch != null) {
                if (BaublesApi.getBaubles(player).getStackInSlot(3) != null
                        && BaublesApi.getBaubles(player).getStackInSlot(3).isItemEqual(beltPouch)) {
                    BaublesApi.getBaubles(player).setInventorySlotContents(3, beltPouch);
                    BaublesApi.getBaubles(player).markDirty();
                }
            }
        }
    }
}
