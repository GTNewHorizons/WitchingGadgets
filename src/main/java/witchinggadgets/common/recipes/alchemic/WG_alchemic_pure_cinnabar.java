package witchinggadgets.common.recipes.alchemic;

import static witchinggadgets.common.recipes.WG_alchemic_recipes.registerAlchemyRecipe;

import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;

public class WG_alchemic_pure_cinnabar {

    public static void registerPureCinnabar() {
        registerAlchemyRecipe(
                "PURECINNABAR",
                "",
                new ItemStack(ConfigItems.itemNugget, 2, 21),
                "oreCinnabar",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EXCHANGE, 2).add(Aspect.EARTH, 1).add(Aspect.POISON, 1)
                        .add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURECINNABAR",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 4, 21),
                "oreNetherrackCinnabar",
                new AspectList().add(Aspect.METAL, 4).add(Aspect.EXCHANGE, 4).add(Aspect.EARTH, 2).add(Aspect.POISON, 2)
                        .add(Aspect.ORDER, 2));

        registerAlchemyRecipe(
                "PURECINNABAR",
                ".2",
                new ItemStack(ConfigItems.itemNugget, 4, 21),
                "oreEndstoneCinnabar",
                new AspectList().add(Aspect.METAL, 4).add(Aspect.EXCHANGE, 4).add(Aspect.EARTH, 2).add(Aspect.POISON, 2)
                        .add(Aspect.ORDER, 2));

        registerAlchemyRecipe(
                "PURECINNABAR",
                "",
                new ItemStack(ConfigItems.itemNugget, 2, 21),
                "oreBlackgraniteCinnabar",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EXCHANGE, 2).add(Aspect.EARTH, 1).add(Aspect.POISON, 1)
                        .add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURECINNABAR",
                "",
                new ItemStack(ConfigItems.itemNugget, 2, 21),
                "oreRedgraniteCinnabar",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EXCHANGE, 2).add(Aspect.EARTH, 1).add(Aspect.POISON, 1)
                        .add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURECINNABAR",
                "",
                new ItemStack(ConfigItems.itemNugget, 2, 21),
                "oreMarbleCinnabar",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EXCHANGE, 2).add(Aspect.EARTH, 1).add(Aspect.POISON, 1)
                        .add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURECINNABAR",
                "",
                new ItemStack(ConfigItems.itemNugget, 2, 21),
                "oreBasaltCinnabar",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EXCHANGE, 2).add(Aspect.EARTH, 1).add(Aspect.POISON, 1)
                        .add(Aspect.ORDER, 1));

        registerAlchemyRecipe(
                "PURECINNABAR",
                "",
                new ItemStack(ConfigItems.itemNugget, 2, 21),
                "rawOreCinnabar",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EXCHANGE, 2).add(Aspect.EARTH, 1).add(Aspect.POISON, 1)
                        .add(Aspect.ORDER, 1));

    }

}
