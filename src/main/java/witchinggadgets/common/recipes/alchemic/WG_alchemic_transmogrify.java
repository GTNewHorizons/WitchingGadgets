package witchinggadgets.common.recipes.alchemic;

import static witchinggadgets.common.recipes.WG_alchemic_recipes.registerAlchemyRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class WG_alchemic_transmogrify {

    public static void registerTransmogrify() {

        registerAlchemyRecipe(
                "ALCHEMICALTRANSMOGRIFY",
                "_GRASS",
                new ItemStack(Blocks.grass),
                new ItemStack(Blocks.dirt),
                new AspectList().add(Aspect.PLANT, 8).add(Aspect.LIFE, 2));

        registerAlchemyRecipe(
                "ALCHEMICALTRANSMOGRIFY",
                "_MYCEL",
                new ItemStack(Blocks.mycelium),
                new ItemStack(Blocks.dirt),
                new AspectList().add(Aspect.PLANT, 2).add(Aspect.DARKNESS, 8));

        registerAlchemyRecipe(
                "ALCHEMICALTRANSMOGRIFY",
                "_SAND",
                new ItemStack(Blocks.sand),
                new ItemStack(Blocks.cobblestone),
                new AspectList().add(Aspect.ENTROPY, 4));

        registerAlchemyRecipe(
                "ALCHEMICALTRANSMOGRIFY",
                "_FLINT",
                new ItemStack(Items.flint),
                new ItemStack(Blocks.gravel),
                new AspectList().add(Aspect.CRYSTAL, 8));
    }
}
