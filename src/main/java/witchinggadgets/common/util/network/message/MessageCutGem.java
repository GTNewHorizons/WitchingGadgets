package witchinggadgets.common.util.network.message;

import net.minecraft.entity.player.EntityPlayerMP;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import witchinggadgets.common.gui.ContainerCuttingTable;

public class MessageCutGem implements IMessage {

    int windowId;
    byte targetGemCut;

    public MessageCutGem() {}

    public MessageCutGem(int windowId, byte targetGemCut) {
        this.windowId = windowId;
        this.targetGemCut = targetGemCut;
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        this.windowId = buffer.readInt();
        this.targetGemCut = buffer.readByte();
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(windowId);
        buffer.writeByte(targetGemCut);
    }

    public static class HandlerServer implements IMessageHandler<MessageCutGem, IMessage> {

        @Override
        public IMessage onMessage(MessageCutGem message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            if (player == null) return null;
            if (player.openContainer instanceof ContainerCuttingTable cuttingTable
                    && cuttingTable.windowId == message.windowId) {
                cuttingTable.setGemCut(message.targetGemCut);
            }
            return null;
        }
    }

}
