package witchinggadgets.common.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.ForgeHooks;

import baubles.api.BaublesApi;
import baubles.api.expanded.BaubleExpandedSlots;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.items.baubles.ItemMagicalBaubles;
import witchinggadgets.common.items.tools.ItemPrimordialGlove;
import witchinggadgets.common.util.network.message.MessagePrimordialGlove;

public class WGKeyHandler {

    public static KeyBinding thaumcraftFKey;
    public static KeyBinding jumpKey;
    public static KeyBinding activateKey;
    public boolean[] keyDown = { false, false, false };
    public static float gemRadial;
    public static boolean gemLock = false;
    private boolean isJumping = false;
    private int multiJumps = 0;

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == Side.SERVER) {
            return;
        }
        if (event.phase == TickEvent.Phase.START) {
            if (thaumcraftFKey == null) {
                for (KeyBinding kb : Minecraft.getMinecraft().gameSettings.keyBindings) {
                    if ("Thaumcraft".equals(kb.getKeyCategory())
                            && "Change Wand Focus".equals(kb.getKeyDescription())) {
                        thaumcraftFKey = kb;
                    }
                }
            }

            if (jumpKey == null) jumpKey = Minecraft.getMinecraft().gameSettings.keyBindJump;

            EntityPlayer player = event.player;
            if (FMLClientHandler.instance().getClient().inGameHasFocus) {
                if (jumpKey.getIsKeyPressed() && !keyDown[2] && Minecraft.getMinecraft().currentScreen == null) {
                    if (isJumping && multiJumps > 0) {
                        event.player.motionY = 0.42D;
                        event.player.fallDistance = 0;

                        if (event.player.isPotionActive(Potion.jump)) event.player.motionY += (float) (event.player
                                .getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F;
                        ForgeHooks.onLivingJump(event.player);
                        multiJumps--;
                    }
                    if (!isJumping) {
                        multiJumps = 0;
                        isJumping = event.player.isAirBorne;
                        ItemStack aItem = BaublesApi.getBaubles(player).getStackInSlot(
                                BaubleExpandedSlots.getIndexesOfAssignedSlotsOfType(BaubleExpandedSlots.charmType)[0]);
                        if (aItem != null && aItem.getItem() instanceof ItemMagicalBaubles
                                && aItem.getItemDamage() == 0)
                            multiJumps += 1;
                    }
                    keyDown[2] = true;
                } else if (keyDown[2]) keyDown[2] = false;
            }
            float step = WGConfig.radialSpeed;
            if (thaumcraftFKey != null && thaumcraftFKey.getIsKeyPressed() && !keyDown[1]) {
                if (player.isSneaking() && player.getCurrentEquippedItem() != null
                        && player.getCurrentEquippedItem().getItem() instanceof ItemPrimordialGlove)
                    WitchingGadgets.packetHandler.sendToServer(new MessagePrimordialGlove(player, (byte) 1, 0));
                else if (gemLock) {
                    gemLock = false;
                    keyDown[1] = true;
                } else if (FMLClientHandler.instance().getClient().inGameHasFocus
                        && player.getCurrentEquippedItem() != null
                        && player.getCurrentEquippedItem().getItem() instanceof ItemPrimordialGlove) {
                            if (gemRadial < 1) gemRadial += step;
                            if (gemRadial > 1) gemRadial = 1f;
                            if (gemRadial >= 1) {
                                gemLock = true;
                                keyDown[1] = true;
                            }
                        }
            } else {
                if (keyDown[1] && thaumcraftFKey != null && !thaumcraftFKey.isPressed()) keyDown[1] = false;
                if (!gemLock) {
                    if (gemRadial > 0) gemRadial -= step;
                    if (gemRadial < 0) gemRadial = 0f;
                }
            }
        }
        if (isJumping && event.player.onGround) {
            event.player.isAirBorne = false;
            isJumping = false;
        }
    }
}
