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
                "",
                new ItemStack(ConfigItems.itemNugget, 2, 21),
                "rawOreCinnabar",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EXCHANGE, 2).add(Aspect.EARTH, 1).add(Aspect.POISON, 1)
                        .add(Aspect.ORDER, 1));

    }

}
