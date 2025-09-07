package witchinggadgets.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import witchinggadgets.common.WGContent;
import witchinggadgets.common.items.tools.ItemBag;

public class ContainerBag extends Container {

    private final World worldObj;
    private final int blockedSlot;
    private final ItemStack pouch;
    private final EntityPlayer player;
    public InventoryBag input = new InventoryBag(this);
    private final int hotbarSlot;
    private static final int POUCH_SLOT_AMOUNT = 18;

    public ContainerBag(InventoryPlayer iinventory, World world) {
        this.worldObj = world;
        this.player = iinventory.player;
        this.pouch = iinventory.getCurrentItem();
        this.blockedSlot = iinventory.currentItem + 45;
        this.hotbarSlot = iinventory.currentItem;

        for (int a = 0; a < POUCH_SLOT_AMOUNT; a++) {
            this.addSlotToContainer(new SlotBag(this.input, this, a, 35 + a % 6 * 18, 9 + a / 6 * 18));
        }

        bindPlayerInventory(iinventory);

        try {
            this.input.stackList = ItemBag.getStoredItems(this.pouch);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        if (slotObject != null && slotObject.getHasStack()) {
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
        ItemStack held = player.getHeldItem();
        if (!worldObj.isRemote && !ItemStack.areItemStacksEqual(this.pouch, held)) {
            player.closeScreen();
            return null;
        }

        ItemStack stack = super.slotClick(slotId, clickedButton, mode, player);

        ItemBag.setStoredItems(this.pouch, this.input.stackList);
        if (!ItemStack.areItemStacksEqual(this.pouch, held)) {
            held.setTagCompound(this.pouch.getTagCompound());
        }
        return stack;
    }

    private boolean isHoldingPouch() {
        ItemStack is = this.player.getHeldItem();
        return (is != null) && (is.getItem() == WGContent.ItemBag);
    }

    public void detectAndSendChanges() {
        if ((!this.player.worldObj.isRemote) && (!isHoldingPouch())) {
            this.player.closeScreen();
            return;
        }
        super.detectAndSendChanges();
    }

    public void saveCharmPouch() {
        if (!this.player.worldObj.isRemote && this.isHoldingPouch()) {
            this.player.setCurrentItemOrArmor(0, this.pouch);
        }
    }
}
