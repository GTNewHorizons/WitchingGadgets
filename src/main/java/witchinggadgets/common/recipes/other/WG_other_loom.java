package witchinggadgets.common.recipes.other;

import static witchinggadgets.common.recipes.WG_other_recipes.registerCompoundRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class WG_other_loom {

    public static void registerLoom() {
        registerCompoundRecipe(
                "LOOM",
                "",
                new AspectList().add(Aspect.AIR, 15).add(Aspect.ORDER, 15),
                2,
                2,
                3,
                new ItemStack(Blocks.fence),
                null,
                new ItemStack(Blocks.iron_bars),
                null,
                new ItemStack(Blocks.fence),
                null,
                "plankWood",
                "plankWood",
                "slabWood",
                "slabWood",
                "plankWood",
                "plankWood");
    }
}
