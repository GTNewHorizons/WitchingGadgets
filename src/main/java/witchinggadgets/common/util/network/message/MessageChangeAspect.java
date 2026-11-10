package witchinggadgets.common.util.network.message;

import net.minecraft.entity.player.EntityPlayerMP;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import witchinggadgets.common.gui.ContainerLabelLibrary;

public class MessageChangeAspect implements IMessage {

    int windowId;
    Aspect aspect;

    public MessageChangeAspect() {}

    public MessageChangeAspect(int windowId, Aspect aspect) {
        this.windowId = windowId;
        this.aspect = aspect;
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        this.windowId = buffer.readInt();
        if (buffer.readBoolean()) {
            this.aspect = Aspect.getAspect(ByteBufUtils.readUTF8String(buffer));
        }
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(windowId);
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
            if (player.openContainer instanceof ContainerLabelLibrary labelLibrary
                    && labelLibrary.windowId == message.windowId) {
                if (ThaumcraftApiHelper.hasDiscoveredAspect(player.getCommandSenderName(), message.aspect)) {
                    labelLibrary.setAspect(message.aspect);
                }
            }
            return null;
        }
    }

}
