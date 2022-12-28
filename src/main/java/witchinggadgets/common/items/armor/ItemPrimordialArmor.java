package witchinggadgets.common.items.armor;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
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
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import taintedmagic.common.items.equipment.ItemShadowFortressArmor;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.armor.Hover;
import travellersgear.api.IActiveAbility;
import travellersgear.api.IEventGear;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.api.IPrimordialCrafting;
import witchinggadgets.client.render.ModelPrimordialArmor;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.items.tools.IPrimordialGear;

enum FlightStatus {
    ON,
    OFF
}

public class ItemPrimordialArmor extends ItemShadowFortressArmor
        implements IActiveAbility, IPrimordialCrafting, IEventGear, IPrimordialGear, IRunicArmor {
    IIcon rune;
    byte tickcounter = 0;

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
        return this.armorType == 0
                && stack.hasTagCompound()
                && stack.getTagCompound().hasKey("goggles");
    }

    @Override
    public boolean showIngamePopups(ItemStack stack, EntityLivingBase living) {
        return this.armorType == 0
                && stack.hasTagCompound()
                && stack.getTagCompound().hasKey("goggles");
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean equipped) {
        super.onUpdate(stack, world, entity, slot, equipped);
        if ((!world.isRemote)
                && (stack.isItemDamaged())
                && (entity.ticksExisted % 40 == 0)
                && ((entity instanceof EntityLivingBase))) stack.damageItem(-1, (EntityLivingBase) entity);
    }

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;

            int amorcounter = 0;
            int modescounter = 0;

            boolean helmet = player.getCurrentArmor(0) != null ? isThis(player.getCurrentArmor(0)) : false;
            boolean chestplate = player.getCurrentArmor(1) != null ? isThis(player.getCurrentArmor(1)) : false;
            boolean leggings = player.getCurrentArmor(2) != null ? isThis(player.getCurrentArmor(2)) : false;
            boolean boots = player.getCurrentArmor(3) != null ? isThis(player.getCurrentArmor(3)) : false;

            int[] modes = new int[] {
                helmet ? getAbility(player.getCurrentArmor(0)) : 0,
                chestplate ? getAbility(player.getCurrentArmor(1)) : 0,
                leggings ? getAbility(player.getCurrentArmor(2)) : 0,
                boots ? getAbility(player.getCurrentArmor(3)) : 0,
            };

            if (helmet) ++amorcounter;
            if (chestplate) ++amorcounter;
            if (leggings) ++amorcounter;
            if (boots) ++amorcounter;

            for (int i : modes) if (i == 1) ++modescounter;

            /*if(leggings && getAbility(player.getCurrentArmor(2))==3)
            	player.capabilities.setPlayerWalkSpeed(0.75F);
            else
            	player.capabilities.setPlayerWalkSpeed(0.1F);

            if(boots && player.capabilities.isFlying && getAbility(player.getCurrentArmor(3)) == 6)
            	player.noClip=true;
            else
            	player.noClip=false;*/

        }
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        ++tickcounter;

        if (tickcounter >= 100) tickcounter = 0;

        byte amorcounter = 0;
        byte[] modescounter = {0, 0, 0, 0, 0, 0};

        boolean helmet = player.getCurrentArmor(0) != null ? isThis(player.getCurrentArmor(0)) : false;
        boolean chestplate = player.getCurrentArmor(1) != null ? isThis(player.getCurrentArmor(1)) : false;
        boolean leggings = player.getCurrentArmor(2) != null ? isThis(player.getCurrentArmor(2)) : false;
        boolean boots = player.getCurrentArmor(3) != null ? isThis(player.getCurrentArmor(3)) : false;

        int[] modes = new int[] {
            helmet ? getAbility(player.getCurrentArmor(0)) : 0,
            chestplate ? getAbility(player.getCurrentArmor(1)) : 0,
            leggings ? getAbility(player.getCurrentArmor(2)) : 0,
            boots ? getAbility(player.getCurrentArmor(3)) : 0,
        };

        if (helmet) ++amorcounter;
        if (chestplate) ++amorcounter;
        if (leggings) ++amorcounter;
        if (boots) ++amorcounter;

        for (int i : modes) {
            if (i == 1) ++modescounter[0];
            if (i == 2) ++modescounter[1];
            if (i == 3) ++modescounter[2];
            if (i == 4) ++modescounter[3];
            if (i == 5) ++modescounter[4];
            if (i == 6) ++modescounter[5];
        }

        if ((!world.isRemote) && (stack.getItemDamage() > 0) && (tickcounter == 1)) stack.damageItem(-1, player);

        if (this.armorType == 3) {
            if (!player.capabilities.isFlying && player.moveForward > 0.0F) {
                if (player.worldObj.isRemote && !player.isSneaking()) {
                    if (!Thaumcraft.instance.entityEventHandler.prevStep.containsKey(
                            Integer.valueOf(player.getEntityId())))
                        Thaumcraft.instance.entityEventHandler.prevStep.put(
                                Integer.valueOf(player.getEntityId()), Float.valueOf(player.stepHeight));
                    player.stepHeight = 1.0F;
                }
                if (player.onGround) {
                    float bonus = 0.055F;
                    if (player.isInWater()) bonus /= 4.0F;
                    player.moveFlying(0.0F, 1.0F, bonus);
                } else if (Hover.getHover(player.getEntityId())) player.jumpMovementFactor = 0.03F;
                else player.jumpMovementFactor = 0.05F;
            }
        }

        if (amorcounter >= 2 && modescounter[2] >= 2 && player.isInsideOfMaterial(Material.lava)) {
            player.setAir(300);
            player.addPotionEffect(new PotionEffect(Potion.blindness.id, 202, 0, true));
            player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 202, 0, true));
        }

        // turn flight off or on
        if ((amorcounter >= 2 && modescounter[0] >= 2)) {
            flightStatus = FlightStatus.ON;
            player.capabilities.allowFlying = true;
        } else if (flightStatus == FlightStatus.ON) {
            flightStatus = FlightStatus.OFF;
            player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
        }

        /*if (modes[2]==3) {
        	player.capabilities.setPlayerWalkSpeed(0.75F);
        } else if (modes[2]!=3) {
        	player.capabilities.setPlayerWalkSpeed(0.1F);
        }

        if(player.capabilities.isFlying && modes[3]==6)
        	player.noClip=true;
        else if(!player.capabilities.isFlying || modes[3]!=6)
        	player.noClip=false;*/

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
                                player.posZ + .5)
                        .expand(4, 4, 4);
                for (Entity projectile : (List<Entity>) world.getEntitiesWithinAABB(Entity.class, aabb)) {
                    if (projectile == null) continue;
                    if (!(projectile instanceof IProjectile)
                            || projectile.getClass().getSimpleName().equalsIgnoreCase("IManaBurst")) continue;

                    Entity shooter = null;
                    if (projectile instanceof EntityArrow) shooter = ((EntityArrow) projectile).shootingEntity;
                    else if (projectile instanceof EntityThrowable)
                        shooter = ((EntityThrowable) projectile).getThrower();

                    if (shooter != null && shooter.equals(player)) continue;

                    double delX = projectile.posX - player.posX;
                    double delY = projectile.posY - player.posY;
                    double delZ = projectile.posZ - player.posZ;

                    double angle = (delX * projectile.motionX + delY * projectile.motionY + delZ * projectile.motionZ)
                            / (Math.sqrt(delX * delX + delY * delY + delZ * delZ)
                                    * Math.sqrt(projectile.motionX * projectile.motionX
                                            + projectile.motionY * projectile.motionY
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
                    double newVel = Math.sqrt(projectile.motionX * projectile.motionX
                            + projectile.motionY * projectile.motionY
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
                break;
            case 4: // water|aqua
                if (this.armorType == 0) {
                    player.setAir(300);
                    player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 202, 0, true));
                    if (player.isInsideOfMaterial(Material.water))
                        player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 202, 0, true));
                }

                int[] curedPotions = {
                    Potion.blindness.id,
                    Potion.poison.id,
                    Potion.wither.id,
                    Potion.confusion.id,
                    Config.potionTaintPoisonID,
                    Potion.digSlowdown.id,
                    Potion.hunger.id,
                    Potion.weakness.id
                };
                for (int c : curedPotions)
                    if (world.isRemote) player.removePotionEffectClient(c);
                    else player.removePotionEffect(c);
                break;
            case 5:
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 202, 0, true));
                break;
            case 6:
                break;
            default:
                break;
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack s, ItemStack s2) {
        return false;
    }

    @Override
    public ArmorProperties getProperties(
            EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        int priority = 0;
        double ratio = this.damageReduceAmount / 12.0D;
        if (source.isMagicDamage()) {
            priority = 1;
            ratio = this.damageReduceAmount / 15.0D;
        } else if (source.isFireDamage() || source.isExplosion()) {
            if (source.isFireDamage() && getAbility(armor) == 3) {
                if (player.isBurning()) player.extinguish();
            }
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

    @Override
    public boolean canActivate(EntityPlayer player, ItemStack stack, boolean isInHand) {
        return true;
        //		return getUpgrade(stack)!=null;
    }

    @Override
    public void activate(EntityPlayer player, ItemStack stack) {
        if (!player.worldObj.isRemote) cycleAbilities(stack);
        //		toggleActive(stack);
    }

    //	public PrimordialArmorUpgrade getUpgrade(ItemStack stack)
    //	{
    //		return PrimordialArmorUpgrade.AIR;
    //		//		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("primordialUpgrade"))
    //		//		{
    //		//			int i = stack.getTagCompound().getByte("primordialUpgrade");
    //		//			if(i>=0&&i<PrimordialArmorUpgrade.values().length)
    //		//				return PrimordialArmorUpgrade.values()[i];
    //		//		}
    //		//		return null;
    //	}
    //	boolean effectActive(ItemStack stack)
    //	{
    //		return !stack.hasTagCompound() || !stack.getTagCompound().getBoolean("disabled");
    //	}
    //	void toggleActive(ItemStack stack)
    //	{
    //		if(!stack.hasTagCompound())
    //			stack.setTagCompound(new NBTTagCompound());
    //		stack.getTagCompound().setBoolean("disabled", !stack.getTagCompound().getBoolean("disabled"));
    //	}
    /*
    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
    	int ab = getAbility(stack);
    	String add = ab>=0&&ab<6? " "+EnumChatFormatting.DARK_GRAY+"- \u00a7"+Aspect.getPrimalAspects().get(ab).getChatcolor()+Aspect.getPrimalAspects().get(ab).getName()+EnumChatFormatting.RESET : "";
    	return super.getItemStackDisplayName(stack)+add;
    }*/

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        int ab = (getAbility(stack) - 1);
        String add = ab >= 0 && ab < 6
                ? " " + EnumChatFormatting.DARK_GRAY + "- \u00a7"
                        + Aspect.getPrimalAspects().get(ab).getChatcolor()
                        + Aspect.getPrimalAspects().get(ab).getName() + EnumChatFormatting.RESET
                : "";

        list.add(EnumChatFormatting.DARK_GRAY + StatCollector.translateToLocal("wg.desc.primal") + add);
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
            if (ab >= 0 && ab < 6)
                return Aspect.getPrimalAspects().get(getAbility(stack) - 1).getColor();
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
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;

            int amorcounter = 0;
            int[] modescounter = {0, 0, 0, 0, 0, 0};

            boolean helmet = player.getCurrentArmor(0) != null ? isThis(player.getCurrentArmor(0)) : false;
            boolean chestplate = player.getCurrentArmor(1) != null ? isThis(player.getCurrentArmor(1)) : false;
            boolean leggings = player.getCurrentArmor(2) != null ? isThis(player.getCurrentArmor(2)) : false;
            boolean boots = player.getCurrentArmor(3) != null ? isThis(player.getCurrentArmor(3)) : false;

            int[] modes = new int[] {
                helmet ? getAbility(player.getCurrentArmor(0)) : 0,
                chestplate ? getAbility(player.getCurrentArmor(1)) : 0,
                leggings ? getAbility(player.getCurrentArmor(2)) : 0,
                boots ? getAbility(player.getCurrentArmor(3)) : 0,
            };

            if (helmet) ++amorcounter;
            if (chestplate) ++amorcounter;
            if (leggings) ++amorcounter;
            if (boots) ++amorcounter;

            for (int i : modes) {
                if (i == 1) ++modescounter[0];
                if (i == 2) ++modescounter[1];
                if (i == 3) ++modescounter[2];
                if (i == 4) ++modescounter[3];
                if (i == 5) ++modescounter[4];
                if (i == 6) ++modescounter[5];
                if (i == 7) ++modescounter[6];
            }

            switch (getAbility(stack)) {
                case 1: {
                    if (event.source.isProjectile()) event.setCanceled(true);
                    break;
                }
                case 2:
                    player.addPotionEffect(new PotionEffect(Potion.heal.id, 202, modescounter[1], true));
                    break;
                case 3: {
                    if (event.source.getSourceOfDamage() instanceof EntityLivingBase) {
                        ((EntityLivingBase) event.source.getSourceOfDamage()).setFire(5 * modescounter[2]);
                    }
                    break;
                }
                case 4:
                    break;
                case 5:
                    break;

                case 6: {
                    if (event.source.getSourceOfDamage() instanceof EntityLivingBase) {
                        ((EntityLivingBase) event.source.getSourceOfDamage())
                                .addPotionEffect(new PotionEffect(Potion.blindness.id, 200 * modescounter[5], 0));
                        ((EntityLivingBase) event.source.getSourceOfDamage())
                                .addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200 * modescounter[5], 3));
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
    //	public enum PrimordialArmorUpgrade
    //	{
    //		AIR(new AspectList().add(Aspect.AIR,32).add(Aspect.MOTION,32).add(Aspect.ARMOR,32), new
    // ItemStack(ConfigItems.itemShard,1,0),new ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.arrow),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.arrow),new ItemStack(ConfigItems.itemWispEssence),new
    // ItemStack(ConfigItems.itemShard,1,0),new ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.arrow),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.arrow),new ItemStack(ConfigItems.itemWispEssence)),
    //		FIRE(new AspectList().add(Aspect.FIRE,32).add(Aspect.LIGHT,32).add(Aspect.ENERGY,32), new
    // ItemStack(ConfigItems.itemShard,1,1),new ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.arrow),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.arrow),new ItemStack(ConfigItems.itemWispEssence),new
    // ItemStack(ConfigItems.itemShard,1,1),new ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.arrow),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.arrow),new ItemStack(ConfigItems.itemWispEssence)),
    //		EARTH(new AspectList().add(Aspect.EARTH,32).add(Aspect.ARMOR,32).add(Aspect.METAL,32), new
    // ItemStack(ConfigItems.itemShard,1,2),new ItemStack(ConfigItems.itemWispEssence),new
    // ItemStack(Blocks.obsidian),new ItemStack(ConfigItems.itemWispEssence),new ItemStack(Blocks.obsidian),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(ConfigItems.itemShard,1,2),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(Blocks.obsidian),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(Blocks.obsidian),new
    // ItemStack(ConfigItems.itemWispEssence)),
    //		WATER(new AspectList().add(Aspect.WATER,32).add(Aspect.POISON,32).add(Aspect.HEAL,32), new
    // ItemStack(ConfigItems.itemShard,1,3),new ItemStack(ConfigItems.itemWispEssence),new
    // ItemStack(Items.milk_bucket),new ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.milk_bucket),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(ConfigItems.itemShard,1,3),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.milk_bucket),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.milk_bucket),new
    // ItemStack(ConfigItems.itemWispEssence)),
    //		ORDER(new AspectList().add(Aspect.ORDER,32).add(Aspect.HEAL,32).add(Aspect.EXCHANGE,32), new
    // ItemStack(ConfigItems.itemShard,1,4),new ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.arrow),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.arrow),new ItemStack(ConfigItems.itemWispEssence),new
    // ItemStack(ConfigItems.itemShard,1,4),new ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.arrow),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(Items.arrow),new ItemStack(ConfigItems.itemWispEssence)),
    //		ENTROPY(new AspectList().add(Aspect.ENTROPY,32).add(Aspect.TRAP,32).add(Aspect.DARKNESS,32), new
    // ItemStack(ConfigItems.itemShard,1,5),new ItemStack(ConfigItems.itemWispEssence),new
    // ItemStack(Blocks.soul_sand),new ItemStack(ConfigItems.itemWispEssence),new ItemStack(Blocks.soul_sand),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(ConfigItems.itemShard,1,5),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(Blocks.soul_sand),new
    // ItemStack(ConfigItems.itemWispEssence),new ItemStack(Blocks.soul_sand),new
    // ItemStack(ConfigItems.itemWispEssence));
    //
    //		final ItemStack[] components;
    //		final AspectList aspects;
    //		PrimordialArmorUpgrade(AspectList aspects, ItemStack... components)
    //		{
    //			this.components = components;
    //			this.aspects = aspects;
    //		}
    //
    //		public ItemStack[] getCompenents()
    //		{
    //			return components;
    //		}
    //		public AspectList getAspects()
    //		{
    //			return aspects;
    //		}
    //	}
}
