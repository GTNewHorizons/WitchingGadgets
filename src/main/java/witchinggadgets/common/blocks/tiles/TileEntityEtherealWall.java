package witchinggadgets.common.blocks.tiles;

import java.util.Objects;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.common.util.EtherealWallMaster;

public class TileEntityEtherealWall extends TileEntityWGBase {

    public EtherealWallMaster master;

    public Block camoID = null;
    public int camoMeta = -1;
    public int camoRenderType = 0;

    public EtherealWallMaster getMaster() {
        EtherealWallMaster masterOV = null;
        EtherealWallMaster masterYmin = null;
        EtherealWallMaster masterYmax = null;
        EtherealWallMaster masterZmin = null;
        EtherealWallMaster masterZmax = null;
        EtherealWallMaster masterXmin = null;
        EtherealWallMaster masterXmax = null;
        TileEntity tile = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
        if (tile instanceof TileEntityEtherealWall etherealWall && etherealWall.master != null) {
            masterYmin = etherealWall.master;
        }

        tile = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
        if (tile instanceof TileEntityEtherealWall etherealWall && etherealWall.master != null) {
            masterYmax = etherealWall.master;
        }

        tile = worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);
        if (tile instanceof TileEntityEtherealWall etherealWall && etherealWall.master != null) {
            masterZmin = etherealWall.master;
        }

        tile = worldObj.getTileEntity(xCoord, yCoord, zCoord + 1);
        if (tile instanceof TileEntityEtherealWall etherealWall && etherealWall.master != null) {
            masterZmax = etherealWall.master;
        }

        tile = worldObj.getTileEntity(xCoord - 1, yCoord, zCoord);
        if (tile instanceof TileEntityEtherealWall etherealWall && etherealWall.master != null) {
            masterXmin = etherealWall.master;
        }

        tile = worldObj.getTileEntity(xCoord + 1, yCoord, zCoord);
        if (tile instanceof TileEntityEtherealWall etherealWall && etherealWall.master != null) {
            masterXmax = etherealWall.master;
        }

        // get Overall Master
        if (masterYmin != null) masterOV = masterYmin;
        else if (masterYmax != null) masterOV = masterYmax;
        else if (masterZmin != null) masterOV = masterZmin;
        else if (masterZmax != null) masterOV = masterZmax;
        else if (masterXmin != null) masterOV = masterXmin;
        else if (masterXmax != null) masterOV = masterXmax;
        // unify where necessary
        if (masterYmax != null && masterYmax != masterOV) {
            masterOV.integrateOtherNet(masterYmax);
        }
        if (masterZmin != null && masterZmin != masterOV) {
            masterOV.integrateOtherNet(masterZmin);
        }
        if (masterZmax != null && masterZmax != masterOV) {
            masterOV.integrateOtherNet(masterZmax);
        }
        if (masterXmin != null && masterXmin != masterOV) {
            masterOV.integrateOtherNet(masterXmin);
        }
        if (masterXmax != null && masterXmax != masterOV) {
            masterOV.integrateOtherNet(masterXmax);
        }

        return masterOV;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (this.master == null) {
            this.master = getMaster();
            if (this.master == null) this.master = new EtherealWallMaster();
            this.master.addTileToNet(this);
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound tag) {
        if (tag.hasKey("camo")) camoID = Block.getBlockFromName(tag.getString("camo"));
        camoMeta = tag.getInteger("camoMeta");
        camoRenderType = tag.getInteger("camoRenderType");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tag) {
        if (camoID != null) tag.setString("camo", Block.blockRegistry.getNameForObject(camoID));
        tag.setInteger("camoMeta", camoMeta);
        tag.setInteger("camoRenderType", camoRenderType);
    }

    public boolean isRenderTypeValid(int renderType, int blockMeta) {
        if (renderType == 0 || renderType == 31 || renderType == 39) return true;
        if (renderType == ConfigBlocks.blockWoodenDeviceRI) return blockMeta == 2 || blockMeta == 6 || blockMeta == 7;
        if (renderType == ConfigBlocks.blockStoneDeviceRI) return blockMeta == 0;
        if (renderType == ConfigBlocks.blockMetalDeviceRI) return blockMeta == 9;
        if (renderType == ConfigBlocks.blockCustomOreRI) return blockMeta == 0 || blockMeta == 7;
        return renderType == ConfigBlocks.blockCosmeticOpaqueRI;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TileEntityEtherealWall that)) return false;
        return xCoord == that.xCoord && yCoord == that.yCoord && zCoord == that.zCoord;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoord, yCoord, zCoord);
    }
}
