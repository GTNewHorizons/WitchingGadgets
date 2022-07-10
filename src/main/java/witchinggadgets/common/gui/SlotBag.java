package witchinggadgets.common.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotBag extends Slot {
    private final ContainerBag pouchContainer;

    public SlotBag(final IInventory inv, final ContainerBag bagContainer, final int id, final int x, final int z) {
        super(inv, id, x, z);
        this.pouchContainer = bagContainer;
    }

    public void onSlotChanged() {
        super.onSlotChanged();
        this.pouchContainer.saveCharmPouch();
    }
}
