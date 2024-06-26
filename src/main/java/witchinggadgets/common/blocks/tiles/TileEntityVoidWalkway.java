package witchinggadgets.common.blocks.tiles;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityVoidWalkway extends TileEntity {

    @Override
    public void updateEntity() {
        double minX = this.xCoord - 2.5;
        double maxX = this.xCoord + 2.5;
        double minZ = this.zCoord - 2.5;
        double maxZ = this.zCoord + 2.5;
        double minY = this.yCoord;
        double maxY = this.yCoord + 1.5D;

        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);

        List<EntityPlayer> list = worldObj.getEntitiesWithinAABB(EntityPlayer.class, aabb);
        boolean flag = false;
        for (EntityPlayer p : list) {
            if (p.isSneaking()) flag = true;
        }
        if (list.isEmpty() || flag) {
            worldObj.setBlockToAir(xCoord, yCoord, zCoord);
        }
    }
}
