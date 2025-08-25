package witchinggadgets.common.gui;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import witchinggadgets.common.items.tools.ItemPrimordialGlove;

public class ContainerPrimordialGlove extends Container {

    private final World worldObj;
    private final int blockedSlot;
    public IInventory input = new InventoryPrimordialGlove(this);
    ItemStack bracelet;
    EntityPlayer player;
    private static final int SLOT_AMOUNT = 5;

    public static HashMap<Integer, ContainerPrimordialGlove> map = new HashMap();

    public ContainerPrimordialGlove(InventoryPlayer iinventory, World world, int x, int y, int z) {
        this.worldObj = world;
        this.player = iinventory.player;
        this.bracelet = iinventory.getCurrentItem();
        this.blockedSlot = (iinventory.currentItem + SLOT_AMOUNT
                + iinventory.mainInventory.length
                - InventoryPlayer.getHotbarSize());

        this.addSlotToContainer(new SlotInfusedGem(this.input, 0, 60, 06));
        this.addSlotToContainer(new SlotInfusedGem(this.input, 1, 100, 06));

        this.addSlotToContainer(new SlotInfusedGem(this.input, 2, 57, 42));
        this.addSlotToContainer(new SlotInfusedGem(this.input, 3, 80, 53));
        this.addSlotToContainer(new SlotInfusedGem(this.input, 4, 103, 42));

        bindPlayerInventory(iinventory);

        if (!world.isRemote) try {
            map.put(player.getEntityId(), this);
            ((InventoryPrimordialGlove) this.input).stackList = ItemPrimordialGlove.getSetGems(this.bracelet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.onCraftMatrixChanged(this.input);
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
        Slot slotObject = (Slot) this.inventorySlots.get(slot);

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
    public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
        if (par1 == this.blockedSlot || (par2 == 0 && par3 == blockedSlot)) return null;
        ItemPrimordialGlove.setSetGems(this.bracelet, ((InventoryPrimordialGlove) this.input).stackList);

        return super.slotClick(par1, par2, par3, par4EntityPlayer);
    }

    @Override
    public void onContainerClosed(EntityPlayer par1EntityPlayer) {
        super.onContainerClosed(par1EntityPlayer);
        if (!this.worldObj.isRemote) {
            map.remove(player.getEntityId());
            ItemPrimordialGlove.setSetGems(this.bracelet, ((InventoryPrimordialGlove) this.input).stackList);

            this.player.inventory.markDirty();
        }
    }

    @Override
    public void detectAndSendChanges() {
        if (!this.bracelet.equals(this.player.getCurrentEquippedItem())) this.player.closeScreen();
        super.detectAndSendChanges();
    }
}
