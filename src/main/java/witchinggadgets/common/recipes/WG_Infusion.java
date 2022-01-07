package witchinggadgets.common.recipes;

import cpw.mods.fml.common.Loader;
import fox.spiteful.forbidden.DarkAspects;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;

public class WG_Infusion {
	static AspectList infusionAspects;
	static AspectList alchemyAspects;
	static ItemStack luckyCoin = new ItemStack(ConfigItems.itemResource,1,18);
	
	public static void register_infusion() {
		
	luckyCoin.addEnchantment(Enchantment.fortune, 1);
	luckyCoin.addEnchantment(Enchantment.looting, 1);
	
	
		
	infusionAspects = new AspectList().add(Aspect.ENTROPY,32).add(Aspect.EARTH,48).add(Aspect.MINE, 64);
	registerInfusionRecipe("STONEEXTRUDER","",new ItemStack(WGContent.BlockWoodenDevice,1,2),7, infusionAspects, ItemList.Machine_HV_RockBreaker.get(1L), new ItemStack[] {ItemList.Conveyor_Module_HV.get(1L),new ItemStack(ConfigBlocks.blockCrystal, 1, 5),new ItemStack(Items.lava_bucket),new ItemStack(ConfigBlocks.blockCrystal, 1, 3), ItemList.IC2_EnergyCrystal.getWildcard(1L),new ItemStack(ConfigBlocks.blockCrystal, 1, 3),new ItemStack(Items.water_bucket) ,new ItemStack(ConfigBlocks.blockCrystal, 1, 5)} );
	
	infusionAspects = new AspectList().add(Aspect.WEATHER,32).add(Aspect.COLD,48).add(Aspect.TOOL, 64);
	registerInfusionRecipe("ICESOLIDIFIER","",new ItemStack(WGContent.BlockWoodenDevice,1,1),7, infusionAspects, ItemList.Machine_HV_FluidSolidifier.get(1L), new ItemStack[] {ItemList.Conveyor_Module_HV.get(1L),new ItemStack(ConfigBlocks.blockCrystal, 1, 2),new ItemStack(Blocks.ice),new ItemStack(ConfigBlocks.blockCrystal, 1, 4), ItemList.IC2_EnergyCrystal.getWildcard(1L),new ItemStack(ConfigBlocks.blockCrystal, 1, 4),new ItemStack(Blocks.packed_ice) ,new ItemStack(ConfigBlocks.blockCrystal, 1, 2)} );
	
	
	if (WGConfig.moduleBag) {
	if (WGConfig.bagEnder) {
		infusionAspects = new AspectList().add(Aspect.VOID, 64).add(Aspect.ELDRITCH, 64).add(Aspect.MAGIC, 128).add(Aspect.TRAVEL,8).add(Aspect.TOOL,32);
		registerInfusionRecipe("ENDERBAG","",new ItemStack(WGContent.ItemBag,1,2),8,infusionAspects,new ItemStack(WGContent.ItemBag,1,0),new ItemStack[] {new ItemStack(Blocks.ender_chest), new ItemStack(WGContent.ItemMaterial,1,3),new ItemStack(WGContent.ItemMaterial,1,5), new ItemStack(Items.ender_eye), new ItemStack(WGContent.ItemMaterial,1,3),new ItemStack(WGContent.ItemMaterial,1,5)});
	}
	if (WGConfig.bagVoid) {
		infusionAspects = new AspectList().add(Aspect.VOID, 256).add(Aspect.ELDRITCH, 32).add(Aspect.ENTROPY, 64);
		registerInfusionRecipe("VOIDBAG","",new ItemStack(WGContent.ItemBag,1,1),6,infusionAspects,new ItemStack(WGContent.ItemBag,1,0),new ItemStack[] {GT_ModHandler.getModItem("Railcraft", "machine.beta", 1L, 11), new ItemStack(WGContent.ItemMaterial,1,3), new ItemStack(ConfigItems.itemResource,1,17), new ItemStack(WGContent.ItemMaterial,1,3) });
	}
	
	if (WGConfig.capeSpectral) {
		infusionAspects = new AspectList().add(Aspect.SOUL, 48).add(Aspect.TRAVEL, 48).add(Aspect.ELDRITCH, 24).add(Aspect.SENSES,24);
		registerInfusionRecipe("CLOAK_SPECTRAL","",new ItemStack(WGContent.ItemCloak,1,1),5,infusionAspects,new ItemStack(WGContent.ItemCloak),new ItemStack[] {new ItemStack(Items.potionitem,1,8270),new ItemStack(WGContent.ItemMaterial,1,5),new ItemStack(Items.ender_pearl),new ItemStack(WGContent.ItemMaterial,1,5)});
	}
	}
	if(Config.allowMirrors)
	{
		//WALLMIRROR
		infusionAspects = new AspectList().add(Aspect.VOID, 20).add(Aspect.TRAVEL, 20).add(Aspect.ELDRITCH, 20).add(Aspect.CRYSTAL, 20);
		registerInfusionRecipe("WALLMIRROR","",new ItemStack(WGContent.BlockWallMirror),8,infusionAspects,new ItemStack(ConfigBlocks.blockMirror),new ItemStack[] {new ItemStack(ConfigItems.itemFocusPortableHole),new ItemStack(ConfigItems.itemShard, 1, 5),new ItemStack(Items.ender_pearl),new ItemStack(Items.gold_ingot),new ItemStack(Items.gold_ingot),new ItemStack(Blocks.quartz_block,1,1)});
	
	}

	/**
	 * INFUSION
	 */
	
	infusionAspects = new AspectList().add(Aspect.MINE, 48).add(Aspect.TOOL, 24).add(Aspect.MOTION, 24).add(Aspect.AIR,16).add((Aspect)gregtech.api.enums.TC_Aspects.NEBRISUM.mAspect, 8);
	registerInfusionRecipe("WGBAUBLES","_HASTEVAMBRACES",new ItemStack(WGContent.ItemMagicalBaubles,1,3),2,infusionAspects,OreDictionary.getOres("travelgearVambraceBase").get(0),new ItemStack[] {Materials.Platinum.getIngots(1),ItemList.IC2_CoffeePowder.get(1L, Materials.Coffee.getDust(1)),new ItemStack(Items.potionitem,1,8194),ItemList.IC2_CoffeePowder.get(1L, Materials.Coffee.getDust(1))});

	infusionAspects = new AspectList().add(Aspect.FLIGHT,16).add(Aspect.MOTION, 8).add(Aspect.AIR,16).add((Aspect)gregtech.api.enums.TC_Aspects.NEBRISUM.mAspect, 8);
	registerInfusionRecipe("WGBAUBLES","_DOUBLEJUMPSHOULDERS",new ItemStack(WGContent.ItemMagicalBaubles,1,0),2,infusionAspects,OreDictionary.getOres("travelgearShoulderBase").get(0),new ItemStack[] {new ItemStack(WGModCompat.tfMagicMapFocus),ItemList.Electric_Piston_MV.get(1L),new ItemStack(WGModCompat.tfMagicMapFocus),new ItemStack(ConfigItems.itemShard,1,0),new ItemStack(WGModCompat.tfMagicMapFocus),ItemList.Electric_Piston_MV.get(1L)});

	infusionAspects = new AspectList().add(Aspect.AIR,32).add(Aspect.WEAPON, 16).add(Aspect.ORDER,8).add((Aspect)gregtech.api.enums.TC_Aspects.NEBRISUM.mAspect, 8);
	registerInfusionRecipe("WGBAUBLES","_SNIPERRING",new ItemStack(WGContent.ItemMagicalBaubles,1,6),2,infusionAspects,new ItemStack(ConfigItems.itemBaubleBlanks,1,1),new ItemStack[] {GT_OreDictUnificator.get(OrePrefixes.lens,Materials.InfusedAir,1L),new ItemStack(ConfigItems.itemPrimalArrow,1,0), new ItemStack(ConfigItems.itemPrimalArrow,1,1),GT_OreDictUnificator.get(OrePrefixes.lens,Materials.InfusedAir,1L),new ItemStack(ConfigItems.itemPrimalArrow,1,2),new ItemStack(ConfigItems.itemPrimalArrow,1,3),GT_OreDictUnificator.get(OrePrefixes.lens,Materials.InfusedAir,1L),new ItemStack(ConfigItems.itemPrimalArrow,1,4),new ItemStack(ConfigItems.itemPrimalArrow,1,5)});

	infusionAspects = new AspectList().add(Aspect.GREED,32).add(Aspect.TOOL,16).add((Aspect)gregtech.api.enums.TC_Aspects.NEBRISUM.mAspect, 8);
	registerInfusionRecipe("WGBAUBLES","_LUCKRING",new ItemStack(WGContent.ItemMagicalBaubles,1,4),3,infusionAspects,new ItemStack(ConfigItems.itemBaubleBlanks,1,1),new ItemStack[] {luckyCoin,GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Silver, 1L),luckyCoin,GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Silver, 1L),luckyCoin,GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Silver, 1L),luckyCoin,GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Silver, 1L)});

	infusionAspects = new AspectList().add(Aspect.TRAVEL,24).add(Aspect.MIND, 16).add(Aspect.TOOL,8);
	registerInfusionRecipe("LABYRINTHSTRING","",new ItemStack(WGContent.ItemMaterial,1,11),2,infusionAspects,new ItemStack(ConfigBlocks.blockMagicalLog,1,1),new ItemStack[] {new ItemStack(Items.ender_pearl),new ItemStack(WGContent.ItemMaterial,1,2),new ItemStack(WGContent.ItemMaterial,1,2),new ItemStack(WGContent.ItemMaterial,1,1),new ItemStack(WGContent.ItemMaterial,1,1),new ItemStack(ConfigBlocks.blockCrystal,1,4)});

	if(WGConfig.terraformer) {
		if(Loader.isModLoaded("dreamcraft")) {
			infusionAspects = new AspectList().add(Aspect.WEATHER, 64).add(Aspect.EXCHANGE, 256).add((Aspect) gregtech.api.enums.TC_Aspects.NEBRISUM.mAspect, 32);
			registerInfusionRecipe("TERRAFORMER", "", new ItemStack(WGContent.BlockMetalDevice, 1, 2), 10, infusionAspects, new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9), new ItemStack[]{Materials.Blaze.getBlocks(1), GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L), GT_ModHandler.getModItem("dreamcraft", "tile.CallistoColdIce", 1L), Materials.Knightmetal.getBlocks(1), gregtech.api.enums.ItemList.Field_Generator_MV.get(1L), GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L)});
		}

		infusionAspects = new AspectList().add(Aspect.TAINT, 32).add(Aspect.EXCHANGE, 16);
		registerInfusionRecipe("TERRAFORMFOCUS_TAINT", "", new ItemStack(WGContent.BlockMetalDevice, 1, 8), 9, infusionAspects, new ItemStack(ConfigBlocks.blockTaint, 1, 0), new ItemStack[]{gregtech.api.util.GT_ModHandler.getModItem("thaumicbases", "blockSalisMundus", 1), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), new ItemStack(ConfigBlocks.blockTube, 1, 0), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L)});
		ThaumcraftApi.addWarpToItem(new ItemStack(WGContent.BlockMetalDevice, 1, 8), 2);

		infusionAspects = new AspectList().add(Aspect.WATER, 32).add(Aspect.EXCHANGE, 16);
		registerInfusionRecipe("TERRAFORMFOCUS_RIVER", "", new ItemStack(WGContent.BlockMetalDevice, 1, 10), 4, infusionAspects, new ItemStack(Items.water_bucket, 1, 0), new ItemStack[]{gregtech.api.util.GT_ModHandler.getModItem("thaumicbases", "blockSalisMundus", 1), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), new ItemStack(ConfigBlocks.blockTube, 1, 0), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L)});

		infusionAspects = new AspectList().add(Aspect.WATER, 64).add(Aspect.EXCHANGE, 16);
		registerInfusionRecipe("TERRAFORMFOCUS_OCEAN", "", new ItemStack(WGContent.BlockMetalDevice, 1, 11), 5, infusionAspects, new ItemStack(WGContent.BlockMetalDevice, 1, 10), new ItemStack[]{gregtech.api.util.GT_ModHandler.getModItem("thaumicbases", "blockSalisMundus", 1), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), new ItemStack(ConfigBlocks.blockTube, 1, 0), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L)});

		infusionAspects = new AspectList().add(Aspect.ELDRITCH, 32).add(Aspect.EXCHANGE, 64);
		registerInfusionRecipe("TERRAFORMFOCUS_END", "", new ItemStack(WGContent.BlockMetalDevice, 1, 12), 6, infusionAspects, new ItemStack(Blocks.end_stone, 1, 0), new ItemStack[]{gregtech.api.util.GT_ModHandler.getModItem("thaumicbases", "blockSalisMundus", 1), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), new ItemStack(ConfigBlocks.blockTube, 1, 0), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L)});
		ThaumcraftApi.addWarpToItem(new ItemStack(WGContent.BlockMetalDevice, 1, 12), 2);

		infusionAspects = new AspectList().add(Aspect.EARTH, 32).add(Aspect.EXCHANGE, 16);
		;
		registerInfusionRecipe("TERRAFORMFOCUS_PLAINS", "", new ItemStack(WGContent.BlockMetalDevice, 1, 3), 3, infusionAspects, new ItemStack(Blocks.grass), new ItemStack[]{gregtech.api.util.GT_ModHandler.getModItem("thaumicbases", "blockSalisMundus", 1), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), new ItemStack(ConfigBlocks.blockTube, 1, 0), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L)});

		infusionAspects = new AspectList().add(Aspect.COLD, 32).add(Aspect.EXCHANGE, 16);
		registerInfusionRecipe("TERRAFORMFOCUS_COLDTAIGA", "", new ItemStack(WGContent.BlockMetalDevice, 1, 4), 5, infusionAspects, new ItemStack(Blocks.ice), new ItemStack[]{gregtech.api.util.GT_ModHandler.getModItem("thaumicbases", "blockSalisMundus", 1), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), new ItemStack(ConfigBlocks.blockTube, 1, 0), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L)});

		infusionAspects = new AspectList().add(Aspect.FIRE, 32).add(Aspect.EXCHANGE, 16);
		registerInfusionRecipe("TERRAFORMFOCUS_DESERT", "", new ItemStack(WGContent.BlockMetalDevice, 1, 5), 5, infusionAspects, new ItemStack(Blocks.sand), new ItemStack[]{gregtech.api.util.GT_ModHandler.getModItem("thaumicbases", "blockSalisMundus", 1), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), new ItemStack(ConfigBlocks.blockTube, 1, 0), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L)});

		infusionAspects = new AspectList().add(Aspect.LIFE, 16).add(Aspect.FIRE, 16).add(Aspect.EXCHANGE, 16);
		registerInfusionRecipe("TERRAFORMFOCUS_JUNGLE", "", new ItemStack(WGContent.BlockMetalDevice, 1, 6), 5, infusionAspects, new ItemStack(Blocks.log, 1, 3), new ItemStack[]{gregtech.api.util.GT_ModHandler.getModItem("thaumicbases", "blockSalisMundus", 1), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), new ItemStack(ConfigBlocks.blockTube, 1, 0), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L)});

		infusionAspects = new AspectList().add(DarkAspects.NETHER, 32).add(Aspect.EXCHANGE, 16);
		registerInfusionRecipe("TERRAFORMFOCUS_HELL", "", new ItemStack(WGContent.BlockMetalDevice, 1, 7), 8, infusionAspects, new ItemStack(Blocks.nether_brick), new ItemStack[]{gregtech.api.util.GT_ModHandler.getModItem("thaumicbases", "blockSalisMundus", 1), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), new ItemStack(ConfigBlocks.blockTube, 1, 0), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L)});
		ThaumcraftApi.addWarpToItem(new ItemStack(WGContent.BlockMetalDevice, 1, 7), 1);

		infusionAspects = new AspectList().add(Aspect.SLIME, 32).add(Aspect.EXCHANGE, 16);
		registerInfusionRecipe("TERRAFORMFOCUS_MUSHROOM", "", new ItemStack(WGContent.BlockMetalDevice, 1, 9), 6, infusionAspects, new ItemStack(Blocks.mycelium), new ItemStack[]{gregtech.api.util.GT_ModHandler.getModItem("thaumicbases", "blockSalisMundus", 1), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), new ItemStack(ConfigBlocks.blockTube, 1, 0), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L)});

		infusionAspects = new AspectList().add(Aspect.HEAL, 32).add(Aspect.EXCHANGE, 8).add(Aspect.ORDER, 16);
		registerInfusionRecipe("TERRAFORMFOCUS_MAGIC", "", new ItemStack(WGContent.BlockMetalDevice, 1, 13), 8, infusionAspects, new ItemStack(ConfigBlocks.blockMagicalLog, 1, 1), new ItemStack[]{new ItemStack(ConfigBlocks.blockCrystal, 1, 6), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), new ItemStack(ConfigBlocks.blockTube, 1, 0), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Thaumium, 1L)});

	}
	
	infusionAspects = new AspectList().add(Aspect.SENSES,24).add(Aspect.WEATHER,8).add(Aspect.WATER, 64).add(Aspect.HEAL,16);
	registerInfusionRecipe("SAUNASTOVE","",new ItemStack(WGContent.BlockWoodenDevice,1,4), 5, infusionAspects,ItemList.Machine_MV_FluidHeater.get(1L),new ItemStack[] { ItemList.Cover_Drain.get(1L),new ItemStack(Blocks.stone_slab), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6),new ItemStack(Blocks.stone_slab)});

	
	if(Config.allowMirrors)
	{
		infusionAspects = new AspectList().add(Aspect.MOTION,32).add(Aspect.TRAVEL,64).add(Aspect.ORDER, 16);
		registerInfusionRecipe("MIRRORPUMP","",new ItemStack(WGContent.BlockMetalDevice,1,0),8, infusionAspects, new ItemStack(ConfigBlocks.blockTube,1,4), new ItemStack[] {new ItemStack(ConfigBlocks.blockWoodenDevice),ItemList.Electric_Pump_MV.get(1L),new ItemStack(ConfigBlocks.blockWoodenDevice), ItemList.Electric_Pump_MV.get(1L),new ItemStack(ConfigBlocks.blockWoodenDevice),ItemList.Electric_Pump_MV.get(1L),new ItemStack(ConfigBlocks.blockWoodenDevice),ItemList.Electric_Pump_MV.get(1L)} );
	}
	
	//Primordial Gear
	if (WGConfig.modulePrimal) {
		
		infusionAspects = new AspectList().add(Aspect.AIR,64).add(Aspect.FIRE,64).add(Aspect.EARTH,64).add(Aspect.WATER,64).add(Aspect.ORDER,64).add(Aspect.ENTROPY,64).add(Aspect.MAGIC,384);
		registerInfusionRecipe("EMPOWERPEARL","",new ItemStack(ConfigItems.itemEldritchObject,1,3),10,infusionAspects,new ItemStack(WGContent.ItemMaterial,1,12),new ItemStack[] {new ItemStack(ConfigBlocks.blockCrystal,1,0),new ItemStack(ConfigBlocks.blockCrystal,1,1),new ItemStack(ConfigBlocks.blockCrystal,1,2),new ItemStack(ConfigBlocks.blockCrystal,1,3),new ItemStack(ConfigBlocks.blockCrystal,1,4),new ItemStack(ConfigBlocks.blockCrystal,1,5),new ItemStack(ConfigBlocks.blockCrystal,1,6)});
		 
		if (WGConfig.moduleGemcutting) {
			infusionAspects = new AspectList().add(Aspect.MAGIC,512).add(Aspect.CRYSTAL,256).add(Aspect.TOOL,128).add(Aspect.AIR,128).add(Aspect.FIRE,128).add(Aspect.WATER,128).add(Aspect.EARTH,128).add(Aspect.ORDER,128).add(Aspect.ENTROPY,128);
			registerInfusionRecipe("PRIMORDIALGLOVE","",new ItemStack(WGContent.ItemPrimordialGlove),6,infusionAspects,new ItemStack(ConfigBlocks.blockStoneDevice,1,11),new ItemStack[] {new ItemStack(WGContent.ItemMaterial,1,5), new ItemStack(ConfigItems.itemResource,1,17), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L), new ItemStack(ConfigItems.itemEldritchObject,1,3), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L), new ItemStack(ConfigItems.itemResource,1,17) });
		}
		infusionAspects = new AspectList().add(Aspect.MAGIC,512).add(Aspect.METAL,1024).add(Aspect.WEAPON,512).add(Aspect.AIR,256).add(Aspect.FIRE,256).add(Aspect.WATER,256).add(Aspect.EARTH,256).add(Aspect.ORDER,256).add(Aspect.ENTROPY,256);
		registerInfusionRecipe("PRIMORDIALWEAPONRY","_CLAYMORE",new ItemStack(WGContent.ItemPrimordialSword),10,infusionAspects,GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),new ItemStack[] {new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemSwordVoid), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject,1,3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource,1,15), new ItemStack(ConfigItems.itemWandRod,1,0), new ItemStack(ConfigItems.itemResource,1,15), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject,1,3), new ItemStack(ConfigItems.itemWispEssence),new ItemStack(ConfigItems.itemSwordVoid) });

		infusionAspects = new AspectList().add(Aspect.MAGIC,512).add(Aspect.METAL,1024).add(Aspect.TOOL,256).add(Aspect.WEAPON,256).add(Aspect.AIR,256).add(Aspect.FIRE,256).add(Aspect.WATER,256).add(Aspect.EARTH,256).add(Aspect.ORDER,256).add(Aspect.ENTROPY,256);
		registerInfusionRecipe("PRIMORDIALWEAPONRY","_HAMMER",new ItemStack(WGContent.ItemPrimordialHammer),10,infusionAspects,GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),new ItemStack[] {new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemPickVoid), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject,1,3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource,1,15), new ItemStack(ConfigItems.itemWandRod,1,0), new ItemStack(ConfigItems.itemResource,1,15), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject,1,3), new ItemStack(ConfigItems.itemWispEssence),new ItemStack(ConfigItems.itemSwordVoid) });

		infusionAspects = new AspectList().add(Aspect.MAGIC,512).add(Aspect.METAL,1024).add(Aspect.TOOL,256).add(Aspect.WEAPON,256).add(Aspect.AIR,256).add(Aspect.FIRE,256).add(Aspect.WATER,256).add(Aspect.EARTH,256).add(Aspect.ORDER,256).add(Aspect.ENTROPY,256);
		registerInfusionRecipe("PRIMORDIALWEAPONRY","_GREATAXE",new ItemStack(WGContent.ItemPrimordialAxe),10,infusionAspects,GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),new ItemStack[] {new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemAxeVoid), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject,1,3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource,1,15), new ItemStack(ConfigItems.itemWandRod,1,0), new ItemStack(ConfigItems.itemResource,1,15), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject,1,3), new ItemStack(ConfigItems.itemWispEssence),new ItemStack(ConfigItems.itemAxeVoid) });

		//
		infusionAspects = new AspectList().add(Aspect.MAGIC,512).add(Aspect.METAL,1024).add(Aspect.ARMOR,256).add(Aspect.AIR,256).add(Aspect.FIRE,256).add(Aspect.WATER,256).add(Aspect.EARTH,256).add(Aspect.ORDER,256).add(Aspect.ENTROPY,256);
		registerInfusionRecipe("PRIMORDIALARMOR","_HELMET",new ItemStack(WGContent.ItemPrimordialHelm),10,infusionAspects,GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),new ItemStack[] {new ItemStack(ConfigItems.itemEldritchObject,1,3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource,1,15), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject,1,3), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L), GT_ModHandler.getModItem("TaintedMagic", "ItemShadowFortressHelmet", 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L), new ItemStack(ConfigItems.itemEldritchObject,1,3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource,1,15), new ItemStack(ConfigItems.itemWispEssence) });
		//ThaumcraftApi.addWarpToItem(new ItemStack(WGContent.ItemPrimordialAxe), 2);
		infusionAspects = new AspectList().add(Aspect.MAGIC,512).add(Aspect.METAL,1024).add(Aspect.ARMOR,256).add(Aspect.AIR,256).add(Aspect.FIRE,256).add(Aspect.WATER,256).add(Aspect.EARTH,256).add(Aspect.ORDER,256).add(Aspect.ENTROPY,256);
		registerInfusionRecipe("PRIMORDIALARMOR","_CUIRASS",new ItemStack(WGContent.ItemPrimordialChest),10,infusionAspects,GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),new ItemStack[] {new ItemStack(ConfigItems.itemEldritchObject,1,3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource,1,15), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject,1,3), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L), GT_ModHandler.getModItem("TaintedMagic", "ItemShadowFortressChestplate", 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L), new ItemStack(ConfigItems.itemEldritchObject,1,3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource,1,15), new ItemStack(ConfigItems.itemWispEssence) });
		//ThaumcraftApi.addWarpToItem(new ItemStack(WGContent.ItemPrimordialAxe), 2);
		infusionAspects = new AspectList().add(Aspect.MAGIC,512).add(Aspect.METAL,1024).add(Aspect.ARMOR,256).add(Aspect.AIR,256).add(Aspect.FIRE,256).add(Aspect.WATER,256).add(Aspect.EARTH,256).add(Aspect.ORDER,256).add(Aspect.ENTROPY,256);
		registerInfusionRecipe("PRIMORDIALARMOR","_GREAVES",new ItemStack(WGContent.ItemPrimordialLegs),10,infusionAspects,GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),new ItemStack[] {new ItemStack(ConfigItems.itemEldritchObject,1,3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource,1,15), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject,1,3), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L), GT_ModHandler.getModItem("TaintedMagic", "ItemShadowFortressLeggings", 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L), new ItemStack(ConfigItems.itemEldritchObject,1,3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource,1,15), new ItemStack(ConfigItems.itemWispEssence) });
		//ThaumcraftApi.addWarpToItem(new ItemStack(WGContent.ItemPrimordialAxe), 2);
		infusionAspects = new AspectList().add(Aspect.MAGIC,512).add(Aspect.METAL,1024).add(Aspect.ARMOR,256).add(Aspect.AIR,256).add(Aspect.FIRE,256).add(Aspect.WATER,256).add(Aspect.EARTH,256).add(Aspect.ORDER,256).add(Aspect.ENTROPY,256);
		registerInfusionRecipe("PRIMORDIALARMOR","_BOOTS",new ItemStack(WGContent.ItemPrimordialBoots),10,infusionAspects,GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),new ItemStack[] {new ItemStack(ConfigItems.itemEldritchObject,1,3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource,1,15), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject,1,3), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L), GT_ModHandler.getModItem("TaintedMagic", "ItemVoidwalkerBoots", 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L), new ItemStack(ConfigItems.itemEldritchObject,1,3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource,1,15), new ItemStack(ConfigItems.itemWispEssence) });
		//ThaumcraftApi.addWarpToItem(new ItemStack(WGContent.ItemPrimordialAxe), 2);

		//		for(ItemPrimordialArmor.PrimordialArmorUpgrade pau : ItemPrimordialArmor.PrimordialArmorUpgrade.values())
		//		{
		//			registerInfusionRecipe("PRIMODIALARMORUPGRADE_"+pau,"_HELMET",new Object[]{"primordialUpgrade",new NBTTagByte((byte)pau.ordinal())},8,pau.getAspects(),new ItemStack(WGContent.ItemPrimordialHelm),pau.getCompenents());
		//			registerInfusionRecipe("PRIMODIALARMORUPGRADE_"+pau,"_CUIRASS",new Object[]{"primordialUpgrade",new NBTTagByte((byte)pau.ordinal())},8,pau.getAspects(),new ItemStack(WGContent.ItemPrimordialChest),pau.getCompenents());
		//			registerInfusionRecipe("PRIMODIALARMORUPGRADE_"+pau,"_GREAVES",new Object[]{"primordialUpgrade",new NBTTagByte((byte)pau.ordinal())},8,pau.getAspects(),new ItemStack(WGContent.ItemPrimordialLegs),pau.getCompenents());
		//			registerInfusionRecipe("PRIMODIALARMORUPGRADE_"+pau,"_BOOTS",new Object[]{"primordialUpgrade",new NBTTagByte((byte)pau.ordinal())},8,pau.getAspects(),new ItemStack(WGContent.ItemPrimordialBoots),pau.getCompenents());
		//		}
		infusionAspects = new AspectList().add(Aspect.SENSES,256).add(Aspect.AURA,128).add(Aspect.ARMOR,128);
		registerInfusionRecipe("HELMGOGGLES","_PRIMORDIAL",new Object[]{"goggles",new NBTTagByte((byte)1)},5,infusionAspects,new ItemStack(WGContent.ItemPrimordialHelm,1,32767),new ItemStack[] { new ItemStack(Items.slime_ball), new ItemStack(ConfigItems.itemGoggles, 1, 32767) });
		infusionAspects = new AspectList().add(Aspect.MIND,512).add(Aspect.HEAL,512).add(Aspect.ARMOR, 128);
		registerInfusionRecipe("MASKGRINNINGDEVIL","_PRIMORDIAL",new Object[]{"mask",new NBTTagByte((byte)0)},8,infusionAspects,new ItemStack(WGContent.ItemPrimordialHelm,1,32767),new ItemStack[] { new ItemStack(Items.dye, 1, 0), new ItemStack(Items.iron_ingot), new ItemStack(Items.leather), new ItemStack(ConfigBlocks.blockCustomPlant, 1, 2), new ItemStack(ConfigItems.itemZombieBrain), new ItemStack(Items.iron_ingot) });
		infusionAspects = new AspectList().add(Aspect.ENTROPY,512).add(Aspect.DEATH,512).add(Aspect.ARMOR, 128);
		registerInfusionRecipe("MASKANGRYGHOST","_PRIMORDIAL",new Object[]{"mask",new NBTTagByte((byte)1)},8,infusionAspects,new ItemStack(WGContent.ItemPrimordialHelm,1,32767),new ItemStack[] { new ItemStack(Items.dye, 1, 15), new ItemStack(Items.iron_ingot), new ItemStack(Items.leather), new ItemStack(Items.poisonous_potato), new ItemStack(Items.skull, 1, 1), new ItemStack(Items.iron_ingot) });
		infusionAspects = new AspectList().add(Aspect.UNDEAD,512).add(Aspect.LIFE,512).add(Aspect.ARMOR, 128);
		registerInfusionRecipe("MASKSIPPINGFIEND","_PRIMORDIAL",new Object[]{"mask",new NBTTagByte((byte)2)},8,infusionAspects,new ItemStack(WGContent.ItemPrimordialHelm,1,32767),new ItemStack[] { new ItemStack(Items.dye, 1, 1), new ItemStack(Items.iron_ingot), new ItemStack(Items.leather), new ItemStack(Items.ghast_tear), new ItemStack(Items.milk_bucket), new ItemStack(Items.iron_ingot) });

	}
	
	
	/**
	 * ENCHANTMENT
	 */
	infusionAspects = new AspectList().add(Aspect.DARKNESS, 48).add(Aspect.CRYSTAL, 48).add(Aspect.MAGIC, 48).add(Aspect.ARMOR,32).add(Aspect.AURA,24).add(Aspect.SOUL,8);
	if (WGConfig.moduleGemcutting) 
		registerInfusionEnchantmentRecipe("ENCH_INVISIBLEGEAR", "", WGContent.enc_invisibleGear, 2, infusionAspects, new ItemStack[] {new ItemStack(Items.quartz),new ItemStack(ConfigItems.itemResource,1,14),new ItemStack(WGContent.ItemMaterial,1,13),new ItemStack(ConfigItems.itemEldritchObject,1,0)});
	else 
		registerInfusionEnchantmentRecipe("ENCH_INVISIBLEGEAR", "", WGContent.enc_invisibleGear, 2, infusionAspects, new ItemStack[] {new ItemStack(Items.quartz),new ItemStack(ConfigItems.itemResource,1,14),new ItemStack(ConfigItems.itemEldritchObject,1,0)});
	WGModCompat.thaumicTinkererRegisterEnchantment(WGContent.enc_invisibleGear, "witchinggadgets:textures/gui/research/icon_ench_invisGear.png", new AspectList().add(Aspect.DARKNESS, 8).add(Aspect.CRYSTAL, 8).add(Aspect.MAGIC, 8).add(Aspect.ARMOR,6).add(Aspect.AURA,4).add(Aspect.SOUL,2), "ENCH_INVISIBLEGEAR");

	infusionAspects = new AspectList().add(Aspect.LIGHT, 64).add(Aspect.SENSES, 48).add(Aspect.MAGIC, 48).add(Aspect.CRYSTAL, 32);
	registerInfusionEnchantmentRecipe("ENCH_UNVEILING", "", WGContent.enc_unveiling, 2, infusionAspects, new ItemStack[] {new ItemStack(Items.golden_carrot),new ItemStack(ConfigItems.itemResource,1,14), ItemList.Emitter_MV.get(1L)});
	WGModCompat.thaumicTinkererRegisterEnchantment(WGContent.enc_unveiling, "witchinggadgets:textures/gui/research/icon_ench_unveiling.png", new AspectList().add(Aspect.LIGHT, 16).add(Aspect.SENSES, 8).add(Aspect.MAGIC, 8).add(Aspect.CRYSTAL, 4), "ENCH_UNVEILING");

	infusionAspects = new AspectList().add(Aspect.MOTION, 64).add(Aspect.DARKNESS, 48).add(Aspect.MAGIC, 48).add(Aspect.GREED,32);
	registerInfusionEnchantmentRecipe("ENCH_STEALTH", "", WGContent.enc_stealth, 2, infusionAspects, new ItemStack[] {new ItemStack(Items.potionitem,1,8206),new ItemStack(ConfigItems.itemResource,1,14),ItemList.Sensor_MV.get(1L)});
	WGModCompat.thaumicTinkererRegisterEnchantment(WGContent.enc_stealth, "witchinggadgets:textures/gui/research/icon_ench_stealth.png", new AspectList().add(Aspect.MOTION, 16).add(Aspect.DARKNESS, 8).add(Aspect.MAGIC, 8).add(Aspect.GREED,4), "ENCH_STEALTH");

	infusionAspects = new AspectList().add(Aspect.WEAPON, 48).add(DarkAspects.ENVY, 32).add(Aspect.MAGIC, 16);
	registerInfusionEnchantmentRecipe("ENCH_BACKSTAB", "", WGContent.enc_backstab, 3, infusionAspects, new ItemStack[] {GT_ModHandler.getModItem("TConstruct", "knifeBlade", 1L,2),new ItemStack(Items.potionitem,1,8206),new ItemStack(ConfigItems.itemResource,1,14)});
	WGModCompat.thaumicTinkererRegisterEnchantment(WGContent.enc_backstab, "witchinggadgets:textures/gui/research/icon_ench_backstab.png", new AspectList().add(Aspect.WEAPON, 12).add(DarkAspects.ENVY, 8).add(Aspect.MAGIC, 4), "ENCH_BACKSTAB");

	infusionAspects = new AspectList().add(Aspect.ARMOR, 48).add(Aspect.TRAP, 32).add(Aspect.MAGIC, 16);
	registerInfusionEnchantmentRecipe("ENCH_RIDEPROTECT", "", WGContent.enc_rideProtect, 3, infusionAspects, new ItemStack[] {new ItemStack(ConfigItems.itemResource,1,14),new ItemStack(Blocks.piston),new ItemStack(Blocks.piston),new ItemStack(Blocks.piston),new ItemStack(Blocks.piston)});
	WGModCompat.thaumicTinkererRegisterEnchantment(WGContent.enc_rideProtect, "witchinggadgets:textures/gui/research/icon_ench_rideProtect.png", new AspectList().add(Aspect.AIR, 20).add(Aspect.ENTROPY, 20).add(Aspect.ORDER, 20), "ENCH_RIDEPROTECT");

	infusionAspects = new AspectList().add(Aspect.SOUL, 64).add(Aspect.MAGIC, 48).add(Aspect.GREED, 24).add(Aspect.ELDRITCH, 32);
	registerInfusionEnchantmentRecipe("ENCH_SOULBOUND", "", WGContent.enc_soulbound, 1, infusionAspects, new ItemStack[] {new ItemStack(Items.ender_eye), new ItemStack(Items.ender_pearl), new ItemStack(Items.name_tag)});
	WGModCompat.thaumicTinkererRegisterEnchantment(WGContent.enc_soulbound, "witchinggadgets:textures/gui/research/icon_ench_soulbound.png", new AspectList().add(Aspect.AIR, 10).add(Aspect.ENTROPY, 10).add(Aspect.ORDER, 20), "ENCH_SOULBOUND");

	}
	private static void registerInfusionRecipe(String tag, String tagAddon, Object result, int difficulty, AspectList infusionAspects, ItemStack centralIngredient, ItemStack[] otherIngredients)
	{
		InfusionRecipe infusionRecipe = ThaumcraftApi.addInfusionCraftingRecipe(tag, result, difficulty, infusionAspects, centralIngredient, otherIngredients);
		WGContent.recipeList.put(tag+tagAddon, infusionRecipe);
	}
	private static void registerInfusionEnchantmentRecipe(String tag, String tagAddon, Enchantment enchantment, int difficulty, AspectList infusionAspects, ItemStack[] otherIngredients)
	{
		InfusionEnchantmentRecipe infusionRecipe = ThaumcraftApi.addInfusionEnchantmentRecipe(tag, enchantment, difficulty, infusionAspects, otherIngredients);
		WGContent.recipeList.put(tag+tagAddon, infusionRecipe);
	}
}
