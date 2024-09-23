package witchinggadgets.common.items.baubles;

import java.util.List;

import baubles.api.expanded.IBaubleExpanded;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
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
    public void activate(EntityPlayer player, ItemStack stack) {
        super.activate(player, stack);
        // 13116: Synchronize raven kama for client-authoritative player movement
        if (subNames[stack.getItemDamage()].equals("raven") && !player.worldObj.isRemote) {
            InventoryBaubles baubles = (InventoryBaubles) BaublesApi.getBaubles(player);
            baubles.syncSlotToClients(3);
        }
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
    public BaubleType getBaubleType(ItemStack stack) {
        return BaubleType.BELT;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List<String> list, boolean par4) {
        if (stack.hasTagCompound() && stack.getTagCompound().getBoolean("noGlide"))
            list.add(StatCollector.translateToLocal(Lib.DESCRIPTION + "noGlide"));
        list.add(StatCollector.translateToLocalFormatted(Lib.DESCRIPTION + "gearSlot.bauble." + getBaubleType(stack)));

        if (Loader.isModLoaded("Botania")) {
            ItemStack cosmetic = getCosmeticItem(stack);
            if (cosmetic != null) list.add(
                    String.format(StatCollector.translateToLocal("botaniamisc.hasCosmetic"), cosmetic.getDisplayName())
                            .replaceAll("&", "\u00a7"));
        }
    }

    @Override
    public void onEquipped(ItemStack stack, EntityLivingBase living) {
        if (living instanceof EntityPlayer) this.onItemEquipped((EntityPlayer) living, stack);
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase living) {
        if (living instanceof EntityPlayer) this.onItemUnequipped((EntityPlayer) living, stack);
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase living) {
        if (living instanceof EntityPlayer) this.onItemTicked((EntityPlayer) living, stack);
    }

    @Override
    public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1) {
        return true;
    }
}
