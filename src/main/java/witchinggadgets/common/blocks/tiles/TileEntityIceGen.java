package witchinggadgets.common.blocks.tiles;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import thaumcraft.common.lib.utils.InventoryUtils;
import witchinggadgets.common.WGConfig;

public class TileEntityIceGen extends TileEntityWGBase {

    public ForgeDirection facing = ForgeDirection.getOrientation(2);
    public int tick = 0;
    int tickGoal = 40;

    public void updateEntity() {
        super.updateEntity();
        if (canWork()) {
            tick++;
            if (tick == 32 && worldObj.isRemote) worldObj.playSoundEffect(
                    xCoord + 0.5F,
                    yCoord + 0.5F,
                    zCoord + 0.5F,
                    "random.fizz",
                    0.5F,
                    2.6F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.8F);
            if (tick >= tickGoal) {
                if (!worldObj.isRemote) createIce();
                else worldObj.playSoundEffect(
                        xCoord + 0.5F,
                        yCoord + 0.5F,
                        zCoord + 0.5F,
                        "dig.stone",
                        0.5F,
                        2.6F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.8F);
                tick = 0;
            }
        } else {
            if (tick > 0) tick = 0;
        }
    }

    private void createIce() {
        ItemStack ice = new ItemStack(Blocks.ice, 16);
        TileEntity inventory = this.worldObj.getTileEntity(
                this.xCoord + this.facing.offsetX,
                this.yCoord + this.facing.offsetY,
                this.zCoord + this.facing.offsetZ);
        if (inventory instanceof IInventory) ice = InventoryUtils
                .placeItemStackIntoInventory(ice, (IInventory) inventory, this.facing.getOpposite().ordinal(), true);

        if (ice != null && WGConfig.allowdropsfrommachinery)
            if (facing.equals(ForgeDirection.UP) || facing.equals(ForgeDirection.DOWN)) {
                EntityItem ei = new EntityItem(
                        this.worldObj,
                        this.xCoord + 0.5D,
                        this.yCoord + 0.5 + this.facing.offsetY * 0.66D,
                        this.zCoord + 0.5D,
                        ice.copy());
                ei.motionX = 0.025000000372529D;
                ei.motionY = (0.075F * this.facing.offsetY);
                ei.motionZ = 0.025000000372529D;
                this.worldObj.spawnEntityInWorld(ei);
            } else {
                EntityItem ei = new EntityItem(
                        this.worldObj,
                        this.xCoord + 0.5D + this.facing.offsetX * 0.66D,
                        this.yCoord + 0.4D + this.facing.getOpposite().offsetY,
                        this.zCoord + 0.5D + this.facing.offsetZ * 0.66D,
                        ice.copy());
                ei.motionX = (0.075F * this.facing.offsetX);
                ei.motionY = 0.025000000372529D;
                ei.motionZ = (0.075F * this.facing.offsetZ);
                this.worldObj.spawnEntityInWorld(ei);
            }
    }

    private boolean canOutput() {
        TileEntity inventory = this.worldObj.getTileEntity(
                this.xCoord + this.facing.offsetX,
                this.yCoord + this.facing.offsetY,
                this.zCoord + this.facing.offsetZ);
        if (inventory instanceof IInventory inv) return InventoryUtils
                .insertStack(inv, new ItemStack(Blocks.ice), this.facing.getOpposite().ordinal(), false) == null;
        return true;
    }

    public boolean canWork() {
        return canOutput() && worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) <= 0
                && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tag) {
        tag.setInteger("facing", this.facing.ordinal());
    }

    @Override
    public void readCustomNBT(NBTTagCompound tag) {
        this.facing = ForgeDirection.getOrientation(tag.getInteger("facing"));
    }
}
