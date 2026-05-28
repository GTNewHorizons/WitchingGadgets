package witchinggadgets.common.util.network.message;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import thaumcraft.api.aspects.Aspect;
import witchinggadgets.common.blocks.tiles.TileEntityLabelLibrary;

public class MessageChangeAspect implements IMessage {

    int x;
    int y;
    int z;
    Aspect aspect;

    public MessageChangeAspect() {}

    public MessageChangeAspect(TileEntityLabelLibrary te) {
        this.x = te.xCoord;
        this.y = te.yCoord;
        this.z = te.zCoord;
        this.aspect = te.aspect;
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        if (buffer.readBoolean()) {
            this.aspect = Aspect.getAspect(ByteBufUtils.readUTF8String(buffer));
        }
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        buffer.writeBoolean(aspect != null);
        if (aspect != null) {
            ByteBufUtils.writeUTF8String(buffer, aspect.getTag());
        }
    }

    public static class HandlerServer implements IMessageHandler<MessageChangeAspect, IMessage> {

        @Override
        public IMessage onMessage(MessageChangeAspect message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            if (player == null) return null;
            World world = player.worldObj;
            if (world != null
                    && world.getTileEntity(message.x, message.y, message.z) instanceof TileEntityLabelLibrary tile) {
                if (tile.isUseableByPlayer(player)) {
                    tile.aspect = message.aspect;
                }
            }
            return null;
        }
    }

}
