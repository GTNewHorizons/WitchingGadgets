package witchinggadgets.common.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.DarkAspects;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.api.IMetaEnum;
import witchinggadgets.api.ITerraformFocus;
import witchinggadgets.client.render.BlockRenderMetalDevice;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.blocks.tiles.TileEntityEssentiaPump;
import witchinggadgets.common.blocks.tiles.TileEntityTerraformFocus;
import witchinggadgets.common.blocks.tiles.TileEntityTerraformer;

public class BlockWGMetalDevice extends BlockContainer implements ITerraformFocus {

    public enum SubID implements IMetaEnum {

        ESSENTIA_PUMP(0, "essentiaPump", null, null, null),
        VOIDMETAL_BLOCK(1, "voidmetalBlock", null, null, null),
        TERRAFORMER(2, "terraformer", null, null, null),
        TF_FOCUS_PLAINS(3, "tfFocusPlains", BiomeGenBase.plains, new ItemStack(Blocks.grass), Aspect.PLANT),
        TF_FOCUS_TAIGA(4, "tfFocusColdTaiga", BiomeGenBase.coldTaiga, new ItemStack(Blocks.ice), Aspect.COLD),
        TF_FOCUS_DESERT(5, "tfFocusDesert", BiomeGenBase.desert, new ItemStack(Blocks.sand), Aspect.FIRE),
        TF_FOCUS_JUNGLE(6, "tfFocusJungle", BiomeGenBase.jungle, new ItemStack(Blocks.log, 1, 3), Aspect.TREE),
        TF_FOCUS_HELL(7, "tfFocusHell", BiomeGenBase.hell, new ItemStack(Blocks.nether_brick), Aspect.FIRE),
        TF_FOCUS_TAINT(8, "tfFocusTaint", ThaumcraftWorldGenerator.biomeTaint, new ItemStack(ConfigBlocks.blockTaint),
                Aspect.TAINT),
        TF_FOCUS_MUSHROOM(9, "tfFocusMushroom", BiomeGenBase.mushroomIsland, new ItemStack(Blocks.mycelium),
                Aspect.SLIME),
        TF_FOCUS_RIVER(10, "tfFocusRiver", BiomeGenBase.river, new ItemStack(Blocks.lapis_block), Aspect.WATER),
        TF_FOCUS_OCEAN(11, "tfFocusOcean", BiomeGenBase.ocean, new ItemStack(Blocks.lapis_block), Aspect.WATER),
        TF_FOCUS_END(12, "tfFocusEnd", BiomeGenBase.sky, new ItemStack(Blocks.end_stone), Aspect.ELDRITCH),
        TF_FOCUS_MAGIC(13, "tfFocusMagic", ThaumcraftWorldGenerator.biomeMagicalForest,
                new ItemStack(ConfigBlocks.blockMagicalLog, 1, 1), Aspect.HEAL);

        final int meta;
        final String name;
        final BiomeGenBase biome;
        final ItemStack display;
        final Aspect aspect;

        SubID(int meta, String name, BiomeGenBase biome, ItemStack display, Aspect aspect) {
            this.meta = meta;
            this.name = name;
            this.biome = biome;
            this.display = display;
            this.aspect = aspect;
        }

        public static boolean isTFFocus(int meta) {
            return isTFFocus(SubID.fromMeta(meta));
        }

        public static boolean isTFFocus(SubID sub) {
            return sub != null && sub.biome != null;
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

    public BlockWGMetalDevice() {
        super(Material.iron);
        this.setHardness(4F);
        this.setResistance(15);
        setCreativeTab(WitchingGadgets.tabWG);
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
        return worldObj.getBlockMetadata(x, y, z) == SubID.VOIDMETAL_BLOCK.meta;
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        for (SubID id : SubID.values()) {
            if (id == SubID.VOIDMETAL_BLOCK) {
                icons[id.meta] = iconRegister.registerIcon("thaumcraft:metalbase");
            } else {
                icons[id.meta] = iconRegister.registerIcon("witchinggadgets:" + id.name);
            }
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (meta < icons.length) return icons[meta];
        return null;
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.getBlockMetadata(x, y, z);
        return getIcon(side, meta);
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        return super.shouldSideBeRendered(iBlockAccess, x, y, z, side);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        if (te instanceof TileEntityEssentiaPump) {
            ForgeDirection fd = ((TileEntityEssentiaPump) te).facing;
            this.setBlockBounds(
                    fd == ForgeDirection.EAST ? .25f : 0,
                    fd == ForgeDirection.UP ? .25f : 0,
                    fd == ForgeDirection.SOUTH ? .25f : 0,
                    fd == ForgeDirection.WEST ? .75f : 1,
                    fd == ForgeDirection.DOWN ? .75f : 1,
                    fd == ForgeDirection.SOUTH ? .75f : 1);
        } else if (meta == SubID.VOIDMETAL_BLOCK.meta || meta == SubID.TERRAFORMER.meta)
            this.setBlockBounds(0, 0, 0, 1, 1, 1);
        else this.setBlockBounds(.125f, 0, .125f, .875f, .75f, .875f);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        SubID sub = SubID.fromMeta(metadata);
        switch (sub) {
            case ESSENTIA_PUMP:
                return new TileEntityEssentiaPump();
            case VOIDMETAL_BLOCK:
                return null;
            case TERRAFORMER:
                return new TileEntityTerraformer();
            default:
                return SubID.isTFFocus(sub) ? new TileEntityTerraformFocus() : null;
        }
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (SubID id : SubID.values()) {
            list.add(new ItemStack(item, 1, id.meta));
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack stack) {
        int playerViewQuarter = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int meta = world.getBlockMetadata(x, y, z);
        int f = playerViewQuarter == 0 ? 2 : playerViewQuarter == 1 ? 5 : playerViewQuarter == 2 ? 3 : 4;
        if (meta == SubID.ESSENTIA_PUMP.getMeta())
            ((TileEntityEssentiaPump) world.getTileEntity(x, y, z)).facing = ForgeDirection.getOrientation(f)
                    .getOpposite();
    }

    @Override
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {}

    @Override
    public int getRenderType() {
        return BlockRenderMetalDevice.renderID;
    }

    @Override
    public Aspect requiredAspect(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        return requiredAspect(meta);
    }

    @Override
    public Aspect requiredAspect(int meta) {
        SubID id = SubID.fromMeta(meta);
        if (id == null) return null;

        if (id == SubID.TF_FOCUS_HELL) {
            return WGModCompat.loaded_ForbiddenMagic ? DarkAspects.NETHER : Aspect.FIRE;
        }

        return id.aspect;
    }

    @Override
    public BiomeGenBase getCreatedBiome(World world, int x, int y, int z) {
        SubID id = SubID.fromMeta(world.getBlockMetadata(x, y, z));
        return id != null ? id.biome : null;
    }

    @Override
    public ItemStack getDisplayedBlock(World world, int x, int y, int z) {
        SubID id = SubID.fromMeta(world.getBlockMetadata(x, y, z));
        return id != null ? id.display : null;
    }
}
