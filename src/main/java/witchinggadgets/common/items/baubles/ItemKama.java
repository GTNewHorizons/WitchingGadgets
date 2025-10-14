package witchinggadgets.common.items.baubles;

import static witchinggadgets.common.util.WGKeyHandler.activateKey;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import baubles.common.container.InventoryBaubles;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import witchinggadgets.client.render.ModelKama;
import witchinggadgets.common.util.Lib;

public class ItemKama extends ItemCloak implements IBaubleExpanded {

    IIcon overlay;

    public ItemKama() {
        super();
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        // 13116: Synchronize raven kama for client-authoritative player movement
        if (!player.isSneaking()) {
            BaubleItemHelper.onBaubleRightClick(stack, world, player);
        }
        if (subNames[stack.getItemDamage()].equals("raven") && !player.worldObj.isRemote) {
            InventoryBaubles baubles = (InventoryBaubles) BaublesApi.getBaubles(player);
            baubles.syncSlotToClients(3);
        }
        return super.onItemRightClick(stack, world, player);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("witchinggadgets:kama");
        this.iconRaven = iconRegister.registerIcon("witchinggadgets:kama_raven");
        this.iconWolf = iconRegister.registerIcon("witchinggadgets:kama_wolf");

        this.overlay = iconRegister.registerIcon("witchinggadgets:kama_overlay");
    }

    @Override
    public IIcon getIconFromDamageForRenderPass(int meta, int pass) {
        if (pass == 1) return overlay;
        return super.getIconFromDamageForRenderPass(meta, pass);
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return getIconFromDamageForRenderPass(stack.getItemDamage(), pass);
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        if (pass == 1) return 0xffffff;
        return super.getColorFromItemStack(stack, pass);
    }

    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return new ModelKama(getColor(itemStack));
    }

    @Override
    public BaubleType getBaubleType(ItemStack aStack) {
        return BaubleType.BELT;
    }

    @Override
    public String[] getBaubleTypes(ItemStack aStack) {
        return new String[] { BaubleExpandedSlots.beltType };
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        if (player.worldObj.isRemote) {
            GameSettings keybind = Minecraft.getMinecraft().gameSettings;
            BaubleItemHelper.addSlotInformation(list, getBaubleTypes(stack));
            list.add(
                    StatCollector.translateToLocal(Lib.DESCRIPTION + "enableCloak").replaceAll(
                            "%s1",
                            StatCollector.translateToLocalFormatted(
                                    GameSettings.getKeyDisplayString(activateKey.getKeyCode()))));
            if (stack.hasTagCompound() && stack.getTagCompound().getBoolean("noGlide")) {
                list.add(StatCollector.translateToLocal(Lib.DESCRIPTION + "noGlide"));
            }
            if (stack.hasTagCompound() && !stack.getTagCompound().getBoolean("noGlide")) {
                list.add(StatCollector.translateToLocal(Lib.DESCRIPTION + "glide"));
            }

            if (Loader.isModLoaded("Botania")) {
                ItemStack cosmetic = getCosmeticItem(stack);
                if (cosmetic != null) list.add(
                        String.format(
                                StatCollector.translateToLocal("botaniamisc.hasCosmetic"),
                                cosmetic.getDisplayName()).replaceAll("&", "\u00a7"));
            }
        }
    }

    @Override
    public boolean canEquip(ItemStack stack, EntityLivingBase living) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack stack, EntityLivingBase living) {
        return true;
    }
}
