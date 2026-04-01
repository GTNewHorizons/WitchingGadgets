package witchinggadgets.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import baubles.api.BaublesApi;
import baubles.api.expanded.BaubleExpandedSlots;
import cpw.mods.fml.common.network.IGuiHandler;
import witchinggadgets.common.blocks.tiles.TileEntityCuttingTable;
import witchinggadgets.common.blocks.tiles.TileEntityLabelLibrary;
import witchinggadgets.common.blocks.tiles.TileEntitySpinningWheel;
import witchinggadgets.common.gui.ContainerBag;
import witchinggadgets.common.gui.ContainerCloak;
import witchinggadgets.common.gui.ContainerCuttingTable;
import witchinggadgets.common.gui.ContainerLabelLibrary;
import witchinggadgets.common.gui.ContainerPrimordialGlove;
import witchinggadgets.common.gui.ContainerSpinningWheel;
import witchinggadgets.common.gui.ContainerVoidBag;
import witchinggadgets.common.pouch.ContainerPatchedFocusPouch;

public class CommonProxy implements IGuiHandler {

    public void registerRenders() {}

    public void registerHandlers() {}

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);

        return switch (ID) {
            // 0
            case ContainerSpinningWheel.ID -> new ContainerSpinningWheel(
                    player.inventory,
                    (TileEntitySpinningWheel) tile);
            // 3
            case ContainerBag.ID -> new ContainerBag(player.inventory, world);
            case 4, 5 -> {
                String baubleSlot = ID == 4 ? BaubleExpandedSlots.capeType : BaubleExpandedSlots.beltType;
                yield new ContainerCloak(
                        player.inventory,
                        world,
                        BaublesApi.getBaubles(player)
                                .getStackInSlot(BaubleExpandedSlots.getIndexesOfAssignedSlotsOfType(baubleSlot)[0]));
            }
            // 6
            case ContainerPatchedFocusPouch.ID -> new ContainerPatchedFocusPouch(player.inventory, world, x, y, z);
            // 7
            case ContainerPrimordialGlove.ID -> new ContainerPrimordialGlove(player.inventory, world, x, y, z);
            // 8
            case ContainerLabelLibrary.ID -> new ContainerLabelLibrary(player.inventory, (TileEntityLabelLibrary) tile);
            // 9
            case ContainerCuttingTable.ID -> new ContainerCuttingTable(player.inventory, (TileEntityCuttingTable) tile);
            // 11
            case ContainerVoidBag.ID -> new ContainerVoidBag(player.inventory, world);
            default -> null;
        };
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public void createEssentiaTrailFx(World worldObj, int x, int y, int z, int tx, int ty, int tz, int count, int color,
            float scale) {}

    public void createTargetedWispFx(World worldObj, double x, double y, double z, double tx, double ty, double tz,
            int color, float scale, float gravity, boolean tinkle, boolean noClip) {}

    public void createSweatFx(EntityPlayer player) {}
}
