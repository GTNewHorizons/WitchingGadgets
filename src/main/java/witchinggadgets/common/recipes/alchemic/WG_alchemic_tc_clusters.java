package witchinggadgets.common.recipes.alchemic;

import static witchinggadgets.common.recipes.WGResearchUtils.addResearchPage;
import static witchinggadgets.common.recipes.WGResearchUtils.refreshResearchPages;
import static witchinggadgets.common.recipes.WG_alchemic_recipes.registerAlchemyRecipe;
import static witchinggadgets.common.recipes.WG_alchemic_recipes.removeCrucibleRecipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.WGContent;

public class WG_alchemic_tc_clusters {

    public static void registerExistingClusters() {
        removeCrucibleRecipe(new ItemStack(ConfigItems.itemNugget, 1, 16)); // Iron
        removeCrucibleRecipe(new ItemStack(ConfigItems.itemNugget, 1, 17)); // Copper
        removeCrucibleRecipe(new ItemStack(ConfigItems.itemNugget, 1, 18)); // Tin
        removeCrucibleRecipe(new ItemStack(ConfigItems.itemNugget, 1, 19)); // Silver
        removeCrucibleRecipe(new ItemStack(ConfigItems.itemNugget, 1, 20)); // Lead
        removeCrucibleRecipe(new ItemStack(ConfigItems.itemNugget, 1, 31)); // Gold

        registerAlchemyRecipe(
                "PUREIRON",
                "",
                new ItemStack(ConfigItems.itemNugget, 2, 16),
                "oreIron",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        if (!OreDictionary.getOres("oreNetherrackIron").isEmpty()) {
            registerAlchemyRecipe(
                    "PUREIRON",
                    ".3",
                    new ItemStack(ConfigItems.itemNugget, 4, 16),
                    "oreNetherrackIron",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreEndstoneIron").isEmpty()) {
            registerAlchemyRecipe(
                    "PUREIRON",
                    ".3",
                    new ItemStack(ConfigItems.itemNugget, 4, 16),
                    "oreEndstoneIron",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreBlackgraniteIron").isEmpty()) {
            registerAlchemyRecipe(
                    "PUREIRON",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 16),
                    "oreBlackgraniteIron",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreRedgraniteIron").isEmpty()) {
            registerAlchemyRecipe(
                    "PUREIRON",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 16),
                    "oreRedgraniteIron",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreMarbleIron").isEmpty()) {
            registerAlchemyRecipe(
                    "PUREIRON",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 16),
                    "oreMarbleIron",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreBasaltIron").isEmpty()) {
            registerAlchemyRecipe(
                    "PUREIRON",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 16),
                    "oreBasaltIron",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("rawOreIron").isEmpty()) {
            registerAlchemyRecipe(
                    "PUREIRON",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 16),
                    "rawOreIron",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
        }

        refreshResearchPages("PUREIRON");
        if (WGContent.recipeList.get("PUREIRON.3") != null) {
            addResearchPage("PUREIRON", new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("PUREIRON.3")));
        }

        if (!OreDictionary.getOres("oreCopper").isEmpty()) {
            registerAlchemyRecipe(
                    "PURECOPPER",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 17),
                    "oreCopper",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreNetherrackCopper").isEmpty()) {
            registerAlchemyRecipe(
                    "PURECOPPER",
                    ".3",
                    new ItemStack(ConfigItems.itemNugget, 4, 17),
                    "oreNetherrackCopper",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreEndstoneCopper").isEmpty()) {
            registerAlchemyRecipe(
                    "PURECOPPER",
                    ".3",
                    new ItemStack(ConfigItems.itemNugget, 4, 17),
                    "oreEndstoneCopper",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreBlackgraniteCopper").isEmpty()) {
            registerAlchemyRecipe(
                    "PURECOPPER",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 17),
                    "oreBlackgraniteCopper",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreRedgraniteCopper").isEmpty()) {
            registerAlchemyRecipe(
                    "PURECOPPER",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 17),
                    "oreRedgraniteCopper",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreMarbleCopper").isEmpty()) {
            registerAlchemyRecipe(
                    "PURECOPPER",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 17),
                    "oreMarbleCopper",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreBasaltCopper").isEmpty()) {
            registerAlchemyRecipe(
                    "PURECOPPER",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 17),
                    "oreBasaltCopper",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("rawOreCopper").isEmpty()) {
            registerAlchemyRecipe(
                    "PURECOPPER",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 17),
                    "rawOreCopper",
                    new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.EXCHANGE, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (ResearchCategories.getResearch("PURECOPPER") != null) {
            refreshResearchPages("PURECOPPER");
        }
        if (WGContent.recipeList.get("PURECOPPER.3") != null) {
            addResearchPage("PURECOPPER", new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("PURECOPPER.3")));
        }

        if (!OreDictionary.getOres("oreTin").isEmpty()) {
            registerAlchemyRecipe(
                    "PURETIN",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 18),
                    "oreTin",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreNetherrackTin").isEmpty()) {
            registerAlchemyRecipe(
                    "PURETIN",
                    ".3",
                    new ItemStack(ConfigItems.itemNugget, 4, 18),
                    "oreNetherrackTin",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreEndstoneTin").isEmpty()) {
            registerAlchemyRecipe(
                    "PURETIN",
                    ".3",
                    new ItemStack(ConfigItems.itemNugget, 4, 18),
                    "oreEndstoneTin",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreBlackgraniteTin").isEmpty()) {
            registerAlchemyRecipe(
                    "PURETIN",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 18),
                    "oreBlackgraniteTin",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreRedgraniteTin").isEmpty()) {
            registerAlchemyRecipe(
                    "PURETIN",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 18),
                    "oreRedgraniteTin",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreMarbleTin").isEmpty()) {
            registerAlchemyRecipe(
                    "PURETIN",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 18),
                    "oreMarbleTin",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreBasaltTin").isEmpty()) {
            registerAlchemyRecipe(
                    "PURETIN",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 18),
                    "oreBasaltTin",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("rawOreTin").isEmpty()) {
            registerAlchemyRecipe(
                    "PURETIN",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 18),
                    "rawOreTin",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (ResearchCategories.getResearch("PURETIN") != null) {
            refreshResearchPages("PURETIN");
        }
        if (WGContent.recipeList.get("PURETIN.3") != null) {
            addResearchPage("PURETIN", new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("PURETIN.3")));
        }

        if (!OreDictionary.getOres("oreSilver").isEmpty()) {
            registerAlchemyRecipe(
                    "PURESILVER",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 19),
                    "oreSilver",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreNetherrackSilver").isEmpty()) {
            registerAlchemyRecipe(
                    "PURESILVER",
                    ".3",
                    new ItemStack(ConfigItems.itemNugget, 4, 19),
                    "oreNetherrackSilver",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreEndstoneSilver").isEmpty()) {
            registerAlchemyRecipe(
                    "PURESILVER",
                    ".3",
                    new ItemStack(ConfigItems.itemNugget, 4, 19),
                    "oreEndstoneSilver",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreBlackgraniteSilver").isEmpty()) {
            registerAlchemyRecipe(
                    "PURESILVER",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 19),
                    "oreBlackgraniteSilver",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreRedgraniteSilver").isEmpty()) {
            registerAlchemyRecipe(
                    "PURESILVER",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 19),
                    "oreRedgraniteSilver",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreMarbleSilver").isEmpty()) {
            registerAlchemyRecipe(
                    "PURESILVER",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 19),
                    "oreMarbleSilver",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("oreBasaltSilver").isEmpty()) {
            registerAlchemyRecipe(
                    "PURESILVER",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 19),
                    "oreBasaltSilver",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (!OreDictionary.getOres("rawOreSilver").isEmpty()) {
            registerAlchemyRecipe(
                    "PURESILVER",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 19),
                    "rawOreSilver",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1)
                            .add(Aspect.ORDER, 1));
        }

        if (ResearchCategories.getResearch("PURESILVER") != null) {
            refreshResearchPages("PURESILVER");
        }
        if (WGContent.recipeList.get("PURESILVER.3") != null) {
            addResearchPage("PURESILVER", new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("PURESILVER.3")));
        }

        if (!OreDictionary.getOres("oreLead").isEmpty()) {
            registerAlchemyRecipe(
                    "PURELEAD",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 20),
                    "oreLead",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));
        }

        if (!OreDictionary.getOres("oreNetherrackLead").isEmpty()) {
            registerAlchemyRecipe(
                    "PURELEAD",
                    ".3",
                    new ItemStack(ConfigItems.itemNugget, 4, 20),
                    "oreNetherrackLead",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));
        }

        if (!OreDictionary.getOres("oreEndstoneLead").isEmpty()) {
            registerAlchemyRecipe(
                    "PURELEAD",
                    ".3",
                    new ItemStack(ConfigItems.itemNugget, 4, 20),
                    "oreEndstoneLead",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));
        }

        if (!OreDictionary.getOres("oreBlackgraniteLead").isEmpty()) {
            registerAlchemyRecipe(
                    "PURELEAD",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 20),
                    "oreBlackgraniteLead",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));
        }

        if (!OreDictionary.getOres("oreRedgraniteLead").isEmpty()) {
            registerAlchemyRecipe(
                    "PURELEAD",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 20),
                    "oreRedgraniteLead",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));
        }

        if (!OreDictionary.getOres("oreMarbleLead").isEmpty()) {
            registerAlchemyRecipe(
                    "PURELEAD",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 20),
                    "oreMarbleLead",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));
        }

        if (!OreDictionary.getOres("oreBasaltLead").isEmpty()) {
            registerAlchemyRecipe(
                    "PURELEAD",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 20),
                    "oreBasaltLead",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));
        }

        if (!OreDictionary.getOres("rawOreLead").isEmpty()) {
            registerAlchemyRecipe(
                    "PURELEAD",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 20),
                    "rawOreLead",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));
        }

        if (ResearchCategories.getResearch("PURELEAD") != null) {
            refreshResearchPages("PURELEAD");
        }
        if (WGContent.recipeList.get("PURELEAD.3") != null) {
            addResearchPage("PURELEAD", new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("PURELEAD.3")));
        }

        registerAlchemyRecipe(
                "PUREGOLD",
                "",
                new ItemStack(ConfigItems.itemNugget, 2, 31),
                "oreGold",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        if (!OreDictionary.getOres("oreNetherrackGold").isEmpty()) {
            registerAlchemyRecipe(
                    "PUREGOLD",
                    ".3",
                    new ItemStack(ConfigItems.itemNugget, 4, 31),
                    "oreNetherrackGold",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));
        }

        if (!OreDictionary.getOres("oreEndstoneGold").isEmpty()) {
            registerAlchemyRecipe(
                    "PUREGOLD",
                    ".3",
                    new ItemStack(ConfigItems.itemNugget, 4, 31),
                    "oreEndstoneGold",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));
        }

        if (!OreDictionary.getOres("oreBlackgraniteGold").isEmpty()) {
            registerAlchemyRecipe(
                    "PUREGOLD",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 31),
                    "oreBlackgraniteGold",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));
        }

        if (!OreDictionary.getOres("oreRedgraniteGold").isEmpty()) {
            registerAlchemyRecipe(
                    "PUREGOLD",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 31),
                    "oreRedgraniteGold",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));
        }

        if (!OreDictionary.getOres("oreMarbleGold").isEmpty()) {
            registerAlchemyRecipe(
                    "PUREGOLD",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 31),
                    "oreMarbleGold",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));
        }

        if (!OreDictionary.getOres("oreBasaltGold").isEmpty()) {
            registerAlchemyRecipe(
                    "PUREGOLD",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 31),
                    "oreBasaltGold",
                    new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));
        }

        if (!OreDictionary.getOres("rawOreGold").isEmpty()) {
            registerAlchemyRecipe(
                    "PUREGOLD",
                    "",
                    new ItemStack(ConfigItems.itemNugget, 2, 31),
                    "rawOreGold",
                    new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.GREED, 1)
                            .add(Aspect.ORDER, 1));
        }

        refreshResearchPages("PUREGOLD");
        if (WGContent.recipeList.get("PUREGOLD.3") != null) {
            addResearchPage("PUREGOLD", new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("PUREGOLD.3")));
        }
    }

}
