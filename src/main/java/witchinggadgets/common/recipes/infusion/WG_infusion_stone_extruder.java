package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.ItemList;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;

public class WG_infusion_stone_extruder {

    public static void registerStoneExtruder() {
        if (WitchingGadgets.isGT5uLoaded) {
            registerInfusionRecipe(
                    "STONEEXTRUDER",
                    "",
                    new ItemStack(WGContent.BlockWoodenDevice, 1, 2),
                    7,
                    new AspectList().add(Aspect.ENTROPY, 32).add(Aspect.EARTH, 48).add(Aspect.MINE, 64),
                    ItemList.Machine_HV_RockBreaker.get(1L),
                    new ItemStack[] { ItemList.Conveyor_Module_HV.get(1L),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 5), new ItemStack(Items.lava_bucket),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 3), ItemList.IC2_EnergyCrystal.getWildcard(1L),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 3), new ItemStack(Items.water_bucket),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 5) });
        } else {
            registerInfusionRecipe(
                    "STONEEXTRUDER",
                    "",
                    new ItemStack(WGContent.BlockWoodenDevice, 1, 2),
                    4,
                    new AspectList().add(Aspect.ENTROPY, 16).add(Aspect.EARTH, 24).add(Aspect.MINE, 32),
                    new ItemStack(Blocks.obsidian),
                    new ItemStack[] { new ItemStack(Blocks.sticky_piston),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 5), new ItemStack(Items.lava_bucket),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 3), new ItemStack(Items.redstone),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 3), new ItemStack(Items.water_bucket),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 5),
                            new ItemStack(ConfigItems.itemPickThaumium) });
        }
    }
}
