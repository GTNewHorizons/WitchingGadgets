package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.WGContent;

public class WG_infusion_wall_mirror {

    public static void registerWallMirror() {
        registerInfusionRecipe(
                "WALLMIRROR",
                "",
                new ItemStack(WGContent.BlockWallMirror),
                8,
                new AspectList().add(Aspect.VOID, 20).add(Aspect.TRAVEL, 20).add(Aspect.ELDRITCH, 20)
                        .add(Aspect.CRYSTAL, 20),
                new ItemStack(ConfigBlocks.blockMirror),
                new ItemStack[] { new ItemStack(ConfigItems.itemFocusPortableHole),
                        new ItemStack(ConfigItems.itemShard, 1, 5), new ItemStack(Items.ender_pearl),
                        new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_ingot),
                        new ItemStack(Blocks.quartz_block, 1, 1) });
    }
}
