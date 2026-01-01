package witchinggadgets.common.items.baubles;

import static witchinggadgets.common.util.WGKeyHandler.activateKey;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import baubles.api.BaubleType;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import thaumcraft.api.IGoggles;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.common.items.armor.Hover;
import vazkii.botania.api.item.ICosmeticAttachable;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.items.interfaces.IItemEvent;
import witchinggadgets.common.util.Lib;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.network.message.MessageOpenCloak;
import witchinggadgets.common.util.network.message.MessageSyncGlide;

@Optional.Interface(iface = "vazkii.botania.api.item.ICosmeticAttachable", modid = "Botania")
public class ItemCloak extends Item implements IBaubleExpanded, ICosmeticAttachable, IItemEvent {

    public static String[] subNames = { "standard", "spectral", "storage", "wolf", "raven" };
    int[] defaultColours = {};
    IIcon iconRaven;
    IIcon iconWolf;

    private static final String TAG_NO_GLIDE = "noGlide";
    private static final String TAG_SPECTRAL = "isSpectral";

    private boolean lastKeybindState = false;

    public ItemCloak() {
        this.setHasSubtypes(true);
        this.setCreativeTab(WitchingGadgets.tabWG);
        maxStackSize = 1;
    }

    @Override
    public boolean isItemTool(ItemStack stack) {
        return stack.stackSize == 1;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("witchinggadgets:cloak");
        this.iconRaven = iconRegister.registerIcon("witchinggadgets:cloak_raven");
        this.iconWolf = iconRegister.registerIcon("witchinggadgets:cloak_wolf");
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta == 3) return this.iconWolf;
        if (meta == 4) return this.iconRaven;
        return this.itemIcon;
    }

    public boolean hasColor(ItemStack stack) {
        return true;
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        return getColor(stack);
    }

    public int getColor(ItemStack stack) {
        if (stack == null) return 0xffffff;
        int meta = stack.getItemDamage();
        if (meta == 0) {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag == null) return ClientUtilities.colour_CloakBlue;
            NBTTagCompound tagDisplay = tag.getCompoundTag("display");
            return tagDisplay == null ? ClientUtilities.colour_CloakBlue
                    : (tagDisplay.hasKey("color") ? tagDisplay.getInteger("color") : ClientUtilities.colour_CloakBlue);
        }
        return meta == 1 ? Aspect.DARKNESS.getColor() : meta == 2 ? Aspect.VOID.getColor() : 0xffffff;
    }

    public void removeColor(ItemStack stack) {
        if (stack == null) return;
        NBTTagCompound tag = stack.getTagCompound();
        if (tag != null) {
            NBTTagCompound tagDisplay = tag.getCompoundTag("display");

            if (tagDisplay.hasKey("color")) {
                tagDisplay.removeTag("color");
            }
        }
    }

    public void setColour(ItemStack stack, int colour) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound == null) {
            nbttagcompound = new NBTTagCompound();
            stack.setTagCompound(nbttagcompound);
        }

        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

        if (!nbttagcompound.hasKey("display")) {
            nbttagcompound.setTag("display", nbttagcompound1);
        }

        nbttagcompound1.setInteger("color", colour);
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String layer) {
        if (itemstack.getItemDamage() < subNames.length) if (subNames[itemstack.getItemDamage()].equals("wolf"))
            return "witchinggadgets:textures/models/cloakWolf.png";
        else if (subNames[itemstack.getItemDamage()].equals("raven"))
            return "witchinggadgets:textures/models/cloakRaven.png";
        return "witchinggadgets:textures/models/cloak.png";
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 0;
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return subNames[stack.getItemDamage()].equals("storage") ? 1 : maxStackSize;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName() + "." + subNames[stack.getItemDamage()];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> itemList) {
        for (int i = 0; i < subNames.length; i++)
            if (i != 4 || WGModCompat.loaded_Twilight) itemList.add(new ItemStack(item, 1, i));
    }

    public static ItemStack[] getStoredItems(ItemStack item) {
        ItemStack[] stackList = new ItemStack[27];

        if (item.hasTagCompound()) {
            NBTTagList inv = item.getTagCompound().getTagList("InternalInventory", 10);

            for (int i = 0; i < inv.tagCount(); i++) {
                NBTTagCompound tag = inv.getCompoundTagAt(i);
                int slot = tag.getByte("Slot") & 0xFF;

                if ((slot >= 0) && (slot < stackList.length)) {
                    stackList[slot] = ItemStack.loadItemStackFromNBT(tag);
                }
            }
        }
        return stackList;
    }

    public static void setStoredItems(ItemStack item, ItemStack[] stackList) {
        NBTTagList inv = new NBTTagList();

        for (int i = 0; i < stackList.length; i++) {
            if (stackList[i] != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stackList[i].writeToNBT(tag);
                inv.appendTag(tag);
            }
        }
        if (!item.hasTagCompound()) {
            item.setTagCompound(new NBTTagCompound());
        }
        item.getTagCompound().setTag("InternalInventory", inv);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        if (player.worldObj.isRemote) {
            BaubleItemHelper.addSlotInformation(list, getBaubleTypes(stack));
            list.add(
                    StatCollector.translateToLocal(Lib.DESCRIPTION + "enableCloak").replaceAll(
                            "%s1",
                            StatCollector.translateToLocalFormatted(
                                    GameSettings.getKeyDisplayString(activateKey.getKeyCode()))));
            if (subNames[stack.getItemDamage()].equals("raven")) {
                if (stack.hasTagCompound() && stack.getTagCompound().getBoolean(TAG_NO_GLIDE)) {
                    list.add(StatCollector.translateToLocal(Lib.DESCRIPTION + "noGlide"));
                }
                if (stack.hasTagCompound() && !stack.getTagCompound().getBoolean(TAG_NO_GLIDE)) {
                    list.add(StatCollector.translateToLocal(Lib.DESCRIPTION + "glide"));
                }
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

    /**
     * The method {@link net.minecraft.client.settings.KeyBinding#isPressed} changes the keybind state so that it only
     * works once per keybind. This alternative instead works by comparing the current tick and last tick state of the
     * keybind. Cloak and Kama items track last state separately, so that it can be updated from within the item's code
     * order-independently, and is tracked as an Item field, because it's only used on the client. Updates only happen
     * while the item is equipped.
     * 
     * @return Whether this is a tick when the activateKey was pressed down.
     */
    private boolean shouldActivate() {
        return !lastKeybindState && activateKey.getIsKeyPressed();
    }

    public void onItemTicked(EntityPlayer player, ItemStack stack) {
        if (player.worldObj.isRemote) {

            if (shouldActivate() && subNames[stack.getItemDamage()].equals("raven")) {
                int slot = BaubleExpandedSlots.getIndexesOfAssignedSlotsOfType(getBaubleTypes(stack)[0])[0];
                if (stack.getTagCompound().getBoolean(TAG_NO_GLIDE)) {
                    stack.getTagCompound().setBoolean(TAG_NO_GLIDE, false);
                    WitchingGadgets.packetHandler.sendToServer(new MessageSyncGlide(slot, false));
                    player.addChatMessage(
                            new ChatComponentText(StatCollector.translateToLocal(Lib.DESCRIPTION + "glide")));
                } else if (!stack.getTagCompound().getBoolean(TAG_NO_GLIDE)) {
                    stack.getTagCompound().setBoolean(TAG_NO_GLIDE, true);
                    WitchingGadgets.packetHandler.sendToServer(new MessageSyncGlide(slot, true));
                    player.addChatMessage(
                            new ChatComponentText(StatCollector.translateToLocal(Lib.DESCRIPTION + "noGlide")));
                }
            }
            if (shouldActivate() && subNames[stack.getItemDamage()].equals("storage")) {
                int kama = this.equals(WGContent.ItemKama) ? 5 : 4;
                player.openGui(
                        WitchingGadgets.instance,
                        kama,
                        player.worldObj,
                        MathHelper.floor_double(player.posX),
                        MathHelper.floor_double(player.posY),
                        MathHelper.floor_double(player.posZ));
                WitchingGadgets.packetHandler.sendToServer(new MessageOpenCloak(player, kama));
            }

            lastKeybindState = activateKey.getIsKeyPressed();
        }

        if (player.ticksExisted < 1) {
            onItemUnequipped(player, stack);
            onItemEquipped(player, stack);
        }

        if (stack.getItemDamage() < subNames.length) {
            if (subNames[stack.getItemDamage()].equals("spectral") && !player.worldObj.isRemote
                    && stack.hasTagCompound()
                    && stack.getTagCompound().getBoolean(TAG_SPECTRAL))
                if (player.ticksExisted % 100 == 0)
                    if (!Utilities.consumeVisFromInventoryWithoutDiscount(player, new AspectList().add(Aspect.AIR, 1)))
                        stack.getTagCompound().setBoolean(TAG_SPECTRAL, false);
            if (subNames[stack.getItemDamage()].equals("raven")) {
                if (!player.onGround && !player.isOnLadder()) {
                    if (player.capabilities.isFlying || Hover.getHover(player.getEntityId())) {
                        if (player.moveForward > 0) player.moveFlying(0, 1, .05f);
                        player.motionY *= 1.125;
                    } else if (player.motionY < 0
                            && (!stack.hasTagCompound() || !stack.getTagCompound().getBoolean(TAG_NO_GLIDE))) {
                                float mod = player.isSneaking() ? .1f : .05f;
                                player.motionY *= player.isSneaking() ? .75 : .5;
                                double x = Math.cos(Math.toRadians(player.rotationYawHead + 90)) * mod;
                                double z = Math.sin(Math.toRadians(player.rotationYawHead + 90)) * mod;
                                player.motionX += x;
                                player.motionZ += z;
                            }
                    player.fallDistance = 0f;
                }
            }

            if (subNames[stack.getItemDamage()].equals("wolf") && stack.hasTagCompound()
                    && stack.getTagCompound().hasKey("wolfPotion")) {
                int amp = stack.getTagCompound().getInteger("wolfPotion");
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 60, amp));
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 60, amp));
                player.addPotionEffect(new PotionEffect(Potion.resistance.id, 60, amp));
                stack.getTagCompound().removeTag("wolfPotion");
                if (stack.getTagCompound().hasNoTags()) stack.setTagCompound(null);
            }
        }
    }

    public void onItemEquipped(EntityLivingBase player, ItemStack stack) {}

    public void onItemUnequipped(EntityLivingBase player, ItemStack stack) {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey(TAG_NO_GLIDE))
            stack.getTagCompound().setBoolean(TAG_NO_GLIDE, true);

        if (stack.hasTagCompound() && stack.getTagCompound().getBoolean(TAG_SPECTRAL))
            stack.getTagCompound().setBoolean(TAG_SPECTRAL, false);
    }

    @Override
    public void onUserDamaged(LivingHurtEvent event, ItemStack stack) {
        if (!stack.equals(event.entityLiving.getEquipmentInSlot(0)) && stack.getItemDamage() == 3) {
            int amp = 1;
            if (event.ammount >= 8) amp++;
            if (event.ammount >= 12) amp++;
            if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setInteger("wolfPotion", amp);
        }
    }

    @Override
    public void onUserAttacking(AttackEntityEvent event, ItemStack stack) {}

    @Override
    public void onUserJump(LivingEvent.LivingJumpEvent event, ItemStack stack) {}

    @Override
    public void onUserFall(LivingFallEvent event, ItemStack stack) {}

    @Override
    public void onUserTargeted(LivingSetAttackTargetEvent event, ItemStack stack) {
        if (stack.hasTagCompound() && stack.getTagCompound().getBoolean(TAG_SPECTRAL)
                && event.entityLiving instanceof EntityCreature) {
            boolean goggles = event.entityLiving.getEquipmentInSlot(4) != null
                    && (event.entityLiving.getEquipmentInSlot(4).getItem() instanceof IRevealer
                            || event.entityLiving.getEquipmentInSlot(4).getItem() instanceof IGoggles);
            boolean special = event.entityLiving instanceof IEldritchMob
                    || event.entityLiving instanceof IBossDisplayData;
            if (!goggles && !special) Utilities.setAttackTarget((EntityCreature) event.entityLiving, null);
        }
    }

    @Optional.Method(modid = "Botania")
    public ItemStack getCosmeticItem(ItemStack stack) {
        if (!stack.hasTagCompound()) return null;
        return ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("botaniaCosmeticOverride"));
    }

    @Optional.Method(modid = "Botania")
    public void setCosmeticItem(ItemStack stack, ItemStack cosmetic) {
        if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        NBTTagCompound cosTag = cosmetic.writeToNBT(new NBTTagCompound());
        stack.getTagCompound().setTag("botaniaCosmeticOverride", cosTag);
    }

    @Override
    public void onWornTick(ItemStack aItem, EntityLivingBase playerEntity) {
        if (playerEntity instanceof EntityPlayer) {
            onItemTicked((EntityPlayer) playerEntity, aItem);
        }
    }

    @Override
    public void onEquipped(ItemStack stack, EntityLivingBase playerEntity) {
        // Must be a EntityPlayer
        if (!(playerEntity instanceof EntityPlayer player)) {
            return;
        }

        onItemEquipped(playerEntity, stack);

        // All logic following is server-side
        if (player.worldObj.isRemote) {
            return;
        }

        // The meta match a valid type of cloak
        int meta = stack.getItemDamage();
        if (meta >= subNames.length) {
            return;
        }

        String type = subNames[meta];

        if (type.equals("raven")) {
            handleRavenEquip(stack);
        } else if (type.equals("spectral")) {
            handleSpectralEquip(stack, player);
        }
    }

    /**
     * Helper method to handle equipping the Raven Mantle. Sets the default 'noGlide' state.
     *
     * @param stack The cloak's ItemStack.
     */
    private void handleRavenEquip(ItemStack stack) {
        if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());

        if (!stack.getTagCompound().hasKey(TAG_NO_GLIDE)) stack.getTagCompound().setBoolean(TAG_NO_GLIDE, true);
    }

    /**
     * Helper method to handle equipping the Spectral Mantle. Consumes vis and toggles the spectral state, de-aggroing
     * mobs.
     *
     * @param stack  The cloak's ItemStack.
     * @param player The player equipping the cloak.
     */
    private void handleSpectralEquip(ItemStack stack, EntityPlayer player) {
        // Check for vis consumption
        if (!Utilities.consumeVisFromInventoryWithoutDiscount(player, new AspectList().add(Aspect.AIR, 1))) {
            return;
        }

        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        // Toggle spectral state
        boolean isSpectral = !stack.getTagCompound().getBoolean(TAG_SPECTRAL);
        stack.getTagCompound().setBoolean(TAG_SPECTRAL, isSpectral);

        if (isSpectral) {
            AxisAlignedBB bounds = AxisAlignedBB.getBoundingBox(
                    player.posX - 16,
                    player.posY - 16,
                    player.posZ - 16,
                    player.posX + 16,
                    player.posY + 16,
                    player.posZ + 16);

            List<EntityCreature> mobs = player.worldObj.getEntitiesWithinAABB(EntityCreature.class, bounds);

            for (EntityCreature e : mobs) {
                if (e != null && !(e instanceof IBossDisplayData) && player.equals(e.getAttackTarget())) {
                    Utilities.setAttackTarget(e, null);
                }
            }
        }
    }

    @Override
    public void onUnequipped(ItemStack aItem, EntityLivingBase playerEntity) {
        onItemUnequipped(playerEntity, aItem);
    }

    @Override
    public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public String[] getBaubleTypes(ItemStack itemstack) {
        return new String[] { BaubleExpandedSlots.capeType };
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return null;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        BaubleItemHelper.onBaubleRightClick(itemStackIn, worldIn, player);
        return super.onItemRightClick(itemStackIn, worldIn, player);
    }
}
