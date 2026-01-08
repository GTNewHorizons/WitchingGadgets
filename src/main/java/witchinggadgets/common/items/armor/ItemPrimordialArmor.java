package witchinggadgets.common.items.armor;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.hazards.Hazard;
import gregtech.api.hazards.IHazardProtector;
import taintedmagic.api.IVoidwalker;
import taintedmagic.common.items.equipment.ItemVoidwalkerBoots;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.armor.Hover;
import thaumcraft.common.items.armor.ItemFortressArmor;
import thaumicboots.api.IBoots;
import thaumicboots.mixins.early.minecraft.EntityLivingBaseAccessor;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.api.IPrimordialCrafting;
import witchinggadgets.client.render.ModelPrimordialArmor;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.items.interfaces.IItemEvent;
import witchinggadgets.common.items.tools.IPrimordialGear;
import witchinggadgets.common.util.Lib;

@Optional.InterfaceList({ @Optional.Interface(iface = "taintedmagic.api.IVoidwalker", modid = "TaintedMagic"),
        @Optional.Interface(iface = "thaumicboots.api.IBoots", modid = "thaumicboots"),
        @Optional.Interface(iface = "gregtech.api.hazards.IHazardProtector", modid = "gregtech_nh") })
public class ItemPrimordialArmor extends ItemFortressArmor implements IPrimordialCrafting, IPrimordialGear, IRunicArmor,
        IItemEvent, IBoots, IWarpingGear, IVisDiscountGear, IHazardProtector, IVoidwalker {

    enum FlightStatus {
        ON,
        OFF
    }

    IIcon rune;

    private FlightStatus flightStatus;

    public ItemPrimordialArmor(ArmorMaterial mat, int idx, int type) {
        super(mat, idx, type);
        this.setCreativeTab(WitchingGadgets.tabWG);
        flightStatus = FlightStatus.OFF;
    }

    @Override
    public int getRunicCharge(ItemStack stack) {
        return 0;
    }

    @Override
    public boolean showNodes(ItemStack stack, EntityLivingBase living) {
        return this.armorType == 0 && stack.hasTagCompound() && stack.getTagCompound().hasKey("goggles");
    }

    @Override
    public boolean showIngamePopups(ItemStack stack, EntityLivingBase living) {
        return this.armorType == 0 && stack.hasTagCompound() && stack.getTagCompound().hasKey("goggles");
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean equipped) {
        super.onUpdate(stack, world, entity, slot, equipped);
        if ((!world.isRemote) && (stack.isItemDamaged())
                && (entity.ticksExisted % 40 == 0)
                && ((entity instanceof EntityLivingBase)))
            stack.damageItem(-1, (EntityLivingBase) entity);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {

        if (player.isSneaking() && !player.worldObj.isRemote) {
            cycleAbilities(itemStackIn);
            return itemStackIn;
        } else return super.onItemRightClick(itemStackIn, worldIn, player);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (!world.isRemote && stack.isItemDamaged() && player.ticksExisted % 20 == 0) stack.damageItem(-1, player);

        byte armorcounter = 0;
        byte[] modescounter = { 0, 0, 0, 0, 0, 0 };

        boolean helmet = player.getCurrentArmor(0) != null && isThis(player.getCurrentArmor(0));
        boolean chestplate = player.getCurrentArmor(1) != null && isThis(player.getCurrentArmor(1));
        boolean leggings = player.getCurrentArmor(2) != null && isThis(player.getCurrentArmor(2));
        boolean boots = player.getCurrentArmor(3) != null && isThis(player.getCurrentArmor(3));

        int[] modes = new int[] { helmet ? getAbility(player.getCurrentArmor(0)) : 0,
                chestplate ? getAbility(player.getCurrentArmor(1)) : 0,
                leggings ? getAbility(player.getCurrentArmor(2)) : 0,
                boots ? getAbility(player.getCurrentArmor(3)) : 0, };

        if (helmet) ++armorcounter;
        if (chestplate) ++armorcounter;
        if (leggings) ++armorcounter;
        if (boots) ++armorcounter;

        for (int i : modes) {
            if (i == 1) ++modescounter[0];
            if (i == 2) ++modescounter[1];
            if (i == 3) ++modescounter[2];
            if (i == 4) ++modescounter[3];
            if (i == 5) ++modescounter[4];
            if (i == 6) ++modescounter[5];
        }

        if (this.armorType == 3) {
            if (getInertiaState(stack) && player.moveForward == 0
                    && player.moveStrafing == 0
                    && player.capabilities.isFlying) {
                player.motionX *= 0.5;
                player.motionZ *= 0.5;
            }
            float bonus = 0.7685F;
            movementEffects(player, bonus, stack);
            if (player.fallDistance > 0.0F) player.fallDistance = 0.0F;
        }

        if (armorcounter >= 2 && modescounter[2] >= 2 && player.isInsideOfMaterial(Material.lava)) {
            player.setAir(300);
            player.addPotionEffect(new PotionEffect(Potion.blindness.id, 202, 0, true));
            player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 202, 0, true));
        }

        // turn flight off or on
        if ((armorcounter >= 2 && modescounter[0] >= 2)) {
            flightStatus = FlightStatus.ON;
            player.capabilities.allowFlying = true;
        } else if (flightStatus == FlightStatus.ON) {
            flightStatus = FlightStatus.OFF;
            player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
        }

        switch (getAbility(stack))
        // cur 1 = aer,2=terra,3=ignis, 4=aqua, 5=ordo, 6=perdidito,
        {
            case 1:
                // Thanks WayOfFlowingTime =P
                AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(
                        player.posX - .5,
                        player.posY - .5,
                        player.posZ - .5,
                        player.posX + .5,
                        player.posY + .5,
                        player.posZ + .5).expand(4, 4, 4);
                for (Entity projectile : world.getEntitiesWithinAABB(Entity.class, aabb)) {
                    if (projectile == null) continue;
                    if (!(projectile instanceof IProjectile)
                            || projectile.getClass().getSimpleName().equalsIgnoreCase("IManaBurst"))
                        continue;

                    Entity shooter = null;
                    if (projectile instanceof EntityArrow) shooter = ((EntityArrow) projectile).shootingEntity;
                    else if (projectile instanceof EntityThrowable)
                        shooter = ((EntityThrowable) projectile).getThrower();

                    if (shooter != null && shooter.equals(player)) continue;

                    double delX = projectile.posX - player.posX;
                    double delY = projectile.posY - player.posY;
                    double delZ = projectile.posZ - player.posZ;

                    double angle = (delX * projectile.motionX + delY * projectile.motionY + delZ * projectile.motionZ)
                            / (Math.sqrt(delX * delX + delY * delY + delZ * delZ) * Math.sqrt(
                                    projectile.motionX * projectile.motionX + projectile.motionY * projectile.motionY
                                            + projectile.motionZ * projectile.motionZ));
                    angle = Math.acos(angle);
                    if (angle < 3 * (Math.PI / 4)) // angle is < 135 degrees
                        continue;

                    if (shooter != null) {
                        delX = -projectile.posX + shooter.posX;
                        delY = -projectile.posY + (shooter.posY + shooter.getEyeHeight());
                        delZ = -projectile.posZ + shooter.posZ;
                    }

                    double curVel = Math.sqrt(delX * delX + delY * delY + delZ * delZ);
                    delX /= curVel;
                    delY /= curVel;
                    delZ /= curVel;
                    double newVel = Math.sqrt(
                            projectile.motionX * projectile.motionX + projectile.motionY * projectile.motionY
                                    + projectile.motionZ * projectile.motionZ);
                    projectile.motionX = newVel * delX;
                    projectile.motionY = newVel * delY;
                    projectile.motionZ = newVel * delZ;
                }
                break;
            case 2:
                if (this.armorType == 3) {
                    player.addPotionEffect(new PotionEffect(WGContent.pot_knockbackRes.id, 202, 0, true));
                }
                break;
            case 3:
                if (this.armorType == 0) {
                    if (!world.isDaytime() || player.getBrightness(0) < 4)
                        player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 202, 0, true));
                }
                if (player.isBurning()) {
                    player.extinguish();
                }
                break;
            case 4: // water|aqua
                if (this.armorType == 0) {
                    player.setAir(300);
                    player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 202, 0, true));
                    if (player.isInsideOfMaterial(Material.water))
                        player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 202, 0, true));
                }

                int[] curedPotions = { Potion.blindness.id, Potion.poison.id, Potion.wither.id, Potion.confusion.id,
                        Config.potionTaintPoisonID, Potion.digSlowdown.id, Potion.hunger.id, Potion.weakness.id };
                for (int c : curedPotions) if (world.isRemote) player.removePotionEffectClient(c);
                else player.removePotionEffect(c);
                break;
            case 5:
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 202, 0, true));
                break;
            default:
                break;
        }
    }

    public static class abilityHandler {

        public static boolean playerHasFoot(EntityPlayer player) {
            ItemStack armour = player.getCurrentArmor(0);
            return armour != null && armour.getItem() == WGContent.ItemPrimordialBoots;
        }

        @SubscribeEvent
        public void jumpBoost(LivingJumpEvent event) {
            if (event.entityLiving instanceof EntityPlayer player) {
                ItemStack boots = player.getCurrentArmor(0);
                if (playerHasFoot(player)) {
                    player.motionY += 0.55f * getJumpModifier(boots);
                }
            }
        }

    }

    public void movementEffects(EntityPlayer player, float bonus, ItemStack itemStack) {
        if (player.moveForward != 0.0F || player.moveStrafing != 0.0F || player.motionY != 0.0F) {
            if (WitchingGadgets.isBootsActive) {
                boolean omniMode = getOmniState(itemStack);
                if (player.moveForward <= 0F && !omniMode) {
                    return;
                }
            }
            if (player.worldObj.isRemote && !player.isSneaking() && getStepAssistState(itemStack)) {
                if (!Thaumcraft.instance.entityEventHandler.prevStep.containsKey(player.getEntityId())) {
                    Thaumcraft.instance.entityEventHandler.prevStep.put(player.getEntityId(), player.stepHeight);
                }
                player.stepHeight = 1.0F;
            }

            float speedMod = (float) getSpeedModifier(itemStack);
            float JUMP_BONUS = 0.55F / 0.2750000059604645F;
            if (player.onGround || player.capabilities.isFlying || player.isOnLadder()) {

                if (WGModCompat.loaded_TaintedMagic) {
                    bonus += ItemVoidwalkerBoots.sashBuff(player);
                }
                bonus *= speedMod;
                if (WitchingGadgets.isBootsActive) {
                    applyOmniState(player, bonus, itemStack);
                } else if (player.moveForward > 0.0) {
                    player.moveFlying(
                            0.0F,
                            player.moveForward,
                            player.capabilities.isFlying ? (bonus - 0.075F) : bonus);
                }
                player.jumpMovementFactor = 0.00002F;
            } else if (Hover.getHover(player.getEntityId())) {
                player.jumpMovementFactor = (0.03F * JUMP_BONUS - 0.02F) * speedMod + 0.02F;
            } else {
                player.jumpMovementFactor = (0.05F * JUMP_BONUS - 0.02F) * speedMod + 0.02F;
            }
        }
    }

    // Thaumic Boots Methods:

    @Optional.Method(modid = "thaumicboots")
    public void applyOmniState(EntityPlayer player, float bonus, ItemStack itemStack) {
        if (player.moveForward != 0.0) {
            player.moveFlying(0.0F, player.moveForward, bonus);
        }
        if (getOmniState(itemStack)) {
            if (player.moveStrafing != 0.0) {
                player.moveFlying(player.moveStrafing, 0.0F, bonus);
            }
            boolean jumping = ((EntityLivingBaseAccessor) player).getIsJumping();
            boolean sneaking = player.isSneaking();
            if (sneaking && !jumping && !player.onGround) {
                player.motionY -= bonus;
            } else if (jumping && !sneaking) {
                player.motionY += bonus;
            }
        }
    }

    @Optional.Method(modid = "thaumicboots")
    public double applyJump(EntityPlayer player) {
        return IBoots.isJumpEnabled(player.inventory.armorItemInSlot(0));
    }

    // Avoid NSM Exception when ThaumicBoots is not present.
    public double getSpeedModifier(ItemStack stack) {
        if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("speed")) {
            return stack.stackTagCompound.getDouble("speed");
        }
        return 1.0;
    }

    public static double getJumpModifier(ItemStack stack) {
        if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("jump")) {
            return stack.stackTagCompound.getDouble("jump");
        }
        return 1.0;
    }

    public boolean getOmniState(ItemStack stack) {
        if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("omni")) {
            return stack.stackTagCompound.getBoolean("omni");
        }
        return true;
    }

    public boolean getStepAssistState(ItemStack stack) {
        if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("step")) {
            return stack.stackTagCompound.getBoolean("step");
        }
        return true;
    }

    public boolean getInertiaState(ItemStack stack) {
        if (stack.stackTagCompound != null) {
            return stack.stackTagCompound.getBoolean("inertiacanceling");
        }
        return false;
    }

    @Override
    public boolean getIsRepairable(ItemStack s, ItemStack s2) {
        return false;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage,
            int slot) {
        int priority = 0;
        double ratio = this.damageReduceAmount / 12.0D;
        if (source.isMagicDamage()) {
            priority = 1;
            ratio = this.damageReduceAmount / 15.0D;
        } else if (source.isFireDamage() || source.isExplosion()) {
            priority = 1;
            ratio = getAbility(armor) == 3 ? .75f : (this.damageReduceAmount / 10.0D);
        } else if (source.isUnblockable()) {
            int ab = (getAbility(armor) - 1);
            priority = ab == 1 ? 1 : 0;
            ratio = ab == 1 ? this.damageReduceAmount / 25.0D : 0.0D;
        }
        if ((player instanceof EntityPlayer)) {
            double set = 0.875D;
            for (int a = 1; a <= 4; a++) {
                ItemStack piece = player.getEquipmentInSlot(a);
                if (piece != null && piece.getItem() instanceof ItemPrimordialArmor) {
                    set += 0.125D;
                    if (piece.hasTagCompound() && piece.stackTagCompound.hasKey("mask")) set += 0.05D;
                }
            }
            ratio *= set;
        }
        return new ISpecialArmor.ArmorProperties(priority, ratio, Integer.MAX_VALUE);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        int ab = (getAbility(stack) - 1);
        String add = ab >= 0 && ab < 6 ? " " + EnumChatFormatting.DARK_GRAY
                + "- \u00a7"
                + Aspect.getPrimalAspects().get(ab).getChatcolor()
                + Aspect.getPrimalAspects().get(ab).getName()
                + EnumChatFormatting.RESET : "";

        list.add(EnumChatFormatting.DARK_GRAY + StatCollector.translateToLocal("wg.desc.primal") + add);
        GameSettings keybind = Minecraft.getMinecraft().gameSettings;
        list.add(
                StatCollector.translateToLocal(Lib.DESCRIPTION + "cycleArmor")
                        .replaceAll(
                                "%s1",
                                StatCollector.translateToLocalFormatted(
                                        GameSettings.getKeyDisplayString(keybind.keyBindSneak.getKeyCode())))
                        .replaceAll(
                                "%s2",
                                StatCollector.translateToLocalFormatted(
                                        GameSettings.getKeyDisplayString(keybind.keyBindUseItem.getKeyCode()))));
        list.add(
                EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount")
                        + ": "
                        + this.getVisDiscount(stack, player, null)
                        + "%");
        super.addInformation(stack, player, list, par4);
    }

    @Override
    public int getVisDiscount(ItemStack s, EntityPlayer p, Aspect a) {
        if (getAbility(s) == 5) return 10;
        else return 6;
    }

    @Override
    public int getWarp(ItemStack s, EntityPlayer p) {
        if (getAbility(s) == 5) return 5;
        else return 10;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return ModelPrimordialArmor.getModel(entityLiving, itemStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "witchinggadgets:textures/models/primordialArmor.png";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("witchinggadgets:primordialArmor" + this.armorType);
        this.rune = iconRegister.registerIcon("witchinggadgets:primordialArmorRune");
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public IIcon getIconFromDamageForRenderPass(int par1, int pass) {
        return pass == 0 ? rune : itemIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int pass) {
        if (pass == 0) {
            int ab = (getAbility(stack) - 1);
            if (ab >= 0 && ab < 6) return Aspect.getPrimalAspects().get(getAbility(stack) - 1).getColor();
        }
        return 0xffffff;
    }

    @Override
    public int getReturnedPearls(ItemStack stack) {
        return 3;
    }

    @Override
    public void cycleAbilities(ItemStack stack) {
        if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        int cur = stack.getTagCompound().getInteger("currentMode");
        // cur 1 = aer,2=terra,3=ignis, 4=aqua, 5=ordo, 6=perdidito,
        cur++;
        if (cur >= 7) cur = 1;
        stack.getTagCompound().setInteger("currentMode", cur);
    }

    @Override
    public int getAbility(ItemStack stack) {
        if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        return stack.getTagCompound().getInteger("currentMode");
    }

    private boolean isThis(ItemStack stack) {
        if (!stack.hasTagCompound()) return false;
        else return stack.getTagCompound().getInteger("currentMode") != 0;
    }

    @Override
    public void onUserDamaged(LivingHurtEvent event, ItemStack stack) {
        if (event.entityLiving instanceof EntityPlayer player) {

            int[] modescounter = { 0, 0, 0, 0, 0, 0 };

            boolean helmet = player.getCurrentArmor(0) != null && isThis(player.getCurrentArmor(0));
            boolean chestplate = player.getCurrentArmor(1) != null && isThis(player.getCurrentArmor(1));
            boolean leggings = player.getCurrentArmor(2) != null && isThis(player.getCurrentArmor(2));
            boolean boots = player.getCurrentArmor(3) != null && isThis(player.getCurrentArmor(3));

            int[] modes = new int[] { helmet ? getAbility(player.getCurrentArmor(0)) : 0,
                    chestplate ? getAbility(player.getCurrentArmor(1)) : 0,
                    leggings ? getAbility(player.getCurrentArmor(2)) : 0,
                    boots ? getAbility(player.getCurrentArmor(3)) : 0, };

            for (int i : modes) {
                if (i == 1) ++modescounter[0];
                if (i == 2) ++modescounter[1];
                if (i == 3) ++modescounter[2];
                if (i == 4) ++modescounter[3];
                if (i == 5) ++modescounter[4];
                if (i == 6) ++modescounter[5];
            }

            switch (getAbility(stack)) {
                case 1: {
                    if (event.source.isProjectile()) event.setCanceled(true);
                    break;
                }
                case 2:
                    player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 202, 1 + modescounter[1], true));
                    break;
                case 3: {
                    if (event.source.getSourceOfDamage() instanceof EntityLivingBase source) {
                        source.setFire(5 * modescounter[2]);
                    }
                    break;
                }

                case 6: {
                    if (event.source.getSourceOfDamage() instanceof EntityLivingBase source) {
                        source.addPotionEffect(new PotionEffect(Potion.blindness.id, 200 * modescounter[5], 0));
                        source.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200 * modescounter[5], 3));
                    }
                    break;
                }

                default:
                    break;
            }
        }
    }

    @Override
    public void onUserAttacking(AttackEntityEvent event, ItemStack stack) {}

    @Override
    public void onUserJump(LivingJumpEvent event, ItemStack stack) {}

    @Override
    public void onUserFall(LivingFallEvent event, ItemStack stack) {
        event.distance = 0F;
        event.setCanceled(true);
    }

    public int getArmorDisplay(EntityPlayer p, ItemStack s, int slot) {
        return this.damageReduceAmount;
    }

    @Override
    public void onUserTargeted(LivingSetAttackTargetEvent event, ItemStack stack) {}

    @Override
    @Optional.Method(modid = "gregtech_nh")
    public boolean protectsAgainst(ItemStack itemStack, Hazard hazard) {
        return true;
    }
}
