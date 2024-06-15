package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.util.GT_ModHandler;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;

public class WG_infusion_bags {

    public static void registerBags() {
        if (WGConfig.bagEnder) {
            registerInfusionRecipe(
                    "ENDERBAG",
                    "",
                    new ItemStack(WGContent.ItemBag, 1, 2),
                    8,
                    new AspectList().add(Aspect.VOID, 64).add(Aspect.ELDRITCH, 64).add(Aspect.MAGIC, 128)
                            .add(Aspect.TRAVEL, 8).add(Aspect.TOOL, 32),
                    new ItemStack(WGContent.ItemBag, 1, 0),
                    new ItemStack[] { new ItemStack(Blocks.ender_chest), new ItemStack(WGContent.ItemMaterial, 1, 3),
                            new ItemStack(WGContent.ItemMaterial, 1, 5), new ItemStack(Items.ender_eye),
                            new ItemStack(WGContent.ItemMaterial, 1, 3), new ItemStack(WGContent.ItemMaterial, 1, 5) });
        }

        if (WGConfig.bagVoid) {
            registerInfusionRecipe(
                    "VOIDBAG",
                    "",
                    new ItemStack(WGContent.ItemBag, 1, 1),
                    6,
                    new AspectList().add(Aspect.VOID, 256).add(Aspect.ELDRITCH, 32).add(Aspect.ENTROPY, 64),
                    new ItemStack(WGContent.ItemBag, 1, 0),
                    new ItemStack[] { GT_ModHandler.getModItem("Railcraft", "machine.beta", 1L, 11),
                            new ItemStack(WGContent.ItemMaterial, 1, 3), new ItemStack(ConfigItems.itemResource, 1, 17),
                            new ItemStack(WGContent.ItemMaterial, 1, 3) });
        }

        if (WGConfig.capeSpectral) {
            registerInfusionRecipe(
                    "CLOAK_SPECTRAL",
                    "",
                    new ItemStack(WGContent.ItemCloak, 1, 1),
                    5,
                    new AspectList().add(Aspect.SOUL, 48).add(Aspect.TRAVEL, 48).add(Aspect.ELDRITCH, 24)
                            .add(Aspect.SENSES, 24),
                    new ItemStack(WGContent.ItemCloak),
                    new ItemStack[] { new ItemStack(Items.potionitem, 1, 8270),
                            new ItemStack(WGContent.ItemMaterial, 1, 5), new ItemStack(Items.ender_pearl),
                            new ItemStack(WGContent.ItemMaterial, 1, 5) });
        }
    }
}
