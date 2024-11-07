package witchinggadgets.common.recipes.alchemic;

import static witchinggadgets.common.recipes.WG_alchemic_recipes.addResearchPage;
import static witchinggadgets.common.recipes.WG_alchemic_recipes.registerAlchemyRecipe;
import static witchinggadgets.common.recipes.WG_alchemic_recipes.removeCrucibleRecipe;

import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
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
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 16),
                "oreIron",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PUREIRON",
                ".3",
                new ItemStack(ConfigItems.itemNugget, 4, 16),
                "oreNetherrackIron",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PUREIRON",
                ".3",
                new ItemStack(ConfigItems.itemNugget, 4, 16),
                "oreEndstoneIron",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PUREIRON",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 16),
                "oreBlackgraniteIron",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PUREIRON",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 16),
                "oreRedgraniteIron",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PUREIRON",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 16),
                "oreMarbleIron",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PUREIRON",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 16),
                "oreBasaltIron",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PUREIRON",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 16),
                "rawOreIron",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        addResearchPage("PUREIRON", new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("PUREIRON.3")));

        registerAlchemyRecipe(
                "PURECOPPER",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 17),
                "oreCopper",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURECOPPER",
                ".3",
                new ItemStack(ConfigItems.itemNugget, 4, 17),
                "oreNetherrackCopper",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURECOPPER",
                ".3",
                new ItemStack(ConfigItems.itemNugget, 4, 17),
                "oreEndstoneCopper",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURECOPPER",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 17),
                "oreBlackgraniteCopper",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURECOPPER",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 17),
                "oreRedgraniteCopper",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURECOPPER",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 17),
                "oreMarbleCopper",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURECOPPER",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 17),
                "oreBasaltCopper",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURECOPPER",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 17),
                "rawOreCopper",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.EXCHANGE, 1)
                        .add(Aspect.ORDER, 1));

        addResearchPage("PURECOPPER", new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("PURECOPPER.3")));

        registerAlchemyRecipe(
                "PURETIN",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 18),
                "oreTin",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                        .add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURETIN",
                ".3",
                new ItemStack(ConfigItems.itemNugget, 4, 18),
                "oreNetherrackTin",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                        .add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURETIN",
                ".3",
                new ItemStack(ConfigItems.itemNugget, 4, 18),
                "oreEndstoneTin",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                        .add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURETIN",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 18),
                "oreBlackgraniteTin",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                        .add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURETIN",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 18),
                "oreRedgraniteTin",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                        .add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURETIN",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 18),
                "oreMarbleTin",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                        .add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURETIN",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 18),
                "oreBasaltTin",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                        .add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURETIN",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 18),
                "rawOreTin",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1)
                        .add(Aspect.ORDER, 1));

        addResearchPage("PURETIN", new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("PURETIN.3")));

        registerAlchemyRecipe(
                "PURESILVER",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 19),
                "oreSilver",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURESILVER",
                ".3",
                new ItemStack(ConfigItems.itemNugget, 4, 19),
                "oreNetherrackSilver",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURESILVER",
                ".3",
                new ItemStack(ConfigItems.itemNugget, 4, 19),
                "oreEndstoneSilver",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURESILVER",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 19),
                "oreBlackgraniteSilver",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURESILVER",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 19),
                "oreRedgraniteSilver",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURESILVER",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 19),
                "oreMarbleSilver",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURESILVER",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 19),
                "oreBasaltSilver",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1).add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURESILVER",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 19),
                "rawOreSilver",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1).add(Aspect.ORDER, 1));

        addResearchPage("PURESILVER", new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("PURESILVER.3")));

        registerAlchemyRecipe(
                "PURELEAD",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 20),
                "oreLead",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        registerAlchemyRecipe(
                "PURELEAD",
                ".3",
                new ItemStack(ConfigItems.itemNugget, 4, 20),
                "oreNetherrackLead",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        registerAlchemyRecipe(
                "PURELEAD",
                ".3",
                new ItemStack(ConfigItems.itemNugget, 4, 20),
                "oreEndstoneLead",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        registerAlchemyRecipe(
                "PURELEAD",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 20),
                "oreBlackgraniteLead",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        registerAlchemyRecipe(
                "PURELEAD",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 20),
                "oreRedgraniteLead",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        registerAlchemyRecipe(
                "PURELEAD",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 20),
                "oreMarbleLead",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        registerAlchemyRecipe(
                "PURELEAD",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 20),
                "oreBasaltLead",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        registerAlchemyRecipe(
                "PURELEAD",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 20),
                "rawOreLead",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        addResearchPage("PURELEAD", new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("PURELEAD.3")));

        registerAlchemyRecipe(
                "PUREGOLD",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 31),
                "oreGold",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        registerAlchemyRecipe(
                "PUREGOLD",
                ".3",
                new ItemStack(ConfigItems.itemNugget, 4, 31),
                "oreNetherrackGold",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        registerAlchemyRecipe(
                "PUREGOLD",
                ".3",
                new ItemStack(ConfigItems.itemNugget, 4, 31),
                "oreEndstoneGold",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        registerAlchemyRecipe(
                "PUREGOLD",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 31),
                "oreBlackgraniteGold",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        registerAlchemyRecipe(
                "PUREGOLD",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 31),
                "oreRedgraniteGold",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        registerAlchemyRecipe(
                "PUREGOLD",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 31),
                "oreMarbleGold",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        registerAlchemyRecipe(
                "PUREGOLD",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 31),
                "oreBasaltGold",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 1));

        registerAlchemyRecipe(
                "PUREGOLD",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 2, 31),
                "rawOreGold",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.GREED, 1).add(Aspect.ORDER, 1));

        addResearchPage("PUREGOLD", new ResearchPage((CrucibleRecipe) WGContent.recipeList.get("PUREGOLD.3")));
    }


}
