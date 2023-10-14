package witchinggadgets.common.util.handler;

import java.util.List;
import java.util.ListIterator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.entities.EntitySpecialItem;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileInfusionMatrix;
import travellersgear.api.TravellersGearAPI;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.api.IPrimordialCrafting;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.items.ItemMaterials;
import witchinggadgets.common.items.baubles.ItemMagicalBaubles;
import witchinggadgets.common.items.tools.IPrimordialGear;
import witchinggadgets.common.items.tools.ItemBag;
import witchinggadgets.common.magic.WGEnchantSoulbound;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.network.message.MessageClientNotifier;
import witchinggadgets.mixins.early.minecraft.EntityLivingAccessor;

public class EventHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void entityHurt(LivingHurtEvent event) {
        if (event.source.isFireDamage() && event.entityLiving.getActivePotionEffect(WGContent.pot_cinderCoat) != null) {
            event.ammount *= 2 + event.entityLiving.getActivePotionEffect(WGContent.pot_cinderCoat).getAmplifier();
        }

        if (event.source.getSourceOfDamage() instanceof EntityPlayer
                && ((EntityPlayer) event.source.getSourceOfDamage()).getCurrentEquippedItem() != null) {
            EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
            if (WGContent.ItemPrimordialHammer.equals(player.getCurrentEquippedItem().getItem())
                    && (event.entityLiving instanceof EntitySlime
                            || event.entityLiving.getClass().getName().endsWith("BlueSlime")
                            || event.entityLiving.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD)) {
                event.ammount *= 2;
            }
            if (player.getCurrentEquippedItem().getItem().equals(WGContent.ItemPrimordialAxe)
                    && !event.source.isUnblockable()) {
                float mod = 1;
                for (int i = 1; i <= 4; i++) if (event.entityLiving.getEquipmentInSlot(i) != null) mod += .5f;
                event.ammount *= mod;
            }

            if (EnchantmentHelper.getEnchantmentLevel(WGContent.enc_backstab.effectId, player.getCurrentEquippedItem())
                    > 0) {
                Vec3 targetVec = event.entityLiving.getLookVec();
                Vec3 attackVec = player.getLookVec();
                if (Math.signum(targetVec.xCoord) == Math.signum(attackVec.xCoord)
                        && Math.signum(targetVec.zCoord) == Math.signum(attackVec.zCoord)) {
                    float mod = 1 + .2f * EnchantmentHelper
                            .getEnchantmentLevel(WGContent.enc_backstab.effectId, player.getCurrentEquippedItem());
                    if (event.entityLiving instanceof EntityCreature
                            && !player.equals(((EntityCreature) event.entityLiving).getAttackTarget())) {
                        mod += .4f;
                    }
                    event.ammount *= mod;
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingSetTarget(LivingSetAttackTargetEvent event) {
        if (!(event.target instanceof EntityPlayer)) return;
        if (event.entityLiving instanceof EntityCreature) {
            EntityPlayer player = (EntityPlayer) event.target;
            if (player.isSneaking())
                if (EnchantmentHelper.getEnchantmentLevel(WGContent.enc_stealth.effectId, player.getCurrentArmor(0)) > 0
                        || EnchantmentHelper
                                .getEnchantmentLevel(WGContent.enc_stealth.effectId, player.getCurrentArmor(1)) > 0) {
                                    float chance = EnchantmentHelper.getEnchantmentLevel(
                                            WGContent.enc_stealth.effectId,
                                            player.getCurrentArmor(0)) * .1f
                                            + EnchantmentHelper.getEnchantmentLevel(
                                                    WGContent.enc_stealth.effectId,
                                                    player.getCurrentArmor(1)) * .1f;
                                    Vec3 targetVec = event.entityLiving.getLookVec();
                                    Vec3 attackVec = player.getLookVec();
                                    if (Math.signum(targetVec.xCoord) != Math.signum(attackVec.xCoord)
                                            || Math.signum(targetVec.zCoord) != Math.signum(attackVec.zCoord))
                                        chance -= .1f;
                                    if (player.getRNG().nextFloat() < chance)
                                        Utilities.setAttackTarget((EntityCreature) event.entityLiving, null);
                                    else {
                                        for (EntityCreature e : (List<EntityCreature>) player.worldObj
                                                .getEntitiesWithinAABB(
                                                        EntityCreature.class,
                                                        AxisAlignedBB.getBoundingBox(
                                                                player.posX - 5,
                                                                player.posY - 5,
                                                                player.posZ - 5,
                                                                player.posX + 5,
                                                                player.posY + 5,
                                                                player.posZ + 5)))
                                            if (e != null && !(e instanceof IBossDisplayData)
                                                    && player.equals(e.getAttackTarget()))
                                                Utilities.setAttackTarget((EntityCreature) event.entityLiving, null);
                                    }
                                }
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        for (ItemStack cloak : Utilities.getActiveMagicalCloak(event.entityPlayer))
            if (cloak != null && cloak.hasTagCompound() && cloak.getTagCompound().getBoolean("isSpectral"))
                event.setCanceled(true);
        if (Loader.isModLoaded("ForgeMultipart")) WGMultiPartHandler.handleWorldInteraction(event);
    }

    @SubscribeEvent
    public void onPlayerInteractWithEntity(EntityInteractEvent event) {
        for (ItemStack cloak : Utilities.getActiveMagicalCloak(event.entityPlayer))
            if (cloak != null && cloak.hasTagCompound() && cloak.getTagCompound().getBoolean("isSpectral"))
                event.setCanceled(true);
    }

    @SubscribeEvent
    public void onPlayerAttackEntity(AttackEntityEvent event) {
        for (ItemStack cloak : Utilities.getActiveMagicalCloak(event.entityPlayer))
            if (cloak != null && cloak.hasTagCompound() && cloak.getTagCompound().getBoolean("isSpectral"))
                event.setCanceled(true);
    }

    @SubscribeEvent
    public void onPlayerBreaking(PlayerEvent.BreakSpeed event) {
        if (TravellersGearAPI.getExtendedInventory(event.entityPlayer)[2] != null
                && TravellersGearAPI.getExtendedInventory(event.entityPlayer)[2].getItem() instanceof ItemMagicalBaubles
                && TravellersGearAPI.getExtendedInventory(event.entityPlayer)[2].getItemDamage() == 3) {
            Block block = event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z);
            if (!event.entityPlayer.onGround) event.newSpeed *= 5.0F;
            if (event.entityPlayer.isInsideOfMaterial(Material.water)
                    && !EnchantmentHelper.getAquaAffinityModifier(event.entityPlayer))
                event.newSpeed *= 5.0F;

            float hardness = block.getBlockHardness(event.entityPlayer.worldObj, event.x, event.y, event.z);
            if (hardness > 20) event.newSpeed = 5 + hardness;
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onLivingDrop(LivingDropsEvent event) {
        if (event.entityLiving instanceof EntityWolf) {
            EntityWolf enemy = (EntityWolf) event.entityLiving;
            for (int i = 0; i < 2 + Math.min(4, event.lootingLevel); i++)
                if (enemy.worldObj.rand.nextInt(Math.max(1, 3 - event.lootingLevel)) == 0) {
                    EntityItem entityitem = new EntityItem(
                            event.entityLiving.worldObj,
                            event.entityLiving.posX,
                            event.entityLiving.posY,
                            event.entityLiving.posZ,
                            new ItemStack(WGContent.ItemMaterial, 1, 6));
                    entityitem.delayBeforeCanPickup = 10;
                    event.drops.add(entityitem);
                }
        }

        // here resides the cancer of crimson titles. begone
        /*
         * if(event.entityLiving instanceof EntityCultistCleric &&
         * event.entityLiving.worldObj.rand.nextInt(10)<1+event.lootingLevel) event.drops.add(new
         * EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY,
         * event.entityLiving.posZ, ItemMagicalBaubles.getItemWithTitle(new
         * ItemStack(WGContent.ItemMagicalBaubles,1,4),Lib.TITLE+"crimsonCultist"))); if(event.entityLiving instanceof
         * EntityCultistKnight && event.entityLiving.worldObj.rand.nextInt(10)<1+event.lootingLevel) event.drops.add(new
         * EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY,
         * event.entityLiving.posZ, ItemMagicalBaubles.getItemWithTitle(new
         * ItemStack(WGContent.ItemMagicalBaubles,1,4),Lib.TITLE+"crimsonKnight"))); if(event.entityLiving instanceof
         * EntityCultistLeader && event.entityLiving.worldObj.rand.nextInt(2)==0) event.drops.add(new
         * EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY,
         * event.entityLiving.posZ, ItemMagicalBaubles.getItemWithTitle(new
         * ItemStack(WGContent.ItemMagicalBaubles,1,4),Lib.TITLE+"crimsonPraetor")));
         */

        if (event.recentlyHit && event.source != null && event.source.getSourceOfDamage() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
            if (player.getCurrentEquippedItem() != null
                    && WGContent.ItemPrimordialSword.equals(player.getCurrentEquippedItem().getItem())
                    && player.getRNG().nextInt(6) < EnchantmentHelper.getLootingModifier(player)) {
                ItemStack head = null;
                if (event.entityLiving instanceof EntitySkeleton)
                    head = new ItemStack(Items.skull, 1, ((EntitySkeleton) event.entityLiving).getSkeletonType());
                else if (event.entityLiving instanceof EntityZombie) head = new ItemStack(Items.skull, 1, 2);
                else if (event.entityLiving instanceof EntityCreeper) head = new ItemStack(Items.skull, 1, 4);
                else if (event.entityLiving instanceof EntityPlayer) {
                    head = new ItemStack(Items.skull, 1, 3);
                    NBTTagCompound tag = new NBTTagCompound();
                    tag.setString("SkullOwner", player.getDisplayName());
                    head.setTagCompound(tag);
                } else if (Loader.isModLoaded("witchery")) {
                    Item wwh = GameRegistry.findItem("witchery", "wolfhead");
                    if (event.entityLiving instanceof EntityWolf) head = new ItemStack(wwh, 1, 0);
                    if (event.entityLiving.getClass().getName().endsWith("EntityHellhound"))
                        head = new ItemStack(wwh, 1, 1);
                } else if (Loader.isModLoaded("IguanaTweaksTConstruct")) {
                    Item ith = GameRegistry.findItem("IguanaTweaksTConstruct", "skullItem");
                    if (event.entityLiving instanceof EntityEnderman) head = new ItemStack(ith, 1, 0);
                    else if (event.entityLiving instanceof EntityPigZombie) head = new ItemStack(ith, 1, 1);
                    else if (event.entityLiving instanceof EntityBlaze) head = new ItemStack(ith, 1, 2);
                    else if (EntityList.getEntityString(event.entityLiving).equals("Blizz"))
                        head = new ItemStack(ith, 1, 3);
                }

                if (head != null) {
                    for (EntityItem eitem : event.drops) {
                        if (eitem != null && OreDictionary.itemMatches(eitem.getEntityItem(), head, true)) return;
                    }
                    event.entityLiving.worldObj.spawnEntityInWorld(
                            new EntityItem(
                                    event.entityLiving.worldObj,
                                    event.entityLiving.posX,
                                    event.entityLiving.posY,
                                    event.entityLiving.posZ,
                                    head));
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingDies(LivingDeathEvent event) {
        if (event.source != null && event.source.getSourceOfDamage() instanceof EntityPlayer
                && event.entityLiving instanceof EntityLiving
                && !event.entityLiving.worldObj.isRemote
                && event.entityLiving.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")) {
            EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();

            if (player.getCurrentEquippedItem() != null
                    && player.getCurrentEquippedItem().getItem() instanceof IPrimordialGear
                    && ((IPrimordialGear) player.getCurrentEquippedItem().getItem())
                            .getAbility(player.getCurrentEquippedItem()) == 5) {
                int baseValue = ((EntityLivingAccessor) event.entityLiving).getExperienceValue();
                int xp = 4 * baseValue;
                while (xp > 0) {
                    int i = EntityXPOrb.getXPSplit(xp);
                    xp -= i;
                    event.entityLiving.worldObj.spawnEntityInWorld(
                            new EntityXPOrb(
                                    event.entityLiving.worldObj,
                                    event.entityLiving.posX,
                                    event.entityLiving.posY,
                                    event.entityLiving.posZ,
                                    i));
                }
            }
        }
    }

    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event) {
        for (int i = 0; i < event.entityPlayer.inventory.getSizeInventory(); i++) {
            if (event.entityPlayer.inventory.getStackInSlot(i) != null
                    && event.entityPlayer.inventory.getStackInSlot(i).getItem() instanceof ItemBag) {
                if (event.entityPlayer.inventory.getStackInSlot(i).getItemDamage() == 1) {
                    ItemStack[] filter = ItemBag.getStoredItems(event.entityPlayer.inventory.getStackInSlot(i));
                    for (ItemStack f : filter) if (OreDictionary.itemMatches(f, event.item.getEntityItem(), true)) {
                        AspectList al = ThaumcraftCraftingManager.getObjectTags(event.item.getEntityItem());
                        al = ThaumcraftCraftingManager.getBonusTags(event.item.getEntityItem(), al);
                        if (al != null && al.size() >= 0) {
                            AspectList primals = ResearchManager.reduceToPrimals(al);
                            Aspect a = primals.getAspects()[event.entityPlayer.getRNG()
                                    .nextInt(primals.getAspects().length)];
                            if (a != null) {
                                int slot = InventoryUtils.isWandInHotbarWithRoom(a, 1, event.entityPlayer);
                                if (slot >= 0) {
                                    ItemWandCasting wand = (ItemWandCasting) event.entityPlayer.inventory.mainInventory[slot]
                                            .getItem();
                                    wand.addVis(
                                            event.entityPlayer.inventory.mainInventory[slot],
                                            a,
                                            primals.getAmount(a),
                                            true);
                                }
                            }
                        }
                        event.item.setDead();
                        event.setCanceled(true);
                        return;
                    }
                } else if (event.entityPlayer.inventory.getStackInSlot(i).getItemDamage() == 3) {
                    ItemStack[] inv = ItemBag.getStoredItems(event.entityPlayer.inventory.getStackInSlot(i));
                    boolean itemWasPickedUp = false;
                    for (int f = 0; f < inv.length; f++) {
                        if (inv[f] == null) {
                            inv[f] = event.item.getEntityItem().copy();
                            event.item.setDead();
                            event.setCanceled(true);
                            itemWasPickedUp = true;
                            break;
                        } else if (inv[f].isItemEqual(event.item.getEntityItem())
                                && ItemStack.areItemStackTagsEqual(inv[f], event.item.getEntityItem())) {
                                    int fit = Math.min(
                                            Math.min(64, inv[f].getMaxStackSize()) - inv[f].stackSize,
                                            event.item.getEntityItem().stackSize);
                                    inv[f].stackSize += fit;
                                    event.item.getEntityItem().stackSize -= fit;
                                    if (event.item.getEntityItem().stackSize <= 0) {
                                        event.item.setDead();
                                        event.setCanceled(true);
                                        itemWasPickedUp = true;
                                        break;
                                    }
                                }
                    }
                    ItemBag.setStoredItems(event.entityPlayer.inventory.getStackInSlot(i), inv);
                    if (itemWasPickedUp) {
                        break;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onCrafted(ItemCraftedEvent event) {
        ItemStack output = event.crafting;
        IInventory craftMatrix = event.craftMatrix;
        if (WGContent.ItemKama.equals(output.getItem())) {
            for (int matrixSlot = 0; matrixSlot < 9; matrixSlot++) {
                ItemStack stackInMatrix = craftMatrix.getStackInSlot(matrixSlot);
                if (stackInMatrix != null && WGContent.ItemCloak.equals(stackInMatrix.getItem()))
                    output.setTagCompound(stackInMatrix.getTagCompound());
            }
        }
        if (WGContent.ItemMaterial.equals(output.getItem()) && output.getItemDamage() == 10) {
            for (int matrixSlot = 0; matrixSlot < 9; matrixSlot++) {
                ItemStack stackInMatrix = craftMatrix.getStackInSlot(matrixSlot);
                if ((stackInMatrix != null) && (stackInMatrix.getItem() instanceof ItemMaterials)
                        && stackInMatrix.getItemDamage() == 9) {
                    stackInMatrix.stackSize += 1;
                    craftMatrix.setInventorySlotContents(matrixSlot, stackInMatrix);
                }
            }
        }
        if (output.getItem() instanceof IPrimordialCrafting && !event.player.worldObj.isRemote
                && (!output.hasTagCompound())) {
            if (((IPrimordialCrafting) output.getItem()).getReturnedPearls(output) > 0) {
                double iX = event.player.posX;
                double iY = event.player.posY + 1;
                double iZ = event.player.posZ;
                for (int yy = -16; yy <= 16; yy++) for (int zz = -16; zz <= 16; zz++)
                    for (int xx = -16; xx <= 16; xx++) if (event.player.worldObj.getTileEntity(
                            (int) event.player.posX + xx,
                            (int) event.player.posY + yy,
                            (int) event.player.posZ + zz) instanceof TileInfusionMatrix) {
                                iX = event.player.posX + xx;
                                iY = event.player.posY + yy - .5;
                                iZ = event.player.posZ + zz;
                            }
                EntitySpecialItem entityitem = new EntitySpecialItem(
                        event.player.worldObj,
                        iX,
                        iY,
                        iZ,
                        new ItemStack(
                                WGContent.ItemMaterial,
                                ((IPrimordialCrafting) output.getItem()).getReturnedPearls(output),
                                12));
                entityitem.motionX = entityitem.motionY = entityitem.motionZ = 0;
                // TODO pearl tags
                if (output.getTagCompound() == null) output.setTagCompound(new NBTTagCompound());
                output.getTagCompound().setBoolean("wasCrafted", true);
                event.player.worldObj.spawnEntityInWorld(entityitem);
            }
        }
    }

    @SubscribeEvent
    public void playerLogin(PlayerLoggedInEvent event) {
        WitchingGadgets.packetHandler.sendTo(new MessageClientNotifier(0), (EntityPlayerMP) event.player);
        // WGPacketPipeline.INSTANCE.sendTo(new PacketClientNotifier(0), (EntityPlayerMP) event.player);
    }

    // TODO SOULBOUND
    /*
     * This is called the moment the player dies and drops his stuff. We go early, so we can get our items before other
     * mods put them into some grave. Also remove them from the list so they won't get duped. If the inventory
     * overflows, e.g. because everything there and the armor is soulbound, let the remainder be dropped/graved.
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDropsEvent evt) {
        if (evt.entityPlayer == null || evt.entityPlayer instanceof FakePlayer || evt.isCanceled()) {
            return;
        }
        if (evt.entityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
            return;
        }

        ListIterator<EntityItem> iter = evt.drops.listIterator();
        while (iter.hasNext()) {
            EntityItem ei = iter.next();
            ItemStack item = ei.getEntityItem();
            if (isSoulBound(item)) {
                if (addToPlayerInventory(evt.entityPlayer, item)) {
                    iter.remove();
                }
            }
        }

        // Note: Baubles will also add its items to evt.drops, but later. We cannot
        // wait for that because gravestone mods also listen to this event. So we have
        // to fetch Baubles items ourselves here.
        // For the same reason we cannot put the items into Baubles slots.

        if (WGConfig.soulboundBaubles) {
            IInventory baubles = BaublesUtil.instance().getBaubles(evt.entityPlayer);
            if (baubles != null) {
                for (int i = 0; i < baubles.getSizeInventory(); i++) {
                    ItemStack item = baubles.getStackInSlot(i);
                    if (isSoulBound(item)) {
                        if (addToPlayerInventory(evt.entityPlayer, item)) {
                            baubles.setInventorySlotContents(i, null);
                        }
                    }
                }
            }
        }

        // Galacticraft. Again we are too early for those items. We just dump the
        // stuff into the normal inventory to not have to keep a separate list.
        if (WGConfig.soulboundGalacticraft) {
            if (evt.entityPlayer instanceof EntityPlayerMP) {
                IInventory galacticraft = GalacticraftUtil.getGCInventoryForPlayer((EntityPlayerMP) evt.entityPlayer);
                if (galacticraft != null) {
                    for (int i = 0; i < galacticraft.getSizeInventory(); i++) {
                        ItemStack item = galacticraft.getStackInSlot(i);
                        if (isSoulBound(item)) {
                            if (addToPlayerInventory(evt.entityPlayer, item)) {
                                galacticraft.setInventorySlotContents(i, null);
                            }
                        }
                    }
                }
            }
        }
    }

    /*
     * This is called when the user presses the "respawn" button. The original inventory would be empty, but
     * onPlayerDeath() above placed items in it. Note: Without other death-modifying mods, the content of the old
     * inventory would always fit into the new one (both being empty but for soulbound items in the old one) and the old
     * one would be discarded just after this method. But better play it safe and assume that an overflow is possible
     * and that another mod may move stuff out of the old inventory, too.
     */
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone evt) {
        if (!evt.wasDeath || evt.isCanceled()) {
            return;
        }
        if (evt.original == null || evt.entityPlayer == null || evt.entityPlayer instanceof FakePlayer) {
            return;
        }
        if (evt.entityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
            return;
        }
        for (int i = 0; i < evt.original.inventory.mainInventory.length; i++) {
            ItemStack item = evt.original.inventory.mainInventory[i];
            if (isSoulBound(item)) {
                if (addToPlayerInventory(evt.entityPlayer, item)) {
                    evt.original.inventory.mainInventory[i] = null;
                }
            }
        }
        for (int i = 0; i < evt.original.inventory.armorInventory.length; i++) {
            ItemStack item = evt.original.inventory.armorInventory[i];
            if (isSoulBound(item)) {
                if (addToPlayerInventory(evt.entityPlayer, item)) {
                    evt.original.inventory.armorInventory[i] = null;
                }
            }
        }
    }

    private boolean isSoulBound(ItemStack item) {
        return EnchantmentHelper.getEnchantmentLevel(WGEnchantSoulbound.id, item) > 0;
    }

    private boolean addToPlayerInventory(EntityPlayer entityPlayer, ItemStack item) {
        if (item == null || entityPlayer == null) {
            return false;
        }
        if (item.getItem() instanceof ItemArmor) {
            ItemArmor arm = (ItemArmor) item.getItem();
            int index = 3 - arm.armorType;
            if (entityPlayer.inventory.armorItemInSlot(index) == null) {
                entityPlayer.inventory.armorInventory[index] = item;
                return true;
            }
        }

        InventoryPlayer inv = entityPlayer.inventory;
        for (int i = 0; i < inv.mainInventory.length; i++) {
            if (inv.mainInventory[i] == null) {
                inv.mainInventory[i] = item.copy();
                return true;
            }
        }

        return false;
    }
}
