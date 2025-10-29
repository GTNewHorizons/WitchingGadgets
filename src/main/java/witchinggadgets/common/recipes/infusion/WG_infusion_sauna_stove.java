package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.ItemList;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;

public class WG_infusion_sauna_stove {

    public static void registerSaunaStove() {
        if (WitchingGadgets.isGT5uLoaded) {
            registerInfusionRecipe(
                    "SAUNASTOVE",
                    "",
                    new ItemStack(WGContent.BlockWoodenDevice, 1, 4),
                    5,
                    new AspectList().add(Aspect.SENSES, 24).add(Aspect.WEATHER, 8).add(Aspect.WATER, 64)
                            .add(Aspect.HEAL, 16),
                    ItemList.Machine_MV_FluidHeater.get(1L),
                    new ItemStack[] { ItemList.Cover_Drain.get(1L), new ItemStack(Blocks.stone_slab),
                            new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6),
                            new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6),
                            new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6),
                            new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6),
                            new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), new ItemStack(Blocks.stone_slab) });
        } else {
            registerInfusionRecipe(
                    "SAUNASTOVE",
                    "",
                    new ItemStack(WGContent.BlockWoodenDevice, 1, 4),
                    5,
                    new AspectList().add(Aspect.SENSES, 24).add(Aspect.WEATHER, 8).add(Aspect.WATER, 64)
                            .add(Aspect.HEAL, 16),
                    new ItemStack(Items.lava_bucket),
                    new ItemStack[] { new ItemStack(Items.bucket), new ItemStack(Blocks.stone_slab),
                            new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6),
                            new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6),
                            new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6),
                            new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6),
                            new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), new ItemStack(Blocks.stone_slab) });
        }
    }
}
