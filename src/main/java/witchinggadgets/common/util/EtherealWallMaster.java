package witchinggadgets.common.util;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.tileentity.TileEntity;

import witchinggadgets.common.blocks.tiles.TileEntityEtherealWall;

public class EtherealWallMaster {

    private ObjectOpenHashSet<TileEntityEtherealWall> tileMap;

    public EtherealWallMaster() {
        this.tileMap = new ObjectOpenHashSet<>();
    }

    public boolean isAnyTileInNetPowered() {
        for (TileEntityEtherealWall tile : tileMap)
            if (tile.getWorldObj().isBlockIndirectlyGettingPowered(tile.xCoord, tile.yCoord, tile.zCoord)) return true;
        return false;
    }

    public boolean addTileToNet(TileEntityEtherealWall tile) {
        if (this.tileMap.contains(tile)) return false;
        this.tileMap.add(tile);
        tile.master = this;
        return true;
    }

    public boolean removeTileFromNet(TileEntityEtherealWall tile) {
        this.tileMap.remove(tile);
        tile.master = null;
        return true;
    }

    /**
     * Disbands Net and sets every Tile's master to null. Allows Tiles to form new nets. Used to allow an Net to be
     * split.
     */
    public void freeSlaves() {
        for (TileEntityEtherealWall tile : tileMap) {
            tile.master = null;
        }
        this.tileMap = new ObjectOpenHashSet<>();
    }

    public TileEntityEtherealWall[] sortTilesByDistanceTo(int x, int y, int z) {
        TileEntityEtherealWall[] result = new TileEntityEtherealWall[tileMap.size()];
        int counter = 0;
        for (TileEntityEtherealWall tile : tileMap) {
            result[counter] = tile;
            counter++;
        }
        return result;
    }

    private boolean areTilesAdjacent(TileEntity par1, TileEntity par2) {
        boolean sameX = par1.xCoord == par2.xCoord;
        boolean sameY = par1.yCoord == par2.yCoord;
        boolean sameZ = par1.zCoord == par2.zCoord;
        if (sameX && sameY) {
            if (Math.abs(par1.zCoord - par2.zCoord) == 1) return true;
            return false;
        }
        if (sameZ && sameY) {
            if (Math.abs(par1.xCoord - par2.xCoord) == 1) return true;
            return false;
        }
        if (sameX && sameZ) {
            if (Math.abs(par1.yCoord - par2.yCoord) == 1) return true;
            return false;
        }
        return false;
    }

    public void integrateOtherNet(EtherealWallMaster net) {
        this.tileMap.addAll(net.tileMap);
    }
}
