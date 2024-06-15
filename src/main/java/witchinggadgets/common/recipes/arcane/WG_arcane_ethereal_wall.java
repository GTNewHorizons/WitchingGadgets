package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerArcaneRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.common.WGContent;

public class WG_arcane_ethereal_wall {

    public static void registerEtherealWall() {
        registerArcaneRecipe(
                "ETHEREALWALL",
                "",
                new ItemStack(WGContent.BlockStoneDevice, 6, 0),
                new AspectList().add(Aspect.ORDER, 15).add(Aspect.EARTH, 5),
                "SsS",
                "STS",
                "SsS",
                'S',
                new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6),
                's',
                new ItemStack(ConfigBlocks.blockCrystal, 1, 32767),
                'T',
                new ItemStack(Blocks.redstone_lamp));
    }
}
