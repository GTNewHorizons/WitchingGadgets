package witchinggadgets.common.recipes.other;

import static witchinggadgets.common.recipes.WG_other_recipes.addBlastTripling;
import static witchinggadgets.common.recipes.WG_other_recipes.registerCompoundRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandTriggerRegistry;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.blocks.tiles.TileEntityBlastfurnace;
import witchinggadgets.common.util.recipe.InfernalBlastfurnaceRecipe;

public class WG_other_infernal_blast_furnace {

    public static void registerInfernalBlastFurnace() {

        ItemStack ifBlFrStair = new ItemStack(
                TileEntityBlastfurnace.stairBlock,
                1,
                TileEntityBlastfurnace.stairBlock != Blocks.stone_brick_stairs ? 1 : 0);
        registerCompoundRecipe(
                "INFERNALBLASTFURNACE",
                "",
                new AspectList().add(Aspect.FIRE, 50).add(Aspect.EARTH, 50).add(Aspect.ENTROPY, 50),
                3,
                3,
                3,
                ifBlFrStair,
                ifBlFrStair,
                ifBlFrStair,
                ifBlFrStair,
                new ItemStack(Blocks.lava),
                ifBlFrStair,
                ifBlFrStair,
                ifBlFrStair,
                ifBlFrStair,
                new ItemStack(TileEntityBlastfurnace.brickBlock[9]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[10]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[11]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[12]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[13]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[14]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[15]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[16]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[17]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[0]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[1]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[2]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[3]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[4]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[5]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[6]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[7]),
                new ItemStack(TileEntityBlastfurnace.brickBlock[8]));

        WandTriggerRegistry.registerWandBlockTrigger(WitchingGadgets.instance.wgWandManager, 0, Blocks.fence, -1);
        WandTriggerRegistry.registerWandBlockTrigger(WitchingGadgets.instance.wgWandManager, 0, Blocks.iron_bars, -1);

        if (WGModCompat.railcraftAllowBlastFurnace()) WandTriggerRegistry.registerWandBlockTrigger(
                WitchingGadgets.instance.wgWandManager,
                1,
                TileEntityBlastfurnace.brickBlock[0],
                -1);
        else {
            WandTriggerRegistry.registerWandBlockTrigger(
                    WitchingGadgets.instance.wgWandManager,
                    1,
                    TileEntityBlastfurnace.brickBlock[0],
                    -1);
            WandTriggerRegistry.registerWandBlockTrigger(
                    WitchingGadgets.instance.wgWandManager,
                    1,
                    TileEntityBlastfurnace.brickBlock[4],
                    -1);
            WandTriggerRegistry.registerWandBlockTrigger(
                    WitchingGadgets.instance.wgWandManager,
                    1,
                    TileEntityBlastfurnace.brickBlock[10],
                    -1);
        }
        WandTriggerRegistry.registerWandBlockTrigger(
                WitchingGadgets.instance.wgWandManager,
                1,
                TileEntityBlastfurnace.stairBlock,
                -1);

        addBlastTripling("Cinnabar");
        InfernalBlastfurnaceRecipe
                .addRecipe(new ItemStack(ConfigItems.itemResource, 3, 3), "clusterCinnabar", 1, 440, false)
                .addBonus(new ItemStack(ConfigItems.itemNugget, 1, 5));
    }
}
