package witchinggadgets.common.blocks.tiles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.common.network.NetworkRegistry;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IAspectSource;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXEssentiaSource;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileBellows;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.Utilities.OreDictStack;
import witchinggadgets.common.util.recipe.InfernalBlastfurnaceRecipe;

public class TileEntityBlastfurnace extends TileEntityWGBase implements IEssentiaTransport {

    public static Block[] brickBlock = new Block[18];
    public static Block stairBlock;

    public byte position = -1;
    public int[] masterPos = { -1, -1, -1 };
    int speedupTick = 0;
    int processTick = 0;
    int recipeTime = 0;
    int fuel = 0;
    int tick = 0;
    boolean specialFuel;
    public ForgeDirection facing = ForgeDirection.UNKNOWN;
    public boolean active = false;
    List<ItemStack> inputs = new ArrayList();

    @Override
    public void updateEntity() {
        if ((position != 1 && position != 3
                && position != 4
                && position != 5
                && position != 7
                && position != 10
                && position != 12
                && position != 14
                && position != 16) || this.worldObj.isRemote)
            return;

        if (position == 10 || position == 12 || position == 14 || position == 16) {
            TileEntityBlastfurnace master = (TileEntityBlastfurnace) worldObj
                    .getTileEntity(masterPos[0], masterPos[1], masterPos[2]);
            if (master.speedupTick <= 0)
                master.speedupTick = VisNetHandler.drainVis(worldObj, xCoord, yCoord, zCoord, Aspect.FIRE, 5);
        }

        if (position != 4) return;

        if (tick <= 80) ++tick;
        else tick = 0;

        if (fuel < 32) storeFuel();

        boolean cooking = false;

        if (processTick > 0 && fuel > 0) {
            processTick--;
            cooking = true;

            if (fuel > 0 && tick == 0) --fuel;

            if (speedupTick > 0) speedupTick--;

            int calc = calculateTime();
            if (processTick > calc) processTick = calc;
        }

        if (processTick <= 0 && !inputs.isEmpty() && fuel > 0) if (cooking) {
            ItemStack inputStack = inputs.get(0);
            InfernalBlastfurnaceRecipe recipe = InfernalBlastfurnaceRecipe.getRecipeForInput(inputStack);
            ItemStack outputStack = recipe.getOutput();
            outputItem(outputStack);

            if (recipe.getBonus() != null) {
                ItemStack bonus = Utilities.copyStackWithSize(recipe.getBonus(), 0);
                if (getBellows() == 0) {
                    if (this.worldObj.rand.nextInt(4) == 0) bonus.stackSize += 1;
                } else for (int b = 0; b < getBellows(); b++)
                    if (this.worldObj.rand.nextFloat() < 0.44F) bonus.stackSize += 1;
                outputItem(bonus);
            }

            inputStack.stackSize -= (recipe.getInput() instanceof OreDictStack)
                    ? ((OreDictStack) recipe.getInput()).amount
                    : ((ItemStack) recipe.getInput()).stackSize;
            if (inputStack.stackSize > 0) inputs.set(0, inputStack);
            else inputs.remove(0);
            cooking = false;
            this.specialFuel = false;
        } else {
            ItemStack inputStack = inputs.get(0);
            InfernalBlastfurnaceRecipe recipe = InfernalBlastfurnaceRecipe.getRecipeForInput(inputStack);
            if (recipe != null) {
                this.recipeTime = recipe.getSmeltingTime();
                this.processTick = calculateTime();
                if (recipe.isSpecial()) this.specialFuel = true;
            } else inputs.remove(0);
            cooking = true;
        }

        if (cooking != this.active) {
            this.active = cooking;
            this.worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 3, specialFuel ? 1 : 0);
            this.worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 4, active ? 1 : 0);
        }
    }

    void storeFuel() {
        for (int x = this.xCoord - 5; x < this.xCoord + 5; x++) {
            for (int y = this.yCoord - 5; y < this.yCoord + 5; y++) {
                for (int z = this.zCoord - 5; z < this.zCoord + 5; z++) {
                    TileEntity tile = this.worldObj.getTileEntity(x, y, z);
                    if (tile instanceof IAspectSource as) {
                        if (as.takeFromContainer(Aspect.FIRE, 1)) {
                            PacketHandler.INSTANCE.sendToAllAround(
                                    new PacketFXEssentiaSource(
                                            this.xCoord,
                                            this.yCoord,
                                            this.zCoord,
                                            (byte) (this.xCoord - x),
                                            (byte) (this.yCoord - y),
                                            (byte) (this.zCoord - z),
                                            Aspect.FIRE.getColor()),
                                    new NetworkRegistry.TargetPoint(
                                            getWorldObj().provider.dimensionId,
                                            this.xCoord,
                                            this.yCoord,
                                            this.zCoord,
                                            32.0D));
                            this.fuel += 1;
                        }
                    }
                }
            }
        }
    }

    int calculateTime() {
        return Math.max(1, recipeTime / (speedupTick > 0 ? 2 : 1) - getBellows() * 40);
    }

    void outputItem(ItemStack item) {
        TileEntity inventory = this.worldObj
                .getTileEntity(this.xCoord + facing.offsetX * 2, this.yCoord + 1, this.zCoord + facing.offsetZ * 2);
        if (inventory instanceof IInventory) {
            item = InventoryUtils.placeItemStackIntoInventory(
                    item,
                    (IInventory) inventory,
                    this.facing.getOpposite().ordinal(),
                    true);
        }
        if (item != null) {
            EntityItem ei = new EntityItem(
                    this.worldObj,
                    this.xCoord + .5D + this.facing.offsetX * 1.66D,
                    this.yCoord + 1.4D,
                    this.zCoord + .5D + this.facing.offsetZ * 1.66D,
                    item.copy());
            ei.motionX = (0.075F * this.facing.offsetX);
            ei.motionY = 0.025000000372529D;
            ei.motionZ = (0.075F * this.facing.offsetZ);
            this.worldObj.spawnEntityInWorld(ei);
        }
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 3, 0);
    }

    int getBellows() {
        int bellows = 0;
        for (ForgeDirection fd : ForgeDirection.VALID_DIRECTIONS)
            if (!fd.equals(ForgeDirection.UP) && !fd.equals(ForgeDirection.DOWN)) {
                int bx = xCoord + fd.offsetX * 2;
                int by = yCoord + fd.offsetY * 2;
                int bz = zCoord + fd.offsetZ * 2;
                if (worldObj.getTileEntity(bx, by, bz) instanceof TileBellows
                        && (((TileBellows) worldObj.getTileEntity(bx, by, bz)).orientation
                                == fd.getOpposite().ordinal())
                        && !worldObj.isBlockIndirectlyGettingPowered(bx, by, bz))
                    bellows++;
            }
        return bellows;
    }

    @Override
    public void readCustomNBT(NBTTagCompound tags) {
        position = tags.getByte("position");
        fuel = tags.getInteger("fuel");
        speedupTick = tags.getInteger("speedupTick");
        processTick = tags.getInteger("processTick");
        recipeTime = tags.getInteger("recipeTime");
        masterPos = tags.getIntArray("masterPos");
        facing = ForgeDirection.getOrientation(tags.getInteger("facing"));
        active = tags.getBoolean("active");
        specialFuel = tags.getBoolean("specialFuel");
        tick = tags.getInteger("tick");

        NBTTagList invList = tags.getTagList("inputs", 10);
        inputs.clear();
        for (int i = 0; i < invList.tagCount(); i++)
            inputs.add(ItemStack.loadItemStackFromNBT(invList.getCompoundTagAt(i)));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tags) {
        tags.setByte("position", position);
        tags.setInteger("fuel", fuel);
        tags.setInteger("speedupTick", speedupTick);
        tags.setInteger("processTick", processTick);
        tags.setInteger("recipeTime", recipeTime);
        tags.setIntArray("masterPos", masterPos);
        tags.setInteger("facing", facing.ordinal());
        tags.setInteger("tick", tick);
        tags.setBoolean("active", active);
        tags.setBoolean("specialFuel", specialFuel);

        NBTTagList invList = new NBTTagList();
        for (ItemStack s : inputs) invList.appendTag(s.writeToNBT(new NBTTagCompound()));
        tags.setTag("inputs", invList);
    }

    public boolean addStackToInputs(ItemStack stack) {
        for (int i = 0; i < inputs.size(); i++) if (this.inputs.get(i) != null && this.inputs.get(i).isItemEqual(stack)
                && (this.inputs.get(i).stackSize + stack.stackSize <= stack.getMaxStackSize())) {
                    this.inputs.get(i).stackSize += stack.stackSize;
                    return true;
                }
        this.inputs.add(stack);
        return true;
    }

    @Override
    public boolean isConnectable(ForgeDirection fd) {
        // return
        // (position==1&&fd==ForgeDirection.NORTH)||(position==3&&fd==ForgeDirection.WEST)||(position==5&&fd==ForgeDirection.EAST)||(position==7&&fd==ForgeDirection.SOUTH);
        return false;
    }

    @Override
    public boolean canInputFrom(ForgeDirection fd) {
        return position == 1 || position == 3 || position == 4 || position == 5 || position == 7;
    }

    @Override
    public int getSuctionAmount(ForgeDirection fd) {
        if (masterPos != null
                && worldObj.getTileEntity(masterPos[0], masterPos[1], masterPos[2]) instanceof TileEntityBlastfurnace
                && ((TileEntityBlastfurnace) worldObj
                        .getTileEntity(masterPos[0], masterPos[1], masterPos[2])).speedupTick < 40)
            return 128;
        return 0;
    }

    @Override
    public Aspect getSuctionType(ForgeDirection fd) {
        return Aspect.FIRE;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public boolean canOutputTo(ForgeDirection fd) {
        return false;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection fd) {
        TileEntity te = worldObj.getTileEntity(masterPos[0], masterPos[1], masterPos[2]);
        if (te instanceof TileEntityBlastfurnace blastfurnace) return blastfurnace.fuel;
        return 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection fd) {
        return Aspect.FIRE;
    }

    @Override
    public boolean renderExtendedTube() {
        return false;
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {}

    @Override
    public int addEssentia(Aspect arg0, int arg1, ForgeDirection arg2) {
        return 0;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection fd) {
        return 0;
    }

    @Override
    public boolean receiveClientEvent(int eventNum, int arg) {
        if (eventNum == 3) {
            this.specialFuel = arg == 1;
            worldObj.markBlockRangeForRenderUpdate(xCoord - 1, yCoord, zCoord - 1, xCoord + 1, yCoord + 2, zCoord + 1);
            for (int i = 0; i < 5; i++) {
                float xx = xCoord + .5f + facing.offsetX * 1.66f + worldObj.rand.nextFloat() * .3f;
                float zz = zCoord + .5f + facing.offsetZ * 1.66f + worldObj.rand.nextFloat() * .3f;
                float mx = facing.offsetX != 0 ? (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * .5f
                        : facing.offsetX * worldObj.rand.nextFloat();
                float mz = facing.offsetZ != 0 ? (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * .5f
                        : facing.offsetZ * worldObj.rand.nextFloat();
                worldObj.spawnParticle(
                        "lava",
                        xx,
                        yCoord + 0.9,
                        zz,
                        0.15 * mx,
                        .2f * worldObj.rand.nextFloat(),
                        0.15 * mz);
            }
            worldObj.playSound(
                    xCoord + .5f + facing.offsetX * 1.66f,
                    yCoord + 1.3f,
                    zCoord + .5f + facing.offsetZ * 1.66f,
                    "liquid.lavapop",
                    0.1f,
                    1f + worldObj.rand.nextFloat() * .8f,
                    false);
            return true;
        }

        if (eventNum == 4) {
            this.active = arg == 1;
            worldObj.markBlockRangeForRenderUpdate(xCoord - 1, yCoord, zCoord - 1, xCoord + 1, yCoord + 2, zCoord + 1);
            return true;
        }

        if (eventNum == 5) {
            for (int i = 0; i < 3; i++) {
                worldObj.spawnParticle(
                        "lava",
                        xCoord + 0.5 + worldObj.rand.nextFloat() * 0.3,
                        yCoord + 0.9,
                        zCoord + 0.5 + worldObj.rand.nextFloat() * .3,
                        (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.5 * 0.15,
                        0.2,
                        (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.5 * 0.15);
            }

            worldObj.playSound(
                    xCoord + .5f,
                    yCoord + 2.5f,
                    zCoord + .5f,
                    "random.fizz",
                    0.5f,
                    2.6f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * .8f,
                    false);
            return true;
        }
        return false;
    }

    public static IIcon icon_bricks;
    public static IIcon[] icon_sideBottom;
    public static IIcon[] icon_sideTop;
    public static IIcon[] icon_cornerBottomL;
    public static IIcon[] icon_cornerBottomR;
    public static IIcon[] icon_cornerTopL;
    public static IIcon[] icon_cornerTopR;
    public static IIcon icon_bottom;
    public static IIcon[] icon_bottomTBLR;
    public static IIcon icon_internal;
    public static IIcon icon_lava;

    public IIcon getTexture(int side) {

        int i = (masterPos == null || masterPos.length < 3) ? 0
                : (worldObj.getTileEntity(
                        masterPos[0],
                        masterPos[1],
                        masterPos[2]) instanceof TileEntityBlastfurnace blastfurnace && blastfurnace.active)
                                ? (blastfurnace.specialFuel ? 2 : 1)
                                : 0;
        return switch (position) {
            case 0 -> side == 2 ? icon_cornerBottomR[i]
                    : side == 4 ? icon_cornerBottomL[i] : side == 0 ? icon_cornerTopL[0] : icon_bricks;
            case 1 -> side == 2 ? icon_sideBottom[i] : side == 0 ? icon_bottomTBLR[0] : icon_bricks;
            case 2 -> side == 2 ? icon_cornerBottomL[i]
                    : side == 5 ? icon_cornerBottomR[i] : side == 0 ? icon_cornerTopR[0] : icon_bricks;
            case 3 -> side == 4 ? icon_sideBottom[i] : side == 0 ? icon_bottomTBLR[2] : icon_bricks;
            case 4 -> icon_bottom;
            case 5 -> side == 5 ? icon_sideBottom[i] : side == 0 ? icon_bottomTBLR[3] : icon_bricks;
            case 6 -> side == 3 ? icon_cornerBottomL[i]
                    : side == 4 ? icon_cornerBottomR[i] : side == 0 ? icon_cornerBottomL[0] : icon_bricks;
            case 7 -> side == 3 ? icon_sideBottom[i] : side == 0 ? icon_bottomTBLR[1] : icon_bricks;
            case 8 -> side == 3 ? icon_cornerBottomR[i]
                    : side == 5 ? icon_cornerBottomL[i] : side == 0 ? icon_cornerBottomR[0] : icon_bricks;
            case 9 -> side == 2 ? icon_cornerTopR[i] : icon_cornerTopL[i];
            case 10 -> side == 2 ? icon_sideTop[i] : i == 1 ? icon_internal : icon_bricks;
            case 11 -> side == 2 ? icon_cornerTopL[i] : icon_cornerTopR[i];
            case 12 -> side == 4 ? icon_sideTop[i] : i == 1 ? icon_internal : icon_bricks;
            case 14 -> side == 5 ? icon_sideTop[i] : i == 1 ? icon_internal : icon_bricks;
            case 15 -> side == 3 ? icon_cornerTopL[i] : icon_cornerTopR[i];
            case 16 -> side == 3 ? icon_sideTop[i] : i == 1 ? icon_internal : icon_bricks;
            case 17 -> side == 3 ? icon_cornerTopR[i] : icon_cornerTopL[i];
            case 22 -> icon_lava;
            default -> icon_bricks;
        };
    }
}
