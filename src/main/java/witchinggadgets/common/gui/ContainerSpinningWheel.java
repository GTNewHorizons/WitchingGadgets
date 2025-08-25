package witchinggadgets.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import witchinggadgets.common.blocks.tiles.TileEntitySpinningWheel;

public class ContainerSpinningWheel extends Container {

    protected TileEntitySpinningWheel tileEntity;
    private static final int SLOT_AMOUNT = 6;

    public ContainerSpinningWheel(InventoryPlayer inventoryPlayer, TileEntitySpinningWheel te) {
        this.tileEntity = te;

        // the Slot constructor takes the IInventory and the slot number in that it binds to
        // and the x-y coordinates it resides on-screen
        for (int i = 0; i < 5; i++) {
            this.addSlotToContainer(new Slot(tileEntity, i, 16, 16 + 24 * i));
        }
        this.addSlotToContainer(new SlotOutput(tileEntity, 5, 142, 64));
        // commonly used vanilla code that adds the player's inventory
        this.bindPlayerInventory(inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 16 + j * 18, 151 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 16 + i * 18, 209));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        // null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            // merges the item into player inventory since its in the tileEntity
            if (slot < SLOT_AMOUNT) {
                if (!this.mergeItemStack(stackInSlot, SLOT_AMOUNT, (SLOT_AMOUNT + 36), true)) {
                    return null;
                }
            }
            // places it into the tileEntity is possible since its in the player inventory
            else if (!this.mergeItemStack(stackInSlot, 0, SLOT_AMOUNT, false)) {
                return null;
            }

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            } else {
                slotObject.onSlotChanged();
            }

            if (stackInSlot.stackSize == stack.stackSize) {
                return null;
            }
            slotObject.onPickupFromSlot(player, stackInSlot);
        }
        return stack;
    }
}
