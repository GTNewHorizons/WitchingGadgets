package witchinggadgets.common.util;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
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

    public void integrateOtherNet(EtherealWallMaster net) {
        this.tileMap.addAll(net.tileMap);
        for (TileEntityEtherealWall other : net.tileMap) {
            other.master = this;
        }
    }
}
