package witchinggadgets.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import thaumcraft.common.container.ContainerGhostSlots;
import witchinggadgets.common.items.tools.ItemBag;

public class ContainerVoidBag extends ContainerGhostSlots {

    private final World worldObj;
    private final int blockedSlot;
    public IInventory input = new InventoryBag(this);
    ItemStack pouch;
    EntityPlayer player;
    private final int hotbarSlot;
    private static final int POUCH_SLOT_AMOUNT = 18;

    public ContainerVoidBag(InventoryPlayer iinventory, World world) {
        this.worldObj = world;
        this.player = iinventory.player;
        this.pouch = iinventory.getCurrentItem();
        this.blockedSlot = iinventory.currentItem + 45;
        this.hotbarSlot = iinventory.currentItem;

        for (int a = 0; a < POUCH_SLOT_AMOUNT; a++) {
            this.addSlotToContainer(new SlotGhostSingleItem(this.input, a, 29 + a % 6 * 20, 7 + a / 6 * 20));
        }

        bindPlayerInventory(iinventory);

        if (!world.isRemote) try {
            ((InventoryBag) this.input).stackList = ItemBag.getStoredItems(this.pouch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.onCraftMatrixChanged(this.input);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 82 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 140));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slot) {
        ItemStack stack = null;
        Slot slotObject = this.inventorySlots.get(slot);

        if ((slotObject != null) && (slotObject.getHasStack())) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            if (slot < POUCH_SLOT_AMOUNT) {
                if (!this.mergeItemStack(stackInSlot, POUCH_SLOT_AMOUNT, this.inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(stackInSlot, 0, POUCH_SLOT_AMOUNT, false)) {
                return null;
            }

            if (stackInSlot.stackSize == 0) slotObject.putStack(null);
            else {
                slotObject.onSlotChanged();
            }
        }

        return stack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }

    @Override
    public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer player) {
        if (slotId == this.blockedSlot || mode == 0 && clickedButton == blockedSlot
                || mode == 2 && clickedButton == hotbarSlot) {
            return null;
        }
        ItemBag.setStoredItems(this.pouch, ((InventoryBag) this.input).stackList);

        return super.slotClick(slotId, clickedButton, mode, player);
    }

    @Override
    public void onContainerClosed(EntityPlayer par1EntityPlayer) {
        super.onContainerClosed(par1EntityPlayer);
        if (!this.worldObj.isRemote) {
            ItemBag.setStoredItems(this.pouch, ((InventoryBag) this.input).stackList);

            this.player.inventory.markDirty();
        }
    }

    @Override
    public void detectAndSendChanges() {
        if (!this.pouch.equals(this.player.getCurrentEquippedItem())) player.closeScreen();
        super.detectAndSendChanges();
    }
}
