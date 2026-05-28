package witchinggadgets.common.util.network.message;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import witchinggadgets.common.blocks.tiles.TileEntityCuttingTable;

public class MessageCutGem implements IMessage {

    int x;
    int y;
    int z;
    byte targetGemCut;

    public MessageCutGem() {}

    public MessageCutGem(TileEntityCuttingTable te) {
        this.x = te.xCoord;
        this.y = te.yCoord;
        this.z = te.zCoord;
        this.targetGemCut = te.targetGemCut;
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.targetGemCut = buffer.readByte();
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        buffer.writeByte(targetGemCut);
    }

    public static class HandlerServer implements IMessageHandler<MessageCutGem, IMessage> {

        @Override
        public IMessage onMessage(MessageCutGem message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            if (player == null) return null;
            World world = player.worldObj;
            if (world != null
                    && world.getTileEntity(message.x, message.y, message.z) instanceof TileEntityCuttingTable tile) {
                if (tile.isUseableByPlayer(player)) {
                    tile.targetGemCut = message.targetGemCut;
                }
            }
            return null;
        }
    }

}
