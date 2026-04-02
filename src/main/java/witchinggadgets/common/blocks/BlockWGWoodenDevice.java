package witchinggadgets.common.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.api.IMetaEnum;
import witchinggadgets.client.gui.GuiCuttingTable;
import witchinggadgets.client.gui.GuiLabelLibrary;
import witchinggadgets.client.gui.GuiSpinningWheel;
import witchinggadgets.client.render.BlockRenderWoodenDevice;
import witchinggadgets.common.blocks.tiles.TileEntityCobbleGen;
import witchinggadgets.common.blocks.tiles.TileEntityCuttingTable;
import witchinggadgets.common.blocks.tiles.TileEntityIceGen;
import witchinggadgets.common.blocks.tiles.TileEntityLabelLibrary;
import witchinggadgets.common.blocks.tiles.TileEntitySaunaStove;
import witchinggadgets.common.blocks.tiles.TileEntitySnowGen;
import witchinggadgets.common.blocks.tiles.TileEntitySpinningWheel;

public class BlockWGWoodenDevice extends BlockContainer implements IWandable {

    public enum SubID implements IMetaEnum {

        SPINNING_WHEEL(0, "spinningWheel"),
        SNOW_GEN(1, "snowGen"),
        COBBLE_GEN(2, "cobbleGen"),
        CUTTING_TABLE(3, "cuttingTable"),
        SAUNA_STOVE(4, "saunaStove"),
        LABEL_LIBRARY(5, "labelLibrary"),
        ICE_GEN(6, "iceGen");

        final int meta;
        final String name;

        SubID(int meta, String name) {
            this.meta = meta;
            this.name = name;
        }

        private static final SubID[] LOOKUP = IMetaEnum.createLookup(values());

        @Override
        public int getMeta() {
            return meta;
        }

        public static SubID fromMeta(int meta) {
            return IMetaEnum.fromLookup(LOOKUP, meta);
        }
    }

    IIcon[] icons = new IIcon[SubID.values().length];
    IIcon saunaTop;

    public BlockWGWoodenDevice() {
        super(Material.wood);
        setCreativeTab(WitchingGadgets.tabWG);
        setHardness(2.5F);
        setResistance(10.0F);
        this.setTickRandomly(true);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        icons[0] = iconRegister.registerIcon("thaumcraft:woodplain");
        icons[4] = iconRegister.registerIcon("witchinggadgets:saunaStove_side");
        saunaTop = iconRegister.registerIcon("witchinggadgets:saunaStove_top");
        BlockRenderWoodenDevice.coal = iconRegister.registerIcon("witchinggadgets:saunaStove_coal");
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        return getIcon(side, world.getBlockMetadata(x, y, z));
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        if (metadata == SubID.SAUNA_STOVE.meta) return side == 1 ? saunaTop : icons[4];
        if (metadata == SubID.SNOW_GEN.meta) return icons[1];
        return icons[0];
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return BlockRenderWoodenDevice.renderID;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.getBlockMetadata(
                x - (side == 4 ? -1 : side == 5 ? 1 : 0),
                y - (side == 0 ? -1 : side == 1 ? 1 : 0),
                z - (side == 2 ? -1 : side == 3 ? 1 : 0));
        if (meta == SubID.CUTTING_TABLE.meta || meta == SubID.SAUNA_STOVE.meta) return true;
        return super.shouldSideBeRendered(world, x, y, z, side);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
        for (int i = 0; i < SubID.values().length; i++) list.add(new ItemStack(item, 1, i));
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what,
            float these, float are) {
        int meta = world.getBlockMetadata(x, y, z);
        SubID sub = SubID.fromMeta(meta);
        switch (sub) {
            case SPINNING_WHEEL -> {
                TileEntitySpinningWheel tile = (TileEntitySpinningWheel) world.getTileEntity(x, y, z);
                if (tile == null || player.isSneaking()) return false;
                player.openGui(WitchingGadgets.instance, GuiSpinningWheel.GUI_ID, world, x, y, z);
                return true;
            }
            case CUTTING_TABLE -> {
                if (!player.isSneaking()) {
                    player.openGui(WitchingGadgets.instance, GuiCuttingTable.GUI_ID, world, x, y, z);
                    return true;
                } else return false;
            }
            case SAUNA_STOVE -> {
                FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(player.inventory.getCurrentItem());
                if (fs == null || world.isRemote) return false;
                TileEntitySaunaStove tile = (TileEntitySaunaStove) world.getTileEntity(x, y, z);
                if (tile.tank.getFluidAmount() < tile.tank.getCapacity()
                        && (tile.tank.getFluid() == null || tile.tank.getFluid().isFluidEqual(fs))) {
                    tile.fill(
                            ForgeDirection.UNKNOWN,
                            FluidContainerRegistry.getFluidForFilledItem(player.inventory.getCurrentItem()),
                            true);
                    ItemStack emptyContainer = null;
                    FluidContainerRegistry.FluidContainerData[] fcs = FluidContainerRegistry
                            .getRegisteredFluidContainerData();
                    for (FluidContainerRegistry.FluidContainerData fcd : fcs)
                        if (fcd.filledContainer.isItemEqual(player.inventory.getCurrentItem()))
                            emptyContainer = fcd.emptyContainer.copy();

                    player.inventory.decrStackSize(player.inventory.currentItem, 1);
                    if (emptyContainer != null) {
                        boolean b = player.inventory.addItemStackToInventory(emptyContainer);
                        if (!b) player.dropPlayerItemWithRandomChoice(emptyContainer, false);
                    }

                    player.inventoryContainer.detectAndSendChanges();
                    world.markBlockForUpdate(x, y, z);
                    world.playSoundEffect(
                            x + 0.5D,
                            y + 0.5D,
                            z + 0.5D,
                            "game.neutral.swim",
                            0.33F,
                            1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F);
                    return true;
                }
                return false;
            }
            case LABEL_LIBRARY -> {
                if (!player.isSneaking()) {
                    if (!world.isRemote)
                        player.openGui(WitchingGadgets.instance, GuiLabelLibrary.GUI_ID, world, x, y, z);
                    return true;
                } else return false;
            }
            default -> {
                return false;
            }
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int x, int y, int z) {
        int meta = iBlockAccess.getBlockMetadata(x, y, z);
        if (meta == SubID.SPINNING_WHEEL.meta
                && iBlockAccess.getTileEntity(x, y, z) instanceof TileEntitySpinningWheel spinningWheel) {
            switch (spinningWheel.facing) {
                case 2:
                default:
                    this.setBlockBounds(0F, 0F, 0.3125F, 1F, 1.25F, 0.6875F);
                    break;
                case 4, 5:
                    this.setBlockBounds(0.3125F, 0F, 0F, 0.6875F, 1.25F, 1F);
                    break;
            }
        } else if (meta == SubID.CUTTING_TABLE.meta) this.setBlockBounds(0, 0, 0, 1, .875f, 1);
        else this.setBlockBounds(0, 0, 0, 1, 1, 1);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack stack) {
        int playerViewQuarter = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        TileEntity te = world.getTileEntity(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        SubID sub = SubID.fromMeta(meta);
        int f;
        switch (playerViewQuarter) {
            case 0 -> f = 2;
            case 1 -> f = 5;
            case 2 -> f = 3;
            default -> f = 4;
        }

        switch (sub) {
            case SPINNING_WHEEL -> ((TileEntitySpinningWheel) te).facing = f;
            case SNOW_GEN -> ((TileEntitySnowGen) te).facing = ForgeDirection.getOrientation(f);
            case COBBLE_GEN -> ((TileEntityCobbleGen) te).facing = ForgeDirection.getOrientation(f);
            case CUTTING_TABLE -> ((TileEntityCuttingTable) te).facing = f;
            case SAUNA_STOVE -> ((TileEntitySaunaStove) te).prepareAreaCheck();
            case LABEL_LIBRARY -> ((TileEntityLabelLibrary) te).facing = f;
            case ICE_GEN -> ((TileEntityIceGen) te).facing = ForgeDirection.getOrientation(f);
            default -> {} // fall through
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (world.getBlockMetadata(x, y, z) == SubID.SAUNA_STOVE.meta) return 8;
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (world.getTileEntity(x, y, z) instanceof TileEntitySaunaStove
                && ((TileEntitySaunaStove) world.getTileEntity(x, y, z)).tick > 0)
            if (world.rand.nextInt(9 - Thaumcraft.proxy.particleCount(2)) == 0) Thaumcraft.proxy.wispFX3(
                    world,
                    x + .5F,
                    y + .875F,
                    z + .5F,
                    x + .3F + world.rand.nextFloat() * 0.4F,
                    y + .5F,
                    z + .3F + world.rand.nextFloat() * 0.4F,
                    0.5F,
                    4,
                    true,
                    -0.025F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return switch (metadata) {
            case 0 -> new TileEntitySpinningWheel();
            case 1 -> new TileEntitySnowGen();
            case 2 -> new TileEntityCobbleGen();
            case 3 -> new TileEntityCuttingTable();
            case 4 -> new TileEntitySaunaStove();
            case 5 -> new TileEntityLabelLibrary();
            case 6 -> new TileEntityIceGen();
            default -> null;
        };
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return super.getDrops(world, x, y, z, metadata, fortune);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntitySpinningWheel tile) {
            dropInventory(world, (IInventory) tile, x, y, z, 4);
        } else if (te instanceof TileEntityCuttingTable tile) {
            dropInventory(world, (IInventory) tile, x, y, z, tile.getSizeInventory());
        } else if (te instanceof TileEntityLabelLibrary tile) {
            dropInventory(world, (IInventory) tile, x, y, z, tile.getSizeInventory());
        }
        super.breakBlock(world, x, y, z, par5, par6);
    }

    private void dropInventory(World world, IInventory inventory, int x, int y, int z, int invSize) {
        for (int i = 0; i < invSize; i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null) {
                float f = world.rand.nextFloat() * 0.8F + 0.1F;
                float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                EntityItem entityitem;
                for (float f2 = world.rand.nextFloat() * 0.8F + 0.1F; stack.stackSize > 0; world
                        .spawnEntityInWorld(entityitem)) {
                    int k1 = world.rand.nextInt(21) + 10;
                    if (k1 > stack.stackSize) k1 = stack.stackSize;
                    stack.stackSize -= k1;
                    entityitem = new EntityItem(
                            world,
                            x + f,
                            y + f1,
                            z + f2,
                            new ItemStack(stack.getItem(), k1, stack.getItemDamage()));
                    float f3 = 0.05F;
                    entityitem.motionX = (float) world.rand.nextGaussian() * f3;
                    entityitem.motionY = (float) world.rand.nextGaussian() * f3 + 0.2F;
                    entityitem.motionZ = (float) world.rand.nextGaussian() * f3;

                    if (stack.hasTagCompound()) {
                        entityitem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
                    }
                }
            }
        }
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side,
            int metadata) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te == null) return 0;
        SubID sub = SubID.fromMeta(metadata);
        if (sub == SubID.SNOW_GEN || sub == SubID.COBBLE_GEN || sub == SubID.ICE_GEN) {
            ForgeDirection dir = player.isSneaking() ? ForgeDirection.getOrientation(side).getOpposite()
                    : ForgeDirection.getOrientation(side);

            switch (sub) {
                case SNOW_GEN -> ((TileEntitySnowGen) te).facing = dir;
                case COBBLE_GEN -> ((TileEntityCobbleGen) te).facing = dir;
                case ICE_GEN -> ((TileEntityIceGen) te).facing = dir;
            }

            player.worldObj.playSound(
                    x + 0.5D,
                    y + 0.5D,
                    z + 0.5D,
                    "thaumcraft:tool",
                    0.3F,
                    1.9F + player.worldObj.rand.nextFloat() * 0.2F,
                    false);
            player.swingItem();
        }
        return 0;
    }

    @Override
    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        return null;
    }

    @Override
    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}

    @Override
    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
}
