package witchinggadgets.common.items.baubles;

import static witchinggadgets.common.util.Lib.Title;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import baubles.api.BaubleType;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.render.ModelMagicalBaubles;
import witchinggadgets.common.items.ItemInfusedGem;
import witchinggadgets.common.util.Lib;
import witchinggadgets.common.util.Utilities;

@Optional.Interface(iface = "vazkii.botania.api.item.ICosmeticAttachable", modid = "Botania")
public class ItemMagicalBaubles extends Item implements IBaubleExpanded, vazkii.botania.api.item.ICosmeticAttachable {

    // String[] subNames = {"ringSocketed_gold","ringSocketed_thaumium","ringSocketed_silver"};
    public static String[] subNames = { "charmDoublejump", "charmKnockback", "vambraceStrength", "vambraceHaste",
            "ringLuck", "titleCrimsonCult", "ringSniper", "charmEmpty", "vambraceEmpty" };
    IIcon[] icons = new IIcon[subNames.length];
    public static HashSet<String> bowSpeedPlayers = new HashSet<String>();

    public ItemMagicalBaubles() {
        super();
        maxStackSize = 1;
        setCreativeTab(WitchingGadgets.tabWG);
        setHasSubtypes(true);
    }

    @Override
    public boolean isItemTool(ItemStack stack) {
        return stack.stackSize == 1;
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        return super.getColorFromItemStack(stack, pass);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        BaubleItemHelper.onBaubleRightClick(stack, world, player);
        return super.onItemRightClick(stack, world, player);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        BaubleItemHelper.addSlotInformation(list, getBaubleTypes(stack));
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey(Title))
            list.add(StatCollector.translateToLocalFormatted(stack.getTagCompound().getString(Title)));

        if (Loader.isModLoaded("Botania")) {
            ItemStack cosmetic = getCosmeticItem(stack);
            if (cosmetic != null) list.add(
                    String.format(StatCollector.translateToLocal("botaniamisc.hasCosmetic"), cosmetic.getDisplayName())
                            .replaceAll("&", "\u00a7"));
        }
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        for (int i = 0; i < subNames.length; i++)
            this.icons[i] = iconRegister.registerIcon("witchinggadgets:bauble_" + subNames[i]);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "witchinggadgets:textures/models/magicalBaubles.png";
    }

    @Override
    public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, int slot) {
        return ModelMagicalBaubles.getModel(entity, stack);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata) {
        return icons[metadata];
    }

    @Override
    public IIcon getIconFromDamageForRenderPass(int meta, int pass) {
        return getIconFromDamage(meta);
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return getIconFromDamageForRenderPass(stack.getItemDamage(), pass);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.rare;
    }

    @Override
    public int getMetadata(int damageValue) {
        return damageValue;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> itemList) {
        for (int i = 0; i < subNames.length; i++) if (i == 5) {
            itemList.add(getItemWithTitle(new ItemStack(this, 1, i), Lib.TITLE + "crimsonCultist"));
            itemList.add(getItemWithTitle(new ItemStack(this, 1, i), Lib.TITLE + "crimsonKnight"));
            itemList.add(getItemWithTitle(new ItemStack(this, 1, i), Lib.TITLE + "crimsonPraetor"));
        } else itemList.add(new ItemStack(this, 1, i));
    }

    public static ItemStack getItemWithTitle(ItemStack stack, String title) {
        if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setString("title", title);
        return stack;
    }

    @Override
    public boolean canEquip(ItemStack stack, EntityLivingBase living) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack stack, EntityLivingBase living) {
        return !subNames[stack.getItemDamage()].contains("binding");
    }

    @Override
    public BaubleType getBaubleType(ItemStack stack) {
        return subNames[stack.getItemDamage()].startsWith("ring") ? BaubleType.RING
                : subNames[stack.getItemDamage()].startsWith("belt") ? BaubleType.BELT
                        : subNames[stack.getItemDamage()].startsWith("necklace") ? BaubleType.AMULET : null;
    }

    @Override
    public String[] getBaubleTypes(ItemStack stack) {
        if (subNames[stack.getItemDamage()].startsWith("cloak")) return new String[] { BaubleExpandedSlots.capeType };
        if (subNames[stack.getItemDamage()].startsWith("charm")) return new String[] { BaubleExpandedSlots.charmType };
        if (subNames[stack.getItemDamage()].startsWith("ring")) return new String[] { BaubleExpandedSlots.ringType };
        if (subNames[stack.getItemDamage()].startsWith("belt")) return new String[] { BaubleExpandedSlots.beltType };
        if (subNames[stack.getItemDamage()].startsWith("necklace"))
            return new String[] { BaubleExpandedSlots.amuletType };
        if (subNames[stack.getItemDamage()].startsWith("vambrace"))
            return new String[] { BaubleExpandedSlots.gauntletType };
        if (subNames[stack.getItemDamage()].startsWith("title")) return new String[] { "title" };
        return new String[] { "" };
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase living) {
        onItemTicked(living, stack);
    }

    @Override
    public void onEquipped(ItemStack stack, EntityLivingBase living) {
        onItemEquipped(living, stack);
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase living) {
        onItemUnequipped(living, stack);
    }

    public void onItemTicked(EntityLivingBase living, ItemStack stack) {
        if (living.ticksExisted < 1) {
            onItemUnequipped(living, stack);
            onItemEquipped(living, stack);
        }

        if (stack.getItemDamage() == 3 && living.isOnLadder()) {
            final boolean isFlying = living instanceof EntityPlayer && ((EntityPlayer) living).capabilities.isFlying;
            if (!isFlying) {
                if (living.isCollidedHorizontally) living.moveEntity(0, .25, 0);
                else if (!living.isSneaking()) living.moveEntity(0, -.1875, 0);
            }
        }
    }

    public void onItemEquipped(EntityLivingBase living, ItemStack stack) {
        if (stack.getItemDamage() == 1) Utilities.addAttributeModToLiving(
                living,
                SharedMonsterAttributes.knockbackResistance,
                new UUID(Lib.ATTRIBUTE_MOD_UUID, stack.getItemDamage()),
                "WGKnockbackResistance",
                0.6,
                0);
        if (stack.getItemDamage() == 2) Utilities.addAttributeModToLiving(
                living,
                SharedMonsterAttributes.attackDamage,
                new UUID(Lib.ATTRIBUTE_MOD_UUID, stack.getItemDamage()),
                "WGStrengthBonus",
                2,
                0);
    }

    public void onItemUnequipped(EntityLivingBase living, ItemStack stack) {
        if (stack.getItemDamage() == 1) Utilities.removeAttributeModFromLiving(
                living,
                SharedMonsterAttributes.knockbackResistance,
                new UUID(Lib.ATTRIBUTE_MOD_UUID, stack.getItemDamage()),
                "WGKnockbackResistance",
                0.6,
                0);
        if (stack.getItemDamage() == 2) Utilities.removeAttributeModFromLiving(
                living,
                SharedMonsterAttributes.attackDamage,
                new UUID(Lib.ATTRIBUTE_MOD_UUID, stack.getItemDamage()),
                "WGStrengthBonus",
                2,
                0);
    }

    public static ItemStack getInlaidGem(ItemStack ring) {
        return ItemInfusedGem.createGem(ItemInfusedGem.getAspect(ring), ItemInfusedGem.getCut(ring), false);
    }

    public static ItemStack setInlaidGem(ItemStack ring, ItemStack gem) {
        if (!ring.hasTagCompound()) ring.setTagCompound(new NBTTagCompound());
        ring.getTagCompound().setByte("GemCut", (byte) ItemInfusedGem.getCut(gem).ordinal());
        ring.getTagCompound().setString("Aspect", ItemInfusedGem.getAspect(gem).getTag());
        return ring;
    }

    @Optional.Method(modid = "Botania")
    public ItemStack getCosmeticItem(ItemStack stack) {
        if (!stack.hasTagCompound()) return null;
        return ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("botaniaCosmeticOverride"));
    }

    @Optional.Method(modid = "Botania")
    public void setCosmeticItem(ItemStack stack, ItemStack cosmetic) {
        if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        if (cosmetic == null) stack.getTagCompound().removeTag("botaniaCosmeticOverride");
        else {
            NBTTagCompound cosTag = cosmetic.writeToNBT(new NBTTagCompound());
            stack.getTagCompound().setTag("botaniaCosmeticOverride", cosTag);
        }
    }
}
