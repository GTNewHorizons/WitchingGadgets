package witchinggadgets.common.gui;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import witchinggadgets.common.blocks.tiles.TileEntityLabelLibrary;

public class ContainerLabelLibrary extends Container {

    private static final String TAG_ASPECTS = "Aspects";
    private static final int LABEL_INPUT_SLOT = 0;
    private static final int LABEL_OUTPUT_SLOT = 1;
    private static final int SLOT_COUNT = 2;
    protected final TileEntityLabelLibrary tileEntity;
    // private int slotCount;

    public ContainerLabelLibrary(InventoryPlayer inventoryPlayer, TileEntityLabelLibrary te) {
        this.tileEntity = te;

        // the Slot constructor takes the IInventory and the slot number in that it binds to
        // and the x-y coordinates it resides on-screen

        this.addSlotToContainer(new Slot(tileEntity, LABEL_INPUT_SLOT, 8, 8) {

            @Override
            public boolean isItemValid(ItemStack stack) {
                return TileEntityLabelLibrary.isLabel(stack);
            }
        });
        this.addSlotToContainer(new SlotOutput(tileEntity, LABEL_OUTPUT_SLOT, 8, 51) {

            @Override
            public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
                this.inventory.decrStackSize(LABEL_INPUT_SLOT, 1);
            }
        });

        this.bindPlayerInventory(inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) for (int j = 0; j < 9; j++)
            addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

        for (int i = 0; i < 9; i++) addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        Slot clickedSlot = inventorySlots.get(slot);
        switch (slot) {
            case LABEL_INPUT_SLOT:
                return transferLabelsWithAspect(null);
            case LABEL_OUTPUT_SLOT:
                if ((clickedSlot).getHasStack()) {
                    ItemStack clickedStack = clickedSlot.getStack();
                    AspectList aspects = ((IEssentiaContainerItem) clickedStack.getItem()).getAspects(clickedStack);
                    return transferLabelsWithAspect(aspects);
                }
                return null;
            default:
                transferInventoryToLabels(clickedSlot);
                return null;
        }
    }

    private void transferInventoryToLabels(Slot clickedSlot) {
        ItemStack clickedItem = clickedSlot.getStack();
        if (TileEntityLabelLibrary.isLabel(clickedItem)) {
            Slot labelSlot = inventorySlots.get(LABEL_INPUT_SLOT);
            ItemStack labels = labelSlot.getStack();
            if (labels == null) {
                if (clickedItem.hasTagCompound() && clickedItem.stackTagCompound.hasKey(TAG_ASPECTS)) {
                    clickedItem.stackTagCompound.removeTag(TAG_ASPECTS);
                }
                labelSlot.putStack(clickedItem);
                clickedSlot.decrStackSize(clickedItem.stackSize);
            } else {
                final int amountToTransfer = Math
                        .min(labels.getMaxStackSize() - labels.stackSize, clickedItem.stackSize);
                if (amountToTransfer == 0) {
                    return;
                }
                labels.stackSize += amountToTransfer;
                labelSlot.putStack(labels);
                clickedSlot.decrStackSize(amountToTransfer);
            }
        }
    }

    private ItemStack transferLabelsWithAspect(@Nullable AspectList aspects) {
        Slot labelSlot = inventorySlots.get(LABEL_INPUT_SLOT);
        ItemStack labels = labelSlot.getStack();
        ItemStack returnStack = null;
        if (labelSlot.getHasStack()) {
            // Set the labels to the given aspect if one is given
            if (aspects != null) ((IEssentiaContainerItem) labels.getItem()).setAspects(labels, aspects);
            returnStack = labels.copy();
            if (!this.mergeItemStack(labels, SLOT_COUNT, this.inventorySlots.size(), true)) {
                // Set un-transferred labels back to no aspects
                if (labels.hasTagCompound() && labels.stackTagCompound.hasKey(TAG_ASPECTS)) {
                    labels.stackTagCompound.removeTag(TAG_ASPECTS);
                }
                return null;
            }
            if (labels.stackSize == 0) {
                labelSlot.putStack(null);
            } else {
                labelSlot.onSlotChanged();
            }
            // Set un-transferred labels back to no aspects
            if (labels.hasTagCompound() && labels.stackTagCompound.hasKey(TAG_ASPECTS)) {
                labels.stackTagCompound.removeTag(TAG_ASPECTS);
            }
        }
        return returnStack;
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, int clickTypeIn, EntityPlayer player) {
        ItemStack heldStack = player.inventory.getItemStack();
        if (slotId == LABEL_INPUT_SLOT && heldStack != null) {
            Slot labelSlot = inventorySlots.get(slotId);
            ItemStack labels = labelSlot.getStack();
            // Accept all label items no matter their aspect, and clear their aspect
            if (labelSlot.isItemValid(heldStack)) {
                if (labels == null) {
                    if (heldStack.hasTagCompound() && heldStack.stackTagCompound.hasKey(TAG_ASPECTS)) {
                        heldStack.stackTagCompound.removeTag(TAG_ASPECTS);
                    }
                    labelSlot.putStack(heldStack);
                    heldStack = null;
                } else {
                    final int amountToTransfer = Math
                            .min(labels.getMaxStackSize() - labels.stackSize, heldStack.stackSize);
                    labels.stackSize += amountToTransfer;
                    labelSlot.putStack(labels);
                    heldStack.stackSize -= amountToTransfer;
                    if (heldStack.stackSize == 0) {
                        heldStack = null;
                    }
                }
                player.inventory.setItemStack(heldStack);
            }
            return heldStack;
        }
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }
}
