package witchinggadgets.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import witchinggadgets.common.items.tools.ItemPrimordialGlove;

public class ContainerPrimordialGlove extends Container {

    private final World worldObj;
    private final int blockedSlot;
    public InventoryPrimordialGlove input = new InventoryPrimordialGlove(this);
    ItemStack bracelet;
    EntityPlayer player;
    private static final int SLOT_AMOUNT = 5;

    public ContainerPrimordialGlove(InventoryPlayer iinventory, World world, int x, int y, int z) {
        this.worldObj = world;
        this.player = iinventory.player;
        this.bracelet = iinventory.getCurrentItem();
        this.blockedSlot = iinventory.currentItem + SLOT_AMOUNT + 27;

        this.addSlotToContainer(new SlotInfusedGem(this.input, 0, 60, 06));
        this.addSlotToContainer(new SlotInfusedGem(this.input, 1, 100, 06));

        this.addSlotToContainer(new SlotInfusedGem(this.input, 2, 57, 42));
        this.addSlotToContainer(new SlotInfusedGem(this.input, 3, 80, 53));
        this.addSlotToContainer(new SlotInfusedGem(this.input, 4, 103, 42));

        bindPlayerInventory(iinventory);

        try {
            this.input.stackList = ItemPrimordialGlove.getSetGems(this.bracelet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slot) {
        ItemStack stack = null;
        Slot slotObject = this.inventorySlots.get(slot);

        if ((slotObject != null) && (slotObject.getHasStack())) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            if (slot < SLOT_AMOUNT) {
                if (!this.mergeItemStack(stackInSlot, SLOT_AMOUNT, this.inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(stackInSlot, 0, SLOT_AMOUNT, false)) {
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
    public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer player) {
        if (par1 == this.blockedSlot || (par2 == 0 && par3 == blockedSlot)) return null;
        ItemStack held = player.getHeldItem();
        if (!worldObj.isRemote && !ItemStack.areItemStacksEqual(this.bracelet, held)) {
            player.closeScreen();
            return null;
        }

        ItemStack stack = super.slotClick(par1, par2, par3, player);

        ItemPrimordialGlove.setSetGems(this.bracelet, this.input.stackList);
        if (!ItemStack.areItemStacksEqual(this.bracelet, held)) {
            held.setTagCompound(this.bracelet.getTagCompound());
        }
        return stack;
    }

    @Override
    public void detectAndSendChanges() {
        if (!ItemStack.areItemStacksEqual(player.getHeldItem(), this.bracelet)) {
            this.player.closeScreen();
            return;
        }
        super.detectAndSendChanges();
    }
}
