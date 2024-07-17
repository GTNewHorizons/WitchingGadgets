package witchinggadgets.common.blocks.tiles;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityWallMirror extends TileEntityWGBase {

    public int activationAnimation = 0;
    public int animation = 0;
    public boolean isActive = false;
    public boolean temp_isActivating = false;
    public boolean temp_isDeActivating = false;

    public int facing = 0;

    public boolean isDummy = false;

    public void updateEntity() {
        super.updateEntity();
        if (this.isDummy) return; // Dummy Blocks don't do anything :P
        if (temp_isActivating) {
            if (activationAnimation < 31) activationAnimation++;
            else {
                isActive = true;
                temp_isActivating = false;
            }
            animation = 0;
        }
        if (temp_isDeActivating) {
            if (activationAnimation > 0) {
                if (activationAnimation < 17) activationAnimation--;
                activationAnimation--;
            } else {
                isActive = false;
                temp_isDeActivating = false;
            }
            animation = 0;
        }

    }

    @Override
    public void readCustomNBT(NBTTagCompound tags) {
        isActive = tags.getBoolean("isActive");
        isDummy = tags.getBoolean("isDummy");
        facing = tags.getInteger("facing");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tags) {
        tags.setBoolean("isActive", isActive);
        tags.setBoolean("isDummy", isDummy);
        tags.setInteger("facing", facing);
    }

    public List<EntityPlayer> getMirroredPlayers() {
        double minX = facing == 2 || facing == 3 ? -2 : facing == 4 ? -8 : -.5;
        double maxX = facing == 2 || facing == 3 ? 2 : facing == 4 ? .5 : 8;
        double minZ = facing == 4 || facing == 5 ? -2 : facing == 2 ? -8 : -.5;
        double maxZ = facing == 4 || facing == 5 ? 2 : facing == 2 ? .5 : 8;

        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(minX, -1, minZ, maxX, 2, maxZ);
        aabb = aabb.getOffsetBoundingBox(xCoord + .5, yCoord + 1, zCoord + .5);

        return worldObj.getEntitiesWithinAABB(EntityPlayer.class, aabb);
    }
}
