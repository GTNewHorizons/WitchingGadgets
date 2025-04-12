package witchinggadgets.common.util.network.message;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import witchinggadgets.WitchingGadgets;

public class MessageOpenCloak implements IMessage {

    int dim;
    int playerid;
    int guiid;

    public MessageOpenCloak() {}

    public MessageOpenCloak(EntityPlayer player, int guiid) {
        this.dim = player.worldObj.provider.dimensionId;
        this.playerid = player.getEntityId();
        this.guiid = guiid;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.dim);
        buf.writeInt(this.playerid);
        buf.writeInt(this.guiid);
    }

    public void fromBytes(ByteBuf buf) {
        this.dim = buf.readInt();
        this.playerid = buf.readInt();
        this.guiid = buf.readInt();
    }

    public static class HandlerClient implements IMessageHandler<MessageOpenCloak, IMessage> {

        @Override
        public IMessage onMessage(MessageOpenCloak message, MessageContext ctx) {
            Minecraft.getMinecraft().thePlayer.openGui(
                    WitchingGadgets.instance,
                    message.guiid,
                    Minecraft.getMinecraft().thePlayer.worldObj,
                    MathHelper.floor_double(Minecraft.getMinecraft().thePlayer.posX),
                    MathHelper.floor_double(Minecraft.getMinecraft().thePlayer.posY),
                    MathHelper.floor_double(Minecraft.getMinecraft().thePlayer.posZ));
            return null;
        }
    }

    public static class HandlerServer implements IMessageHandler<MessageOpenCloak, IMessage> {

        public HandlerServer() {}

        public IMessage onMessage(MessageOpenCloak message, MessageContext ctx) {
            World world = DimensionManager.getWorld(message.dim);
            if (world == null) {
                return null;
            } else {
                EntityPlayer ent = ctx.getServerHandler().playerEntity;
                if (ent == null) {
                    return null;
                } else {
                    boolean hasServerGui = WitchingGadgets.proxy.getServerGuiElement(
                            message.guiid,
                            ent,
                            world,
                            (int) ent.posX,
                            (int) ent.posY,
                            (int) ent.posZ) != null;
                    ent.openGui(
                            WitchingGadgets.instance,
                            message.guiid,
                            ent.worldObj,
                            MathHelper.floor_double(ent.posX),
                            MathHelper.floor_double(ent.posY),
                            MathHelper.floor_double(ent.posZ));
                    return hasServerGui ? null : new MessageOpenCloak(ent, message.guiid);
                }
            }
        }
    }
}
