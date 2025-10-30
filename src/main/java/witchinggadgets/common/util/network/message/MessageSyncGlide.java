package witchinggadgets.common.util.network.message;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import witchinggadgets.common.items.baubles.ItemCloak;

public class MessageSyncGlide implements IMessage {

    int slotId;
    boolean glide;

    public MessageSyncGlide() {}

    public MessageSyncGlide(int slotId, boolean glide) {
        this.slotId = slotId;
        this.glide = glide;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.slotId);
        buf.writeBoolean(this.glide);
    }

    public void fromBytes(ByteBuf buf) {
        this.slotId = buf.readInt();
        this.glide = buf.readBoolean();
    }

    public static class HandlerServer implements IMessageHandler<MessageSyncGlide, IMessage> {

        public HandlerServer() {}

        public IMessage onMessage(MessageSyncGlide message, MessageContext ctx) {
            EntityPlayer ent = ctx.getServerHandler().playerEntity;
            ItemStack stack = BaublesApi.getBaubles(ent).getStackInSlot(message.slotId);
            // Just making sure the player is indeed wearing the raven cloak or kama there
            // The message deliberately sends state rather than simply toggling state
            // so that if this fails for any reason, state will re-sync on next activation
            if (stack != null && stack.getItem() instanceof ItemCloak
                    && stack.getItemDamage() < ItemCloak.subNames.length
                    && ItemCloak.subNames[stack.getItemDamage()].equals("raven")
                    && stack.hasTagCompound()) {
                // This if/else shouldn't actually be needed, but I kept it from the client-side code
                // Preferably the same methods should run on the client and server to be sure
                // If this elseif is refactored into an else, same should be done in the client code
                if (stack.getTagCompound().getBoolean("noGlide")) {
                    stack.getTagCompound().setBoolean("noGlide", false);
                } else if (!stack.getTagCompound().getBoolean("noGlide")) {
                    stack.getTagCompound().setBoolean("noGlide", true);
                }
            }
            return null;
        }
    }
}
