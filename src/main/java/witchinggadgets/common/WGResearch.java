package witchinggadgets.common;

import cpw.mods.fml.common.Loader;
import fox.spiteful.forbidden.DarkAspects;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import magicbees.api.MagicBeesAPI;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.*;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.items.baubles.ItemCloak;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.research.WGResearchItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WGResearch
{
	
	public static final ResourceLocation[] wgbackgrounds = {
															new ResourceLocation("witchinggadgets:textures/gui/research/WGResearchBack.png"),
															new ResourceLocation("witchinggadgets:textures/gui/research/WGResearchBackAwoken.png")
															};

	public static void setupResearchPages()
	{
		ResearchCategories.registerCategory("WITCHGADG", new ResourceLocation("witchinggadgets:textures/gui/research/WGIcon.png"), wgbackgrounds[0]);
	}

	public static void registerResearch()
	{
		AspectList researchAspects;
		ResearchPage[] pages;
		
		//WGPOTIONS
		researchAspects = new AspectList();
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.WGPOTIONS.1"), new ResearchPage("witchinggadgets_research_page.WGPOTIONS.2")};
		getResearchItem("WGPOTIONS", "WITCHGADG", researchAspects, 0, 0, 0, new ResourceLocation("witchinggadgets:textures/gui/research/icon_potioneffects.png")).setRound().setAutoUnlock().setPages(pages).registerResearchItem();

		//SPINNINGWHEEL
		researchAspects = new AspectList().add(Aspect.CLOTH,15).add(Aspect.MAGIC,5).add(Aspect.CRAFT,15);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.SPINNINGWHEEL.1"), new ResearchPage("witchinggadgets_research_page.SPINNINGWHEEL.2"), new ResearchPage((ShapedArcaneRecipe) WGContent.recipeList.get("SPINNINGWHEEL"))};
		getResearchItem("SPINNINGWHEEL", "WITCHGADG", researchAspects, 4,-1, 2, new ItemStack(WGContent.BlockWoodenDevice,1,0)).setRound().setPages(pages).setParents("WGPOTIONS").setSpecial().setConcealed().registerResearchItem();
		
		//ORIGINAL ENCHFABRIC
		//getFakeResearchItem("ENCHFABRIC", "ARTIFICE", 8,-1, new ItemStack(ConfigItems.itemResource, 1, 7)).registerResearchItem();
		new FakeResearchItem("WG.ENCHFABRIC", "WITCHGADG", "ENCHFABRIC", "ARTIFICE", 2, -4, ResearchCategories.getResearch("ENCHFABRIC").icon_item).registerResearchItem();
		
		//ADVANCED ROBES | Fleece
		researchAspects = new AspectList().add(Aspect.CLOTH, 3).add(Aspect.MAGIC, 4).add(Aspect.TAINT, 1);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.ADVANCEDROBES.1"), new ResearchPage((ShapelessArcaneRecipe) WGContent.recipeList.get("ADVANCEDROBES_CLOTH")), new ResearchPage((ShapedArcaneRecipe) WGContent.recipeList.get("ADVANCEDROBES_CHEST")), new ResearchPage((ShapedArcaneRecipe) WGContent.recipeList.get("ADVANCEDROBES_LEGS"))};
		getResearchItem("ADVANCEDROBES", "WITCHGADG", researchAspects, 2, -2, 3, new ItemStack(WGContent.ItemMaterial,1,5)).setParents("WG.ENCHFABRIC", "SPINNINGWHEEL").setPages(pages).setConcealed().registerResearchItem();
		
		
		if (WGConfig.moduleBag) {
			
			//BAG CLOTH
			researchAspects = new AspectList().add(Aspect.CLOTH, 2).add(Aspect.VOID, 2).add(Aspect.HUNGER, 2);
			pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.BAGOFTRICKS.1"), new ResearchPage((ShapelessArcaneRecipe) WGContent.recipeList.get("BAGOFTRICKS_CLOTH")), new ResearchPage((ShapedArcaneRecipe) WGContent.recipeList.get("BAGOFTRICKS_BAG"))};
			getResearchItem("BAGOFTRICKS", "WITCHGADG", researchAspects, 3, 0, 2, new ItemStack(WGContent.ItemBag,1,0)).setParents("SPINNINGWHEEL").setConcealed().setPages(pages).registerResearchItem();
			
			if (WGConfig.bagEnder) {
				//ENDERBAG
				researchAspects = new AspectList().add(Aspect.CLOTH, 2).add(Aspect.ELDRITCH, 3).add(Aspect.VOID, 3);
				pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.ENDERBAG.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("ENDERBAG"))};
				getResearchItem("ENDERBAG", "WITCHGADG", researchAspects, 2, 0, 5, new ItemStack(WGContent.ItemBag,1,2)).addWarp(15).setPages(pages).setParents("BAGOFTRICKS","INFUSION","WGPOTIONS").setHidden().setItemTriggers(new ItemStack(Blocks.ender_chest,1,32767)).setAspectTriggers(Aspect.ELDRITCH).registerResearchItem();
			}
			if (WGConfig.bagHungry) {
				//HUNGERBAG
				researchAspects = new AspectList().add(Aspect.CLOTH, 2).add(Aspect.HUNGER, 3).add(Aspect.VOID, 3);
				pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.HUNGERBAG.1"), new ResearchPage((IArcaneRecipe) WGContent.recipeList.get("HUNGERBAG"))};
				getResearchItem("HUNGERBAG", "WITCHGADG", researchAspects, 5, 0, 4, new ItemStack(WGContent.ItemBag,1,3)).addWarp(2).setPages(pages).setParents("BAGOFTRICKS","HUNGRYCHEST").setConcealed().registerResearchItem();
			}
			if (WGConfig.bagVoid) {
				//ORIGINAL ELDRITCHMINOR
				//getFakeResearchItem("ELDRITCHMINOR", "ELDRITCH", 2,2, new ResourceLocation("thaumcraft", "textures/misc/r_eldritchminor.png")).setSpecial().registerResearchItem();
				new FakeResearchItem("WG.ELDRITCHMINOR", "WITCHGADG", "ELDRITCHMINOR", "ELDRITCH", 1, 3, ResearchCategories.getResearch("ELDRITCHMINOR").icon_resource).setParents("WGPOTIONS").setConcealed().registerResearchItem();
				
				//VOIDBAG
				researchAspects = new AspectList().add(Aspect.CLOTH, 2).add(Aspect.VOID, 3).add(Aspect.ENTROPY, 5).add(Aspect.ELDRITCH, 3);
				pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.VOIDBAG.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("VOIDBAG"))};
				getResearchItem("VOIDBAG", "WITCHGADG", researchAspects, 2, 2, 4, new ItemStack(WGContent.ItemBag,1,1)).addWarp(4).setParents("BAGOFTRICKS","WG.ELDRITCHMINOR").setConcealed().setPages(pages).registerResearchItem();
			}
		}
		
		
		
		//ADVANCEDSCRIBINGTOOLS
		getResearchItem("ADVANCEDSCRIBINGTOOLS","WITCHGADG",
				new AspectList().add(Aspect.MAGIC, 3).add(Aspect.LIGHT, 2).add(DarkAspects.GLUTTONY, 2).add(Aspect.MIND, 3),
				6,5,2,
				new ItemStack(WGContent.ItemAdvancedScribingTools))
				.setParentsHidden("WGPOTIONS")
				.setPages(
						new ResearchPage("witchinggadgets_research_page.ADVANCEDSCRIBINGTOOLS.1"),
						new ResearchPage((IArcaneRecipe) WGContent.recipeList.get("ADVANCEDSCRIBINGTOOLS")),
						new ResearchPage((IArcaneRecipe) WGContent.recipeList.get("ADVANCEDSCRIBINGTOOLSrefill"))).registerResearchItem();
		
		if (WGConfig.moduleGemcutting) {
			//GEMCUTTING
			researchAspects = new AspectList().add(Aspect.CRYSTAL,1).add(Aspect.ORDER, 1).add(Aspect.MAGIC, 1).add(Aspect.CRAFT, 1);
			pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.GEMCUTTING.1"), new ResearchPage((ShapedOreRecipe) WGContent.recipeList.get("GEMCUTTING_TOOLS")), new ResearchPage((List) WGContent.recipeList.get("GEMCUTTING")), new ResearchPage("witchinggadgets_research_page.GEMCUTTING.2"), new ResearchPage("witchinggadgets_research_page.GEMCUTTING.3"), new ResearchPage("witchinggadgets_research_page.GEMCUTTING.4"), new ResearchPage("witchinggadgets_research_page.GEMCUTTING.5"), new ResearchPage("witchinggadgets_research_page.GEMCUTTING.6"), new ResearchPage("witchinggadgets_research_page.GEMCUTTING.7.noMirrors")};
			getResearchItem("GEMCUTTING", "WITCHGADG", researchAspects, 6, 3, 5, new ItemStack(WGContent.BlockWoodenDevice,1,3)).setPages(pages).registerResearchItem();
			//CRYSTALCAPSULE
			researchAspects = new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.ORDER, 2).add(Aspect.VOID, 4);
			pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.CRYSTALCAPSULE.1"), new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("CRYSTALCAPSULE_Wax")), new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("CRYSTALCAPSULE_Refractory")), new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("CRYSTALCAPSULE_Cell"))};
			getResearchItem("CRYSTALCAPSULE", "WITCHGADG", researchAspects, 6, 4, 4, new ItemStack(WGContent.ItemCapsule)).setPages(pages).setParents("GEMCUTTING").setConcealed().registerResearchItem();
		}
		
		//SCANCAMERA
		researchAspects = new AspectList().add(Aspect.SENSES, 1).add(Aspect.MIND, 1).add(Aspect.SOUL, 1).add(Aspect.CRYSTAL, 1);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.SCANCAMERA.1"), new ResearchPage((IArcaneRecipe)WGContent.recipeList.get("SCANCAMERA")), new ResearchPage("witchinggadgets_research_page.SCANCAMERA.2"), new ResearchPage((IArcaneRecipe)WGContent.recipeList.get("SCANCAMERA_DEVELOP")), new ResearchPage((IRecipe)WGContent.recipeList.get("SCANCAMERA_CLEARPLATE"))};
		getResearchItem("SCANCAMERA", "WITCHGADG", researchAspects, 5, 3, 5, new ItemStack(WGContent.ItemScanCamera)).setPages(pages).registerResearchItem();
		//CALCULATOR
		researchAspects = new AspectList().add(Aspect.TOOL, 1).add(Aspect.MIND, 1).add(Aspect.MECHANISM, 1);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.CALCULATOR.1"), new ResearchPage((IArcaneRecipe)WGContent.recipeList.get("CALCULATOR")) };
		getResearchItem("CALCULATOR", "WITCHGADG", researchAspects, 5, 5, 3, new ItemStack(WGContent.ItemMaterial,1,7)).setPages(pages).setParents("INFUSION").setConcealed().registerResearchItem();
		//LABYRINTHSTRING
		researchAspects = new AspectList().add(Aspect.TOOL, 1).add(Aspect.MIND, 1).add(Aspect.TRAVEL, 1);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.LABYRINTHSTRING.1"), new ResearchPage((InfusionRecipe)WGContent.recipeList.get("LABYRINTHSTRING")) };
		getResearchItem("LABYRINTHSTRING", "WITCHGADG", researchAspects, 5, 4, 3, new ItemStack(WGContent.ItemMaterial,1,11)).setPages(pages).setParents("INFUSION").setConcealed().registerResearchItem();


		//WGBAUBLES
		researchAspects = new AspectList().add(Aspect.CLOTH, 1).add(Aspect.MAGIC, 1).add(Aspect.ARMOR, 1);
		pages = new ResearchPage[]{
				new ResearchPage("witchinggadgets_research_page.WGBAUBLES.1"), new ResearchPage((ShapedArcaneRecipe) WGContent.recipeList.get("WGBAUBLES_KNOCKBACKSHOULDERS")), 
				new ResearchPage("witchinggadgets_research_page.WGBAUBLES.2"), new ResearchPage((ShapedArcaneRecipe) WGContent.recipeList.get("WGBAUBLES_WOLFVAMBRACES")),
				new ResearchPage("witchinggadgets_research_page.WGBAUBLES.3"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("WGBAUBLES_HASTEVAMBRACES")),
				new ResearchPage("witchinggadgets_research_page.WGBAUBLES.4"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("WGBAUBLES_DOUBLEJUMPSHOULDERS")),
				new ResearchPage("witchinggadgets_research_page.WGBAUBLES.5"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("WGBAUBLES_SNIPERRING")),
				new ResearchPage("witchinggadgets_research_page.WGBAUBLES.6"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("WGBAUBLES_LUCKRING")), new ResearchPage((ShapedArcaneRecipe) WGContent.recipeList.get("WGBAUBLES_COIN"))
		};
		getResearchItem("WGBAUBLES", "WITCHGADG", researchAspects, 6, -1, 4, new ItemStack(WGContent.ItemMagicalBaubles,1,2)).addWarp(4).setParents("THAUMIUM").setConcealed().setPages(pages).registerResearchItem();

		
		if (WGConfig.moduleCloak) {
			
			//CLOAK
			ItemStack standardCloak = new ItemStack(WGContent.ItemCloak,1,0);//ItemCloak.getCloakWithTag("STANDARD");
			researchAspects = new AspectList().add(Aspect.CLOTH, 1).add(Aspect.AIR,1).add(Aspect.ARMOR,1).add(Aspect.MAGIC, 1);
			pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.CLOAK.1"), new ResearchPage((IArcaneRecipe)WGContent.recipeList.get("CLOAK"))};
			getResearchItem("CLOAK", "WITCHGADG", researchAspects, 4, -3, 3, standardCloak).setParents("ADVANCEDROBES").setParentsHidden("BAGOFTRICKS").setConcealed().setPages(pages).registerResearchItem();

			if (WGConfig.capeSpectral) {
				researchAspects = new AspectList().add(Aspect.CLOTH, 4).add(Aspect.TRAVEL, 2).add(Aspect.SOUL,2).add(Aspect.DARKNESS, 4);
				pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.CLOAK_SPECTRAL.1"), new ResearchPage((InfusionRecipe)WGContent.recipeList.get("CLOAK_SPECTRAL")), new ResearchPage("witchinggadgets_research_page.CLOAK_SPECTRAL.2")};
				getResearchItem("CLOAK_SPECTRAL", "WITCHGADG", researchAspects, 6, -3, 4, new ItemStack(WGContent.ItemCloak,1,1)).setParents("CLOAK").setParentsHidden("INFUSION").setPages(pages).setConcealed().registerResearchItem();
			}
			if (WGConfig.capeStorage) {
				researchAspects = new AspectList().add(Aspect.CLOTH, 4).add(Aspect.VOID, 6).add(Aspect.HUNGER, 4);
				pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.CLOAK_STORAGE.1"), new ResearchPage((IArcaneRecipe)WGContent.recipeList.get("CLOAK_STORAGE"))};
				getResearchItem("CLOAK_STORAGE", "WITCHGADG", researchAspects, 6, -4, 4, new ItemStack(WGContent.ItemCloak,1,2)).setParents("CLOAK").setParentsHidden("BAGOFTRICKS" ).setPages(pages).setConcealed().registerResearchItem();
			}
			if (WGConfig.capeWolf) {
				researchAspects = new AspectList().add(Aspect.CLOTH, 4).add(Aspect.BEAST, 4).add(Aspect.HUNGER, 4);
				pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.CLOAK_WOLF.1"), new ResearchPage((IArcaneRecipe) WGContent.recipeList.get("CLOAK_WOLF"))};
				getResearchItem("CLOAK_WOLF", "WITCHGADG", researchAspects, 5, -5, 4, new ItemStack(WGContent.ItemCloak,1,3)).setParents("CLOAK" ).setPages(pages).setConcealed().registerResearchItem();
			}
			
			if(WGConfig.capeRaven)
			{
				researchAspects = new AspectList().add(Aspect.CLOTH, 4).add(Aspect.AIR, 4).add(Aspect.FLIGHT, 4).add(Aspect.TRAVEL, 2);
				pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.CLOAK_RAVEN.1"), new ResearchPage((IArcaneRecipe) WGContent.recipeList.get("CLOAK_RAVEN"))};
				getResearchItem("CLOAK_RAVEN", "WITCHGADG", researchAspects, 4, -5, 4, new ItemStack(WGContent.ItemCloak,1,4)).setParents("CLOAK" ).setPages(pages).setConcealed().registerResearchItem();
			}
			
			if (WGConfig.moduleKama) {
				//KAMA
				researchAspects = new AspectList().add(Aspect.CLOTH, 2).add(Aspect.ARMOR, 2);
				ArrayList<ShapedArcaneRecipe> recList = new ArrayList();
				for(int cm=0; cm<ItemCloak.subNames.length; cm++)
					recList.add((ShapedArcaneRecipe)WGContent.recipeList.get("CLOAKKAMA_"+cm));
				pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.CLOAKKAMA.1"), new ResearchPage((ShapedArcaneRecipe[]) recList.toArray(new ShapedArcaneRecipe[0]))};
				getResearchItem("CLOAKKAMA", "WITCHGADG", researchAspects, 5, -2, 5, new ItemStack(WGContent.ItemKama,1,0)).setParents("WGBAUBLES","CLOAK").setConcealed().setPages(pages).registerResearchItem();
			}
		}
		
		//ORIGINAL ARCANESTONE
		//getFakeResearchItem("ARCANESTONE", "ARTIFICE", -3,-4,  new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6)).registerResearchItem();
		new FakeResearchItem("WG.ARCANESTONE", "WITCHGADG", "ARCANESTONE", "ARTIFICE", -4, -4, ResearchCategories.getResearch("ARCANESTONE").icon_item).setParents("WGPOTIONS").setConcealed().registerResearchItem();
		//STONEEXTRUDER
		researchAspects = new AspectList().add(Aspect.EARTH, 1).add(Aspect.MECHANISM, 7).add(Aspect.TOOL, 12);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.STONEEXTRUDER.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("STONEEXTRUDER")) };
		getResearchItem("STONEEXTRUDER", "WITCHGADG", researchAspects, -4, -6, 4, new ItemStack(WGContent.BlockWoodenDevice,1,2)).addWarp(2).setParents("WG.ARCANESTONE","THAUMIUM").setConcealed().setPages(pages).registerResearchItem();
		//ICESOLIDIFIER
		researchAspects = new AspectList().add(Aspect.COLD, 1).add(Aspect.MECHANISM, 1).add(Aspect.TOOL, 1);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.ICESOLIDIFIER.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("ICESOLIDIFIER")), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("SNOWBALLER"))};
		getResearchItem("ICESOLIDIFIER", "WITCHGADG", researchAspects, -4, -8, 4, new ItemStack(WGContent.BlockWoodenDevice,1,1)).addWarp(3).setParents("STONEEXTRUDER").setConcealed().setPages(pages).registerResearchItem();

		if(Loader.isModLoaded("magicbees")) {
			//AGEINGSTONE
			researchAspects = new AspectList().add(Aspect.LIFE, 3).add(Aspect.MECHANISM, 3).add((Aspect) MagicBeesAPI.thaumcraftAspectTempus, 3).add(Aspect.EARTH, 6);
			if (Aspect.getAspect("tempus") != null) researchAspects.add(Aspect.getAspect("tempus"), 2);
			pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.AGEINGSTONE.1"), new ResearchPage((ShapedArcaneRecipe) WGContent.recipeList.get("AGEINGSTONE"))};
			getResearchItem("AGEINGSTONE", "WITCHGADG", researchAspects, -5, -6, 3, new ItemStack(WGContent.BlockStoneDevice, 1, 1)).addWarp(1).setParents("WG.ARCANESTONE", "MB_EssenceTime").setConcealed().setPages(pages).registerResearchItem();
		}

		//ETHEREALWALL
		researchAspects = new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.MECHANISM, 2).add(Aspect.EARTH, 4).add(Aspect.MOTION,7).add(Aspect.TRAP, 4);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.ETHEREALWALL.1"), new ResearchPage("witchinggadgets_research_page.ETHEREALWALL.2"), new ResearchPage((ShapedArcaneRecipe) WGContent.recipeList.get("ETHEREALWALL")) };
		getResearchItem("ETHEREALWALL", "WITCHGADG", researchAspects, -6, -6, 3, new ItemStack(WGContent.BlockStoneDevice,1,0)).addWarp(3).setParents("WG.ARCANESTONE").setConcealed().setPages(pages).registerResearchItem();

		
		//ORIGINAL BATHSALTS
		//getFakeResearchItem("BATHSALTS", "ALCHEMY", -1,-4,  new ItemStack(ConfigItems.itemBathSalts)).registerResearchItem();
		new FakeResearchItem("WG.BATHSALTS", "WITCHGADG", "BATHSALTS", "ALCHEMY", -5, -2, ResearchCategories.getResearch("BATHSALTS").icon_item).setParents("WGPOTIONS").setConcealed().registerResearchItem();
		//SAUNA
		researchAspects = new AspectList().add(Aspect.WATER, 3).add(Aspect.FIRE, 3).add(Aspect.MECHANISM, 1).add(Aspect.HEAL,25).add(Aspect.ORDER,7).add(Aspect.WEATHER,2);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.SAUNASTOVE.1"), new ResearchPage("witchinggadgets_research_page.SAUNASTOVE.2"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("SAUNASTOVE")) };
		getResearchItem("SAUNASTOVE", "WITCHGADG", researchAspects, -7,-2, 4, new ItemStack(WGContent.BlockWoodenDevice,1,4)).setParents("WG.BATHSALTS").setPages(pages).setConcealed().registerResearchItem();

		//ORIGINAL JAR
		//getFakeResearchItem("JARLABEL", "ALCHEMY", -2,2,  new ItemStack(ConfigBlocks.blockJar)).registerResearchItem();
		new FakeResearchItem("WG.JARLABEL", "WITCHGADG", "JARLABEL", "ALCHEMY", -3, -4, ResearchCategories.getResearch("JARLABEL").icon_item).setParents("WGPOTIONS").setConcealed().registerResearchItem();
		//LABELLIB
		researchAspects = new AspectList().add(Aspect.SENSES, 4).add(Aspect.MIND, 4).add(Aspect.TOOL, 2);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.LABELLIB.1"), new ResearchPage((ShapedArcaneRecipe) WGContent.recipeList.get("LABELLIB")) };
		getResearchItem("LABELLIB", "WITCHGADG", researchAspects, -3, -6, 1, new ItemStack(WGContent.BlockWoodenDevice,1,5)).setParents("WG.JARLABEL").setPages(pages).setConcealed().registerResearchItem();

		
		
		//MIRROR
		if(Config.allowMirrors)
		{
			//getFakeResearchItem("MIRRORESSENTIA", "ARTIFICE", -2,-2,  new ItemStack(ConfigBlocks.blockMirror,1,6)).registerResearchItem();
			new FakeResearchItem("WG.MIRRORESSENTIA", "WITCHGADG", "MIRRORESSENTIA", "ARTIFICE", -3, 4, ResearchCategories.getResearch("MIRRORESSENTIA").icon_item).setParents("WGPOTIONS").setConcealed().registerResearchItem();

			//MIRRORPUMP
			researchAspects = new AspectList().add(Aspect.TOOL, 12).add(Aspect.WATER, 7).add(Aspect.TRAVEL, 5).add(Aspect.SLIME, 4).add(Aspect.MAGIC,4).add(Aspect.MECHANISM,8);
			pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.MIRRORPUMP.1"), new ResearchPage((InfusionRecipe)WGContent.recipeList.get("MIRRORPUMP")) };
			getResearchItem("MIRRORPUMP", "WITCHGADG", researchAspects, -3, 6, 5, new ItemStack(WGContent.BlockMetalDevice,1,0)).setPages(pages).setParents("WG.MIRRORESSENTIA").setConcealed().registerResearchItem();

		}

		
		//ORIGINAL ALCHEMICALMANUFACTURE
		//getFakeResearchItem("ALCHEMICALMANUFACTURE", "ALCHEMY", -4,-3, new ResourceLocation("thaumcraft", "textures/misc/r_alchman.png")).registerResearchItem();
		new FakeResearchItem("WG.ALCHEMICALMANUFACTURE", "WITCHGADG", "ALCHEMICALMANUFACTURE", "ALCHEMY", -5, -3, ResearchCategories.getResearch("ALCHEMICALMANUFACTURE").icon_resource).setParents("WGPOTIONS").setConcealed().registerResearchItem();
		//ALCHEMICALTRANSFORM
		researchAspects = new AspectList().add(Aspect.PLANT, 4).add(Aspect.LIFE, 2).add(Aspect.WATER, 2);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.ALCHEMICALTRANSMOGRIFY.1"), new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("ALCHEMICALTRANSMOGRIFY_GRASS")), new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("ALCHEMICALTRANSMOGRIFY_MYCEL")), new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("ALCHEMICALTRANSMOGRIFY_SAND")), new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("ALCHEMICALTRANSMOGRIFY_FLINT"))};
		getResearchItem("ALCHEMICALTRANSMOGRIFY", "WITCHGADG", researchAspects, -7, -3, 3, new ItemStack(Blocks.grass)).setParents("WG.ALCHEMICALMANUFACTURE").setConcealed().setPages(pages).registerResearchItem();
		//ROSEVINE
		researchAspects = new AspectList().add(Aspect.PLANT, 2).add(Aspect.AIR, 3).add(Aspect.ENTROPY, 2).add(Aspect.WEAPON, 4).add(Aspect.CROP,8);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.ROSEVINE.1"), new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("ROSEVINE.1")), new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("ROSEVINE.2")), new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("ROSEVINE.3"))};
		getResearchItem("ROSEVINE", "WITCHGADG", researchAspects, -7, -4, 3, new ItemStack(WGContent.BlockRoseVine)).setParents("WG.ALCHEMICALMANUFACTURE").setConcealed().setPages(pages).registerResearchItem();

		
		//ORIGINAL PUREIRON
		//getFakeResearchItem("PUREIRON", "ALCHEMY", -5, 0, new ItemStack(ConfigItems.itemNugget, 1, 16)).registerResearchItem();
		new FakeResearchItem("WG.PUREIRON", "WITCHGADG", "PUREIRON", "ALCHEMY", -5, 0, ResearchCategories.getResearch("PUREIRON").icon_item).setParents("WGPOTIONS").setConcealed().registerResearchItem();
		//PURECINNABAR
		researchAspects = new AspectList().add(Aspect.METAL,5).add(Aspect.ORDER, 1).add(Aspect.POISON, 1);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.PURECINNABAR.1"), new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("PURECINNABAR"))};
		getResearchItem("PURECINNABAR", "WITCHGADG", researchAspects, -6, 1, 3, new ItemStack(ConfigItems.itemNugget, 1, 21)).setConcealed().setParents("WG.PUREIRON").setPages(pages).registerResearchItem();

		
		
		//METALLURGICPERFECTION_CLUSTERS
		if(WGConfig.allowClusters)
		{
			ArrayList<ResearchPage> clusterPages = new ArrayList<ResearchPage>();
			clusterPages.add(new ResearchPage("witchinggadgets_research_page.METALLURGICPERFECTION_CLUSTERS.1"));
			for(String ore : WGContent.GT_Cluster) {
				
				if(WGContent.recipeList.containsKey("METALLURGICPERFECTION_CLUSTERS_"+ore))
					clusterPages.add( new ResearchPage((CrucibleRecipe)WGContent.recipeList.get("METALLURGICPERFECTION_CLUSTERS_"+ore)) );
				
				String vore="Netherrack_"+ore;
				if(WGContent.recipeList.containsKey("METALLURGICPERFECTION_CLUSTERS_"+vore))
					clusterPages.add( new ResearchPage((CrucibleRecipe)WGContent.recipeList.get("METALLURGICPERFECTION_CLUSTERS_"+vore)) );
				
				vore="Endstone_"+ore;
				if(WGContent.recipeList.containsKey("METALLURGICPERFECTION_CLUSTERS_"+vore))
					clusterPages.add( new ResearchPage((CrucibleRecipe)WGContent.recipeList.get("METALLURGICPERFECTION_CLUSTERS_"+vore)) );
				
				vore="Blackgranite_"+ore;
				if(WGContent.recipeList.containsKey("METALLURGICPERFECTION_CLUSTERS_"+vore))
					clusterPages.add( new ResearchPage((CrucibleRecipe)WGContent.recipeList.get("METALLURGICPERFECTION_CLUSTERS_"+vore)) );
				
				vore="Redgranite_"+ore;
				if(WGContent.recipeList.containsKey("METALLURGICPERFECTION_CLUSTERS_"+vore))
					clusterPages.add( new ResearchPage((CrucibleRecipe)WGContent.recipeList.get("METALLURGICPERFECTION_CLUSTERS_"+vore)) );
				
				vore="Marble_"+ore;
				if(WGContent.recipeList.containsKey("METALLURGICPERFECTION_CLUSTERS_"+vore))
					clusterPages.add( new ResearchPage((CrucibleRecipe)WGContent.recipeList.get("METALLURGICPERFECTION_CLUSTERS_"+vore)) );
				
				vore="Basalt_"+ore;
				if(WGContent.recipeList.containsKey("METALLURGICPERFECTION_CLUSTERS_"+vore))
					clusterPages.add( new ResearchPage((CrucibleRecipe)WGContent.recipeList.get("METALLURGICPERFECTION_CLUSTERS_"+vore)) );
			}
			pages = clusterPages.toArray(new ResearchPage[0]);
			researchAspects = new AspectList().add(Aspect.METAL,20).add(Aspect.ORDER, 10).add(Aspect.CRYSTAL, 10).add(Aspect.EXCHANGE,20).add(Aspect.MINE,10).add(Aspect.MIND,5).add(Aspect.GREED,4).add((Aspect) gregtech.api.enums.TC_Aspects.NEBRISUM.mAspect,8);
			ArrayList<String> clusterParents = new ArrayList<String>();
			clusterParents.add("WG.PUREIRON");
			clusterParents.add("PUREGOLD");
			if(Utilities.researchExists("ALCHEMY", "PURECOPPER"))
				clusterParents.add("PURECOPPER");
			if(Utilities.researchExists("ALCHEMY", "PURETIN"))
				clusterParents.add("PURETIN");
			if(Utilities.researchExists("ALCHEMY", "PURESILVER"))
				clusterParents.add("PURESILVER");
			if(Utilities.researchExists("ALCHEMY", "PURELEAD"))
				clusterParents.add("PURELEAD");
			clusterParents.add("PURECINNABAR");
			getResearchItem("METALLURGICPERFECTION_CLUSTERS", "WITCHGADG", researchAspects, -7, 0, 10, new ResourceLocation("witchinggadgets:textures/gui/research/icon_mp_cluster.png")).addWarp(10).setConcealed().setSpecial().setParents(clusterParents.toArray(new String[0])).setPages(pages).registerResearchItem();
		}
		
		
		if(WGConfig.allowTransmutations)
		{
			//ORIGINAL TRANSIRON
			//getFakeResearchItem("TRANSIRON", "ALCHEMY", -5,-1, new ItemStack(ConfigItems.itemNugget, 1, 0)).registerResearchItem();
			new FakeResearchItem("WG.TRANSIRON", "WITCHGADG", "TRANSIRON", "ALCHEMY", -5, -1, ResearchCategories.getResearch("TRANSIRON").icon_item).setParents("WGPOTIONS").setConcealed().registerResearchItem();
			
			//METALLURGICPERFECTION_TRANSMUTATION
			ArrayList<ResearchPage> transmutePages = new ArrayList<ResearchPage>();
			transmutePages.add(new ResearchPage("witchinggadgets_research_page.METALLURGICPERFECTION_TRANSMUTATION.1"));
			for(String ore : WGContent.GT_Cluster)
				if(WGContent.recipeList.containsKey("METALLURGICPERFECTION_TRANSMUTATION_"+ore))
					transmutePages.add( new ResearchPage((CrucibleRecipe)WGContent.recipeList.get("METALLURGICPERFECTION_TRANSMUTATION_"+ore)) );
			pages = transmutePages.toArray(new ResearchPage[0]);
			researchAspects = new AspectList().add(Aspect.METAL,20).add(Aspect.ORDER, 10).add(Aspect.TOOL, 10).add(Aspect.MAGIC,10).add(Aspect.MIND,5).add(Aspect.EXCHANGE,20).add(Aspect.GREED,4).add((Aspect) gregtech.api.enums.TC_Aspects.NEBRISUM.mAspect,8);
			ArrayList<String> transmuteParents = new ArrayList<String>();
			transmuteParents.add("WG.TRANSIRON");
			transmuteParents.add("TRANSGOLD");
			if(Utilities.researchExists("ALCHEMY", "TRANSCOPPER"))
				transmuteParents.add("TRANSCOPPER");
			if(Utilities.researchExists("ALCHEMY", "TRANSTIN"))
				transmuteParents.add("TRANSTIN");
			if(Utilities.researchExists("ALCHEMY", "TRANSSILVER"))
				transmuteParents.add("TRANSSILVER");
			if(Utilities.researchExists("ALCHEMY", "TRANSLEAD"))
				transmuteParents.add("TRANSLEAD");
			getResearchItem("METALLURGICPERFECTION_TRANSMUTATION", "WITCHGADG", researchAspects, -7, -1, 10, new ResourceLocation("witchinggadgets:textures/gui/research/icon_mp_trans.png")).addWarp(10).setConcealed().setSpecial().setParents(transmuteParents.toArray(new String[0])).setPages(pages).registerResearchItem();
		}

		//ORIGINAL INFERNALFURNACE
		//getFakeResearchItem("INFERNALFURNACE", "ARTIFICE", 2,-2, new ResourceLocation("thaumcraft", "textures/misc/r_infernalfurnace.png")).registerResearchItem();
		new FakeResearchItem("WG.INFERNALFURNACE", "WITCHGADG", "INFERNALFURNACE", "ARTIFICE", 3, 3, ResearchCategories.getResearch("INFERNALFURNACE").icon_resource).setParents("WGPOTIONS").setConcealed().registerResearchItem();
		//INFERNALBLASTFURNACE
		researchAspects = new AspectList().add(Aspect.FIRE,2).add(Aspect.METAL, 1).add(Aspect.CRAFT, 1).add(Aspect.DARKNESS, 1).add(Aspect.TAINT, 1);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.INFERNALBLASTFURNACE.1"), new ResearchPage("witchinggadgets_research_page.INFERNALBLASTFURNACE.2"), new ResearchPage((List) WGContent.recipeList.get("INFERNALBLASTFURNACE")), new ResearchPage("witchinggadgets_research_page.INFERNALBLASTFURNACE.3")};
		getResearchItem("INFERNALBLASTFURNACE", "WITCHGADG", researchAspects, 3, 5, 6, new ResourceLocation("witchinggadgets:textures/gui/research/icon_blastfurnace.png")).addWarp(3).setConcealed().setPages(pages).setParents("WG.INFERNALFURNACE").registerResearchItem();

		//ORIGINAL INFUSIONENCHANTMENT
		//getFakeResearchItem("INFUSIONENCHANTMENT", "ARTIFICE", -4,3, new ResourceLocation("thaumcraft:textures/misc/r_enchant.png")).setSiblings().registerResearchItem();
		new FakeResearchItem("WG.INFUSIONENCHANTMENT", "WITCHGADG", "INFUSIONENCHANTMENT", "ARTIFICE", -4, 3, ResearchCategories.getResearch("INFUSIONENCHANTMENT").icon_resource).setParents("WGPOTIONS").setConcealed().registerResearchItem();
		
		//ENCH_INVISIBLEGEAR
		researchAspects = new AspectList().add(Aspect.MAGIC, 2).add(Aspect.CRYSTAL, 4).add(Aspect.DARKNESS, 4);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.ENCH_INVISIBLEGEAR.1"), new ResearchPage((InfusionEnchantmentRecipe) WGContent.recipeList.get("ENCH_INVISIBLEGEAR"))};
		getResearchItem("ENCH_INVISIBLEGEAR", "WITCHGADG", researchAspects, -5, 5, 4, new ResourceLocation("witchinggadgets:textures/gui/research/icon_ench_invisGear.png")).setParents("WG.INFUSIONENCHANTMENT").setConcealed().setPages(pages).registerResearchItem();
		//ENCH_REVEALING
		researchAspects = new AspectList().add(Aspect.MAGIC, 2).add(Aspect.SENSES, 4).add(Aspect.LIGHT, 4);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.ENCH_UNVEILING.1"), new ResearchPage((InfusionEnchantmentRecipe) WGContent.recipeList.get("ENCH_UNVEILING"))};
		getResearchItem("ENCH_UNVEILING", "WITCHGADG", researchAspects, -6, 3, 4, new ResourceLocation("witchinggadgets:textures/gui/research/icon_ench_unveiling.png")).setParents("WG.INFUSIONENCHANTMENT").setConcealed().setPages(pages).registerResearchItem();
		//ENCH_STEALTH
		researchAspects = new AspectList().add(Aspect.MAGIC, 2).add(Aspect.MOTION, 4).add(Aspect.DARKNESS, 4);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.ENCH_STEALTH.1"), new ResearchPage((InfusionEnchantmentRecipe) WGContent.recipeList.get("ENCH_STEALTH"))};
		getResearchItem("ENCH_STEALTH", "WITCHGADG", researchAspects, -6, 4, 4, new ResourceLocation("witchinggadgets:textures/gui/research/icon_ench_stealth.png")).addWarp(2).setParents("WG.INFUSIONENCHANTMENT").setConcealed().setPages(pages).registerResearchItem();
		//ENCH_BACKSTAB
		researchAspects = new AspectList().add(Aspect.MAGIC, 2).add(Aspect.WEAPON, 4).add(Aspect.DARKNESS, 4);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.ENCH_BACKSTAB.1"), new ResearchPage((InfusionEnchantmentRecipe) WGContent.recipeList.get("ENCH_BACKSTAB"))};
		getResearchItem("ENCH_BACKSTAB", "WITCHGADG", researchAspects, -7, 4, 4, new ResourceLocation("witchinggadgets:textures/gui/research/icon_ench_backstab.png")).addWarp(2).setParents("ENCH_STEALTH").setConcealed().setPages(pages).registerResearchItem();
		//ENCH_RIDEPROTECT
		researchAspects = new AspectList().add(Aspect.MAGIC, 2).add(Aspect.TRAP, 4).add(Aspect.ARMOR, 4);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.ENCH_RIDEPROTECT.1"), new ResearchPage((InfusionEnchantmentRecipe) WGContent.recipeList.get("ENCH_RIDEPROTECT"))};
		getResearchItem("ENCH_RIDEPROTECT", "WITCHGADG", researchAspects, -4, 5, 4, new ResourceLocation("witchinggadgets:textures/gui/research/icon_ench_rideProtect.png")).setParents("WG.INFUSIONENCHANTMENT").setConcealed().setPages(pages).registerResearchItem();
		//ENCH_SOULBOUND
		researchAspects = new AspectList().add(Aspect.MAGIC, 4).add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 2);
		pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.ENCH_SOULBOUND.1"), new ResearchPage((InfusionEnchantmentRecipe) WGContent.recipeList.get("ENCH_SOULBOUND")), new ResearchPage((ShapedArcaneRecipe)WGContent.recipeList.get("ENCH_SOULBOUND_BOOK"))};
		getResearchItem("ENCH_SOULBOUND", "WITCHGADG", researchAspects, -6, 2, 4, new ResourceLocation("witchinggadgets:textures/gui/research/icon_ench_soulbound.png")).setParents("WG.INFUSIONENCHANTMENT").setConcealed().setPages(pages).registerResearchItem();

		if (WGConfig.terraformer && WGContent.recipeList.containsKey("TERRAFORMER")) {
			//ORIGINAL CENTRIFUGE
			new FakeResearchItem("WG.CENTRIFUGE", "WITCHGADG", "CENTRIFUGE", "ALCHEMY", 0, -5, new ItemStack(ConfigBlocks.blockTube, 1, 2)).setParents("WGPOTIONS").setConcealed().registerResearchItem();
			//TERRAFORMER
			researchAspects = new AspectList().add(Aspect.EARTH, 64).add(Aspect.EXCHANGE, 14).add(Aspect.ENERGY, 7).add(Aspect.ORDER, 8).add(Aspect.ENTROPY, 16).add(Aspect.MINE, 24).add(Aspect.COLD, 12).add(Aspect.FIRE, 9).add(Aspect.AURA, 8);
			pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMER.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("TERRAFORMER"))};
			getResearchItem("TERRAFORMER", "WITCHGADG", researchAspects, 0, -7, 5, new ItemStack(WGContent.BlockMetalDevice, 1, 2)).addWarp(10).setPages(pages).setParents("WG.CENTRIFUGE").setConcealed().registerResearchItem();

			researchAspects = new AspectList().add(Aspect.PLANT, 8).add(Aspect.ORDER, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
			pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_PLAINS.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("TERRAFORMFOCUS_PLAINS"))};
			getResearchItem("TERRAFORMFOCUS_PLAINS", "WITCHGADG", researchAspects, -2, -6, 3, new ItemStack(WGContent.BlockMetalDevice, 1, 3)).setPages(pages).setParents("TERRAFORMER").setConcealed().registerResearchItem();

			researchAspects = new AspectList().add(Aspect.COLD, 8).add(Aspect.ORDER, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
			pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_COLDTAIGA.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("TERRAFORMFOCUS_COLDTAIGA"))};
			getResearchItem("TERRAFORMFOCUS_COLDTAIGA", "WITCHGADG", researchAspects, -2, -7, 3, new ItemStack(WGContent.BlockMetalDevice, 1, 4)).setPages(pages).setParents("TERRAFORMER").setConcealed().registerResearchItem();

			researchAspects = new AspectList().add(Aspect.FIRE, 4).add(Aspect.ENTROPY, 8).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
			pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_DESERT.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("TERRAFORMFOCUS_DESERT"))};
			getResearchItem("TERRAFORMFOCUS_DESERT", "WITCHGADG", researchAspects, -2, -8, 3, new ItemStack(WGContent.BlockMetalDevice, 1, 5)).setPages(pages).setParents("TERRAFORMER").setConcealed().registerResearchItem();

			researchAspects = new AspectList().add(Aspect.TREE, 8).add(Aspect.PLANT, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
			pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_JUNGLE.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("TERRAFORMFOCUS_JUNGLE"))};
			getResearchItem("TERRAFORMFOCUS_JUNGLE", "WITCHGADG", researchAspects, -1, -9, 3, new ItemStack(WGContent.BlockMetalDevice, 1, 6)).setPages(pages).setParents("TERRAFORMER").setConcealed().registerResearchItem();

			researchAspects = new AspectList().add(Aspect.DARKNESS, 8).add(Aspect.FIRE, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
			pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_HELL.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("TERRAFORMFOCUS_HELL"))};
			getResearchItem("TERRAFORMFOCUS_HELL", "WITCHGADG", researchAspects, 0, -9, 3, new ItemStack(WGContent.BlockMetalDevice, 1, 7)).addWarp(2).setPages(pages).setParents("TERRAFORMER").setConcealed().registerResearchItem();

			researchAspects = new AspectList().add(Aspect.PLANT, 8).add(Aspect.EARTH, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
			pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_MUSHROOM.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("TERRAFORMFOCUS_MUSHROOM"))};
			getResearchItem("TERRAFORMFOCUS_MUSHROOM", "WITCHGADG", researchAspects, 1, -9, 3, new ItemStack(WGContent.BlockMetalDevice, 1, 9)).setPages(pages).setParents("TERRAFORMER").setConcealed().registerResearchItem();

			researchAspects = new AspectList().add(Aspect.TAINT, 8).add(Aspect.MAGIC, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
			pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_TAINT.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("TERRAFORMFOCUS_TAINT"))};
			getResearchItem("TERRAFORMFOCUS_TAINT", "WITCHGADG", researchAspects, 2, -8, 3, new ItemStack(WGContent.BlockMetalDevice, 1, 8)).addWarp(3).setPages(pages).setParents("TERRAFORMER", "BOTTLETAINT").setConcealed().registerResearchItem();

			researchAspects = new AspectList().add(Aspect.WATER, 8).add(Aspect.MAGIC, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
			pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_RIVER.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("TERRAFORMFOCUS_RIVER"))};
			getResearchItem("TERRAFORMFOCUS_RIVER", "WITCHGADG", researchAspects, 2, -6, 3, new ItemStack(WGContent.BlockMetalDevice, 1, 10)).setPages(pages).setParents("TERRAFORMER").setConcealed().registerResearchItem();

			researchAspects = new AspectList().add(Aspect.WATER, 8).add(Aspect.MAGIC, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
			pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_OCEAN.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("TERRAFORMFOCUS_OCEAN"))};
			getResearchItem("TERRAFORMFOCUS_OCEAN", "WITCHGADG", researchAspects, 3, -6, 3, new ItemStack(WGContent.BlockMetalDevice, 1, 11)).setPages(pages).setParents("TERRAFORMFOCUS_RIVER").setConcealed().registerResearchItem();

			researchAspects = new AspectList().add(Aspect.ELDRITCH, 8).add(Aspect.MAGIC, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
			pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_END.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("TERRAFORMFOCUS_END"))};
			getResearchItem("TERRAFORMFOCUS_END", "WITCHGADG", researchAspects, 2, -7, 3, new ItemStack(WGContent.BlockMetalDevice, 1, 12)).addWarp(3).setPages(pages).setParents("TERRAFORMER").setConcealed().registerResearchItem();

			researchAspects = new AspectList().add(Aspect.HEAL, 8).add(Aspect.MAGIC, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
			pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_MAGIC.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("TERRAFORMFOCUS_MAGIC"))};
			getResearchItem("TERRAFORMFOCUS_MAGIC", "WITCHGADG", researchAspects, -1, -10, 3, new ItemStack(WGContent.BlockMetalDevice, 1, 13)).setPages(pages).setParents("TERRAFORMFOCUS_JUNGLE").setConcealed().registerResearchItem();

		}
		

		//TODO primal
		if (WGConfig.modulePrimal) {
			//ORIGINAL PRIMPEARL
			//getFakeResearchItem("PRIMPEARL", "ELDRITCH", 0, 4, new ItemStack(ConfigItems.itemEldritchObject, 1, 3)).setSpecial().registerResearchItem();
			new FakeResearchItem("WG.PRIMPEARL", "WITCHGADG", "PRIMPEARL", "ELDRITCH", 0, 4, ResearchCategories.getResearch("PRIMPEARL").icon_item).registerResearchItem();
			
			//PRIMORDIALGEARSET
			researchAspects = new AspectList().add(Aspect.MAGIC,1).add(Aspect.ENERGY,1).add(Aspect.AIR,1).add(Aspect.FIRE,1).add(Aspect.WATER,1).add(Aspect.EARTH,1).add(Aspect.ORDER,1).add(Aspect.ENTROPY,1);
			pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.PRIMORDIALGEARSET.1"),new ResearchPage("witchinggadgets_research_page.PRIMORDIALGEARSET.2")};
			getResearchItem("PRIMORDIALGEARSET", "WITCHGADG", researchAspects, 0,6,10, new ResourceLocation("witchinggadgets:textures/gui/research/icon_primordialGear.png"))
				.setParents("VOIDMETAL","WG.PRIMPEARL").setConcealed().setPages(pages).registerResearchItem();
			
			//EMPOWERPEARL
			researchAspects = new AspectList().add(Aspect.MAGIC,8).add(Aspect.CRYSTAL,4).add(Aspect.VOID,4).add(Aspect.ENERGY,4);
			pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.EMPOWERPEARL.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("EMPOWERPEARL"))};
			getResearchItem("EMPOWERPEARL", "WITCHGADG", researchAspects, 0,2,5, new ItemStack(WGContent.ItemMaterial,1,12))
				.setParents("WG.PRIMPEARL","WGPOTIONS").setItemTriggers(new ItemStack(WGContent.ItemMaterial,1,12)).setHidden().setPages(pages).registerResearchItem();
			 
			if (WGConfig.moduleGemcutting) {
			//PRIMORDIALGLOVE
			researchAspects = new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.MAGIC, 1).add(Aspect.TOOL, 1).add(Aspect.AIR,1).add(Aspect.FIRE,1).add(Aspect.WATER,1).add(Aspect.EARTH,1).add(Aspect.ORDER,1).add(Aspect.ENTROPY,1);
			pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.PRIMORDIALGLOVE.1"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("PRIMORDIALGLOVE")), new ResearchPage("witchinggadgets_research_page.PRIMORDIALGLOVE.2")};
			getResearchItem("PRIMORDIALGLOVE", "WITCHGADG", researchAspects, 2, 5, 10, new ItemStack(WGContent.ItemPrimordialGlove)).addWarp(15)
				.setParents("VOIDMETAL","PRIMORDIALGEARSET","WG.PRIMPEARL").setConcealed().setPages(pages).registerResearchItem();
			}
			
			//PRIMORDIALWEAPONRY
			researchAspects = new AspectList().add(Aspect.ENTROPY, 1).add(Aspect.WEAPON, 1).add(Aspect.TOOL, 1).add(Aspect.AIR,1).add(Aspect.FIRE,1).add(Aspect.WATER,1).add(Aspect.MAGIC, 2).add(Aspect.EARTH,1).add(Aspect.ORDER,1);
			pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.PRIMORDIALWEAPONRY.1"), new ResearchPage("witchinggadgets_research_page.PRIMORDIALWEAPONRY.2"), new ResearchPage("witchinggadgets_research_page.PRIMORDIALWEAPONRY.3"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("PRIMORDIALWEAPONRY_CLAYMORE")), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("PRIMORDIALWEAPONRY_HAMMER")), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("PRIMORDIALWEAPONRY_GREATAXE"))};
			getResearchItem("PRIMORDIALWEAPONRY", "WITCHGADG", researchAspects, -2, 7, 10, new ResourceLocation("witchinggadgets:textures/gui/research/icon_primordialWeaponry.png")).addWarp(25)
				.setParents("PRIMORDIALGEARSET").setConcealed().setPages(pages).registerResearchItem();

			//PRIMORDIALHELMET
			researchAspects = new AspectList().add(Aspect.ARMOR, 1).add(Aspect.MAGIC, 1).add(Aspect.AIR,1).add(Aspect.FIRE,1).add(Aspect.WATER,1).add(Aspect.EARTH,1).add(Aspect.ORDER,1).add(Aspect.ENTROPY,1);
			pages = new ResearchPage[]{ new ResearchPage("witchinggadgets_research_page.PRIMORDIALARMOR.1"),new ResearchPage("witchinggadgets_research_page.PRIMORDIALARMOR.2"), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("PRIMORDIALARMOR_HELMET")), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("PRIMORDIALARMOR_CUIRASS")), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("PRIMORDIALARMOR_GREAVES")), new ResearchPage((InfusionRecipe) WGContent.recipeList.get("PRIMORDIALARMOR_BOOTS")),new ResearchPage((InfusionRecipe) WGContent.recipeList.get("HELMGOGGLES_PRIMORDIAL")),new ResearchPage((InfusionRecipe) WGContent.recipeList.get("MASKGRINNINGDEVIL_PRIMORDIAL")),new ResearchPage((InfusionRecipe) WGContent.recipeList.get("MASKANGRYGHOST_PRIMORDIAL")),new ResearchPage((InfusionRecipe) WGContent.recipeList.get("MASKSIPPINGFIEND_PRIMORDIAL")) };
			getResearchItem("PRIMORDIALARMOR", "WITCHGADG", researchAspects, 2, 7, 10, new ResourceLocation("witchinggadgets:textures/gui/research/icon_primordialArmor.png")).addWarp(25)
				.setParents("PRIMORDIALGEARSET","ARMORFORTRESS").setConcealed().setPages(pages).registerResearchItem();

		}
		

		if(Loader.isModLoaded("dreamcraft")) {
			//Dreamcraft-Pearl-Dupe Recipe fix
			researchAspects = new AspectList().add(Aspect.AURA, 64).add(Aspect.ORDER, 256).add(Aspect.FIRE, 256).add(Aspect.EARTH, 256).add(Aspect.WATER, 256).add(Aspect.AIR, 256).add(Aspect.ENTROPY, 256).add(Aspect.MAGIC, 64).add(Aspect.TAINT, 64);
			InfusionRecipe PearlDupe = ThaumcraftApi.addInfusionCraftingRecipe("PRIMORDRIALPEARL", GT_ModHandler.getModItem("Thaumcraft", "ItemEldritchObject", 1L, 3), 20, researchAspects, GT_ModHandler.getModItem("dreamcraft", "item.PrimordialPearlFragment", 1L), new ItemStack[]{
					GT_ModHandler.getModItem("Thaumcraft", "ItemEldritchObject", 1L), GT_ModHandler.getModItem("ThaumicTinkerer", "kamiResource", 1L, 6), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Ichorium, 1L),
					GT_ModHandler.getModItem("TwilightForest", "tile.TFAuroraBrick", 1L), GT_ModHandler.getModItem("Thaumcraft", "ItemEldritchObject", 1L), GT_ModHandler.getModItem("TwilightForest", "tile.TFAuroraBrick", 1L),
					GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Ichorium, 1L), GT_ModHandler.getModItem("ThaumicTinkerer", "kamiResource", 1L, 7), GT_ModHandler.getModItem("Thaumcraft", "ItemEldritchObject", 1L),
					GT_ModHandler.getModItem("ThaumicTinkerer", "kamiResource", 1L, 7), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Ichorium, 1L), GT_ModHandler.getModItem("TwilightForest", "tile.TFAuroraBrick", 1L),
					GT_ModHandler.getModItem("Thaumcraft", "ItemEldritchObject", 1L), GT_ModHandler.getModItem("TwilightForest", "tile.TFAuroraBrick", 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Ichorium, 1L),
					GT_ModHandler.getModItem("ThaumicTinkerer", "kamiResource", 1L, 6)});

			ResearchItem PearlDupeResearch = new ResearchItem("PRIMORDRIALPEARL", "WITCHGADG", new AspectList().add(Aspect.AURA,27).add(Aspect.MAGIC,24).add(Aspect.TAINT,21).add(Aspect.ORDER,18).add(Aspect.ENTROPY,15).add(Aspect.AIR,12).add(Aspect.FIRE,9).add(Aspect.EARTH,6).add(Aspect.WATER,3), -1, 3, 20, GT_ModHandler.getModItem("Thaumcraft", "ItemEldritchObject", 1L,3));
			pages = new ResearchPage[] {new ResearchPage("tc.research_page.PRIMORDRIALPEARL.1"),new ResearchPage((InfusionRecipe) PearlDupe)};
			PearlDupeResearch.setPages(pages).setRound().setParentsHidden("PRIMPEARL","ICHORIUM").setConcealed().registerResearchItem();
			ThaumcraftApi.addWarpToResearch("PRIMORDRIALPEARL", 20);
		}
	}
	
	public static void modifyStandardThaumcraftResearch()
	{
		//Add Thaumium Shears
		ResearchItem thaumium = ResearchCategories.getResearch("THAUMIUM");
		ResearchPage[] pages = thaumium.getPages();
		ResearchPage[] newPages = new ResearchPage[pages.length+1];
		System.arraycopy(pages, 0, newPages, 0, 7);
		newPages[7] = new ResearchPage((IRecipe)WGContent.recipeList.get("THAUMIUMSHEARS"));
		System.arraycopy(pages, 7, newPages, 8, newPages.length - 8);
		thaumium.setPages(newPages);
	}

	private static void registerCompoundRecipe(String tag, String tagAddon, AspectList creationAspects, int sizeX, int sizeY, int sizeZ, Object... recipe)
	{
		List<Object> compoundRecipe = Arrays.asList(creationAspects, sizeX, sizeY, sizeZ, Arrays.asList(recipe));
		WGContent.recipeList.put(tag+tagAddon, compoundRecipe);
	}

	private static WGResearchItem getResearchItem(String tag, String category, AspectList researchAspects, int xPos, int yPos, int complexity, Object icon)
	{
		WGResearchItem item = null;
		if(icon instanceof ItemStack)
			item = new WGResearchItem(tag, category, researchAspects, xPos, yPos, complexity, (ItemStack)icon);
		if(icon instanceof ResourceLocation)
			item = new WGResearchItem(tag, category, researchAspects, xPos, yPos, complexity, (ResourceLocation)icon);
		return item;
	}
}