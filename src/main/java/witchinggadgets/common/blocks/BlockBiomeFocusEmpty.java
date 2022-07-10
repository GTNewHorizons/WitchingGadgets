package witchinggadgets.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import thaumcraft.api.aspects.Aspect;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.api.ITerraformFocus;
import witchinggadgets.common.blocks.tiles.TileEntityTerraformFocus;

public class BlockBiomeFocusEmpty extends BlockContainer implements ITerraformFocus {

    private BiomeGenBase Saved = null;

    public BlockBiomeFocusEmpty() {
        super(Material.iron);
        this.setHardness(4F);
        this.setResistance(15);
        setCreativeTab(WitchingGadgets.tabWG);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconRegister.registerIcon("witchinggadgets:tfFocusEmpty");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityTerraformFocus();
    }

    @Override
    public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {
        if (this.Saved == null) this.Saved = p_149726_1_.getBiomeGenForCoords(p_149726_2_, p_149726_4_);
        super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
    }

    @Override
    public Aspect requiredAspect(int metadata) {
        if (Saved != null) {
            if (Saved.getTempCategory() == BiomeGenBase.TempCategory.COLD) return Aspect.COLD;
            if (Saved.getTempCategory() == BiomeGenBase.TempCategory.WARM) return Aspect.FIRE;
            if (Saved.getTempCategory() == BiomeGenBase.TempCategory.OCEAN) return Aspect.WATER;
            if (Saved.getTempCategory() == BiomeGenBase.TempCategory.MEDIUM) return Aspect.EARTH;
        }
        return null;
    }

    @Override
    public Aspect requiredAspect(World world, int x, int y, int z) {
        return requiredAspect(0);
    }

    @Override
    public BiomeGenBase getCreatedBiome(World world, int x, int y, int z) {
        return Saved;
    }

    @Override
    public ItemStack getDisplayedBlock(World world, int x, int y, int z) {
        return null;
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
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        this.setBlockBounds(.125f, 0, .125f, .875f, .75f, .875f);
    }
}
