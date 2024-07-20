package witchinggadgets.common.blocks.tiles;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityAgeingStone extends TileEntityWGBase {

    @Override
    public void updateEntity() {
        AxisAlignedBB box = AxisAlignedBB
                .getBoundingBox(xCoord - 3, yCoord - 2, zCoord - 3, xCoord + 4, yCoord + 3, zCoord + 4);
        List<Entity> hitEntities = worldObj.getEntitiesWithinAABB(Entity.class, box);
        for (int i = 0; i < hitEntities.size(); i++) {
            Entity ent = hitEntities.get(i);
            if (ent instanceof EntityAgeable ageable) {
                int age = ageable.getGrowingAge();

                if (age < 0) {
                    ++age;
                    ageable.setGrowingAge(age);
                } else if (age > 0) {
                    --age;
                    ageable.setGrowingAge(age);
                }
            }
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound tags) {}

    @Override
    public void writeCustomNBT(NBTTagCompound tags) {}
}
