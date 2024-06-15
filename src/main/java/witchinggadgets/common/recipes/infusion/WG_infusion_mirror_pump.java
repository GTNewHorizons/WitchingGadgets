package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionRecipe;

import net.minecraft.item.ItemStack;

import gregtech.api.enums.ItemList;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.common.WGContent;

public class WG_infusion_mirror_pump {

    public static void registerMirrorPump() {
        registerInfusionRecipe(
                "MIRRORPUMP",
                "",
                new ItemStack(WGContent.BlockMetalDevice, 1, 0),
                8,
                new AspectList().add(Aspect.MOTION, 32).add(Aspect.TRAVEL, 64).add(Aspect.ORDER, 16),
                new ItemStack(ConfigBlocks.blockTube, 1, 4),
                new ItemStack[] { new ItemStack(ConfigBlocks.blockWoodenDevice), ItemList.Electric_Pump_MV.get(1L),
                        new ItemStack(ConfigBlocks.blockWoodenDevice), ItemList.Electric_Pump_MV.get(1L),
                        new ItemStack(ConfigBlocks.blockWoodenDevice), ItemList.Electric_Pump_MV.get(1L),
                        new ItemStack(ConfigBlocks.blockWoodenDevice), ItemList.Electric_Pump_MV.get(1L) });
    }
}
